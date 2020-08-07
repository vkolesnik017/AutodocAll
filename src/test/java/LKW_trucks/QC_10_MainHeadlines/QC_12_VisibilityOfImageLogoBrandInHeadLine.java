package LKW_trucks.QC_10_MainHeadlines;

import ATD.LKW_Category_brand_page_Logic;
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
import static com.codeborne.selenide.Selenide.closeWebDriver;

public class QC_12_VisibilityOfImageLogoBrandInHeadLine {
    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0");
    }

    @DataProvider(name = "routes", parallel = true)
    Object[] dataProvider() throws SQLException {
        return new SetUp().setUpShopWithSubroutes("subprod", "DE", "lkw_main", "lkw_category_brand,lkw_category_maker_brand");
    }

    @Test(dataProvider = "routes")
    @Flaky
    @Owner(value = "Kolesnik")
    @Description(value = "Test checks visibility of image logo brand in headline")
    public void testChecksVisibilityOfImageLogoBrandInHeadLine(String route) {
        openPage(route);
        new LKW_Category_brand_page_Logic().visibilityOfImageBrandInHeadLine();
    }

    @AfterMethod
    public void close() {
        closeWebDriver();
    }
}
