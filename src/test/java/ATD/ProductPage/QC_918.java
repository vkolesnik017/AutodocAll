package ATD.ProductPage;


import Common.DataBase;
import ATD.Product_page_Logic;
import io.qameta.allure.Description;
import io.qameta.allure.Flaky;
import io.qameta.allure.Owner;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.sql.SQLException;

import static ATD.CommonMethods.openPage;
import static Common.SetUp.setUpBrowser;
import static com.codeborne.selenide.Selenide.closeWebDriver;

public class QC_918 {

    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0", false);
    }

    @Test(enabled = false)
    @Flaky
    @Owner(value = "Romaniuta")
    @Description(value = "Test checks oem list with car")
    public void testOEMlistWithCar() throws SQLException {
        openPage(new DataBase("ATD").getFullRouteByRouteAndSubroute("prod", "DE", "main", "category_car_list17"));
        new Product_page_Logic().checkOEMlist();
    }

    @AfterMethod
    public void close() {
        closeWebDriver();
    }
}
