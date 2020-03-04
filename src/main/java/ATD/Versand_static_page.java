package ATD;


import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selectors.byXpath;
import static com.codeborne.selenide.Selenide.*;

public class Versand_static_page {

    protected SelenideElement topBlock() {
        return $(By.cssSelector(".top-block"));
    }

    protected SelenideElement topBlockLeft() {
        return $(By.cssSelector(".top-block__left"));
    }

    protected SelenideElement topBlockRight() {
        return $(By.cssSelector(".top-block__right"));
    }

    protected SelenideElement deliveryCompanyImages() {
        return $(By.cssSelector(".top-block__right-images"));
    }

    protected SelenideElement pricesBlock() {
        return $(By.cssSelector(".prices-block"));
    }

    protected SelenideElement deliveryPriceBlock() {
        return $(By.cssSelector(".prices-block__items"));
    }

    protected SelenideElement limitForFreeDelivery() {
        return $(byXpath("(//*[@class='prices-block__items-item']/p)[2]"));
    }

    protected SelenideElement vatBlock() {
        return $(By.cssSelector(".prices-block__text"));
    }

    protected SelenideElement countryPricesBlock() {
        return $(By.cssSelector(".country-prices"));
    }

    protected ElementsCollection countyPriceDelivery() {
        return $$(By.cssSelector(".country-prices__item"));
    }

    protected SelenideElement allCountriesButton() {
        return $(By.cssSelector(".country-prices__button"));
    }

    protected SelenideElement allCountriesBlock() {
        return $(By.xpath("//*[@class='country-prices__pop country-prices-pop-js']"));
    }

    protected SelenideElement deliveryTimeBlock() {
        return $(By.cssSelector(".delivery-time"));
    }

    protected SelenideElement textDelivery() {
        return $(By.cssSelector(".delivery-time__text"));
    }

    protected SelenideElement greenDeliveryBlock() {
        return $(By.xpath("//*[@class='delivery-time__block']"));
    }

    protected SelenideElement redDeliveryBlock() {
        return $(By.xpath("//*[@class='delivery-time__block red']"));
    }

    protected SelenideElement deliveryLink() {
        return $(By.xpath("//*[@class='delivery-time__text']/span/a"));
    }

    protected SelenideElement recommendationBlock() {
        return $(By.cssSelector(".recommendation-block"));
    }

    protected SelenideElement deliveryCheckboxClick() {
        return $(By.xpath("//*[@class='safe-order__check']/label/span"));
    }

    protected SelenideElement deliveryCheckbox() {
        return $(By.name("security_delivery"));
    }

    protected SelenideElement safeOrderBlock() {
        return $(By.cssSelector(".safe-order"));
    }

    protected SelenideElement soDays() {
        return $(By.xpath("//*[@class='safe-order__eclipse-orange']/p[1]"));
    }

    protected SelenideElement soDaysBig() {
        return $(By.xpath("//*[@class='safe-order__eclipse-orange']/p[2]"));
    }

    protected SelenideElement soTime() {
        return $(By.xpath("//*[@class='safe-order__eclipse-gray']/p[3]/span"));
    }

    protected SelenideElement soPrice1() {
        return $(By.xpath("//div[1][@class='safe-order__number-row']/p[1]"));
    }

    protected SelenideElement soPrice2() {
        return $(By.xpath("//div[2][@class='safe-order__number-row']/p[1]"));
    }

    protected SelenideElement soDays2() {
        return $(By.xpath("//div[1][@class='safe-order__number-row']/p[3]"));
    }

    protected SelenideElement soDays3() {
        return $(By.xpath("//div[2][@class='safe-order__number-row']/p[3]"));
    }

    protected SelenideElement soText() {
        return $(By.xpath("//*[@class='safe-order__text']"));
    }

    protected SelenideElement soReturnText() {
        return $(By.xpath("//*[@class='safe-order__return']"));
    }

