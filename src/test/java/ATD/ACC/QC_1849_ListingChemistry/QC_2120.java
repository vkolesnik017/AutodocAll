package ATD.ACC.QC_1849_ListingChemistry;

import ATD.Cart_page_Logic;
import ATD.Listing_chemicals_Page_Logic;
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

public class QC_2120 {

    private String idProduct, valueCounter, idAddProduct, valueCounterAddProduct;
    private Listing_chemicals_Page_Logic listing_chemicals_page_logic = new Listing_chemicals_Page_Logic();

    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0", false);
    }

    @DataProvider(name = "route", parallel = true)
    Object[] dataProvider() throws SQLException {
        return new SetUp("ATD").setUpShopWithSubroutes("prod", "DE", "main", "listing_chemicals");
    }

    @Test(dataProvider = "route")
    @Flaky
    @Owner(value = "Sergey-QA")
    @Description(value = "Test Checks number of added products in basket")
    public void testCheckingNumberOfAddedProductsInBasket(String route) {
        openPage(route);
        idProduct = listing_chemicals_page_logic.getIdProductListing();
        listing_chemicals_page_logic.increasesNumberProductsInQuantityCounter();
        valueCounter = listing_chemicals_page_logic.getValueQuantityCounterFirstProductListing();
        listing_chemicals_page_logic.clickBtnAddToBasketFirstProduct()
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
