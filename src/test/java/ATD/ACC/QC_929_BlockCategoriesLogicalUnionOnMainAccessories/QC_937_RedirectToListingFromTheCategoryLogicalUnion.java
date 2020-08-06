package ATD.ACC.QC_929_BlockCategoriesLogicalUnionOnMainAccessories;

import ATD.Index_accessories_page_Logic;
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
import static com.codeborne.selenide.Selenide.closeWebDriver;


public class QC_937_RedirectToListingFromTheCategoryLogicalUnion {

    private String nameCategory, nameTitle;
    private Index_accessories_page_Logic index_accessories_page_logic = new Index_accessories_page_Logic();



    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0");
    }

    @DataProvider(name = "route", parallel = true)
    Object[] dataProvider() throws SQLException {
        return new SetUp().setUpShopWithSubroutes("prod", "DE", "main","index_accessories");
    }


    @Test(dataProvider = "route")
    @Flaky
    @Owner(value = "Sergey-QA")
    @Description(value = "Test checking redirect on listing page when clicking on a category in a logical union")
    public void testCheckingRedirectWithCategoryLogicalUnion(String route) {
        openPage(route);
        nameCategory = index_accessories_page_logic.getCategoryName();
        index_accessories_page_logic.clicksOnCategory();
        nameTitle = new Listing_accessories_page_Logic().getTitleName();
        Assert.assertEquals(nameCategory, nameTitle);
    }

    @AfterMethod
    private void close() {
        closeWebDriver();
    }

}

