package ATD.AccAnalogAlts.QC_790_AlternativesAnalogs;


import Common.DataBase;
import ATD.Product_page_Logic;
import Common.SetUp;
import io.qameta.allure.Description;
import io.qameta.allure.Flaky;
import io.qameta.allure.Owner;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.sql.SQLException;

import static Common.SetUp.setUpBrowser;
import static com.codeborne.selenide.Selenide.closeWebDriver;
import static com.codeborne.selenide.Selenide.open;

public class QC_808 {

    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0", false);
    }

    @DataProvider(name = "routes", parallel = true)
    Object[] dataProvider() throws SQLException {
        return new SetUp("ATD").setUpShopWithSubroutes("prod", "DE", "main", "product24");
    }

    @Test(dataProvider = "routes")
    @Flaky
    @Owner(value = "Romaniuta")
    @Description(value = "Test Checks Empfohlener Products Match Car")
    public void testEmpfohlenerProductsMatchCar(String route) throws SQLException {
        open(new DataBase("ATD").getFullRouteByRouteAndSubroute("prod", "DE", "main", "maker_car_list3"));
        open(route);
        new Product_page_Logic().checkAnalogProductMatchCar(route);
    }

    @AfterMethod
    public void close() {
        closeWebDriver();
    }
}
