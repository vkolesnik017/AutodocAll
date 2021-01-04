package ATD;

import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Condition.text;

public class MotoroilBrand_page_Logic extends MotoroilBrand_page {

    @Step("Checks visibility of viscosity oil value in selector. MotoroilBrand_page")
    public MotoroilBrand_page_Logic checkVisibilityOfBrandNameOilValueInSelectorAndListingName(String nameBrand) {
        oilSelector().scrollTo().shouldBe(visible);
        nameOilInSelector().shouldBe(visible).shouldHave(attribute("selected"));
        brandNameInListing().shouldHave(text(nameBrand));
        return this;
    }
}
