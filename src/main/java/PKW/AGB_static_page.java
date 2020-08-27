package PKW;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selectors.byXpath;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$x;

public class AGB_static_page {

    SelenideElement blockAGBText() {
        return $(byXpath("//*[@class='agb_text']"));
    }

    SelenideElement pdfDownloadButton() {
        return $(byXpath("//ul[@class='link_agb']/li[2]//span[@class='link']"));
    }

    SelenideElement acrobatReaderButton() {
        return $(byXpath("//ul[@class='link_agb']/li[3]//span[@class='link']"));
    }

    SelenideElement countryListBlockAgb() {
        return $(byXpath("//*[@class='country_list']"));
    }

    ElementsCollection imagesCountryFlag() {
        return $$x("//*[@class='flag']//img");
    }

    ElementsCollection countryPrice() {
        return $$x("//*[@class='price']");
    }

    SelenideElement autodocEmail() {
        return $(byXpath("//*[@class='flag']"));
    }

    SelenideElement countryPriceOne() {
        return $(byXpath("//*[@class='flag']"));
    }

    SelenideElement autodocLinkAgbText() {
        return $(byXpath("//*[@class='text']//a[2]")); }

    SelenideElement zollLinkAgb() {
        return $(byXpath("//a[contains(@href,'zollkosten')]"));
    }

    SelenideElement klarnaLinkAgbFirst() {
        return $(byXpath("//a[contains(@href,'invoice')]"));
    }

    SelenideElement klarnaLinkAgbSecond() {
        return $(byXpath("//a[contains(@href,'consent')]"));
    }

    SelenideElement europaLinkAgbSecond() {
        return $(byXpath("//a[contains(@href,'europa')]"));
    }

//    ElementsCollection countryText() {
//        return $$x("//tr[@class='color']");
//    }
//
//    SelenideElement countryTextOne() {
//        return $(byXpath("//tr[@class='color']//td"));
//    }

    SelenideElement deliveryTimeBlockAgb() {
        return $(byXpath("//*[@class='delivery_time']"));
    }
//
//    SelenideElement autodocLinkAgbText() {
//        return $(byXpath("//*[@class='text']//a[2]"));
}

    //*[@class='text']//a[2]

