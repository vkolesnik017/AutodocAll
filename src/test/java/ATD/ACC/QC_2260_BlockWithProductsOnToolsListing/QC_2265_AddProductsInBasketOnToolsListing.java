package ATD.ACC.QC_2260_BlockWithProductsOnToolsListing;

import ATD.Cart_page_Logic;
import ATD.Listing_instruments_page_Logic;
import ATD.SetUp;
import io.qameta.allure.Description;
import io.qameta.allure.Flaky;
import io.qameta.allure.Owner;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import java.sql.SQLException;
import static ATD.CommonMethods.openPage;
import static ATD.SetUp.setUpBrowser;
import static com.codeborne.selenide.Selenide.closeWebDriver;


public class QC_2265_AddProductsInBasketOnToolsListing {

    private String idProduct, valueCounter, idAddProduct, valueCounterAddProduct;
    private Listing_instruments_page_Logic listingInstrumentsPageLogic = new Listing_instruments_page_Logic();

    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0");
    }

    @DataProvider(name = "route", parallel = true)
    Object[] dataProvider() throws SQLException {
        return new SetUp().setUpShopWithSubroutes("prod", "DE", "main", "listing_instruments");
    }

    @Test(dataProvider = "route")
    @Flaky
    @Owner(value = "Sergey-QA")
    @Description(value = "Test Checks number of added products in basket")
    public void testCheckingNumberOfAddedProductsInBasket(String route) {
        openPage(route);
        idProduct = listingInstrumentsPageLogic.getIdProductListing();
        listingInstrumentsPageLogic.increasesNumberProductsInQuantityCounter();
        valueCounter = listingInstrumentsPageLogic.getValueQuantityCounterFirstProductListing();
        listingInstrumentsPageLogic.clickBtnAddToBasketFirstProduct()
                .cartClick();
        idAddProduct = new ATD.Cart_page_Logic().getIdAddedProduct();
        valueCounterAddProduct = new Cart_page_Logic().getValueQuantityCounter();
        Assert.assertEquals(idProduct, idAddProduct);
        Assert.assertEquals(valueCounter,valueCounterAddProduct);
    }

    @AfterMethod
    private void close() {
        closeWebDriver();
    }
}
