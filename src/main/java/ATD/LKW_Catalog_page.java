package ATD;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.exist;
import static com.codeborne.selenide.Selenide.*;

class LKW_Catalog_page {
    SelenideElement menuCatalogInHeader() {
        return $(".menu-catalog>a");
    }

    SelenideElement catalogTecDoc() {
        return $x("//div[@class='car-parts-categories']");
    }

    ElementsCollection categoriesTecDockCatalog() {
        return $$x("//div[contains(@class,'car-parts-categories__item')]//a").filter(exist);
    }

    ElementsCollection categoriesTecDockCatalogSecondLevel() {
        return $$x("//div[contains(@class,'car-parts-categories__item')]//li/span").filter(exist);
    }

    ElementsCollection categoriesInHeaderCatalogSecondBlock() {
        return $$x("//div[@class='menu-category__2-block']//*[@class='mCSB_container mCS_y_hidden mCS_no_scrollbar_y']//a").filter(exist);
    }

    ElementsCollection categoriesInHeaderCatalogThirdBlock() {
        return $$x("//div[@class='menu-category__3-block']//*[@class='mCSB_container mCS_y_hidden mCS_no_scrollbar_y']//a").filter(exist);
    }

    ElementsCollection categoriesInHeaderCatalogFourthdBlock() {
        return $$x("//div[@class='menu-category__4-block']//*[@class='mCSB_container mCS_y_hidden mCS_no_scrollbar_y']//a").filter(exist);
    }

    SelenideElement customCategory() {
        return $x("//a[contains(text(),'Elektrik')]");
    }

    SelenideElement carCategory() {
        return $x("//a[@class='header-i header-i--car ga-click']");
    }

    public SelenideElement motoCategory() {
        return $x("//a[contains(@class,'header-i--moto')]");
    }

    SelenideElement logoInHeader() {
        return $(".header__logo-main");
    }
}
