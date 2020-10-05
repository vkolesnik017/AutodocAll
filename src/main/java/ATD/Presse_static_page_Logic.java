package ATD;

import io.qameta.allure.Step;
import org.testng.Assert;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

import static com.codeborne.selenide.CollectionCondition.sizeGreaterThan;
import static com.codeborne.selenide.Condition.exist;
import static com.codeborne.selenide.Condition.visible;
import static org.testng.Assert.assertEquals;

public class Presse_static_page_Logic extends Presse_static_page {

    @Step("Checking the presence of the text in the blocks. Presse_static_page")
    public Presse_static_page_Logic checkingPresenceOfTheBlocks() {
        presseHeader().shouldBe(visible);
        presseHeaderTitle().shouldBe(visible);
        Assert.assertFalse(presseHeaderTitle().text().isEmpty());
        presseHeaderFirstText().shouldBe(visible);
        Assert.assertFalse(presseHeaderFirstText().text().isEmpty());
        presseHeaderSecondText().shouldBe(visible);
        Assert.assertFalse(presseHeaderFirstText().text().isEmpty());
        presseInfoBlock().shouldBe(visible, exist);
        presseInfoTitle().shouldBe(visible);
        Assert.assertFalse(presseInfoTitle().text().isEmpty());
        presseInfoText().shouldBe(visible);
        Assert.assertFalse(presseInfoTitle().text().isEmpty());
        presseFirstPersonBlock().shouldBe(visible, exist);
        presseSecondPersonBlock().shouldBe(visible, exist);
        return this;
    }

    @Step("Open the Articles block. Presse_static_page")
    public Presse_static_page_Logic openTheArticleBlock() {
        presseContentBlock().shouldBe(visible, exist);
        atdHilft().shouldBe(visible, exist);
        int amountCount;
        while (mehrButton().isDisplayed()) {
            amountCount = artTitle().size();
            mehrButton().scrollIntoView("{block: \"center\"}");
            mehrButton().click();
            artTitle().shouldHave(sizeGreaterThan(amountCount));
        }
        return this;
    }

    @Step("Open the Articles block. Presse_static_page")
    public Presse_static_page_Logic checkingTheTexts() {
        for (int i = 0; i < articleTexts().size(); i++) {
            articleTexts().get(i).shouldBe(visible);
            Assert.assertFalse(articleTexts().get(i).text().isEmpty());
        }
        return this;
    }

    @Step("Gets the status of the photo code. Presse_static_page")
    public Presse_static_page_Logic getStatusPhotoCod() throws IOException {
        if (pressePhotos().isDisplayed()) {
            String linkInsideImage = pressePhotos().getAttribute("src");
            URL url = new URL(linkInsideImage);
            HttpURLConnection http = (HttpURLConnection) url.openConnection();
            http.setInstanceFollowRedirects(true);
            int responseCode = http.getResponseCode();
            assertEquals(responseCode, 200);
        }
        return this;
    }

    @Step("Gets the status of the image code. Presse_static_page")
    public Presse_static_page_Logic getStatusImageCod() throws IOException {
        if (atdHilftImage().isDisplayed()) {
            String linkInsideImage = atdHilftImage().getAttribute("src");
            URL url = new URL(linkInsideImage);
            HttpURLConnection http = (HttpURLConnection) url.openConnection();
            http.setInstanceFollowRedirects(true);
            int responseCode = http.getResponseCode();
            assertEquals(responseCode, 200);
        }
        return this;
    }
}





