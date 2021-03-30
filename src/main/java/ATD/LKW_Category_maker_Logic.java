package ATD;

import Common.DataBase;
import com.codeborne.selenide.ElementsCollection;
import io.qameta.allure.Step;
import org.testng.Assert;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;

import static ATD.CommonMethods.getAttributeFromUnVisibleElement;
import static Common.CommonMethods.checkingContainsUrl;
import static com.codeborne.selenide.CollectionCondition.size;
import static com.codeborne.selenide.CollectionCondition.sizeGreaterThan;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;
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
        secondLinkClick();
        checkingContainsUrl(new DataBase("ATD").getRouteByRouteName("DE", "lkw_parent_category"));
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
        modelsOfTruckInBlock().shouldHave(sizeGreaterThan(6));
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
    public LKW_Category_maker_Logic applicabilityOfTopProductToTruck(String selectedBrand, List<String> urlList) {
        for (int i = 0; i < urlList.size(); i++) {
            open(urlList.get(i));
            new LKW_Product_page_Logic().matchingProductToSelectedTruck(selectedBrand);
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

    @Step("get product urls from 'Details' button .LKW_Category_maker")
    public List<String> getProductUrls() {
        List<String> urls = btnDetails().stream().map(n -> getAttributeFromUnVisibleElement(n, "url")).collect(Collectors.toList());
        return urls;
    }

    @Step("check Titles Of TOP Auto Links. LKW_Category_maker")
    public LKW_Category_maker_Logic checkTitlesOfTopAutoLinks(List<String> expectedAutoLinks) {
        linksBlock().shouldBe(visible);
        Assert.assertEquals(topAutoLinks().size(), expectedAutoLinks.size());
        List<String> titleOfTopAutoLinks = topAutoLinks().stream().map(n -> n.getText()).collect(Collectors.toList());
        Assert.assertEquals(titleOfTopAutoLinks, expectedAutoLinks);
        return this;
    }

    @Step("check Transition Of TOP Auto Links. LKW_Category_maker")
    public LKW_Category_maker_Logic checkTransitionOfTopAutoLinks() throws IOException {
        for (int i = 0; i < topAutoLinks().size(); i++) {
            topAutoLinks().get(i).shouldBe(visible).click();
            new Category_car_list_page_Logic().checkResponseOfServer(200);
            back();
        }
        return this;
    }
}
