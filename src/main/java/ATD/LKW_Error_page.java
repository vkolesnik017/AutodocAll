package ATD;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$$x;
import static com.codeborne.selenide.Selenide.$x;

public class LKW_Error_page {

    SelenideElement headlineInHeader() {return $x("//div[@class='title_page']");}

    SelenideElement mainErrorImage() {return $x("//div[@class='img']");}

    ElementsCollection parentCategories() {return $$x("//div[@class='car-parts-categories__item-link']");}

    ElementsCollection childCategoriesPopUpOfParentCategory() {return $$x("//div[@class='car-parts-categories-modal__wrapper']");}

    ElementsCollection titleOfParentCategories() {return $$x("//div[@class='car-parts-categories__item-name']");}

    ElementsCollection imageOfParentCategories() {return $$x("//div[@class='car-parts-categories__item-link']/div/img");}

    ElementsCollection childCategoriesFirstLevel() {return $$x("//div[@class='car-parts-categories-modal__level1 js-height-compare js-init-height']/ul[@class='car-parts-categories-modal__link-list']/li/*[self::a or self::span]");}

    ElementsCollection childCategoriesFirstLevelForCheck() {return $$x("//div[@class='car-parts-categories-modal__level1 js-height-compare']/ul[@class='car-parts-categories-modal__link-list']/li").filter(visible);}

    ElementsCollection visibleChildCategorySecondLevel() {return $$x("//div[contains(@class,'car-parts-categories-modal__level2')]/ul[@class='car-parts-categories-modal__link-list']/li");}

    ElementsCollection visibleIntermediateCategorySecondLevel() {return $$x("//div[@class='car-parts-categories-modal__level2 js-height-compare js-init-height']/ul[@class='car-parts-categories-modal__cat-list']/li");}

       ElementsCollection intermediateChildCategoriesSecondLevel() {return $$x("//div[contains(@class,'car-parts-categories-modal__level2')]/ul[@class='car-parts-categories-modal__cat-list']/li").filter(visible);}

    ElementsCollection childCategoriesThirdLevel() {return $$x("//div[@class='car-parts-categories-modal__level3 js-height-compare js-init-height']/div/*[self::a or self::span]");}

    ElementsCollection visibleChildCategoriesSecondLevel() {return $$x("//div[@class='car-parts-categories-modal__level2 js-height-compare js-init-height']/ul[@class='car-parts-categories-modal__link-list']/li");}

    ElementsCollection imageOfVisibleChildCategoriesSecondLevel() {return $$x("//div[@class='car-parts-categories-modal__level2 js-height-compare js-init-height']/ul[@class='car-parts-categories-modal__link-list']/li//img");}

    ElementsCollection titleOfVisibleChildCategoriesSecondLevel() {return $$x("//div[@class='car-parts-categories-modal__level2 js-height-compare js-init-height']/ul[@class='car-parts-categories-modal__link-list']/li/*[self::a or self::span]/span");}

    ElementsCollection visibleIntermediateCategoryFirstLevel() {return $$x("//div[@class='car-parts-categories-modal__level1 js-height-compare']/ul[@class='car-parts-categories-modal__cat-list']/li").filter(visible);}

    ElementsCollection imageOfVisibleChildCategoriesFirstLevel() {return $$x("//div[@class='car-parts-categories-modal__level1 js-height-compare js-init-height']/ul[@class='car-parts-categories-modal__link-list']/li/*[self::a or self::span]/img");}

    SelenideElement secondLevelBlock() {return $x("//li[@class='active']/div[2]");}

    SelenideElement thirdLevelBlock() {return $x("//div[@class='car-parts-categories-modal__level3 js-height-compare js-init-height']");}

    ElementsCollection titleOfVisibleChildCategoriesFirstLevel() {return $$x("//div[@class='car-parts-categories-modal__level1 js-height-compare js-init-height']/ul[@class='car-parts-categories-modal__link-list']/li/*[self::a or self::span]/span");}

    ElementsCollection intermediateChildCategoriesFirstLevel(int position) {return $$x("(//div[contains(@class,'car-parts-categories-modal__level1')])["+position+"]/ul[@class='car-parts-categories-modal__cat-list']/li");}

    ElementsCollection visibleChildCategoriesPopUpOfParentCategory() {return $$x("//div[@class='car-parts-categories-modal__wrapper']").filter(visible);}

    ElementsCollection popUpOfParentCategories() {return $$x("//div[@class='car-parts-categories-modal__content']");}

    ElementsCollection visibleChildCategories() {return $$x("//ul[@class='car-parts-categories-modal__link-list']//a").filter(visible);}

}
