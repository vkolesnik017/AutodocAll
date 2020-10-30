package ATD;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$$x;
import static com.codeborne.selenide.Selenide.$x;

public class Group_list_hp_page {

    SelenideElement topProductsBlock() {return $x("//div[contains(@class,'js-product-list-animation')]");}

    ElementsCollection signalWordOfDangerousProduct() {return $$x("//div[@class='popup-dangerous__title']");}

    ElementsCollection btnMoreOfDangerousProduct() {return $$x("//span[@class='dangerous-listing__show-more']");}

    SelenideElement dangerousPopUp() {return $x("//div[@class='popup-dangerous']");}
}
