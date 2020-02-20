package ATD;

import com.codeborne.selenide.Condition;
import io.qameta.allure.Step;

import static ATD.CommonMethods.mailRandom;
import static ATD.CommonMethods.password;
import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Condition.not;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;

public class Main_page_logic extends Main_page {

  // Search bar
  @Step("Input text in search bar")
  public Main_page_logic inputTextInSearchBar(String text) {
    searchBar().setValue(text);
    return this;
  }

  @Step("Click tooltip in search by exact text {exactTooltipText}")
  public Search_page_Logic clickTooltipInSearchByExactText(String exactTooltipText) {
    tooltipToSearch().shouldBe(visible);
    tooltipsToSearch().filter(exactText(exactTooltipText)).shouldHaveSize(1).get(0).click();
    return page(Search_page_Logic.class);
  }

  // Selector

  @Step("Close tooltip in car selector")
  public Main_page_logic closeTooltipInCarSelector() {
    tooltipInCarSelectorPopup().shouldBe(visible);
    sleep(2000);
    tooltipInCarSelectorCloseBtn().click();
    tooltipInCarSelectorCloseBtn().shouldNot(visible);
    return this;
  }

    // Registration popup
    @Step("Open registration popup.")
    public Main_page_logic openRegistrationPopup() {
        loginBtnInHeader().click();
        registrationButtonInLoginPopup().click();
        return this;
    }

    @Step("Filling required fields for registration")
    public Main_page_logic fillRequiredFieldsForRegistration(String firstName, String secondName, String mail) {
        vornameInRegForm().setValue(firstName);
        nameInRegForm().setValue(secondName);
        mailInRegForm().setValue(mail);
        passStepInRegForm().click();
        return this;
    }

    @Step("Filling password fields and click registration")
    public Profile_page fillPasswordFieldsAndClickRegistration() {
        newPassRegForm().setValue(password);
        newPassConfirmRegForm().setValue(password);
        registrationBtnRegForm().click();
        return page(Profile_page.class);
    }

    @Step(":registration form. Main_page")
    public Main_page_logic checkingDatenschutzerklarungLinkBehaviorRegistrationForm() {
        new CommonMethods().checkingDatenschutzerklarungLinkBehavior(datenschutzerklarungLinkInRegPopup(), "underline solid rgb(50, 103, 214)");
        return this;
    }

    // Selector kba

    // This method only for DE
    @Step("Fill in KBA fields")
    public Main_page_logic fillNumberKba(String numberForFirstField, String numberForSecondField) {
        firstFieldKBA().setValue(numberForFirstField);
        secondFieldKBA().setValue(numberForSecondField);
        return this;
    }

    // This method for all shop, except DE
    @Step("Fill in KBA field")
    public Main_page_logic fillNumberKba(String kbaNumber) {
        firstFieldKBA().setValue(kbaNumber);
        return this;
    }

    @Step("Click search KBA button")
    public Catalog_page clickKbaBtn() {
        selectorKbaBtn().click();
        return page(Catalog_page.class);
    }

    @Step("Click link \"Was ist eine Schlüsselnummer?\" and check appears info KBA popup")
    public Main_page_logic clickLinkAndCheckAppearsInfoKbaPopup() {
        arrowInBrandSelectorVerticalCar().waitUntil(visible, 30000);
        linkInfoKba().click();
        kbaPopup().shouldBe(visible);
        return this;
    }

    // Car selector popup
    @Step("Choose brand in car selector popup")
    public Main_page_logic chooseBrandInCarSelectorPopup(String brandName) {
        brandSelectorInCarSelectorPopup().selectOption(brandName);
        Wait().until(webDriver -> brandSelectorInCarSelectorPopup().getSelectedText().equals(brandName));
        return this;
    }

    @Step("Choose model in car selector popup")
    public Main_page_logic chooseModelInPopupSelectorForChooseCar(String modelNumberValue) {
        modelSelectorInCarSelectorPopup().selectOptionByValue(modelNumberValue);
        return this;
    }

