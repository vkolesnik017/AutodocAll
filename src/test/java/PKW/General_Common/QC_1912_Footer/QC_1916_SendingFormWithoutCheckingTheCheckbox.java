package PKW.General_Common.QC_1912_Footer;

import Common.SetUp;
import PKW.Main_page_Logic;
import io.qameta.allure.Description;
import io.qameta.allure.Flaky;
import io.qameta.allure.Owner;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static Common.SetUp.setUpBrowser;
import static PKW.CommonMethods.mailRandomMailinator;
import static PKW.CommonMethods.openPage;
import static com.codeborne.selenide.Selenide.closeWebDriver;

public class QC_1916_SendingFormWithoutCheckingTheCheckbox {

    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0",false);
    }

    @DataProvider(name = "route", parallel = true)
    Object[] dataProvider() {
        return new SetUp("PKW").setUpShop("prod", "DE");
    }

    @Test(dataProvider = "route")
    @Flaky
    @Owner(value = "LavrynenkoOlha")
    @Description(value = "Error pop-up after sending the subscribe form in the footer")
    public void testSendingTheSubscribeFormWithoutCheckbox(String route) {
        openPage(route);
        String mail = mailRandomMailinator("1916");
        new Main_page_Logic().errorPopUpInTheSubscribeForm(mail);
    }

    @AfterMethod
    private void close() {
        closeWebDriver();
    }
}