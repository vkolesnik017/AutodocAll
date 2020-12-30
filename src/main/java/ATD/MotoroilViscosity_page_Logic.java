package ATD;

import static com.codeborne.selenide.Condition.*;
import io.qameta.allure.Step;

public class MotoroilViscosity_page_Logic extends MotoroilViscosity_page{

    @Step("Checks visibility of viscosity oil value in selector. MMotoroilViscosity_page")
    public MotoroilViscosity_page_Logic checkVisibilityOfViscosityOilValueInSelector() {
        selectorViscosityBlock().scrollTo().shouldBe(visible);
        viscosityOilNameInSelector().shouldBe(visible).shouldHave(attribute("selected"));
        viscosityOilFilter().shouldBe(visible).shouldHave(text("SAE 5W-30"));
        subNameOnListing().shouldHave(text("5W-30"));
        return this;
    }
}
