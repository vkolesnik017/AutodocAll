package PKW;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selectors.byXpath;
import static com.codeborne.selenide.Selenide.$;

public class FAQ_static_page {

    SelenideElement blockFaq() {
        return $(byXpath("//*[@class='faq_page_box']"));
    }

    SelenideElement faqAccordionButton() {
        return $(byXpath("//div[@class='box'][1]//li[1]/span"));
    }

    SelenideElement faqAccordionContent() {
        return $(byXpath("//div[@class='box'][1]//li[1]//p"));
    }
}
