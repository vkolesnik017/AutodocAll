package PKW.Section_ACC.QC_3013_Accessories_Listing;

import Common.SetUp;
import PKW.Listing_accessories_page_Logic;
import PKW.Product_page_Logic;
import io.qameta.allure.Description;
import io.qameta.allure.Flaky;
import io.qameta.allure.Owner;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.sql.SQLException;

import static Common.SetUp.setUpBrowser;
import static PKW.CommonMethods.openPage;
import static com.codeborne.selenide.Selenide.closeWebDriver;

public class QC_2296 {

    private String nameProduct, titleNameProduct;
    private Listing_accessories_page_Logic listingAccessoriesPageLogic = new Listing_accessories_page_Logic();

    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0", false);
    }

    @DataProvider(name = "route", parallel = true)
    Object[] dataProviderProducts() throws SQLException {
        return new SetUp("PKW").setUpShopWithSubroutes("prod", "DE", "main", "listing_accessories");
    }

    @Test(dataProvider = "route")
    @Flaky
    @Owner(value = "Sergey-QA")
    @Description(value = "Test Checks transition on product page from first product in listing.")
    public void testCheckingTransitionFromFirstProductInListing(String route) {
        openPage(route);
        nameProduct = listingAccessoriesPageLogic.getNameProductInListing();
        listingAccessoriesPageLogic.clickFirstProductInListing();
        titleNameProduct = new Product_page_Logic().getNameTitle();
        Assert.assertEquals(nameProduct, titleNameProduct);
    }

    @AfterMethod
    public void close() {
        closeWebDriver();
    }
}
