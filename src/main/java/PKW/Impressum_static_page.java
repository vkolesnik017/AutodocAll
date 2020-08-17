package PKW;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selectors.byXpath;
import static com.codeborne.selenide.Selenide.$;

public class Impressum_static_page {

    SelenideElement blockImpressumtext() {
        return $(byXpath("//*[@class='agb_text']"));
    }

    SelenideElement contactSidebar() {
        return $(byXpath("//*[@class='sidebar text']"));
    }

    SelenideElement pkwSiteLink() {
        return $(byXpath("//*[@class='agb_text']//p[2]//a[2]"));
    }

    SelenideElement atdSiteLink() {
        return $(byXpath("//*[@class='agb_text']//p[4]//a[2]"));
    }

    SelenideElement pkwEmail() {
        return $(byXpath("//*[@class='agb_text']//p[2]//a[1]"));
    }

    SelenideElement atdEmail() {
        return $(byXpath("//*[@class='agb_text']//p[4]//a[1]"));
    }

    SelenideElement osTextLink() {
        return $(byXpath("//*[@class='agb_text']//p[6]//a[1]"));
    }
}
