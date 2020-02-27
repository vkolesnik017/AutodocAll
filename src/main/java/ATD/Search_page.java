package ATD;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.By;

import static ATD.CommonMethods.closeCookiesFooterMessage;
import static com.codeborne.selenide.CollectionCondition.sizeGreaterThan;
import static com.codeborne.selenide.Condition.exactValue;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.*;

public class Search_page {

    public Cart_page cartClick() {
        new Main_page().cartClick();
        return page(Cart_page.class);
    }

    public Search_page checksProductTitlesContainExpectedTextGoingAllPagination(String expectedText) {
        new Listing_page().checksProductTitlesContainExpectedTextGoingAllPagination(expectedText);
        return this;
    }

    public Search_page closeFooterMessageCookies(){
        closeCookiesFooterMessage();
        return this;
    }

    @Step
    public Search_page verifyTextInSearchBar(String expectedText) {
        new Main_page().searchBar().shouldHave(exactValue(expectedText));
        return this;
    }

    public SelenideElement titleOnSearchPage() {
        return $(".title_count_search");
    }

    public SelenideElement blockOfHelpSearchProducts() {
        return $(".filter-not-found__title");
    }

    public SelenideElement blockOfLinkingCategory() {
        return $(".sidebar-category");
    }

    private SelenideElement buyButton() {
        return $(By.xpath("//div[@class='button ']/a[@id='search_page_product']"));
    }

    private SelenideElement detalisBtn() {
        return $x("//div[@class='about']/button");
    }

    public SelenideElement dividingLineForProductsOtherCategories() { return $x("//*[@class='w_search no_margin']"); }

    SelenideElement closeBtnPopupOfChooseCar() {
        return $(".back");
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
    SelenideElement emailFieldInPopUpOfGrayBtn() {
        return $(byId("form_AvailabilityReminder[email]"));
    }

    SelenideElement sendButtonInPopUpOfGrayBtn() {
        return $(byCssSelector(".popup-available__button"));
    }

    SelenideElement checkboxInPopUpOfGrayBtn() {
        return $(byCssSelector(".popup-available__label"));
    }

    SelenideElement closeSuccessPopUpOfGrayBtn() {
        return $(byXpath("//div[@class='popup_top']//a[@class='close']"));
    }

    // locator for search button by ID product in listing
    SelenideElement buttonProductById(String id) {
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
    public Cart_page_Logic addFirstProductAndGoToCart() {
        buyButton().click();
        cartPopupWithProduct().shouldBe(visible);
        cartClick();
        return page(Cart_page_Logic.class);
    }

    @Step
    public Product_page_Logic detailsClick(){
        detalisBtn().click();
        return page(Product_page_Logic.class);
    }

    @Step("Gets all the characteristics of the desired product from search listing {productArticle}")
    // example String for productArticle = V99-75-0011
    public ElementsCollection getCharacteristicsDesiredProductForSearch(String productArticle) {
        return $$x("//*[@class='rc' and contains(text(),'" + productArticle +"')]/ancestor::div[@class='box criteria_toogle_active']//li")
                .shouldHave(sizeGreaterThan(10));
    }

    SelenideElement secondListingPage() { return $(By.xpath("//*[@class='pagination']/span[3]/a")); }

    // Form soft 404
    SelenideElement mailFieldSoftForm() { return $(By.id("form_email")); }

    SelenideElement submitBtnSoftForm() { return $(By.cssSelector(".notification-form__row > button")); }

    SelenideElement subscribeCheckboxSoftForm() { return $(By.id("subscribe_on")); }

    SelenideElement errPopupSoftForm() { return $(By.id("popup_update")); }

    SelenideElement successPopupSoftForm() { return $(By.xpath("//div[@class='new_popup popup_email_not']")); }

    SelenideElement closeErrPopupSoftForm() { return $(By.xpath("//div[@class='popup_content']//a[@class='close']")); }

    SelenideElement closeSuccessPopupSoftForm() { return $(By.xpath("//div[@class='button loc']")); }

    SelenideElement datenschutzerklarungLinkSoftForm() { return $(By.cssSelector("#privacy_policy1>a")); }

}

