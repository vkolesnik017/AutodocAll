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

public class QC_69 {
    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0", false);
    }

    @DataProvider(name = "routes", parallel = true)
    Object[] dataProvider() throws SQLException {
        return new SetUp("ATD").setUpShopWithSubroutes("subprod", "DE", "lkw_main", "lkw_category_maker");
    }

    @Test(dataProvider = "routes")
    @Flaky
    @Owner(value = "Kolesnik")
    @Description(value = "Test checks bread crumbs block in Category maker route")
    public void testChecksBreadCrumbsInCategoryMaker(String route) throws SQLException {
        openPage(route);

        new LKW_Category_maker_Logic().checkLinksInBreadCrumbsBlock().checkLinkClickInBreadCrumbsBlock();
    }

    @AfterMethod
    public void close() {
        closeWebDriver();
    }
}
