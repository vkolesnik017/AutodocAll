package ATD;


import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Condition.checked;
import static com.codeborne.selenide.Condition.selected;
import static com.codeborne.selenide.Selectors.byXpath;
import static com.codeborne.selenide.Selenide.$;

public class Versand_static_page {

    public SelenideElement topBlockLeft() {
        return $(By.cssSelector(".top-block__left"));
    }

    public SelenideElement topBlockRight() {
        return $(By.cssSelector(".top-block__right"));
    }

    public SelenideElement deliveryCompanyImages() {
        return $(By.cssSelector(".top-block__right-images"));
    }

    public SelenideElement deliveryPriceBlock() {
        return $(By.cssSelector(".prices-block__items"));
    }

    public SelenideElement limitForFreeDelivery() {
        return $(byXpath("(//*[@class='prices-block__items-item']/p)[2]"));
}

    public SelenideElement vatBlock() {
        return $(By.cssSelector(".prices-block__text"));
    }

    public SelenideElement countyPriceDelivery() { return $(By.cssSelector(".country-prices__item")); }

    public SelenideElement allCountriesButton() {
        return $(By.cssSelector(".country-prices__button"));
    }

    public SelenideElement allCountriesBlock() { return $(By.xpath("//*[@class='country-prices__pop country-prices-pop-js']")); }

    public SelenideElement textDelivery() {
        return $(By.cssSelector(".delivery-time__text"));
    }

    public SelenideElement greenDeliveryBlock() {
        return $(By.xpath("//*[@class='delivery-time__block']"));
    }

    public SelenideElement redDeliveryBlock() {
        return $(By.xpath("//*[@class='delivery-time__block red']"));
    }

    public SelenideElement deliveryLink() {
        return $(By.xpath("//*[@class='delivery-time__text']/span/a"));
    }

    public SelenideElement recommendationBlock() {
        return $(By.cssSelector(".recommendation-block"));
    }

    public SelenideElement deliveryCheckboxClick() {
        return $(By.xpath("//*[@class='safe-order__check']/label/span"));
    }

    public SelenideElement deliveryCheckbox() {
        return $(By.name("security_delivery"));
    }

    public SelenideElement soDays() { return $(By.xpath("//*[@class='safe-order__eclipse-orange']/p[1]")); }

    public SelenideElement soDaysBig() { return $(By.xpath("//*[@class='safe-order__eclipse-orange']/p[2]")); }

    public SelenideElement soTime() { return $(By.xpath("//*[@class='safe-order__eclipse-gray']/p[3]/span")); }

    public SelenideElement soPrice1() { return $(By.xpath("//div[1][@class='safe-order__number-row']/p[1]")); }

    public SelenideElement soPrice2() { return $(By.xpath("//div[2][@class='safe-order__number-row']/p[1]")); }

    public SelenideElement soDays2() { return $(By.xpath("//div[1][@class='safe-order__number-row']/p[3]")); }

    public SelenideElement soDays3() { return $(By.xpath("//div[2][@class='safe-order__number-row']/p[3]")); }

    public SelenideElement soText() { return $(By.xpath("//*[@class='safe-order__text']")); }

    public SelenideElement soReturnText() { return $(By.xpath("//*[@class='safe-order__return']")); }

    public SelenideElement tyresDelivery() { return $(By.xpath("//*[@class='tyres-delivery']")); }

    public SelenideElement shippingBlock() { return $(By.xpath("//*[@class='oversize-block']")); }

    public SelenideElement shippingTab2() { return $(By.xpath("//*[@class='tabs-js']/li[2]")); }

    public SelenideElement tabUnfoldingButton() { return $(By.xpath("//*[@class='full-text full-text-js']/a")); }

    public SelenideElement fullTab1() { return $(By.xpath("//*[@class='oversize-block__tabs-list list-full']")); }

    public SelenideElement countryBlock() { return $(By.xpath("//*[@class='oversize-block__tabs-wrap-country']")); }

    public SelenideElement chooseDeliveryTitle() { return $(By.xpath("//*[@class='choose-delivery__title']/span")); }

    public SelenideElement glsCheckbox() { return $(By.xpath("//div[1][@class='choose-delivery__wrap-item']/div/label/input")); }

    public SelenideElement glsCheckboxClick() { return $(By.xpath("//div[1][@class='choose-delivery__wrap-item']/div/label/span")); }

    public SelenideElement dhlCheckbox() { return $(By.xpath("//div[2][@class='choose-delivery__wrap-item']/div/label/input")); }

    public SelenideElement dhlCheckboxClick() { return $(By.xpath("//div[2][@class='choose-delivery__wrap-item']/div/label/span")); }

    public SelenideElement noxCheckbox() { return $(By.xpath("//div[3][@class='choose-delivery__wrap-item']/div/label/input")); }

    public SelenideElement noxCheckboxClick() { return $(By.xpath("//div[3][@class='choose-delivery__wrap-item']/div/label/span")); }

    public SelenideElement dpdCheckbox() { return $(By.xpath("//div[4][@class='choose-delivery__wrap-item']/div/label/input")); }

    public SelenideElement dpdCheckboxClick() { return $(By.xpath("//div[4][@class='choose-delivery__wrap-item']/div/label/span")); }

    public SelenideElement pnordCheckbox() { return $(By.xpath("//div[5][@class='choose-delivery__wrap-item']/div/label/input"));}

    public SelenideElement pnordCheckboxClick() { return $(By.xpath("//div[5][@class='choose-delivery__wrap-item']/div/label/span"));}

    public SelenideElement chooseDeliveryInput() { return $(By.xpath("//*[@class='choose-delivery__input']/input")); }

    public SelenideElement chooseDeliveryButton() { return $(By.xpath("//*[@class='delivery-button-js']")); }

    public SelenideElement chooseDeliveryAnswerText() { return $(By.xpath("//*[@class='choose-delivery__text delivery-text-js']")); }

    public SelenideElement chooseDeliveryBlock() { return $(By.xpath("//*[@class='choose-delivery__wrap delivery-wrap-js']")); }

    //Checks the functionality of the checkbox
    public void checkUncheckCheckbox(SelenideElement checkLocator, SelenideElement clickLocator) {
        $(checkLocator).shouldNotBe(selected);
        $(checkLocator).shouldNotBe(checked);
        $(clickLocator).click();
        $(checkLocator).shouldBe(selected);
        $(checkLocator).shouldBe(checked);
        $(clickLocator).click();
        $(checkLocator).shouldNotBe(selected);
        $(checkLocator).shouldNotBe(checked);
    }


    @Step
    // pulling prices for free delivery from the text in the delivery block
    public Float getDeliveryLimitFromText() {
        String deliveryLimit = limitForFreeDelivery().getText();
        deliveryLimit = deliveryLimit.replaceAll("[^0-9,]", "");
        return Float.valueOf(deliveryLimit.startsWith(",") ? deliveryLimit.substring(1) : deliveryLimit.split(",")[0]);
    }
}
