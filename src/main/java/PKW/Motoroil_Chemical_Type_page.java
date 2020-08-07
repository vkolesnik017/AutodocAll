package PKW;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$$x;
import static com.codeborne.selenide.Selenide.$x;

public class Motoroil_Chemical_Type_page {

    SelenideElement breadCrumbsBlock() {return $x("//div[@class='crabs']");}

    ElementsCollection linksOfBreadCrumbsBlock() {return $$x("//div[@class='crabs']/div/a");}

    ElementsCollection relinkingBlocks() {return $$x("//div[@class='listing_microdata']");}

    SelenideElement titleOfRelinkingBLocks(int positionOfBlock) {return $x("(//div[@class='listing_microdata'])["+positionOfBlock+"]//div[@class='lm_title']");}

    SelenideElement contentPartOfRelinkingBLocks(int positionOfBlock) {return $x("(//div[@class='listing_microdata'])["+positionOfBlock+"]//div[@class='lm_content']");}

    ElementsCollection linksOfRelinkingBlocks(int positionOfBlock) {return $$x("(//div[@class='listing_microdata'])["+positionOfBlock+"]//p/a");}
}
