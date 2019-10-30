package ATD;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.page;

class Search_page {

    Cart_page cartClick() {
        new Main_page().cartClick();
        return page(Cart_page.class);
    }

    SelenideElement buyButton() {
        return $(By.xpath("//div[@class='button ']/a[@id='search_page_product']"));
    }

    SelenideElement emailFieldInPopUpAvailable() {
        return $(byId("form_AvailabilityReminder[email]"));
    }

    SelenideElement sendenButtonInPopUpAvailable() {
        return $(byCssSelector(".popup-available__button"));
    }

    SelenideElement checkboxInPopUpAvailable() {
        return $(byCssSelector(".popup-available__label"));
    }

    SelenideElement closeSuccessPopUpAvailable() {
        return $(byXpath("//div[@class='popup_top']//a[@class='close']"));
    }

    // locator for search a button product in listing by ID
    SelenideElement productButtonById(String id) {
        return $(byId(id));
    }

    // locator for search an image product in listing by ID
    SelenideElement imageProductById(String id) {
        return $(byXpath("//*[@id='" + id + "']/../../..//*[@class='ovVisLi_image']"));
    }

}

