package ATD;

import Common.DataBase;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.testng.Assert;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.SQLException;

import static ATD.CommonMethods.getCurrencyAndVerify;
import static ATD.CommonMethods.getPriceFromElement;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.page;
import static com.codeborne.selenide.Selenide.sleep;
import static com.codeborne.selenide.WebDriverRunner.source;

public class CartAllData_page_Logic extends CartAllData_page {

    @Step("Next buttin clicking. CartAllData_page")
    public Payment_handler_page_Logic nextBtnClick() {
        nextBtn().click();
        return page(Payment_handler_page_Logic.class);
    }

    @Step("Next buttin clicking and Wait until preloader disappear. CartAllData_page")
    public Payment_handler_page_Logic nextBtnClick(int sleepTime) throws Exception {
        nextBtn().click();
        waitUntilPreloaderDisappearAndSleep(sleepTime);
        return page(Payment_handler_page_Logic.class);
    }

    @Step("Next buttin clicking. CartAllData_page")
    public CartAllData_page_Logic payPalBtnClick() {
        sleep(5000);
        payPalBtn().click();
        return this;
    }

    @Step("Check tyres are not delivered popup and clicking close popup, after that checking one more popup and after clicking close must redirect us on main page. CartAllData_page")
    public Main_page checkTyresNotDeliveredPopupAndRedirect() {
        tyresAreNotDeliveredToCountryPopup().shouldBe(visible);
        closePopupBtn().click();
        popupOfEmptyBasket().shouldBe(visible);
        closeBtnPopupOfEmptyBasket().click();
        new Main_page().logoInHeader().shouldBe(visible);
        String pageSource = source();
        if (!pageSource.contains("ROUTE_NAME\":\"main\"")) Assert.fail("Wrong page. Must open Main Page");
        return page(Main_page.class);
    }

    @Step("Check removing tyres on alldata withgetTotalPriceAllDataPage other products with delivery to other country. CartAllData_page")
    public CartAllData_page_Logic checkRemovingTyresFromAlldataWithOtherProducts(String productId) {
        tyresAreNotDeliveredToCountryPopup().shouldBe(visible);
        closePopupBtn().click();
        searchProductByID(productId).shouldNotBe(visible);
        return this;
    }

    @Step("Checking popup that appear when delivery impossible and clicking close popup,after that checking one more popup and after clicking close must redirect us on main page. CartAllData_page")
    public Main_page closePopupDeliveryImpossibleAndCheckEmptyCart() {
        popupOfDangerousProduct().shouldBe(appear);
        closePopupBtn().click();
//        areaOutOfPopup(), deleteProductBtnInPopup();
        popupOfEmptyBasket().shouldBe(appear);
        closeBtnPopupOfEmptyBasket().click();
        new Main_page().logoInHeader().shouldBe(appear);
        String pageSource = source();
        if (!pageSource.contains("ROUTE_NAME\":\"main\"")) Assert.fail("Wrong page. Must open Main Page");
        return page(Main_page.class);
    }


    @Step("Make and check limit price for free delivery. CartAllData_page")
    public CartAllData_page_Logic makeAndCheckLimitPriceForFreeDelivery(float deliveryLimit) {
        // An increase in the quantity of products for checking the limit of free delivery
        float totalPrice = getPriceFromElement(totalProductPrice());
        while (!freeDeliveryIcon().isDisplayed() && totalPrice < deliveryLimit) {
            String beforeClickPrice = totalProductPrice().text();
            sleep(3000);
            counterPlusBtn().click();
            totalProductPrice().shouldHave(not(text(beforeClickPrice)));
            totalPrice = getPriceFromElement(totalProductPrice());
            if (totalPrice < deliveryLimit) {
                freeDeliveryIcon().shouldBe(not(visible));
            } else if (totalPrice > deliveryLimit) {
                freeDeliveryIcon().shouldBe(visible);
                break;
            }
        }
        return this;
    }

    @Step(":from CartAllData_page")
    public CartAllData_page_Logic counterIncreaseForPaired(String startValue) {
        new CommonMethods().checkingCounterIncreaseForPaired(startValue, fieldWithQuantityOfProducts(), counterPlusBtn());
        return this;
    }

