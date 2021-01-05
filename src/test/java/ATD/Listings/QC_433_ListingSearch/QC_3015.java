package ATD.Listings.QC_433_ListingSearch;

import ATD.Main_page_Logic;
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

public class QC_3015 {
    private ProductSearch_aws productSearchAwsPage = new ProductSearch_aws();
    private ProductCard_aws productPageAws = new ProductCard_aws();

    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0", false);
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
        String brand = productPageAws.getTitleOfBrandProduct();
        String artNum = productPageAws.getArtNumOfProduct();
        String id = productPageAws.getIdOfProduct();
        openPage(route + "/" + brand + "/" + id);
        new Main_page_Logic().useSearch(artNum).checkMatchingOptions(matchingOptions, 1);
    }

    @AfterMethod
    private void close() {
        closeWebDriver();
    }
}
