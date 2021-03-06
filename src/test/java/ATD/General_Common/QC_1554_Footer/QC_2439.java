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

import static ATD.CommonMethods.openPage;
import static Common.SetUp.setUpBrowser;
import static com.codeborne.selenide.Selenide.closeWebDriver;

public class QC_2439 {

    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0", false);
    }

    @DataProvider(name = "route", parallel = false)
    Object[] dataProvider() {
        return new SetUp("ATD").setUpShopsWithMainRoute("prod", "AT,BG,BE,CZ,DE,DK,EE,ES,FI,FR,EN,GR,HU,IT,LD,LT,LV,NL,NO,PL,PT,RO,SE,SI,SK", "main");
    }

    @Flaky
    @Owner(value = "LavrynenkoOlha")
    @Test(dataProvider = "route", enabled = false)
    @Description(value = "Test check the transition to the Instagram in the Social Network Block")
    public void checkingTransitionInTheSocialNetworksBlock(String route) {

        openPage(route);
        new Main_page_Logic().checkingTransitionToTheInstagramByImage()
                .checkingTransitionToTheInstagramByLink();
    }

    @AfterMethod
    private void close() {
        closeWebDriver();
    }
}