    @Step(":from CartAllData_page")
    public CartAllData_page_Logic counterIncreaseForAllProducts(int startValue) {
        new CommonMethods().checkingCounterIncrease(startValue, fieldWithQuantityOfProducts(), counterPlusBtn());
        return this;
    }

    @Step(":from CartAllData_page")
    public CartAllData_page_Logic counterDecreaseForPaired(String startValue) {
        new CommonMethods().checkingCounterDecreaseForPaired(startValue, fieldWithQuantityOfProducts(), counterMinusBtn());
        sleep(1000);
        return this;
    }

    @Step("Check free delivery price. CartAllData_page")
    public CartAllData_page_Logic checkFreeDeliveryPriceAllData(String deliveryPrice) {
        freeDeliveryIconForTyres().shouldHave(text(deliveryPrice));
        return this;
    }

    @Step("Check for presence free delivery price. CartAllData_page")
    public CartAllData_page_Logic checkPresenceFreeDeliveryPriceCartAllDataPage() {
        freeDeliveryIcon().shouldBe(visible);
        return this;
    }

    @Step("Check for absence free delivery price. CartAllData_page")
    public CartAllData_page_Logic checkAbsenceFreeDeliveryPriceCartAllDataPage() {
        freeDeliveryIcon().shouldNotBe(visible);
        return this;
    }

    @Step("Checks for presence regular delivery price. CartAllData_page")
    public CartAllData_page_Logic checkPresenceRegularDeliveryPrice() {
        deliveryPrice().shouldBe(visible);
        return this;
    }

    @Step("Checks regular delivery price. CartAllData_page")
    public CartAllData_page_Logic checkRegularDeliveryPriceAllData(String regularDeliveryPrice) {
        deliveryPrice().shouldHave(text(regularDeliveryPrice));
        return this;
    }

    @Step("Checks Heavy loads delivery price. CartAllData_page")
    public CartAllData_page_Logic checkHeavyLoadsDeliveryPriceAllData(String heavyLoadsDeliveryPrice) {
        heavyLoadsShippingCost().shouldHave(text(heavyLoadsDeliveryPrice));
        return this;
    }

    @Step("Check for presence Heavy loads delivery price. CartAllData_page")
    public CartAllData_page_Logic checkPresenceHeavyLoadsDeliveryPriceAllDataPage() {
        heavyLoadsShippingCost().shouldBe(visible);
        return this;
    }

    @Step("Checks for absence Heavy loads delivery price. CartAllData_page")
    public CartAllData_page_Logic checkAbsenceHeavyLoadsDeliveryPrice() {
        heavyLoadsShippingCost().shouldNotBe(visible);
        return this;
    }

    @Step("Checks for the absence of VAT percentage. CartAllData_page")
    public CartAllData_page_Logic checkAbsenceOfVatPercentage() {
        percentageOfVat().shouldNotBe(visible);
        return this;
    }

    @Step("Checks for text containing VAT percentage. CartAllData_page")
    public CartAllData_page_Logic checkTextContainingVatPercentage(String textWithPercentageOfVAT) {
        percentageOfVat().shouldHave(text(textWithPercentageOfVAT));
        return this;
    }

    @Step("Check text {textWithAddressInfo} in delivery address info block. CartAllData_page")
    public CartAllData_page_Logic checkTextInDeliveryAddressInfoBlock(String textWithAddressInfo) {
        deliveryAddressInfo().shouldHave(text(textWithAddressInfo));
        return this;
    }

    @Step("Check text {textWithAddressInfo} in payers address info block. CartAllData_page")
    public CartAllData_page_Logic checkTextInPayersAddressInfoBlock(String textWithAddressInfo) {
        payersAddressInfo().shouldHave(text(textWithAddressInfo));
        return this;
    }


    @Step("Checks currency on all data page. CartAllData_page")
    public CartAllData_page_Logic checkCurrencyOnAllDataPage(String shop) throws SQLException {
        String expectedCurrency = new DataBase("ATD").getCurrency(shop);
        getCurrencyAndVerify(totalOrderPriceInHead(), "totalOrderPriceInHead", shop, expectedCurrency);
        getCurrencyAndVerify(productPrice(), "productPrice", shop, expectedCurrency);
        getCurrencyAndVerify(totalProductPrice(), "totalProductPrice", shop, expectedCurrency);
        getCurrencyAndVerify(priceOfAllProducts(), "priceOfAllProducts", shop, expectedCurrency);
        getCurrencyAndVerify(deliveryPrice(), "deliveryPrice", shop, expectedCurrency);
        getCurrencyAndVerify(totalOrderPrice(), "totalOrderPrice", shop, expectedCurrency);
        //checks currency for safe order price
        if (priceOfSafeOrder().isDisplayed()) {
            getCurrencyAndVerify(priceOfSafeOrder(), "priceSafeOrder", shop, expectedCurrency);
        }
        return this;
    }

