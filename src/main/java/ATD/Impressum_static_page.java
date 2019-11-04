package ATD;


import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;

public class Impressum_static_page {
    public SelenideElement logo() {
        return $(By.cssSelector(".logo>a>img"));
    }

    public SelenideElement title() {
        return $(By.xpath("//div[@class='head_name']"));
    }

    public SelenideElement mainBlock() {
        return $(By.cssSelector(".text_page.agb_page"));
    }

    public SelenideElement emailLink() {
        return $(By.xpath("//*[@class='text_page agb_page']/p[4]/a[1]"));
    }

    public SelenideElement webLink() {
        return $(By.xpath("//*[@class='text_page agb_page']/p[4]/a[2]"));
    }

    public SelenideElement map() {
        return $(By.xpath("//*[@class='text_page agb_page']/p[5]"));
    }
}
