package ATD.TopPartsBlock.QC_2689_TopProductsBlock;

import ATD.LKW_Categories_maker_page_Logic;
import ATD.LKW_Category_maker_Logic;
import Common.SetUp;
import io.qameta.allure.Description;
import io.qameta.allure.Flaky;
import io.qameta.allure.Owner;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.sql.SQLException;
import java.util.List;

import static ATD.CommonMethods.openPage;
import static Common.SetUp.setUpBrowser;
import static com.codeborne.selenide.Selenide.closeWebDriver;

public class QC_80 {
    private LKW_Category_maker_Logic categoryMakerPage = new LKW_Category_maker_Logic();
    private LKW_Categories_maker_page_Logic categoriesMakerPage = new LKW_Categories_maker_page_Logic();

    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0", false);
    }

    @DataProvider(name = "routes", parallel = true)
    Object[] dataProvider() throws SQLException {
        return new SetUp("ATD").setUpShopWithSubroutes("subprod", "DE", "lkw_main", "lkw_category_maker2,lkw_category_maker_brand");
    }

    @Test(dataProvider = "routes")
    @Flaky
    @Owner(value = "Kolesnik")
    @Description(value = "Test check applicability of products from TOP block to truck")
    public void testChecksApplicabilityProductToTruck(String route) {
        openPage(route);
        String truck = categoryMakerPage.getSelectedTruck();
        List<String> allProductsUrl = categoryMakerPage.getProductUrls();
        categoryMakerPage.applicabilityOfTopProductToTruck(truck,allProductsUrl);
    }

    @DataProvider(name = "routesCategoriesMaker", parallel = true)
    Object[] dataProviderCategoriesMaker() throws SQLException {
        return new SetUp("ATD").setUpShopWithSubroutes("subprod", "DE", "lkw_main", "lkw_categories_maker3");
    }

    @Test(dataProvider = "routesCategoriesMaker")
    @Flaky
    @Owner(value = "Kolesnik")
    @Description(value = "Test check applicability of products from TOP block to truck")
    public void testChecksApplicabilityProductToTruckCategoriesMaker(String route) {
        openPage(route);
        String truck = categoriesMakerPage.getSelectedTruck();
        List<String> allProductsUrl = categoriesMakerPage.getProductUrls();
        categoriesMakerPage.applicabilityOfTopProductToTruck(truck,allProductsUrl);
    }

    @AfterMethod
    public void close() {
        closeWebDriver();
    }
}
