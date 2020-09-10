package ATD.ACC.QC_1260_MainProductCatalogOnMainTools;

import ATD.Index_instruments_page_Logic;
import ATD.Listing_instruments_page_Logic;
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

public class QC_1267_LogicalUnionMainCatalogTools {

    private String nameCategory, titleCategory;
    private Index_instruments_page_Logic index_instruments_page_logic = new Index_instruments_page_Logic();

    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0");
    }

    @DataProvider(name = "route", parallel = true)
    Object[] dataProvider() throws SQLException {
        return new SetUp().setUpShopWithSubroutes("prod", "DE", "main","index_instruments");
    }

    @Test(dataProvider = "route")
    @Flaky
    @Owner(value = "Sergey-QA")
    @Description(value = "Test checking in Logical Union presence catalog and transition on listing page when clicking on a category")
    public void testCheckingInLogicalUnionPresenceCatalogAndTransitionWithCategory(String route) {
        openPage(route);
        nameCategory = index_instruments_page_logic.getNameFirstCategoryInLogicalUnionAfterHover();
        index_instruments_page_logic.clickOnFirstCategoryInLogicalUnion();
        titleCategory = new Listing_instruments_page_Logic().getTitleNameCategory();
        Assert.assertEquals(nameCategory, titleCategory);
    }

    @AfterMethod
    private void close() {
        closeWebDriver();
    }


}
