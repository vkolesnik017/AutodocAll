package ATD;


import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;

public class Widerruf_static_page {

    SelenideElement logo() {
        return $(By.cssSelector(".logo>a>img"));
    }

    SelenideElement title() {
        return $(By.xpath("//div[@class='head_name']"));
    }

    SelenideElement mainBlock() {
        return $(By.cssSelector(".text_page.agb_page"));
    }

    SelenideElement emailLink1() {
        return $(By.xpath("//*[@class='serv-attention']/p[3]/a"));
    }

    SelenideElement emailLink2() {
        return $(By.xpath("//*[@class='serv-attention']/p[16]/a"));
    }
}
