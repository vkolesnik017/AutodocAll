package PKW;

import io.qameta.allure.Step;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;


public class Car_parts_Logic extends Car_parts {

    @Step("Checking presence soft 404 block. Car_parts")
    public Car_parts_Logic checkingPresenceSoft404Block() {
        soft404Block().shouldBe(visible);
        return this;
    }


    @Step(":from Car_parts")
    public Car_parts_Logic checkingDatenschutzerklarungLinkBehaviorInSoft404Form() {
        new CommonMethods().checkingDatenschutzerklarungLinkBehavior(linkDatenschutzerklärungInSoft404Block(), "underline solid rgb(0, 0, 0)");
        return this;
    }

    @Step("Checking success popup with click checkbox in soft 404 block. Car_parts")
    public String checkingSuccessPopupClickCheckbox(String qc) {
        String mail = qc + PKW.CommonMethods.mailRandom();
        subscriptionMailFieldInSoft404Block().setValue(mail);
        subscriptionMailCheckboxInSoft404Block().click();
        subscriptionButtonInSoft404Block().click();
        subscriptionSuccessPopup().shouldHave(text("Mit großer Freude informieren wir Sie, sobald die Produkte auf Lager sind"));
        subscriptionPopupClose().click();
        return mail;
    }



}