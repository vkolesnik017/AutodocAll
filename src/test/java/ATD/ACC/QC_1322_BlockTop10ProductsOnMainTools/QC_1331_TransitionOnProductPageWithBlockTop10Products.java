package ATD.ACC.QC_1322_BlockTop10ProductsOnMainTools;

import ATD.Index_instruments_page_Logic;
import ATD.Product_page_Logic;
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


public class QC_1331_TransitionOnProductPageWithBlockTop10Products {

    private String nameProduct, nameTitle;
    private Index_instruments_page_Logic index_instruments_page_logic = new Index_instruments_page_Logic();


    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0");
    }

    @DataProvider(name = "route", parallel = true)
    Object[] dataProvider() throws SQLException {
        return new SetUp().setUpShopWithSubroutes("prod", "DE", "main", "index_instruments");
    }

    @Test(dataProvider = "route")
    @Flaky
    @Owner(value = "Sergey-QA")
    @Description(value = "Test checking transition on product page after click on product block top-10")
    public void testCheckingTransitionOnProductPage(String route) {
        openPage(route);
        nameProduct = index_instruments_page_logic.getProductName();
        index_instruments_page_logic.clickFirstProductInTopBlock();
        nameTitle = new Product_page_Logic().getTitleNameForProductPageInstruments();
        Assert.assertEquals(nameProduct, nameTitle);
    }

        @AfterMethod
        private void close() {
            closeWebDriver();
        }

}