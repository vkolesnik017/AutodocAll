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
}
