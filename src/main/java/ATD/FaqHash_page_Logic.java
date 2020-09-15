package ATD;

import io.qameta.allure.Step;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static com.codeborne.selenide.Condition.visible;

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

}
