package ATD;

import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.*;

public class Index_instruments_page_Logic extends Index_instruments_page {

    @Step("Checks presence main title on page tools. Index_instruments_page")
    public Index_instruments_page_Logic checkPresenceMainTitle() {
        titleMainPage().shouldBe(visible);
        return this;
    }
}
