package LKW_trucks.QC_10_MainHeadlines;

import ATD.LKW_Category_maker_brand_page_Logic;
import ATD.LKW_maker_car_list_Logic;
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

public class QC_16_VisibilityOfMarkModelMotorInHeadLine {
    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0");
    }

    @DataProvider(name = "routes", parallel = true)
    Object[] dataProvider() throws SQLException {
        return new SetUp().setUpShopWithSubroutes("subprod", "DE", "lkw_main", "lkw_maker_car_list");
    }

    @Test(dataProvider = "routes")
    @Flaky
    @Owner(value = "Kolesnik")
    @Description(value = "Test checks visibility of mark, model and motor in headline")
    public void testChecksVisibilityOfMarkModelMotorInHeadLine(String route) {
        openPage(route);
        new LKW_maker_car_list_Logic().visibilityOfMakrModelMotorInHeadLine();

    }

    @AfterMethod
    private void tearDown() {
        close();
    }
}
