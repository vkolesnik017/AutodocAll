package PKW;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selectors.byXpath;
import static com.codeborne.selenide.Selenide.*;

public class Datenschutz_static_page {

    ElementsCollection linksOnThePage() {
        return $$x("//*[@class='agb_text']//a");
    }

    SelenideElement emailKlarna() {
        return $x("//*[@class='agb_text']//a[contains(text(),'@klarna')]");
    }

    SelenideElement emailTrustpilot() {
        return $x("//*[@class='agb_text']//a[contains(text(),'@trust')]");
    }

    ElementsCollection emailPkwLinks() {
        return $$x("//*[@class='agb_text']//a[contains(text(),'@pkwteile.de')]");
    }

    SelenideElement textBlock() {
        return $x("//*[@class='agb_text']");
    }

    SelenideElement pdfDownloadButton() {
        return $x("//ul[@class='link_agb']/li[2]//span[@class='link']");
    }

    SelenideElement acrobatReaderButton() {
        return $(byXpath("//ul[@class='link_agb']/li[3]//span[@class='link']"));
    }
}
