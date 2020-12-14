package ATD;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.*;

class About_us_static_page {


    SelenideElement imageTwelve() {
        return $(By.xpath("//*[@class='about-10years__10']//img"));
    }

    SelenideElement textOnTheImage() {
        return $(By.xpath("//*[@class='about-10years__autodoc']//span"));
    }

    SelenideElement textOnTheImageSecond() {
        return $(By.xpath("//*[@class='about-10years__bold']"));
    }

    ElementsCollection numbersOnTheBlock() {
        return $$(By.xpath("//*[@class='about-10years__num']"));
    }

    ElementsCollection textOnTheBlock() {
        return $$(By.xpath("//*[@class='about-10years__name']"));
    }

    SelenideElement numberWithPlus() {
        return $(By.xpath("//*[@class='about-10years__num ico-pls']"));
    }

    SelenideElement textBlockTitle() {
        return $(By.xpath("//*[@class='about-10years__title']"));
    }

    SelenideElement textBlockText() {
        return $(By.xpath("//*[@class='about-10years__text']"));
    }

    SelenideElement bigTitle() {
        return $(By.xpath("//*[@class='our-advantages__head']"));
    }

    ElementsCollection sixTextTitles() {
        return $$x("//*[@class='our-advantages__name']");
    }

    ElementsCollection fiveTextBlocks() {
        return $$x("//*[@class='our-advantages__text']");
    }

    SelenideElement oneTextBlock() {
        return $(By.xpath("//*[@class='our-advantages__text our-advantages__text--auto']"));
    }

    SelenideElement firstIcon() {
        return $(By.xpath("//*[@class='our-advantages__item ico-assort']"));
    }

    SelenideElement secondIcon() {
        return $(By.xpath("//*[@class='our-advantages__item ico-supp']"));
    }

    SelenideElement thirdIcon() {
        return $(By.xpath("//*[@class='our-advantages__item ico-choice']"));
    }

    SelenideElement fourthIcon() {
        return $(By.xpath("//*[@class='our-advantages__item ico-quality']"));
    }

    SelenideElement fifthIcon() {
        return $(By.xpath("//*[@class='our-advantages__item ico-lowcost']"));
    }

    SelenideElement sixthIcon() {
        return $(By.xpath("//*[@class='our-advantages__item ico-app']"));
    }

    ElementsCollection iconsWithTitleAndTextBlock() {
        return $$x("//*[@class='our-advantages__list']//div");
    }

    SelenideElement bigDiscountImage() {
        return $(By.xpath("//*[@class='traffic-value__growth']"));
    }

    SelenideElement staticText() {
        return $(By.xpath("//*[@class='traffic-value__static-text']"));
    }

    ElementsCollection nineYears() {
        return $$x("//*[@class='traffic-value__year']");
    }

    ElementsCollection nineTraffic() {
        return $$x("//*[@class='traffic-value__count']");
    }
}
