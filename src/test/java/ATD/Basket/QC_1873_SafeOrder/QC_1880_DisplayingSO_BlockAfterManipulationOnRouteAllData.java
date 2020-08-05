package ATD.Basket.QC_1873_SafeOrder;

import ATD.DataBase;
import ATD.Product_page_Logic;
import io.qameta.allure.Description;
import io.qameta.allure.Flaky;
import io.qameta.allure.Owner;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.sql.SQLException;

import static ATD.CommonMethods.*;
import static ATD.SetUp.setUpBrowser;
import static com.codeborne.selenide.Selenide.close;

public class QC_1880_DisplayingSO_BlockAfterManipulationOnRouteAllData {

    private String mail = "qc_1880_autotest@mailinator.com", prodID;
    private Product_page_Logic product_page_logic = new Product_page_Logic();

    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0");
    }

    @Test
    @Flaky
    @Owner(value = "Chelombitko")
    @Description(value = "Test checks displaying SO block after manipulation on route alldata")
    public void testDisplayingSO_BlockAfterManipulationOnRouteAllData() throws SQLException {
        openPage(new DataBase().getFullRouteByRouteAndSubroute("prod", "BE", "main", "product32"));
        String shop = getCurrentShopFromJSVarInHTML();
        prodID = product_page_logic.addProductToCart()
                .closePopupOtherCategoryIfYes()
                .getProductId();
        product_page_logic.cartClick()
                .checkOfIdAddedProductInBasket(prodID);
        openPage(new DataBase().getFullRouteByRouteAndSubroute("prod", "BE", "main", "product7"));
        product_page_logic.addProductToCart()
                .closePopupOtherCategoryIfYes()
                .cartClick()
                .nextButtonClick()
                .signIn(mail, password)
                .fillAllFields(shop)
                .nextBtnClick()
                .choosePayPal()
                .nextBtnClick()
                .checkPresenceSafeOrderBlock()
                .checkThatSafeOrderCheckboxIsSelected()
                .openUpperBlockWithSummary()
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
    private void tearDown() {
        close();
    }
}