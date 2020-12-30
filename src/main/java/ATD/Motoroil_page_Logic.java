package ATD;

import io.qameta.allure.Step;
import org.testng.Assert;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Objects;

import static ATD.CommonMethods.checkingContainsUrl;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.back;
import static com.codeborne.selenide.Selenide.page;
import static org.testng.Assert.assertEquals;

public class Motoroil_page_Logic extends Motoroil_page {
    @Step("Check car selectors Block presence. Motoroil page")
    public Motoroil_page_Logic checkCarSelectorPresence() {
        carSelector().shouldBe(visible);
        return this;
    }

    @Step("Check oil selector block presence. Motoroil page")
    public Motoroil_page_Logic checkOilSelectorPresence() {
        oilSelector().shouldBe(visible);
        return this;
    }

    @Step("Check popular brands block presence. Motoroil page")
    public Motoroil_page_Logic checkPopularBrandsBlockPresence() {
        popularBrandBlock().shouldBe(visible);
        return this;
    }

    @Step("Check reviews block presence. Motoroil page")
    public Motoroil_page_Logic reviewsBlockPresence() {
        reviewsBlock().shouldBe(visible);
        return this;
    }

    @Step("Check seo block presence. Motoroil page")
    public Motoroil_page_Logic seoBlockPresence() {
        seoBlock().shouldBe(visible);
        Assert.assertFalse(seoBlock().text().isEmpty());
        return this;
    }

    @Step("Check kba info pop Up presence. Motoroil page")
    public Motoroil_page_Logic infoKbaPopUpPresence() {
        kbaInfoButton().shouldBe(visible).click();
        kbaInfoPopUp().shouldBe(visible).shouldHave(attribute("style", "display: block;"));
        kbaInfoPopUpCloseButton().shouldBe(visible).click();
        kbaInfoPopUp().shouldNotBe(visible);
        return this;
    }

    @Step("Check advantages block and elements here presence. Motoroil page")
    public Motoroil_page_Logic advantagesBlockPresence() throws IOException {
        advantagesBlock().shouldBe(visible);
        for (int i = 0; i < advantagesBlockItem().size(); i++) {
            advantagesBlockItem().get(i).shouldBe(visible);
            advantagesBlockIcon().get(i).shouldBe(visible);
            String linkInsideImage = advantagesBlockIcon().get(i).getAttribute("src");
            assert linkInsideImage != null;
            URL url = new URL(linkInsideImage);
            HttpURLConnection http = (HttpURLConnection) url.openConnection();
            http.setInstanceFollowRedirects(true);
            int responseCode = http.getResponseCode();
            assertEquals(responseCode, 200);
            advantagesBlockCount().get(i).shouldBe(visible);
            Assert.assertFalse(advantagesBlockCount().get(i).text().isEmpty());
            advantagesBlockTitle().get(i).shouldBe(visible);
            Assert.assertFalse(advantagesBlockTitle().get(i).text().isEmpty());
            advantagesBlockText().get(i).shouldBe(visible);
            Assert.assertFalse(advantagesBlockText().get(i).text().isEmpty());
        }
        return this;
    }

    @Step("Check oil viscosity block and elements here presence. Motoroil page")
    public Motoroil_page_Logic oilViscosityBlockPresence() {
        oilViscosityBlock().shouldBe(visible);
        for (int i = 0; i < oilViscosityItem().size(); i++) {
            oilViscosityIcon().get(i).shouldBe(visible);
            oilViscosityText().get(i).shouldBe(visible);
            Assert.assertFalse(oilViscosityText().get(i).text().isEmpty());
        }
        return this;
    }

