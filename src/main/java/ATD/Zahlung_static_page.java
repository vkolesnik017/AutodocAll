package ATD;


import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;

class Zahlung_static_page {

    SelenideElement logo() {
        return $(By.cssSelector(".logo>a>img"));
    }

    SelenideElement title() {
        return $(By.cssSelector(".head_name"));
    }

    SelenideElement checkMainBlock() {
        return $(By.cssSelector(".text_page.agb_page"));
    }
}
