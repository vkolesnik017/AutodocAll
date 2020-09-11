package PKW.ACC.QC_1793_ChemistryListing;

import Common.SetUp;
import PKW.Index_chemicals_page_Logic;
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


public class QC_1796_TitlePageListingChemistry {

    private String nameCategory, titleName;
    private Index_chemicals_page_Logic index_chemicals_page_logic = new Index_chemicals_page_Logic();

    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0");
    }

    @DataProvider(name = "route", parallel = true)
    Object[] dataProviderProducts() throws SQLException {
        return new SetUp("PKW").setUpShopWithSubroutes("prod", "DE", "main", "index_chemicals");
    }

    @Test(dataProvider = "route")
    @Flaky
    @Owner(value = "Sergey-QA")
    @Description(value = "Test Checks that title name on Listing page matches selected category")
    public void testCheckingThatListingPageMatchesSelectedCategory(String route) {
        openPage(route);
        nameCategory = index_chemicals_page_logic.getNameSeparateCategoryInMainCatalog();
        index_chemicals_page_logic.clickOnFirstSeparateCategoryMainCatalog();
        titleName = new Listing_chemicals_page_Logic().getTitleNameListingPage();
        Assert.assertEquals(nameCategory.toLowerCase(), titleName.toLowerCase());

    }

    @AfterMethod
    public void close() {
        closeWebDriver();
    }
}
