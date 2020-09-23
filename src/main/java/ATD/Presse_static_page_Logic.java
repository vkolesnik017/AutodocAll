package ATD;

import io.qameta.allure.Step;
import org.testng.Assert;

import static com.codeborne.selenide.Condition.visible;

public class Presse_static_page_Logic extends Presse_static_page {

    @Step("Open the Articles block. Presse_static_page")
    public Presse_static_page_Logic openTheArticleBlock() {
        while (mehrButton().isDisplayed()){
            mehrButton().scrollIntoView(false);
            mehrButton().click();
        }
        return this;
    }

    @Step("Checking the titles in the Article Block. Presse_static_page")
    public Presse_static_page_Logic checkingTheTitles() {
        for (int i = 0; i < articleTitle().size(); i++) {
            articleTitle().get(i).shouldBe(visible);
            Assert.assertFalse(articleTitle().get(i).text().isEmpty());
            articleTitle().get(i).click();

        }
        return this;
    }
}


