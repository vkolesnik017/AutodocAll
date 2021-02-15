package ATD.Registration.QC_2703_RegistrationLogin;

import ATD.Main_page_Logic;
import Common.SetUp;
import io.qameta.allure.Description;
import io.qameta.allure.Flaky;
import io.qameta.allure.Owner;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static ATD.CommonMethods.mailinatorMailRandom;
import static ATD.CommonMethods.openPage;
import static Common.SetUp.setUpBrowser;
import static com.codeborne.selenide.Selenide.closeWebDriver;

public class QC_1635 {


    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0", false);
    }

    @DataProvider(name = "route", parallel = true)
    Object[] dataProvider() {
        return new SetUp("ATD").setUpShop("prod", "DE");
    }

    @Test(dataProvider = "route")
    @Owner(value = "Chelombitko")
    @Description(value = "Registration from login button")
    @Flaky
    public void testRegistrationFromButtonLogin(String route) {
        String mail = mailinatorMailRandom();
        openPage(route);
        new Main_page_Logic().registrationFromLoginButton("QC_1635_" + mail)
                .logOuAndLoginWithUser("QC_1635_" + mail);
    }

    @AfterMethod
    public void close() {
        closeWebDriver();
    }
}
