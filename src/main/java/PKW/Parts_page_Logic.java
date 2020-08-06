package PKW;

import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.visible;

public class Parts_page_Logic extends Parts_page {

    @Step("presence of TecDoc catalog. Parts_page ")
    public Parts_page_Logic  presenceOfTecDocCatalog() {
        tecDocCatalog().shouldBe(visible);
        return this;
    }

}
