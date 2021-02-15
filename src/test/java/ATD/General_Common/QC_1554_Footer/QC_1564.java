package ATD.General_Common.QC_1554_Footer;

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

public class QC_1564 {

    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0", false);
    }

    @DataProvider(name = "route", parallel = true)
    Object[] dataProvider() {
        return new SetUp("ATD").setUpShop("prod", "DE");
    }

    @Test(dataProvider = "route", enabled = true) //TODO логика теста была изменена, авто тест требует переработки
    @Flaky
    @Owner(value = "Chelombitko")
    @Description(value = "Test successful newsletter subscription")
    public void checkingSuccessfulSubscription(String route) {
        String mail = "ac_1564_" + mailinatorMailRandom();
        openPage(route);
        new Main_page_Logic().checkSuccessfulNewsletterSubscription(mail);
    }
    @AfterMethod
    private void close() {
        closeWebDriver();
    }
}
