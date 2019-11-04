package ATD;


import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;

public class Zahlung_static_page {

    public SelenideElement logo() {
        return $(By.cssSelector(".logo>a>img"));
    }

    public SelenideElement title() {
        return $(By.cssSelector(".head_name"));
    }

    public SelenideElement checkMainBlock() {
        return $(By.cssSelector(".text_page.agb_page"));
    }
}