    @Step("Check oil viscosity block functionality. Motoroil page")
    public MotoroilViscosity_page_Logic oilViscosityBlockFunctionality() {
        oilViscosityBlock().shouldBe(visible);
        for (int i = 0; i < oilViscosityItem().size(); i++) {
            String urlContains = oilViscosityItemLink().get(i).getAttribute("href").replaceAll("^.+\\/", "").replaceAll("\\W", " ");
            String textContains = oilViscosityText().get(i).getText().toLowerCase().replaceAll("\\W", " ");
            Assert.assertEquals(urlContains, textContains);
            oilViscosityItem().get(i).shouldBe(visible).click();
        }
        return page(MotoroilViscosity_page_Logic.class);
    }

    @Step("Check sending kba selector form with empty fields. Motoroil page")
    public Motoroil_page_Logic sendKbaSelectorFormEmptyFields() {
        kbaSelectorBlock().shouldBe(visible);
        kbaSelectorSendButton().click();
        kbaSelectorErrorMessageEmptyFields().shouldBe(visible);
        kbaSelectorErrorMessageEmptyFieldsIcon().shouldBe(visible);
        kbaSelectorErrorMessageEmptyFieldsText().shouldBe(visible).shouldHave(text("Geben Sie bitte eine Schlüsselnummer ein, um nach einem Wagen zu suchen"));
        return this;
    }

    @Step("Check sending kba selector form with invalid data. Motoroil page")
    public Motoroil_page_Logic sendKbaSelectorFormInvalidData() {
        kbaSelectorBlock().shouldBe(visible);
        kbaSelectorFirstInput().sendKeys("64F");
        kbaSelectorSecondInput().sendKeys("F");
        kbaSelectorSendButton().click();
        kbaSelectorErrorMessageEmptyFields().shouldBe(visible);
        kbaSelectorErrorMessageEmptyFieldsIcon().shouldBe(visible);
        kbaSelectorErrorMessageEmptyFieldsText().shouldBe(visible).shouldHave(text("Falsche Zeichennummer eingegeben"));
        return this;
    }

    @Step("Check presence of marks block on main oil page. Motoroil page")
    public Motoroil_page_Logic checkPresenceOfMarksBlock() {
        carListBlock().shouldBe(visible);
        mehrSchliebenButtonMarksBlock().shouldHave(text("Mehr")).click();
        schliebenButtonMarksBlock().shouldBe(visible);
        mehrSchliebenButtonMarksBlock().shouldHave(text("Schließen")).click();
        mehrButtonMarksBlock().shouldBe(visible);
        return this;
    }

    @Step("Check transfer from marks block to catalog. Motoroil page")
    public Motoroil_page_Logic checkTransferFromMarksBlockToCatalog() {
        carListBlock().shouldBe(visible);
        String titleOfMark = Objects.requireNonNull(carsMakerItem().getAttribute("title")).toLowerCase();
        carsMakerItem().shouldBe(visible).shouldHave(attribute("title","MERCEDES-BENZ")).click();
        checkingContainsUrl(titleOfMark);
        return this;
    }

    @Step("Check presence the TOP Block on hte Main Oil Page. Motoroil page")
    public Motoroil_page_Logic checkPresenceOfTopBlock() {
        topBlockOnMainOilPage().shouldBe(visible);
        for (int i = 0; i < topBlockItems().size(); i++) {
            topBlockItems().get(i).shouldBe(visible);
            topBlockItemsImage().get(i).shouldHave(text("Motoröl"));
        }
        return this;
    }

    @Step("Check transfer from the TOP Block on the Main Oil Page to product page. Motoroil page")
    public Motoroil_page_Logic checkTransferFromTopBlockToProductPage() {
        topBlockOnMainOilPage().shouldBe(visible);
        String idProduct = topBlockItem().getAttribute("data-product-id");
        topBlockItemFirst().scrollIntoView(true).hover();
        topBlockItemDetails().shouldBe(visible).click();
        checkingContainsUrl(idProduct);
        back();
        topBlockItemOneClick().shouldBe(visible).scrollIntoView("{block: \"center\"}").click();
        checkingContainsUrl(idProduct);
        return this;
    }
}
