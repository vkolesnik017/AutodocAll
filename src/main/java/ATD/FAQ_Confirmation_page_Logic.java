package ATD;


import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.text;

public class FAQ_Confirmation_page_Logic extends FAQ_Confirmation_page {

    @Step("Checking Faq confirmation message. FAQ_Confirmation_page")
    public void checkFaqConfirmationMessage() {
        faqConfirmationMessge().shouldHave(text("Sie haben Ihre E-Mail-Adresse bestätigt. Nach der Bearbeitung, erhalten Sie eine Benachrichtigung via E-Mail."));
    }
}
