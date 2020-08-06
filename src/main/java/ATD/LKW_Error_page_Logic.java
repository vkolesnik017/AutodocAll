package ATD;

import io.qameta.allure.Step;

import static com.codeborne.selenide.CollectionCondition.sizeGreaterThan;
import static com.codeborne.selenide.Condition.appear;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.page;

public class LKW_Error_page_Logic extends LKW_Error_page {

    @Step("visibility of headline   .LKW_Error_page")
    public LKW_Error_page_Logic visibilityOfHeadLine() {
        headlineInHeader().shouldBe(visible);
        return this;
    }

    @Step("visibility of main error image   .LKW_Error_page")
    public LKW_Error_page_Logic visibilityOfMainErrorImage() {
        mainErrorImage().shouldBe(visible);
        return this;
    }

    @Step("presence of Parent categories block .LKW_Error_page")
    public LKW_Error_page_Logic presenceOfParentCategoriesBlock(int countOfParentCategories) {
        parentCategories().shouldHave(sizeGreaterThan(countOfParentCategories));
        return this;
    }

    @Step("visibility Of Child categories popUp of Parent Category .LKW_Error_page")
    public LKW_Error_page_Logic checkParentCategoriesOfTecDocCatalog() {
        for (int i = 0; i < parentCategories().size(); i++) {
            if (titleOfParentCategories().get(i).getText().equals("Reifen")) {
                continue;
            }
            imageOfParentCategories().get(i).shouldBe(visible);
            titleOfParentCategories().get(i).shouldBe(visible);
            parentCategories().get(i).click();
            childCategoriesPopUpOfParentCategory().get(i).shouldBe(visible);
            checkFirstLevelOfParentCategories(i);
        }
        return this;
    }


    @Step("check First Level of parent categories .LKW_Error_page")
    public LKW_Error_page_Logic checkFirstLevelOfParentCategories(int position) {
        if (childCategoriesFirstLevel().get(0).isDisplayed() && visibleIntermediateCategoryFirstLevel().get(0).isDisplayed()) {
            childCategoriesFirstLevel().shouldHave(sizeGreaterThan(0));
            checkVisibleChildCategoriesFirstLevel();
            checkIntermediateChildCategoryFirstLevel(position);
        } else if (childCategoriesFirstLevel().get(0).isDisplayed()) {
            childCategoriesFirstLevel().shouldHave(sizeGreaterThan(0));
            checkVisibleChildCategoriesFirstLevel();
        } else if (visibleIntermediateCategoryFirstLevel().get(0).isDisplayed()) {
            checkIntermediateChildCategoryFirstLevel(position);
        }
        return this;
    }

    @Step("check visible child categories .LKW_Error_page")
    public LKW_Error_page_Logic checkVisibleChildCategoriesFirstLevel() {
        for (int i = 0; i < childCategoriesFirstLevel().size(); i++) {
            imageOfVisibleChildCategoriesFirstLevel().get(i).shouldBe(visible);
            titleOfVisibleChildCategoriesFirstLevel().get(i).shouldBe(visible);

        }
        return this;
    }

    @Step("check intermediate child category first level .LKW_Error_page")
    public LKW_Error_page_Logic checkIntermediateChildCategoryFirstLevel(int position) {
        for (int j = 0; j < intermediateChildCategoriesFirstLevel(position + 1).size(); j++) {
            intermediateChildCategoriesFirstLevel(position + 1).get(j).click();
            secondLevelBlock().should(appear);
            checkSecondLevelOfParentCategories();
        }
        return this;
    }

    @Step("check Second Level of parent categories .LKW_Error_page")
    public LKW_Error_page_Logic checkSecondLevelOfParentCategories() {

        if (visibleChildCategorySecondLevel().get(0).isDisplayed() && visibleIntermediateCategorySecondLevel().get(0).isDisplayed()) {
            visibleChildCategorySecondLevel().shouldHave(sizeGreaterThan(0));
            checkVisibleChildCategoriesSecondLevel();
            checkIntermediateChildCategorySecondLevel();
        } else if (visibleChildCategorySecondLevel().get(0).isDisplayed()) {
            visibleChildCategorySecondLevel().shouldHave(sizeGreaterThan(0));
            checkVisibleChildCategoriesSecondLevel();
        } else if (visibleIntermediateCategorySecondLevel().get(0).isDisplayed()) {
            checkIntermediateChildCategorySecondLevel();
        }
        return this;
    }

    @Step("check visible child categories .LKW_Error_page")
    public LKW_Error_page_Logic checkVisibleChildCategoriesSecondLevel() {
        for (int i = 0; i < visibleChildCategoriesSecondLevel().size(); i++) {
            imageOfVisibleChildCategoriesSecondLevel().get(i).shouldBe(visible);
            titleOfVisibleChildCategoriesSecondLevel().get(i).shouldBe(visible);

        }
        return this;
    }

    @Step("check intermediate child category second level .LKW_Error_page")
    public LKW_Error_page_Logic checkIntermediateChildCategorySecondLevel() {
        for (int j = 0; j < intermediateChildCategoriesSecondLevel().size(); j++) {
            intermediateChildCategoriesSecondLevel().get(j).click();
            thirdLevelBlock().should(appear);
            checkThirdLevelOfParentCategories();
        }
        return this;
    }
    @Step("check Second Level of parent categories .LKW_Error_page")
    public LKW_Error_page_Logic checkThirdLevelOfParentCategories() {
        childCategoriesThirdLevel().shouldHave(sizeGreaterThan(0));
        return this;
    }

    @Step("transition to Child category .LKW_Error_page")
    public LKW_Category_page_Logic transitionToChildCategory(int parentCategoryPosition, int childCategoryPosition) {
        parentCategories().get(parentCategoryPosition).click();
        childCategoriesPopUpOfParentCategory().get(parentCategoryPosition).shouldBe(visible);
        childCategoriesFirstLevelForCheck().get(childCategoryPosition).shouldBe(visible).click();
        return page(LKW_Category_page_Logic.class);
    }
}
