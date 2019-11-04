package ATD;


import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Condition.checked;
import static com.codeborne.selenide.Condition.selected;
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

    public SelenideElement vatBlock() {
        return $(By.cssSelector(".prices-block__text"));
    }

    public SelenideElement countyPriceDelivery() {
        return $(By.cssSelector(".country-prices__item"));
    }

    public SelenideElement allCountriesButton() {
        return $(By.cssSelector(".country-prices__button"));
    }

    public SelenideElement allCountriesBlock() {
        return $(By.xpath("//*[@class='country-prices__pop country-prices-pop-js']"));
    }

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

    //Checks the functionality of the checkbox
    public void checkUncheckCheckbox(SelenideElement checkLocator, SelenideElement clickLocator) {
        $(checkLocator).shouldNotBe(selected);
        $(checkLocator).shouldNotBe(checked);
        $(clickLocator).click();
        $(checkLocator).shouldBe(selected);
        $(checkLocator).shouldBe(checked);
    }
}
