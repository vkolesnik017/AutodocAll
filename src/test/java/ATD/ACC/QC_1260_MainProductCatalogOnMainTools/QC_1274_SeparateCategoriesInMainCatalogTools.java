package ATD.ACC.QC_1260_MainProductCatalogOnMainTools;

import ATD.*;
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

public class QC_1274_SeparateCategoriesInMainCatalogTools {

    private String nameCategory, titleNameCategory;
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
    @Description(value = "Test checks presence separate category and transition on listing page after click on it")
    public void testCheckPresenceSeparateCategoryAndTransitionOnListingPage(String route) {
        openPage(route);
        nameCategory = index_instruments_page_logic.getNameFirstSeparateCategoryMainCatalog();
        index_instruments_page_logic.clickFirstSeparateCategoryMainCatalog();
        titleNameCategory = new Listing_instruments_page_Logic().getTitleNameCategory();
        Assert.assertEquals(nameCategory, titleNameCategory);
    }

    @AfterMethod
    private void tearDown() {
        close();
    }
}