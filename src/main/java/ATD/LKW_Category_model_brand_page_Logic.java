package ATD;

import io.qameta.allure.Step;
import org.testng.Assert;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.WebDriverRunner.url;

public class LKW_Category_model_brand_page_Logic  extends LKW_Category_model_brand_page{

    @Step("Check successfully LKW_Category car list page loading .LKW_Category_model_brand_page")
    public LKW_Category_model_brand_page_Logic checkSuccessfullyLKWCategoryModelBrandPageLoading(String currentUrl) {
        listingOfProducts().shouldBe(visible);
        Assert.assertTrue(url().contains(currentUrl));
        return this;
    }
}
