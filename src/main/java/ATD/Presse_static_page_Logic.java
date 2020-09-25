package ATD;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.testng.Assert;

import java.util.ArrayList;
import java.util.List;

import static com.codeborne.selenide.CollectionCondition.sizeGreaterThan;
import static com.codeborne.selenide.Condition.exist;
import static com.codeborne.selenide.Condition.visible;

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

    @Step("checking the image on the page. Presse_static_page")
    public Presse_static_page_Logic checkingTheImageOnThePage() {
        List<SelenideElement> locatorsImage = new ArrayList<>();
        locatorsImage.add(new Presse_static_page().atdHilftImage());
        locatorsImage.add(new Presse_static_page().presseFirstPhoto());
        locatorsImage.add(new Presse_static_page().presseSecondPhoto());
        return this;
    }
}




