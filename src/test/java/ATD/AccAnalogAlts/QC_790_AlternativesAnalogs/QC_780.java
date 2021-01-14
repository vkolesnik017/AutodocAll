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

import static ATD.CommonMethods.openPage;
import static Common.SetUp.setUpBrowser;
import static com.codeborne.selenide.Selenide.closeWebDriver;
import static com.codeborne.selenide.Selenide.open;

public class QC_780 {
    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0", false);
    }

    @DataProvider(name = "routes", parallel = true)
    Object[] dataProvider() throws SQLException {
        return new SetUp("ATD").setUpShopWithSubroutes("prod", "DE", "main", "maker_car_list3");
    }

    @Test(dataProvider = "routes")
    @Flaky
    @Owner(value = "Romaniuta")
    @Description(value = "Test Checks Related Products Popup On Product Page With Car")
    public void testRelatedProductsPopupOnProductPageWithCar(String route) throws SQLException {
        openPage(route);
        open(new DataBase("ATD").getFullRouteByRouteAndSubroute("prod", "DE", "main", "product22"));
        new Product_page_Logic().checkRelatedProductsPopup(6);
    }

    @AfterMethod
    public void close() {
        closeWebDriver();
    }
}
