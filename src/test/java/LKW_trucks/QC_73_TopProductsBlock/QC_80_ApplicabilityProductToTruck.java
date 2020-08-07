package LKW_trucks.QC_73_TopProductsBlock;

import ATD.LKW_Categories_maker_page_Logic;
import ATD.LKW_Category_maker_Logic;
import ATD.SetUp;
import io.qameta.allure.Description;
import io.qameta.allure.Flaky;
import io.qameta.allure.Owner;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.sql.SQLException;

import static ATD.CommonMethods.openPage;
import static ATD.SetUp.setUpBrowser;
import static com.codeborne.selenide.Selenide.closeWebDriver;

public class QC_80_ApplicabilityProductToTruck {
    private LKW_Category_maker_Logic categoryMakerPage = new LKW_Category_maker_Logic();
    private LKW_Categories_maker_page_Logic categoriesMakerPage = new LKW_Categories_maker_page_Logic();

    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0");
    }

    @DataProvider(name = "routes", parallel = true)
    Object[] dataProvider() throws SQLException {
        return new SetUp().setUpShopWithSubroutes("subprod", "DE", "lkw_main", "lkw_category_maker2,lkw_category_maker_brand");
    }

    @Test(dataProvider = "routes")
    @Flaky
    @Owner(value = "Kolesnik")
    @Description(value = "Test check applicability of products from TOP block to truck")
    public void testChecksApplicabilityProductToTruck(String route) {
        openPage(route);
        String truck = categoryMakerPage.getSelectedTruck();
        categoryMakerPage.applicabilityOfTopProductToTruck(truck);
    }

    @DataProvider(name = "routesCategoriesMaker", parallel = true)
    Object[] dataProviderCategoriesMaker() throws SQLException {
        return new SetUp().setUpShopWithSubroutes("subprod", "DE", "lkw_main", "lkw_categories_maker3");
    }

    @Test(dataProvider = "routesCategoriesMaker")
    @Flaky
    @Owner(value = "Kolesnik")
    @Description(value = "Test check applicability of products from TOP block to truck")
    public void testChecksApplicabilityProductToTruckCategoriesMaker(String route) {
        openPage(route);
        String truck = categoriesMakerPage.getSelectedTruck();
        categoriesMakerPage.applicabilityOfTopProductToTruck(truck);
    }

    @AfterMethod
    public void close() {
        closeWebDriver();
    }}
