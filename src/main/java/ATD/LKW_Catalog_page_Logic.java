package ATD;

import com.codeborne.selenide.ElementsCollection;
import io.qameta.allure.Step;
import org.testng.Assert;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.page;
import static com.codeborne.selenide.WebDriverRunner.url;

public class LKW_Catalog_page_Logic extends LKW_Catalog_page {

    @Step("Getting category in TecDock catalog. LKW_Catalog_page")
    public List<Integer> getCategoryInTecDocCatalog() {
        List<Integer> tecDocCatalogList = new ArrayList<>();
        catalogTecDoc().shouldBe(visible);
        addAttributeOfProductToList(categoriesTecDockCatalog(), tecDocCatalogList);
        addAttributeOfProductToList(categoriesTecDockCatalogSecondLevel(), tecDocCatalogList);
        return tecDocCatalogList;
    }

    @Step("Getting category in Header Catalog. LKW_Catalog_page")
    public List<Integer> getCategoryInHeaderCatalog() {
        List<Integer> catalogInHeaderList = new ArrayList<>();
        menuCatalogInHeader().scrollTo().click();
        customCategory().hover();
        addAttributeOfProductToList(categoriesInHeaderCatalogSecondBlock(), catalogInHeaderList);
        addAttributeOfProductToList(categoriesInHeaderCatalogThirdBlock(), catalogInHeaderList);
        addAttributeOfProductToList(categoriesInHeaderCatalogFourthdBlock(), catalogInHeaderList);
        return catalogInHeaderList;
    }

    @Step("Comparison TecDoc and InHeader catalogs. LKW_Catalog_page")
    public LKW_Catalog_page_Logic comparisonTecDocAndInHeaderCatalogs() {
        new LKW_main_page_Logic().selectTruckInSelector("36","682","1008978");
        getCategoryInTecDocCatalog();
        List<Integer> attributeOfTecDocCatalog = new ArrayList<>(getCategoryInTecDocCatalog());
        getCategoryInHeaderCatalog();
        List<Integer> attributeOfInHeaderCatalog = new ArrayList<>(getCategoryInHeaderCatalog());
        Assert.assertEquals(sortingListWithAttributes(attributeOfInHeaderCatalog), sortingListWithAttributes(attributeOfTecDocCatalog));
        return this;
    }

    @Step("Sorting of list with attributes. LKW_Catalog_page")
    private List<Integer> sortingListWithAttributes(List<Integer> attributeList) {
        List<Integer> expectedAttributeList = new ArrayList<>(attributeList);
        Collections.sort(expectedAttributeList);
        return expectedAttributeList;
    }

    @Step("add attributes to list. LKW_Catalog_page")
    private LKW_Catalog_page_Logic addAttributeOfProductToList(ElementsCollection category, List<Integer> listWithAttribute) {
        for (int k = 0; k < category.size(); k++) {
            listWithAttribute.add(Integer.parseInt(category.get(k).getAttribute("data-category-id")));
        }
        return this;
    }

    @Step("Сheck that the page loads successfully")
    public LKW_Catalog_page_Logic checkSuccessfullyPageLoading() {
        catalogTecDoc().shouldBe(visible);
        Assert.assertEquals(url(), "https://lkwteile.autodoc.de/lastkraftwagen/daf/45?car_id=1004434");
        return this;
    }

    @Step("Select Car category")
    public Main_page_Logic selectCarCategory() {
        carCategory().click();
        return page(Main_page_Logic.class);
    }

    @Step("Select Moto category")
    public Moto_main_page_Logic selectMotoCategory() {
        motoCategory().click();
        return page(Moto_main_page_Logic.class);
    }
}
