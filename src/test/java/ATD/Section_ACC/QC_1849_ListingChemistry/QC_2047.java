package ATD.Section_ACC.QC_1849_ListingChemistry;

import ATD.Index_chemicals_page_Logic;
import ATD.Listing_chemicals_Page_Logic;
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
import java.util.ArrayList;

import static ATD.CommonMethods.openPage;
import static Common.SetUp.setUpBrowser;
import static com.codeborne.selenide.Selenide.closeWebDriver;

public class QC_2047 {

    private ArrayList nameCategories, nameCategoriesSidebar;
    private Index_chemicals_page_Logic index_chemicals_page_logic = new Index_chemicals_page_Logic();
    private  Listing_chemicals_Page_Logic listingChemicalsPageLogic = new Listing_chemicals_Page_Logic();

    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0", false);
    }

    @DataProvider(name = "route", parallel = true)
    Object[] dataProvider() throws SQLException {
        return new SetUp("ATD").setUpShopWithSubroutes("prod", "DE", "main", "index_chemicals");
    }

    @Test(dataProvider = "route")
    @Flaky
    @Owner(value = "Sergey-QA")
    @Description(value = "Test Checking comparison between separate categories on main page and sidebar on listing.")
    public void testCheckingComparisonCategoriesBetweenMainPageAndSidebarOnListing(String route) {
        openPage(route);
        nameCategories = index_chemicals_page_logic.getNameAllSeparateCategoriesInMainCatalogAndAddToList();
        index_chemicals_page_logic.clickFirstSeparateCategoryMainCatalog();
        nameCategoriesSidebar = listingChemicalsPageLogic.getNameCategoriesFromSidebarAndTitleNamePage();
        Assert.assertEquals(nameCategories, nameCategoriesSidebar);
        listingChemicalsPageLogic.transitionThroughEachCategoryInSidebar();
    }

    @AfterMethod
    private void close() {
        closeWebDriver();
    }
}
