package PKW;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import static PKW.CommonMethods.checkingContainsUrl;
import static com.codeborne.selenide.CollectionCondition.*;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;

public class Zahlung_static_page_Logic extends Zahlung_static_page {

    @Step("Checks the elements. Zahlung_static_page")
    public Zahlung_static_page_Logic checkElementsOnThePage() {
        blockZahlungText().shouldBe(visible);
        pdfDownloadButton().click();
        acrobatReaderButton().click();
        switchTo().window(1);
        checkingContainsUrl("/reader/?loc=de");
        closeWindow();
        switchTo().window(0);

        SelenideElement logoForAnotherSites = $x("//*[contains(@class,'logo')]");
        linksZahlungPage().shouldHave(size(7));

        for (int i = 0; i < linksZahlungPage().size(); i++) {
            linksZahlungPage().get(i).click();
            switchTo().window(1);
            logoForAnotherSites.shouldBe(visible);
            closeWindow();
            switchTo().window(0);
        }
        return this;
    }
}