    @Step("Click reset button in car selector popup")
    public Main_page_logic resetCarSelectorPopup() {
        resetCarBtnInCarSelectorPopup().click();
        resetCarBtnInCarSelectorPopup().shouldBe(not(visible));
        return this;
    }

    // Vertical car selector popup

    // The method needed for pages where the vertical car selector is hidden by default
    @Step("Open vertical car selector if it hidden")
    public Main_page_logic openVerticalCarSelectorIfItHidden() {
        if (!brandSelectorInVerticalCarSelector().isDisplayed()) {
            hiddenVerticalSelector().click();
        }
        return this;
    }

    @Step("Choose brand in vertical car selector")
    public Main_page_logic chooseBrandInVerticalCarSelector(String brandName) {
        openVerticalCarSelectorIfItHidden();
        brandSelectorInVerticalCarSelector().selectOption(brandName);
        Wait().until(webDriver -> brandSelectorInVerticalCarSelector().getSelectedText().equals(brandName));
        return this;
    }

    @Step("Choose model in vertical car selector")
    public Main_page_logic chooseModelInVerticalCarSelector(String modelNumberValue) {
        modelSelectorInVerticalCarSelector().selectOptionByValue(modelNumberValue);
        sleep(1500);
        return this;
    }

    @Step("Choose type in vertical car selector")
    private Main_page_logic chooseTypeInVerticalCarSelector(String typeNumberValue) {
        typeSelectorInVerticalCarSelector().selectOptionByValue(typeNumberValue);
        return this;
    }

    @Step("Choose brand, model, type in vertical car selector")
    public Main_page_logic chooseBrandModelTypeInSelector(String brandName, String modelNumberValue, String typeNumberValue) {
        chooseBrandInVerticalCarSelector(brandName);
        chooseModelInVerticalCarSelector(modelNumberValue);
        chooseTypeInVerticalCarSelector(typeNumberValue);
        return this;
    }

    @Step("Click reset button in vertical car selector")
    public Main_page_logic resetVerticalCarSelector() {
        resetBtnInVerticalCarSelector().click();
        resetBtnInVerticalCarSelector().shouldBe(not(visible));
        return this;
    }

    @Step("Click search button in vertical car selector when NOT selected all fields")
    public Main_page_logic clickSearchBtnInVerticalSelectorWhenNotSelectedFields() {
        searchBtnInVerticalSelector().click();
        return this;
    }

    @Step("Click search button in vertical car selector when SELECTED all fields, for redirect to catalog page")
    public Catalog_page clickSearchBtnInVerticalSelectorWhenSelectedAllFields() {
        searchBtnInVerticalSelector().click();
        return page(Catalog_page.class);
    }


    // GDPR footer
    public Main_page_logic scrollToFooterSubscribeBlock() {
        footerForm().scrollTo();
        footerForm().shouldBe(Condition.visible);
        return this;
    }

    public Main_page_logic checkingDatenschutzerklarungLinkBehaviorInReviewsForm() {
        new CommonMethods().checkingDatenschutzerklarungLinkBehavior(clickDatenschutzInSubscribeBlock(), "none solid rgb(0, 104, 215)");
        return this;
    }

    public Main_page_logic checkingErrorPopupUnclickCheckbox(String qc) {
        String mail = qc + mailRandom();
        subscriptionMailField().setValue(mail);
        subscriptionButton().click();
        subscriptionErrPopup().shouldHave(Condition.text("Um fortzufahren bestätigen Sie bitte Ihr Newsletter-Abo"));
        subscriptionPopupClose().click();
        return this;
    }

    public String checkingErrorPopupClickCheckbox(String qc) {
        String mail = qc + mailRandom();
        subscriptionMailField().setValue(mail);
        subscriptionMailCheckbox().click();
        subscriptionButton().click();
        subscriptionSuccessPopup().shouldHave(Condition.text("Herzlichen Dank! Viel Spaß beim Shoppen!"));
        subscriptionPopupClose().click();
        return mail;
    }

}
