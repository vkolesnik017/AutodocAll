package ATD.Section_ACC.QC_1340_ListingAccessories;

import ATD.Index_accessories_group_page_Logic;
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

public class QC_3428 {


    private Listing_accessories_page_Logic listing_accessories_page_logic = new Listing_accessories_page_Logic();
    private Index_accessories_group_page_Logic indexAccessoriesGroupPageLogic = new Index_accessories_group_page_Logic();



    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0", false);
    }

    @DataProvider(name = "route", parallel = true)
    Object[] dataProvider() throws SQLException {
        return new SetUp("ATD").setUpShopWithSubroutes("prod", "DE", "main","listing_accessories,accessories_listing_criteria2");
    }


    @Test(dataProvider = "route")
    @Flaky
    @Owner(value = "Sergey-QA")
    @Description(value = "Test Checking the Logical Union block in the sidebar on accessory listings")
    public void testCheckingLogicalUnionBlockInSidebarOnAccessoryListings(String route) {
        openPage(route);
        ArrayList <String> nameCategoriesSidebar = listing_accessories_page_logic.getNameAllCategoriesInSidebarAndTitleNamePage();
        listing_accessories_page_logic.transitionThroughEachCategoryInSidebar()
                .clickThirdBreadCrumb();
        ArrayList <String> nameCategories = indexAccessoriesGroupPageLogic.getAllCategoriesName();
        Assert.assertEquals(nameCategoriesSidebar, nameCategories);
    }

    @AfterMethod
    private void close() {
        closeWebDriver();
    }

}
