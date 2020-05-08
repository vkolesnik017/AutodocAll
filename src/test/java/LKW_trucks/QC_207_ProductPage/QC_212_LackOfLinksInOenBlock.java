package LKW_trucks.QC_207_ProductPage;

import ATD.LKW_Product_page_Logic;
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

public class QC_212_LackOfLinksInOenBlock {

    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0");
    }

    @DataProvider(name = "routes", parallel = true)
    Object[] dataProvider() throws SQLException {
        return new SetUp().setUpShopWithSubroutes("subprod", "DE", "lkw_main", "lkw_product3");
    }

    @Test(dataProvider = "routes")
    @Flaky
    @Owner(value = "Kolesnik")
    @Description(value = "Test checks Lack of links in OEN block")
    public void testChecksLackOfLinksInOenBlock(String route) {
        openPage(route);
        new LKW_Product_page_Logic()
                .visibilityOfOemBlock()
                .lackOfLinksInOenBlock()
                .selectTruckInHorizontalSelector("24", "4177", "1004416")
                .lackOfLinksInOenBlock();
    }

    @AfterMethod
    private void tearDown() {
        close();
    }
}
