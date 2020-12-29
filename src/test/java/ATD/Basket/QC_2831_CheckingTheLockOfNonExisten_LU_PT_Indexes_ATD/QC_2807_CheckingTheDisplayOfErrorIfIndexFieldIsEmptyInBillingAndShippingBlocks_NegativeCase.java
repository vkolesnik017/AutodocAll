package ATD.Basket.QC_2831_CheckingTheLockOfNonExisten_LU_PT_Indexes_ATD;

import ATD.CartAddress_page_Logic;
import ATD.Product_page_Logic;
import Common.SetUp;
import io.qameta.allure.Description;
import io.qameta.allure.Flaky;
import io.qameta.allure.Owner;
import org.testng.annotations.*;

import java.sql.SQLException;

import static ATD.CommonMethods.*;
import static Common.SetUp.setUpBrowser;
import static com.codeborne.selenide.Selenide.closeWebDriver;

public class QC_2807_CheckingTheDisplayOfErrorIfIndexFieldIsEmptyInBillingAndShippingBlocks_NegativeCase {

    private String mail = "QC_2807_autotestATD@mailinator.com";
    private CartAddress_page_Logic cartAddress_page_logic = new CartAddress_page_Logic();

    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0", false);
    }

    @DataProvider(name = "shop", parallel = true)
    Object[] dataProvider() throws SQLException {
        return new SetUp("ATD").setUpShopsWithSubroute("prod", "PT,LD", "main", "product32");
    }

    @Test(dataProvider = "shop")
    @Flaky
    @Owner(value = "Chelombitko")
    @Description(value = "Test checking the display of an error if the index field is empty in the Billing and Delivery blocks. Negative case")
    public void testCheckingTheDisplayOfErrorIfIndexFieldIsEmptyInBillingAndShippingBlocks(String shop) {
        openPage(shop);
        String actualShop = getCurrentShopFromJSVarInHTML();
        new Product_page_Logic().addProductToCart()
                .closePopupOtherCategoryIfYes()
                .cartClick()
                .nextButtonClick()
                .signIn(mail, password)
                .chooseDeliveryCountryForShipping(actualShop)
                .clickCheckboxForOpenBilling()
                .chooseDeliveryCountryForBilling(actualShop)
                .clearFieldsPostalCod()
                .nextBtnClick();
        cartAddress_page_logic.checkPresenceElement(cartAddress_page_logic.errorTooltipForShipping())
                .checkPresenceElement(cartAddress_page_logic.errorTooltipForBilling());
    }

    @AfterMethod
    private void close() {
        closeWebDriver();
    }
}
