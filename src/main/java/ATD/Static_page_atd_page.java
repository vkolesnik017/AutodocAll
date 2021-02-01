package ATD;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$x;

public class Static_page_atd_page {

    SelenideElement helpingBlock() {
        return $(".atd-hilft");
    }

    SelenideElement pdfLinkInHelpingBLock() {
        return $(".hilft-article__files>span");
    }

    SelenideElement headlineOfHelpingPressBlock() {
        return $(".atd-hilft__header");
    }

    SelenideElement mainFormOfHelpingPressBlock() {
        return $(".atd-hilft__main");
    }

    SelenideElement articleBlockOfMainFormOfHelpingPressBlock() {
        return $(".atd-hilft__article");
    }

    SelenideElement covidImageOfMainFormOfHelpingPressBlock() {
        return $(".atd-hilft__aside");
    }

    SelenideElement textOfArticleOfHelpingPressBlock() {
        return $(".atd-hilft__text");
    }

    SelenideElement pdfBlockOfArticleOfHelpingPressBlock() {
        return $(".hilft-article");
    }

    SelenideElement imageOfPdfBlockOfHelpingPressBlock() {
        return $(".hilft-article__img");
    }

    SelenideElement contentOfPdfBlockOfHelpingPressBlock() {
        return $(".hilft-article__content");
    }

    SelenideElement headlineOfContentPdfBlock() {
        return $(".hilft-article__title");
    }

    ElementsCollection visiblePressLinks() {
        return $$x("//div[@class='slick-track']/div").filter(visible);
    }
}
