package ATD.Basket.QC_2461_OrderWithDropshippingGoods;

import ATD.CartAllData_page_Logic;
import ATD.Product_page_Logic;
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

import static ATD.CommonMethods.*;
import static Common.SetUp.setUpBrowser;
import static com.codeborne.selenide.Selenide.closeWebDriver;
import static com.codeborne.selenide.Selenide.switchTo;

public class QC_2476 {

    private DataBase bd = new DataBase("ATD");
    private Product_page_Logic product_page_logic = new Product_page_Logic();
    private String mail = "QC_2476_autotest@mailinator.com";

    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0", false);
    }

    @Test()
    @Flaky
    @Owner(value = "Chelombitko")
    @Description(value = "Goods from our warehouse + drop, undivided billing")
    public void testGoodsFromOurWarehouseAndDropUndividedBilling() throws SQLException {
        openPage(bd.getFullRouteByRouteAndSubroute("prod", "PT", "main", "product71"));
        String shop = getCurrentShopFromJSVarInHTML();
        String nameFirm = bd.getFirmData("firm_atd", shop, "Name");
        String idFirm = bd.getFirmData("firm_atd", shop, "ID");
        String indexOfCountry = bd.getFirmData("firm_atd", shop, "Index");
        String city = bd.getFirmData("firm_atd", shop, "City");
        float productPrice = product_page_logic.addProductToCart()
                .closePopupOtherCategoryIfYes()
                .getProductPrice();
        String idProductDrop = product_page_logic.getProductId();
        openPage(bd.getFullRouteByRouteAndSubroute("prod", "PT", "main", "product79"));
        String idProductWarehouse = product_page_logic.getProductId();
        String amountVatPercentage = product_page_logic.addProductToCart()
                .closePopupOtherCategoryIfYes()
                .cartClick()
                .nextButtonClick()
                .signIn(mail, password)
                .fillAllFieldsAndFirmForShipping(shop, indexOfCountry, nameFirm, city)
                .fillFieldIdCompanyShipping(idFirm)
                .fillFieldTelNumForShipping("200+002")
                .nextBtnClick()
                .clickOnTheDesiredPaymentMethod(shop, "Bank")
                .nextBtnClick()
                .checkPresenceVAT_inTotalPriceOfGoods(idProductDrop)
                .checkTotalPriceForSpecificQuantityOfGoods(productPrice, 4, idProductDrop)
                .checkAbsenceVAT_inTotalPriceOfGoods(idProductWarehouse)
                .checkAbsenceOfVatPercentage()
                .getAmountVatPercentage(idProductDrop);
        String orderID = new CartAllData_page_Logic().nextBtnClick()
                .getOrderNumber();
        Order_aws order_aws = new Order_aws(orderID);
        order_aws.openOrderInAwsWithLogin()
                .checkVatStatusInOrder(amountVatPercentage, "1")
                .checkVatStatusInOrder("Ohne Mwst", "2")
                .checkFirmConfirmationStatus("ДА/auto")
                .reSaveOrder()
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
