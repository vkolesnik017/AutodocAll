package ATD.StaticPage;

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

public class QC_1547_StaticPage_mobile_app {

    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0");
    }

    @DataProvider(name = "route", parallel = true)
    Object[] dataProvider() {
        return new SetUp("ATD").setUpShop("prod", "DE");
    }

    @Test(dataProvider = "route")
    @Owner(value = "Chelombitko")
    @Flaky
    @Description(value = "Test checks elements on mobile app page")
    public void checkMobileAppPageElements(String route) {
        openPage(route);
        new Main_page_Logic().clickMobileApp()
                .checkForItemsFromTopPage()
                .checkBlockWithSlider()
                .checkAutoBannerSwitching()
                .checkBannerSwitchButtons()
                .checkReviewsBlock()
                .checkAppStoreAndGooglePlayButtonsFunctionality();
    }

    @AfterMethod
    public void close() {
        closeWebDriver();
    }
}