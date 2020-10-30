package ATD;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$$x;
import static com.codeborne.selenide.Selenide.$x;

public class Group_list_body_page {

    SelenideElement topProductsBlock() {return $x("//div[contains(@class,'js-product-list-animation')]");}

    ElementsCollection signalWordOfDangerousProduct() {return $$x("//div[@class='popup-dangerous__title']");}

    SelenideElement dangerousPopUp() {return $x("//div[@class='popup-dangerous']");}
}

