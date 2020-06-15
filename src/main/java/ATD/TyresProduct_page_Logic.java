package ATD;


import io.qameta.allure.Step;
import org.testng.Assert;

import java.sql.SQLException;

import static ATD.CommonMethods.getPriceFromElement;
import static ATD.CommonMethods.waitingWhileLinkBecomeExpected;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.page;
import static com.codeborne.selenide.Selenide.switchTo;

public class TyresProduct_page_Logic extends TyresProduct_page {

    @Step("Click delivery link and check redirect. TyresProduct_page")
    public TyresProduct_page_Logic clickDeliveryLinkAndCheckRedirect() throws SQLException {
        deliveryLink().click();
        switchTo().window(1);
        waitingWhileLinkBecomeExpected(new DataBase().getFullRouteByRouteAndSubroute("prod", "DE", "main", "staticVersand"));
        return this;
    }

    @Step("Check product price visibility. TyresProduct_page")
    public TyresProduct_page_Logic checkProductPriceVisibility() {
        productPrice().shouldBe(visible);
        return this;
    }

    @Step("Check product price visibility. TyresProduct_page")
    public TyresProduct_page_Logic checkDeliveryLinkVisibility() {
        deliveryLink().shouldBe(visible);
        return this;
    }

    @Step("Check FAQ block visibility. TyresProduct_page")
    public TyresProduct_page_Logic checkFAQblockVisibility() {
        faqBlock().shouldBe(visible);
        return this;
    }

    @Step("Check rating block visibility. TyresProduct_page")
    public TyresProduct_page_Logic checkRatingBlockVisibility() {
        ratingBlock().shouldBe(visible);
        return this;
    }

    @Step("Check payment methods block visibility. TyresProduct_page")
    public TyresProduct_page_Logic checkPaymentMethodsBlockVisibility() {
        paymentMethodsBlock().shouldBe(visible);
        return this;
    }

    @Step("Check delivery methods block visibility. TyresProduct_page")
    public TyresProduct_page_Logic checkDeliveryMethodsBlockVisibility() {
        deliveryMethodsBlock().shouldBe(visible);
        return this;
    }

    @Step("Check add to basket from topseller block on tyres product page. TyresProduct_page")
    public TyresProduct_page_Logic checkAddToBasketFromTopsellerBlock() {
        topsellerBlock().shouldBe(visible);
        addToBasketTopsellerBlockButton().click();
        return this;
    }

    @Step("Check transition to product page from topseller block. TyresProduct_page")
    public TyresProduct_page_Logic checkTransitionToProductPageFromTopsellerBlock() {
        topsellerBlock().shouldBe(visible);
        String urlInTopBlock = productFronTopsellerBlock().attr("href");
        productFronTopsellerBlock().click();
        waitingWhileLinkBecomeExpected(urlInTopBlock);
        return this;
    }

    @Step("Check reifenlabel block visibility. TyresProduct_page")
    public TyresProduct_page_Logic checkReifenlabelBlockVisibility() {
        reifenlabelBlock().shouldBe(visible);
        return this;
    }

    @Step("Check adding tyres to basket. TyresProduct_page")
    public Cart_page_Logic checkAddingTyresToBasket() {
        basketBlock().hover();
        Float priceOfOneTyre = getPriceFromElement(priceOnProductPage());
        Float priceOfTyresInBasket = getPriceFromElement(priceInBasketPopup());
        Float numberOftyresInFloat = Float.parseFloat(numberOfProductsOnProductPage().attr("value").trim());
        Float resutlPrice = (priceOfOneTyre * numberOftyresInFloat);
        Assert.assertEquals(resutlPrice, priceOfTyresInBasket);
        numberOfProductsInBasketPopup().shouldHave(text(numberOfProductsOnProductPage().text()));
        basketBlock().click();
        return page(Cart_page_Logic.class);
    }

    @Step("Check tyres horizontal selector visibility. TyresProduct_page")
    public TyresProduct_page_Logic checkTyresHorizontalSelectorVisibility() {
        tyresHorizontalSelector().shouldBe(visible);
        return this;
    }

    @Step("Check tyres photo block visibility. TyresProduct_page")
    public TyresProduct_page_Logic checkTyresPhotoBlockVisibility() {
        tyresPhotoBlock().shouldBe(visible);
        return this;
    }

    @Step("Check tyres product title visibility. TyresProduct_page")
    public TyresProduct_page_Logic checkTyresProductTitleVisibility() {
        tyresProductTitle().shouldBe(visible);
        return this;
    }

    @Step("Check tyres characteristics block. TyresProduct_page")
    public TyresProduct_page_Logic checkCharacteristicsBlock() {
        Product_page productPage = new Product_page();
        productPage.minimizedCharactericticBlock().shouldBe(visible);
        productPage.uncoverCharactericticBtn().click();
        productPage.maximizedCharacteristicBlock().shouldBe(visible);
        productPage.coverCharacteristicBtn().click();
        productPage.minimizedCharactericticBlock().shouldBe(visible);
        return this;
    }

    @Step("Check tyres delivery block. TyresProduct_page")
    public TyresProduct_page_Logic checkDeliveryBlockVisibility() {
        tyresDeliveryBlock().shouldBe(visible);
        return this;
    }

    @Step("Check topseller block. TyresProduct_page")
    public TyresProduct_page_Logic checkTopsellerBlockVisibility() {
        topsellerBlock().shouldBe(visible);
        productInTopsellerBlock().shouldBe(visible);
        return this;
    }

    @Step("Check reviews block. TyresProduct_page")
    public TyresProduct_page_Logic checkReviewsBlockVisibility() {
        reviewsBlock().shouldBe(visible);
        return this;
    }

    @Step("Check related products block. TyresProduct_page")
    public TyresProduct_page_Logic checkRelatedProductsBlockVisibility() {
        relatedProductsBlock().shouldBe(visible);
        return this;
    }
}
