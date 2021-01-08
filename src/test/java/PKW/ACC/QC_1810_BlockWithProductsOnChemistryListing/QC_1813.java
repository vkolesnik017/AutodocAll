package PKW.ACC.QC_1810_BlockWithProductsOnChemistryListing;


import Common.SetUp;
import PKW.Cart_page_Logic;
import PKW.Listing_chemicals_page_Logic;
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

public class QC_1813 {

    private String idProduct, valueCounter, idAddProduct, valueCounterAddProduct;
    private Listing_chemicals_page_Logic listing_chemicals_page_logic = new Listing_chemicals_page_Logic();

    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0", false);
    }

    @DataProvider(name = "route", parallel = true)
    Object[] dataProviderProducts() throws SQLException {
        return new SetUp("PKW").setUpShopWithSubroutes("prod", "DE", "main", "listing_chemicals");
    }

    @Test(dataProvider = "route")
    @Flaky
    @Owner(value = "Sergey-QA")
    @Description(value = "Test Checks work quantity counter in card product on listing and checks add products in basket.")
    public void testCheckingWorkQuantityCounterInCardProduct(String route) {
        openPage(route);
        new Listing_chemicals_page_Logic().checkingWorkQuantityCounterOnDecreaseAndIncrease();
        idProduct = listing_chemicals_page_logic.getIdProductListing();
        listing_chemicals_page_logic.increasesNumberProductsInQuantityCounter();
        valueCounter = listing_chemicals_page_logic.getValueQuantityCounterFirstProductListing();
        listing_chemicals_page_logic.clickBtnAddToBasketFirstProduct()
                .cartClick();
        idAddProduct = new Cart_page_Logic().getIdAddedProduct();
        valueCounterAddProduct = new Cart_page_Logic().getValueQuantityCounter();
        Assert.assertEquals(idProduct, idAddProduct);
        Assert.assertEquals(valueCounter, valueCounterAddProduct);

    }

    @AfterMethod
    public void close() {
        closeWebDriver();
    }
}
