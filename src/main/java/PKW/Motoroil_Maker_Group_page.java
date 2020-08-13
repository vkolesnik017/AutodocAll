package PKW;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$$x;
import static com.codeborne.selenide.Selenide.$x;

public class Motoroil_Maker_Group_page {

    SelenideElement breadCrumbsBlock() {
        return $x("//div[@class='crabs']");
    }

    ElementsCollection linksOfBreadCrumbsBlock() {
        return $$x("//div[@class='crabs']/div/a");
    }

    ElementsCollection linksOfRelinkingBlocks(int positionOfBlock) {
        return $$x("(//div[@class='listing_microdata'])[" + positionOfBlock + "]//p/a");
    }

}
