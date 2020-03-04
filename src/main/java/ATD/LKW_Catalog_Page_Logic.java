package ATD;

import com.codeborne.selenide.ElementsCollection;
import io.qameta.allure.Step;
import org.testng.Assert;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static com.codeborne.selenide.Condition.visible;

public class LKW_Catalog_Page_Logic extends LKW_Catalog_Page {

    @Step("Getting category in TecDock catalog")
    public List<Integer> getCategoryInTecDocCatalog() {
        List<Integer> tecDocCatalogList = new ArrayList<>();
        catalogTecDoc().shouldBe(visible);
        addAttributeOfProductToList(categoriesTecDockCatalog(), tecDocCatalogList);
        addAttributeOfProductToList(categoriesTecDockCatalogSecondLevel(), tecDocCatalogList);
        return tecDocCatalogList;
    }

    @Step("Getting category in Header Catalog")
    public List<Integer> getCategoryInHeaderCatalog() {
        List<Integer> catalogInHeaderList = new ArrayList<>();
        menuCatalogInHeader().scrollTo().click();
        customCategory().hover();
        addAttributeOfProductToList(categoriesInHeaderCatalogSecondBlock(), catalogInHeaderList);
        addAttributeOfProductToList(categoriesInHeaderCatalogThirdBlock(), catalogInHeaderList);
        addAttributeOfProductToList(categoriesInHeaderCatalogFourthdBlock(), catalogInHeaderList);
        return catalogInHeaderList;
    }

    @Step("Comparison TecDoc and InHeader catalogs")
    public LKW_Catalog_Page_Logic comparisonTecDocAndInHeaderCatalogs() {
        List<Integer> attributeOfTecDocCatalog = new ArrayList<>(getCategoryInTecDocCatalog());
        List<Integer> attributeOfInHeaderCatalog = new ArrayList<>(getCategoryInHeaderCatalog());
        Assert.assertEquals(sortingListWithAttributes(attributeOfInHeaderCatalog), sortingListWithAttributes(attributeOfTecDocCatalog));
        return this;
    }

    @Step("Sorting of list with attributes")
    public List<Integer> sortingListWithAttributes(List<Integer> attributeList) {
        List<Integer> expectedAttributeList = new ArrayList<>(attributeList);
        Collections.sort(expectedAttributeList);
        return expectedAttributeList;
    }

    @Step("add attributes to list")
    public LKW_Catalog_Page_Logic addAttributeOfProductToList(ElementsCollection category, List<Integer> listWithAttribute) {
        for (int k = 0; k < category.size(); k++) {
            listWithAttribute.add(Integer.parseInt(category.get(k).getAttribute("data-category-id")));
        }
        return this;
    }
}
