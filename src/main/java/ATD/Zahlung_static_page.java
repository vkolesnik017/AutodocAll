package ATD;


import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;

public class Zahlung_static_page {

    protected SelenideElement logo() {
        return $(By.cssSelector(".logo>a>img"));
    }

    protected SelenideElement title() {
        return $(By.cssSelector(".head_name"));
    }

    protected SelenideElement checkMainBlock() {
        return $(By.cssSelector(".text_page.agb_page"));
    }
}
