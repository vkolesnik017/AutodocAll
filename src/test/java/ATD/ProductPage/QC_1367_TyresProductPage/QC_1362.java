package ATD.ProductPage.QC_1367_TyresProductPage;

import ATD.Tyre_item_page_Logic;
import Common.SetUp;
import io.qameta.allure.Description;
import io.qameta.allure.Flaky;
import io.qameta.allure.Owner;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.sql.SQLException;

import static ATD.CommonMethods.clickOfBuyBtnForAllPages;
import static ATD.CommonMethods.openPage;
import static Common.SetUp.setUpBrowser;
import static com.codeborne.selenide.Selenide.closeWebDriver;

public class QC_1362 {

    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0", false);
    }

    @DataProvider(name = "routes", parallel = true)
    Object[] dataProvider() throws SQLException {
        return new SetUp("ATD").setUpShopWithSubroutes("prod", "DE", "main", "tyre_item13");
    }

    @Test(dataProvider = "routes")
    @Flaky
    @Owner(value = "Romaniuta")
    @Description(value = "Test Checks Add Tyres To Basket From Product Page")
    public void testAddTyresToBasketFromProductPage(String route) {
        openPage(route);
        clickOfBuyBtnForAllPages();
        new Tyre_item_page_Logic().checkAddingTyresToBasket()
                                     .checkOfIdAddedProductInBasket("14738438");
    }

    @AfterMethod
    public void close() {
        closeWebDriver();
    }
}
