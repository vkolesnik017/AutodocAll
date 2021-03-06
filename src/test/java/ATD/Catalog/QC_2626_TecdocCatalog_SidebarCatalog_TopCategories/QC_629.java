package ATD.Catalog.QC_2626_TecdocCatalog_SidebarCatalog_TopCategories;

import ATD.*;
import Common.DataBase;
import Common.SetUp;
import io.qameta.allure.Description;
import io.qameta.allure.Flaky;
import io.qameta.allure.Owner;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.sql.SQLException;

import static Common.CommonMethods.checkingContainsUrl;
import static ATD.CommonMethods.openPage;
import static Common.SetUp.setUpBrowser;
import static com.codeborne.selenide.Selenide.closeWebDriver;

public class QC_629 {

    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0", false);
    }

    @DataProvider(name = "routes", parallel = true)
    Object[] dataProvider() throws SQLException {
        return new SetUp("ATD").setUpShopWithSubroutes("subprod", "DE", "moto_main", "moto_categories");
    }

    @Test(dataProvider = "routes")
    @Flaky
    @Owner(value = "Kolesnik")
    @Description(value = "Test checks transition by click on child categories")
    public void testChecksTransitionByClickOnChildCategories(String route) throws SQLException {
        openPage(route);

        new Moto_Categories_page_Logic()
                .clickOnChildCategory();
        checkingContainsUrl(new DataBase("ATD").getRouteByRouteName("DE", "moto_category5"));
    }

   @DataProvider(name = "routesCatalog", parallel = true)
    Object[] dataProviderCatalog() throws SQLException {
        return new SetUp("ATD").setUpShopWithSubroutes("subprod", "DE", "moto_main", "moto_catalog2");
    }

    @Test(dataProvider = "routesCatalog")
    @Flaky
    @Owner(value = "Kolesnik")
    @Description(value = "Test checks transition by click on child categories")
    public void testChecksTransitionByClickOnChildCategoriesCatalog(String route) throws SQLException {
        openPage(route);

        new Moto_Catalog_page_Logic()
                .clickOnChildCategory();
        checkingContainsUrl(new DataBase("ATD").getRouteByRouteName("DE", "moto_category_car_list8"));
    }

    @DataProvider(name = "routesSelectMoto", parallel = true)
    Object[] dataProviderSelectMoto() throws SQLException {
        return new SetUp("ATD").setUpShopWithSubroutes("subprod", "DE", "moto_main", "moto_categories"); // ,moto_catalog_model2
    }

    @Test(dataProvider = "routesSelectMoto")
    @Flaky
    @Owner(value = "Kolesnik")
    @Description(value = "Test checks transition by click on child categories")
    public void testChecksTransitionByClickOnChildCategoriesSelectMoto(String route) throws SQLException {
        openPage(route);

        new Moto_Categories_page_Logic()
                .selectMotoWithOutTransition("4081", "12008", "135713")
                .clickOnChildCategory();
        checkingContainsUrl(new DataBase("ATD").getRouteByRouteName("DE", "moto_category_car_list10"));
    }

    @DataProvider(name = "routesWithNotAllValuesOfMoto", parallel = true)
    Object[] dataProviderWithNotAllValuesOfMoto() throws SQLException {
        return new SetUp("ATD").setUpShopWithSubroutes("subprod", "DE", "moto_main", "moto_catalog_model2");
    }

    @Test(dataProvider = "routesWithNotAllValuesOfMoto")
    @Flaky
    @Owner(value = "Kolesnik")
    @Description(value = "Test checks transition by click on child categories")
    public void testChecksTransitionByClickOnChildCategoriesWithNotAllValuesOfMoto(String route) {
        openPage(route);

        new Moto_Catalog_model_page_Logic()
                .clickOnChildCategoryWithOutAllValuesInSelector()
                .openingOfSelectorWithTooltip();
    }

    @AfterMethod
    public void close() {
        closeWebDriver();
    }
}
