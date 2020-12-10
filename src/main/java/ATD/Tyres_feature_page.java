package ATD;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byId;
import static com.codeborne.selenide.Selenide.*;

public class Tyres_feature_page {

    ElementsCollection btnMoreOfLinkingBlock() {return $$x("//div[@class='most-popular']//span[@class='link']");}

    SelenideElement linkingBlock() {return $x("//div[@class='most-popular']");}

    ElementsCollection btnPaginatorOfLinkingBlock() {return $$x("//div[@class='most-popular']//button");}

    ElementsCollection titlesOfLinkingBlocks() {return $$x("//div[@class='most-popular']//a/div[2]");}

    ElementsCollection visibleTitleOfLinkingBlocks() {return $$x("//div[@class='most-popular']//a/div[2]").filter(visible);}

    SelenideElement headLineOfLinkingBlock() {return $x("//div[@class='most-popular__heading']");}

    ElementsCollection visibleBtnMoreOfLinkingBlock() {return $$x("//div[@class='most-popular']//span[@class='link']").filter(visible);}

    SelenideElement mainHeadline() {return $x("//h1[@class='title-reifen']");}

    ElementsCollection seoHeadlines() {return $$x("//div[@class='reifen-text-rows']/strong");}

    ElementsCollection seoTexts() {return $$x("//div[@class='reifen-text-rows']//*[self::p or self::ul/li/b]");}

    SelenideElement tyresSizeSelector() {return $(".reifen-selector");}

    SelenideElement widthDropdown() { return $("select#form_Width"); }

    SelenideElement heightDropdown() { return $("select#form_CrossSections"); }

    SelenideElement diameterDropdown() { return $("#form_Size"); }

    SelenideElement winterSeason() {return $(byId("winter-radio"));}

    SelenideElement summerSeason() {return $("summer-radio");}
}
