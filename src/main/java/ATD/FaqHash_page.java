package ATD;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$$x;
import static com.codeborne.selenide.Selenide.$x;

public class FaqHash_page {
    ElementsCollection titleOfParentCategories() { return $$x("//span[@class='name']");    }

    SelenideElement tecDocCatalogBlock() {return $x("//div[@class='list_ersats_n list-ersatz-n--catalog']");}

    ElementsCollection  activeSubCategories() {return $$x("//li[@class='ctg active']/div[2]//li/*[self::a or self::span]/span");}

    ElementsCollection titleWithParameter(String titleOfParentCategory) {return $$x("//li[@class='ctg']/div/span[contains(text(),'"+titleOfParentCategory+"')]/ancestor::li/div[2]/ul/li/*[self::a or self::span]/span");}

}
