package PKW.ACC.QC_2283_BlockWithProductsOnToolsListing;

import PKW.Listing_instruments_Page_Logic;
import PKW.Product_page_Logic;
import PKW.SetUp;
import io.qameta.allure.Description;
import io.qameta.allure.Flaky;
import io.qameta.allure.Owner;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import java.sql.SQLException;
import static PKW.CommonMethods.openPage;
import static PKW.SetUp.setUpBrowser;
import static com.codeborne.selenide.Selenide.closeWebDriver;

public class QC_2285_TransitionOnProductPageFromToolsListing {

    private String nameProduct, titleNameProduct;
    private Listing_instruments_Page_Logic listingInstrumentsPageLogic = new Listing_instruments_Page_Logic();

    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0");
    }

    @DataProvider(name = "route", parallel = true)
    Object[] dataProviderProducts() throws SQLException {
        return new SetUp().setUpShopWithSubroutes("prod", "DE", "main", "listing_instruments");
    }

    @Test(dataProvider = "route")
    @Flaky
    @Owner(value = "Sergey-QA")
    @Description(value = "Test Checks transition on product page from first product in listing.")
    public void testCheckingTransitionFromFirstProductInListing(String route) {
        openPage(route);
        nameProduct = listingInstrumentsPageLogic.getNameProductInListing();
        listingInstrumentsPageLogic.clickFirstProductInListing();
        titleNameProduct = new Product_page_Logic().getNameTitle();
        Assert.assertEquals(nameProduct, titleNameProduct);
    }

    @AfterMethod
    public void close() {
        closeWebDriver();
    }
}
