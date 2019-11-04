package ATD;


import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;

public class Widerruf_static_page {

    public SelenideElement logo() {
        return $(By.cssSelector(".logo>a>img"));
    }

    public SelenideElement title() {
        return $(By.xpath("//div[@class='head_name']"));
    }

    public SelenideElement mainBlock() {
        return $(By.cssSelector(".text_page.agb_page"));
    }

    public SelenideElement emailLink1() {
        return $(By.xpath("//*[@class='serv-attention']/p[3]/a"));
    }

    public SelenideElement emailLink2() {
        return $(By.xpath("//*[@class='serv-attention']/p[16]/a"));
    }
}
