package ATD;

import com.codeborne.selenide.ElementsCollection;
import io.qameta.allure.Step;
import org.testng.Assert;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;

import static com.codeborne.selenide.CollectionCondition.sizeNotEqual;
import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.page;
import static com.codeborne.selenide.WebDriverRunner.url;

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

  @Step("Сheck that the page loads successfully. Categories_page")
  public Categories_page_Logic checkSuccessfullyPageLoading(){
    searchBarByCatalog().shouldBe(visible);
    Assert.assertEquals(url(),"https://www.autodoc.de/ersatzteile/mercedes-benz/a-klasse/a-class-w177/130593-a-180-d-177-003");
    return this;
  }

  @Step("Select LKW category. Categories_page")
  public LKW_main_page_Logic selectLKWCategory(){
    lkwCategory().click();
    return page(LKW_main_page_Logic.class);
  }

    @Step("Select Moto category. Categories_page")
    public Moto_main_page_Logic selectMotoCategory() {
        motoCategory().click();
        return page(Moto_main_page_Logic.class);
    }

    @Step("Check response code is 200 for all categories in tecdoc catalog. Categories_page")
    public Categories_page_Logic check200ResponseTecdoc() throws IOException {
      for (int i = 0; i < tecdocCategoriesA().size(); i++) {
        System.out.println(tecdocCategoriesA().get(i).attr("href"));
        URL url = new URL(tecdocCategoriesA().get(i).attr("href"));
        HttpURLConnection http = (HttpURLConnection) url.openConnection();
        http.setInstanceFollowRedirects(true);
        int responseCode = http.getResponseCode();
        Assert.assertEquals(responseCode, 200);
      }

      for (int i = 0; i < tecdocCategoriesSpan().size(); i++) {
        System.out.println(tecdocCategoriesSpan().get(i).attr("url"));
        URL url = new URL(tecdocCategoriesSpan().get(i).attr("url"));
        HttpURLConnection http = (HttpURLConnection) url.openConnection();
        http.setInstanceFollowRedirects(true);
        int responseCode = http.getResponseCode();
        Assert.assertEquals(responseCode, 200);
      }
      return this;
    }

    @Step("Check response code is 200 for all categories in dropdown catalog. Categories_page")
    public Categories_page_Logic check200ResponseDropdown() throws Exception {
      catalogInHeader().click();
      Thread.sleep(5000);
      for (int i = 0; i < dropdownCategories().size(); i++) {
        System.out.println(dropdownCategories().get(i).attr("href"));
        URL url = new URL(dropdownCategories().get(i).attr("href"));
        HttpURLConnection http = (HttpURLConnection) url.openConnection();
        http.setInstanceFollowRedirects(true);
        int responseCode = http.getResponseCode();
        Assert.assertEquals(responseCode, 200);
      }
      return this;
    }

    @Step("Get All Categories From Catalog Dropdown Menu")
    public ArrayList<Integer> getAllCategoriesFromCatalogDropdownMenu() throws Exception {
        catalogInHeader().click();
        Thread.sleep(5000);
        System.out.println(secondListInDropdownCatalog().size());
        System.out.println(firstListInDropdownCatalog().size());
        ArrayList<Integer> listWithCategoriesInDropdownCatalog = new ArrayList<>();
        for (int i = 0; i < firstListInDropdownCatalog().size(); i++) {
            System.out.println(firstListInDropdownCatalog().get(i).attr("data-for-first-list"));
            listWithCategoriesInDropdownCatalog.add(Integer.parseInt(firstListInDropdownCatalog().get(i).attr("data-for-first-list")));
          }
        for (int i = 0; i < secondListInDropdownCatalog().size(); i++) {
          System.out.println(secondListInDropdownCatalog().get(i).attr("data-category-id"));
          listWithCategoriesInDropdownCatalog.add(Integer.parseInt(secondListInDropdownCatalog().get(i).attr("data-category-id")));
        }
        Collections.sort(listWithCategoriesInDropdownCatalog);
          System.out.println(listWithCategoriesInDropdownCatalog.size());
          return listWithCategoriesInDropdownCatalog;
    }

    @Step("Compare categories from catalog and AWS")
    public Categories_page_Logic compareCategories(ArrayList<Integer> listFromcatalog, ArrayList<Integer> listFromAWS) {
      Assert.assertEquals(listFromcatalog, listFromAWS);
      return this;
    }
  }
