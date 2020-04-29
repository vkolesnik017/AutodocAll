package ATD;

import AWS.ProductCard_aws;
import io.qameta.allure.Step;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;

import javax.swing.*;

import static com.codeborne.selenide.CollectionCondition.size;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.WebDriverRunner.source;
import static com.codeborne.selenide.WebDriverRunner.url;

public class LKW_Product_page_Logic extends LKW_Product_page {

    @Step("Check links in bread crumbs block .LKW_Product_page")
    public LKW_Product_page_Logic checkLinksInBreadCrumbsBlock() {
        breadCrumbsLinks().shouldHave(size(4));
        firstLinkOfBreadCrumbsBlock().shouldBe(visible);
        secondLinkOfBreadCrumbsBlock().shouldHave(exactText("Filter"));
        thirdLinkOfBreadCrumbsBlock().shouldHave(exactText("Innenraumluftfilter"));
        fourthLinkOfBreadCrumbsBlock().shouldHave(exactText("HENGST FILTER Filter, Innenraumluft")).shouldNotHave(attribute("href"));
        return this;
    }

    @Step("Check links in bread crumbs block with not active product .LKW_Product_page")
    public LKW_Product_page_Logic checkLinksInBreadCrumbsBlockWithNotActiveProduct() {
        breadCrumbsLinks().shouldHave(size(2));
        firstLinkOfBreadCrumbsBlock().shouldBe(visible);
        secondLinkOfBreadCrumbsBlock().shouldHave(exactText("PROKOM Relais"));
        return this;
    }

    @Step("Check links in bread crumbs block with selecting truck .LKW_Product_page")
    public LKW_Category_car_list_page_Logic checkLinksInBreadCrumbsWithTruck() {
        selectTruckInHorizontalSelector("24", "4176", "1003688");
        titleInTruckSelectorHeader().shouldHave(text("Dieses Produkt passt zu Ihrem"));
        thirdLinkOfBreadCrumbsBlock().click();
        return page(LKW_Category_car_list_page_Logic.class);
    }


    @Step("Select truck in horizontal truck selector .LKW_Product_page")
    public LKW_Product_page_Logic selectTruckInHorizontalSelector(String marke, String model, String motor) {
        markeInHorizontalTruckSelector().selectOptionByValue(marke);
        modelInHorizontalTruckSelector().selectOptionByValue(model);
        motorInHorizontalTruckSelector().selectOptionByValue(motor);
        searchBtnInHorizontalTruckSelector().click();
        return this;
    }

    @Step("Select tecDoc Catalog in bread crumbs .LKW_Product_page")
    public LKW_Categories_page_Logic goToTecDocCatalogFromBreadCrumbs() {
        firstLinkOfBreadCrumbsBlock().click();
        return page(LKW_Categories_page_Logic.class);
    }

    @Step("checking the compatibility of goods and cars .LKW_Product_page")
    public LKW_Product_page_Logic checkCompatibilityProductAndTruck() {
        breadCrumbsBlock().shouldBe(visible);
        if (compatibilityTruckBlock().isDisplayed()) {
            linkOfCompatibilityTruckAndProduct().shouldBe(visible);
        } else {
            String idOfProduct = url().replaceAll("[^0-9]", "");
            executeJavaScript("window.open('about:blank','_blank')");
            switchTo().window(1);
            new ProductCard_aws(idOfProduct).openProductCardPageAndLogin();
            switchTo().window(1).close();
            switchTo().window(0);
        }
        return this;
    }

    @Step("Check successfully LKW_Product page loading .LKW_Product_page")
    public LKW_Product_page_Logic checkSuccessfullyLKWProductPageLoading(String currentUrl) {
        breadCrumbsBlock().shouldBe(visible);
        Assert.assertTrue(url().equals(currentUrl));
        return this;
    }


    @Step("Check visibility of message about compatibility truck and product .LKW_Product_page")
    public LKW_Product_page_Logic visibilityOfMessageAboutCompatibilityTruckAndProduct(String exactText) {
        titleInTruckSelectorHeader().shouldBe(visible).shouldHave(exactText(exactText));
        return this;
    }

    @Step("Check visibility of message about compatibility truck and product .LKW_Product_page")
    public LKW_Product_page_Logic visibilityOfTitleAboutNotCompatibilityTruckAndProduct(String exactText) {
        titleInTruckSelectorHeaderWithNotSuitableCar().shouldBe(visible).shouldHave(exactText(exactText));
        return this;
    }

    @Step("Check visibility of message about compatibility truck and product  in Car compatibility block .LKW_Product_page")
    public LKW_Product_page_Logic visibilityOfSuitableCarInCarCompatibilityBlock() {
        carCompatibilityBlock().scrollIntoView("{block: \"center\"}");
        titleOfSuitableCarInCompatibilityBlock().shouldHave(exactText("DAF 65 CF FA 65 CF 180, Baujahr 02.1998 - 12.2000, 6240 , 181 PS")).shouldHave(cssValue("color", "rgba(74, 182, 73, 1)"));
        return this;
    }

    @Step("Check сomparison of selected not suitable car and product .LKW_Product_page")
    public LKW_maker_car_list_Logic сomparisonOfSelectedNotSuitableCarAndProduct() {
        selectTruckInHorizontalSelector("2242", "8959", "1012748");
        return page(LKW_maker_car_list_Logic.class);
    }

    @Step("visibility of tooltip for marke_field in selector  .LKW_Product_page")
    public LKW_Product_page_Logic visibilityOfTooltipForMarkeFieldInHorizontalSelector() {
        markeInHorizontalTruckSelector().shouldHave(value("0"));
        searchBtnInHorizontalTruckSelector().click();
        tooltipForFieldInHorizontalCarSelector().shouldBe(visible);
        return this;
    }

    @Step("visibility of tooltip for model_field in selector  .LKW_Product_page")
    public LKW_Product_page_Logic visibilityOfTooltipForModelFieldInHorizontalSelector() {
        markeInHorizontalTruckSelector().selectOptionByValue("24");
        searchBtnInHorizontalTruckSelector().click();
        tooltipForFieldInHorizontalCarSelector().shouldBe(visible);
        return this;
    }


    @Step("visibility of tooltip for motor_field in selector  .LKW_Product_page")
    public LKW_Product_page_Logic visibilityOfTooltipForMotorFieldInHorizontalSelector() {
        modelInHorizontalTruckSelector().selectOptionByValue("714");
        searchBtnInHorizontalTruckSelector().click();
        tooltipForFieldInHorizontalCarSelector().shouldBe(visible);
        return this;
    }


    @Step("select brand of car in horizontal truck selector .LKW_Product_page")
    public LKW_Product_page_Logic selectBrandOfCarInHorizontalSelector(String valueOfBrand) {
        markeInHorizontalTruckSelector().selectOptionByValue(valueOfBrand);
        markeInHorizontalTruckSelector().shouldHave(value(valueOfBrand));
        return this;
    }

    @Step("reset of car brand field in vertical selector .LKW_Product_page")
    public LKW_Product_page_Logic resetOfCarBrandFieldInVerticalSelector() {
        String currentUrl = url();
        modelInHorizontalTruckSelector().shouldHave(value("0")).shouldBe(visible);
        actions().moveToElement(resetBtnInHorizontalCarSelector(), 0, 10).click().build().perform();
        Assert.assertTrue(currentUrl.equals(url()));
        markeInHorizontalTruckSelector().shouldBe(visible).shouldHave(value("0"));
        modelInHorizontalTruckSelector().shouldHave(value("0"));
        return this;
    }

}
