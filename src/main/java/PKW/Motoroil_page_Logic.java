package PKW;

import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.visible;

public class Motoroil_page_Logic extends Motoroil_page {

@Step("presence of brands block. Motoroil_page")
    public Motoroil_page_Logic presenceOfBrandsBlock() {
    brandsBlock().shouldBe(visible);
    return this;
}

}
