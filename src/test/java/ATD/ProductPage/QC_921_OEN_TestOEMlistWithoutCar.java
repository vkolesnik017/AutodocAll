package ATD.ProductPage;


import ATD.DataBase;
import ATD.Product_page_Logic;
import io.qameta.allure.Description;
import io.qameta.allure.Flaky;
import io.qameta.allure.Owner;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.sql.SQLException;

import static ATD.CommonMethods.openPage;
import static ATD.SetUp.setUpBrowser;
import static com.codeborne.selenide.Selenide.close;

public class QC_921_OEN_TestOEMlistWithoutCar {

    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0");
    }

    @Test
    @Flaky
    @Owner(value = "Romaniuta")
    @Description(value = "Test checks oem list without car")
    public void testOEMlistWithoutCar() throws SQLException {
        openPage(new DataBase().getFullRouteByRouteAndSubroute("prod", "DE", "main", "product15"));
        new Product_page_Logic().scrollToOenBlock()
                          .checkOEMlistWithoutCar();
    }
    @AfterMethod
    private void tearDown() {
        close();
    }
}
