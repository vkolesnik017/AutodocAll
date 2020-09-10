package MOTO.QC_330_BlockOfMainHeadline;

import ATD.Moto_Categories_maker_page_Logic;
import ATD.Moto_makers_page_Logic;
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

public class QC_333_MatchingQuantityOfBrandsAndModels {
    private Moto_Categories_maker_page_Logic categoriesMakerPage = new Moto_Categories_maker_page_Logic();
    private Moto_makers_page_Logic makersPage = new Moto_makers_page_Logic();

    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0");
    }

    @DataProvider(name = "routes", parallel = true)
    Object[] dataProvider() throws SQLException {
        return new SetUp("ATD").setUpShopWithSubroutes("subprod", "DE", "moto_main", "moto_categories_maker2");
    }

    @Test(dataProvider = "routes")
    @Flaky
    @Owner(value = "Kolesnik")
    @Description(value = "Test checks matching of quantity brands and model in headline and on page")
    public void testChecksMatchingQuantityOfBrandsAndModels(String route) {
        openPage(route);
        int totalCountOfModels = categoriesMakerPage.getCountOfModelsFromTitle();
        categoriesMakerPage.comparingQuantityOfModels(totalCountOfModels);
    }

    @DataProvider(name = "routesMakers", parallel = true)
    Object[] dataProviderMakers() throws SQLException {
        return new SetUp("ATD").setUpShopWithSubroutes("subprod", "DE", "moto_main", "moto_makers");
    }

    @Test(dataProvider = "routesMakers")
    @Flaky
    @Owner(value = "Kolesnik")
    @Description(value = "Test checks matching of quantity brands and model in headline and on page")
    public void testChecksMatchingQuantityOfBrandsAndModelsMakers(String route) {
        openPage(route);
        int totalCountOfBrands = makersPage.getCountOfModelsFromTitle();
        makersPage.comparingQuantityOfModels(totalCountOfBrands);
    }

    @AfterMethod
    public void close() {
        closeWebDriver();
    }

}
