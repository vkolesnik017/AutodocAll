package PKW.Listings.QC_1162_MainIssueBlockOfListing;

import PKW.Cart_page_Logic;
import PKW.Motoroil_Maker_page_Logic;
import PKW.Motoroil_viscosity_page_Logic;
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

public class QC_1164 {
    private Motoroil_viscosity_page_Logic viscosityPage = new Motoroil_viscosity_page_Logic();
    private Motoroil_Maker_page_Logic makerPage = new Motoroil_Maker_page_Logic();
    private Cart_page_Logic cartPage = new Cart_page_Logic();

    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0", false);
    }

    @DataProvider(name = "routes", parallel = true)
    Object[] dataProvider() throws SQLException {
        return new Common.SetUp("PKW").setUpShopWithSubroutes("prod", "DE", "main", "motoroil_viscosity,motoroil_viscosity_brand,motoroil_specification,motoroil_release,motoroil_brand");
    }

    @Test(dataProvider = "routes")
    @Flaky
    @Owner(value = "Kolesnik")
    @Description(value = "Test checks presence of Products listing block")
    public void testChecksPresenceOfProductsListingBlock(String route) {
        openPage(route);

        String idOfAddedProduct = viscosityPage.presenceOfMainListingOfProducts().getIdOfAddedProductToBasket(0);
        String volumeOfAddedProduct = viscosityPage.getVolumeOfAddedProduct(0);
        viscosityPage.addProductToBasket(0);
        String idOfAddedProductInBasket = cartPage.presenceOfAddedProductList().getIdAddedProduct();
        Assert.assertEquals(idOfAddedProductInBasket, idOfAddedProduct);
        Assert.assertEquals(cartPage.getVolumeAddedProduct(), volumeOfAddedProduct);
    }

    @DataProvider(name = "routesMaker", parallel = true)
    Object[] dataProviderMaker() throws SQLException {
        return new Common.SetUp("PKW").setUpShopWithSubroutes("prod", "DE", "main", "motoroil_maker,motoroil_maker_group,motoroil_chemical_type,car_parts_motoroil");
    }

    @Test(dataProvider = "routesMaker")
    @Flaky
    @Owner(value = "Kolesnik")
    @Description(value = "Test checks presence of Products listing block")
    public void testChecksPresenceOfProductsListingBlockMaker(String route) {
        openPage(route);

        String idOfAddedProduct = makerPage.presenceOfMainListingOfProducts().getIdOfAddedProductToBasket(0);
        String volumeOfAddedProduct = makerPage.getVolumeOfAddedProduct(0);
      /*  makerPage.clickOnBtnAddToBasket(0)
                .displayOfBasketDropMenu()
                .displayEngineOilViscosity()
                .displayEngineOilViscosity();
        new Main_page_Logic().checkCountOfProductInBasketAtHeader(1);
        makerPage.clickOnBasketIconInHeader();*/

        makerPage.addProductToCart(0).clickOnBasketIconInHeader();
        String idOfAddedProductInBasket = cartPage.presenceOfAddedProductList().getIdAddedProduct();
        Assert.assertEquals(idOfAddedProductInBasket, idOfAddedProduct);
        Assert.assertEquals(cartPage.getVolumeAddedProduct(), volumeOfAddedProduct);
    }

    @AfterMethod
    public void close() {
        closeWebDriver();
    }
}
