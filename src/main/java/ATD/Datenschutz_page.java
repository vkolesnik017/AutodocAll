package ATD;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;

public class Datenschutz_page {

    public SelenideElement titlePage() {
       return $(By.xpath("//div[@class='head_name']"));
    }

    public SelenideElement logo() {
        return $(By.cssSelector(".logo>a>img"));
    }

    public SelenideElement mainBlock() {
        return $(By.cssSelector(".text_page.agb_page"));
    }


}
