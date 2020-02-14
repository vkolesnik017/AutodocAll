package ATD;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.By;

import static ATD.CommonMethods.closeCookiesFooterMessage;
import static ATD.CommonMethods.waitWhileRouteBecomeExpected;
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

    public Search_page verifyNameRouteEqualsSearch() {
        waitWhileRouteBecomeExpected("search");
        return this;
    }

    @Step
    public Search_page verifyTextInSearchBar(String expectedText) {
        new Main_page().searchBar().shouldHave(exactValue(expectedText));
        return this;
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

    private SelenideElement closeBtnPopupOfChooseCar() {
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
        cartPopupWithProduct().shouldBe(visible);
        cartClick();
        return page(Cart_page.class);
    }

    @Step
    public Product_page detailsClick(){
        detalisBtn().click();
        return page(Product_page.class);
    }

    @Step("Gets all the characteristics of the desired product from search listing {productArticle}")
    // example String for productArticle = V99-75-0011
    public ElementsCollection getCharacteristicsDesiredProductForSearch(String productArticle) {
        return $$x("//*[@class='rc' and contains(text(),'" + productArticle +"')]/ancestor::div[@class='box criteria_toogle_active']//li")
                .shouldHave(sizeGreaterThan(10));
    }

    @Step
    public Search_page closePopupOfChooseCar() {
        closeBtnPopupOfChooseCar().click();
        return this;
    }

}

