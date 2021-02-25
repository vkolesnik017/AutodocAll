package ATD;

import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;

public class Accessories_listing_criteria_page_Logic extends Accessories_listing_criteria_page {


    @Step("check Headline of generics block . Accessories_listing_criteria_page")
    public Accessories_listing_criteria_page_Logic checkHeadlineOfGenericsBlock(String headline) {
        headlineOfGenericsBlock().shouldBe(visible).shouldHave(text(headline));
        return this;
    }
}