    @Step("Check currency for VAT price only for CH. CartAllData_page")
    public CartAllData_page_Logic checkCurrencyForVatPrice(String shop) throws SQLException {
        String expectedCurrency = new DataBase("ATD").getCurrency(shop);
        getCurrencyAndVerify(vatPriceInHead(), "vatPriceInHead", shop, expectedCurrency);
        getCurrencyAndVerify(vatPriceInTotalOrder(), "vatPriceInTotalOrder", shop, expectedCurrency);
        return this;
    }

    @Step("Checks for absence Safe Order block for Heavy Loads. CartAllData_page")
    public CartAllData_page_Logic checkAbsenceSafeOrderBlock() {
        sleep(3000);
        safeOrderBlock().shouldNotBe(visible);
        return this;
    }

    @Step("Checks for presence Safe Order block for Heavy Loads. CartAllData_page")
    public CartAllData_page_Logic checkPresenceSafeOrderBlock() {
        safeOrderBlock().shouldBe(visible);
        return this;
    }

    @Step("Get text in Safe Order block. CartAllData_page")
    public String getTextInSafeOrderBlock() {
        return String.valueOf(safeOrderBlock().getText());
    }

    @Step("Click Safe Order Checkbox. CartAllData_page")
    public CartAllData_page_Logic clickSafeOrderCheckbox() {
        safeOrderCheckbox().click();
        return this;
    }

    @Step("Checks that the Safe Order checkbox is not selected. CartAllData_page")
    public CartAllData_page_Logic checkThatSafeOrderCheckboxIsNotSelected() {
        safeOrderCheckbox().shouldNotHave(attribute("checked"));
        return this;
    }

    @Step("Checks that the Safe Order checkbox is selected. CartAllData_page")
    public CartAllData_page_Logic checkThatSafeOrderCheckboxIsSelected() {
        safeOrderCheckbox().shouldHave(attribute("checked"));
        return this;
    }

    @Step("Check presence Safe Order price from order summery block. CartAllData_page")
    public CartAllData_page_Logic checkPresenceSafeOrderPriceFromOrderSummeryBlock() {
        if (!safeOrderCostFromHeavyLoadsProduct().isDisplayed()) {
            safeOrderFromOrderSummaryBlock().shouldBe(visible);
        }
        return this;
    }

    @Step("Check absence Safe Order price from order summery block. CartAllData_page")
    public CartAllData_page_Logic checkAbsenceSafeOrderPriceFromOrderSummeryBlock() {
        safeOrderCostFromHeavyLoadsProduct().shouldNotBe(visible);
        return this;
    }

    @Step("Add safe order price in order and checks what total price included SO. CartAllData_page")
    public CartAllData_page_Logic addSafeOrderInOrderAndCheckTotalPriceIncludedSO(String shop) {
        float price = getTotalPriceAllDataPage(shop);
        String priceSO = priceOfSafeOrder().getText();
        float realPriseSO = Float.parseFloat(priceSO.substring(0, priceSO.indexOf(" ")).replaceAll(",", "."));
        clickSafeOrderCheckbox();
        sleep(2000);
        Float totalPrice = getTotalPriceAllDataPage(shop);
        float totalPriceIncludedSO = price + realPriseSO;
        BigDecimal result = new BigDecimal(totalPriceIncludedSO);
        BigDecimal formatPriceUp = result.setScale(2, RoundingMode.UP);
        float roundMax = Float.parseFloat(String.valueOf(formatPriceUp));
        BigDecimal formatPriceDOWN = result.setScale(2, RoundingMode.FLOOR);
        float roundMin = Float.parseFloat(String.valueOf((formatPriceDOWN)));
        float res = 0.0f;
        if (totalPrice.equals(roundMax)) {
            res = roundMax;
        }
        if (totalPrice.equals(roundMin)) {
            res = roundMin;
        }
        Assert.assertEquals(res, totalPrice);
        return this;
    }

