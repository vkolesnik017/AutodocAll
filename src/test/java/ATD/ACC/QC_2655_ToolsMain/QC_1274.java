package ATD.ACC.QC_2655_ToolsMain;

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

public class QC_1274 {

    private String nameCategory, titleNameCategory;
    private Index_instruments_page_Logic index_instruments_page_logic = new Index_instruments_page_Logic();

    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0", false);
    }

    @DataProvider(name = "route", parallel = true)
    Object[] dataProvider() throws SQLException {
        return new SetUp("ATD").setUpShopWithSubroutes("prod", "DE", "main","index_instruments");
    }

    @Test(dataProvider = "route")
    @Flaky
    @Owner(value = "Sergey-QA")
    @Description(value = "Test checks presence separate category and transition on listing page after click on it")
    public void testCheckPresenceSeparateCategoryAndTransitionOnListingPage(String route) {
        openPage(route);
        nameCategory = index_instruments_page_logic.getNameFirstSeparateCategoryMainCatalog();
        index_instruments_page_logic.clickFirstSeparateCategoryMainCatalog();
        titleNameCategory = new Listing_instruments_page_Logic().getTitleNameCategory();
        Assert.assertEquals(nameCategory, titleNameCategory);
    }

    @AfterMethod
    private void close() {
        closeWebDriver();
    }
}
