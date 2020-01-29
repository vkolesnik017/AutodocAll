package AWS;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byXpath;
import static com.codeborne.selenide.Selenide.*;

public class CategoriesSynonyms_aws {

  public String urlWithSynonymTurboAndShopDE = "https://aws.autodoc.de/categories/synonyms?id=&name=&syn_name=turbo&pageId=&lang%5B%5D=de&search=";

  private By genericsInSearchTable = byXpath("(//table[4]//td[@class='name'])[position()>1]");

  private SelenideElement synonymByGenericFromSearchBlock(String generic) {
    return $x("//table[4]//td[@class='name' and text()='" + generic + "']/..//div[@class='synon']/span");
  }

  @Step
  public String getRandomGenericFromSearchBlock() {
    $(genericsInSearchTable).shouldBe(visible);
    ElementsCollection coll = $$(genericsInSearchTable);
    String synonym = coll.get((int) (Math.random() * coll.size())).getText();
    return synonym;
  }

  @Step
  public String getSynonymByGenericInSearchBlock(String generic) {
    return synonymByGenericFromSearchBlock(generic).getText();
  }
}
