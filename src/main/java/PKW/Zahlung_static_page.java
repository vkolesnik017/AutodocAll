package PKW;

import com.codeborne.selenide.SelenideElement;
import static com.codeborne.selenide.Selectors.byXpath;
import static com.codeborne.selenide.Selenide.*;

public class Zahlung_static_page {

    SelenideElement blockZahlungText() {
        return $(byXpath("//*[@class='agb_text']"));
    }

    SelenideElement pdfDownloadButton() {
        return $(byXpath("//ul[@class='link_agb']/li[2]//span[@class='link']"));
    }

    SelenideElement acrobatReaderButton() {
        return $(byXpath("//ul[@class='link_agb']/li[3]//span[@class='link']"));
    }

    SelenideElement payPalLinkZahlung() {
        return $(byXpath("//*[@class='agb_text']//a"));
    }

    SelenideElement payPalLinkZahlungSecond() {
        return $(byXpath("//*[@class='agb_text']//a[2]"));
    }

    SelenideElement sofortLinkZahlung() {
        return $(byXpath("//*[@class='agb_text']//a[3]"));
    }

    SelenideElement klarnaLinkZahlung() {
        return $(byXpath("//*[@class='agb_text']//a[4]"));
    }

    SelenideElement datenschultzLinkZahlung() {
        return $(byXpath("//*[@class='agb_text']//a[5]"));
    }

    SelenideElement zollLinkZahlung() {
        return $(byXpath("//*[@class='agb_text']//a[6]"));
    }
}

