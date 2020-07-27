package ATD;

import com.codeborne.selenide.SelenideElement;
import static com.codeborne.selenide.Selenide.$x;

public class Listing_instruments_page {

    SelenideElement titleNameCategory() {
        return $x("//h2[@class='title_count_search']");
    }

    SelenideElement seoTextBlock() {
        return $x("//div[@class='block_youtube_video']");
    }

    SelenideElement btnReturnOnFirstPageInPagination() {
        return $x("//div[@class='page_list'][1]//span[@class='first']");
    }

    SelenideElement btnPreviousPageInPagination() {
        return $x("//div[@class='page_list'][1]//span[contains(@class,'previous')]");
    }

    SelenideElement btnNextPageInPagination() {
        return $x("//div[@class='page_list'][1]//span[@class='next'][1]");
    }

    SelenideElement btnLastPageInPagination() {
        return $x("//div[@class='page_list'][1]//span[@class='next'][2]");
    }

    SelenideElement btnSecondPageInPagination() {
        return $x("//div[@class='page_list'][1]//span[3]");
    }

}
