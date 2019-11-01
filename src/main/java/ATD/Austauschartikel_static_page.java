package ATD;


import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;

class Austauschartikel_static_page {

    SelenideElement logo() {
        return $(By.cssSelector(".logo>a>img"));
    }

    SelenideElement title() {
        return $(By.xpath("//div[@class='phand-about__title']"));
    }

    SelenideElement mainText() { return $(By.xpath("//div[@class='phand-about__text']")); }

    SelenideElement ausLogo() { return $(By.xpath("//div[@class='phand-about__price']")); }

    SelenideElement title2() { return $(By.xpath("//div[@class='return-rules__title']")); }

    SelenideElement instruction() { return $(By.xpath("//div[@class='instruction']")); }

    SelenideElement mainImage() { return $(By.xpath("//img[@class='return-rules__infograph-img']")); }

    SelenideElement returnPolicy() { return $(By.cssSelector(".return-policy")); }

    SelenideElement formLink() { return $(By.cssSelector(".anchor-on-form")); }

    SelenideElement plzInput() { return $(By.xpath("//*[@name='index']")); }
}
