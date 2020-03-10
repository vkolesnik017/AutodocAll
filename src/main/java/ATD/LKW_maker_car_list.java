package ATD;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$x;

public class LKW_maker_car_list {

    protected SelenideElement tecDocCatalog() {return $x("//div[@class='car-parts-categories__list']");}
}
