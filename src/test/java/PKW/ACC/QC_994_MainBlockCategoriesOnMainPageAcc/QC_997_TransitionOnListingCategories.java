package PKW.ACC.QC_994_MainBlockCategoriesOnMainPageAcc;

import PKW.Index_accessories_page_Logic;
import PKW.Listing_accessories_page_Logic;
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

public class QC_997_TransitionOnListingCategories {

    private String nameCategory, titleNameListing;
    private Index_accessories_page_Logic index_accessories_page_logic = new Index_accessories_page_Logic();

    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0");
    }

    @DataProvider(name = "route", parallel = true)
    Object[] dataProviderProducts() throws SQLException {
        return new SetUp().setUpShopWithSubroutes("prod", "DE", "main", "index_accessories");
    }

    @Test(dataProvider = "route")
    @Flaky
    @Owner(value = "Sergey-QA")
    @Description(value = "Test Checks transition on listing page from category main catalog")
    public void testCheckingTransitionOnListingPageFromCategoryMainCatalog(String route) {
        openPage(route);
        nameCategory = index_accessories_page_logic.getNameFirstCategoryFromFirstBlockInMainCatalog();
        index_accessories_page_logic.clickOnFirstCategoryInBlockCategoriesMainCatalog();
        titleNameListing = new Listing_accessories_page_Logic().getNameMainTitleOnListingPage();
        Assert.assertEquals(nameCategory, titleNameListing);
    }

    @AfterMethod
    public void close() {
        closeWebDriver();
    }


}
