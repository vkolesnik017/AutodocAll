package ATD;

import Common.DataBase;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.testng.Assert;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static ATD.CommonMethods.*;
import static Common.CommonMethods.checkingContainsUrl;
import static Common.CommonMethods.roundOfTheCost;
import static com.codeborne.selenide.CollectionCondition.sizeGreaterThan;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;

public class Cart_page_Logic extends Cart_page {

    @Step("Clicking next button. Cart_page")
    public CartAccount_page_Logic nextButtonClick() {
        nextButton().click();
        return page(CartAccount_page_Logic.class);
    }

    @Step("Clicking next button. Cart_page")
    public CartAddress_page_Logic clickBtnNextAndTransitionOnAddressPage() {
        nextButton().click();
        checkingContainsUrl("/basket/address");
        return page(CartAddress_page_Logic.class);
    }

    @Step(":in Cart_page")
    public Cart_page_Logic counterIncreaseForPaired(String startValue) {
        new CommonMethods().checkingCounterIncreaseForPaired(startValue, fieldWithQuantityOfProducts(), counterPlusBtn());
        return this;
    }

    @Step(":in Cart_page")
    public Cart_page_Logic counterDecreaseForPaired(String startValue) {
        new CommonMethods().checkingCounterDecreaseForPaired(startValue, fieldWithQuantityOfProducts(), counterMinusBtn());
        return this;
    }

    @Step("Click uncover characteristics for first product and get his characteristics. Cart_page")
    public ElementsCollection getCharacteristicsOfProduct() {
        uncoverCharacteristics().click();
        return $$(".info__description>li").shouldHave(sizeGreaterThan(2));
    }

    @Step("Make price for minimum order for CH. Cart_page")
    public Cart_page makePriceForMinimumOrderForCH(String shop) {
        if (shop.equals("CH")) {
            if (!closeDeliveryLimitPopupForCH().isDisplayed()) {
                sleep(2000);
            }
            if (closeDeliveryLimitPopupForCH().isDisplayed()) {
                closeDeliveryLimitPopupForCH().click();
                while (nextBtnIsNotActiveForCH().isDisplayed()) {
                    counterPlusBtn().click();
                    sleep(500);
                }
            }
        }
        return this;
    }

