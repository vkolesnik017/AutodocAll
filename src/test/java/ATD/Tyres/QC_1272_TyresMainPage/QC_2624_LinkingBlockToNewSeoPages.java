package ATD.Tyres.QC_1272_TyresMainPage;

import ATD.Tyres_feature_page_Logic;
import ATD.Tyres_page_Logic;
import Common.SetUp;
import io.qameta.allure.Description;
import io.qameta.allure.Flaky;
import io.qameta.allure.Owner;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.sql.SQLException;

import static ATD.CommonMethods.openPage;
import static Common.SetUp.setUpBrowser;
import static com.codeborne.selenide.Selenide.closeWebDriver;
import static com.codeborne.selenide.WebDriverRunner.url;

public class QC_2624_LinkingBlockToNewSeoPages {
    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0", false);
    }

    @DataProvider(name = "routes", parallel = false)
    Object[] dataProvider() throws SQLException {
        return new SetUp("ATD").setUpShopWithSubroutes("prod", "DE", "main", "tyres,tyres2,tyres3,tyres4");
    }

    @Test(dataProvider = "routes")
    @Flaky
    @Owner(value = "Kolesnik")
    @Description(value = "Test сheck linking block to new SEO pages")
    public void testCheckLinkingBlockToNewSeoPages(String route) {
        openPage(route);
        String currentUrl = url();
        new Tyres_page_Logic().presenceOfLinkingBlock().checkSwipeByClick().totalCountOfLinkingBlock(7)
                .appearanceOfAnimationInLinkingBlock().checkTitlesOfLinkingBlock().presenceOfHeadlineAtLinkingBlock().checkTransitionsInLinkingBlock(currentUrl);
    }

    @DataProvider(name = "routesFeature", parallel = false)
    Object[] dataProviderFeature() throws SQLException {
        return new SetUp("ATD").setUpShopWithSubroutes("prod", "DE", "main", "tyres_feature,tyres_feature2,tyres_feature3,tyres_feature4,tyres_feature5,tyres_feature6,tyres_feature7");
    }

    @Test(dataProvider = "routesFeature")
    @Flaky
    @Owner(value = "Kolesnik")
    @Description(value = "Test сheck linking block to new SEO pages")
    public void testCheckLinkingBlockToNewSeoPagesFeature(String route) {
        openPage(route);
        String currentUrl = url();
        new Tyres_feature_page_Logic().presenceOfLinkingBlock().checkSwipeByClick().totalCountOfLinkingBlock(6)
                .appearanceOfAnimationInLinkingBlock().checkTitlesOfLinkingBlock().presenceOfHeadlineAtLinkingBlock().checkTransitionsInLinkingBlock(currentUrl);
    }

    @AfterMethod
    public void close() {
        closeWebDriver();
    }
}
