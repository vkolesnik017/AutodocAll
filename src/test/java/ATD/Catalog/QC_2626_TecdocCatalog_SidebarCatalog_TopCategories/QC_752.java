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
import static com.codeborne.selenide.Selenide.*;

public class QC_752 {
    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0", false);
    }

    @DataProvider(name = "routes", parallel = true)
    Object[] dataProvider() throws SQLException {
        return new SetUp("ATD").setUpShopWithSubroutes("subprod", "DE", "moto_main", "moto_parent_category");

    }

    @Test(dataProvider = "routes")
    @Flaky
    @Owner(value = "Kolesnik")
    @Description(value = "Test checks transition by click at child categories")
    public void testChecksTransitionByClickAtChildCategories(String route) throws SQLException {
        openPage(route);

        new Moto_Parent_Category_page_Logic()
                .clickOnChildCategory(2);
        checkingContainsUrl(new DataBase("ATD").getFullRouteByRouteAndSubroute("subprod", "DE", "moto_main", "moto_category"));

        open(new DataBase("ATD").getFullRouteByRouteAndSubroute("subprod", "DE", "moto_main", "moto_catalog"));
        new Moto_Catalog_page_Logic()
                .clickOnMainLogoInHeader()
                .clickOnImageParentCategory()
                .clickOnChildCategoryWithMoto(2);
        checkingContainsUrl(new DataBase("ATD").getFullRouteByRouteAndSubroute("subprod", "DE", "moto_main", "moto_category_car_list3"));
    }

    @DataProvider(name = "routesCategoryMaker", parallel = true)
    Object[] dataProviderCategoryMaker() throws SQLException {
        return new SetUp("ATD").setUpShopWithSubroutes("subprod", "DE", "moto_main", "moto_parent_category_maker2");

    }

    @Test(dataProvider = "routesCategoryMaker")
    @Flaky
    @Owner(value = "Kolesnik")
    @Description(value = "Test checks transition by click at child categories")
    public void testChecksTransitionByClickAtChildCategoriesCategoryMaker(String route) throws SQLException {
        openPage(route);

        new Moto_Parent_Category_maker_page_Logic()
                .clickOnChildCategory(2);
        checkingContainsUrl(new DataBase("ATD").getFullRouteByRouteAndSubroute("subprod", "DE", "moto_main", "moto_category_maker"));

    }

    @AfterMethod
    public void close() {
        closeWebDriver();
    }
}
