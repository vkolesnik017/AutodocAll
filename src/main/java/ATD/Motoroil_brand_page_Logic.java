package ATD;

import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.visible;

public class Motoroil_brand_page_Logic extends Motoroil_brand_page {

    @Step("presence INHALT filter in sidebar. Motoroil_brand_page")
    public Motoroil_brand_page_Logic presenceInhaltFilterInSidebar() {
        inhaltFilterInSideBar().shouldBe(visible);
        return this;
    }

    @Step("display OEM-Freigabe Filter In Sidebar. Motoroil_brand_page")
    public Motoroil_brand_page_Logic displayOemFreigabeFilterInSidebar() {
        oemFreigabeFilterInSideBar().shouldBe(visible);
        return this;
    }

    @Step("display of Specification Filter block In Sidebar. Motoroil_brand_page")
    public Motoroil_brand_page_Logic displaySpecificationFilterInSidebar() {
        specificationFilterInSideBar().shouldBe(visible);
        return this;
    }
}
