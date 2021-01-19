package ATD;

import io.qameta.allure.Step;

import static com.codeborne.selenide.CollectionCondition.size;
import static com.codeborne.selenide.Condition.*;

public class Widerruf_static_page_Logic extends Widerruf_static_page {

    @Step("Checks items. Widerruf_static_page")
    public Widerruf_static_page_Logic checkItemsOnPage() {
        title().shouldBe(visible);
        mainBlock().shouldBe(visible);
        for (int i = 0; i < emailLinks().size(); i++) {
            emailLinks().get(i).shouldHave(attribute("href", "mailto:info@autodoc.de")).shouldHave(text("info@autodoc.de"));
        }
        emailLinks().shouldHave(size(2));
        return this;
    }
}
