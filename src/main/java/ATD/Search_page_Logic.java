package ATD;

import com.codeborne.selenide.Condition;
import io.qameta.allure.Step;

import static ATD.CommonMethods.waitWhileRouteBecomeExpected;
import static ATD.CommonMethods.waitingWhileLinkBecomeExpected;
import static com.codeborne.selenide.Selenide.page;
import static com.codeborne.selenide.WebDriverRunner.url;
import static org.testng.Assert.assertEquals;


public class Search_page_Logic extends Search_page {

    @Step("Verify name route equals search")
    public Search_page_Logic verifyNameRouteEqualsSearch() {
        waitWhileRouteBecomeExpected("search");
        return this;
    }

    @Step("Close popup of choose car")
    public Search_page_Logic closePopupOfChooseCar() {
        closeBtnPopupOfChooseCar().click();
        return this;
    }

    @Step("Switch on second page")
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


}

