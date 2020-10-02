package ATD.GrayButton;

import ATD.Tyre_item_page_Logic;
import AWS.ProductCard_aws;
import AWS.ProductSearch_aws;
import Common.SetUp;
import io.qameta.allure.Description;
import io.qameta.allure.Flaky;
import io.qameta.allure.Owner;
import mailinator.WebMail;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static ATD.CommonMethods.openPage;
import static Common.SetUp.setUpBrowser;
import static com.codeborne.selenide.Selenide.closeWebDriver;
import static mailinator.WebMail.passwordForMail;

public class QC_2067_CheckNotifyOfStockFormValidationForOutOfStockProducts {
    private ProductCard_aws productPageAws = new ProductCard_aws();
    private Tyre_item_page_Logic tyreItemPage = new Tyre_item_page_Logic();
    private String email = "QC_2067_autotest@autodoc.si";
    private WebMail webMailPage = new WebMail();

    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0");
    }

    @DataProvider(name = "route")
    Object[] dataProvider() {
        return new SetUp("ATD").setUpShop("prod", "DE");
    }

    @Owner(value = "Kolesnik")
    @Test(dataProvider = "route")
    @Flaky
    @Description(value = "test checking notify of stock from validation for Out of stock products")
    public void testCheckNotifyOfStockFormValidationForOutOfStockProducts(String route) {
        new ProductSearch_aws().openProductSearchPageAndLogin().selectCategory("100001")
                .selectFirstSearchFilter("no").selectAvailabilityAtSupplierFilter("no").clickOnSearchButton().goToProductCartByClickOnTitle(0);
        String urlOfProductPage = "https://www.autodoc.de/reifen/" + productPageAws.getTitleOfBrandProduct() + "-" + productPageAws.getEanOfProduct() + "-" + productPageAws.getArtNumOfProduct();
        openPage(urlOfProductPage);
        String fullEanNumberOfProduct = tyreItemPage.getFullEanNumberOfProduct();
        tyreItemPage.presenceOfHorizontalSelector().goToSizeListingByClickOnBreadCrumbLink(1).appearsOfOutOfStockProductPopUp(fullEanNumberOfProduct)
                .setValueInEmailFieldOfPopUp(email).clickOnGetMailingLabel().clickOnBtnSubscription();
        openPage(urlOfProductPage);
        tyreItemPage.presenceOfHorizontalSelector().goToBrandSizeListingByClickOnBreadCrumbLink(2).appearsOfOutOfStockProductPopUp(fullEanNumberOfProduct)
                .setValueInEmailFieldOfPopUp(email).clickOnGetMailingLabel().clickOnBtnSubscription();
        webMailPage.openMail(email, passwordForMail);
        Assert.assertEquals(webMailPage.getTotalCountOfLetters(), 2);
        webMailPage.deleteAllLetters();
    }

    @AfterMethod
    private void close() {
        closeWebDriver();
    }
}
