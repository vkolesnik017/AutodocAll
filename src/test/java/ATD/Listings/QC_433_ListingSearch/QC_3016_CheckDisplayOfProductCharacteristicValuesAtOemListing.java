package ATD.Listings.QC_433_ListingSearch;

import ATD.Product_page_Logic;
import ATD.Search_page_Logic;
import AWS.OptionsOfCharacteristics_aws;
import AWS.ProductCard_aws;
import AWS.ProductSearch_aws;
import Common.SetUp;
import io.qameta.allure.Description;
import io.qameta.allure.Flaky;
import io.qameta.allure.Owner;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.List;

import static ATD.CommonMethods.openPage;
import static Common.SetUp.setUpBrowser;
import static com.codeborne.selenide.Selenide.closeWebDriver;

public class QC_3016_CheckDisplayOfProductCharacteristicValuesAtOemListing {
    private ProductSearch_aws productSearchAwsPage = new ProductSearch_aws();
    private ProductCard_aws productPageAws = new ProductCard_aws();
    private Product_page_Logic productPage = new Product_page_Logic();

    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0");
    }

    @DataProvider(name = "route")
    Object[] dataProvider() {
        return new SetUp("ATD").setUpShop("prod", "DE");
    }

    @Test(dataProvider = "route")
    @Flaky
    @Owner(value = "Kolesnik")
    @Description(value = "test Checking the display of product characteristic values in the main title on the search listing")
    public void testCheckDisplayOfProductCharacteristicValues(String route) {
        List<String> options = new OptionsOfCharacteristics_aws()
                .openOptionsOfCharacteristicsInAwsWithLogin()
                .setSkin()
                .setGeneric("854")
                .clickSearch()
                .getIdMainParameters();
        String titleOfFirstProduct = productSearchAwsPage.openProductSearchPage().getTitleOfFirstProduct(0);
        List<String> matchingOptions = productSearchAwsPage.selectCategory("Stoßdämpfer ( Shock Absorber ) [854]")
                .selectFirstSearchFilter("no")
                .selectSecondSearchFilter("no")
                .setSortingFilter("marketingRating")
                .clickOnSearchButton()
                .waitOfChangeTitleOfProduct(0, titleOfFirstProduct)
                .goToProductCartByClickOnTitle(0)
                .setLanguage("de")
                .presenceOfLanguageBlock("de")
                .getOfMatchingOptions(options);
        List<String> valuesOfMatchingOptions = productPageAws.getValuesOfMatchingOptions(options);
        String brand = productPageAws.getTitleOfBrandProduct();
        String id = productPageAws.getIdOfProduct();
        openPage(route + "/" + brand + "/" + id);
        productPage.selectAnyCarFromOenBlock(0);
        new Search_page_Logic().checkMatchingOptions(matchingOptions, 1);
        openPage(route + "/" + brand + "/" + id);
        productPage.presenceOfApplicabilityBlock().clickOnAnyApplicabilityVehicle(0);
        String marke = productPage.getMarkeFromApplicabilityVehicle(0);
        String model = productPage.getModelFromApplicabilityVehicle(0);
        productPage.selectCarHorizontalSelector(marke, model, "33363")
                .checkVisibleCharacteristic()
                .clickSearchBtnInHorizontalSelector()
                .presenceOfCarMatchBlock()
                .presenceOfCompatibilityCar()
                .clickOnThirdBreadCrumbLink(2)
                .checkMatchingValuesInProductTitle(valuesOfMatchingOptions, 0);
      }

    @AfterMethod
    private void close() {
        closeWebDriver();
    }
}
