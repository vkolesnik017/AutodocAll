package ATD;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.*;

class LKW_maker_page {

    SelenideElement titleOfSidebar() {
        return $x("//div[@class='links-title']");
    }

    ElementsCollection linkingBlocks() {
        return $$x("//div[@class='box']");
    }

    ElementsCollection linksOfLinkingBlocks(int position) {
        return $$x("//div[@class='box']["+position+"]//a");
    }
    SelenideElement titleOfTopCar(String title) {
        return $x("//a[contains(text(),'"+title+"')]");
    }
}
