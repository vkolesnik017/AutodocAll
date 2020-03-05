package ATD;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import io.qameta.allure.Step;

import static ATD.CommonMethods.*;
import static com.codeborne.selenide.CollectionCondition.sizeGreaterThan;
import static com.codeborne.selenide.Condition.exactValue;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$$x;
import static com.codeborne.selenide.Selenide.page;

public class Search_page_Logic extends Search_page {

    @Step(":on Search_page")
    public Cart_page_Logic cartClick() {
        new Main_page_Logic().cartClick();
        return page(Cart_page_Logic.class);
    }

    @Step(":on Search_page")
    public Search_page checksProductTitlesContainExpectedTextGoingAllPagination(String expectedText) {
        new Listing_page_Logic().checksProductTitlesContainExpectedTextGoingAllPagination(expectedText);
        return this;
    }

    @Step("Close footer message cookies. Search_page")
    public Search_page_Logic closeFooterMessageCookies() {
        closeCookiesFooterMessage();
        return this;
    }

    @Step("Verify text in search bar. Search_page")
    public Search_page_Logic verifyTextInSearchBar(String expectedText) {
        new Main_page().searchBar().shouldHave(exactValue(expectedText));
        return this;
    }

    @Step("Verify name route equals search. Search_page")
    public Search_page_Logic verifyNameRouteEqualsSearch() {
        waitWhileRouteBecomeExpected("search");
        return this;
    }

    @Step("Close popup of choose car. Search_page")
    private Search_page_Logic closePopupOfChooseCar() {
        closeBtnPopupOfChooseCar().click();
        return this;
    }

    @Step("Switch on second page. Search_page")
    public Search_page_Logic switchOnSecondPage() {
        secondListingPage().scrollTo();
        closePopupOfChooseCar().secondListingPage().click();
        return this;
    }


    @Step("Click on the button (Tell me when the product appears). Search_page")
    public Search_page_Logic clickButtonProductById(String idProduct) {
        buttonProductById(idProduct).click();
        return this;
    }

    @Step("Subscriptions for product that are not in stock. Search_page")
    public Search_page_Logic sendRequestByGrayButtonFromSearchPage(String email) {
        emailFieldInPopUpOfGrayBtn().setValue(email);
        checkboxInPopUpOfGrayBtn().click();
        sendButtonInPopUpOfGrayBtn().click();
        closeSuccessPopUpOfGrayBtn().click();
        return this;
    }

    @Step("Checking behavior of soft form 404. Search_page")
    public Catalog_page_Logic checkingBehaviorSoft404(String mail) {
        mailFieldSoftForm().setValue(mail);
        submitBtnSoftForm().click();
        errPopupSoftForm().shouldHave(Condition.text("Um fortzufahren bestätigen Sie bitte Ihr Newsletter-Abo"));
        closeErrPopupSoftForm().click();
        subscribeCheckboxSoftForm().click();
        submitBtnSoftForm().click();
        successPopupSoftForm().shouldBe(Condition.appear);
        closeSuccessPopupSoftForm().shouldHave(Condition.text("Einkauf fortsetzen")).click();
        waitingWhileLinkBecomeExpected("https://www.autodoc.de/autoteile");
        return page(Catalog_page_Logic.class);
    }

    @Step(":soft 404 form. Search_page")
    public Search_page_Logic checkingDatenschutzerklarungLinkBehaviorSoftForm() {
        new CommonMethods().checkingDatenschutzerklarungLinkBehavior(datenschutzerklarungLinkSoftForm(), "underline solid rgb(0, 0, 0)");
        return this;
    }

    @Step("Adds the first product and goes to the basket")
    public Cart_page_Logic addFirstProductAndGoToCart() {
        buyButton().click();
        cartPopupWithProduct().shouldBe(visible);
        cartClick();
        return page(Cart_page_Logic.class);
    }

    @Step(": on Search_page")
    public Search_page_Logic counterIncrease(String startValue){
        new CommonMethods().checkingCounterIncrease(startValue, counterValue(), counterPlus());
        return this;
    }

    @Step(": on Search_page")
    public Search_page_Logic counterDecrease(String startValue) {
        new CommonMethods().checkingCounterDecrease(startValue, counterValue(), counterMinus());
        return this;
    }

    @Step("Clicking details. Search_page")
    public Product_page_Logic detailsClick(){
        detalisBtn().click();
        return page(Product_page_Logic.class);
    }

    @Step("Gets all the characteristics of the desired product from search listing {productArticle}. Search_page")
    // example String for productArticle = V99-75-0011
    public ElementsCollection getCharacteristicsDesiredProductForSearch(String productArticle) {
        return $$x("//*[@class='rc' and contains(text(),'" + productArticle +"')]/ancestor::div[@class='box criteria_toogle_active']//li")
                .shouldHave(sizeGreaterThan(10));
    }
}

