package ATD;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;

public class ListingTecDocSoft404_page {

  public SelenideElement blockOfNoFindProduct() {
    return $("#no_product_find");
  }

  public SelenideElement blockWithCategories() {
    return $(".maintenance-block");
  }

  public SelenideElement blockWithTopProducts() {
    return $(".top-small-products");
  }
}
