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

import static ATD.CommonMethods.openPage;
import static ATD.CommonMethods.password;
import static Common.SetUp.setUpBrowser;
import static com.codeborne.selenide.Selenide.closeWebDriver;
import static com.codeborne.selenide.Selenide.switchTo;

public class QC_2989 {

    private DataBase bd = new DataBase("ATD");
    private Product_page_Logic product_page_logic = new Product_page_Logic();
    private CartAllData_page_Logic cartAllData_page_logic = new CartAllData_page_Logic();
    private String mail = "qc_2989_autotest@mailinator.com";

    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0", false);
    }

    @Test()
    @Flaky
    @Owner(value = "Chelombitko")
    @Description(value = "Create an order for drop + legal entity (split billing)")
    public void testCreationOrderForDropAndLegalEntity() throws SQLException {
        openPage(bd.getFullRouteByRouteAndSubroute("prod", "BE", "main", "product92"));
        String productID = product_page_logic.getProductId();
        float totalPriceAllDada = product_page_logic.addProductToCart()
                .closePopupOtherCategoryIfYes()
                .cartClick()
                .nextButtonClick()
                .signIn(mail, password)
                .fillFieldTelNumForShipping("200+002")
                .fillFieldTelNumForBilling("200+002")
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
        order_aws.reSaveOrder()
                .clickCustomerId();
        switchTo().window(1);
        new Customer_view_aws().checkResponseInBlockLogsCompanyNumbers("success(200)");
    }

    @AfterMethod
    private void close() {
        closeWebDriver();
    }
}