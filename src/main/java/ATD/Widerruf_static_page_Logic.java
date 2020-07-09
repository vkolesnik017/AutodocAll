package ATD;

import io.qameta.allure.Step;

import static ATD.CommonMethods.clickable;
import static com.codeborne.selenide.Condition.visible;

public class Widerruf_static_page_Logic extends Widerruf_static_page {

    @Step("Checks items. Widerruf_static_page")
    public Widerruf_static_page_Logic checkItemsOnPage(){
        title().shouldBe(visible);
        mainBlock().shouldBe(visible);
        emailLink1().shouldBe(clickable);
        emailLink2().shouldBe(clickable);
        return this;
    }
}
