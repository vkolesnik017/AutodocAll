package ATD;

import AWS.CatalogCategories_aws;
import io.qameta.allure.Step;
import org.testng.Assert;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.executeJavaScript;
import static com.codeborne.selenide.Selenide.switchTo;

public class FaqHash_page_Logic extends FaqHash_page {

    @Step("presence of TecDocCatalog. FaqHash_page")
    public FaqHash_page_Logic presenceOfTecDocCatalog() {
        tecDocCatalogBlock().shouldBe(visible);
        return this;
    }

    @Step("get Parent categories. FaqHash_page")
    public List<String> getParentCategories() {
        List<String> parentCategories = titleOfParentCategories().stream()
                .filter(title -> !title.getText().equals("Reifen"))
                .map(title -> title.getText())
                .collect(Collectors.toList());
        return parentCategories;
    }

    @Step("get exaсt SubCategories. FaqHash_page")
    public List<String> getExaсtSubCategories(List<String> list) {
        List<String> subCategories = new ArrayList<>();

        for (int i = 0; i < titleOfParentCategories().size(); i++) {
            if (list.contains(titleOfParentCategories().get(i).getText())) {
                continue;
            } else {
                titleOfParentCategories().get(i).click();
                for (int j = 0; j < activeSubCategories().size(); j++) {
                    subCategories.add(activeSubCategories().get(j).getText().replaceAll("[^a-zA-ZÖö]", ""));
                }
                titleOfParentCategories().get(i).click();
            }
        }
        return subCategories;
    }

    @Step("compare SubCategories list with aws. FaqHash_page")
    public FaqHash_page_Logic compareSubCategoriesListWithAws(List<String> list) throws SQLException {
        CatalogCategories_aws catalogPage = new CatalogCategories_aws();
        executeJavaScript("window.open('about:blank', '-blank')");
        switchTo().window(1);
        catalogPage.openChildCategoriesPageInAws();
        switchTo().window(0);
        presenceOfTecDocCatalog();
        for (int i = 0; i < titleOfParentCategories().size(); i++) {
            if (list.contains(titleOfParentCategories().get(i).getText())) {
                continue;
            } else {
                titleOfParentCategories().get(i).shouldBe(visible).click();
                List<String> subCategoryFront = new ArrayList<>();
                String currentParentCategory = activeParentCategory().attr("data-node_id");
                for (int j = 0; j < activeSubCategories().size(); j++) {
                    subCategoryFront = activeSubCategories().stream().map(n -> n.getText().replaceAll("\\s+$", "")).collect(Collectors.toList());
                }
                switchTo().window(1);
                List<String> subCategoriesFromAws = catalogPage.getChildCategoriesByParentName(currentParentCategory);
                Assert.assertEquals(subCategoryFront, subCategoriesFromAws, String.format("Test failed in category - %s", currentParentCategory));
                subCategoryFront.clear();
                subCategoriesFromAws.clear();
                switchTo().window(0);
                titleOfParentCategories().get(i).shouldBe(visible).click();
            }
        }
        return this;
    }

}
