package ATD.GrayButton.QC_1014_OutOfStockProducts;

import ATD.Product_page_Logic;
import Common.DataBase;
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

public class QC_3219 {

    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0", false);
    }

    @Test
    @Flaky
    @Owner(value = "Bobryshev")
    @Description(value = "Goods out of stock in a litre-by-litre glut on grocery oils")

    public void testGoodsOutOfStockOnGroceryOils() throws SQLException {
        openPage(new DataBase("ATD").getFullRouteByRouteAndSubroute("prod", "DE", "main", "product95"));
        new Product_page_Logic().checkPresenceGreyButtonFromBlockUnitByLitre();
    }

    @AfterMethod
    public void close() {
        closeWebDriver();
    }
}
