package PKW;

import io.qameta.allure.Step;
import static PKW.CommonMethods.checkingContainsUrl;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;

public class Zahlung_static_page_Logic extends Zahlung_static_page {

    @Step("Checks the clickable link, pdf download and acrobat reader buttons, text block visibility. Zahlung_static_page")
    public Zahlung_static_page_Logic checkElementsOnThePage() {

        CommonMethods commonMethods = new CommonMethods();

        blockZahlungText().shouldBe(visible);
        pdfDownloadButton().click();
        acrobatReaderButton().click();
        switchTo().window(1);
        checkingContainsUrl("/reader/?loc=de");
        closeWindow();
        switchTo().window(0);

        payPalLinkZahlung().click();
        commonMethods.checkingUrlAndCloseTab("/de/home/");
        payPalLinkZahlungSecond().click();
        commonMethods.checkingUrlAndCloseTab("/de/home/");
        sofortLinkZahlung().click();
        commonMethods.checkingUrlAndCloseTab("/sofort/");
        klarnaLinkZahlung().click();
        commonMethods.checkingUrlAndCloseTab("/legal/terms/27506/de_de/invoice?fee=0");
        datenschultzLinkZahlung().click();
        commonMethods.checkingUrlAndCloseTab("/terms/27506/de_de/consent");
        zollLinkZahlung().click();
        commonMethods.checkingUrlAndCloseTab("/Zollkosten/zollkosten_node.html");
        return this;
    }
}
