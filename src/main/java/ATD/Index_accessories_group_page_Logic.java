package ATD;


import com.codeborne.selenide.ElementsCollection;
import io.qameta.allure.Step;

import java.io.IOException;
import java.util.ArrayList;
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
    public Index_accessories_group_page_Logic checkCategoriesForServerResponses200( List<String> allCategories) throws IOException {
        CommonMethods.checkCategoriesForServerResponses200(allCategories);
        return this;
    }


}
