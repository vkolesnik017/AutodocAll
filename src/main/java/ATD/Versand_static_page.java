package ATD;


import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Condition.checked;
import static com.codeborne.selenide.Condition.selected;
import static com.codeborne.selenide.Selenide.$;

class Versand_static_page {

    SelenideElement topBlockLeft() {
        return $(By.cssSelector(".top-block__left"));
    }

    SelenideElement topBlockRight() {
        return $(By.cssSelector(".top-block__right"));
    }

    SelenideElement deliveryCompanyImages() {
        return $(By.cssSelector(".top-block__right-images"));
    }

    SelenideElement deliveryPriceBlock() {
        return $(By.cssSelector(".prices-block__items"));
    }

    SelenideElement vatBlock() {
        return $(By.cssSelector(".prices-block__text"));
    }

    SelenideElement countyPriceDelivery() {
        return $(By.cssSelector(".country-prices__item"));
    }

    SelenideElement allCountriesButton() {
        return $(By.cssSelector(".country-prices__button"));
    }

    SelenideElement allCountriesBlock() {
        return $(By.xpath("//*[@class='country-prices__pop country-prices-pop-js']"));
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

    //Checks the functionality of the checkbox
    void checkUncheckCheckbox(SelenideElement checkLocator, SelenideElement clickLocator) {
        $(checkLocator).shouldNotBe(selected);
        $(checkLocator).shouldNotBe(checked);
        $(clickLocator).click();
        $(checkLocator).shouldBe(selected);
        $(checkLocator).shouldBe(checked);
    }
}
