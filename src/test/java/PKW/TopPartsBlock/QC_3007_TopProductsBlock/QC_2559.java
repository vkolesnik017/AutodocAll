package PKW.TopPartsBlock.QC_3007_TopProductsBlock;

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

import static ATD.CommonMethods.openPage;
import static Common.SetUp.setUpBrowser;
import static com.codeborne.selenide.Selenide.closeWebDriver;

public class QC_2559 {

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
    @Description(value = "Test сheck main elements of TOP product block")
    public void testCheckLinkingBlockByBrands(String route) {
        openPage(route);
        new Tyres_page_Logic().presenceOfTopProductBlock().checkSizeOfTopProducts(12).checkAbilityToAddTopProductToCart();
    }

    @DataProvider(name = "routesTyres", parallel = true)
    Object[] dataProviderTyres() throws SQLException {
        return new Common.SetUp("PKW").setUpShopWithSubroutes("subprod", "DE", "main_tyres", "tyres,tyres2");  //,tyres3
    }

    @Test(dataProvider = "routesTyres")
    @Flaky
    @Owner(value = "Kolesnik")
    @Description(value = "Test сheck main elements of TOP product block")
    public void testCheckLinkingBlockByBrandsTyres(String route) {
        openPage(route);
        new Tyres_page_Logic().presenceOfTopProductBlock().checkSizeOfTopProducts(12).checkAbilityToAddTopProductToCart();
    }

    @DataProvider(name = "routesTyresMaker", parallel = true)
    Object[] dataProviderTyresMaker() throws SQLException {
        return new Common.SetUp("PKW").setUpShopWithSubroutes("subprod", "DE", "main_tyres", "tyres_maker,tyres_maker_group");
    }

    @Test(dataProvider = "routesTyresMaker")
    @Flaky
    @Owner(value = "Kolesnik")
    @Description(value = "Test сheck main elements of TOP product block")
    public void testCheckLinkingBlockByBrandsTyresMaker(String route) {
        openPage(route);
        new Tyres_maker_page_Logic().presenceOfTopProductBlock().checkSizeOfTopProducts(12).checkAbilityToAddTopProductToCart();
    }
    @AfterMethod
    public void close() {
        closeWebDriver();
    }
}

