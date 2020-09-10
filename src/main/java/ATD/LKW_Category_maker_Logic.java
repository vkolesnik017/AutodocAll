package ATD;

import Common.DataBase;
import com.codeborne.selenide.ElementsCollection;
import io.qameta.allure.Step;
import org.testng.Assert;

import java.sql.SQLException;

import static ATD.CommonMethods.checkingContainsUrl;
import static com.codeborne.selenide.CollectionCondition.size;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.back;
import static com.codeborne.selenide.Selenide.page;
import static com.codeborne.selenide.WebDriverRunner.url;

public class LKW_Category_maker_Logic extends LKW_Category_maker {

    @Step("check successfully child category page loading. LKW_Category_maker ")
    public LKW_Category_maker_Logic checkSuccessfullyCategoryMakerPageLoading(String currentUrl) {
        childCategoryBlockSideBar().shouldBe(visible);
        Assert.assertEquals(url(), currentUrl);
        return this;
    }

    @Step("Check the presence of  Breadcrumbs block .LKW_Category_maker")
    public LKW_Category_maker_Logic checkOfPresenceBreadCrumbsBlock() {
        breadCrumbsBlock().shouldBe(visible);
        return this;
    }

    @Step("Check links in bread crumbs block .LKW_Category_maker")
    public LKW_Category_maker_Logic checkLinksInBreadCrumbsBlock() {
        breadCrumbsLinks().shouldHave(size(6));
        firstLinkOfBreadCrumbsBlock().shouldBe(visible);
        secondLinkOfBreadCrumbsBlock().shouldHave(exactText("Filter"));
        thirdLinkOfBreadCrumbsBlock().shouldHave(exactText("ÖLFILTER"));
        fourthLinkOfBreadCrumbsBlock().shouldHave(exactText("DAF")).shouldNotHave(attribute("href"));
        fifthLinkOfBreadCrumbsBlock().shouldHave(exactText("Baureihe auswählen")).shouldNotHave(attribute("href"));
        sixthLinkOfBreadCrumbsBlock().shouldHave(exactText("Typ wählen")).shouldNotHave(attribute("href"));
        return this;
    }

    @Step("Check  link click in bread crumbs block .LKW_Category_maker")
    public LKW_Category_maker_Logic checkLinkClickInBreadCrumbsBlock() throws SQLException {
        firstLinkClick().checkSuccessfullyLKWCategoriesPageLoading();
        back();
        secondLinkClick();  checkingContainsUrl(new DataBase().getRouteByRouteName("DE","lkw_parent_category"));
        back();
        thirdLinkClick().checkSuccessfullyChildCategoryPageLoading();
        return this;
    }

    @Step("Click on first link in bread crumbs block .LKW_Category_maker")
    public LKW_Categories_page_Logic firstLinkClick() {
        firstLinkOfBreadCrumbsBlock().click();
        return page(LKW_Categories_page_Logic.class);
    }

    @Step("Click on second link in bread crumbs block .LKW_Category_maker")
    public LKW_Parent_Category_page_Logic secondLinkClick() {
        secondLinkOfBreadCrumbsBlock().click();
        return page(LKW_Parent_Category_page_Logic.class);
    }

    @Step("Click on third link in bread crumbs block .LKW_Category_maker")
    public LKW_Category_page_Logic thirdLinkClick() {
        thirdLinkOfBreadCrumbsBlock().click();
        return page(LKW_Category_page_Logic.class);
    }

    @Step("visibility of headline   .LKW_Category_maker")
    public LKW_Category_maker_Logic visibilityOfHeadLine() {
        headlineInHeader().shouldBe(visible);
        return this;
    }

    @Step("click on brand .LKW_Category_maker")
    public LKW_Category_maker_brand_page_Logic clickOnBrand(String titleOfBrand) {
        titleOfBrand(titleOfBrand).click();
        return page(LKW_Category_maker_brand_page_Logic.class);
    }


    @Step("reset of vertical selector   .LKW_Category_maker")
    public LKW_Category_maker_Logic setupDefaultValueForVerticalSelector() {
        if (!markeInVerticalCarSelector().has(value("0"))) {
            markeInVerticalCarSelector().selectOptionByValue("0");
        }
        return this;
    }


    @Step("visibility of tooltip for marke_field in selector  .LKW_Category_maker")
    public LKW_Category_maker_Logic visibilityOfTooltipForMarkeFieldInSelector() {
        btnSearchInVerticalCarSelector().click();
        tooltipForMarkeFieldInVerticalCarSelector().shouldBe(visible);
        return this;
    }

