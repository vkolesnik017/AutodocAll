package ATD;

import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.page;

public class Moto_Parent_Category_maker_page_Logic  extends Moto_Parent_Category_maker_page{

    @Step(" reset of motorcycle selector .Moto_Parent_Category_maker_page")
    public Moto_Parent_Category_page_Logic resetOfMotoSelector() {
        btnResetOfSelector().shouldBe(visible).click();
        return page(Moto_Parent_Category_page_Logic.class);
    }

    @Step(" select motorcycle model in selector .Moto_Parent_Category_maker_page")
    public Moto_Parent_Category_maker_page_Logic selectMotoModel(String model) {
        brandOfMotoField().shouldNotHave(exactValue("0"));
        modelFiledInSelector().shouldBe(visible).selectOptionByValue(model);
        return this;
    }

    @Step(" select motorcycle motor in selector .Moto_Parent_Category_maker_page")
    public Moto_Parent_Category_maker_page_Logic selectMotoMotor(String motor) {
        motorFiledInSelector().shouldBe(visible).selectOptionByValue(motor);
        return this;
    }

    @Step(" click on Search button in selector .Moto_Parent_Category_maker_page")
    public Moto_Catalog_page_Logic clickOnSearchButton() {
        searchButton().click();
        return page(Moto_Catalog_page_Logic.class);
    }

    @Step(" presence of exact text in headline at child category block  .Moto_Parent_Category_maker_page")
    public Moto_Parent_Category_maker_page_Logic visibilityOfHeadlineAtChildCategoryBlock(String text) {
        headlineOfChildCategoryBlock().shouldBe(visible).shouldHave(exactText(text));
        return this;
    }

    @Step(" click on child Category  .Moto_Parent_Category_maker_page")
    public Moto_Category_maker_page_Logic clickOnChildCategory(int position) {
        linksOfChildCategoriesList().get(position).shouldBe(visible).click();
        return page(Moto_Category_maker_page_Logic.class);
    }


}
