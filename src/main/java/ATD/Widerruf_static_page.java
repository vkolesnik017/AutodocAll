package ATD;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

class Widerruf_static_page {

    SelenideElement logo() {
        return $(By.cssSelector(".logo>a>img"));
    }

    SelenideElement title() {
        return $(By.xpath("//div[@class='head_name']"));
    }

    SelenideElement mainBlock() {
        return $(By.cssSelector(".text_page.agb_page"));
    }

    ElementsCollection emailLinks() {
        return $$(By.xpath("//*[@class='serv-attention']//a"));
    }
}