    @Step("visibility of tooltip for model_field in selector  .LKW_Category_maker")
    public LKW_Category_maker_Logic visibilityOfTooltipForModelFieldInSelector() {
        markeInVerticalCarSelector().selectOptionByValue("24");
        btnSearchInVerticalCarSelector().click();
        tooltipForMarkeFieldInVerticalCarSelector().shouldBe(visible);
        return this;
    }


    @Step("visibility of tooltip for motor_field in selector  .LKW_Category_maker")
    public LKW_Category_maker_Logic visibilityOfTooltipForMotorFieldInSelector() {
        motorInVerticalCarSelector().selectOptionByValue("714");
        btnSearchInVerticalCarSelector().click();
        tooltipForMarkeFieldInVerticalCarSelector().shouldBe(visible);
        return this;
    }

    @Step("select brand of car in vertical truck selector .LKW_Category_maker")
    public LKW_Category_maker_Logic selectBrandOfCarInVerticalSelector(String valueOfBrand) {
        markeInVerticalCarSelector().selectOptionByValue(valueOfBrand);
        markeInVerticalCarSelector().shouldHave(value(valueOfBrand));
        return this;
    }

    @Step("reset of car brand field in vertical selector .LKW_Category_maker")
    public LKW_Category_page_Logic resetOfVerticalSelector() {
        resetBtnInVerticalCarSelector().click();
        headLine().should(disappear);
        return page(LKW_Category_page_Logic.class);
    }

    @Step("availability of  TOP  brands block .LKW_Category_maker")
    public LKW_Category_maker_Logic availabilityOfTopBrandsBlock() {
        topBrandsBlock().shouldBe(visible);
        return this;
    }


    @Step("availability of  elements in Top brands block .LKW_Category_maker")
    public LKW_Category_maker_Logic availabilityOfElementsInTopBrandsBlock() {
        brandsInTopBrandsBlock().shouldHaveSize(6);
        for (int i = 0; i < brandsInTopBrandsBlock().size(); i++) {
            imageOfBrandsInTopBrandsBlock().get(i).shouldBe(visible);
            titleOfBrandsInTopBrandsBlock().get(i).shouldBe(visible);
            yearOfBrandsInTopBrandsBlock().get(i).shouldBe(visible);
        }
        return this;
    }

    @Step("check transition at icon of truck model in TOP brands block .LKW_Category_maker")
    public LKW_Category_car_list_page_Logic checkTransitionAtIconOfTruckModel() {
        modelOfTruckInTopBlock("F 10").shouldBe(visible).click();
        return page(LKW_Category_car_list_page_Logic.class);
    }

    @Step("click at link More for opening models block .LKW_Category_maker")
    public LKW_Category_maker_Logic openOfModelsBlock() {
        linkMoreOfTopBrandBlock().click();
        return this;
    }

    @Step("visibility of models block in open condition .LKW_Category_maker")
    public LKW_Category_maker_Logic modelBlockInOpenCondition() {
        modelsOfTruckInBlock().shouldHaveSize(37);
        return this;
    }

    @Step("close of truck models block .LKW_Category_maker")
    public LKW_Category_maker_Logic closeOfModelsBlock() {
        linkLessOfModelsBlock().scrollTo().click();
        modelsOfTruckInBlock().shouldHaveSize(6);
        return this;
    }

    @Step("presence of headline and two advantages in Advantage block .LKW_Category_maker")
    public LKW_Category_maker_Logic presenceOfHeadlineAndAdvantagesInBlock() {
        headlineOfAdvantageBlock().shouldBe(visible);
        advantagesInBlock().shouldHaveSize(2);
        return this;
    }


    @Step("applicability of top product to truck on Product page .LKW_Category_maker")
    public LKW_Category_maker_Logic applicabilityOfTopProductToTruck(String selectedBrand) {
        for (int i = 0; i < imageOfTopProduct().size(); i++) {
            clickOnImageOfTopProduct(imageOfTopProduct(), i).matchingProductToSelectedTruck(selectedBrand);
            back();
        }
        return this;
    }

    @Step("applicability of top product to truck on Product page .LKW_Category_maker")
    public LKW_Product_page_Logic clickOnImageOfTopProduct(ElementsCollection topProduct, int position) {
        topProduct.get(position).click();
        return page(LKW_Product_page_Logic.class);
    }


    @Step("get selected brand of truck from selector .LKW_Category_maker")
    public String getSelectedTruck() {
        String brandOfTruck = markeInVerticalCarSelector().getText();
        return brandOfTruck;
    }
}
