package ATD;


import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selectors.byXpath;
import static com.codeborne.selenide.Selenide.*;

class Versand_static_page {

    // Locators for top-block
    SelenideElement topBlock() {
        return $(By.cssSelector(".top-block"));
    }

    SelenideElement topBlockLeft() {
        return $(By.cssSelector(".top-block__left"));
    }

    SelenideElement topBlockRight() {
        return $(By.cssSelector(".top-block__right"));
    }

    SelenideElement deliveryCompanyImages() {
        return $(By.cssSelector(".top-block__right-images"));
    }

    // Locators for islands block
    SelenideElement islandsBlockTitle() {
        return $(".islands-block__title");
    }

    ElementsCollection islandDeliveryPrices() {
        return $$x("//ul[@class='islands-block__list']//span[@class='price']");
    }

    // Locators for prices block
    SelenideElement pricesBlock() {
        return $(By.cssSelector(".prices-block"));
    }

    SelenideElement deliveryPriceBlock() {
        return $(By.cssSelector(".prices-block__items"));
    }

    SelenideElement limitForFreeDelivery() {
        return $(byXpath("(//*[@class='prices-block__items-item']/p)[2]"));
    }

    SelenideElement deliveryPrice() {
        return $x("//div[@class='prices-block__items-item']/span[@data-image='image1']");
    }

    SelenideElement vatBlock() {
        return $(By.cssSelector(".prices-block__text"));
    }

    // Locators for country- prices block
    SelenideElement countryPricesBlock() {
        return $(By.cssSelector(".country-prices"));
    }

    ElementsCollection countyPriceDelivery() {
        return $$(By.cssSelector(".country-prices__item"));
    }

    SelenideElement allCountriesButton() {
        return $(By.cssSelector(".country-prices__button"));
    }

    SelenideElement allCountriesBlock() {
        return $(By.xpath("//*[@class='country-prices__pop country-prices-pop-js']"));
    }

    SelenideElement deliveryPriceLocator(String country) {
        return $x("//*[@class='country-prices__pop country-prices-pop-js']//span[contains (text(), '" + country + "')]/../../span");
    }

    SelenideElement deliveryPriceLocatorWithNameShop(String shop) {
        return $x("(//img[contains(@src,'" + shop + "')]/../..//span)[2]");
    }

    // Locators for delivery page callback form
    SelenideElement datenschutzerklarungLink() {
        return $("#Delivery_privacy_policy > a");
    }

    SelenideElement sendShipFormMailField() {
        return $x("//input[@id='form_Email']");
    }

    SelenideElement mailingCheckbox() {
        return $("#isSubscribe_delivery");
    }

    SelenideElement submitButton() {
        return $x("//*[@name='request_callback']");
    }

    SelenideElement sendShipFormSuccesPopup() {
        return $(By.id("popup_update"));
    }

    SelenideElement sendShipFormSuccesPopupCloseBtn() {
        return $x("//div[@id='popup_update']//div[@class='buttons-inner']/a");
    }

    SelenideElement countryDropdown() {
        return $("#form_Land");
    }

    // Locators for delivery-time block
    SelenideElement deliveryTimeBlock() {
        return $(By.cssSelector(".delivery-time"));
    }

    SelenideElement textDelivery() {
        return $(By.cssSelector(".delivery-time__text"));
    }

    SelenideElement greenDeliveryBlock() {
        return $(By.xpath("//*[@class='delivery-time__block']"));
    }

    SelenideElement redDeliveryBlock() {
        return $(By.xpath("//*[@class='delivery-time__block red']"));
    }

    SelenideElement deliveryLink() {
        return $(By.xpath("//*[@class='delivery-time__text']/span/a"));
    }

    // Locators for recommendation block
    SelenideElement recommendationBlock() {
        return $(By.cssSelector(".recommendation-block"));
    }

    // Locators for  safe-order block
    SelenideElement safeOrderBlock() {
        return $(By.cssSelector(".safe-order"));
    }

    SelenideElement deliveryCheckboxClick() {
        return $(By.xpath("//*[@class='safe-order__check']/label/span"));
    }

    SelenideElement deliveryCheckbox() {
        return $(By.name("security_delivery"));
    }

    SelenideElement safeOrderPrice() {
        return $x("(//div[@class='safe-order__number-row']/p[@class='price'])[2]");
    }

    SelenideElement soDays() {
        return $(By.xpath("//*[@class='safe-order__eclipse-orange']/p[1]"));
    }

    SelenideElement soDaysBig() {
        return $(By.xpath("//*[@class='safe-order__eclipse-orange']/p[2]"));
    }

    SelenideElement soTime() {
        return $(By.xpath("//*[@class='safe-order__eclipse-gray']/p[3]/span"));
    }

    SelenideElement soPrice1() {
        return $(By.xpath("//div[1][@class='safe-order__number-row']/p[1]"));
    }

    SelenideElement soPrice2() {
        return $(By.xpath("//div[2][@class='safe-order__number-row']/p[1]"));
    }

    SelenideElement soDays2() {
        return $(By.xpath("//div[1][@class='safe-order__number-row']/p[3]"));
    }

    SelenideElement soDays3() {
        return $(By.xpath("//div[2][@class='safe-order__number-row']/p[3]"));
    }

    SelenideElement soText() {
        return $(By.xpath("//*[@class='safe-order__text']"));
    }

    SelenideElement soReturnText() {
        return $(By.xpath("//*[@class='safe-order__return']"));
    }

    // Locators for tyres-delivery block
    SelenideElement tyresDelivery() {
        return $(By.xpath("//*[@class='tyres-delivery']"));
    }

    SelenideElement tyresDeliveryRowItemFree() {
        return $x("//div[@class='tyres-delivery__row-item tyres-delivery__row-item--free']");
    }

    SelenideElement tyresDeliveryRowItemOrder() {
        return $x("//div[@class='tyres-delivery__row-item tyres-delivery__row-item--order']");
    }

    SelenideElement tyresDeliveryRowItemPoint() {
        return $x("//div[@class='tyres-delivery__row-item tyres-delivery__row-item--point']");
    }

    // Locators for oversize block
    SelenideElement oversizeShippingBlock() {
        return $(By.xpath("//*[@class='oversize-block']"));
    }

    SelenideElement oversizeShippingTab() {
        return $(By.xpath("//*[@class='tabs-js']/li[2]"));
    }

    SelenideElement tabUnfoldingButton() {
        return $(By.xpath("//*[@class='full-text full-text-js']/a"));
    }

    SelenideElement tabMinimizingButton() {
        return $x("//*[@class='full-text full-text-js active']");
    }

    SelenideElement fullPartsList() {
        return $(By.xpath("//*[@class='oversize-block__tabs-list list-full']"));
    }

    SelenideElement oversizeCountryBlock() {
        return $(By.xpath("//*[@class='oversize-block__tabs-wrap-country']"));
    }
}
