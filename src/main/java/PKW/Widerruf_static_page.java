package PKW;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selectors.byXpath;
import static com.codeborne.selenide.Selenide.$;

public class Widerruf_static_page {

    SelenideElement blockWiderrufText() {
        return $(byXpath("//*[@class='agb_text']"));
    }

    SelenideElement pdfDownloadButton() {
        return $(byXpath("//ul[@class='link_agb']/li[2]//span[@class='link']"));
    }

    SelenideElement acrobatReaderButton() {
        return $(byXpath("//ul[@class='link_agb']/li[3]//span[@class='link']"));
    }

    SelenideElement autodocEmailLink() {
        return $(byXpath("//*[@class='serv-attention']/p[4]//a"));
    }

    SelenideElement autodocEmailLinkSecond() {
        return $(byXpath("//*[@class='serv-attention']/p[17]//a"));
    }
}
