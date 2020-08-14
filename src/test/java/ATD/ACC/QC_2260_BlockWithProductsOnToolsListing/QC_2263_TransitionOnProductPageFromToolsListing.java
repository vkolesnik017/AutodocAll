package ATD.ACC.QC_2260_BlockWithProductsOnToolsListing;

import ATD.Listing_instruments_page_Logic;
import ATD.Product_page_Logic;
import ATD.SetUp;
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
import static ATD.SetUp.setUpBrowser;
import static com.codeborne.selenide.Selenide.closeWebDriver;

public class QC_2263_TransitionOnProductPageFromToolsListing {

    private String idProductFromListing, idProduct;
    private Listing_instruments_page_Logic listingInstrumentsPageLogic = new Listing_instruments_page_Logic();

    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0");
    }

    @DataProvider(name = "route", parallel = true)
    Object[] dataProvider() throws SQLException {
        return new SetUp().setUpShopWithSubroutes("prod", "DE", "main", "listing_instruments");
    }

    @Test(dataProvider = "route")
    @Flaky
    @Owner(value = "Sergey-QA")
    @Description(value = "Test Checks transition with main product.")
    public void testCheckingTransitionWithMainProduct(String route) {
        openPage(route);
        idProductFromListing = listingInstrumentsPageLogic.getIdProductListing();
        listingInstrumentsPageLogic.clickOnProductTitle();
        idProduct = new Product_page_Logic().getIdFromBtnProduct();
        Assert.assertEquals(idProductFromListing, idProduct);

    }

    @AfterMethod
    private void close() {
        closeWebDriver();
    }
}
