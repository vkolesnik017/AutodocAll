package ATD;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$$x;
import static com.codeborne.selenide.Selenide.$x;


public class Tyres_maker_page {

    public ElementsCollection brandsInSlider() {
        return $$x("//a[contains(@class,'top-brands-slider')]//img");
    }

    SelenideElement seasonBlock() {
        return $x("//div[@class='tyre-seasons']");
    }

    ElementsCollection seasonsInSeasonBlock() {
        return $$x("//div[@class='tyre-seasons__item']//img");
    }
}
