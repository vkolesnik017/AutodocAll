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
}
