package PKW.ACC.QC_1798_SideBarOnChemistryListing;

import PKW.Listing_chemicals_page_Logic;
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

public class QC_1801_ClickableCategoriesFromTopProductsSidebarOnChemistryListing {

    private String nameCategory, titleNameCategory;
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
    @Description(value = "Test Checks transition for first category from top products sidebar.")
    public void testCheckingTransitionFirstCategoryFromTopProductsSidebar(String route) {
        openPage(route);
        nameCategory = listing_chemicals_page_logic.getNameFirstCategoryFromTopCategoriesSidebar();
        listing_chemicals_page_logic.clickFirstCategoryFromTopCategoriesSidebar();
        titleNameCategory = listing_chemicals_page_logic.getTitleNameListingPage();
        Assert.assertEquals(nameCategory.toLowerCase(), titleNameCategory.toLowerCase());
    }

    @AfterMethod
    public void close() {
        closeWebDriver();
    }
}
