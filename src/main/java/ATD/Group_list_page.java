package ATD;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$$x;
import static com.codeborne.selenide.Selenide.$x;

public class Group_list_page {

    SelenideElement pdfManualBlock() {
        return $x("//*[@class = 'product_manual_block']");
    }

    SelenideElement pdfManualTitleBlock() {
        return $x("//*[@class = 'manual_block_title']");
    }

    ElementsCollection previewImages() {
        return $$x("//*[@class = 'product_manual__item']//img");
    }

    ElementsCollection titlesOfManuals() {
        return $$x("//*[@class = 'product_manual__item']//a");
    }

    ElementsCollection downloadLinkOfManuals() {
        return $$x("//*[@class = 'product_manual__item']//span");
    }

    ElementsCollection sizeFile() {
        return $$x("//*[@class = 'product_manual__item']//em");
    }

    ElementsCollection typeFile() {
        return $$x("//*[@class = 'product_manual__item']//i");
    }
}
