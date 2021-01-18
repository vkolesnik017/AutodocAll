package ATD;

import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.page;

public class LKW_FaqHash_page_Logic extends LKW_FaqHash_page{

    @Step("presence of Oil category .LKW_FaqHash_page")
    public LKW_FaqHash_page_Logic presenceOfOilCategory() {
        imageOfParentCategories().get(0).shouldBe(visible);
        titleOfParentCategories().get(0).shouldBe(visible).shouldHave(exactText("Öle & Flüssigkeiten"));
        return this;
    }

    @Step("click on Oil parent category .LKW_FaqHash_page")
    public LKW_FaqHash_page_Logic clickOnOilParentCategory() {
        imageOfParentCategories().get(0).shouldBe(visible).click();
        popUpOfParentCategories().get(0).shouldBe(visible);
        visibleChildCategoriesPopUpOfParentCategory().shouldHaveSize(1);
        return this;
    }

    @Step("click on Oil child category .LKW_FaqHash_page")
    public LKW_Category_car_list_page_Logic clickOnOilChildCategory() {
        visibleChildCategories().get(3).shouldBe(visible).click();
        return page(LKW_Category_car_list_page_Logic.class);
    }
}