    @Step("Remove the safe order price in order and checks what total price included SO. CartAllData_page")
    public CartAllData_page_Logic removeSafeOrderInOrderAndCheckTotalPriceIncludedSO(String shop) {
        float price = getTotalPriceAllDataPage(shop);
        String priceSO = priceOfSafeOrder().getText();
        float realPriseSO = Float.parseFloat(priceSO.substring(0, priceSO.indexOf(" ")).replaceAll(",", "."));
        float totalPriceIncludedSO = price - realPriseSO;
        clickSafeOrderCheckbox();
        sleep(2000);
        Float totalPrice = getTotalPriceAllDataPage(shop);
        BigDecimal result = new BigDecimal(totalPriceIncludedSO);
        BigDecimal formatPriceUp = result.setScale(2, RoundingMode.UP);
        float roundMax = Float.parseFloat(String.valueOf(formatPriceUp));
        BigDecimal formatPriceDOWN = result.setScale(2, RoundingMode.FLOOR);
        float roundMin = Float.parseFloat(String.valueOf((formatPriceDOWN)));
        float res = 0.0f;
        if (totalPrice.equals(roundMax)) {
            res = roundMax;
        }
        if (totalPrice.equals(roundMin)) {
            res = roundMin;
        }
        Assert.assertEquals(res, totalPrice);
        return this;
    }

    @Step("Get total price of the CartAllData_page")
    public Float getTotalPriceAllDataPage(String shop) {
        Float totalPrice = null;
        totalOrderPrice().shouldBe(visible);
        if (shop.equals("EN")) {
            totalPrice = getTotalPriceAllDataPageForEnShop();
        } else {
            String realPrice = totalOrderPrice().getText();
            realPrice = realPrice.substring(0, realPrice.indexOf(" ")).replaceAll(",", ".");
            totalPrice = Float.parseFloat(realPrice);
        }
        return totalPrice;
    }

    @Step("Get total price for EN shop. CartAllData_page")
    public Float getTotalPriceAllDataPageForEnShop() {
        String realPrice;
        if (labelVAT().isDisplayed()) {
            String vat = labelVAT().getText();
            realPrice = totalOrderPrice().getText().replace("£ ", "").replace(vat, "");
        } else {
            realPrice = totalOrderPrice().getText().replace("£ ", "");
        }
        realPrice = realPrice.replaceAll(",", ".");
        Float totalPrice = Float.parseFloat(realPrice);
        return totalPrice;
    }


    @Step("Transition to Product page. CartAllData_page")
    public Product_page_Logic transitionToProductPage() {
        imageProduct().click();
        return page(Product_page_Logic.class);
    }


    @Step("Get price including VAT. CartAllData_page")
    public Float getPriceIncludingVat(String vat) {
        Float productPrice = getRegularProductPriceFormAllDataPage();
        float priseWithVat = 0.0f;
        if (vat.equals("20")) {
            priseWithVat = (productPrice * 1.2f); // For shop EN
        }
        if (vat.equals("16")) {
            priseWithVat = (productPrice * 1.16f); // For shop DE
        }
        if (vat.equals("21")) {
            priseWithVat = (productPrice * 1.21f); // For shop BE
        }
        return priseWithVat;
    }


    @Step("Get regular product price. CartAllData_page")
    public Float getRegularProductPriceFormAllDataPage() {
        String regularProductPrice = productPrice().getText().replaceAll("[^0-9,]", "");
        regularProductPrice = regularProductPrice.replaceAll(",", ".");
        Float productPrice = Float.parseFloat(regularProductPrice);
        return productPrice;
    }


    @Step(": on CartAllData_page")
    public CartAllData_page_Logic checkAbsenceGoodInCartPage(String idProduct) {
        new Cart_page_Logic().checkAbsenceGoodInCartPage(idProduct);
        return this;
    }


    @Step(": on CartAllData_page")
    public CartAllData_page_Logic checkPresenceGoodInCardPage(String idProduct) {
        new Cart_page_Logic().checkPresenceGoodInCardPage(idProduct);
        return this;
    }

    @Step("Checks the presence of pop up delivery limit. CartAllData_page")
    public CartAllData_page_Logic checkPresencePopUpDeliveryLimitAllDataPage() {
        popupDeliveryLimitAllDataPage().shouldBe(visible);
        return this;
    }

