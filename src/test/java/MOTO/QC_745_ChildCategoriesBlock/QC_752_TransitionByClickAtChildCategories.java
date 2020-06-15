package MOTO.QC_745_ChildCategoriesBlock;

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
import static com.codeborne.selenide.Selenide.close;
import static com.codeborne.selenide.Selenide.open;

public class QC_752_TransitionByClickAtChildCategories {
    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0");
    }

    @DataProvider(name = "routes", parallel = true)
    Object[] dataProvider() throws SQLException {
        return new SetUp().setUpShopWithSubroutes("subprod", "DE", "moto_main", "moto_parent_category");

    }

    @Test(dataProvider = "routes")
    @Flaky
    @Owner(value = "Kolesnik")
    @Description(value = "Test checks transition by click at child categories")
    public void testChecksTransitionByClickAtChildCategories(String route) throws SQLException {
        openPage(route);

        new Moto_Parent_Category_page_Logic()
                .clickOnChildCategory(2);
        checkingContainsUrl(new DataBase().getFullRouteByRouteAndSubroute("subprod", "DE", "moto_main", "moto_category"));

        open(new DataBase().getFullRouteByRouteAndSubroute("subprod", "DE", "moto_main", "moto_catalog"));
        new Moto_Catalog_page_Logic()
                .clickOnMainLogoInHeader()
                .clickOnImageParentCategory()
                .clickOnChildCategoryWithMoto(2);
        checkingContainsUrl(new DataBase().getFullRouteByRouteAndSubroute("subprod", "DE", "moto_main", "moto_category_car_list3"));
    }

    @DataProvider(name = "routesCategoryMaker", parallel = true)
    Object[] dataProviderCategoryMaker() throws SQLException {
        return new SetUp().setUpShopWithSubroutes("subprod", "DE", "moto_main", "moto_parent_category_maker2");

    }

    @Test(dataProvider = "routesCategoryMaker")
    @Flaky
    @Owner(value = "Kolesnik")
    @Description(value = "Test checks transition by click at child categories")
    public void testChecksTransitionByClickAtChildCategoriesCategoryMaker(String route) throws SQLException {
        openPage(route);

        new Moto_Parent_Category_maker_page_Logic()
                .clickOnChildCategory(2);
        checkingContainsUrl(new DataBase().getFullRouteByRouteAndSubroute("subprod", "DE", "moto_main", "moto_category_maker"));

    }

    @AfterMethod
    private void tearDown() {
        close();
    }
}