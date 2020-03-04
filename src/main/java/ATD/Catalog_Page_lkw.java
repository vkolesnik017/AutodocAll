package ATD;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.exist;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;

public class Catalog_Page_lkw {
    public SelenideElement menuCatalogInHeader() {
        return $(".menu-catalog>a");
    }

    public SelenideElement catalogTecDoc() {
        return $x("//div[@class='car-parts-categories']");
    }

    //  public SelenideElement firstBlockTecDocCatalog() { return $x("//div[@class='car-parts-categories-modal__level1 js-height-compare']");}

    public SelenideElement firstBlockTecDocCatalog() {
        return $x("//div[@class='car-parts-categories-modal__content']");
    }

    public SelenideElement ssfirstBlockTecDocCatalog() {
        return $x("//div[@class='car-parts-categories-modal__wrapper']");
    }

    public ElementsCollection categoriesWithDropMenuFirstLevelTecDoc() {
        return $$x("//div[contains(@class,'car-parts-categories-modal__level1')]//div[@class='car-parts-categories-modal__cat-name']").filter(visible);
    }

    public ElementsCollection categoriesWithOutDropMenuFirstLevelTecDoc() {
        return $$x("//div[@class='car-parts-categories-modal__level1 js-height-compare']//a[@class='car-parts-categories-modal__cat-link js--lkw-modal__cat-link ']").filter(visible);
    }


    public ElementsCollection categoriesWithOutDropMenuSecondLevelTecDoc() {
        return $$x("//div[@class='car-parts-categories-modal__level2 js-height-compare js-init-height']//a[@class='car-parts-categories-modal__cat-link js--lkw-modal__cat-link ']").filter(visible);
    }

    public ElementsCollection allCategoriesFirstLevelTecDoc() {
        return $$x("//div[contains(@class,'car-parts-categories-modal__level1')]/ul//li").filter(visible);
    }

    public SelenideElement closeBtnOfCookiesPopUp() {
        return $x("//div[contains(@class,'block-cookies__close')]");
    }

    public ElementsCollection categoriesTeckDockCatalog() {
        return $$x("//div[contains(@class,'car-parts-categories__item')]//a").filter(exist);
    }

    public ElementsCollection categoriesTeckDockCatalogSecondLevel() {
        return $$x("//div[contains(@class,'car-parts-categories__item')]//li/span").filter(exist);
    }

    public ElementsCollection categoriesInHeaderCatalogSecondBlock() {
        return $$x("//div[@class='menu-category__2-block']//*[@class='mCSB_container mCS_y_hidden mCS_no_scrollbar_y']//a").filter(exist);
    }

    public ElementsCollection categoriesInHeaderCatalogThirdBlock() {
        return $$x("//div[@class='menu-category__3-block']//*[@class='mCSB_container mCS_y_hidden mCS_no_scrollbar_y']//a").filter(exist);
    }

    public ElementsCollection categoriesInHeaderCatalogFourthdBlock() {
        return $$x("//div[@class='menu-category__4-block']//*[@class='mCSB_container mCS_y_hidden mCS_no_scrollbar_y']//a").filter(exist);
    }

    public SelenideElement customCategory(){ return $x("//a[contains(text(),'Elektrik')]");}
}
