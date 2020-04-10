package ATD;


import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selectors.byXpath;
import static com.codeborne.selenide.Selenide.*;

class Versand_static_page {

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

    SelenideElement pricesBlock() {
        return $(By.cssSelector(".prices-block"));
    }

    SelenideElement deliveryPriceBlock() {
        return $(By.cssSelector(".prices-block__items"));
    }

    SelenideElement limitForFreeDelivery() {
        return $(byXpath("(//*[@class='prices-block__items-item']/p)[2]"));
    }

    SelenideElement vatBlock() {
        return $(By.cssSelector(".prices-block__text"));
    }

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

    SelenideElement recommendationBlock() {
        return $(By.cssSelector(".recommendation-block"));
    }

    SelenideElement deliveryCheckboxClick() {
        return $(By.xpath("//*[@class='safe-order__check']/label/span"));
    }

    SelenideElement deliveryCheckbox() {
        return $(By.name("security_delivery"));
    }

    SelenideElement safeOrderBlock() {
        return $(By.cssSelector(".safe-order"));
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

    SelenideElement shippingBlock() {
        return $(By.xpath("//*[@class='oversize-block']"));
    }

    SelenideElement shippingTab2() {
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

    SelenideElement countryBlock() {
        return $(By.xpath("//*[@class='oversize-block__tabs-wrap-country']"));
    }

    SelenideElement chooseDeliveryBlock() {
        return $(By.cssSelector(".choose-delivery"));
    }

    SelenideElement chooseDeliveryTitle() {
        return $(By.xpath("//*[@class='choose-delivery__title']/span"));
    }

    SelenideElement glsCheckbox() {
        return $(By.xpath("//div[1][@class='choose-delivery__wrap-item']/div/label/input"));
    }

    SelenideElement glsCheckboxClick() {
        return $(By.xpath("//div[1][@class='choose-delivery__wrap-item']/div/label/span"));
    }

    SelenideElement dhlCheckbox() {
        return $(By.xpath("//div[2][@class='choose-delivery__wrap-item']/div/label/input"));
    }

    SelenideElement dhlCheckboxClick() {
        return $(By.xpath("//div[2][@class='choose-delivery__wrap-item']/div/label/span"));
    }

    SelenideElement noxCheckbox() {
        return $(By.xpath("//div[3][@class='choose-delivery__wrap-item']/div/label/input"));
    }

    SelenideElement noxCheckboxClick() {
        return $(By.xpath("//div[3][@class='choose-delivery__wrap-item']/div/label/span"));
    }

    SelenideElement dpdCheckbox() {
        return $(By.xpath("//div[4][@class='choose-delivery__wrap-item']/div/label/input"));
    }

    SelenideElement dpdCheckboxClick() {
        return $(By.xpath("//div[4][@class='choose-delivery__wrap-item']/div/label/span"));
    }

    SelenideElement pnordCheckbox() {
        return $(By.xpath("//div[5][@class='choose-delivery__wrap-item']/div/label/input"));
    }

    SelenideElement pnordCheckboxClick() {
        return $(By.xpath("//div[5][@class='choose-delivery__wrap-item']/div/label/span"));
    }

    SelenideElement chooseDeliveryInput() {
        return $(By.xpath("//*[@class='choose-delivery__input']/input"));
    }

    SelenideElement chooseDeliveryButton() {
        return $(By.xpath("//*[@class='delivery-button-js']"));
    }

    SelenideElement chooseDeliveryAnswerText() {
        return $(By.xpath("//*[@class='choose-delivery__text delivery-text-js']"));
    }

    SelenideElement deliveryOptionsBlock() {
        return $(By.xpath("//*[@class='choose-delivery__wrap delivery-wrap-js']"));
    }

    SelenideElement deliveryPriceForUKlocator() { return $x("//*[@class='country-prices__pop country-prices-pop-js']//span[contains (text(), 'Großbritannien')]/../../span"); }

}
