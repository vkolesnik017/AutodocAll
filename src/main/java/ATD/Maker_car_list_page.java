package ATD;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;

public class Maker_car_list_page {

  public SelenideElement headingOfSearchByCatalog() {
    return $(".page_search_title");
  }

}
