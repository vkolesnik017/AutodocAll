package ATD.ACC.QC_1042_MainCategoryCatalogOnMainChemie;

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

import static ATD.CommonMethods.openPage;
import static Common.SetUp.setUpBrowser;
import static com.codeborne.selenide.Selenide.closeWebDriver;

public class QC_1059_TransitionToListingPageWithCategoryLogicalUnion {

    private String nameCategory, titleCategory;
    private Index_chemicals_page_Logic index_chemicals_page_logic = new Index_chemicals_page_Logic();

    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0");
    }

    @DataProvider(name = "route", parallel = true)
    Object[] dataProvider() throws SQLException {
        return new SetUp("ATD").setUpShopWithSubroutes("prod", "DE", "main","index_chemicals");
    }

    @Test(dataProvider = "route")
    @Flaky
    @Owner(value = "Sergey-QA")
    @Description(value = "Test checking transition on listing page when clicking on a category in Logical Union ")
    public void testCheckingTransitionWithCategoryLogicalUnion(String route) {
        openPage(route);
        nameCategory = index_chemicals_page_logic.getNameFirstCategoryInLogicalUnionAfterHover();
        index_chemicals_page_logic.clickOnFirstCategoryInLogicalUnion();
        titleCategory = new Listing_chemicals_Page_Logic().getNameTitleCategory();
        Assert.assertEquals(nameCategory, titleCategory);
    }

    @AfterMethod
    private void close() {
        closeWebDriver();
    }
}
