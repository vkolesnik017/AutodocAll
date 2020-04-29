package ATD;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selectors.byId;
import static com.codeborne.selenide.Selenide.*;

public class Categories_page {

  public SelenideElement linkForCategoryOilFilter() {
    return $("[data-ga-action='10359']");
  }

  public SelenideElement searchBarByCatalog() {
    return $(byId("page_search"));
  }

  public ElementsCollection tooltipsToSearchByCatalog() {
    return $$(".ui-autocomplete>li");
  }

    public SelenideElement lkwCategory() {
        return $x("//a[@class='header-i header-i--truck ga-click']");
    }

    public SelenideElement motoCategory() {
        return $x("//a[contains(@class,'header-i--moto')]");
    }

    ElementsCollection tecdocCategoriesA() { return  $$x("//*[@class='list_ersats_n list-ersatz-n--catalog']//li//a");}

    ElementsCollection tecdocCategoriesSpan() { return  $$x("//*[@class='list_links']/li/span");}

    ElementsCollection dropdownCategories() { return $$(".mCSB_container  a"); }

    SelenideElement catalogInHeader() { return $(".menu-catalog"); }

    ElementsCollection categories() { return $$x("//*[@class='mCSB_container']//a[@data-for-first-list]"); }

    ElementsCollection firstListInDropdownCatalog() { return $$x("//*[@class='menu-category__first-item']/a"); }

    ElementsCollection secondListInDropdownCatalog() { return $$x("//*[@class='menu-category__second-item']/a"); }

    ElementsCollection parentCategoriesTecdocName() { return $$x("//*[@class='list_ersats_n list-ersatz-n--catalog']//span[@class='name']"); }


}