    @Step("Close popup delivery limit. CartAllData_page")
    public CartAllData_page_Logic closePopUpDeliveryLimitCartAllDataPage() {
        closePopupDeliveryLimitAllDataPage().click();
        return this;
    }

    @Step("Deletes goods from cart when click on the (Artikel entfernen) button in the delivery pop-up. CartAllData_page")
    public CartAllData_page_Logic deleteGoodsInDeliveryPopupCartAllDataPage() {
        btnDeleteGoodsInDeliveryPopupAllDataPage().click();
        return this;
    }

    @Step("Click the button (Adresse ändern) in the delivery pop-up. CartAllData_page")
    public CartAddress_page_Logic clickBtnChangeAddressInDeliveryPopupCartAllDataPage() {
        btnChangeAddressInDeliveryPopupAllDataPage().click();
        return page(CartAddress_page_Logic.class);
    }

    //The method is used when delivering goods to FR
    @Step("Checks for absence btn (Adresse ändern) in the delivery pop-up.")
    public CartAllData_page_Logic checkAbsenceBtnChangeAddressInDeliveryPopup() {
        btnChangeAddressInDeliveryPopupAllDataPage().shouldNotBe(visible);
        return this;
    }

    @Step("Checks the presence of pop up country delivery limit. CartAllData_page")
    public CartAllData_page_Logic checkPresencePopUpCountryDeliveryLimit() {
        popupCountryDeliveryLimitAllDataPage().shouldBe(visible);
        return this;
    }

    @Step("Delete goods {idGoods} from CartAllData_page")
    public CartAllData_page_Logic deleteGoodFromCartAllDataPage(String idGood) {
        deleteGoodFromAllDataPage(idGood).click();
        return this;
    }

    @Step("Delete goods from CartAllData_page")
    public Main_page_Logic deleteGoodFromCartAllDataPage() {
        deleteGoodBtn().click();
        btnConfirmProductDelete().click();
        closeBtnPopupOfEmptyBasket().click();
        return page(Main_page_Logic.class);
    }

    @Step("Click button confirm product delete. CartAllData_page")
    public CartAllData_page_Logic clickBtnConfirmProductDelete() {
        btnConfirmProductDelete().click();
        return this;
    }

    @Step(":on CartAllData_page")
    public CartAllData_page_Logic checkingCounterIncrease(int increaseCount, String idGood, String idGoodFromBtnPlus) {
        new CommonMethods().checkingCounterIncrease(increaseCount, counterValue(idGood), counterPlus(idGoodFromBtnPlus));
        return this;
    }

    @Step(":on CartAllData_page")
    public CartAllData_page_Logic checkingCounterDecrease(int decreaseCount, String idGood, String idGoodFromBtnMinus) {
        new CommonMethods().checkingCounterDecrease(decreaseCount, counterValue(idGood), counterMinus(idGoodFromBtnMinus));
        return this;
    }

    @Step("Transition to page Cart Address. CartAllData_page")
    public CartAddress_page_Logic clickBtnReturnToCartAddressPage() {
        sleep(5000);
        returnToPageCartAddress().click();
        return page(CartAddress_page_Logic.class);
    }

    @Step("Transition to page Cart page. CartAllData_page")
    public Cart_page_Logic clickBtnReturnToCartPage() {
        sleep(5000);
        returnToCartPage().click();
        return page(Cart_page_Logic.class);
    }

    @Step("Check of id {idOfProduct} added product in AllData. CartAllData_page")
    public CartAllData_page_Logic checkOfIdAddedProductInAllData(String idOfProduct) {
        idOfAddedProduct().shouldHave(attribute("data-article_id", idOfProduct));
        return this;
    }

    @Step("Check of absence id {idOfProduct} added product in AllData. CartAllData_page")
    public CartAllData_page_Logic checkOfAbsenceIdAddedProductInAllData(String idOfProduct) {
        idOfAddedProduct().shouldNotHave(attribute("data-article_id", idOfProduct));
        return this;
    }

    @Step(":for CartAllData_page")
    public CartAllData_page_Logic checkPresenceBonusSticker() {
        new Cart_page_Logic().checkPresenceBonusSticker();
        return this;
    }

    @Step(":for CartAllData_page")
    public CartAllData_page_Logic checkAbsenceBonusSticker() {
        new Cart_page_Logic().checkAbsenceBonusSticker();
        return this;
    }

