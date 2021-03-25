package ATD;

import AWS.ProductCard_aws;
import io.qameta.allure.Step;
import org.testng.Assert;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static ATD.CommonMethods.getTextFromUnVisibleElement;
import static Common.CommonMethods.waitWhileRouteContainsExpected;
import static com.codeborne.selenide.CollectionCondition.size;
import static com.codeborne.selenide.CollectionCondition.sizeGreaterThan;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;
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
        mainTitle().shouldBe(visible);
        if (compatibilityTruckBlock().isDisplayed()) {
            linkOfCompatibilityTruckAndProduct().shouldBe(visible);
        } else {
            String idOfProduct = url().replace(url().replace(url().substring(url().lastIndexOf("/")), ""), "").replaceAll("[^0-9]", "");
            executeJavaScript("window.open('about:blank','_blank')");
            switchTo().window(1);
            new ProductCard_aws(idOfProduct).openProductCardPageAndLogin().checkTruckLabel();
            switchTo().window(1).close();
            switchTo().window(0);
        }
        return this;
    }

    @Step("Check successfully LKW_Product page loading .LKW_Product_page")
    public LKW_Product_page_Logic checkSuccessfullyLKWProductPageLoading(String currentUrl) {
        breadCrumbsBlock().should(appear);
        Assert.assertTrue(url().contains(currentUrl));
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
        return this;
    }

    @Step("select brand of car in horizontal truck selector .LKW_Product_page")
    public LKW_Product_page_Logic matchingProductToSelectedTruck(String selectedBrand) {
        truckingBlockOfMatching().shouldBe(visible).hover();
        List<String> truckingBrands = new ArrayList<>(getTruckBrandsFromBLock());
        Assert.assertTrue(truckingBrands.contains(selectedBrand));
        truckingBrands.clear();
        return this;
    }

    @Step("get truck brand from block .LKW_Product_page")
    public List<String> getTruckBrandsFromBLock() {
        List<String> brands = new ArrayList<>();
        for (int i = 0; i < brandsOfTruckInMatchingBLock().size(); i++) {
            brands.add(getTextFromUnVisibleElement(brandsOfTruckInMatchingBLock().get(i)).replaceAll("\n", "").trim());
        }
        return brands;
    }

    @Step("visibility of dynamic characteristic .LKW_Product_page")
    public LKW_Product_page_Logic visibilityOfDynamicCharacteristic() {
        dynamicCharacteristic("Motorcode", "OM 936 LA").should(appear);
        return this;
    }

    @Step("visibility of EON block .LKW_Product_page")
    public LKW_Product_page_Logic visibilityOfOemBlock() {
        oenBlock().shouldBe(visible);
        return this;
    }

    @Step("lack of links in OEN block .LKW_Product_page")
    public LKW_Product_page_Logic lackOfLinksInOenBlock() {
        for (int i = 0; i < listOfOen().size(); i++) {
            listOfOen().get(i).shouldNotHave(attribute("href"));
        }
        return this;
    }


    @Step("visibility of compatible truck block .LKW_Product_page")
    public LKW_Product_page_Logic visibilityOfCompatibleTruckBLock() {
        compatibleTruckBLock().shouldBe(visible);
        return this;
    }

    @Step("check of sorting compatible truck list .LKW_Product_page")
    public LKW_Product_page_Logic checkOfSortingCompatibleTruckList() {

        List<String> listOfTruck = new ArrayList<>();
        for (int i = 0; i < compatibleTruckList().size(); i++) {
            listOfTruck.add(compatibleTruckList().get(i).getText());
        }
        List<String> sortingListOfTruck = new ArrayList<>(listOfTruck);
        Collections.sort(sortingListOfTruck);
        Assert.assertEquals(sortingListOfTruck, listOfTruck);
        return this;
    }

    @Step("visibility of models list of selected truck in compatible block .LKW_Product_page")
    public LKW_Product_page_Logic visibilityOfModelsListBlock(String titleOfTruck) {
        for (int i = 0; i < compatibleTruckList().size(); i++) {
            if (compatibleTruckList().get(i).has(exactText(titleOfTruck))) {
                compatibleTruckList().get(i).click();
            }
        }
        modelsListBlock().should(appear);
        return this;
    }

    @Step("visibility of  application block specification .LKW_Product_page")
    public LKW_Product_page_Logic visibilityOfApplicationBlockSpecification(String modelOfTruck) {
        for (int i = 0; i < compatibleTruckLinks().size(); i++) {
            if (compatibleTruckLinks().get(i).has(text(modelOfTruck))) {
                compatibleTruckLinks().get(i).click();
            }
        }
        applicationSpecificationBLock().should(appear);
        return this;
    }


    @Step("visibility of compatible truck in selector .LKW_Product_page")
    public LKW_Product_page_Logic availabilityOfCompatibleTruckInSelector() {
        List<String> brandsOfTrucks = new ArrayList<>();
        for (int i = 0; i < brandsOfTrucksInSelector().size(); i++) {
            brandsOfTrucks.add(brandsOfTrucksInSelector().get(i).getText());
        }
        if (closeCompatibleTruckModelList().isDisplayed()) {
            closeCompatibleTruckModelList().click();
        }
        for (int i = 0; i < compatibleTruckList().size(); i++) {
            if (!brandsOfTrucks.contains(compatibleTruckList().get(i).getText())) {
                compatibleTruckList().get(i).shouldNotHave(attribute("href"));
            }
        }
        return this;
    }

    @Step("open of characteristic block .LKW_Product_page")
    public LKW_Product_page_Logic openCharacteristicBlock() {
        openBlockOfCharacteristic().click();
        listOfCharacteristics().shouldHave(sizeGreaterThan(10));
        return this;
    }

    @Step("visibility of selected truck selector after click by characteristic .LKW_Product_page")
    public LKW_Product_page_Logic visibilityOfSelectedTruckSelector() {
        activeLinksOfCharacteristic().get(0).shouldBe(visible).click();
        selectedTruckSelector().should(appear);
        darkBackground().should(appear);
        return this;
    }

    @Step("visibility of related and analogues products block .LKW_Product_page")
    public LKW_Product_page_Logic visibilityOfAnaloguesAndRelatedProductsBlock() {
        relatedProductsBlock().shouldBe(visible);
        analoguesBlock().shouldBe(visible);
        return this;
    }

    @Step("visibility of analogues addition info block .LKW_Product_page")
    public LKW_Product_page_Logic visibilityOfAnaloguesAdditionInfoBlock() {
        analoguesBlock().scrollIntoView("{block: \"center\"}");
        for (int i = 0; i < imageOfAnaloguesProducts().size(); i++) {
            headlineOfAnaloguesBlock().hover();
            imageOfAnaloguesProducts().get(i).shouldBe(visible).hover();
            additionInfoBlockOfAnaloguesProduct().get(0).should(appear);
        }
        return this;
    }


    @Step("visibility of related addition info block .LKW_Product_page")
    public LKW_Product_page_Logic visibilityOfRelatedAdditionInfoBlock() {
        while (forwardLinkRelatedBlock().isDisplayed()) {
            hoverOnImageOfRelatedProduct();
            forwardLinkRelatedBlock().click();
        }
        hoverOnImageOfRelatedProduct();
        return this;
    }

    @Step("hover on image of product in related block .LKW_Product_page")
    public LKW_Product_page_Logic hoverOnImageOfRelatedProduct() {
        for (int i = 0; i < imageOfRelatedProductsBlock().size(); i++) {
            headlineOfRelatedBlock().hover();
            imageOfRelatedProductsBlock().get(i).shouldBe(visible).hover();
            additionInfoBlockOfRelatesProduct().get(0).should(appear);
        }
        return this;
    }

    @Step("get id of related product .LKW_Product_page")
    public String getIdOfRelatedProduct() {
        String idOfBtnAddToBasket = btnAddToBasketRelatedBlock().get(0).getAttribute("id");
        return idOfBtnAddToBasket;
    }

    @Step("added related product to basket .LKW_Product_page")
    public Cart_page_Logic addRelatedProductToBasket() {
        btnAddToBasketRelatedBlock().get(0).shouldBe(visible).click();
        basketDropMenu().should(appear);
        basketDropMenu().should(disappear);
        basket().click();
        return page(Cart_page_Logic.class);
    }

    @Step(": from. LKW_Product_page")
    public AutodocClub_page_Logic clickBannerAutodocClub() {
        return new Product_page_Logic().clickBannerAutodocClub();
    }

    @Step(": from. LKW_Product_page")
    public String getUrlAutodocClubFromBannerAutodocClub() {
        return new Product_page_Logic().getUrlAutodocClubFromBannerAutodocClub();
    }

}
