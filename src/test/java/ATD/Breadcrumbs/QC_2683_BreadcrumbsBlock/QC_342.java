package ATD.Breadcrumbs.QC_2683_BreadcrumbsBlock;

import ATD.Moto_Category_car_list_model_page_Logic;
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

public class QC_342 {

    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0", false);
    }

    @DataProvider(name = "routes", parallel = true)
    Object[] dataProvider() throws SQLException {
        return new SetUp("ATD").setUpShopWithSubroutes("subprod", "DE", "moto_main", "moto_category_car_list_model2");
    }

    @Test(dataProvider = "routes")
    @Flaky
    @Owner(value = "Kolesnik")
    @Description(value = "Test checks structure of bread crumbs block at model listing route")
    public void testChecksStructureOfBreadCrumbsBlockAtModelListing(String route) throws SQLException {
        openPage(route);

        new Moto_Category_car_list_model_page_Logic().checkBreadCrumbsBlock();
    }

    @AfterMethod
    public void close() {
        closeWebDriver();
    }
}
