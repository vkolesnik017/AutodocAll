package ATD;


import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

class Presse_static_page {

    SelenideElement presseHeader() {
        return $(By.xpath("//*[@class='press-release__header']"));
    }

    SelenideElement presseInfoBlock() {
        return $(By.xpath("//*[@class='press-inform']"));
    }

    SelenideElement presseContentBlock() {
        return $(By.xpath("//*[@class='press-content'][1]"));
    }

    SelenideElement mehrButton() {
        return $(By.xpath("//*[@class='press-content__more js-press-content__more'][1]"));
    }

    SelenideElement atdHilft() {
        return $(By.xpath("//*[@class='atd-hilft']"));
    }

    ElementsCollection articleTitle() {
        return $$(By.xpath("//*[@class='press-article__title']"));
    }

    ElementsCollection downloadPDF() {
        return $$(By.xpath("//*[@class='press-article__file press-article__file--pdf']"));
    }

    ElementsCollection downloadJPG() {
        return $$(By.xpath("//*[@class='press-article__file press-article__file--pdf']"));
    }
}
