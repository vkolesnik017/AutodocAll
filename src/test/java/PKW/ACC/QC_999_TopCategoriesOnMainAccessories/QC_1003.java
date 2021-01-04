package PKW.ACC.QC_999_TopCategoriesOnMainAccessories;

import Common.SetUp;
import PKW.Index_accessories_page_Logic;
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
import static com.codeborne.selenide.WebDriverRunner.url;

public class QC_1003 {

    private String urlCategory, urlName;
    Index_accessories_page_Logic indexAccessoriesPageLogic = new Index_accessories_page_Logic();

    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0", false);
    }

    @DataProvider(name = "route", parallel = true)
    Object[] dataProviderProducts() throws SQLException {
        return new SetUp("PKW").setUpShopWithSubroutes("prod", "DE", "main", "index_accessories");
    }

    @Test(dataProvider = "route")
    @Flaky
    @Owner(value = "Sergey-QA")
    @Description(value = "Test checks transition category from top category block.")
    public void testCheckingTransitionCategoryFromTopCategoryBlock(String route) {
        openPage(route);
        urlCategory = indexAccessoriesPageLogic.getURLProductFromTopProductsBlock();
        indexAccessoriesPageLogic.clickOnProductFromTopCategoriesBlock();
        urlName = url();
        Assert.assertEquals(urlCategory, urlName);
    }

    @AfterMethod
    public void close() {
        closeWebDriver();
    }
}
