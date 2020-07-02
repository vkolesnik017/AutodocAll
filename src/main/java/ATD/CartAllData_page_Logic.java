package ATD;

import io.qameta.allure.Step;
import org.testng.Assert;

import java.sql.SQLException;

import static ATD.CommonMethods.*;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.WebDriverRunner.source;

public class CartAllData_page_Logic extends CartAllData_page {

    @Step("Next buttin clicking. CartAllData_page")
    public Payment_handler_page_Logic nextBtnClick() {
        nextBtn().click();
        return page(Payment_handler_page_Logic.class);
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
            sleep(1000);
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

    @Step(":on CartAllData_page")
    public CartAllData_page_Logic counterIncreaseForPaired(String startValue) {
        new CommonMethods().checkingCounterIncreaseForPaired(startValue, fieldWithQuantityOfProducts(), counterPlusBtn());
        return this;
    }

    @Step(":on CartAllData_page")
    public CartAllData_page_Logic counterDecreaseForPaired(String startValue) {
        new CommonMethods().checkingCounterDecreaseForPaired(startValue, fieldWithQuantityOfProducts(), counterMinusBtn());
        sleep(1000);
        return this;
    }

    @Step("Check free delivery price. CartAllData_page")
    public CartAllData_page_Logic checkFreeDeliveryPriceAllData(String deliveryPrice) {
        freeDeliveryIcon().shouldHave(text(deliveryPrice));
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
        String expectedCurrency = new DataBase().getCurrency(shop);
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
        String expectedCurrency = new DataBase().getCurrency(shop);
        getCurrencyAndVerify(vatPriceInHead(), "vatPriceInHead", shop, expectedCurrency);
        getCurrencyAndVerify(vatPriceInTotalOrder(), "vatPriceInTotalOrder", shop, expectedCurrency);
        return this;
    }

    @Step("Checks for absence Safe Order block for Heavy Loads. CartAllData_page")
    public CartAllData_page_Logic checkAbsenceSafeOrderBlock() {
        safeOrderBlock().shouldNot(visible);
        return this;
    }

    @Step("Checks for presence Safe Order block for Heavy Loads. CartAllData_page")
    public CartAllData_page_Logic checkPresenceSafeOrderBlock() {
        safeOrderBlock().shouldBe(visible);
        return this;
    }

    @Step("Click Safe Order Checkbox. CartAllData_page")
    public CartAllData_page_Logic clickSafeOrderCheckbox() {
        safeOrderCheckbox().click();
        return this;
    }

    @Step("Check presence Safe Order price from order summery block . CartAllData_page")
    public CartAllData_page_Logic checkPresenceSafeOrderPriceFromOrderSummeryBlock() {
        safeOrderCost().shouldBe(visible);
        return this;
    }

    @Step("Check absence Safe Order price from order summery block . CartAllData_page")
    public CartAllData_page_Logic checkAbsenceSafeOrderPriceFromOrderSummeryBlock() {
        safeOrderCost().shouldNotBe(visible);
        return this;
    }

    @Step("Get total price of the CartAllData_page")
    public Float getTotalPriceAllDataPage() {
        String realPrice = totalOrderPrice().getText();
        realPrice = realPrice.substring(0, realPrice.indexOf(" ")).replaceAll(",", ".");
        Float totalPrice = Float.parseFloat(realPrice);
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
        returnToPageCartAddress().click();
        return page(CartAddress_page_Logic.class);
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
}
