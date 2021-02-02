package ATD;

import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;

public class Static_page_atd_page_Logic extends Static_page_atd_page {

    @Step("presence of helping Autodoc block. Static_page_atd_page")
    public Static_page_atd_page_Logic presenceHelpingBlock() {
        helpingBlock().shouldBe(visible).scrollTo();
        headlineOfHelpingPressBlock().shouldBe(visible).shouldHave(text("Autodoc Hilft"));
        return this;
    }

    @Step("presence Of Main Form Of Helping Block. Static_page_atd_page")
    public Static_page_atd_page_Logic presenceOfMainFormOfHelpingBlock() {
        mainFormOfHelpingPressBlock().shouldBe(visible);
        articleBlockOfMainFormOfHelpingPressBlock().shouldBe(visible);
        covidImageOfMainFormOfHelpingPressBlock().shouldBe(visible);
        return this;
    }

    @Step("presence Of Article Block. Static_page_atd_page")
    public Static_page_atd_page_Logic presenceOfArticleBlock() {
        textOfArticleOfHelpingPressBlock().shouldBe(visible);
        pdfBlockOfArticleOfHelpingPressBlock().shouldBe(visible);
        return this;
    }

    @Step("check Pdf Block Of Article Block. Static_page_atd_page")
    public Static_page_atd_page_Logic checkPdfBlockOfArticleBlock() {
        imageOfPdfBlockOfHelpingPressBlock().shouldBe(visible);
        contentOfPdfBlockOfHelpingPressBlock().shouldBe(visible);
        return this;
    }

    @Step("check Content Block Of Article Pdf Block. Static_page_atd_page")
    public Static_page_atd_page_Logic checkContentBlockOfArticlePdfBlock() {
        headlineOfContentPdfBlock().shouldBe(visible).shouldHave(text("Initiative „Spenden für Corona“ - Unterstützung der Autodoc GmbH"));
        return this;
    }

    @Step("download Pdf In Helping Block. Static_page_atd_page")
    public Static_page_atd_page_Logic downloadPdfInHelpingBlock() {
        pdfLinkInHelpingBLock().click();
        return this;
    }

    @Step("presence Of visible Press Links. Static_page_atd_page")
    public Static_page_atd_page_Logic presenceOfVisiblePressLinks() {
        for (int i = 0; i < visiblePressLinks().size(); i++) {
            visiblePressLinks().get(i).shouldBe(visible);
        }
        return this;
    }
}
