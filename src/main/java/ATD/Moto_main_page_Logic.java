package ATD;

import io.qameta.allure.Step;
import org.testng.Assert;

import static com.codeborne.selenide.Condition.exactValue;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.page;
import static com.codeborne.selenide.WebDriverRunner.url;

public class Moto_main_page_Logic extends Moto_main_page {

    @Step("Check successfully Moto page loading. Moto_main_page")
    public Moto_main_page_Logic checkSuccessfullyMotoPageLoading() {
        menuCatalogInHeader().shouldBe(visible);
        Assert.assertTrue(url().contains("https://moto.autodoc.de/"));
        return this;
    }

    @Step("Checking that selector is empty. Moto_main_page")
    public Moto_main_page_Logic checkOfEmptyMotoSelector() {
        markeOfHorizontalMotoSelector().shouldHave(exactValue("0"));
        modelOfHorizontalMotoSelector().shouldHave(exactValue("0"));
        motorOfHorizontalMotoSelector().shouldHave(exactValue("0"));
        return this;
    }

    @Step("Select child category on Moto main page. Moto_main_page")
    public Moto_Category_page_Logic selectChildCategory() {
        tecDocCatalogOnMainPageMoto().scrollTo();
        childCategoryOnMotoMainPage().click();
        return page(Moto_Category_page_Logic.class);
    }

    @Step("Select child category on Moto main page. Moto_main_page")
    public Moto_Catalog_page_Logic selectMotoInHorizontalMotoSelector(String marke, String model, String motor) {
        markeOfHorizontalMotoSelector().selectOptionByValue(marke);
        modelOfHorizontalMotoSelector().selectOptionByValue(model);
        motorOfHorizontalMotoSelector().selectOptionByValue(motor);
        searchInHorizontalMotoSelector().click();
        return page(Moto_Catalog_page_Logic.class);
    }
}
