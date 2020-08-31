package PKW.ACC.QC_2294_BlockWithProductsOnAccListing;

import PKW.Cart_page_Logic;
import PKW.Listing_accessories_page_Logic;
import PKW.SetUp;
import io.qameta.allure.Description;
import io.qameta.allure.Flaky;
import io.qameta.allure.Owner;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import java.sql.SQLException;
import static PKW.CommonMethods.openPage;
import static PKW.SetUp.setUpBrowser;
import static com.codeborne.selenide.Selenide.closeWebDriver;

public class QC_2298_AddProductsToBasketOnACCListing {

    private String idProduct, valueCounter, idAddProduct, valueCounterAddProduct;
    private Listing_accessories_page_Logic listingAccessoriesPageLogic = new Listing_accessories_page_Logic();

    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0");
    }

    @DataProvider(name = "route", parallel = true)
    Object[] dataProviderProducts() throws SQLException {
        return new SetUp().setUpShopWithSubroutes("prod", "DE", "main", "listing_accessories");
    }

    @Test(dataProvider = "route")
    @Flaky
    @Owner(value = "Sergey-QA")
    @Description(value = "Test Checks add products in basket.")
    public void testCheckingAddProductsInBasket(String route) {
        openPage(route);
        idProduct = listingAccessoriesPageLogic.getIdProductListing();
        listingAccessoriesPageLogic.increasesNumberProductsInQuantityCounter();
        valueCounter = listingAccessoriesPageLogic.getValueQuantityCounterFirstProductListing();
        listingAccessoriesPageLogic.clickBtnAddToBasketFirstProduct()
                .cartClick();
        idAddProduct = new Cart_page_Logic().getIdAddedProduct();
        valueCounterAddProduct = new Cart_page_Logic().getValueQuantityCounter();
        Assert.assertEquals(idProduct, idAddProduct);
        Assert.assertEquals(valueCounter,valueCounterAddProduct);
    }

    @AfterMethod
    public void close() {
        closeWebDriver();
    }
}