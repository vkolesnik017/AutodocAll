package PKW.ACC.QC_1810_BlockWithProductsOnChemistryListing;

import PKW.*;
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
import static com.codeborne.selenide.Selenide.close;


public class QC_1814_AddProductsInBasketOnChemistryListing {

    private String idProduct, valueCounter, idAddProduct, valueCounterAddProduct;
    private Listing_chemicals_page_Logic listing_chemicals_page_logic = new Listing_chemicals_page_Logic();

    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0");
    }

    @DataProvider(name = "route", parallel = true)
    Object[] dataProviderProducts() throws SQLException {
        return new SetUp().setUpShopWithSubroutes("prod", "DE", "main", "listing_chemicals");
    }

    @Test(dataProvider = "route")
    @Flaky
    @Owner(value = "Sergey-QA")
    @Description(value = "Test Checks add products in basket.")
    public void testCheckingAddProductsInBasket(String route) {
        openPage(route);
        idProduct = listing_chemicals_page_logic.getIdProductListing();
        listing_chemicals_page_logic.increasesNumberProductsInQuantityCounter();
        valueCounter = listing_chemicals_page_logic.getValueQuantityCounterFirstProductListing();
        listing_chemicals_page_logic.clickBtnAddToBasketFirstProduct()
                .cartClick();
        idAddProduct = new Cart_page_Logic().getIdAddedProduct();
        valueCounterAddProduct = new Cart_page_Logic().getValueQuantityCounter();
        Assert.assertEquals(idProduct, idAddProduct);
        Assert.assertEquals(valueCounter,valueCounterAddProduct);
    }

    @AfterMethod
    private void tearDown() {
        close();
    }
}