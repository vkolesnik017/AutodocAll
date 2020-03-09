package ATD;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.*;

public class LKW_maker_page {

    protected SelenideElement titleOfSidebar() {
        return $x("//div[@class='links-title']");
    }

    protected ElementsCollection linkingBlocks() {
        return $$x("//div[@class='box']");
    }

    protected ElementsCollection linksOfLinkingBlocks(int position) {
        return $$x("//div[@class='box']["+position+"]//a");
    }
    protected SelenideElement titleOfTopCar(String title) {
        return $x("//a[contains(text(),'"+title+"')]");
    }
}
