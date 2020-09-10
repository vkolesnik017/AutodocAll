package ATD.ACC.QC_2044_BlockCategoriesInSidebarOnChemistryListing;

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

import static ATD.CommonMethods.openPage;
import static Common.SetUp.setUpBrowser;
import static com.codeborne.selenide.Selenide.closeWebDriver;

public class QC_2048_SelectCategoryFromSidebarOnChemistryListing {

    private String nameCategory, titleNameCategory;
    private Listing_chemicals_Page_Logic listing_chemicals_page_logic = new Listing_chemicals_Page_Logic();

    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0");
    }

    @DataProvider(name = "route", parallel = true)
    Object[] dataProvider() throws SQLException {
        return new SetUp("ATD").setUpShopWithSubroutes("prod", "DE", "main", "listing_chemicals");
    }

    @Test(dataProvider = "route")
    @Flaky
    @Owner(value = "Sergey-QA")
    @Description(value = "Test checks transition for first category from sidebar .")
    public void testCheckingTransitionForFirstCategoryFromSidebar(String route) {
        openPage(route);
        nameCategory = listing_chemicals_page_logic.getNameFirstCategoryInSidebar();
        listing_chemicals_page_logic.clickFirstCategoryInSidebar();
        titleNameCategory = listing_chemicals_page_logic.getNameTitleCategory();
        Assert.assertEquals(nameCategory,titleNameCategory);
    }

    @AfterMethod
    private void close() {
        closeWebDriver();
    }
}
