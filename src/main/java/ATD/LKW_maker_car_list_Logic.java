package ATD;

import io.qameta.allure.Step;
import org.testng.Assert;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.page;
import static com.codeborne.selenide.WebDriverRunner.url;

public class LKW_maker_car_list_Logic  extends LKW_maker_car_list{

    @Step("check successfully child category page loading. LKW_maker_car_list ")
    public LKW_maker_car_list_Logic checkSuccessfullyMakerCarListPageLoading(String currentUrl) {
        tecDocCatalog().shouldBe(visible);
        Assert.assertEquals(url(), currentUrl);
        return this;
    }
    @Step("input article of product in search_field in header. LKW_maker_car_list ")
    public LKW_maker_car_list_Logic inputArticleOfProductInSearchField() {
        inputSearchInHeader().setValue("4.90930");
        return this;
    }

    @Step("select product in search_field drop menu. LKW_maker_car_list ")
    public LKW_Product_page_Logic selectProductInSearchDropMenu() {
        dropMenuOfSearchFieldInHeader().shouldBe(visible);
        productsInDropMenuOfSearchField().get(0).click();
        return page(LKW_Product_page_Logic.class);
    }

}
