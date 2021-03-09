package ATD;

import io.qameta.allure.Step;
import org.testng.Assert;

import java.util.List;
import java.util.stream.Collectors;

import static ATD.CommonMethods.getTextFromUnVisibleElement;
import static com.codeborne.selenide.Condition.*;

public class Accessories_listing_criteria_page_Logic extends Accessories_listing_criteria_page {


    @Step("check Headline of generics block . Accessories_listing_criteria_page")
    public Accessories_listing_criteria_page_Logic checkHeadlineOfGenericsBlock(String headline) {
        headlineOfGenericsBlock().shouldBe(visible).shouldHave(text(headline));
        return this;
    }

    @Step("set Category In SideBar by position. Accessories_listing_criteria_page")
    public Accessories_listing_criteria_page_Logic setCategoryInSideBarByPosition(int position) {
        categoryFilters().get(position).shouldBe(visible).click();
        return this;
    }

    @Step("get Category. Accessories_listing_criteria_page")
    public String getCategory(int position) {
        return categoryFilters().get(position).getText();
    }

    @Step("check Products With Selected Category Filter. Accessories_listing_criteria_page")
    public Accessories_listing_criteria_page_Logic checkProductsWithSelectedCategoryFilter(String categoryFilter) {
        for (int i = 0; i < listingOfProducts().size(); i++) {
            titleOfProducts().get(i).shouldHave(text(categoryFilter));
        }
        return this;
    }

    @Step("check Count Of Product In Characteristic Table View. Accessories_listing_criteria_page")
    public Accessories_listing_criteria_page_Logic checkCountOfProductInCharacteristicTableView(String expectedCount) {
        List<String> count = valueOfCountInDescription().stream().map(n -> getTextFromUnVisibleElement(n).trim()).collect(Collectors.toList());
        for (int i = 0; i < count.size(); i++) {
            Assert.assertEquals(count.get(i), expectedCount);
        }
        return this;
    }

    @Step("set Color filter by title in sideBar. Accessories_listing_criteria_page")
    public Accessories_listing_criteria_page_Logic setColorFilterByTitleInSideBar(String expectedColor) {
        colorFilterBlockInSideBar().shouldBe(visible).scrollTo();
        if (btnMoreOfColorFilterInSideBar().isDisplayed()) {
            btnMoreOfColorFilterInSideBar().click();
        }
        colorFilterByTitle(expectedColor).shouldBe(visible).click();
        return this;
    }

    @Step("set Color filter by title in sideBar. Accessories_listing_criteria_page")
    public Accessories_listing_criteria_page_Logic checkProductsWithSelectedColorFilter(String expectedColor) {
        for (int i = 0; i < colorCharacteristicOfProduct().size(); i++) {
            colorCharacteristicOfProduct().get(i).shouldHave(text(expectedColor));
        }
        return this;
    }

}
