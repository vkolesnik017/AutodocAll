package PKW;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;


public class Versand_static_page_Logic extends Versand_static_page{


    @Step("Checking gdpr success with click checkbox in footer subscribe block. Versand_static_page")
    public String checkingGdprSuccessWithClickCheckboxInFooter(String qc) {
        String mail = qc + PKW.CommonMethods.mailRandom();
        subscriptionMailField().setValue(mail);
        subscriptionMailCheckbox().click();
        subscriptionButton().click();
        subscriptionSuccessPopup().shouldHave(text("Viel Spaß beim Shoppen!"));
        subscriptionPopupClose().click();
        return mail;
    }

    @Step(":From Versand_static_page")
    public Versand_static_page_Logic checkingDatenschutzerklarungLinkBehavior(SelenideElement orderDatenschutzerklarungLink) {
        new CommonMethods().checkingDatenschutzerklarungLinkBehavior(orderDatenschutzerklarungLink, "underline solid rgb(0, 0, 0)");
        return this;
    }

    @Step("Scrolling to footer subscribe block. Versand_static_page")
    public Versand_static_page_Logic scrollToFooterSubscribeBlock() {
        footerForm().scrollIntoView(false);
        footerForm().shouldBe(visible);
        return this;
    }



}
