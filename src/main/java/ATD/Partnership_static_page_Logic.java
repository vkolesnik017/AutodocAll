package ATD;

import io.qameta.allure.Step;

import static ATD.CommonMethods.clickable;
import static ATD.CommonMethods.mailRandom;
import static com.codeborne.selenide.Condition.appear;
import static com.codeborne.selenide.Condition.visible;

public class Partnership_static_page_Logic extends Partnership_static_page {

    @Step("Scrolling ang check appearing of send ship form. Partnership_static_page")
    public Partnership_static_page_Logic scrollToSendShipForm() {
        sendShipForm().scrollTo();
        sendShipForm().shouldBe(visible);
        return this;
    }

    @Step(":in Partnership_static_page")
    public Partnership_static_page_Logic checkingDatenschutzerklarungLinkBehavior() {
        new CommonMethods().checkingDatenschutzerklarungLinkBehavior(datenschutzerklarungLink(), "underline solid rgb(0, 0, 0)");
        return this;
    }

    @Step("Filling fields and check behavior send ship form. Partnership_static_page")
    public String fillingFieldsAndCheckBehaviorSendShipForm() {
        String mail = "qc519_" + mailRandom();
        sendShipFormMailField().setValue(mail);
        submitShipDataButton().click();
        sendShipFormSuccesPopup().shouldBe(appear);
        sendShipFormSuccesPopupCloseBtn().click();
        return mail;
    }

    @Step("Checks items at the top of the Partnership pages. Partnership_static_page")
    public Partnership_static_page_Logic checkPageElements() {
        mainLogo().shouldBe(visible).shouldBe(clickable);
        title().shouldBe(visible);
        logoAboveText().shouldBe(visible);
        mainTextFromPage().shouldBe(visible);
        firstBlockOfAchievements().shouldBe(visible);
        secondBlockOfAchievements().shouldBe(visible);
        offerTitle().shouldBe(visible);
        leftBlockWithOffer().shouldBe(visible);
        imageOfLeftBlockWithOffer().shouldBe(visible);
        rightBlockWithOffer().shouldBe(visible);
        imageOfRightBlockWithOffer().shouldBe(visible);
        return this;
    }

    @Step("Checks for Add partner block and elements inside it. Partnership_static_page")
    public Partnership_static_page_Logic checkAddPartnerBlock() {
        addPartnerBlock().shouldBe(visible);
        mapText().shouldBe(visible);
        sendShipForm().shouldBe(visible);
        submitShipDataButton().click();
        emailError().shouldBe(visible);
        datenschutzerklarungLink().shouldBe(visible).shouldBe(clickable);
        acceptSubscriptionCheckbox().shouldBe(visible);
        return this;
    }
}
