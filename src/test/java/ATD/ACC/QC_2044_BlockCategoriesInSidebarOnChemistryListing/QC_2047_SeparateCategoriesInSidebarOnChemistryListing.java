package ATD.ACC.QC_2044_BlockCategoriesInSidebarOnChemistryListing;

import ATD.Index_chemicals_page_Logic;
import ATD.Listing_chemicals_Page_Logic;
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
import java.util.ArrayList;

import static ATD.CommonMethods.openPage;
import static ATD.SetUp.setUpBrowser;
import static com.codeborne.selenide.Selenide.closeWebDriver;

public class QC_2047_SeparateCategoriesInSidebarOnChemistryListing {

    private ArrayList nameCategories, nameCategoriesSidebar;
    private Index_chemicals_page_Logic index_chemicals_page_logic = new Index_chemicals_page_Logic();

    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0");
    }

    @DataProvider(name = "route", parallel = true)
    Object[] dataProvider() throws SQLException {
        return new SetUp().setUpShopWithSubroutes("prod", "DE", "main", "index_chemicals");
    }

    @Test(dataProvider = "route")
    @Flaky
    @Owner(value = "Sergey-QA")
    @Description(value = "Test Checking comparison between separate categories on main page and sidebar on listing.")
    public void testCheckingComparisonCategoriesBetweenMainPageAndSidebarOnListing(String route) {
        openPage(route);
        nameCategories = index_chemicals_page_logic.getNameAllSeparateCategoriesInMainCatalogAndAddToList();
        index_chemicals_page_logic.clickFirstSeparateCategoryMainCatalog();
        nameCategoriesSidebar = new Listing_chemicals_Page_Logic().getNameCategoriesFromSidebarAndTitleNamePage();
        Assert.assertEquals(nameCategories, nameCategoriesSidebar);
    }

    @AfterMethod
    private void close() {
        closeWebDriver();
    }
}
