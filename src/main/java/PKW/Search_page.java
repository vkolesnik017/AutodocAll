package PKW;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selectors.byId;
import static com.codeborne.selenide.Selenide.*;

public class Search_page {
    SelenideElement productListBlock() {
        return $x("//div[@class='listing_items']");
    }

    SelenideElement forwardLinkOfPaginator() {
        return $x("//span[@class='next'][1]/a");
    }


    ElementsCollection visibleTitleOfProducts() {
        return $$x("//*[self::a[@class='ga-click prod_link'] or self::span[@class='ga-click prod_link link']]");
    }

    ElementsCollection allCharacteristicsOfProducts() {
        return $$x("//div[@class='prod_params_container']//li");
    }


    ElementsCollection visibleArtNumOfProduct() {
        return $$x("//div[@class='nr']/span");
    }


    ElementsCollection visibleCharacteristicsOfProducts(int position) {
        return $$x("(//div[@class='prod_params_container'])[" + position + "]/ul/li");
    }

    SelenideElement markeFieldSelector() {return $(byId("form_maker_id"));}

    SelenideElement modelFieldSelector() {return $(byId("form_model_id"));}

    SelenideElement motorFieldSelector() {return $(byId("form_car_id"));}
}
