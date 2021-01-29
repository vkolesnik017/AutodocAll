package ATD;

import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.visible;

public class Static_page_atd_page_Logic extends Static_page_atd_page {

    @Step("presence of helping Autodoc block. Static_page_atd_page")
    public Static_page_atd_page_Logic presenceHelpingBlock() {
        helpingBlock().shouldBe(visible);
        return this;
    }

    @Step("download Pdf In Helping Block. Static_page_atd_page")
    public Static_page_atd_page_Logic downloadPdfInHelpingBlock() {
        pdfLinkInHelpingBLock().scrollTo().click();
        return this;
    }
}
