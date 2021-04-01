package ATD.Listings.QC_3503_Oil_Listing_MainProductsBlock;

import ATD.Listing_page_Logic;
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
import static com.codeborne.selenide.Selenide.back;
import static com.codeborne.selenide.Selenide.closeWebDriver;
import static com.codeborne.selenide.WebDriverRunner.url;


public class QC_3505 {

    Listing_page_Logic listingPageLogic = new Listing_page_Logic();


    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0", false);
    }


    @DataProvider(name = "route", parallel = true)
    Object[] dataProvider() throws SQLException {
        return new SetUp("ATD").setUpShopWithSubroutes("prod", "DE", "main", "motoroil_brand,motoroil_viscosity2,motoroil_search3");
    }

    @Test(dataProvider = "route")
    @Flaky
    @Owner(value = "Sergey_QA")
    @Description(value = "test check transition to product page from listing oil")
    public void testCheckTransitionToProductFromListingOil(String route) {
        openPage(route);
        String hrefTitleProduct = listingPageLogic.getLinkFromProduct(listingPageLogic.firstProductTitleOnListing(), "href");
        String urlBrandFromProduct = listingPageLogic.getLinkFromProduct(listingPageLogic.brandOfProduct(), "url");
        String urlImgProduct = listingPageLogic.getLinkFromProduct(listingPageLogic.imgProduct(), "url");

        listingPageLogic.clickAnyElementFromProduct(listingPageLogic.firstProductTitleOnListing());
        String urlProductPage = url();
        Assert.assertEquals(hrefTitleProduct, urlProductPage);
        back();

        listingPageLogic.clickAnyElementFromProduct(listingPageLogic.brandOfProduct());
        Assert.assertEquals(urlBrandFromProduct, urlProductPage);
        back();

        listingPageLogic.clickAnyElementFromProduct(listingPageLogic.imgProduct());
        Assert.assertEquals(urlImgProduct, urlProductPage);
    }


    @AfterMethod
    private void close() {
        closeWebDriver();
    }

}
