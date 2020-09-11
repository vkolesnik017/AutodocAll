package ATD.ACC.QC_950_BlockTopProductsOnMainAccessories;

import ATD.Index_accessories_page_Logic;
import ATD.Product_page_Logic;
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

public class QC_961_TransitionOnProductPageWithMiniCardTopProductBlock {

    private String nameCategory, nameTitle;
    private Index_accessories_page_Logic index_accessories_page_logic = new Index_accessories_page_Logic();

    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0");
    }

    @DataProvider(name = "route", parallel = true)
    Object[] dataProvider() throws SQLException {
        return new SetUp("ATD").setUpShopWithSubroutes("prod", "DE", "main","index_accessories");
    }

    @Test(dataProvider = "route")
    @Flaky
    @Owner(value = "Sergey-QA")
    @Description(value = "Test checking transition on product page when clicking on a goods in a block top products")
    public void testCheckingTransitionWithGoodsBlockTopProducts(String route) {
        openPage(route);
        nameCategory = index_accessories_page_logic.getNameFirstProductInTop6Block();
        index_accessories_page_logic.clickOnFirstProductInTop6Block();
        nameTitle = new Product_page_Logic().getTitleNameForProductPageAccessories();
        Assert.assertEquals(nameCategory, nameTitle);

    }


    @AfterMethod
    private void close() {
        closeWebDriver();
    }
}
