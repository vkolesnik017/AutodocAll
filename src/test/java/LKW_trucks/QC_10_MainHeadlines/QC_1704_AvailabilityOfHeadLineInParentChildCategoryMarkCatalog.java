package LKW_trucks.QC_10_MainHeadlines;

import ATD.LKW_Parent_Category_page_Logic;
import ATD.SetUp;
import io.qameta.allure.Description;
import io.qameta.allure.Flaky;
import io.qameta.allure.Owner;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.sql.SQLException;

import static ATD.CommonMethods.openPage;
import static ATD.SetUp.setUpBrowser;
import static com.codeborne.selenide.Selenide.close;

public class QC_1704_AvailabilityOfHeadLineInParentChildCategoryMarkCatalog {
    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0");
    }

    @DataProvider(name = "routes", parallel = true)
    Object[] dataProvider() throws SQLException {
        return new SetUp().setUpShopWithSubroutes("subprod", "DE", "lkw_main", "lkw_parent_category,lkw_category2,lkw_makers");
    }

    @Test(dataProvider = "routes")
    @Flaky
    @Owner(value = "Kolesnik")
    @Description(value = "Test checks availability of headline in Parent, Child category and catalog of mark routes")
    public void testChecksAvailabilityOfHeadLineInParentChildCategoryMarkCatalog(String route) {
        openPage(route);
        new LKW_Parent_Category_page_Logic().visibilityOfHeadLine();
    }

    @AfterMethod
    private void tearDown() {
        close();
    }
}