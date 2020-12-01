package PKW;

import io.qameta.allure.Step;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.page;

public class Catalog_page_Logic extends Catalog_page {

    @Step("click btn Tyres in header. Catalog_page")
    public Tyres_page_Logic clickBtnTyresInHeader() {
        btnTyresInHeader().shouldBe(visible).click();
        return page(Tyres_page_Logic.class);
    }

    @Step("check presence title with model vehicle. Catalog_page")
    public Catalog_page_Logic checkPresenceTitleWithVehicleModel() {
        titleWithModelVehicle().shouldBe(visible);
        return this;
    }


}
