package ATD;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$x;

public class Cart_page_mob {

    SelenideElement idOfAddedProduct(){return $x("//div[@class='prod_item ']");}
}
