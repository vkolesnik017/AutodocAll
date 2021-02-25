package ATD.Basket.QC_2461_OrderWithDropshippingGoods;

import ATD.CartAllData_page_Logic;
import ATD.Product_page_Logic;
import AWS.Order_aws;
import Common.DataBase;
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

public class QC_2584 {

    private DataBase bd = new DataBase("ATD");
    private Product_page_Logic product_page_logic = new Product_page_Logic();
    private CartAllData_page_Logic cartAllData_page_logic = new CartAllData_page_Logic();
    private String mail = "qc_2584_autotest@mailinator.com";

    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0", false);
    }

    @DataProvider(name = "route", parallel = true)
    Object[] dataProviderProducts() throws SQLException {
        return new SetUp("ATD").setUpShopsWithSubroute("prod", "DE,PL", "main", "product71");
    }

    @Test(dataProvider = "route")
    @Flaky
    @Owner(value = "Chelombitko")
    @Description(value = "Checking an order with a drop for DE and PL with non-split billing")
    public void testCheckingOrderWithDrop(String route) throws SQLException {
        openPage(route);
        String shop = getCurrentShopFromJSVarInHTML();
        String nameFirm = bd.getFirmData("firm_atd", shop, "Name");
        String idFirm = bd.getFirmData("firm_atd", shop, "ID");
        String city = bd.getFirmData("firm_atd", shop, "City");
        String idProduct = product_page_logic.getProductId();
        String amountVatPercentage = product_page_logic.addProductToCart()
                .closePopupOtherCategoryIfYes()
                .cartClick()
                .nextButtonClick()
                .signIn(mail, password)
                .fillAllFieldsAndFirmForShipping(shop, "12345", nameFirm, city)
                .fillInCompanyIdFieldForCountryWhereIdNeeded(shop, shop, idFirm)
                .clickOnTheDesiredPaymentMethod(shop, "Bank")
                .nextBtnClick()
                .checkAbsenceVatPostscriptInTotalPriceOfGoods(idProduct)
                .checkFreeDeliveryPriceAllData("0,00")
                .getTotalVatPercentageAmount();
        String orderID = cartAllData_page_logic.nextBtnClick()
                .getOrderNumber();
        Order_aws order_aws = new Order_aws(orderID);
        order_aws.openOrderInAwsWithLogin()
                .checkVatStatusInOrder(amountVatPercentage)
                .reSaveOrder()
                .checkVatStatusInOrder(amountVatPercentage);
    }

    @AfterMethod
    private void close() {
        closeWebDriver();
    }
}
