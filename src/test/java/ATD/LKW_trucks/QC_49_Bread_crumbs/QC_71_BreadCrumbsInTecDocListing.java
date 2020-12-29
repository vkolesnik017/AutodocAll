package ATD.LKW_trucks.QC_49_Bread_crumbs;

import ATD.LKW_Category_car_list_page_Logic;
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

public class QC_71_BreadCrumbsInTecDocListing {
    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0", false);
    }

    @DataProvider(name = "routes", parallel = true)
    Object[] dataProvider() throws SQLException {
        return new SetUp("ATD").setUpShopWithSubroutes("subprod", "DE", "lkw_main", "lkw_category_car_list10");
    }

    @Test(dataProvider = "routes")
    @Flaky
    @Owner(value = "Kolesnik")
    @Description(value = "Test checks bread crumbs block in TecDoc listing route")
    public void testChecksBreadCrumbsInTecDocListing(String route) {
        openPage(route);

        new LKW_Category_car_list_page_Logic().checkLinksInBreadCrumbsBlockTecDocListing()
                .checkLinkClickInBreadCrumbsBlock();
    }
    @AfterMethod
    public void close() {
        closeWebDriver();
    }
}
