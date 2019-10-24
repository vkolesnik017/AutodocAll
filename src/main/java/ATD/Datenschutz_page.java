package ATD;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;

class Datenschutz_page {

    SelenideElement titlePage() {
       return $(By.xpath("//div[@class='head_name']"));
    }

    SelenideElement logo() {
        return $(By.cssSelector(".logo>a>img"));
    }

    SelenideElement mainBlock() {
        return $(By.cssSelector(".text_page.agb_page"));
    }


}
