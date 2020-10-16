package PKW;

import io.qameta.allure.Step;
import static PKW.CommonMethods.checkingContainsUrl;
import static com.codeborne.selenide.Condition.attribute;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.switchTo;

public class Widerruf_static_page_Logic extends Widerruf_static_page {

    @Step("Checks the elements. Widerruf_static_page")
    public Widerruf_static_page_Logic checkElementsOnThePage() {
        blockWiderrufText().shouldBe(visible);
        autodocEmailLink().shouldHave(attribute("href"));
        autodocEmailLinkSecond().shouldHave(attribute("href"));
        pdfDownloadButton().click();
        acrobatReaderButton().click();
        switchTo().window(1);
        checkingContainsUrl("/reader/");
        return this;
    }
}
