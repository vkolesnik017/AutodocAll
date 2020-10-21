package ATD;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$$x;
import static com.codeborne.selenide.Selenide.$x;

public class Group_list_hp_page {

    SelenideElement topProductsBlock() {return $x("//div[contains(@class,'js-product-list-animation')]");}

    ElementsCollection signalWordOfDangerousProduct() {return $$x("//div[@class='dangerous-listing__title hidden hazard-attention-title']");}
}
