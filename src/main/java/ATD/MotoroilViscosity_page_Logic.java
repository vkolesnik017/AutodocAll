package ATD;

import static com.codeborne.selenide.Condition.*;
import io.qameta.allure.Step;

public class MotoroilViscosity_page_Logic extends MotoroilViscosity_page{

    @Step("Checks visibility of viscosity oil value in selector. MotoroilViscosity_page")
    public MotoroilViscosity_page_Logic checkVisibilityOfViscosityOilValueInSelector() {
        selectorOil().scrollTo().shouldBe(visible);
        viscosityOilNameInSelector().shouldBe(visible).shouldHave(attribute("selected"));
        viscosityOilFilter().shouldBe(visible).shouldHave(text("SAE 5W-30"));
        subNameOnListing().shouldHave(text("5W-30"));
        return this;
    }

    @Step(": for MotoroilViscosity_page_Logic")
    public MotoroilViscosity_page_Logic checkPresenceSpecificProductInGrayBtnInLiterBlock(String idProduct) {
        new Motoroil_brand_page_Logic().checkPresenceSpecificProductInGrayBtnInLiterBlock(idProduct);
        return this;
    }

    @Step(": for MotoroilViscosity_page_Logic")
    public MotoroilViscosity_page_Logic checkAbsenceArticleNum(String expectedArtNum) {
        new Listing_page_Logic().checkAbsenceArticleNum(expectedArtNum);
        return this;
    }

    @Step(": for MotoroilViscosity_page_Logic")
    public MotoroilViscosity_page_Logic checkPresenceSpecificArticleNumOnListing(String expectedArtNum) {
        new Listing_page_Logic().checkPresenceSpecificArticleNumOnListing(expectedArtNum);
        return this;
    }

    @Step("Check presence oil selector. MotoroilViscosity_page")
    public MotoroilViscosity_page_Logic checkPresenceOilSelector() {
        selectorOil().shouldBe(visible);
        return this;
    }

}
