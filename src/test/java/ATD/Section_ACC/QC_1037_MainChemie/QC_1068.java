package ATD.Section_ACC.QC_1037_MainChemie;

import ATD.Index_chemicals_page_Logic;
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

public class QC_1068 {

    private String nameProduct, nameTitleProduct;
    private Index_chemicals_page_Logic index_chemicals_page_logic = new Index_chemicals_page_Logic();


    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0", false);
    }

    @DataProvider(name = "route", parallel = true)
    Object[] dataProvider() throws SQLException {
        return new SetUp("ATD").setUpShopWithSubroutes("prod", "DE", "main", "index_chemicals");
    }

    @Test(dataProvider = "route")
    @Flaky
    @Owner(value = "Sergey-QA")
    @Description(value = "Test checking transition on product page when clicking on a product in a block top products")
    public void testCheckingTransitionWithProductBlockTopProducts(String route) {
        openPage(route);
        nameProduct = index_chemicals_page_logic.getNameProductInBlockTopProducts();
        index_chemicals_page_logic.checkingTransitionOnProductPageAfterClickDetails();
        nameTitleProduct = new Product_page_Logic().getTitleNameProduct();
        Assert.assertEquals(nameProduct, nameTitleProduct);
    }

    @AfterMethod
    private void close() {
        closeWebDriver();
    }

}
