package ATD.ProductPage.QC_2741_ProductPage_CarRoute;

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
import static Common.SetUp.setUpBrowser;
import static com.codeborne.selenide.Selenide.closeWebDriver;

public class QC_2945_DisplayOfGenericAlternativeNameInProductName {
    private ProductCard_aws productPageAws = new ProductCard_aws();
    private CategoriesAlternative_aws catAlternativePage = new CategoriesAlternative_aws();
    private ProductSearch_aws productSearchAwsPage = new ProductSearch_aws();

    public QC_2945_DisplayOfGenericAlternativeNameInProductName() throws SQLException {
    }

    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0", false);
    }

    @DataProvider(name = "route")
    Object[] dataProvider() {
        return new SetUp("ATD").setUpShop("prod", "SI");
    }

    @Test(dataProvider = "route")
    @Flaky
    @Owner(value = "Kolesnik")
    @Description(value = "test Checking the display of the generic alternative name in the product name")
    public void testCheckingDisplayOfGenericAlternativeNameInProductName(String route) {
        catAlternativePage.openAlternativeCategoriesProductInAwsWithLogin();
        String alternativeTitle = catAlternativePage.getAlternativeTitle("85", "atd", "si");
        String titleOfFirstProduct = productSearchAwsPage.openProductSearchPage().getTitleOfFirstProduct(0);
        productSearchAwsPage.openProductSearchPage().selectCategory("Streuscheibe, Nebelscheinwerfer ( Diffusing Lens, fog light ) [85]").selectFirstSearchFilter("no")
                .clickOnSearchButton().waitOfChangeTitleOfProduct(0, titleOfFirstProduct).goToProductCartByClickOnTitle(0);
        String brand = productPageAws.getTitleOfBrandProduct();
        String id = productPageAws.getIdOfProduct();
        openPage(route + "/" + brand + "/" + id);
        new Product_page_Logic().checkAlternativeTitleOfProduct(alternativeTitle);
    }

    @AfterMethod
    private void close() {
        closeWebDriver();
    }

}
