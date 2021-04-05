package ATD;

import com.codeborne.selenide.CollectionCondition;
import io.qameta.allure.Step;

import java.io.IOException;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.back;

public class Category_model_brand_page_Logic extends Category_model_brand_page {

    @Step("display Of Brands Block. Category_model_brand_page")
    public Category_model_brand_page_Logic displayOfBrandsBlock() {
        brandsBlock().shouldBe(visible);
        return this;
    }

    @Step("check Count Of Visible Brands. Category_model_brand_page")
    public Category_model_brand_page_Logic checkCountOfVisibleBrands(int expectedSize) {
        visibleBrands().shouldHaveSize(expectedSize);
        return this;
    }

    @Step("click On button 'More' Of Brands Block. Category_model_brand_page")
    public Category_model_brand_page_Logic clickOnBtnMoreOfBrandsBlock() {
        btnMoreOfBrandsBlock().shouldBe(visible).click();
        return this;
    }

    @Step("check Transition Of TOP Auto Links. Category_model_brand_page")
    public Category_model_brand_page_Logic checkTransitionOfBrandsLinks() throws IOException {
        int countOfBrands = visibleBrands().size();
        for (int i = 0; i < countOfBrands; i++) {
            displayOfBrandsBlock();
            if (btnMoreOfBrandsBlock().isDisplayed()) {
                btnMoreOfBrandsBlock().click();
            }
            visibleBrands().shouldHave(CollectionCondition.sizeGreaterThan(6));
            visibleBrands().get(i).click();
            new Category_car_list_page_Logic().checkResponseOfServer(200);
            back();
        }
        return this;
    }
}
