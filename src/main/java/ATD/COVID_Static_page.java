package ATD;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selectors.byXpath;
import static com.codeborne.selenide.Selenide.*;


public class COVID_Static_page {

    SelenideElement covidTitle() {
        return $(byXpath("//*[@class='head_name']"));
    }

    ElementsCollection covidQuestionsBlock() {
        return $$(byXpath("//*[@class='text_page agb_page']//p"));
    }
}
