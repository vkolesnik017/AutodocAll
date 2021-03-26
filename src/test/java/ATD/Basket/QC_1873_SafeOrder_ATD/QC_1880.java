package ATD.Basket.QC_1873_SafeOrder_ATD;

import Common.DataBase;
import ATD.Product_page_Logic;
import Common.SetUp;
import io.qameta.allure.Description;
import io.qameta.allure.Flaky;
import io.qameta.allure.Owner;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.sql.SQLException;

import static ATD.CommonMethods.*;
import static Common.SetUp.setUpBrowser;
import static com.codeborne.selenide.Selenide.closeWebDriver;

public class QC_1880 {

    private String mail = "qc_1880_autotest@mailinator.com", prodID;
    private final Product_page_Logic product_page_logic = new Product_page_Logic();

    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0", false);
    }

    @DataProvider(name = "route", parallel = true)
    Object[] dataProviderProducts() throws SQLException {
        return new SetUp("ATD").setUpShopsWithSubroute("prod", "BE,DE,FR", "main", "product32");
    }

    @Test(dataProvider = "route")
    @Flaky
    @Owner(value = "Chelombitko")
    @Description(value = "Test checks displaying SO block after manipulation on route alldata")
    public void testDisplayingSO_BlockAfterManipulationOnRouteAllData(String route) throws SQLException {
        openPage(route);
        String shop = getCurrentShopFromJSVarInHTML();
        prodID = product_page_logic.addProductToCart()
                .closePopupOtherCategoryIfYes()
                .getProductId();
        product_page_logic.cartClick()
                .checkOfIdAddedProductInBasket(prodID);
        openPage(new DataBase("ATD").getFullRouteByRouteAndSubroute("prod", shop, "main", "product2"));
        product_page_logic.addProductToCart()
                .closePopupOtherCategoryIfYes()
                .cartClick()
                .nextButtonClick()
                .signIn(mail, password)
                .fillAllFieldsForShipping(shop)
                .nextBtnClick()
                .choosePayPal()
                .nextBtnClick()
                .checkPresenceSafeOrderBlock()
                .checkPresenceSafeOrderInUpperBlockWithSummery(shop)
                .checkPresenceSafeOrderBlock()
                .checkThatSafeOrderCheckboxIsSelected()
                .counterIncreaseForAllProducts(2)
                .checkPresenceSafeOrderBlock()
                .checkThatSafeOrderCheckboxIsSelected()
                .deleteGoodFromCartAllDataPage(prodID)
                .clickBtnConfirmProductDelete()
                .checkPresenceSafeOrderBlock()
                .checkThatSafeOrderCheckboxIsSelected()
                .clickBtnApplyBonus()
                .checkPresenceSafeOrderBlock()
                .checkThatSafeOrderCheckboxIsSelected()
                .CancelBonusApplying()
                .checkPresenceSafeOrderBlock()
                .checkThatSafeOrderCheckboxIsSelected()
                .applyDiscount("3summjan0801")
                .checkPresenceSafeOrderBlock()
                .checkThatSafeOrderCheckboxIsSelected()
                .openInfoOfProduct()
                .checkPresenceSafeOrderBlock()
                .checkThatSafeOrderCheckboxIsSelected();
    }

    @AfterMethod
    private void close() {
        closeWebDriver();
    }
}