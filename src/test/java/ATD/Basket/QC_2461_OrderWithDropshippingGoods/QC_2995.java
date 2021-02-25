package ATD.Basket.QC_2461_OrderWithDropshippingGoods;

import ATD.CartAllData_page_Logic;
import ATD.Product_page_Logic;
import Common.DataBase;
import io.qameta.allure.Description;
import io.qameta.allure.Flaky;
import io.qameta.allure.Owner;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.sql.SQLException;

import static ATD.CommonMethods.openPage;
import static ATD.CommonMethods.password;
import static Common.SetUp.setUpBrowser;
import static com.codeborne.selenide.Selenide.closeWebDriver;

public class QC_2995 {

    private DataBase bd = new DataBase("ATD");
    private Product_page_Logic product_page_logic = new Product_page_Logic();
    private String mail = "qc_2995_autotest@mailinator.com";

    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0", false);
    }

    @Test()
    @Flaky
    @Owner(value = "Chelombitko")
    @Description(value = "Displaying postscript with VAT in / basket with a logged in user")
    public void testCheckVisiblePostscriptVatInBasket() throws SQLException {
        openPage(bd.getFullRouteByRouteAndSubroute("prod", "BE", "main", "product71"));
        String prodId = product_page_logic.getProductId();
        String vatPostscript = product_page_logic.addProductToCart()
                .closePopupOtherCategoryIfYes()
                .cartClick()
                .nextButtonClick()
                .signIn(mail, password)
                .fillFieldTelNumForShipping("200+002")
                .nextBtnClick()
                .clickOnTheDesiredPaymentMethod("BE", "Bank")
                .nextBtnClick()
                .checkPresenceVatPostscriptInTotalPriceOfGoods(prodId)
                .checkPresenceOfVatPostscript()
                .getAmountVatPercentage(prodId);
        new CartAllData_page_Logic().clickBtnReturnToCartPage()
                .checkTextContainingVatPercentage(vatPostscript)
                .checkPresenceVatPostscriptInTotalPriceOfGoods(prodId);
    }

    @AfterMethod
    private void close() {
        closeWebDriver();
    }
}
