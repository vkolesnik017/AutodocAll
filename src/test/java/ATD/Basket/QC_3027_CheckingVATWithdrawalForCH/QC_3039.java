package ATD.Basket.QC_3027_CheckingVATWithdrawalForCH;

import AWS.*;
import io.qameta.allure.Description;
import io.qameta.allure.Flaky;
import io.qameta.allure.Owner;
import mailinator.WebMail;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.IOException;

import static ATD.CommonMethods.openPage;
import static AWS.CurrencyRatesPage_aws.currencyRatesPageURL;
import static AWS.Delivery_prices_aws.delivery_prices_aws;
import static AWS.OrderAdd_page_aws.addOrderPageURL;
import static Common.File.assertThatPdfContainsText;
import static Common.File.renameDownloadFile;
import static Common.SetUp.setUpBrowser;
import static com.codeborne.selenide.Selenide.*;
import static mailinator.WebMail.passwordForMail;

public class QC_3039 {

    private String mail = "QC_3039_autotest@autodoc.si";
    private String customerID = "21893836";
    private String mailDE_LI = "QC_3039_autotestDE@autodoc.si";
    private String customerID_DE_LI = "21894072";
    private String productWithDeposit = "24.3481-8515.5";
    private String heavyLoad = "6504-03-2509591P";
    private String articleIdIlliquidProduct;
    private Order_aws order_aws = new Order_aws();

    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "87.0", true);
        articleIdIlliquidProduct = new ProductSearch_aws().openProductSearchPageAndLogin().chooseIlliquidProductAndGetArticleId();
        close();
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
        order_aws.addProductInOrder(articleIdIlliquidProduct, "1")
                .confirmationAddingGoodsToOrder("")

                .addProductInOrder(productWithDeposit, "1")
                .confirmationAddingGoodsToOrder("")

                .addProductInOrder(heavyLoad, "1")
                .confirmationAddingGoodsToOrder("");

        float depositSum = order_aws.reSaveOrder()
                .getPfandSumInAddedProductTabel(productWithDeposit);
        order_aws.checkTextInLabelDanger(articleIdIlliquidProduct, "Неликвид")
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
        assertThatPdfContainsText("C:/Users/User/Downloads/_" + orderID + ".pdf", vatForCH);
        order_aws.clickBtnClosePopUpReturn()
                .clickBtnDeclaration()
                .checkModalWindowDeclarationAndClickPrintBtn();
        renameDownloadFile("C:/Users/User/Downloads/doc.pdf", "C:/Users/User/Downloads/doc" + orderID + ".pdf");
        assertThatPdfContainsText("C:/Users/User/Downloads/doc" + orderID + ".pdf", vatForCH);
    }



    @DataProvider(name = "deliveryShop", parallel = false)
    Object[] blockingWords() {
        return new Object[][]{
                {"Liechtenstein"},
                {"Germany"}
        };
    }

    @Test(dataProvider = "deliveryShop")
    @Flaky
    @Owner(value = "Chelombitko")
    @Description(value = "Test checking VAT for CH for delivery in LI and DE with index 78266 when creating an order in AWS")
    public void testCheckingVAT_percentageForCH_ForDeliveryInShopLI_DE(String deliveryShop) throws IOException {
        String vatForCH = new PageVAT_aws().getVatForCH();
        openPage(delivery_prices_aws);
        float deliveryCostForRegion = new Delivery_prices_aws().getDeliveryPriceForIslandOrRegion("Büsingen am Hochrhein");
        float deliveryCostForCountry = new Delivery_prices_aws().getDeliveryPriceForCountry("Liechtenstein");
        openPage(currencyRatesPageURL);
        float actualDeliveryCostForRegion = new CurrencyRatesPage_aws().exchangeAmountAtDesiredRate(deliveryCostForRegion, "CHF");
        float actualDeliveryCostForCountry = new CurrencyRatesPage_aws().exchangeAmountAtDesiredRate(deliveryCostForCountry, "CHF");
        openPage(addOrderPageURL);
        String orderID = new OrderAdd_page_aws().fillsInFieldCustomerID(customerID_DE_LI)
                .chooseSkinInSelector("auto-doc.ch (CH)")
                .fillingPostalCodeInBlockDeliveryAddress("78266")
                .chooseDeliveryCountry(deliveryShop)
                .selectedPaymentMethod("PostFinance")
                .selectedDeliveryMethod("Standardversand")
                .clickSaveOrderBtn()
                .checkVatStatusInOrder("Mit MwSt " + vatForCH + "%")
                .checkDeliveryCostForCountryOrRegion(actualDeliveryCostForRegion, actualDeliveryCostForCountry, deliveryShop)
                .checkHeavyLoadsDeliveryPriceOrderAWS("0")
                .getOrderIdOfOrder();

        executeJavaScript("window.open('about:blank', '-blank')");
        switchTo().window(1);
        new WebMail().openMail(mailDE_LI, passwordForMail)
                .checkAndOpenLetterWithOrderNumber(orderID)
                .checkTextContainingVatPercentageInEmail("inkl. " + vatForCH + "% MwST")
                .checkDeliveryCostForCountryOrRegion(actualDeliveryCostForRegion, actualDeliveryCostForCountry, deliveryShop);
        closeWindow();
        switchTo().window(0);
        order_aws.addProductInOrder(articleIdIlliquidProduct, "1")
                .confirmationAddingGoodsToOrder("")

                .addProductInOrder(productWithDeposit, "1")
                .confirmationAddingGoodsToOrder("")

                .addProductInOrder(heavyLoad, "1")
                .checkPresenceErrorPopupInTopRight()
                .closeModalWindowAddProduct();

        float depositSum = order_aws.reSaveOrder()
                .getPfandSumInAddedProductTabel(productWithDeposit);
        order_aws.checkTextInLabelDanger(articleIdIlliquidProduct, "Неликвид")
                .checkOrderHasExpectedPfandPrice(depositSum)
                .checkDeliveryCostForCountryOrRegion(actualDeliveryCostForRegion, actualDeliveryCostForCountry, deliveryShop);
        order_aws.checkHeavyLoadsDeliveryPriceOrderAWS("0");

        order_aws.getArticleId();
        order_aws.openPopUpAccountsAndCheckVat(vatForCH)
                .closePopupAccounts()
                .clickReturnButton()
                .chooseReturnType("Возврат")
                .clickCheckBoxProductInPopupReturn()
                .clickCheckBoxDeliveryInPopupReturn()
                .clickPrintButtonInPopupReturn();
        assertThatPdfContainsText("C:/Users/User/Downloads/_" + orderID + ".pdf", vatForCH);
        order_aws.clickBtnClosePopUpReturn()
                .clickBtnDeclaration()
                .checkModalWindowDeclarationAndClickPrintBtn();
        renameDownloadFile("C:/Users/User/Downloads/doc.pdf", "C:/Users/User/Downloads/doc" + orderID + ".pdf");
        assertThatPdfContainsText("C:/Users/User/Downloads/doc" + orderID + ".pdf", vatForCH);
    }

    @AfterMethod
    private void close() {
        closeWebDriver();
    }
}
