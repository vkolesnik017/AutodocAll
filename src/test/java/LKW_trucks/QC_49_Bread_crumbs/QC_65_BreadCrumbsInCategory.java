package LKW_trucks.QC_49_Bread_crumbs;

import ATD.LKW_Categories_maker_page_Logic;
import ATD.LKW_Category_brand_page_Logic;
import ATD.LKW_Category_page_Logic;
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

public class QC_65_BreadCrumbsInCategory {
    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0");
    }

    @DataProvider(name = "routes", parallel = true)
    Object[] dataProvider() throws SQLException {
        return new SetUp().setUpShopWithSubroutes("subprod", "DE", "lkw_main", "lkw_category_brand");
    }

    @Test(dataProvider = "routes")
    @Flaky
    @Owner(value = "Kolesnik")
    @Description(value = "Test checks bread crumbs block in Category route")
    public void testChecksBreadCrumbsInCategory(String route) {
        openPage(route);
        new LKW_Category_brand_page_Logic().checkLinksInBreadCrumbsBlock().checkLinkClickInBreadCrumbsBlock();
    }
}
