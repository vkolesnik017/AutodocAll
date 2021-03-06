package ATD.Section_ACC.QC_1340_ListingAccessories;

import ATD.Index_accessories_page_Logic;
import ATD.Listing_accessories_page_Logic;
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

public class QC_1366 {

    private String nameCategory, titleCategory;
    private Listing_accessories_page_Logic listing_accessories_page_logic = new Listing_accessories_page_Logic();

    private ArrayList nameCategories, nameCategoriesSidebar;
    private Index_accessories_page_Logic index_accessories_page_logic = new Index_accessories_page_Logic();


    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0", false);
    }

    @DataProvider(name = "route", parallel = true)
    Object[] dataProvider() throws SQLException {
        return new SetUp("ATD").setUpShopWithSubroutes("prod", "DE", "main","index_accessories");
    }


    @Test(dataProvider = "route")
    @Flaky
    @Owner(value = "Sergey-QA")
    @Description(value = "Test Checking category comparison between sidebar and logical union and transition on listing page when clicking on a category in Sidebar.")
    public void testChecksComparisonCategoriesBetweenSidebarAndLogicalUnion(String route) {
        openPage(route);
        nameCategories = new Index_accessories_page_Logic().getNameAllCategoriesInLogicalUnionAndAddToList();
        index_accessories_page_logic.clicksOnCategory();
        nameCategoriesSidebar = new Listing_accessories_page_Logic().getNameAllCategoriesInSidebarAndTitleNamePage();
        Assert.assertEquals(nameCategories, nameCategoriesSidebar);
        nameCategory = listing_accessories_page_logic.getNameFirstCategoryInSidebar();
        listing_accessories_page_logic.clickOnFirstCategoryInSidebar();
        titleCategory = new Listing_accessories_page_Logic().getTitleName();
        Assert.assertEquals(nameCategory, titleCategory);
    }

    @AfterMethod
    private void close() {
        closeWebDriver();
    }

}
