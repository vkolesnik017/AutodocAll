package ATD;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selectors.byName;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;

class Moto_main_page {
    protected SelenideElement menuCatalogInHeader() {
        return $(".menu-catalog>a");
    }

    protected SelenideElement markeOfHorizontalMotoSelector() {
        return $(byName("maker_id"));
    }
    protected SelenideElement modelOfHorizontalMotoSelector() {
        return $(byName("model_id"));
    }

    protected SelenideElement motorOfHorizontalMotoSelector() {
        return $(byName("car_id"));
    }

    protected SelenideElement tecDocCatalogOnMainPageMoto() {
        return $x("//ul[@class='moto-parts-catalog__list']");
    }

    protected SelenideElement childCategoryOnMotoMainPage() {
        return $x("//ul[@class='moto-parts-catalog__list']//span[contains(text(),'Luftfilter')]");
    }

    protected SelenideElement searchInHorizontalMotoSelector() {
        return $x("//a[@class='submit search_button ripple-out']");
    }

}
