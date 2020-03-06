package ATD;


import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.text;

public class Reviews_Confirmation_page_Logic extends Reviews_Confirmation_page {

    @Step("Checking reviews confirmation message. Reviews_Confirmation_page")
    public void checkReviewsConfirmationMessage() {
        reviewsConfirmationMessge().shouldHave(text("Sie haben Ihre E-Mail-Adresse bestätigt. Nach der Bearbeitung, erhalten Sie eine Benachrichtigung via E-Mail."));
    }
}
