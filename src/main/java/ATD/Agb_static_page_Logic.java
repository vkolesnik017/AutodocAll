package ATD;


import io.qameta.allure.Step;

import static ATD.CommonMethods.clickable;
import static com.codeborne.selenide.Condition.*;

public class Agb_static_page_Logic extends Agb_static_page {

    @Step("Checks element in the page Agb. Agb_static_page")
    public Agb_static_page_Logic checkElementPage(){
        logo().shouldBe(visible);
        titlePage().shouldBe(visible);
        mainBlock().shouldBe(visible);
        return this;
    }

    @Step("Checks clickable links. Agb_static_page")
    public Agb_static_page_Logic checkClickableLinks() {
        for (int i = 0; i < linksFromPage().size(); i++) {
            linksFromPage().get(i).shouldBe(clickable);
        }
        return this;
    }
}
