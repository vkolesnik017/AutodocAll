package ATD;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$$x;
import static com.codeborne.selenide.Selenide.$x;

public class Tyres_feature_page {

    ElementsCollection btnMoreOfLinkingBlock() {return $$x("//div[@class='most-popular']//span[@class='link']");}

    SelenideElement linkingBlock() {return $x("//div[@class='most-popular']");}

    ElementsCollection btnPaginatorOfLinkingBlock() {return $$x("//div[@class='most-popular']//button");}

    ElementsCollection titlesOfLinkingBlocks() {return $$x("//div[@class='most-popular']//a/div[2]");}

    ElementsCollection visibleTitleOfLinkingBlocks() {return $$x("//div[@class='most-popular']//a/div[2]").filter(visible);}

    SelenideElement headLineOfLinkingBlock() {return $x("//div[@class='most-popular__heading']");}

    ElementsCollection visibleBtnMoreOfLinkingBlock() {return $$x("//div[@class='most-popular']//span[@class='link']").filter(visible);}
}
