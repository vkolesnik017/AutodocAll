package PKW.ACC.QC_1810_BlockWithProductsOnChemistryListing;

import PKW.Listing_chemicals_page_Logic;
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
import static com.codeborne.selenide.Selenide.close;

public class QC_1812_TransitionOnProductPageFromChemistryListing {

    private String nameProduct, titleNameProduct;
    private Listing_chemicals_page_Logic listing_chemicals_page_logic = new Listing_chemicals_page_Logic();

    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0");
    }

    @DataProvider(name = "route", parallel = true)
    Object[] dataProviderProducts() throws SQLException {
        return new SetUp().setUpShopWithSubroutes("prod", "DE", "main", "listing_chemicals");
    }

    @Test(dataProvider = "route")
    @Flaky
    @Owner(value = "Sergey-QA")
    @Description(value = "Test Checks transition on product page from first product in listing.")
    public void testCheckingTransitionFromFirstProductInListing(String route) {
        openPage(route);
        nameProduct = listing_chemicals_page_logic.getNameFirstProductInListing();
        listing_chemicals_page_logic.clickFirstProductInListing();
        titleNameProduct = new Product_page_Logic().getNameTitle();
        Assert.assertEquals(nameProduct, titleNameProduct);
    }

    @AfterMethod
    private void tearDown() {
        close();
    }
}
