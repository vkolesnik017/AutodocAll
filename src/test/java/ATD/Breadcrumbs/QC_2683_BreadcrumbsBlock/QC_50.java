package ATD.Breadcrumbs.QC_2683_BreadcrumbsBlock;

import ATD.LKW_Category_maker_Logic;
import Common.SetUp;
import io.qameta.allure.Description;
import io.qameta.allure.Flaky;
import io.qameta.allure.Owner;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.sql.SQLException;

import static ATD.CommonMethods.openPage;
import static Common.SetUp.setUpBrowser;
import static com.codeborne.selenide.Selenide.closeWebDriver;

public class QC_50 {
    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0", false);
    }

    @DataProvider(name = "routes", parallel = true)
    Object[] dataProvider() throws SQLException {
        return new SetUp("ATD").setUpShopWithSubroutes("subprod", "DE", "lkw_main", "lkw_product,lkw_parent_category,lkw_category,lkw_category_maker,lkw_category_brand,lkw_category_maker_brand,lkw_category_car_list,lkw_maker_car_list,lkw_maker_car_list2,lkw_categories_maker");
    }

    @Test(dataProvider = "routes")
    @Flaky
    @Owner(value = "Kolesnik")
    @Description(value = "Test checks bread crumbs block in Categories maker route")
    public void testChecksPresenceOfBreadCrumbsBlock(String route) {
        openPage(route);
        new LKW_Category_maker_Logic().checkOfPresenceBreadCrumbsBlock();
    }
    @AfterMethod
    public void close() {
        closeWebDriver();
    }
}
