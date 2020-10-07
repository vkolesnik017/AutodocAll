package ATD;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

class Presse_static_page {

    SelenideElement presseHeader() {
        return $(By.xpath("//*[@class='press-release__header']"));
    }

    SelenideElement presseHeaderTitle() {
        return $(By.xpath("//*[@class='press-release__header-title']"));
    }

    SelenideElement presseHeaderFirstText() {
        return $(By.xpath("//*[@class='press-release__header-advantage']"));
    }

    SelenideElement presseHeaderSecondText() {
        return $(By.xpath("//*[@class='press-release__header-text']//p"));
    }

    SelenideElement presseInfoBlock() {
        return $(By.xpath("//*[@class='press-inform']"));
    }

    SelenideElement presseInfoTitle() {
        return $(By.xpath("//*[@class='press-inform__title']"));
    }

    SelenideElement presseInfoText() {
        return $(By.xpath("//*[@class='press-inform__text']//p"));
    }

    SelenideElement presseContentBlock() {
        return $(By.xpath("//*[@class='press-content'][1]"));
    }

    SelenideElement presseFirstPersonBlock() {
        return $(By.xpath("//*[@class='press-inform__persone'][1]"));
    }

    SelenideElement pressePhotos() {
        return $(By.xpath("//*[@class='press-inform__persone']//div//img"));
    }

    SelenideElement presseSecondPersonBlock() {
        return $(By.xpath("//*[@class='press-inform__persone'][2]"));
    }

    SelenideElement mehrButton() {
        return $(By.xpath("//*[@class='press-content__more js-press-content__more'][1]"));
    }

    SelenideElement atdHilft() {
        return $(By.xpath("//*[@class='atd-hilft']"));
    }

    SelenideElement atdHilftImage() {
        return $(By.xpath("//*[@class='atd-hilft__img']//img"));
    }

    ElementsCollection articleTexts() {
        return $$(By.xpath("//*[@class='atd-hilft__text']//p"));
    }

    ElementsCollection articleTitle() {
        return $$(By.xpath("//*[@class='press-article__title']//a"));
    }

    ElementsCollection downloadPDF() {
        return $$(By.xpath("//*[@class='press-article__file press-article__file--pdf']"));
    }

    ElementsCollection downloadJPG() {
        return $$(By.xpath("//*[@class='press-article__file press-article__file--pdf']"));
    }

    ElementsCollection artTitle() {
        return $$(By.xpath("//*[@class='press-article__title']")).filter(visible);
    }


}

