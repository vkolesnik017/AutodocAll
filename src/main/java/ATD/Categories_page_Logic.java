package ATD;

import com.codeborne.selenide.ElementsCollection;
import io.qameta.allure.Step;

import static com.codeborne.selenide.CollectionCondition.sizeNotEqual;
import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.page;

public class Categories_page_Logic extends Categories_page {

  @Step("Input text {text} in search bar by catalog. Categories_page")
  public Categories_page_Logic inputTextInSearchBarByCatalog(String text) {
    searchBarByCatalog().setValue(text);
    return this;
  }

  @Step("Check that no deleted category {nameDeletedCategory} in tooltip to search by catalog. Categories_page")
  public Categories_page_Logic checkThatNoTooltipInSearchByCatalog(String nameDeletedCategory) {
    ElementsCollection tooltips =  tooltipsToSearchByCatalog().shouldHave(sizeNotEqual(0));
    tooltips.filter(exactText(nameDeletedCategory)).shouldHaveSize(0);
    return this;
  }

  @Step("Click tooltip in search by catalog by exact text {exactTooltipText}. Categories_page")
  public Category_name_page_Logic clickTooltipInSearchByCatalogByExactText(String exactTooltipText) {
    tooltipsToSearchByCatalog().get(0).shouldBe(visible);
    tooltipsToSearchByCatalog().filter(exactText(exactTooltipText)).shouldHaveSize(1).get(0).click();
    return page(Category_name_page_Logic.class);
  }

  @Step("Click oil filter category link. Categories_page")
  Categories_page_Logic clickOilFilterCategoryLink() {
    linkForCategoryOilFilter().click();
    return page(Categories_page_Logic.class);
  }

}
