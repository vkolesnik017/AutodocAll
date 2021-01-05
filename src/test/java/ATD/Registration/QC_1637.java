package ATD.Registration;

import ATD.Main_page_Logic;
import Common.SetUp;
import io.qameta.allure.Description;
import io.qameta.allure.Flaky;
import io.qameta.allure.Owner;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static ATD.CommonMethods.*;
import static Common.SetUp.setUpBrowser;
import static com.codeborne.selenide.Selenide.closeWebDriver;

public class QC_1637 {


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
    @Description(value = "Registration from basket")
    @Flaky
    public void TestRegistrationInBasket(String route) {
        String mail = mailRandom();
        openPage(route);
        new Main_page_Logic().useSearch(ridex_82B0896)
                .addFirstProductAndGoToCart()
                .nextButtonClick()
                .registrationFromCartAndBackToMainPage("QC_1637_" + mail)
                .logOuAndLoginWithUser("QC_1637_" + mail);
    }

    @AfterMethod
    public void close() {
        closeWebDriver();
    }
}
