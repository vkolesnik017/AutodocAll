package PKW.Section_ACC.QC_3011_ToolsListing;

import Common.SetUp;
import PKW.Cart_page_Logic;
import PKW.Listing_instruments_Page_Logic;
import io.qameta.allure.Description;
import io.qameta.allure.Flaky;
import io.qameta.allure.Owner;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.sql.SQLException;

import static Common.SetUp.setUpBrowser;
import static PKW.CommonMethods.openPage;
import static com.codeborne.selenide.Selenide.closeWebDriver;

public class QC_2286 {

    private String idProduct, valueCounter, idAddProduct, valueCounterAddProduct;
    private Listing_instruments_Page_Logic listingInstrumentsPageLogic = new Listing_instruments_Page_Logic();
    private Cart_page_Logic cartPageLogic = new Cart_page_Logic();

    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0", false);
    }

    @DataProvider(name = "route", parallel = true)
    Object[] dataProviderProducts() throws SQLException {
        return new SetUp("PKW").setUpShopWithSubroutes("prod", "DE", "main", "listing_instruments");
    }

    @Test(dataProvider = "route")
    @Flaky
    @Owner(value = "Sergey-QA")
    @Description(value = "Test Checks work quantity counter in card product on listing.")
    public void testCheckingWorkQuantityCounterInCardProduct(String route) {
        openPage(route);
        new Listing_instruments_Page_Logic().checkingWorkQuantityCounterOnDecreaseAndIncrease();
        idProduct = listingInstrumentsPageLogic.getIdProductListing();
        listingInstrumentsPageLogic.increasesNumberProductsInQuantityCounter();
        valueCounter = listingInstrumentsPageLogic.getValueQuantityCounterFirstProductListing();
        listingInstrumentsPageLogic.clickBtnAddToBasketFirstProduct()
                .cartClick();
        idAddProduct = cartPageLogic.getIdAddedProduct();
        valueCounterAddProduct = cartPageLogic.getValueQuantityCounter();
        Assert.assertEquals(idProduct, idAddProduct);
        Assert.assertEquals(valueCounter, valueCounterAddProduct);
    }

    @AfterMethod
    public void close() {
        closeWebDriver();
    }
}