    @Step("Make and check limit price for free delivery. Cart_page")
    public Cart_page makeAndCheckLimitPriceForFreeDelivery(float deliveryLimit) {
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

    @Step("Checks presence free delivery icon. Cart_page")
    public Cart_page_Logic checkPresenceFreeDeliveryIcon() {
        freeDeliveryIcon().waitUntil(visible,10000);
        return this;
    }

    @Step("Checks currency on cart page. Cart_page")
    public Cart_page_Logic checkCurrencyOnCartPage(String shop) throws SQLException {
        makePriceForMinimumOrderForCH(shop);
        String expectedCurrency = new DataBase("ATD").getCurrency(shop);
        getCurrencyAndVerify(totalOrderPriceInHead(), "orderPriceInHead", shop, expectedCurrency);
        getCurrencyAndVerify(priceOfAllProducts(), "priceOfAllProducts", shop, expectedCurrency);
        getCurrencyAndVerify(totalOrderPrice(), "totalOrderPrice", shop, expectedCurrency);
        getCurrencyAndVerify(productPrice(), "productPrice", shop, expectedCurrency);
        getCurrencyAndVerify(totalProductPrice(), "totalProductPrice", shop, expectedCurrency);
        return this;
    }

    @Step("Checks currency on cart page from discount block. Cart_page")
    public Cart_page_Logic checkCurrencyOnCartPageFromDiscountBlock(String shop) throws SQLException {
        if (discountBlock().isDisplayed()) {
            String expectedCurrency = new DataBase("ATD").getCurrency(shop);
            getCurrencyAndVerify(priceWithoutDiscount(), "priceWithoutDiscount", shop, expectedCurrency);
            getCurrencyAndVerify(priceWithDiscount(), "priceWithDiscount", shop, expectedCurrency);
            getCurrencyAndVerify(sunDiscount(), "discount", shop, expectedCurrency);
        }
        return this;
    }

    @Step("Check of id {idOfProduct} added product to basket. Cart_page")
    public Cart_page_Logic checkOfIdAddedProductInBasket(String idOfProduct) {
        idOfAddedProduct().shouldHave(attribute("data-article_id", idOfProduct));
        return this;
    }

    @Step("Checks the presence of pop up delivery limit. Cart_page")
    public Cart_page_Logic checkPresencePopUpDeliveryLimit() {
        popupDeliveryLimitCartPage().shouldBe(visible);
        return this;
    }

    @Step("Close popup delivery limit. Cart_page")
    public Cart_page_Logic closePopUpDeliveryLimitCartPage() {
        closePopupDeliveryLimitCartPage().click();
        sleep(2000);
        return this;
    }

    @Step("Deletes goods from cart when click on the (Artikel entfernen) button in the delivery pop-up. Cart_page")
    public Cart_page_Logic deleteGoodsInDeliveryPopupCartPage() {
        btnDeleteGoodsInDeliveryPopupCartPage().click();
        return this;
    }

    @Step("Click the button (Einkauf fortsetzen) in the delivery pop-up. Cart_page")
    public Cart_page_Logic clickBtnContinueShoppingInDeliveryPopupCartPage() {
        btnContinueShoppingInDeliveryPopupCartPage().click();
        return this;
    }

    @Step("Click the button (Artikel entfernen) in the delivery pop-up. Cart_page")
    public Cart_page_Logic clickBtnDeletedGoodsViaDeliveryPopup() {
        btnDeletedGoodsViaDeliveryPopup().click();
        return this;
    }

    @Step("Click the button (Adresse ändern) in the delivery pop-up. Cart_page")
    public CartAddress_page_Logic clickBtnChangeAddressInDeliveryPopupCartPageCartPage() {
        btnChangeAddressInDeliveryPopupCartPage().click();
        return page(CartAddress_page_Logic.class);
    }

    //The method is used when delivering goods to FR
    @Step("Checks for absence btn (Einkauf fortsetzen) in the delivery pop-up. Cart_page")
    public Cart_page_Logic checkAbsenceBtnContinueShoppingInDeliveryPopupCartPage() {
        popupCountryDeliveryLimitCartPage().shouldBe(visible);
        btnContinueShoppingInDeliveryPopupCartPage().shouldNotBe(visible);
        return this;
    }

    @Step("Checks the absence of good by id {idProducts} in cart page. Cart_page")
    public Cart_page_Logic checkAbsenceGoodInCartPage(String idProducts) {
        productsIDLocator(idProducts).shouldNotBe(visible);
        return this;
    }


    @Step("Checks the presence of good by id {idProducts} in cart page. Cart_page")
    public Cart_page_Logic checkPresenceGoodInCardPage(String idProducts) {
        productsIDLocator(idProducts).shouldBe(visible);
        return this;
    }

    @Step("Delete goods from cart page. Cart_page")
    public Main_page_Logic deleteGoodFromCartPage() {
        deleteGoodsBtn().click();
        confirmationDeleteGoodsBtn().click();
        closeBtnPopupOfEmptyBasket().click();
        return page(Main_page_Logic.class);
    }

    @Step("Delete definitely goods from cart page. Cart_page")
    public Cart_page_Logic deleteDefinitelyGoodsFromCartPage(String idProduct) {
        deleteDefinitelyGoodsBTN(idProduct).click();
        confirmationDeleteGoodsBtn().click();
        return this;
    }

    @Step("Check that the basket is empty. Cart_page")
    public Cart_page_Logic checkEmptyCart() {
        emptyCart().shouldBe(visible);
        return this;
    }

    @Step("Get name title product. Cart_page")
    public String getNameTitleProduct() {
        return titleNameProduct().getText();
    }

    @Step("Checks presence bonus sticker. Cart_page")
    public Cart_page_Logic checkPresenceBonusSticker() {
        bonusSticker().shouldBe(visible);
        return this;
    }

    @Step("Check article number of product. Cart_page")
    public Cart_page_Logic checkArticleNumberOfProduct(String productArticle) {
        articleNumber().shouldHave(text(productArticle));
        return this;
    }

    @Step("Checks absence bonus sticker. Cart_page")
    public Cart_page_Logic checkAbsenceBonusSticker() {
        bonusSticker().shouldNotBe(visible);
        return this;
    }

    @Step("check absence of Quantity characteristic in Product description block. Cart_page")
    public Cart_page_Logic checkAbsenceOfQuantityCharacteristicInProductDescriptionBlock(List<String> idOfOrder) {
        for (int i = 0; i < idOfOrder.size(); i++) {
            btnMoreInfoOfProduct(idOfOrder.get(i)).shouldBe(visible).click();
            collectionMoreInfoBlockInProduct().get(i).shouldBe(visible);
            for (int j = 0; j < characteristicListOfProduct(idOfOrder.get(i)).size(); j++) {
                characteristicListOfProduct(idOfOrder.get(i)).get(j).shouldNotHave(exactText("Menge"));
            }
        }
        return this;
    }

    @Step("Get id product . Cart_page")
    public String getIdAddedProduct() {
        return idAddedProduct().getAttribute("data-article_id");
    }

    @Step("Get value in quantity counter. Cart_page")
    public String getValueQuantityCounter() {
        return valueQuantityCounter().getValue();
    }

    @Step("Checks presence PayPal label. Cart_page")
    public Cart_page_Logic checkPresencePaymentsMethodLabel(SelenideElement locator) {
        locator.shouldBe(visible);
        return this;
    }

    @Step("get id of added products to list. Cart_page")
    public List<String> getIdAddedProductsToList() {
        listOfAddedProductsBlock().shouldBe(visible);
        List<String> idOfAddedProducts = new ArrayList<>();
        for (int i = 1; i < collectionListOfAddedProducts().size(); i++) {
            idOfAddedProducts.add(collectionListOfAddedProducts().get(i).getAttribute("data-article_id"));
        }

        return idOfAddedProducts;
    }

    @Step("Checks for presence Safe Order block for Heavy Loads. Cart_page")
    public Cart_page_Logic checkPresenceSafeOrderBlock() {
        safeOrderBlock().shouldBe(visible);
        return this;
    }

    @Step("Checks for absence Safe Order block for Heavy Loads. Cart_page")
    public Cart_page_Logic checkAbsenceSafeOrderBlock() {
        safeOrderBlock().shouldNotBe(visible);
        return this;
    }

    @Step("Checks that the Safe Order checkbox is not selected. Cart_page")
    public Cart_page_Logic checkThatSafeOrderCheckboxIsNotSelected() {
        safeOrderCheckbox().shouldNotHave(attribute("checked"));
        return this;
    }

    @Step("Click Safe Order Checkbox. Cart_page")
    public Cart_page_Logic clickSafeOrderCheckbox() {
        safeOrderCheckbox().click();
        return this;
    }

    @Step("Checks presence safe order in summery block. Cart_page")
    public Cart_page_Logic checkPresenceSOInSummeryBlock() {
        safeOrderInSummeryBlock().shouldBe(visible);
        return this;
    }

    @Step("Checks absence safe order in summery block. Cart_page")
    public Cart_page_Logic checkAbsenceSOInSummeryBlock() {
        safeOrderInSummeryBlock().shouldNotBe(visible);
        return this;
    }

    @Step("Get safe order price. Cart_page")
    public float getSafeOrderPrice() {
        String soPrice = safeOrderInSummeryBlock().getText();
        return Float.parseFloat(soPrice.replaceAll(",",".").replaceAll(" €",""));
    }

    @Step("Get text in Safe Order block. Cart_page")
    public String getTextInSafeOrderBlock() {
        return String.valueOf(safeOrderBlock().getText());
    }

    @Step("Checks the number of days in the Safe Order block for DE and FR shops. Cart_page")
    public Cart_page_Logic checkNumberOfDaysInSafeOrderBlockForDeAndFrShops(String shop, String numberDays) {
        if (shop.equals("DE") || shop.equals("FR")) {
            String safeOrderText = getTextInSafeOrderBlock();
            Assert.assertTrue(safeOrderText.contains(numberDays));
        } else {
            System.out.println("Shop is not equal to DE and FR");
        }
        return this;
    }

    @Step("added all product to WishList. Cart_page")
    public Cart_page_Logic addedAllProductToWishList() {
        btnAddProductToWishList().shouldBe(visible).click();
        successAddedProductToWishListPopUp().shouldBe(visible);
        btnCloseSuccessAddedProductToWishListPopUp().shouldBe(visible).click();
        successAddedProductToWishListPopUp().shouldNotBe(visible);
        btnAddProductToWishList().shouldHave(text("Zur Wunschliste hinzugefügt"));
        return this;
    }

    @Step("presence label add product to WishList. Cart_page")
    public Cart_page_Logic presenceLabelAddProductToWishList() {
        for (int i = 0; i < labelAddProductToWishList().size(); i++) {
            labelAddProductToWishList().get(i).shouldBe(visible);
        }
        return this;
    }

    @Step("remove all products from basket. Cart_page")
    public Cart_page_Logic removeAllProductsFromBasket() {
        int countOfProduct = 0;
        while (collectionBtnRemoveProduct().get(0).isDisplayed()) {
            countOfProduct = collectionBtnRemoveProduct().size();
            collectionBtnRemoveProduct().get(0).click();
            confirmationDeleteGoodsBtn().click();
            collectionBtnRemoveProduct().shouldHaveSize(countOfProduct - 1);
        }
        closeBtnPopupOfEmptyBasket().shouldBe(visible).click();
        return this;
    }

    @Step("click on WishList label of added product. Cart_page")
    public Cart_page_Logic clickOnWishListLabel() {
        wishListLabelOfProduct().get(0).shouldBe(visible).click();
        return this;
    }

    @Step("presence and close Add product to WishList. Cart_page")
    public Cart_page_Logic presenceAndCloseAddProductToWishList() {
        removeProductFromWishListPopUp().shouldBe(visible);
        btnCloseOfRemoveProductFromWishListPopUp().click();
        removeProductFromWishListPopUp().shouldNotBe(visible);
        return this;
    }


    @Step("presence and remove Added product to WishList. Cart_page")
    public Cart_page_Logic presenceAndRemoveAddedProductToWishList() {
        removeProductFromWishListPopUp().shouldBe(visible);
        btnOkOfRemoveProductFromWishListPopUp().click();
        removeProductFromWishListPopUp().shouldNotBe(visible);
        wishListLabelOfProduct().get(0).shouldNotHave(exactText("Zur Wunschliste hinzugefügt"));
        btnAddProductToWishList().shouldBe(visible).shouldHave(text("Alle Artikel zu meiner Wunschliste hinzufügen"));
        return this;
    }

    @Step("go to main page. Cart_page")
    public Main_page_Logic goToMainPage() {
        mainLogo().shouldBe(visible).click();
        return page(Main_page_Logic.class);
    }

    @Step("Checking the absence of a Zustand characteristic in the product. Cart_page")
    public Cart_page_Logic checkingAbsenceOfZustandCharacteristic() {
        btnMoreInfoProduct().click();
        moreInfoBlockInProduct().shouldBe(visible);
        characteristicZustandInProduct().shouldNotBe(visible);
        return this;
    }

    @Step("Get product price. Cart_page")
    public Float getProductPrice() {
        Float productPrice = null;
        String realPrice = totalProductPrice().getText();
        realPrice = realPrice.replaceAll(" €", "").replaceAll(",", ".");
        productPrice = Float.parseFloat(realPrice);
        return productPrice;
    }

    @Step("Get product price in summery block. Cart_page")
    public Float getProductPriceInSummeryBlock() {
        Float productPrice = null;
            String realPrice = totalProductPriceInSummeryBlock().getText();
            realPrice = realPrice.replaceAll(" €", "").replaceAll(",", ".");
            productPrice = Float.parseFloat(realPrice);
        return productPrice;
    }

    @Step("Get total order price. Cart_page")
    public Float getTotalOrderPrice() {
        Float totalOrderPrice = null;
        String realPrice = totalOrderPrice().getText();
        realPrice = realPrice.substring(0, realPrice.indexOf(" ")).replaceAll(",", ".");
        totalOrderPrice = Float.parseFloat(realPrice);
        return totalOrderPrice;
    }

    @Step("Checks total price included SO. Cart_page")
    public Cart_page_Logic checkTotalPriceIncludedSO() {
        float productPrice = getProductPriceInSummeryBlock();
        String priceSO = priceOfSafeOrder().getText();
        Float totalOrderPrice = getTotalOrderPrice();
        float formatPriseSO = Float.parseFloat(priceSO.substring(0, priceSO.indexOf(" ")).replaceAll(",", "."));
        float totalPriceIncludedSO = productPrice + formatPriseSO;
        float res = roundOfTheCost(totalPriceIncludedSO, totalOrderPrice);
        Assert.assertEquals(res, totalOrderPrice);
        return this;
    }

    @Step("Checks for text containing VAT percentage. Cart_page")
    public Cart_page_Logic checkTextContainingVatPercentage(String textWithPercentageOfVAT) {
        percentageOfVat().shouldHave(text(textWithPercentageOfVAT));
        return this;
    }

    @Step("Check presence of VAT in the total price of product{idProduct}. Cart_page")
    public Cart_page_Logic checkPresenceVatPostscriptInTotalPriceOfGoods(String idProduct) {
        vatFromTotalProductPrice(idProduct).shouldBe(visible);
        return this;
    }

    @Step("Get deposit price in product block {productID}. Cart_page")
    public String getDepositPriceInProductBlock(String productID) {
       return pfandPriceInProductBlock(productID).getText();
    }

    @Step("Checks presence deposit price {deposit} in summery block. Cart_page")
    public Cart_page_Logic checkPresenceDepositInSummeryBlock(String deposit) {
        pfandPriceInTotalPriceBlock().shouldHave(exactText(deposit));
        return this;
    }

    @Step("Checks for the label of the bank payment method. Cart_page")
    public Cart_page_Logic checksForLabelOfBankPaymentMethod() {
        if ((firstLabelBank().isDisplayed()) || secondLabelBank().isDisplayed() || thirdLabelBank().isDisplayed() || fourthLabelBank().isDisplayed()) {
            System.out.println("Label bank is visible");
        } else {
            Assert.fail("Leib bank not visible");
        }
        return this;
    }
}
