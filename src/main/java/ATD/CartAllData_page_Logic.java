package ATD;

import Common.DataBase;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.testng.Assert;

import java.sql.SQLException;

import static ATD.CommonMethods.*;
import static Common.CommonMethods.checkingContainsUrl;
import static Common.CommonMethods.roundOfTheCost;
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

    @Step("Click logo title. CartAllData_page")
    public Main_page clickLogo() {
        titleLogo().shouldBe(visible).click();
        return page(Main_page.class);
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
        float totalPrice = getPriceFromElement(totalPrice());
        while (!freeDeliveryIcon().isDisplayed() && totalPrice < deliveryLimit) {
            String beforeClickPrice = totalPrice().text();
            sleep(1000);
            counterPlusBtn().click();
            sleep(3000);
            totalPrice().shouldHave(not(text(beforeClickPrice)));
            totalPrice = getPriceFromElement(totalPrice());
            if (totalPrice < deliveryLimit) {
                freeDeliveryIcon().shouldBe(not(visible));
            } else if (totalPrice > deliveryLimit) {
                freeDeliveryIcon().shouldBe(visible);
                break;
            }
        }
        return this;
    }

    @Step("Checks free delivery absence in case raising the delivery limit. CartAllData_page")
    public CartAllData_page_Logic checkNoFreeDelivery (float deliveryLimit) {
        float totalPrice = getPriceFromElement(totalPrice());
        if (totalPrice < deliveryLimit) {
            while (totalPrice < deliveryLimit) {
                String beforeClickPrice = totalPrice().text();
                sleep(1000);
                counterPlusBtn().click();
                sleep(3000);
                totalPrice().shouldHave(not(text(beforeClickPrice)));
                totalPrice = getPriceFromElement(totalPrice());
                if (totalPrice > deliveryLimit) {
                    checkAbsenceFreeDeliveryPriceCartAllDataPage();
                    break;
                }
            }
        } else
            checkAbsenceFreeDeliveryPriceCartAllDataPage();
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

    @Step("Checks regular delivery price {regularDeliveryPrice}. CartAllData_page")
        public CartAllData_page_Logic checkRegularDeliveryPrice(String regularDeliveryPrice) {
            deliveryPrice().shouldHave(text(regularDeliveryPrice));
            return this;
    }

    @Step("Rounds the receiving parameter {regularDeliveryPrice} up and down and checks the price of regular delivery. CartAllData_page")
    public CartAllData_page_Logic checkRegularDeliveryPrice(float regularDeliveryPrice) {
        Float deliveryPrice = getRegularDeliveryPrice();
        float res = roundOfTheCost(regularDeliveryPrice, deliveryPrice);
        deliveryPrice().shouldHave(text(String.valueOf(res).replaceAll("\\.", ",")));
        return this;
    }

    @Step("Checks delivery costs for a costForCountry {deliveryCostForCountry} or costForRegion {deliveryCostForRegion}. CartAllData_page")
    public CartAllData_page_Logic checkDeliveryCostForCountryOrRegion(float deliveryCostForRegion, float deliveryCostForCountry, String shop) {
        if (shop.equals("DE")) {
            checkRegularDeliveryPrice(deliveryCostForRegion);
        }
        if (shop.equals("LI")) {
            checkRegularDeliveryPrice(deliveryCostForCountry);
        }
        return this;
    }

    @Step("Checks Heavy loads delivery price. CartAllData_page")
    public CartAllData_page_Logic checkHeavyLoadsDeliveryPriceAllData(String heavyLoadsDeliveryPrice) {
        heavyLoadsDeliveryPrice().shouldHave(text(heavyLoadsDeliveryPrice));
        return this;
    }

    @Step("Check for presence Heavy loads delivery price. CartAllData_page")
    public CartAllData_page_Logic checkPresenceHeavyLoadsDeliveryPriceAllDataPage() {
        heavyLoadsDeliveryPrice().shouldBe(visible);
        return this;
    }

    @Step("Checks for absence Heavy loads delivery price. CartAllData_page")
    public CartAllData_page_Logic checkAbsenceHeavyLoadsDeliveryPrice() {
        heavyLoadsDeliveryPrice().shouldNotBe(visible);
        return this;
    }

    @Step("Get regular delivery price. CartAllData_page")
    public float getRegularDeliveryPrice() {
        String deliveryPrice = deliveryPrice().getText();
        return Float.parseFloat(deliveryPrice.substring(0, deliveryPrice.indexOf(" ")).replaceAll(",", "."));
    }

    @Step("Get total delivery price and safe order. CartAllData_page")
    public float getTotalDeliveryPriceAndSafeOrder(float deliveryPrice, float safeOrderPrice) {
        return deliveryPrice + safeOrderPrice;
    }

    @Step("Checks for the absence of VAT percentage. CartAllData_page")
    public CartAllData_page_Logic checkAbsenceOfVatPostscript() {
        percentageOfVat().shouldNotBe(visible);
        return this;
    }

    @Step("Checks for the presence of VAT percentage. CartAllData_page")
    public CartAllData_page_Logic checkPresenceOfVatPostscript() {
        percentageOfVat().shouldBe(visible);
        return this;
    }

    @Step("Checks for text containing VAT percentage. CartAllData_page")
    public CartAllData_page_Logic checkTextContainingVatPercentage(String textWithPercentageOfVAT) {
        percentageOfVat().shouldHave(text(textWithPercentageOfVAT));
        return this;
    }

    @Step("Get total VAT percentage amount. CartAllData_page")
    public String getTotalVatPercentageAmount() {
        return percentageOfVat().getText().replaceAll("[^0-9]","");
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
        getCurrencyAndVerify(totalPrice(), "totalProductPrice", shop, expectedCurrency);
        getCurrencyAndVerify(priceOfAllProducts(), "priceOfAllProducts", shop, expectedCurrency);
        getCurrencyAndVerify(deliveryPrice(), "deliveryPrice", shop, expectedCurrency);
        getCurrencyAndVerify(totalOrderPrice(), "totalOrderPrice", shop, expectedCurrency);
        //checks currency for safe order price
        if (priceOfSafeOrder().isDisplayed()) {
            getCurrencyAndVerify(priceOfSafeOrder(), "priceSafeOrder", shop, expectedCurrency);
        }
        return this;
    }

    @Step("Checks currency on all data page for Tyres. CartAllData_page")
    public CartAllData_page_Logic checkCurrencyOnAllDataPageForTyres(String shop) throws SQLException {
        String expectedCurrency = new DataBase("ATD").getCurrency(shop);
        getCurrencyAndVerify(totalOrderPriceInHead(), "totalOrderPriceInHead", shop, expectedCurrency);
        getCurrencyAndVerify(productPrice(), "productPrice", shop, expectedCurrency);
        getCurrencyAndVerify(totalPrice(), "totalProductPrice", shop, expectedCurrency);
        getCurrencyAndVerify(priceOfAllProducts(), "priceOfAllProducts", shop, expectedCurrency);
        getCurrencyAndVerify(freeDeliveryIconForTyres(), "deliveryPrice", shop, expectedCurrency);
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

    @Step("Checks sum safe order. CartAllData_page")
    public CartAllData_page_Logic checkSumSO(String safeOrderSum) {
        priceOfSafeOrder().waitUntil(visible, 5000);
        String sumInBlockSO = priceOfSafeOrder().getText().replaceAll("[^0-9,]", "").replaceAll(",",".");
        safeOrderPriceFromOrderSummaryBlock().waitUntil(visible, 5000);
        String sumSO_InSummeryOrder = safeOrderPriceFromOrderSummaryBlock().getText().replaceAll("[^0-9,]", "").replaceAll(",",".");
        Assert.assertEquals(safeOrderSum, sumInBlockSO);
        Assert.assertEquals(safeOrderSum, sumSO_InSummeryOrder);
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
    public CartAllData_page_Logic checkPresenceSOPriceFromOrderSummeryBlock() {
        if (!safeOrderCostFromHeavyLoadsProduct().isDisplayed()) {
            safeOrderPriceFromOrderSummaryBlock().shouldBe(visible);
        }
        return this;
    }

    @Step("Check absence Safe Order price from order summery block. CartAllData_page")
    public CartAllData_page_Logic checkAbsenceSafeOrderPriceFromOrderSummeryBlock() {
        safeOrderCostFromHeavyLoadsProduct().shouldNotBe(visible);
        return this;
    }

    @Step("Get safe order price. CartAllData_page")
    public float getSafeOrderPrice() {
        safeOrderPriceFromOrderSummaryBlock().shouldBe(visible);
        String safeOrderPrice = safeOrderPriceFromOrderSummaryBlock().getText();
        return Float.parseFloat(safeOrderPrice.substring(0, safeOrderPrice.indexOf(" ")).replaceAll(",", "."));
    }

    @Step("Get safe order price from SO block. CartAllData_page")
    public float getSafeOrderPriceFromSOBlock() {
        safeOrderBlock().shouldBe(visible);
        String safeOrderPrice = priceOfSafeOrder().getText();
        return Float.parseFloat(safeOrderPrice.substring(0, safeOrderPrice.indexOf(" ")).replaceAll(",", "."));
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
        float res = roundOfTheCost(totalPriceIncludedSO, totalPrice);
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
        float totalPrice = getTotalPriceAllDataPage(shop);
        float res = roundOfTheCost(totalPriceIncludedSO, totalPrice);
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
            realPrice = totalOrderPrice().getText().replace("?? ", "").replace(vat, "");
        } else {
            realPrice = totalOrderPrice().getText().replace("?? ", "");
        }
        realPrice = realPrice.replaceAll(",", ".");
        return Float.parseFloat(realPrice);
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
        String regularProductPrice = productPrice().shouldBe(visible).getText().replaceAll("[^0-9,]", "");
        regularProductPrice = regularProductPrice.replaceAll(",", ".");
        return Float.parseFloat(regularProductPrice);
    }

    @Step("Get total product price. CartAllData_page")
    public Float getTotalProductPrice(String idProduct) {
        String productPrice = totalProductPrice(idProduct).shouldBe(visible).getText().replaceAll("[^0-9,]", "").replaceAll(",", ".");
        return Float.parseFloat(productPrice);
    }

    @Step("Checks the total price for a specific quantity {quantity} of goods {priceOneUnitOfGoods}. CartAllData_page")
    public CartAllData_page_Logic checkTotalPriceForSpecificQuantityOfGoods(float priceOneUnitOfGoods, int quantity, String idProduct) {
        float totalProductPrice = getTotalProductPrice(idProduct);
        float sum = priceOneUnitOfGoods * quantity;
        Assert.assertEquals(totalProductPrice, sum, 0.01f);
        return this;
    }

    @Step("Check presence of VAT in the total price of product{idProduct}. CartAllData_page")
    public CartAllData_page_Logic checkPresenceVatPostscriptInTotalPriceOfGoods(String idProduct) {
        vatFromTotalProductPrice(idProduct).shouldBe(visible);
        return this;
    }

    @Step("Check absence of VAT in the total price of product{idProduct}. CartAllData_page")
    public CartAllData_page_Logic checkAbsenceVatPostscriptInTotalPriceOfGoods(String idProduct) {
        vatFromTotalProductPrice(idProduct).shouldNotBe(visible);
        return this;
    }

    @Step("Get the amount of VAT percentage from the product{idProduct}. CartAllData_page")
    public String getAmountVatPercentage(String idProduct) {
        return vatFromTotalProductPrice(idProduct).getText().replaceAll("[^0-9,]", "");
    }

    @Step("Check absence of VAT in the total price of goods{idProduct}. CartAllData_page")
    public CartAllData_page_Logic checkAbsenceVAT_inTotalPriceOfGoods(String idProduct) {
        vatFromTotalProductPrice(idProduct).shouldNot(visible);
        return this;
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

    @Step("Click the button (Adresse ??ndern) in the delivery pop-up. CartAllData_page")
    public CartAddress_page_Logic clickBtnChangeAddressInDeliveryPopupCartAllDataPage() {
        btnChangeAddressInDeliveryPopupAllDataPage().click();
        return page(CartAddress_page_Logic.class);
    }

    //The method is used when delivering goods to FR
    @Step("Checks for absence btn (Adresse ??ndern) in the delivery pop-up.")
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
        returnToCartPage().scrollTo().hover().click();
        checkingContainsUrl("/basket");
        return page(Cart_page_Logic.class);
    }

    @Step("Transition to page Payments page. CartAllData_page")
    public Cart_page_Logic clickBtnReturnToPaymentsPage() {
        sleep(5000);
        returnToCartPaymentsPage().scrollTo().hover().click();
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

    @Step("Checks product price on site matches price on alldata page including VAT. CartAllData_page")
    public CartAllData_page_Logic checkProductPriceOnSitesMatchesPriceOnAllDataPageIncludingVat(Float priceWithVatPerAllDataPage, Float priceProductInAlldata) {
        float res = roundOfTheCost(priceWithVatPerAllDataPage, priceProductInAlldata);
        Assert.assertEquals(res, priceProductInAlldata);
        return this;
    }

    @Step("Checking presence characteristic Zustand from info product. CartAllData_page")
    public CartAllData_page_Logic checkingLackCharacteristicZustandInProduct() {
        characteristicZustandInProduct().shouldNotBe(visible);
        return this;
    }

    @Step("Checks presence deposit price {deposit} in summery block. CartAllData_page")
    public CartAllData_page_Logic checkPresenceDepositInSummeryBlock(String deposit) {
        pfandPriceInTotalPriceBlock().shouldHave(exactText(deposit));
        return this;
    }

    @Step("Checks presence deposit in product block. CartAllData_page")
    public CartAllData_page_Logic checkPresenceDepositInProductBlock(String productID) {
        pfandPriceInProductBlock(productID).shouldBe(visible);
        return this;
    }

    public float getHeavyLoadsDeliveryPrice() {
        return Float.parseFloat(heavyLoadsDeliveryPrice().getText().replaceAll("[^0-9,]", "").replaceAll(",", "."));
    }

    @Step("Checks for the label of the bank payment method. CartAllData_page")
    public CartAllData_page_Logic checksForLabelOfBankPaymentMethod() {
        if ((firstLabelBank().isDisplayed()) || secondLabelBank().isDisplayed() || thirdLabelBank().isDisplayed() || fourthLabelBank().isDisplayed()) {
            System.out.println("Label bank is visible");
        } else {
            Assert.fail("Leib bank not visible");
        }
        return this;
    }
}
