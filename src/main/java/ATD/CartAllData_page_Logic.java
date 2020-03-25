package ATD;

import io.qameta.allure.Step;
import org.testng.Assert;

import java.sql.SQLException;

import static ATD.CommonMethods.*;
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

    @Step("Checks for the absence of VAT percentage")
    public CartAllData_page_Logic checkAbsenceOfVatPercentage() {
        percentageOfVat().shouldNotBe(visible);
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
    public CartAllData_page_Logic checkAbsenceSafeOrderBlock(){
        safeOrderBlock().shouldNot(visible);
        return this;
    }

    @Step("Checks for presence Safe Order block for Heavy Loads. CartAllData_page")
    public CartAllData_page_Logic checkPresenceSafeOrderBlock(){
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
    public Double getTotalPriceAllDataPage(){
        String realPrice = totalOrderPrice().getText();
        realPrice = realPrice.substring(0, realPrice.indexOf(" ")).replaceAll(",",".");
        Double totalPrice = Double.parseDouble(realPrice);
        return totalPrice;
    }

    @Step(": on CartAllData_page")
    public CartAllData_page_Logic checkAbsenceGoodInCartPage(String idProduct){
        new Cart_page_Logic().checkAbsenceGoodInCartPage(idProduct);
        return this;
    }

    @Step(": on CartAllData_page")
    public CartAllData_page_Logic checkPresenceGoodInCardPage(String idProduct){
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
    public CartAllData_page_Logic deleteGoodsInDeliveryPopupCartAllDataPage(){
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
}
