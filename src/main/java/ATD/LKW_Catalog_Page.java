package ATD;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.exist;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;

public class LKW_Catalog_Page {
    protected SelenideElement menuCatalogInHeader() {
        return $(".menu-catalog>a");
    }

    protected SelenideElement catalogTecDoc() {
        return $x("//div[@class='car-parts-categories']");
    }

    protected ElementsCollection categoriesTecDockCatalog() {
        return $$x("//div[contains(@class,'car-parts-categories__item')]//a").filter(exist);
    }

    protected ElementsCollection categoriesTecDockCatalogSecondLevel() {
        return $$x("//div[contains(@class,'car-parts-categories__item')]//li/span").filter(exist);
    }

    protected ElementsCollection categoriesInHeaderCatalogSecondBlock() {
        return $$x("//div[@class='menu-category__2-block']//*[@class='mCSB_container mCS_y_hidden mCS_no_scrollbar_y']//a").filter(exist);
    }

    protected ElementsCollection categoriesInHeaderCatalogThirdBlock() {
        return $$x("//div[@class='menu-category__3-block']//*[@class='mCSB_container mCS_y_hidden mCS_no_scrollbar_y']//a").filter(exist);
    }

    protected ElementsCollection categoriesInHeaderCatalogFourthdBlock() {
        return $$x("//div[@class='menu-category__4-block']//*[@class='mCSB_container mCS_y_hidden mCS_no_scrollbar_y']//a").filter(exist);
    }

    protected SelenideElement customCategory() {
        return $x("//a[contains(text(),'Elektrik')]");
    }
}
