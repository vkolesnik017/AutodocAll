package PKW;

import io.qameta.allure.Step;
import static Common.CommonMethods.checkingContainsUrl;
import static com.codeborne.selenide.CollectionCondition.size;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.switchTo;

public class Widerruf_static_page_Logic extends Widerruf_static_page {

    @Step("Checks the elements. Widerruf_static_page")
    public Widerruf_static_page_Logic checkElementsOnThePage() {
        blockWiderrufText().shouldBe(visible);
        for (int i = 0; i < emailLinks().size(); i++) {
            emailLinks().get(i).shouldHave(attribute("href", "mailto:info@autodoc.de")).shouldHave(text("info@autodoc.de"));
        }
        emailLinks().shouldHave(size(2));
        pdfDownloadButton().click();
        acrobatReaderButton().click();
        switchTo().window(1);
        checkingContainsUrl("/reader/");
        return this;
    }
}
