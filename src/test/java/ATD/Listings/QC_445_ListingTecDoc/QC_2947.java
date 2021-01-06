package ATD.Listings.QC_445_ListingTecDoc;

import ATD.Product_page_Logic;
import AWS.CategoriesAlternative_aws;
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

import java.sql.SQLException;

import static ATD.CommonMethods.openPage;
import static ATD.CommonMethods.pageReload;
import static Common.SetUp.setUpBrowser;
import static com.codeborne.selenide.Selenide.closeWebDriver;

public class QC_2947 {
    private ProductCard_aws productPageAws = new ProductCard_aws();
    private CategoriesAlternative_aws catAlternativePage = new CategoriesAlternative_aws();
    private Product_page_Logic productPage = new Product_page_Logic();
    private ProductSearch_aws productSearchAwsPage = new ProductSearch_aws();

    public QC_2947() throws SQLException {
    }

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
    @Description(value = "test Checking the display of the generic alternative name in the product name")
    public void testCheckingDisplayOfGenericAlternativeNameInProductName(String route) {
        catAlternativePage.openAlternativeCategoriesInAwsWithLogin()
                .presenceAlternativeTitleForGeneric("135", "atd", "de")
                .presenceAlternativeTitleForGeneric("215", "atd", "de");
        String alternativeTitle = catAlternativePage.getAlternativeTitle("135", "atd", "de");
        String titleOfFirstProduct = productSearchAwsPage.openProductSearchPage().getTitleOfFirstProduct(0);
        productSearchAwsPage.openProductSearchPage()
                .selectCategory("Dichtring, Ölablassschraube ( Seal, oil drain plug ) [135]")
                .selectFirstSearchFilter("no")
                .clickOnSearchButton()
                .waitOfChangeTitleOfProduct(0, titleOfFirstProduct)
                .goToProductCartByClickOnTitle(0);
        String brand = productPageAws.getTitleOfBrandProduct();
        String artNum = productPageAws.getArtNumOfProduct();
        String id = productPageAws.getIdOfProduct();
        openPage(route + "/" + brand + "/" + id);
        productPage.selectAnyCarFromOenBlock(0)
                .checkAlternativeTitleOfProductThroughArticle(artNum, alternativeTitle);
        openPage(route + "/" + brand + "/" + id);
        productPage.presenceOfApplicabilityBlock()
                .clickOnAnyApplicabilityVehicle(0);
        String marke = productPage.getMarkeFromApplicabilityVehicle(0);
        String model = productPage.getModelFromApplicabilityVehicle(0);
        productPage.selectCarHorizontalSelector(marke, model, "15155")
                .clickSearchBtnInHorizontalSelector();
        pageReload();
        productPage.presenceOfCarMatchBlock()
                .clickOnThirdBreadCrumbLink(2)
                .checkAlternativeTitleOfProductThroughArticle(artNum, alternativeTitle);
    }

    @AfterMethod
    private void close() {
        closeWebDriver();
    }
}
