package ATD;


import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;

public class FAQ_Confirmation_page {

    private SelenideElement faqConfirmationMessge() { return $(".icon_faq > p"); }

    public void checkFaqConfirmationMessage() {
        faqConfirmationMessge().shouldHave(text("Sie haben Ihre E-Mail-Adresse bestätigt. Nach der Bearbeitung, erhalten Sie eine Benachrichtigung via E-Mail."));
    }
}
