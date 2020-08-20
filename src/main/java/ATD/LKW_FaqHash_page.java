package ATD;

import com.codeborne.selenide.ElementsCollection;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$$x;

public class LKW_FaqHash_page {
    ElementsCollection titleOfParentCategories() {return $$x("//div[@class='car-parts-categories__item-name']");}

    ElementsCollection imageOfParentCategories() {return $$x("//div[@class='car-parts-categories__item-link']/div/img");}

    ElementsCollection visibleChildCategoriesPopUpOfParentCategory() {return $$x("//div[@class='car-parts-categories-modal__wrapper']").filter(visible);}

    ElementsCollection popUpOfParentCategories() {return $$x("//div[@class='car-parts-categories-modal__content']");}
}
