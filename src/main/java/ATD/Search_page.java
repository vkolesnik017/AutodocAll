package ATD;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.page;

public class Search_page {

    public Cart_page cartClick() {
        new Main_page().cartClick();
        return page(Cart_page.class);
    }

    public SelenideElement buyButton() {
        return $(By.xpath("//div[@class='button ']/a[@id='search_page_product']"));
    }

    // locators in popup of gray button for subscription for product which is not stock
    public SelenideElement emailFieldInPopUpOfGrayBtn() {
        return $(byId("form_AvailabilityReminder[email]"));
    }

    public SelenideElement sendButtonInPopUpOfGrayBtn() {
        return $(byCssSelector(".popup-available__button"));
    }

    public SelenideElement checkboxInPopUpOfGrayBtn() {
        return $(byCssSelector(".popup-available__label"));
    }

    public SelenideElement closeSuccessPopUpOfGrayBtn() {
        return $(byXpath("//div[@class='popup_top']//a[@class='close']"));
    }

    // locator for search button by ID product in listing
    public SelenideElement buttonProductById(String id) {
        return $(byId(id));
    }

    // locator for search image by ID product in listing
    public SelenideElement imageProductById(String id) {
        return $(byXpath("//*[@id='" + id + "']/../../..//*[@class='ovVisLi_image']"));
    }

    SelenideElement cartPopupWithProduct() {
        return $(byCssSelector(".cart-items-block__title"));
    }

    public Cart_page addFirstProductAndGoToCart() {
        buyButton().click();
        cartPopupWithProduct().shouldBe(Condition.visible);
        cartClick();
        return page(Cart_page.class);
    }

}

