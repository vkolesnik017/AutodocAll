package ATD.ACC.QC_1042_MainCategoryCatalogOnMainChemie;

import ATD.Index_chemicals_page_Logic;
import ATD.Listing_chemicals_Page_Logic;
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

public class QC_1056_PresenceSeparateCategoryInMainChemistry {

    private String nameCategory, titleNameCategory;
    private Index_chemicals_page_Logic index_chemicals_page_logic = new Index_chemicals_page_Logic();

    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0");
    }

    @DataProvider(name = "route", parallel = true)
    Object[] dataProvider() throws SQLException {
        return new SetUp().setUpShopWithSubroutes("prod", "DE", "main","index_chemicals");
    }

    @Test(dataProvider = "route")
    @Flaky
    @Owner(value = "Sergey-QA")
    @Description(value = "Test checks presence separate category and transition on listing page after click on it")
    public void testCheckPresenceSeparateCategoryAndTransitionOnListingPage(String route) {
        openPage(route);
        nameCategory = index_chemicals_page_logic.getNameFirstSeparateCategoryMainCatalog();
        index_chemicals_page_logic.clickFirstSeparateCategoryMainCatalog();
        titleNameCategory = new Listing_chemicals_Page_Logic().getNameTitleCategory();
        Assert.assertEquals(nameCategory,titleNameCategory);

    }

    @AfterMethod
    private void close() {
        closeWebDriver();
    }
}
