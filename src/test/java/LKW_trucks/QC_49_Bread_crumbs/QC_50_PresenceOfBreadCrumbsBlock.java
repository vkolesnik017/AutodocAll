package LKW_trucks.QC_49_Bread_crumbs;

import ATD.LKW_Categories_maker_page_Logic;
import ATD.LKW_Category_maker_Logic;
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

public class QC_50_PresenceOfBreadCrumbsBlock {
    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0");
    }

    @DataProvider(name = "routes", parallel = true)
    Object[] dataProvider() throws SQLException {
        return new SetUp().setUpShopWithSubroutes("subprod", "DE", "lkw_main", "lkw_product,lkw_parent_category,lkw_category,lkw_category_maker,lkw_category_brand,lkw_category_maker_brand,lkw_category_car_list,lkw_maker_car_list,lkw_maker_car_list2,lkw_categories_maker");
    }

    @Test(dataProvider = "routes")
    @Flaky
    @Owner(value = "Kolesnik")
    @Description(value = "Test checks bread crumbs block in Categories maker route")
    public void testChecksPresenceOfBreadCrumbsBlock(String route) {
        openPage(route);
        new LKW_Category_maker_Logic().checkOfPresenceBreadCrumbsBlock();
    }
}