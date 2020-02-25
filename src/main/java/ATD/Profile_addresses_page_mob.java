package ATD;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;

class Profile_addresses_page_mob {

    SelenideElement billingAddress() {
        return $(By.xpath("//div[@class='title_table billing']/following::form[1]"));
    }

    SelenideElement subscribeCheckbox() {
        return $(By.xpath("//input[@name='isNewsSubscribe']"));
    }

    SelenideElement clickLocatorSubscribeCheckbox() {
        return $(By.xpath("//label[@for='form_isNewsSubscribe']"));
    }

    SelenideElement ortBilling() {
        return $(By.id("form_rOrt"));
    }

    SelenideElement strasseBilling() {
        return $(By.id("form_rStrasse"));
    }

    SelenideElement hausnummerBilling() {
        return $(By.id("form_payment_house"));
    }

    SelenideElement plzBilling() {
        return $(By.id("form_rPlz"));
    }

    SelenideElement telBilling() {
        return $(By.id("form_rTelefon"));
    }

    SelenideElement ortShipping() {
        return $(By.id("form_lOrt"));
    }

    SelenideElement strasseShipping() {
        return $(By.id("form_lStrasse"));
    }

    SelenideElement hausnummerShipping() {
        return $(By.id("form_delivery_house"));
    }

    SelenideElement plzShipping() {
        return $(By.id("form_lPlz"));
    }

    SelenideElement telShipping() {
        return $(By.id("form_lTelefon"));
    }

    SelenideElement name() {
        return $(By.id("form_lName"));
    }

    SelenideElement vorname() {
        return $(By.id("form_lVorname"));
    }

    SelenideElement submitBtn() {
        return $(By.xpath("//a[@class='button profile_submit_address']"));
    }

    SelenideElement successPopup() {
        return $(By.xpath("//div[@class='popup_review_success_body']"));
    }
}
