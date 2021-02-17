package ATD.Basket.QC_2461_OrderWithDropshippingGoods;

import ATD.CartAllData_page_Logic;
import ATD.Product_page_Logic;
import AWS.Customer_view_aws;
import AWS.Order_aws;
import Common.DataBase;
import io.qameta.allure.Description;
import io.qameta.allure.Flaky;
import io.qameta.allure.Owner;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.sql.SQLException;

import static ATD.CommonMethods.*;
import static Common.SetUp.setUpBrowser;
import static com.codeborne.selenide.Selenide.closeWebDriver;
import static com.codeborne.selenide.Selenide.switchTo;

public class QC_2546 {

    private DataBase bd = new DataBase("ATD");
    private Product_page_Logic product_page_logic = new Product_page_Logic();
    private CartAllData_page_Logic cartAllData_page_logic = new CartAllData_page_Logic();
    private String mail = "qc_2546_autotest@mailinator.com";

    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0", false);
    }

    @Test()
    @Flaky
    @Owner(value = "Chelombitko")
    @Description(value = "Heavy goods + drop, undivided billing")
    public void testHeavyGoodsAndDropUndividedBilling() throws SQLException {
        openPage(bd.getFullRouteByRouteAndSubroute("prod", "BE", "main", "product71"));
        String idDropProduct = product_page_logic.addProductToCart()
                .closePopupOtherCategoryIfYes()
                .getProductId();
        openPage(bd.getFullRouteByRouteAndSubroute("prod", "BE", "main", "HeavyLoadProduct3"));
        String amountVatPercentage = product_page_logic.addProductToCart()
                .closePopupOtherCategoryIfYes()
                .cartClick()
                .nextButtonClick()
                .signIn(mail, password)
                .fillFieldTelNumForShipping("200+002")
                .nextBtnClick()
                .clickOnTheDesiredPaymentMethod("BE", "Bank")
                .nextBtnClick()
                .checkPresenceVatPostscriptInTotalPriceOfGoods(idDropProduct)
                .checkFreeDeliveryPriceAllData("0,00")
                .checkRegularDeliveryPrice("9,95")
                .checkPresenceHeavyLoadsDeliveryPriceAllDataPage()
                .checkAbsenceOfVatPostscript()
                .getAmountVatPercentage(idDropProduct);
        float totalPriseInAllData = cartAllData_page_logic.getTotalPriceAllDataPage("BE");
        String orderID = cartAllData_page_logic.nextBtnClick()
                .getOrderNumber();
        Order_aws order_aws = new Order_aws(orderID);
        order_aws.openOrderInAwsWithLogin()
                .checkVatStatusInOrder(amountVatPercentage, "1")
                .checkVatStatusInOrder("Ohne Mwst", "2")
                .checkFirmConfirmationStatus("ДА/auto");
        float totalPriseInOrderWSA = order_aws.getTotalPriceOrderAWS();
        Assert.assertEquals(totalPriseInAllData, totalPriseInOrderWSA);
        order_aws.reSaveOrder()
                .checkVatStatusInOrder(amountVatPercentage, "1")
                .checkVatStatusInOrder("Ohne Mwst", "2")
                .checkFirmConfirmationStatus("ДА/auto")
                .clickCustomerId();
        switchTo().window(1);
        new Customer_view_aws().checkResponseInBlockLogsCompanyNumbers("success(200)");
    }

    @AfterMethod
    private void close() {
        closeWebDriver();
    }
}
