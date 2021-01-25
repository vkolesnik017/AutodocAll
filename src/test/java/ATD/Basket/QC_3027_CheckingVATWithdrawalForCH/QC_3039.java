package ATD.Basket.QC_3027_CheckingVATWithdrawalForCH;

import AWS.*;
import io.qameta.allure.Description;
import io.qameta.allure.Flaky;
import io.qameta.allure.Owner;
import mailinator.WebMail;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.IOException;

import static ATD.CommonMethods.openPage;
import static AWS.Delivery_prices_aws.delivery_prices_aws;
import static AWS.OrderAdd_page_aws.addOrderPageURL;
import static Common.File.assertThatPdfContainsText;
import static Common.SetUp.setUpBrowser;
import static com.codeborne.selenide.Selenide.*;
import static mailinator.WebMail.passwordForMail;

public class QC_3039 {

    private String mail = "QC_3039_autotest@autodoc.si";
    private String customerID = "21893836";
    private String mailDE = "QC_3039_autotestDE@autodoc.si";
    private String illiquidProduct = "50364";
    private String productWithDeposit = "24.3481-8515.5";
    private String heavyLoad = "6504-03-2509591P";

    private Order_aws order_aws = new Order_aws();

    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "87.0", true);
    }

    @Test()
    @Flaky
    @Owner(value = "Chelombitko")
    @Description(value = "Test checking VAT percentage for CH when creating an order in AWS")
    public void testCheckingVAT_percentageForCH_wenCreatingOrderInAWS() throws IOException {
        String vatForCH = new PageVAT_aws().getVatForCH();
        openPage(delivery_prices_aws);
        float deliveryCost = new Delivery_prices_aws().getDeliveryPriceForCountry("Switzerland");
        openPage(addOrderPageURL);
        String orderID = new OrderAdd_page_aws().fillsInFieldCustomerID(customerID)
                .chooseSkinInSelector("auto-doc.ch (CH)")
                .chooseDeliveryCountry("Switzerland")
                .selectedPaymentMethod("PostFinance")
                .selectedDeliveryMethod("Standardversand")
                .clickSaveOrderBtn()
                .checkVatStatusInOrder("Mit MwSt " + vatForCH + "%")
                .compereActualDeliveryCostWithExpected(deliveryCost)
                .checkHeavyLoadsDeliveryPriceOrderAWS("0")
                .getOrderIdOfOrder();

        executeJavaScript("window.open('about:blank', '-blank')");
        switchTo().window(1);
        new WebMail().openMail(mail, passwordForMail)
                .checkAndOpenLetterWithOrderNumber(orderID)
                .checkTextContainingVatPercentageInEmail("inkl. " + vatForCH + "% MwST")
                .checkRegularDeliveryPriceInEmail(String.valueOf(deliveryCost));
        closeWindow();
        switchTo().window(0);
        order_aws.addProductInOrder(illiquidProduct, "1")
                .confirmationAddingGoodsToOrder("8037880")

                .addProductInOrder(productWithDeposit, "1")
                .confirmationAddingGoodsToOrder("")

                .addProductInOrder(heavyLoad, "1")
                .confirmationAddingGoodsToOrder("");

        float depositSum = order_aws.reSaveOrder()
                .getPfandSumInAddedProductTabel(productWithDeposit);
        order_aws.checkTextInLabelDanger("50364", "Неликвид")
                .checkOrderHasExpectedPfandPrice(depositSum)
                .compereActualDeliveryCostWithExpected(deliveryCost);
        float heavyLoadsSum = order_aws.getHeavyLoads(heavyLoad);
        order_aws.compereDeliveryCostOfHeavyLoadsWithCostOnSite(heavyLoadsSum);

        order_aws.getArticleId();
        order_aws.openPopUpAccountsAndCheckVat(vatForCH)
                .closePopupAccounts()
                .clickReturnButton()
                .chooseReturnType("Возврат")
                .clickCheckBoxProductInPopupReturn()
                .clickCheckBoxDeliveryInPopupReturn()
                .clickPrintButtonInPopupReturn();
        assertThatPdfContainsText("C:/Users/User/Downloads/_" + orderID + ".pdf", "MwSt. " + vatForCH + " %");
        order_aws.clickBtnClosePopUpReturn()
                .clickBtnDeclaration()
                .checkModalWindowDeclarationAndClickPrintBtn();
        assertThatPdfContainsText("C:/Users/User/Downloads/doc.pdf", "MWST. " + vatForCH + " %");
    }

    @AfterMethod
    private void close() {
        closeWebDriver();
    }
}
