package ATD.Registration;

import ATD.Main_page_Logic;
import ATD.SetUp;
import io.qameta.allure.Description;
import io.qameta.allure.Flaky;
import io.qameta.allure.Owner;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static ATD.CommonMethods.mailRandom;
import static ATD.CommonMethods.openPage;
import static ATD.SetUp.setUpBrowser;
import static com.codeborne.selenide.Selenide.close;

public class QC_1635_RegistrationFromLoginButton {


    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0");
    }

    @DataProvider(name = "route", parallel = true)
    Object[] dataProvider() {
        return new SetUp().setUpShop("prod", "DE");
    }

    @Test(dataProvider = "route")
    @Owner(value = "Chelombitko")
    @Description(value = "Registration from login button")
    @Flaky
    public void testRegistrationFromButtonLogin(String route) {
        String mail = mailRandom();
        openPage(route);
        new Main_page_Logic().registrationFromLoginButton(mail)
                .logOuAndLoginWithUser(mail);
    }

    @AfterMethod
    private void tearDown() {
        close();
    }
}
