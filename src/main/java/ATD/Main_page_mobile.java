package ATD;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selectors.byCssSelector;
import static com.codeborne.selenide.Selenide.$;

public class Main_page_mobile {

  public SelenideElement numberBasket() {
    return $(byCssSelector(".code"));
  }

}
