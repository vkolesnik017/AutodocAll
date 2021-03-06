package ATD;


import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static com.codeborne.selenide.Condition.attribute;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.page;

public class Index_accessories_group_page_Logic extends Index_accessories_group_page {


    @Step("Checking presence bread crumbs block. Index_accessories_group_page")
    public Index_accessories_group_page_Logic checkingPresenceBreadCrumbsBlock() {
        blockBreadCrumbs().shouldBe(visible);
        return this;
    }

    @Step("Click first bread crumb. Index_accessories_group_page")
    public Categories_page_Logic clickFirstBreadCrumb() {
        firstBreadCrumb().click();
        return page(Categories_page_Logic.class);
    }

    @Step("Click second bread crumb. Index_accessories_group_page")
    public Index_accessories_page_Logic clickSecondBreadCrumb() {
        secondBreadCrumb().click();
        return page(Index_accessories_page_Logic.class);
    }

    @Step("Checking presence and not clickable third bread crumb. Index_accessories_group_page")
    public Index_accessories_group_page_Logic checkingPresenceAndNotClickableThirdBreadCrumb() {
        thirdBreadCrumb().shouldBe(visible).shouldNotBe(attribute("href")).shouldBe(attribute("content"));
        return this;
    }

    @Step(": from. Index_accessories_group_page")
    public ArrayList<String> getHrefOrUrlCategoriesThenWriteToList(ElementsCollection categories) {
        return CommonMethods.getHrefOrUrlCategoriesThenWriteToList(categories);
    }

    @Step(": from. Index_accessories_group_page")
    public Index_accessories_group_page_Logic checkCategoriesForServerResponses200(List<String> allCategories) throws IOException {
        CommonMethods.checkCategoriesForServerResponses200(allCategories);
        return this;
    }

    @Step("Checking presence of accessorize block. Index_accessories_group_page")
    public Index_accessories_group_page_Logic checkingPresenceAccessorizeBlock() {
        blockAccessorizeCatalog().shouldBe(visible);
        return this;
    }

    @Step("Get name first Category in block logic union.Index_accessories_group_page")
    public String getNameFirstCategoryInBlockUnionLogic() {
        return firstElementInBlockAccessorizeCatalog().getText();
    }

    @Step("Click first category in a logical union. Index_accessories_group_page")
    public Listing_accessories_page_Logic clicksOnFirstCategory() {
        firstElementInBlockAccessorizeCatalog().shouldBe(visible).click();
        return page(Listing_accessories_page_Logic.class);
    }

    @Step("Get all categories name. Index_accessories_group_page")
    public ArrayList<String> getAllCategoriesName() {
        ArrayList<String> nameCategories = new ArrayList<>();
        for (SelenideElement element : mainCategories()) {
            String name = element.getText();
            nameCategories.add(name);
            Collections.sort(nameCategories);
        }
        return nameCategories;
    }

}
