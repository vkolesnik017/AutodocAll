package ATD;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$$x;
import static com.codeborne.selenide.Selenide.$x;

public class Categories_maker_page {

    SelenideElement tecDocCatalog() {return $x("//ul[@class='parts-parent-cats__list']");}

    ElementsCollection titlesOfParentCategories() {return $$x("//div[@class=' parts-parent-cats']//span[@class='parts-parent-cats__name']");}

    SelenideElement exactParentCategory(String parentCategory) {return $x("//span[@class='parts-parent-cats__name'][text()='"+parentCategory+"']");}
}
