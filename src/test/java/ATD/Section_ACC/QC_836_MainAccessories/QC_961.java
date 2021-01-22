package ATD.Section_ACC.QC_836_MainAccessories;

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

public class QC_961 {

    private Index_accessories_page_Logic index_accessories_page_logic = new Index_accessories_page_Logic();

    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0", false);
    }

    @DataProvider(name = "route", parallel = true)
    Object[] dataProvider() throws SQLException {
        return new SetUp("ATD").setUpShopWithSubroutes("prod", "DE", "main","index_accessories,index_accessories_group1");
    }

    @Test(dataProvider = "route")
    @Flaky
    @Owner(value = "Sergey-QA")
    @Description(value = "Test checking transition on product page when clicking on a goods in a block top products")
    public void testCheckingTransitionWithGoodsBlockTopProducts(String route) {
        openPage(route);
        String idCategory = index_accessories_page_logic.getIdProductFromTopProductsBlock();
        index_accessories_page_logic.clickOnFirstProductInTop6Block();
        String idProduct = new Product_page_Logic().getIdFromBtnProduct();
        Assert.assertEquals(idCategory, idProduct);
    }


    @AfterMethod
    private void close() {
        closeWebDriver();
    }
}
