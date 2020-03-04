package ATD;


import io.qameta.allure.Step;

import static ATD.CommonMethods.clickable;
import static com.codeborne.selenide.Condition.*;

public class Impressum_static_page_Logic extends Impressum_static_page {

    @Step("Checks for items. Impressum_static_page")
    public Impressum_static_page_Logic checkItemsOnPage(){
        logo().shouldBe(visible);
        title().shouldBe(visible);
        mainBlock().shouldBe(visible);
        emailLink().shouldBe(clickable);
        webLink().shouldBe(clickable);
        map().shouldBe(visible);
        return this;
    }
}
