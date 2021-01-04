package ATD.ACC.QC_886_TopCategoriesDirectionOnMainAccessories;

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

import static ATD.CommonMethods.openPage;
import static Common.SetUp.setUpBrowser;
import static com.codeborne.selenide.Selenide.closeWebDriver;

public class QC_920 {

    private String nameCategory, nameTitle;
    private Index_accessories_page_Logic index_accessories_page_logic = new Index_accessories_page_Logic();

    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0", false);
    }

    @DataProvider(name = "route", parallel = true)
    Object[] dataProvider() throws SQLException {
        return new SetUp("ATD").setUpShopWithSubroutes("prod", "DE", "main", "index_accessories");
    }

    @Test(dataProvider = "route")
    @Flaky
    @Owner(value = "Sergey-QA")
    @Description(value = "Test checking transition on listing page when clicking on a category in a block top accessories, the number of categories in each tab of the top accessories block and presence top categories block")
    public void testCheckTransitionAfterClickingTheCategoryAndNumberOfCategories(String route) {
        openPage(route);
        new Index_accessories_page_Logic().checkNumberOfCategoriesInEachTabBlockTopAcc()
                .checkingPresenceOfTopCategoriesBlock();
        nameCategory = index_accessories_page_logic.getNameFirstCategoryInBlockTopAccessories();
        index_accessories_page_logic.clickOnCategoryInBlockTopAccessories();
        nameTitle = new Listing_accessories_page_Logic().getTitleName();
        Assert.assertEquals(nameCategory.toLowerCase(), nameTitle.toLowerCase());
    }

    @AfterMethod
    private void close() {
        closeWebDriver();
    }
}
