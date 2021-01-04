package PKW.ACC.QC_994_MainBlockCategoriesOnMainPageAcc;

import Common.SetUp;
import PKW.Index_accessories_page_Logic;
import PKW.Listing_accessories_page_Logic;
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

public class QC_996 {

    private String nameCategory, nameListing;
    private Index_accessories_page_Logic indexAccessoriesPageLogic = new Index_accessories_page_Logic();

    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0", false);
    }

    @DataProvider(name = "route", parallel = true)
    Object[] dataProviderProducts() throws SQLException {
        return new SetUp("PKW").setUpShopWithSubroutes("prod", "DE", "main", "index_accessories");
    }

    @Test(dataProvider = "route")
    @Flaky
    @Owner(value = "Sergey-QA")
    @Description(value = "Test Checks quantity blocks with categories and transition on listing from main catalog")
    public void testCheckingQuantityBlocksWithCategoriesAndTransitionOnListing(String route) {
        openPage(route);
        indexAccessoriesPageLogic.checkingQuantityBlocksWithCategoriesInMainCatalog();
        nameCategory = indexAccessoriesPageLogic.getNameFirstCategoryFromFirstBlockInMainCatalog();
        indexAccessoriesPageLogic.clickOnFirstCategoryInBlockCategoriesMainCatalog();
        nameListing = new Listing_accessories_page_Logic().getNameMainTitleOnListingPage();
        Assert.assertEquals(nameCategory,nameListing);
    }

    @AfterMethod
    public void close() {
        closeWebDriver();
    }
}
