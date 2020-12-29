package ATD.ProductPage.QC_2741_ProductPage_CarRoute;

import ATD.Product_page_Logic;
import AWS.PriceProductDescription_aws;
import AWS.ProductCard_aws;
import Common.SetUp;
import io.qameta.allure.Description;
import io.qameta.allure.Flaky;
import io.qameta.allure.Owner;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static ATD.CommonMethods.openPage;
import static Common.SetUp.setUpBrowser;
import static com.codeborne.selenide.Selenide.closeWebDriver;

public class QC_3117_CheckDisplayPriceMeterElementOnProductPage {

    PriceProductDescription_aws priceProductPage = new PriceProductDescription_aws();

    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0", false);
    }

    @DataProvider(name = "routes", parallel = true)
    Object[] dataProvider() {
        return new SetUp("ATD").setUpShopsWithMainRoute("prod", "DE", "main");
    }

    @Test(dataProvider = "routes")
    @Flaky
    @Owner(value = "Kolesnik")
    @Description(value = "Test Checking the display of the 'price per meter' element in the TOP products block")
    public void testCheckDisplayPriceMeterElementInTopProductsBlock(String route) {
        priceProductPage.openPriceDescriptionPage();
        String idOfProduct = priceProductPage.getIncludedProduct().getIdOfProduct();
        String brand = new ProductCard_aws().getTitleOfBrandProduct();
        openPage(route + "/" + brand + "/" + idOfProduct);
        new Product_page_Logic().presencePricePerMeterTitle();
    }

    @AfterMethod
    private void close() {
        closeWebDriver();
    }
}
