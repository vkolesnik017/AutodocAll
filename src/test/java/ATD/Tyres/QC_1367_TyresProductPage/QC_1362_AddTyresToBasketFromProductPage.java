package ATD.Tyres.QC_1367_TyresProductPage;

import ATD.SetUp;
import ATD.TyresProduct_page_Logic;
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
import static ATD.SetUp.setUpBrowser;
import static com.codeborne.selenide.Selenide.close;

public class QC_1362_AddTyresToBasketFromProductPage {

    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0");
    }

    @DataProvider(name = "routes", parallel = true)
    Object[] dataProvider() throws SQLException {
        return new SetUp().setUpShopWithSubroutes("prod", "DE", "main", "tyre_item");
    }

    @Test(dataProvider = "routes")
    @Flaky
    @Owner(value = "Romaniuta")
    @Description(value = "Test Checks Add Tyres To Basket From Product Page")
    public void testAddTyresToBasketFromProductPage(String route) {
        openPage(route);
        clickOfBuyBtnForAllPages();
        new TyresProduct_page_Logic().checkAddingTyresToBasket()
                                     .checkOfIdAddedProductInBasket("8064604");
    }

    @AfterMethod
    public void tearDown() {
        close();
    }
}