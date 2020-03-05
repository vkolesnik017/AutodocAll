package ATD;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selectors.byId;
import static com.codeborne.selenide.Selenide.*;

class Categories_page {

  SelenideElement linkForCategoryOilFilter() {
    return $("[data-ga-action='10359']");
  }

  SelenideElement searchBarByCatalog() {
    return $(byId("page_search"));
  }

  ElementsCollection tooltipsToSearchByCatalog() {
    return $$(".ui-autocomplete>li");
  }

  SelenideElement lkwCategory(){return $x("//a[@class='header-i header-i--truck ga-click']");}
}
