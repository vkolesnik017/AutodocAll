package LKW_trucks.QC_49_Bread_crumbs;

import ATD.LKW_Category_maker_brand_page_Logic;
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

public class QC_67_BreadCrumbsInCategoryParent {
    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0");
    }

    @DataProvider(name = "routes", parallel = true)
    Object[] dataProvider() throws SQLException {
        return new SetUp().setUpShopWithSubroutes("subprod", "DE", "lkw_main", "lkw_parent_category");
    }

    @Test(dataProvider = "routes")
    @Flaky
    @Owner(value = "Kolesnik")
    @Description(value = "Test checks bread crumbs block in Parent category route")
    public void testChecksBreadCrumbsInParentCategory(String route) {
        openPage(route);
        new LKW_Parent_Category_page_Logic().checkLinksInBreadCrumbsBlock();
    }
}
