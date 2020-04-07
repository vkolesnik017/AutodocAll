package ATD;

import com.codeborne.selenide.ElementsCollection;
import io.qameta.allure.Step;
import org.testng.Assert;

import java.util.ArrayList;
import java.util.List;

import static com.codeborne.selenide.CollectionCondition.size;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.back;
import static com.codeborne.selenide.Selenide.page;
import static com.codeborne.selenide.WebDriverRunner.url;

public class LKW_Category_page_Logic extends LKW_Category_page {
    @Step("check successfully child category page loading .LKW_Category_page ")
    public LKW_Category_page_Logic checkSuccessfullyChildCategoryPageLoading() {
        imageOfChildCategory().shouldBe(visible);
        Assert.assertEquals(url(), "https://lkwteile.autodoc.de/ersatzteile/olfilter-200157");
        return this;
    }

    @Step("Check links in bread crumbs block .LKW_Category_page")
    public LKW_Category_page_Logic checkLinksInBreadCrumbsBlock() {
        breadCrumbsLinks().shouldHave(size(6));
        firstLinkOfBreadCrumbsBlock().shouldBe(visible);
        secondLinkOfBreadCrumbsBlock().shouldHave(exactText("Filter"));
        thirdLinkOfBreadCrumbsBlock().shouldHave(exactText("Lenkgetriebehydraulikfilter")).shouldNotHave(attribute("href"));
        fourthLinkOfBreadCrumbsBlock().shouldHave(exactText("Hersteller auswählen")).shouldNotHave(attribute("href"));
        fifthLinkOfBreadCrumbsBlock().shouldHave(exactText("Modell auswählen")).shouldNotHave(attribute("href"));
        sixthLinkOfBreadCrumbsBlock().shouldHave(exactText("Ersatzteile kaufen")).shouldNotHave(attribute("href"));
        return this;
    }

    @Step("Check  link click in bread crumbs block .LKW_Category_page")
    public LKW_Category_page_Logic checkLinkClickInBreadCrumbsBlock() {
        firstLinkClick().checkSuccessfullyLKWCategoriesPageLoading();
        back();
        secondLinkClick().checkSuccessfullyLKWParentCategoryPageLoading("https://lkwteile.autodoc.de/ersatzteile/filter");

        return this;
    }

    @Step("Click on first link in bread crumbs block .LKW_Category_page")
    public LKW_Categories_page_Logic firstLinkClick() {
        firstLinkOfBreadCrumbsBlock().click();
        return page(LKW_Categories_page_Logic.class);
    }

    @Step("Click on second link in bread crumbs block .LKW_Category_page")
    public LKW_Parent_Category_page_Logic secondLinkClick() {
        secondLinkOfBreadCrumbsBlock().click();
        return page(LKW_Parent_Category_page_Logic.class);
    }

    @Step("check of presence child categories in TOP Child Category block in sidebar .LKW_Category_page ")
    public LKW_Category_page_Logic presenceOfChildCategoriesInTopBlockInSidebar() {
        topChildCategoryBlockInSidebar().shouldBe(visible);
        List<String> titleOfCategoriesForCheck = new ArrayList<>(titleOfChildCategoriesForCheck());
        List<String> titleOfCategoriesFromSideBar = new ArrayList<>(getTitleOfChildCategoriesLinksInSideBar(linksOfChildCategoriesOfTopChildBlockInSidebar()));
        checkOfPresenceCategoriesInChildCategoryBlockInSidebar(titleOfCategoriesForCheck, titleOfCategoriesFromSideBar);
        return this;
    }

    @Step("title of Child categories links in top Child Category block in sidebar for check .LKW_Category_page")
    public List<String> titleOfChildCategoriesForCheck() {
        List<String> titleOfCheckCategories = new ArrayList<>();
        titleOfCheckCategories.add("Kraftstofffilter");
        titleOfCheckCategories.add("Luftfilter");
        titleOfCheckCategories.add("Innenraumluftfilter");
        titleOfCheckCategories.add("Filter-Satz");
        titleOfCheckCategories.add("Lenkgetriebehydraulikfilter");
        titleOfCheckCategories.add("Getriebehydraulikfilter");
        titleOfCheckCategories.add("Kühlmittelfilter");
        return titleOfCheckCategories;
    }

    @Step("get title of Child categories links in Top Child category block in sideBar .LKW_Category_page")
    public List<String> getTitleOfChildCategoriesLinksInSideBar(ElementsCollection listOfChildCategoriesLinks) {
        List<String> titleOfChildCategoriesLinksInSideBar = new ArrayList<>();
        for (int i = 0; i < listOfChildCategoriesLinks.size(); i++) {
            titleOfChildCategoriesLinksInSideBar.add(listOfChildCategoriesLinks.get(i).getText());
        }
        return titleOfChildCategoriesLinksInSideBar;
    }

    @Step("check of presence categories in Top child category block in sidebar .LKW_Category_page")
    public LKW_Category_page_Logic checkOfPresenceCategoriesInChildCategoryBlockInSidebar(List<String> listOfExpectedCategories, List<String> listOfActualCategories) {
        Assert.assertTrue(listOfExpectedCategories.containsAll(listOfActualCategories));
        return this;
    }


    @Step("click on brand .LKW_Category_page")
    public LKW_Category_brand_page_Logic clickOnBrand(String titleOfBrand) {
        titleOfBrand(titleOfBrand).click();
        return page(LKW_Category_brand_page_Logic.class);
    }

}
