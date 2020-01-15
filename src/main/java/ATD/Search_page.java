package ATD;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.By;

import static ATD.CommonMethods.closeCookiesFooterMessage;
import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;
import static com.codeborne.selenide.Selenide.page;

public class Search_page {

    public Cart_page cartClick() {
        new Main_page().cartClick();
        return page(Cart_page.class);
    }

    public Search_page closeFooterMessageCookies(){
        closeCookiesFooterMessage();
        return this;
    }

    private SelenideElement buyButton() {
        return $(By.xpath("//div[@class='button ']/a[@id='search_page_product']"));
    }

    private SelenideElement detalisBtn() {
        return $x("//div[@class='about']/button");
    }

    // locator for counter
    private SelenideElement counterValue() {
        return $(By.xpath("//input[@class='amount qty_2']"));
    }

    private SelenideElement counterPlus() {
        return $(By.xpath("//a[@class='ga-click plus add']"));
    }

    private SelenideElement counterMinus() {
        return $(By.xpath("//a[@class='ga-click minus remove']"));
    }

    public Search_page counterIncrease(String startValue){
        new CommonMethods().checkingCounterIncrease(startValue, counterValue(), counterPlus());
        return this;
    }

    public Search_page counterDecrease(String startValue){
        new CommonMethods().checkingCounterDecrease(startValue, counterValue(), counterMinus());
        return this;
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
        return $(byXpath("//*[@id='" + id + "']/../../..//img[contains(@class,'image')]"));
    }

    private SelenideElement cartPopupWithProduct() {
        return $(byCssSelector(".cart-items-block__title"));
    }

    @Step
    public Cart_page addFirstProductAndGoToCart() {
        buyButton().click();
        cartPopupWithProduct().shouldBe(Condition.visible);
        cartClick();
        return page(Cart_page.class);
    }

    @Step
    public Product_page detailsClick(){
        detalisBtn().click();
        return page(Product_page.class);
    }

}

