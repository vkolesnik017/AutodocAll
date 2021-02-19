package ATD.Basket.QC_3027_CheckingVATWithdrawalForCH;

import ATD.*;
import AWS.*;
import Common.DataBase;
import Common.SetUp;
import io.qameta.allure.Description;
import io.qameta.allure.Flaky;
import io.qameta.allure.Owner;
import org.testng.annotations.*;

import java.io.IOException;
import java.sql.SQLException;

import static ATD.CommonMethods.*;
import static AWS.CurrencyRatesPage_aws.currencyRatesPageURL;
import static AWS.Delivery_prices_aws.delivery_prices_aws;
import static AWS.ProfilerPage_aws.profilerPage_aws;
import static Common.CommonMethods.convertStringToFloat;
import static Common.File.assertThatPdfContainsText;
import static Common.File.renameDownloadFile;
import static Common.SetUp.setUpBrowser;
import static com.codeborne.selenide.Selenide.closeWebDriver;


public class QC_3036 {

    private String mail = "QC_3036_autotest@mailinator.com";
    private String mailDE = "QC_3036_autotestDE@mailinator.com";
    private Product_page_Logic product_page_logic = new Product_page_Logic();
    private CartAllData_page_Logic cartAllData_page_logic = new CartAllData_page_Logic();
    private Cart_page_Logic cart_page_logic = new Cart_page_Logic();
    private DataBase dB = new DataBase("ATD");


    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "87.0", true);
    }

    @DataProvider(name = "route", parallel = false)
    Object[] dataProviderProducts() throws SQLException {
        return new SetUp("ATD").setUpShopsWithSubroute("prod", "CH,IT,FR,AT", "main", "staticVersand");
    }

    @Test(dataProvider = "route", enabled = false) // Требуется обновление логики, ожидаю апдейта по тикету
    @Flaky
    @Owner(value = "Chelombitko")
    @Description(value = "Test checks VAT percentage for CH")
    public void testCheckVATPercentageForCH(String route) throws SQLException, IOException {
        String vatForCH = new PageVAT_aws().getVatForCH();
        openPage(route);
        String shop = getCurrentShopFromJSVarInHTML();
        float deliveryPrice = new Versand_static_page_Logic().getDeliveryPrice(shop, "CH", "CH");
        openPage(dB.getFullRouteByRouteAndSubroute("prod", shop, "main", "product54"));
        String artIdOFNotLiquid = product_page_logic.addProductToCart()
                .closePopupOtherCategoryIfYes()
                .getArticleNumber();

        openPage(dB.getFullRouteByRouteAndSubroute("prod", shop, "main", "product55"));
        String idOfCollateral = product_page_logic.addProductToCart()
                .closePopupOtherCategoryIfYes()
                .getProductId();

        openPage(dB.getFullRouteByRouteAndSubroute("prod", shop, "main", "product56"));
        product_page_logic.addProductToCart()
                .closePopupOtherCategoryIfYes();

        String deposit = product_page_logic.cartClick()
                .nextButtonClick()
                .signIn(mail, password)
                .chooseDeliveryCountryForShipping("CH")
                .fillFieldTelNumForShipping("200+002")
                .fillFieldFirmNameForShipping("autotest")
                .fillInCompanyIdFieldForCountryWhereIdNeeded(shop, shop, "autotest")
                .clickBtnReturnTheCartPage()
                .getDepositPriceInProductBlock(idOfCollateral);
        float heavyLoadsDeliveryCost = cart_page_logic.checkPresenceDepositInSummeryBlock(deposit)
                .checkTextContainingVatPercentage(vatForCH)
                .clickBtnNextAndTransitionOnAddressPage()
                .nextBtnClick()
                .clickOnTheDesiredPaymentMethod(shop, "Bank")
                .nextBtnClick()
                .checkPresenceDepositInProductBlock(idOfCollateral)
                .checkTextContainingVatPercentage(vatForCH)
                .checkPresenceDepositInSummeryBlock(deposit)
                .checkRegularDeliveryPrice(deliveryPrice)
                .checkPresenceHeavyLoadsDeliveryPriceAllDataPage()
                .getHeavyLoadsDeliveryPrice();
        String orderNumber = cartAllData_page_logic.nextBtnClick()
                .getOrderNumber();

        Order_aws order_aws = new Order_aws(orderNumber);
        String artID = order_aws.openOrderInAwsWithoutLogin()
                .checkVatStatusInOrder("Mit MwSt " + vatForCH + "%")
                .compereActualDeliveryCostWithExpected(deliveryPrice)
                .compereDeliveryCostOfHeavyLoadsWithCostOnSite(heavyLoadsDeliveryCost)
                .checkOrderHasExpectedPfandPrice(convertStringToFloat(deposit))
                .checkTextInLabelDanger(artIdOFNotLiquid, "Неликвид")
                .getArticleId();

        order_aws.reSaveOrder()

                .checkVatStatusInOrder("Mit MwSt " + vatForCH + "%")
                .compereActualDeliveryCostWithExpected(deliveryPrice)
                .compereDeliveryCostOfHeavyLoadsWithCostOnSite(heavyLoadsDeliveryCost)
                .checkOrderHasExpectedPfandPrice(convertStringToFloat(deposit))
                .checkTextInLabelDanger(artIdOFNotLiquid, "Неликвид")

                .openPopUpAccountsAndCheckVat(vatForCH)
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
        renameDownloadFile("C:/Users/User/Downloads/doc.pdf", "C:/Users/User/Downloads/doc" + orderNumber + ".pdf");
        assertThatPdfContainsText("C:/Users/User/Downloads/doc" + orderNumber + ".pdf", "MWST. " + vatForCH + " %");

        openPage(profilerPage_aws);
        new ProfilerPage_aws().fillingFieldsOrderIdAndArticleId(orderNumber, artID)
                .checkVatInTazFormula(new ProfilerPage_aws().taxFormula(), "1.077");
    }


    @DataProvider(name = "deliveryShop", parallel = false)
    Object[] blockingWords() {
        return new Object[][]{
                {"DE"},
                {"LI"}
        };
    }

    @Test(dataProvider = "deliveryShop", enabled = false) // Требуется обновление логики, ожидаю апдейта по тикету
    @Flaky
    @Owner(value = "Chelombitko")
    @Description(value = "Test checks VAT percentage for CH for delivery in LI and DE with index 78266")
    public void testCheckVATPercentageForCH_ForDeliveryInShopDE_LI(String deliveryShop) throws SQLException, IOException {
        String vatForCH = new PageVAT_aws().getVatForCH();
        openPage(delivery_prices_aws);
        float deliveryCostForRegion = new Delivery_prices_aws().getDeliveryPriceForIslandOrRegion("Büsingen am Hochrhein");
        float deliveryCostForCountry = new Delivery_prices_aws().getDeliveryPriceForCountry("Liechtenstein");
        openPage(currencyRatesPageURL);
        float actualDeliveryCostForRegion = new CurrencyRatesPage_aws().exchangeAmountAtDesiredRate(deliveryCostForRegion, "CHF");
        float actualDeliveryCostForCountry = new CurrencyRatesPage_aws().exchangeAmountAtDesiredRate(deliveryCostForCountry, "CHF");
        openPage(dB.getFullRouteByRouteAndSubroute("prod", "CH", "main", "product54"));
        String artIdOFNotLiquid = product_page_logic.addProductToCart()
                .closePopupOtherCategoryIfYes()
                .getArticleNumber();

        openPage(dB.getFullRouteByRouteAndSubroute("prod", "CH", "main", "product55"));
        String idOfCollateral = product_page_logic.addProductToCart()
                .closePopupOtherCategoryIfYes()
                .getProductId();

        openPage(dB.getFullRouteByRouteAndSubroute("prod", "CH", "main", "product56"));
        product_page_logic.addProductToCart()
                .closePopupOtherCategoryIfYes();

        String deposit = product_page_logic.cartClick()
                .nextButtonClick()
                .signIn(mailDE, password)
                .fillingPostalCodeFieldJSForShipping("78266")
                .chooseDeliveryCountryForShipping(deliveryShop)
                .fillFieldTelNumForShipping("200+002")
                .nextBtnClick()
                .clickBtnReturnTheCartPage()
                .checkPresencePopUpDeliveryLimit()
                .closePopUpDeliveryLimitCartPage()
                .getDepositPriceInProductBlock(idOfCollateral);
        String orderNumber = cart_page_logic.checkPresenceDepositInSummeryBlock(deposit)
                .checkTextContainingVatPercentage(vatForCH)
                .clickBtnNextAndTransitionOnAddressPage()
                .nextBtnClick()
                .clickOnTheDesiredPaymentMethod("CH", "Bank")
                .nextBtnClick()
                .checkPresenceDepositInProductBlock(idOfCollateral)
                .checkTextContainingVatPercentage(vatForCH)
                .checkDeliveryCostForCountryOrRegion(actualDeliveryCostForRegion, actualDeliveryCostForCountry, deliveryShop)
                .checkAbsenceHeavyLoadsDeliveryPrice()
                .nextBtnClick()
                .getOrderNumber();

        Order_aws order_aws = new Order_aws(orderNumber);
        String artID = order_aws.openOrderInAwsWithoutLogin()
                .checkVatStatusInOrder("Mit MwSt " + vatForCH + "%")
                .checkDeliveryCostForCountryOrRegion(actualDeliveryCostForRegion, actualDeliveryCostForCountry, deliveryShop)
                .checkHeavyLoadsDeliveryPriceOrderAWS("0")
                .checkOrderHasExpectedPfandPrice(convertStringToFloat(deposit))
                .checkTextInLabelDanger(artIdOFNotLiquid, "Неликвид")
                .getArticleId();

        order_aws.reSaveOrder()

                .checkVatStatusInOrder("Mit MwSt " + vatForCH + "%")
                .checkDeliveryCostForCountryOrRegion(actualDeliveryCostForRegion, actualDeliveryCostForCountry, deliveryShop)
                .checkHeavyLoadsDeliveryPriceOrderAWS("0")
                .checkOrderHasExpectedPfandPrice(convertStringToFloat(deposit))
                .checkTextInLabelDanger(artIdOFNotLiquid, "Неликвид")

                .openPopUpAccountsAndCheckVat(vatForCH)
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
        renameDownloadFile("C:/Users/User/Downloads/doc.pdf", "C:/Users/User/Downloads/doc" + orderNumber + ".pdf");
        assertThatPdfContainsText("C:/Users/User/Downloads/doc" + orderNumber + ".pdf", "MWST. " + vatForCH + " %");

        openPage(profilerPage_aws);
        new ProfilerPage_aws().fillingFieldsOrderIdAndArticleId(orderNumber, artID)
                .checkVatInTazFormula(new ProfilerPage_aws().taxFormula(), "1.077");
    }

    @AfterMethod
    private void close() {
        closeWebDriver();
    }
}


