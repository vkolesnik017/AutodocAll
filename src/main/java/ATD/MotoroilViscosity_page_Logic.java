package ATD;

import static com.codeborne.selenide.Condition.*;

import Common.DataBase;
import io.qameta.allure.Step;
import org.testng.Assert;

import java.sql.SQLException;

public class MotoroilViscosity_page_Logic extends MotoroilViscosity_page{

    @Step("Checks visibility of viscosity oil value in selector. MotoroilViscosity_page")
    public MotoroilViscosity_page_Logic checkVisibilityOfViscosityOilValueInSelector() {
        selectorViscosityBlock().scrollTo().shouldBe(visible);
        viscosityOilNameInSelector().shouldBe(visible).shouldHave(attribute("selected"));
        viscosityOilFilter().shouldBe(visible).shouldHave(text("SAE 5W-30"));
        subNameOnListing().shouldHave(text("5W-30"));
        return this;
    }

    @Step("check Engine Oil Properties Text. Motoroil_viscosity_page")
    public MotoroilViscosity_page_Logic checkEngineOilPropertiesText(String value) throws SQLException {
        String frontText = allEngineOilPropertiesText().shouldBe(visible).getText().replaceAll("\\W", "");
        String bdText = new DataBase("ATD").getTranslate("seo_text", "DE", value).replaceAll("\\W", "");
        Assert.assertEquals(frontText, bdText);
        return this;
    }

    @Step(": for MotoroilViscosity_page_Logic")
    public MotoroilViscosity_page_Logic checkPresenceSpecificProductInGrayBtnInLiterBlock(String idProduct) {
        new Motoroil_brand_page_Logic().checkPresenceSpecificProductInGrayBtnInLiterBlock(idProduct);
        return this;
    }

    @Step(": for MotoroilViscosity_page_Logic")
    public MotoroilViscosity_page_Logic checkAbsenceSpecificProductInGrayBtnInLiterBlock(String idProduct) {
        new Motoroil_brand_page_Logic().checkAbsenceSpecificProductInGrayBtnInLiterBlock(idProduct);
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

    @Step(": for MotoroilViscosity_page_Logic")
    public MotoroilViscosity_page_Logic checkAbsenceGrayBtnAtExpectedProduct(String idProduct) {
        new Listing_page_Logic().checkAbsenceGrayBtnAtExpectedProduct(idProduct);
        return this;
    }
}
