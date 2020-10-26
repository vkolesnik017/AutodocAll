package ATD.GrayButton.QC_1014_OutOfStockProducts;

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

import java.sql.SQLException;

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
    Object[] dataProvider() throws SQLException {
        return new SetUp("ATD").setUpShopWithSubroutes("prod", "DE", "main", "tyres");
    }

    @Owner(value = "Kolesnik")
    @Test(dataProvider = "route")
    @Flaky
    @Description(value = "test checking notify of stock from validation for Out of stock products")
    public void testCheckNotifyOfStockFormValidationForOutOfStockProducts(String route) {
        webMailPage.openMail(email, passwordForMail);
        webMailPage.deleteAllLetters();
        new ProductSearch_aws().openProductSearchPageAndLogin().selectCategory("100001")
                .selectFirstSearchFilter("no").selectAvailabilityAtSupplierFilter("no").clickOnSearchButton().goToProductCartByClickOnTitle(0);
        String brand = productPageAws.getTitleOfBrandProduct();
        String ean = productPageAws.getEanOfProduct();
        String artNum = productPageAws.getArtNumOfProduct();
        openPage(route + "/" + brand + "-" + ean + "-" + artNum);
        String fullEanNumberOfProduct = tyreItemPage.getFullEanNumberOfProduct();
        tyreItemPage.presenceOfHorizontalSelector().goToSizeListingByClickOnBreadCrumbLink(1).appearsOfOutOfStockProductPopUp(fullEanNumberOfProduct)
                .setValueInEmailFieldOfPopUp(email).clickOnGetMailingLabel().clickOnBtnSubscription();
        openPage(route + "/" + brand + "-" + ean + "-" + artNum);
        tyreItemPage.presenceOfHorizontalSelector().goToBrandSizeListingByClickOnBreadCrumbLink(2).appearsOfOutOfStockProductPopUp(fullEanNumberOfProduct)
                .setValueInEmailFieldOfPopUp(email).clickOnGetMailingLabel().clickOnBtnSubscription().closeOutOfStockProductPopUp().presenceOfAllTopTireSizeLinks();
        webMailPage.openMailWithLoggedUser().presenceOfToolbarElements();
        Assert.assertEquals(webMailPage.getTotalCountOfLetters(), 2);
        webMailPage.deleteAllLetters();
    }

    @AfterMethod
    private void close() {
        closeWebDriver();
    }
}
