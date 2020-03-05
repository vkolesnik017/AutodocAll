package ATD;

import io.qameta.allure.Step;

import static ATD.CommonMethods.clickable;
import static com.codeborne.selenide.Condition.visible;

public class Datenschutz_page_Logic extends Datenschutz_page {

    @Step("Checks items on a page. Datenschutz_page")
    public Datenschutz_page_Logic checkItemsOnPage() {
        titlePage().shouldBe(visible);
        logo().shouldBe(visible);
        mainBlock().shouldBe(visible);
        return this;
    }

    @Step("Checks clickable links. Datenschutz_page")
    public Datenschutz_page_Logic checkClickableLinks() {
        for (int i = 0; i < links().size(); i++) {
            links().get(i).shouldBe(clickable);
        }
        return this;
    }
}
