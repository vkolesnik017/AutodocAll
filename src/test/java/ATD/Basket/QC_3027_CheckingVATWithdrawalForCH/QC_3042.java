package ATD.Basket.QC_3027_CheckingVATWithdrawalForCH;

import ATD.Product_page_Logic;
import ATD.Versand_static_page_Logic;
import AWS.CurrencyRatesPage_aws;
import AWS.Delivery_prices_aws;
import AWS.Order_aws;
import AWS.PageVAT_aws;
import Common.DataBase;
import io.qameta.allure.Description;
import io.qameta.allure.Flaky;
import io.qameta.allure.Owner;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.IOException;
import java.sql.SQLException;

import static ATD.CommonMethods.*;
import static AWS.CurrencyRatesPage_aws.currencyRatesPageURL;
import static AWS.Delivery_prices_aws.delivery_prices_aws;
import static Common.File.assertThatPdfContainsText;
import static Common.SetUp.setUpBrowser;
import static com.codeborne.selenide.Selenide.closeWebDriver;

public class QC_3042 {

    private String mail = "QC_3042_autotest@mailinator.com";
    private String mailDE = "QC_3042_autotestDE@mailinator.com";
    private String illiquidProduct = "VKJA 5561";
    private String productWithDeposit = "24.3481-8515.5";
    private String heavyLoad = "6504-03-2509591P";

    private Product_page_Logic product_page_logic = new Product_page_Logic();
    private DataBase dB = new DataBase("ATD");

    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "87.0", true);
    }

    //TODO тест не реализован полностью из за дефекта BSK-1791

    @Test()
    @Flaky
    @Owner(value = "Chelombitko")
    @Description(value = "Test checking the VAT percentage when changing the country to CH of delivery in AWS")
    public void testCheckingVAT_percentageWhenChangingDeliveryInCH() throws SQLException, IOException {
        String vatForCH = new PageVAT_aws().getVatForCH();
        openPage(dB.getFullRouteByRouteAndSubroute("prod", "CH", "main", "staticVersand"));
        String shop = getCurrentShopFromJSVarInHTML();
        float deliveryPrice = new Versand_static_page_Logic().getDeliveryPrice(shop, "CH", "CH");
        openPage(dB.getFullRouteByRouteAndSubroute("prod", shop, "main", "product32"));
        String orderNumber = product_page_logic.addProductToCart()
                .closePopupOtherCategoryIfYes()
                .cartClick()
                .nextButtonClick()
                .signIn(mail, password)
                .chooseDeliveryCountryForShipping("DE")
                .fillFieldTelNumForShipping("200+002")
                .nextBtnClick()
                .clickOnTheDesiredPaymentMethod(shop, "Bank")
                .nextBtnClick()
                .nextBtnClick()
                .getOrderNumber();

        Order_aws order_aws = new Order_aws(orderNumber);

        order_aws.openOrderInAwsWithoutLogin()
                .choosesDeliveryCountry("Switzerland")
                .reSaveOrder()
                .checkVatStatusInOrder("Mit MwSt " + vatForCH + "%")

                .addProductInOrder(illiquidProduct, "1")
                .confirmationAddingGoodsToOrder("8")

                .addProductInOrder(productWithDeposit, "1")
                .confirmationAddingGoodsToOrder("")

                .addProductInOrder(heavyLoad, "1")
                .confirmationAddingGoodsToOrder("");

        float depositSum = order_aws.reSaveOrder()
                .getPfandSumInAddedProductTabel(productWithDeposit);
        order_aws.checkTextInLabelDanger("VKJA 5561", "Неликвид")
                .checkOrderHasExpectedPfandPrice(depositSum)
                .compereActualDeliveryCostWithExpected(deliveryPrice);
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
        assertThatPdfContainsText("C:/Users/User/Downloads/_" + orderNumber + ".pdf", "MwSt. " + vatForCH + " %");
        order_aws.clickBtnClosePopUpReturn()
                .clickBtnDeclaration()
                .checkModalWindowDeclarationAndClickPrintBtn();
        assertThatPdfContainsText("C:/Users/User/Downloads/doc.pdf", "MWST. " + vatForCH + " %");
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
    @Description(value = "Test checking the VAT percentage when changing the country to DE of delivery in ABC")
    public void testCheckingVAT_percentageWhenChangingDeliveryInDE(String deliveryShop) throws SQLException, IOException {
        String vatForCH = new PageVAT_aws().getVatForCH();
        openPage(delivery_prices_aws);
        float deliveryCostForRegion = new Delivery_prices_aws().getDeliveryPriceForIslandOrRegion("Büsingen am Hochrhein");
        float deliveryCostForCountry = new Delivery_prices_aws().getDeliveryPriceForCountry("Liechtenstein");
        openPage(currencyRatesPageURL);
        float actualDeliveryCostForRegion = new CurrencyRatesPage_aws().exchangeAmountAtDesiredRate(deliveryCostForRegion, "CHF");
        float actualDeliveryCostForCountry = new CurrencyRatesPage_aws().exchangeAmountAtDesiredRate(deliveryCostForCountry, "CHF");
        openPage(dB.getFullRouteByRouteAndSubroute("prod", "CH", "main", "product32"));
        String orderNumber = product_page_logic.addProductToCart()
                .closePopupOtherCategoryIfYes()
                .cartClick()
                .nextButtonClick()
                .signIn(mailDE, password)
                .chooseDeliveryCountryForShipping("DE")
                .fillFieldTelNumForShipping("200+002")
                .nextBtnClick()
                .clickOnTheDesiredPaymentMethod("CH", "Bank")
                .nextBtnClick()
                .nextBtnClick()
                .getOrderNumber();

        Order_aws order_aws = new Order_aws(orderNumber);

        order_aws.openOrderInAwsWithoutLogin()
                .fillingPostalCodeInBlockDeliveryAddress("78266")
                .choosesDeliveryCountry(deliveryShop)
                .reSaveOrder()
                .checkVatStatusInOrder("Mit MwSt " + vatForCH + "%")

                .addProductInOrder(illiquidProduct, "1")
                .confirmationAddingGoodsToOrder("")

                .addProductInOrder(productWithDeposit, "1")
                .confirmationAddingGoodsToOrder("");

        float depositSum = order_aws.reSaveOrder()
                .getPfandSumInAddedProductTabel(productWithDeposit);
        order_aws.checkTextInLabelDanger("VKJA 5561", "Неликвид")
                .checkOrderHasExpectedPfandPrice(depositSum)
                .checkDeliveryCostForCountryOrRegion(actualDeliveryCostForRegion, actualDeliveryCostForCountry, deliveryShop)
                .checkHeavyLoadsDeliveryPriceOrderAWS("0");

    }

    @AfterMethod
    private void close() {
        closeWebDriver();
    }
}
