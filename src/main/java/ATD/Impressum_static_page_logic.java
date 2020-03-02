package ATD;


import io.qameta.allure.Step;

import static ATD.CommonMethods.clickable;
import static com.codeborne.selenide.Condition.*;

public class Impressum_static_page_logic extends Impressum_static_page {

    @Step("Checks for items on the Impressum page ")
    public Impressum_static_page_logic checkItemsOnPage(){
        logo().shouldBe(visible);
        title().shouldBe(visible);
        mainBlock().shouldBe(visible);
        emailLink().shouldBe(clickable);
        webLink().shouldBe(clickable);
        map().shouldBe(visible);
        return this;
    }
}
