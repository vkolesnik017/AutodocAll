package PKW.Section_ACC.QC_3017_Chemistry_Listing;

import Common.SetUp;
import PKW.Listing_chemicals_page_Logic;
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

public class QC_1800 {

    private String nameCategory, titleNameCategory;
    private Listing_chemicals_page_Logic listing_chemicals_page_logic = new Listing_chemicals_page_Logic();

    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0", false);
    }

    @DataProvider(name = "route", parallel = true)
    Object[] dataProviderProducts() throws SQLException {
        return new SetUp("PKW").setUpShopWithSubroutes("prod", "DE", "main", "listing_chemicals");
    }

    @Test(dataProvider = "route")
    @Flaky
    @Owner(value = "Sergey-QA")
    @Description(value = "Test Checks presence top categories block from sidebar and checks transition for first category from top products sidebar.")
    public void testCheckingPresenceTopCategoriesBlockFromSidebarAndTransitionFromTopProducts(String route) {
        openPage(route);
        new Listing_chemicals_page_Logic().checkingPresenceTopCategoriesBlockFromSidebar();
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
