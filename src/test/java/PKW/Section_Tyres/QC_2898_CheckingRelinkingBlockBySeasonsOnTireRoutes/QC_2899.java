package PKW.Section_Tyres.QC_2898_CheckingRelinkingBlockBySeasonsOnTireRoutes;

import Common.SetUp;
import PKW.Tyres_page_Logic;
import io.qameta.allure.Description;
import io.qameta.allure.Owner;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import java.sql.SQLException;
import static Common.SetUp.setUpBrowser;
import static Common.CommonMethods.checkingContainsUrl;
import static PKW.CommonMethods.openPage;
import static com.codeborne.selenide.Selenide.closeWebDriver;
import static com.codeborne.selenide.WebDriverRunner.url;


public class QC_2899 {

    private Tyres_page_Logic tyresPageLogic = new Tyres_page_Logic();

    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0", false);
    }

    @DataProvider(name = "route", parallel = true)
    Object[] dataProvider() {
        return new SetUp("PKW").setUpShopsWithMainRoute("subprod", "DE", "main_tyres");
    }

    @DataProvider(name = "route2", parallel = true)
    Object[] dataProvider2() throws SQLException {
        return new SetUp("PKW").setUpShopWithSubroutes("subprod", "DE", "main_tyres", "tyres_maker,tyres,tyres2,tyres3,tyres_maker_group");
    }

    @Test(dataProvider = "route")
    @Owner(value = "Sergey_QA")
    @Description(value = "Test checking relinking block by seasons on tire routes")
    public void testCheckingRelinkingBlockBySeasonsOnTireRoutes(String route) {
        openPage(route);
        String urlFromWinterSeasons = tyresPageLogic.getUrlFromWinterSeasonsInRelinkBlock();
        tyresPageLogic.checkPresenceRelinkBlockBySeasons()
                .clickOnWinterBlockFromRelink();
        String urlPageAfterClick = url();
        Assert.assertEquals(urlFromWinterSeasons, urlPageAfterClick);
        checkingContainsUrl("winterreifen");
    }

    @Test(dataProvider = "route2")
    @Owner(value = "Sergey_QA")
    @Description(value = "Test checking relinking block by seasons on tire routes")
    public void test2CheckingRelinkingBlockBySeasonsOnTireRoutes(String route) {
        openPage(route);
        String urlFromWinterSeasons = tyresPageLogic.getUrlFromWinterSeasonsInRelinkBlock();
        tyresPageLogic.checkPresenceRelinkBlockBySeasons()
                .clickOnWinterBlockFromRelink();
        String urlPageAfterClick = url();
        Assert.assertEquals(urlFromWinterSeasons, urlPageAfterClick);
        checkingContainsUrl("winterreifen");
    }

    @AfterMethod
    public void close() {
        closeWebDriver();
    }





}
