package ATD.Section_ACC.QC_2655_ToolsMain;

import ATD.Index_instruments_page_Logic;
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

public class QC_1282 {

    private String nameProduct, titleNameProduct;
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
    @Description(value = "Test checking transition on product page when clicking on a Product in a block top-6 ")
    public void testCheckingTransitionWithProductBlockTop6(String route) {
        openPage(route);
        nameProduct = index_instruments_page_logic.getNameFirstProductInTop6ProductBlock();
        index_instruments_page_logic.clickOnFirstProductInBlockTop6();
        titleNameProduct = new Product_page_Logic().getTitleNameProduct();
        Assert.assertEquals(nameProduct, titleNameProduct);
    }


    @AfterMethod
    private void close() {
        closeWebDriver();
    }


}
