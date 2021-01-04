package PKW.ACC.QC_1417_MainChemistry;

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


public class QC_1545 {

    private String nameCategory, titleCategory;
    private Index_chemicals_page_Logic index_chemicals_page_logic = new Index_chemicals_page_Logic();

    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0", false);
    }

    @DataProvider(name = "route", parallel = true)
    Object[] dataProviderProducts() throws SQLException {
        return new SetUp("PKW").setUpShopWithSubroutes("prod", "DE", "main", "index_chemicals");
    }

    @Test(dataProvider = "route")
    @Flaky
    @Owner(value = "Sergey-QA")
    @Description(value = "Test checks presence top categories block , transition by first category, work btn more and less.")
    public void testCheckingSeoCategoriesBlock(String route) {
        openPage(route);
        index_chemicals_page_logic.checkingPresenceSeoTopCategoriesBlock()
                .checkWorkBtnMoreAndLessInTopCategoryBlock();
        nameCategory = index_chemicals_page_logic.getNameFirstCategoryInTopCategoriesBlock();
        index_chemicals_page_logic.clickFirstCategoryInTopCategoriesBlock();
        titleCategory = new Listing_chemicals_page_Logic().getTitleNameListingPage();
        Assert.assertEquals(nameCategory, titleCategory);
    }

    @AfterMethod
    public void close() {
        closeWebDriver();
    }
}
