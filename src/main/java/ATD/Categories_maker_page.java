package ATD;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.*;

public class Categories_maker_page {

    SelenideElement tecDocCatalog() {return $x("//ul[@class='parts-parent-cats__list']");}

    ElementsCollection titlesOfParentCategories() {return $$x("//div[@class=' parts-parent-cats']//span[@class='parts-parent-cats__name']");}

    SelenideElement exactParentCategory(String parentCategory) {return $x("//span[@class='parts-parent-cats__name'][text()='"+parentCategory+"']");}

    ElementsCollection titlesOfSeoBlock() {return $$x("//div[@class='search_n_text']/*[self::h3 or self::strong]");}

    ElementsCollection titleLinksOfSeoBlock() {return $$(".search_n_text a");}
}
