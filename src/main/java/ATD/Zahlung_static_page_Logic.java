package ATD;

import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.visible;

public class Zahlung_static_page_Logic extends Zahlung_static_page {

    @Step("Checks the elements. Zahlung_static_page")
    public Zahlung_static_page_Logic checkElementsOnPage() {
        logo().shouldBe(visible);
        title().shouldBe(visible);
        checkMainBlock().shouldBe(visible);
        return this;
    }
}
