package PKW.General_Common.QC_1912_Footer;

import PKW.Main_page_Logic;
import PKW.SetUp;
import io.qameta.allure.Description;
import io.qameta.allure.Flaky;
import io.qameta.allure.Owner;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import static PKW.CommonMethods.*;
import static PKW.SetUp.setUpBrowser;
import static com.codeborne.selenide.Selenide.closeWebDriver;

public class QC_1913_PositiveSendingTheSubscribeFormFooter {

    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0");
    }

    @DataProvider(name = "route", parallel = true)
    Object[] dataProvider() {
        return new SetUp().setUpShop("prod", "DE");
    }

    @Test(dataProvider = "route")
    @Flaky
    @Owner(value = "LavrynenkoOlha")
    @Description(value = "Positive Sending The Subscribe Form in the Footer")
    public void testSendingTheSubscribeForm(String route) {
        openPage(route);
        String mail = mailRandomMailinator("1913");
        new Main_page_Logic().positiveSendingSubscribeForm(mail);
    }

    @AfterMethod
    private void close() {
        closeWebDriver();
    }
}