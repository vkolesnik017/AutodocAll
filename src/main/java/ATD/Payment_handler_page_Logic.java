package ATD;

import com.codeborne.selenide.ex.ElementNotFound;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.page;

public class Payment_handler_page_Logic extends Payment_handler_page {

    @Step("Closing popup after making order. Payment_handler_page")
    public Payment_handler_page_Logic closePopupAfterOrder() {
        try {
            popupAfterOrder().shouldBe(visible);
            closePopupAfterOrderBtn().click();
        } catch (ElementNotFound e) {
        }
        return this;
    }

    @Step("Getting order number. Payment_handler_page")
    public String getOrderNumber() {
        return orderNumber().getText();
    }

    @Step("Checks success text in header. Payment_handler_page")
    public Payment_handler_page_Logic checkSuccessTextInHeader() {
        successTextInHeader().shouldBe(visible);
        return this;
    }

    @Step(": for Payment_handler_page")
    public Profile_plus_page_Logic goToProfilePlusPage() {
        new Main_page_Logic().profilePlusBtnClickInHeader();
        return page(Profile_plus_page_Logic.class);
    }
}
