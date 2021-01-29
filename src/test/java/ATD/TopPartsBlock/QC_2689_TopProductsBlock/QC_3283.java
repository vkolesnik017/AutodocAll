package ATD.TopPartsBlock.QC_2689_TopProductsBlock;

import ATD.Cart_page_Logic;
import ATD.Supplier_brand_line_page_Logic;
import Common.SetUp;
import io.qameta.allure.Description;
import io.qameta.allure.Flaky;
import io.qameta.allure.Owner;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.sql.SQLException;

import static ATD.CommonMethods.openPage;
import static Common.SetUp.setUpBrowser;
import static com.codeborne.selenide.Selenide.closeWebDriver;

public class QC_3283 {

    Supplier_brand_line_page_Logic brandLinePage = new Supplier_brand_line_page_Logic();

    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0", false);
    }

    @DataProvider(name = "routes", parallel = true)
    Object[] dataProvider() throws SQLException {
        return new SetUp("ATD").setUpShopWithSubroutes("prod", "DE", "main", "supplier_brand_line2");
    }

    @Test(dataProvider = "routes")
    @Flaky
    @Owner(value = "Kolesnik")
    @Description(value = "Test check adding of TOP product to basket")
    public void testChecksAddingTopProductToBasket(String route) {
        openPage(route);
        String idOfAddedProduct = brandLinePage.presenceTopProductsBlock().getIdOfTopProduct(0);
        brandLinePage.addTopProductToBasket(0)
                .appearBasketDropMenu()
                .clickOnBasketInHeader();
        new Cart_page_Logic().checkOfIdAddedProductInBasket(idOfAddedProduct);
    }

    @AfterMethod
    public void close() {
        closeWebDriver();
    }
}
