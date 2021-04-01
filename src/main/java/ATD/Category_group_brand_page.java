package ATD;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.*;

public class Category_group_brand_page {

    SelenideElement headlineOfOtherEnginePartsBlock() {return $x("//div[@class='title_list no_border_top']/b");}

    ElementsCollection titlesOfOtherEnginePartsBlock() {return $$(".product-parent-cats a");}
}
