package ATD.Selectors.QC_301_MotoSelector;

import ATD.Moto_main_page_Logic;
import Common.SetUp;
import io.qameta.allure.Description;
import io.qameta.allure.Flaky;
import io.qameta.allure.Owner;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.List;

import static ATD.CommonMethods.openPage;
import static Common.SetUp.setUpBrowser;
import static com.codeborne.selenide.Selenide.closeWebDriver;

public class QC_2726 {
    private String dataFile = "C:/Autotests/files/data/QC_2726_data.xls";
    private Moto_main_page_Logic mainPage = new Moto_main_page_Logic();

    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0", false);
    }

    @DataProvider(name = "routes", parallel = false)
    Object[] dataProvider() {
        return new SetUp("ATD").setUpShopsWithMainRoute("subprod", "DE,AT,BG,CH,CZ,DK,EE,FI,EN,GR,HU,LD,LT,LV,NL,NO,PL,RO,SE,SI,SK", "moto_main");
    }

    @Test(dataProvider = "routes")
    @Flaky
    @Owner(value = "Kolesnik")
    @Description(value = "Test checking prioritizing child by sales on catalog page with top car")
    public void testPrioritizingChildBySalesOnCatalogPageWithTopCar(String route) {
        openPage(route);
        List<String> topMotoBrands = mainPage.getTopMotoBrandsFromSelector();
        mainPage.presenceTopBrandsAndModelsInSelector(topMotoBrands, dataFile, "DE");
    }

    @DataProvider(name = "routesES", parallel = true)
    Object[] dataProviderES() {
        return new SetUp("ATD").setUpShopsWithMainRoute("subprod", "ES", "moto_main");
    }

    @Test(dataProvider = "routesES")
    @Flaky
    @Owner(value = "Kolesnik")
    @Description(value = "Test checking prioritizing child by sales on catalog page with top car")
    public void testPrioritizingChildBySalesOnCatalogPageWithTopCarES(String route) {
        openPage(route);
        List<String> topMotoBrands = mainPage.getTopMotoBrandsFromSelector();
        mainPage.presenceTopBrandsAndModelsInSelector(topMotoBrands, dataFile, "ES");
    }

    @DataProvider(name = "routesFR", parallel = true)
    Object[] dataProviderFR() {
        return new SetUp("ATD").setUpShopsWithMainRoute("subprod", "FR", "moto_main");
    }

    @Test(dataProvider = "routesFR")
    @Flaky
    @Owner(value = "Kolesnik")
    @Description(value = "Test checking prioritizing child by sales on catalog page with top car")
    public void testPrioritizingChildBySalesOnCatalogPageWithTopCarFR(String route) {
        openPage(route);
        List<String> topMotoBrands = mainPage.getTopMotoBrandsFromSelector();
        mainPage.presenceTopBrandsAndModelsInSelector(topMotoBrands, dataFile, "FR");
    }

    @DataProvider(name = "routesIT", parallel = true)
    Object[] dataProviderIT() {
        return new SetUp("ATD").setUpShopsWithMainRoute("subprod", "IT", "moto_main");
    }

    @Test(dataProvider = "routesIT")
    @Flaky
    @Owner(value = "Kolesnik")
    @Description(value = "Test checking prioritizing child by sales on catalog page with top car")
    public void testPrioritizingChildBySalesOnCatalogPageWithTopCarIT(String route) {
        openPage(route);
        List<String> topMotoBrands = mainPage.getTopMotoBrandsFromSelector();
        mainPage.presenceTopBrandsAndModelsInSelector(topMotoBrands, dataFile, "IT");
    }

    @DataProvider(name = "routesPT", parallel = true)
    Object[] dataProviderPT() {
        return new SetUp("ATD").setUpShopsWithMainRoute("subprod", "PT", "moto_main");
    }

    @Test(dataProvider = "routesPT")
    @Flaky
    @Owner(value = "Kolesnik")
    @Description(value = "Test checking prioritizing child by sales on catalog page with top car")
    public void testPrioritizingChildBySalesOnCatalogPageWithTopCarPT(String route) {
        openPage(route);
        List<String> topMotoBrands = mainPage.getTopMotoBrandsFromSelector();
        mainPage.presenceTopBrandsAndModelsInSelector(topMotoBrands, dataFile, "PT");
    }
    
    @AfterMethod
    public void close() {
        closeWebDriver();
    }
}
