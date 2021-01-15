package ATD.ACC.QC_1340_ListingAccessories;

import ATD.Cart_page_Logic;
import ATD.Listing_accessories_page_Logic;
import Common.SetUp;
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
import static Common.SetUp.setUpBrowser;
import static com.codeborne.selenide.Selenide.closeWebDriver;

public class QC_2269 {

    private String idProduct, valueCounter, idAddProduct, valueCounterAddProduct;
    private Listing_accessories_page_Logic listingAccessoriesPageLogic = new Listing_accessories_page_Logic();
    private Cart_page_Logic cartPageLogic = new Cart_page_Logic();

    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0", false);
    }

    @DataProvider(name = "route", parallel = true)
    Object[] dataProvider() throws SQLException {
        return new SetUp("ATD").setUpShopWithSubroutes("prod", "DE", "main", "listing_accessories");
    }

    @Test(dataProvider = "route")
    @Flaky
    @Owner(value = "Sergey-QA")
    @Description(value = "Test Checks number of added products in basket")
    public void testCheckingNumberOfAddedProductsInBasket(String route) {
        openPage(route);
        new Listing_accessories_page_Logic().checkingWorkQuantityCounterOnDecreaseAndIncrease();
        idProduct = listingAccessoriesPageLogic.getIdProductListing();
        listingAccessoriesPageLogic.increasesNumberProductsInQuantityCounter();
        valueCounter = listingAccessoriesPageLogic.getValueQuantityCounterFirstProductListing();
        listingAccessoriesPageLogic.clickBtnAddToBasketFirstProduct()
                .cartClick();
        idAddProduct = cartPageLogic.getIdAddedProduct();
        valueCounterAddProduct = cartPageLogic.getValueQuantityCounter();
        Assert.assertEquals(idProduct, idAddProduct);
        Assert.assertEquals(valueCounter,valueCounterAddProduct);
    }

    @AfterMethod
    private void close() {
        closeWebDriver();
    }
}
