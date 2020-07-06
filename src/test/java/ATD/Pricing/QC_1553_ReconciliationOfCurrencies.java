package ATD.Pricing;

import ATD.Cart_page_Logic;
import ATD.Main_page_Logic;
import ATD.Product_page_Logic;
import ATD.SetUp;
import io.qameta.allure.Description;
import io.qameta.allure.Flaky;
import io.qameta.allure.Owner;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.sql.SQLException;

import static ATD.CommonMethods.getCurrentShopFromJSVarInHTML;
import static ATD.CommonMethods.openPage;
import static ATD.SetUp.setUpBrowser;
import static com.codeborne.selenide.Selenide.close;


public class QC_1553_ReconciliationOfCurrencies {

    // productsId: accessory, chemistry, tool, oil, ordinary product
    private String[] productsId = {"988236", "13558633", "15735664", "13626328", "7831937" }; //TODO будем ли выносить в базу id товаров для теста ?
    private String emailForAnotherShop = "reconciliationOfCurrencies@mailinator.com";
    private String password = "1234";

    private Product_page_Logic product_page_logic = new Product_page_Logic();

    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0");
    }

    @DataProvider(name = "routeAndProductsIdAllShops", parallel = true)
    Object[] dataProviderAllShops() {
        return new SetUp().setUpShopWithListParam("prod", "AT, BG, BE, CZ, DE, DK, EE, ES, FI, FR, EN, GR, HU, IT, LD, LT, LV, NL, NO, PL, PT, RO, SE, SI, SK", productsId);
    }

    @Owner("Chelombitko")
    @Test(dataProvider = "routeAndProductsIdAllShops")
    @Flaky
    @Description(value = "The test checks the currency with product from the category of accessories, chemistry, tools, oil and ordinary product on all languages except Switzerland")
    public void testReconciliationOfCurrencies(String routeAndProductId) throws SQLException {
        String route = routeAndProductId.split("_")[0];
        String productId = routeAndProductId.split("_")[1];
        openPage(route + "/a/" + productId);
        String shop = getCurrentShopFromJSVarInHTML();
        product_page_logic.CompareCurrencyOnProductPageAndInBasketPopup(shop).cartClick()
                .checkCurrencyOnCartPage(shop)
                .checkCurrencyOnCartPageFromDiscountBlock(shop)
                .nextButtonClick()
                .signIn(emailForAnotherShop, password)
                .nextBtnClick()
                .choosePayPal()
                .nextBtnClick()
                .checkCurrencyOnAllDataPage(shop);

    }

    @DataProvider(name = "routeForTires", parallel = true)
    Object[] dataProviderForTires() {
        return new SetUp().setUpShop("prod", "AT, BG, BE, CZ, DE, DK, EE, ES, FI, FR, EN, HU, IT, LV, NL, PL, PT, RO, SE, SI, SK");
    }

    @Owner("Chelombitko")
    @Test(dataProvider = "routeForTires")
    @Flaky
    @Description(value = "The test checks the currency with product tire")
    public void testReconciliationOfCurrenciesForTires(String route) throws SQLException {
        openPage(route);
        String shop = getCurrentShopFromJSVarInHTML();
        new Main_page_Logic().clickTiresCategory()
                .imagesProductsTires().click();
        product_page_logic.CompareCurrencyOnProductPageAndInBasketPopup(shop).cartClick().checkCurrencyOnCartPage(shop)
                .nextButtonClick()
                .signIn(emailForAnotherShop, password)
                .nextBtnClick()
                .choosePayPal()
                .nextBtnClick()
                .checkCurrencyOnAllDataPage(shop);
    }

    @DataProvider(name = "routeAndProductsIdForCH", parallel = true)
    Object[] dataProviderForCH() throws SQLException {
        return new SetUp().setUpShopWithListParam("prod", "CH", productsId);
    }

    @Owner("Chelombitko")
    @Test(dataProvider = "routeAndProductsIdForCH")
    @Flaky
    @Description(value = "The test checks the currency with product from the category of accessories, chemicals, tools, oil and ordinary product only in Switzerland")
    public void testReconciliationOfCurrenciesForCH(String routeAndProductId) throws SQLException {
        String route = routeAndProductId.split("_")[0];
        String productId = routeAndProductId.split("_")[1];
        String emailForCH = "reconciliationOfCurrenciesCH@mailinator.com";
        openPage(route + "/a/" + productId);
        String shop = getCurrentShopFromJSVarInHTML();
        product_page_logic.CompareCurrencyOnProductPageAndInBasketPopup(shop).cartClick().makePriceForMinimumOrderForCH(shop);
        new Cart_page_Logic().checkCurrencyOnCartPage(shop)
                .nextButtonClick()
                .signIn(emailForCH, password)
                .nextBtnClick()
                .choosePayPal()
                .nextBtnClick()
                .checkCurrencyOnAllDataPage(shop)
                .checkCurrencyForVatPrice(shop);
    }

    @AfterMethod
    private void tearDown() {
        close();
    }
}
