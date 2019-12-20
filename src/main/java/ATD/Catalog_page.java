package ATD;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;

public class Catalog_page {

  public SelenideElement catalogBlog() {
    return $(".list-ersatz-n--catalog");
  }

  public SelenideElement linkForCateforyOilFilter() {
    return $("[data-ga-action='10359']");
  }
}
