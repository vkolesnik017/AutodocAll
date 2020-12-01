package PKW;

import com.codeborne.selenide.SelenideElement;
import static com.codeborne.selenide.Selenide.$x;

public class Tyres_maker_group_page {

    SelenideElement carNameTitle() {
        return $x("//div[@class='selectors-reifen__top-line__title']/h1");
    }
}
