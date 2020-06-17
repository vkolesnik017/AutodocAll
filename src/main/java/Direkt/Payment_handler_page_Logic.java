package Direkt;

import com.codeborne.selenide.Condition;
import io.qameta.allure.Step;

public class Payment_handler_page_Logic extends Payment_handler_page {

    @Step("Checking order confirmation label. Payment_handler_page_Logic")
    public Payment_handler_page_Logic checkLabelConfirm() {
        confirmationLabel().shouldHave(Condition.text("Die Auftragsnummer lautet"));
        return this;
    }

}
