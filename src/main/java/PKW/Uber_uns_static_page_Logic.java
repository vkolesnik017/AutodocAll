package PKW;

import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.visible;

public class Uber_uns_static_page_Logic extends Uber_uns_static_page {
    @Step("Check the elements on the Uber Uns page. Uber_uns_static_page")
    public Uber_uns_static_page_Logic checkElementsOnThePage() {
        blockUberUns().shouldBe(visible);
        return this;
    }
}
