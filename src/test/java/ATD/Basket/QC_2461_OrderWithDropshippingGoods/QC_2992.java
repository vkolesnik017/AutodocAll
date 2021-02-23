package ATD.Basket.QC_2461_OrderWithDropshippingGoods;

import ATD.CartAllData_page_Logic;
import ATD.Product_page_Logic;
import ATD.Tyre_item_page_Logic;
import ATD.Versand_static_page_Logic;
import AWS.Customer_view_aws;
import AWS.Order_aws;
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
import static com.codeborne.selenide.Selenide.switchTo;

public class QC_2992 {

    private DataBase bd = new DataBase("ATD");
    private Product_page_Logic product_page_logic = new Product_page_Logic();
    private CartAllData_page_Logic cartAllData_page_logic = new CartAllData_page_Logic();
    private float deliveryPrice;
    private String mail = "qc_2992_autotest@mailinator.com";

    @BeforeClass
    void setUp() throws SQLException {
        setUpBrowser(false, "chrome", "77.0", false);
        openPage(bd.getFullRouteByRouteAndSubroute("prod", "BE", "main", "staticVersand"));
        deliveryPrice = new Versand_static_page_Logic().getDeliveryPrice();
    }

    @Test()
    @Flaky
    @Owner(value = "Chelombitko")
    @Description(value = "Adding a regular product, then returning to the main page and adding a drop")
    public void testAddingRegularProductTheReturningToMainPageAndAddingDrop() throws SQLException {
        openPage(bd.getFullRouteByRouteAndSubroute("prod", "BE", "main", "product32"));
        product_page_logic.addProductToCart()
                .closePopupOtherCategoryIfYes()
                .cartClick()
                .nextButtonClick()
                .signIn(mail, password)
                .fillFieldTelNumForShipping("200+002")
                .nextBtnClick()
                .clickOnTheDesiredPaymentMethod("BE", "Bank")
                .nextBtnClick()
                .checkRegularDeliveryPrice(deliveryPrice)
                .checkAbsenceOfVatPostscript();
        openPage(bd.getFullRouteByRouteAndSubroute("prod", "BE", "main", "product71"));
        String productID = product_page_logic.getProductId();
        String mpnNum = new Tyre_item_page_Logic().getMpnNumOfProduct();
        String amountVatPercentage = product_page_logic.addProductToCart()
                .closePopupOtherCategoryIfYes()
                .cartClickAndReturnAllDataPage()
                .checkPresenceVatPostscriptInTotalPriceOfGoods(productID)
                .getAmountVatPercentage(productID);
        String orderID = cartAllData_page_logic.nextBtnClick()
                .getOrderNumber();
        Order_aws order_aws = new Order_aws(orderID);
        order_aws.openOrderInAwsWithLogin()
                .checkVatStatusInOrder(amountVatPercentage, "1")
                .checkVatStatusInOrder("Ohne Mwst", "2")
                .checkPresenceVatInSellingPrice(mpnNum, "inkl." + amountVatPercentage  + "% VAT")
                .reSaveOrder()
                .checkVatStatusInOrder(amountVatPercentage, "1")
                .checkVatStatusInOrder("Ohne Mwst", "2")
                .checkPresenceVatInSellingPrice(mpnNum, "inkl." + amountVatPercentage  + "% VAT")
                .clickCustomerId();
        switchTo().window(1);
        new Customer_view_aws().checkResponseInBlockLogsCompanyNumbers("success(200)");
    }

    @AfterMethod
    private void close() {
        closeWebDriver();
    }
}
