package ATD;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$$x;
import static com.codeborne.selenide.Selenide.$x;

public class Index_accessories_group_page {

// Locators for steps breadcrumbs block

    SelenideElement blockBreadCrumbs() { return $x("//div[@class='steps breadcrumbs']"); }

    SelenideElement firstBreadCrumb() { return $x("//div[@class='steps breadcrumbs']//li[@class='step_1 active parts_step_1']"); }

    SelenideElement secondBreadCrumb() {return $x("//div[@class='steps breadcrumbs']//li[@class='step_2 active parts_step_2']"); }

    SelenideElement thirdBreadCrumb() { return $x("//div[@class='steps breadcrumbs']//li[@class='step_3 active parts_step_3']//a"); }


// Locators for  accessories-catalog block

   public ElementsCollection mainCategories() { return $$x("//div[@class='accessories-catalog']//a[@class='accessories-catalog__item']"); }

    SelenideElement blockAccessorizeCatalog() { return $x("//*[@class='accessories-catalog']"); }

    SelenideElement firstElementInBlockAccessorizeCatalog() { return $x("//*[@class='accessories-catalog__row']//a[1]"); }


// Locators for



}
