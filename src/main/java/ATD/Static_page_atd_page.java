package ATD;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;

public class Static_page_atd_page {

    SelenideElement helpingBlock() {return $(".atd-hilft");}

    SelenideElement pdfLinkInHelpingBLock() {return $(".hilft-article__files>span");}
}
