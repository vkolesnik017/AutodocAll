package ATD;


import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;

class Agb_static_page {

    SelenideElement logo() {
        return $(By.cssSelector(".logo>a>img"));
    }

    SelenideElement mainBlock() {
        return $(By.cssSelector(".text_page.agb_page"));
    }

    SelenideElement mailLink() {
        return $(By.xpath("//*[@class='col']/p[2]/a[1]"));
    }

    SelenideElement atdLink() {
        return $(By.xpath("//*[@class='col']/p[2]/a[2]"));
    }

    SelenideElement zollLink() {
        return $(By.xpath("//*[@class='col']/p[42]/a"));
    }

    SelenideElement klarnaLink() {
        return $(By.xpath("//*[@class='col']/p[61]/a"));
    }

    SelenideElement klarnaLink2() {
        return $(By.xpath("//*[@class='col']/p[63]/a"));
    }

    SelenideElement euLink() { return $(By.xpath("//*[@class='col right']/p[36]/a")); }
}
