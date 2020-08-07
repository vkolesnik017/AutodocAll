package PKW;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$x;

public class Parts_page {

  SelenideElement tecDocCatalog() {return $x("//div[@class='cont']");}
}
