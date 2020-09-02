package ATD;

import io.qameta.allure.Step;

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
}
