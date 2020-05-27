package ATD.ACC.QC_1356_BlockCategoriesInSidebarOnListingAccessories;

import ATD.Listing_accessories_page_Logic;
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
import static ATD.CommonMethods.openPage;
import static ATD.SetUp.setUpBrowser;
import static com.codeborne.selenide.Selenide.close;

public class QC_1368_SelectCategoryFromSidebar {

    private String nameCategory, titleCategory;
    private Listing_accessories_page_Logic listing_accessories_page_logic = new Listing_accessories_page_Logic();


    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0");
    }

    @DataProvider(name = "route", parallel = true)
    Object[] dataProvider() throws SQLException {
        return new SetUp().setUpShopWithSubroutes("prod", "DE", "main","listing_accessories");
    }

    @Test(dataProvider = "route")
    @Flaky
    @Owner(value = "Sergey-QA")
    @Description(value = "Test checking transition on listing page when clicking on a category in Sidebar .")
    public void testCheckingTransitionWithCategorySidebar(String route) {
        openPage(route);
        nameCategory = listing_accessories_page_logic.getNameFirstCategoryInSidebar();
        listing_accessories_page_logic.clickOnFirstCategoryInSidebar();
        titleCategory = new Listing_accessories_page_Logic().getTitleName();
        Assert.assertEquals(nameCategory, titleCategory);

    }


    @AfterMethod
    private void tearDown() {
        close();
    }

}
