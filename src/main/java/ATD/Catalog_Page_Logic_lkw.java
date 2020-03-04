package ATD;

import com.codeborne.selenide.ElementsCollection;
import io.qameta.allure.Step;
import org.testng.Assert;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static com.codeborne.selenide.Condition.visible;

public class Catalog_Page_Logic_lkw extends Catalog_Page_lkw {

    @Step("Selecting category in TecDock catalog")
    public List<Integer> selectCategoryInTecDocCatalog() {
        List<Integer> teckDockCatalogList = new ArrayList<>();
        catalogTecDoc().shouldBe(visible);
        addAttributeOfProductToList(categoriesTeckDockCatalog(), teckDockCatalogList);
        addAttributeOfProductToList(categoriesTeckDockCatalogSecondLevel(), teckDockCatalogList);
        return teckDockCatalogList;
    }

    @Step("Selecting category in Header Catalog")
    public List<Integer> selectCategoryInHeaderCatalog() {
        List<Integer> catalogInHeaderList = new ArrayList<>();
        menuCatalogInHeader().scrollTo().click();
        customCategory().hover();
        addAttributeOfProductToList(categoriesInHeaderCatalogSecondBlock(), catalogInHeaderList);
        addAttributeOfProductToList(categoriesInHeaderCatalogThirdBlock(), catalogInHeaderList);
        addAttributeOfProductToList(categoriesInHeaderCatalogFourthdBlock(), catalogInHeaderList);
        return catalogInHeaderList;
    }

    @Step("Comparison TecDoc and InHeader catalogs")
    public Catalog_Page_Logic_lkw comparisonTecDocAndInHeaderCatalogs() {
        List<Integer> attributeOfTecDocCatalog = new ArrayList<>(selectCategoryInTecDocCatalog());
        List<Integer> attributeOfInHeaderCatalog = new ArrayList<>(selectCategoryInHeaderCatalog());
        Assert.assertEquals(sortingListWithAttributes(attributeOfInHeaderCatalog),sortingListWithAttributes(attributeOfTecDocCatalog));
        return this;
    }

    @Step("Sorting of list with attributes")
    public List<Integer> sortingListWithAttributes(List<Integer> attributeList) {
        List<Integer> expectedAttributeList = new ArrayList<>(attributeList);
        Collections.sort(expectedAttributeList);
        return expectedAttributeList;
    }

    @Step("add attributes to list")
    public Catalog_Page_Logic_lkw addAttributeOfProductToList(ElementsCollection category, List<Integer> listWithAttribute) {
        for (int k = 0; k < category.size(); k++) {
            listWithAttribute.add(Integer.parseInt(category.get(k).getAttribute("data-category-id")));
        }
        return this;
    }
}
