package PKW.Section_ACC.QC_1745_MainTools;

import Common.SetUp;
import PKW.Index_instruments_page_Logic;
import PKW.Listing_instruments_Page_Logic;
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


public class QC_1755 {

    private String nameCategory, titleNameCategory;
    private Index_instruments_page_Logic index_instruments_page_logic = new Index_instruments_page_Logic();

    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0", false);
    }

    @DataProvider(name = "route", parallel = true)
    Object[] dataProviderProducts() throws SQLException {
        return new SetUp("PKW").setUpShopWithSubroutes("prod", "DE", "main", "index_instruments");
    }

    @Test(dataProvider = "route")
    @Flaky
    @Owner(value = "Sergey-QA")
    @Description(value = "Test checks transition first category from top category block.")
    public void testCheckingTransitionFirstCategoryFromTopCategoryBlock(String route) {
        openPage(route);
        nameCategory = index_instruments_page_logic.getNameFirstCategoryFromTopCategoriesBlock();
        index_instruments_page_logic.clickFirstCategoryFromTopCategoriesBlock();
        titleNameCategory = new Listing_instruments_Page_Logic().getNameTitleCategory();
        Assert.assertEquals(nameCategory, titleNameCategory);
    }

    @AfterMethod
    public void close() {
        closeWebDriver();
    }
}
