package ATD;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selectors.byCssSelector;
import static com.codeborne.selenide.Selenide.$;

public class Tyres_page {

  public SelenideElement imagesProductsTires() {
    return $(byCssSelector(".prod_img"));
  }
}
