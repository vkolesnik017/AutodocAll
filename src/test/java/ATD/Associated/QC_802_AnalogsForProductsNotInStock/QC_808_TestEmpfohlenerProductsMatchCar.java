package ATD.Associated.QC_802_AnalogsForProductsNotInStock;


import ATD.DataBase;
import ATD.Product_page_Logic;
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
import static com.codeborne.selenide.Selenide.open;

public class QC_808_TestEmpfohlenerProductsMatchCar {

    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0");
    }

    @DataProvider(name = "routes", parallel = true)
    Object[] dataProvider() throws SQLException {
        return new SetUp().setUpShopWithSubroutes("prod", "DE", "main", "product24");
    }

    @Test(dataProvider = "routes")
    @Flaky
    @Owner(value = "Romaniuta")
    @Description(value = "Test Checks Empfohlener Products Match Car")
    public void testEmpfohlenerProductsMatchCar(String route) throws SQLException {
        open(new DataBase().getFullRouteByRouteAndSubroute("prod", "DE", "main", "maker_car_list3"));
        open(route);
        new Product_page_Logic().checkAnalogProductMatchCar(route);
    }

    @AfterMethod
    public void tearDown() {
        close();
    }
}