    @Step("Open upper block with summary. CartAllData_page")
    public CartAllData_page_Logic openUpperBlockWithSummary() {
        btnOpenUpperBlockWithSummary().click();
        btnOpenUpperBlockWithSummary().shouldNotBe(visible);
        return this;
    }

    @Step("Checks presence safe order price in upper block with summery. CartAllData_page")
    public CartAllData_page_Logic checkPresenceSafeOrderInUpperBlockWithSummery(String shop) {
        String priceSO = priceOfSafeOrder().getText();
        String realPriseSO = priceSO.substring(0, priceSO.indexOf(" "));
        if (shop.equals("DE") || shop.equals("FR")) {
            clickSafeOrderCheckbox();
        }
        checkThatSafeOrderCheckboxIsSelected();
        openUpperBlockWithSummary();
        safeOrderInUpperBlockWithSummery(realPriseSO).shouldBe(visible);
        return this;
    }

    @Step("Click button apply bonus. CartAllData_page")
    public CartAllData_page_Logic clickBtnApplyBonus() {
        btnApplyBonus().click();
        btnApplyBonus().shouldNotBe(visible);
        return this;
    }

    @Step("Cancel bonus applying. CartAllData_page")
    public CartAllData_page_Logic CancelBonusApplying() {
        bonusCheckboxInOrderSummary().click();
        btnApplyBonus().shouldBe(visible);
        return this;
    }

    @Step("Apply discount {expectedDiscount}. CartAllData_page")
    public CartAllData_page_Logic applyDiscount(String expectedDiscount) {
        openDiscountBlock().click();
        fieldForInputDiscount().shouldBe(visible);
        fieldForInputDiscount().setValue(expectedDiscount);
        btnApplyDiscount().click();
        BtnConfirmApplyDiscount().click();
        return this;
    }

    @Step("Open info of product. CartAllData_page")
    public CartAllData_page_Logic openInfoOfProduct() {
        btnOpenInfoOfProduct().click();
        infoOfProductBlock().shouldBe(visible);
        return this;
    }

    @Step("Checks presence payments method label. CartAllData_page")
    public CartAllData_page_Logic checkPresencePaymentsMethodLabel(SelenideElement locator) {
        locator.shouldBe(visible);
        return this;
    }

    @Step("Wait until preloader disappear. CartAllData_page")
    public CartAllData_page_Logic waitUntilPreloaderDisappearAndSleep(int sleepTime) throws Exception {
        if(preloader().isDisplayed()) {
            preloader().waitUntil(attribute("style", "display: none;"), 20000);
            Thread.sleep(sleepTime);
        }
        return this;
    }

    @Step("compare art number of product. CartAllData_page")
    public CartAllData_page_Logic compareArtNumOfProduct(String mpnNumOfProduct) {
        String artNumOfProduct = artNumOfProduct().shouldBe(visible).getText().replace("Artikelnummer: ", "");
        Assert.assertEquals(mpnNumOfProduct, artNumOfProduct);
        return this;
    }

    @Step("Checks product price on site matches price on alldata page including VAT. Product_page")
    public CartAllData_page_Logic checkProductPriceOnSitesMatchesPriceOnAllDataPageIncludingVat(Float priceWithVatPerAllDataPage, Float priceProductInAlldata) {
        BigDecimal result = new BigDecimal(priceWithVatPerAllDataPage);
        BigDecimal formatPriceUp = result.setScale(2, RoundingMode.UP);
        float roundMax = Float.parseFloat(String.valueOf(formatPriceUp));
        BigDecimal formatPriceDOWN = result.setScale(2, RoundingMode.FLOOR);
        float roundMin = Float.parseFloat(String.valueOf((formatPriceDOWN)));
        float res = 0.0f;
        if (priceProductInAlldata.equals(roundMax)) {
            res = roundMax;
        }
        if (priceProductInAlldata.equals(roundMin)) {
            res = roundMin;
        }
        Assert.assertEquals(res, priceProductInAlldata);
        return this;
    }

    @Step("Checking presence characteristic Zustand from info product. CartAllData_page")
    public CartAllData_page_Logic checkingLackCharacteristicZustandInProduct() {
        characteristicZustandInProduct().shouldNotBe(visible);
        return this;
    }
}