    protected SelenideElement tyresDelivery() {
        return $(By.xpath("//*[@class='tyres-delivery']"));
    }

    protected SelenideElement tyresDeliveryRowItemFree() {
        return $x("//div[@class='tyres-delivery__row-item tyres-delivery__row-item--free']");
    }

    protected SelenideElement tyresDeliveryRowItemOrder() {
        return $x("//div[@class='tyres-delivery__row-item tyres-delivery__row-item--order']");
    }

    protected SelenideElement tyresDeliveryRowItemPoint() {
        return $x("//div[@class='tyres-delivery__row-item tyres-delivery__row-item--point']");
    }

    protected SelenideElement shippingBlock() {
        return $(By.xpath("//*[@class='oversize-block']"));
    }

    protected SelenideElement shippingTab2() {
        return $(By.xpath("//*[@class='tabs-js']/li[2]"));
    }

    protected SelenideElement tabUnfoldingButton() {
        return $(By.xpath("//*[@class='full-text full-text-js']/a"));
    }

    protected SelenideElement tabMinimizingButton() {
        return $x("//*[@class='full-text full-text-js active']");
    }

    protected SelenideElement fullPartsList() {
        return $(By.xpath("//*[@class='oversize-block__tabs-list list-full']"));
    }

    protected SelenideElement countryBlock() {
        return $(By.xpath("//*[@class='oversize-block__tabs-wrap-country']"));
    }

    protected SelenideElement chooseDeliveryBlock() {
        return $(By.cssSelector(".choose-delivery"));
    }

    protected SelenideElement chooseDeliveryTitle() {
        return $(By.xpath("//*[@class='choose-delivery__title']/span"));
    }

    protected SelenideElement glsCheckbox() {
        return $(By.xpath("//div[1][@class='choose-delivery__wrap-item']/div/label/input"));
    }

    protected SelenideElement glsCheckboxClick() {
        return $(By.xpath("//div[1][@class='choose-delivery__wrap-item']/div/label/span"));
    }

    protected SelenideElement dhlCheckbox() {
        return $(By.xpath("//div[2][@class='choose-delivery__wrap-item']/div/label/input"));
    }

    protected SelenideElement dhlCheckboxClick() {
        return $(By.xpath("//div[2][@class='choose-delivery__wrap-item']/div/label/span"));
    }

    protected SelenideElement noxCheckbox() {
        return $(By.xpath("//div[3][@class='choose-delivery__wrap-item']/div/label/input"));
    }

    protected SelenideElement noxCheckboxClick() {
        return $(By.xpath("//div[3][@class='choose-delivery__wrap-item']/div/label/span"));
    }

    protected SelenideElement dpdCheckbox() {
        return $(By.xpath("//div[4][@class='choose-delivery__wrap-item']/div/label/input"));
    }

    protected SelenideElement dpdCheckboxClick() {
        return $(By.xpath("//div[4][@class='choose-delivery__wrap-item']/div/label/span"));
    }

    protected SelenideElement pnordCheckbox() {
        return $(By.xpath("//div[5][@class='choose-delivery__wrap-item']/div/label/input"));
    }

    protected SelenideElement pnordCheckboxClick() {
        return $(By.xpath("//div[5][@class='choose-delivery__wrap-item']/div/label/span"));
    }

    protected SelenideElement chooseDeliveryInput() {
        return $(By.xpath("//*[@class='choose-delivery__input']/input"));
    }

    protected SelenideElement chooseDeliveryButton() {
        return $(By.xpath("//*[@class='delivery-button-js']"));
    }

    protected SelenideElement chooseDeliveryAnswerText() {
        return $(By.xpath("//*[@class='choose-delivery__text delivery-text-js']"));
    }

    protected SelenideElement deliveryOptionsBlock() {
        return $(By.xpath("//*[@class='choose-delivery__wrap delivery-wrap-js']"));
    }

}
