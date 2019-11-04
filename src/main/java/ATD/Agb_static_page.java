package ATD;


import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;

public class Agb_static_page {

    public SelenideElement logo() {
        return $(By.cssSelector(".logo>a>img"));
    }

    public SelenideElement mainBlock() {
        return $(By.cssSelector(".text_page.agb_page"));
    }

    public SelenideElement mailLink() {
        return $(By.xpath("//*[@class='col']/p[2]/a[1]"));
    }

    public SelenideElement atdLink() {
        return $(By.xpath("//*[@class='col']/p[2]/a[2]"));
    }

    public SelenideElement zollLink() {
        return $(By.xpath("//*[@class='col']/p[42]/a"));
    }

    public SelenideElement klarnaLink() {
        return $(By.xpath("//*[@class='col']/p[61]/a"));
    }

    public SelenideElement klarnaLink2() {
        return $(By.xpath("//*[@class='col']/p[63]/a"));
    }

    public SelenideElement euLink() { return $(By.xpath("//*[@class='col right']/p[36]/a")); }
}
