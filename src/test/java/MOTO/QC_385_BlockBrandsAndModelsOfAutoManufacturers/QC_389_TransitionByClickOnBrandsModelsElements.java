package MOTO.QC_385_BlockBrandsAndModelsOfAutoManufacturers;

import ATD.*;
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
import static ATD.SetUp.setUpBrowser;
import static com.codeborne.selenide.Selenide.closeWebDriver;

public class QC_389_TransitionByClickOnBrandsModelsElements {

    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0");
    }

    @DataProvider(name = "routes", parallel = true)
    Object[] dataProvider() throws SQLException {
        return new SetUp().setUpShopWithSubroutes("subprod", "DE", "moto_main", "moto_categories_maker2");
    }

    @Test(dataProvider = "routes")
    @Flaky
    @Owner(value = "Kolesnik")
    @Description(value = "Test checks transition by click on brands/models elements")
    public void testChecksTransitionByClickOnBrandsModelsElements(String route) throws SQLException {
        openPage(route);

        new Moto_Categories_maker_page_Logic()
                .clickOnMotoModel(0);
        checkingContainsUrl(new DataBase().getRouteByRouteName("DE", "moto_catalog_model3"));
    }

    @DataProvider(name = "routesCategoryMaker", parallel = true)
    Object[] dataProviderCategoryMaker() throws SQLException {
        return new SetUp().setUpShopWithSubroutes("subprod", "DE", "moto_main", "moto_category_maker3");
    }

    @Test(dataProvider = "routesCategoryMaker")
    @Flaky
    @Owner(value = "Kolesnik")
    @Description(value = "Test checks transition by click on brands/models elements")
    public void testChecksTransitionByClickOnBrandsModelsElementsCategoryMaker(String route) throws SQLException {
        openPage(route);

        new Moto_Category_maker_page_Logic()
                .clickOnMotoModel(5);
        checkingContainsUrl(new DataBase().getRouteByRouteName("DE", "moto_category_car_list_model4"));
    }

    @DataProvider(name = "routesMakers", parallel = true)
    Object[] dataProviderMakers() throws SQLException {
        return new SetUp().setUpShopWithSubroutes("subprod", "DE", "moto_main", "moto_makers");
    }

    @Test(dataProvider = "routesMakers")
    @Flaky
    @Owner(value = "Kolesnik")
    @Description(value = "Test checks transition by click on brands/models elements")
    public void testChecksTransitionByClickOnBrandsModelsElementsMakers(String route) throws SQLException {
        openPage(route);

        new Moto_makers_page_Logic()
                .clickOnImageOfMotomaker(0);
        checkingContainsUrl(new DataBase().getRouteByRouteName("DE", "moto_categories_maker4"));
    }

    @DataProvider(name = "routesCategories", parallel = true)
    Object[] dataProviderCategories() throws SQLException {
        return new SetUp().setUpShopWithSubroutes("subprod", "DE", "moto_main", "moto_categories");
    }

    @Test(dataProvider = "routesCategories")
    @Flaky
    @Owner(value = "Kolesnik")
    @Description(value = "Test checks transition by click on brands/models elements")
    public void testChecksTransitionByClickOnBrandsModelsElementsCategories(String route) throws SQLException {
        openPage(route);

        new Moto_Categories_page_Logic()
                .clickOnMotoBrands(0);
        checkingContainsUrl(new DataBase().getRouteByRouteName("DE", "moto_categories_maker"));
    }
    @AfterMethod
    public void close() {
        closeWebDriver();
    }
}
