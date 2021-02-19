package ATD.Basket.QC_2461_OrderWithDropshippingGoods;

import ATD.CartAllData_page_Logic;
import ATD.Product_page_Logic;
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

import static ATD.CommonMethods.openPage;
import static ATD.CommonMethods.password;
import static Common.SetUp.setUpBrowser;
import static com.codeborne.selenide.Selenide.closeWebDriver;

public class QC_2942 {

    private DataBase bd = new DataBase("ATD");
    private Product_page_Logic product_page_logic = new Product_page_Logic();
    private CartAllData_page_Logic cartAllData_page_logic = new CartAllData_page_Logic();
    private String mail = "qc_2942_autotest@mailinator.com";

    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0", false);
    }

    @Test()
    @Flaky
    @Owner(value = "Chelombitko")
    @Description(value = "Adding a regular product to an existing order with a drop")
    public void testAddingRegularProductToExistingOrderWithDrop() throws SQLException {
        openPage(bd.getFullRouteByRouteAndSubroute("prod", "BE", "main", "product92"));
        String productID = product_page_logic.getProductId();
        float totalPriceAllDada = product_page_logic.addProductToCart()
                .closePopupOtherCategoryIfYes()
                .cartClick()
                .nextButtonClick()
                .signIn(mail, password)
                .fillFieldTelNumForShipping("200+002")
                .nextBtnClick()
                .clickOnTheDesiredPaymentMethod("BE", "Bank")
                .nextBtnClick()
                .checkPresenceVatPostscriptInTotalPriceOfGoods(productID)
                .getTotalPriceAllDataPage("BE");
        String amountVatPercentage = cartAllData_page_logic.getAmountVatPercentage(productID);
        String orderID = cartAllData_page_logic.nextBtnClick()
                .getOrderNumber();
        Order_aws order_aws = new Order_aws(orderID);
        float totalPriceOrderAWS = order_aws.openOrderInAwsWithLogin()
                .checkVatStatusInOrder("Mit MwSt " + amountVatPercentage + "%")
                .getTotalPriceOrderAWS();
        Assert.assertEquals(totalPriceAllDada, totalPriceOrderAWS);
        order_aws.addProductInOrder("n501", "10")
                .confirmationAddingGoodsToOrder("");
        order_aws.choosesFirmConfirmationStatus("check")
                .reSaveOrder();
        float sellingPraise = order_aws.getSellingPriceOfCertainProduct("N501");
        float totalSellingPrice = order_aws.getPriceOfGoodsByMultiplyingItByQuantity(sellingPraise, 10.00f);
        float newTotalPriceAfterReSave = order_aws.getTotalPriceOrderAWS();
        float priceWithoutRegularProduct = order_aws.subtractsRemovedProductCostFromTotalOrderCost(newTotalPriceAfterReSave, totalSellingPrice);
        Assert.assertEquals(priceWithoutRegularProduct, totalPriceOrderAWS, 0.2f);
        order_aws.checkFirmConfirmationStatus("ДА/auto")
                .checkVatStatusInOrder("Mit MwSt " + amountVatPercentage + "%", "1")
                .checkVatStatusInOrder("Ohne Mwst", "2");
    }

    @AfterMethod
    private void close() {
        closeWebDriver();
    }
}
