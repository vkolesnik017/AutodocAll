package ATD.Catalog.QC_2626_TecdocCatalog_SidebarCatalog_TopCategories;

import ATD.LKW_Parent_Category_page_Logic;
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

import static ATD.CommonMethods.checkingContainsUrl;
import static ATD.CommonMethods.openPage;
import static Common.SetUp.setUpBrowser;
import static com.codeborne.selenide.Selenide.closeWebDriver;

public class QC_23 {
    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0", false);
    }

    @DataProvider(name = "routes", parallel = true)
    Object[] dataProvider() throws SQLException {
        return new SetUp("ATD").setUpShopWithSubroutes("subprod", "DE", "lkw_main", "lkw_parent_category,lkw_category,lkw_category_maker,lkw_category_brand,lkw_category_maker_brand,lkw_category_car_list14,lkw_categories_maker");
    }

    @Test(dataProvider = "routes")
    @Flaky
    @Owner(value = "Kolesnik")
    @Description(value = "Test checks transition on Parent category page from child category in Sidebar")
    public void testChecksTransitionOnParentCategoryPage(String route) throws SQLException {
        openPage(route);
        new LKW_Parent_Category_page_Logic().presenceOfAllParentCategoriesInSideBar()
                .goToParentCategoryPage("Abgasanlage");
        checkingContainsUrl(new DataBase("ATD").getRouteByRouteName("DE", "lkw_parent_category6"));
    }

    @AfterMethod
    public void close() {
        closeWebDriver();
    }
}
