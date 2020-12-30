package ATD.ACC.QC_2115_BlockWithProductsOnChemistryListing;

import ATD.Listing_chemicals_Page_Logic;
import ATD.Product_page_Logic;
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
import static com.codeborne.selenide.Selenide.closeWebDriver;

public class QC_2118_TransitionOnProductPageWithChemistryListing {

    private String idProductFromListing, idProduct;
    private Listing_chemicals_Page_Logic listingChemicalsPageLogic = new Listing_chemicals_Page_Logic();

    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0", false);
    }

    @DataProvider(name = "route", parallel = true)
    Object[] dataProvider() throws SQLException {
        return new SetUp("ATD").setUpShopWithSubroutes("prod", "DE", "main", "listing_chemicals");
    }

    @Test(dataProvider = "route")
    @Flaky
    @Owner(value = "Sergey-QA")
    @Description(value = "Test Checks transition with main product.")
    public void testCheckingTransitionWithMainProduct(String route) {
        openPage(route);
        idProductFromListing = listingChemicalsPageLogic.getIdProductListing();
        listingChemicalsPageLogic.clickOnProductTitle();
        idProduct = new Product_page_Logic().getIdFromBtnProduct();
        Assert.assertEquals(idProductFromListing, idProduct);

    }

    @AfterMethod
    private void close() {
        closeWebDriver();
    }
}
