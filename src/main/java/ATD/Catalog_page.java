package ATD;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;

public class Catalog_page {

  public SelenideElement catalogBlog() {
    return $(".list-ersatz-n--catalog");
  }
}