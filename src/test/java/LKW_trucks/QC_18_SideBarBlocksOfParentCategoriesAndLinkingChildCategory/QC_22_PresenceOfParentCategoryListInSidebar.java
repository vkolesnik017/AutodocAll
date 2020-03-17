package LKW_trucks.QC_18_SideBarBlocksOfParentCategoriesAndLinkingChildCategory;

import ATD.LKW_Parent_Category_page_Logic;
import ATD.SetUp;
import io.qameta.allure.Description;
import io.qameta.allure.Flaky;
import io.qameta.allure.Owner;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.sql.SQLException;

import static ATD.CommonMethods.openPage;
import static ATD.SetUp.setUpBrowser;

public class QC_22_PresenceOfParentCategoryListInSidebar {
    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0");
    }

    @DataProvider(name = "routes", parallel = true)
    Object[] dataProvider() throws SQLException {
        return new SetUp().setUpShopWithSubroutes("subprod", "DE", "lkw_main", "lkw_parent_category,lkw_category,lkw_category_maker,lkw_category_brand,lkw_category_maker_brand,lkw_category_car_list10,lkw_categories_maker");
    }

    @Test(dataProvider = "routes")
    @Flaky
    @Owner(value = "Kolesnik")
    @Description(value = "Test checks presence of all parent categories in sidebar")
    public void testChecksPresenceOfAllParentCategories(String route) {
        openPage(route);
        new LKW_Parent_Category_page_Logic().presenceOfAllParentCategoriesInSideBar();
    }
}
