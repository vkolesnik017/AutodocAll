package PKW.Tyres.QC_2900_LinkingBlockOfBrands;

import Common.DataBase;
import PKW.Tyres_maker_page_Logic;
import PKW.Tyres_page_Logic;
import io.qameta.allure.Description;
import io.qameta.allure.Flaky;
import io.qameta.allure.Owner;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.sql.SQLException;

import static ATD.CommonMethods.checkingContainsUrl;
import static ATD.CommonMethods.openPage;
import static Common.SetUp.setUpBrowser;
import static com.codeborne.selenide.Selenide.closeWebDriver;
import static com.codeborne.selenide.WebDriverRunner.url;

public class QC_2901 {
    private Tyres_page_Logic tyresPage = new Tyres_page_Logic();
    private Tyres_maker_page_Logic tyresMakerPage = new Tyres_maker_page_Logic();
    private DataBase db = new DataBase("PKW");

    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0", false);
    }

    @DataProvider(name = "routes", parallel = true)
    Object[] dataProvider() {
        return new Common.SetUp("PKW").setUpShopsWithMainRoute("subprod", "DE", "main_tyres");
    }

    @Test(dataProvider = "routes")
    @Flaky
    @Owner(value = "Kolesnik")
    @Description(value = "Test checking the linking block by brands")
    public void testCheckLinkingBlockByBrands(String route) throws SQLException {
        openPage(route);
        tyresPage.presenceOfBrandsLinkingBlock().selectBrandByName("Michelin");
        checkingContainsUrl("michelin");
        openPage(db.getFullRouteByRouteName("subprod", "DE", "main_tyres"));
        tyresPage.presenceOfBrandsLinkingBlock().clickOnAllBrands();
        checkingContainsUrl(db.getRouteByRouteName("DE", "tyres_type_list_brands"));
    }

    @DataProvider(name = "routesTyres", parallel = true)
    Object[] dataProviderTyres() throws SQLException {
        return new Common.SetUp("PKW").setUpShopWithSubroutes("subprod", "DE", "main_tyres", "tyres,tyres2,tyres3");
    }

    @Test(dataProvider = "routesTyres")
    @Flaky
    @Owner(value = "Kolesnik")
    @Description(value = "Test checking the linking block by brands")
    public void testCheckLinkingBlockByBrandsTyres(String route) {
        openPage(route);
        String currentUrl = url();
        tyresPage.presenceOfBrandsLinkingBlock().selectBrandByName("Michelin");
        checkingContainsUrl("michelin");
        openPage(currentUrl);
        tyresPage.presenceOfBrandsLinkingBlock().clickOnAllBrands();
        checkingContainsUrl("all_brands");
    }

    @DataProvider(name = "routesTyresMaker", parallel = true)
    Object[] dataProviderTyresMaker() throws SQLException {
        return new Common.SetUp("PKW").setUpShopWithSubroutes("subprod", "DE", "main_tyres", "tyres_maker");
    }

    @Test(dataProvider = "routesTyresMaker")
    @Flaky
    @Owner(value = "Kolesnik")
    @Description(value = "Test checking the linking block by brands")
    public void testCheckLinkingBlockByBrandsTyresMaker(String route) throws SQLException {
        openPage(route);
        tyresMakerPage.presenceOfBrandsLinkingBlock().selectBrandByName("Michelin");
        checkingContainsUrl("michelin");
    }

    @AfterMethod
    public void close() {
        closeWebDriver();
    }
}
