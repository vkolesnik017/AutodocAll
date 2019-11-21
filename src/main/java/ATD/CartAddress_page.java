package ATD;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.page;

public class CartAddress_page {

    @Step
    public Main_page logoClick() {
        $(By.xpath("//div[@class='cart-page-head__logo']")).click();
        return page(Main_page.class);
    }

    public SelenideElement nextButton() {
        return $(byCssSelector(".address-continue>a"));
    }

    @Step
    public CartPayments_page nextBtnClick() {
        nextButton().click();
        return page(CartPayments_page.class);
    }

    SelenideElement countryInSelector(String country) {
        return $(byXpath("//*[@name='lLand']//*[@data-code='" + country + "']"));
    }

    public SelenideElement currentCountryInSelector() {
        return $(byXpath("//*[@id='form_lLand']/option[@selected]"));
    }

    public SelenideElement postalCodeField() {
        return $(byName("lPlz"));
    }

    @Step("Postal code: {postalCode}")
    public CartAddress_page enterPostalCode(String postalCode) {
        postalCodeField().clear();
        postalCodeField().click();
        postalCodeField().setValue(postalCode);
        return this;
    }

    @Step
    public CartAddress_page chooseDeliveryCountry(String country) {
        countryInSelector(country).shouldBe(visible).click();
        return this;
    }

}
