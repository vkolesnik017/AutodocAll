package ATD;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.testng.Assert;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import static ATD.CommonMethods.*;
import static com.codeborne.selenide.CollectionCondition.sizeGreaterThan;
import static com.codeborne.selenide.CollectionCondition.sizeLessThanOrEqual;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;

public class Tyres_feature_page_Logic extends Tyres_feature_page {

    @Step("checkingAbsenceOfCurrentLinkInLinkungBlock. Tyres_feature_page")
    public Tyres_feature_page_Logic checkingAbsenceOfCurrentLinkInLinkingBlock(String url, String titleFromMainBlock) {
        mainHeadline().shouldBe(visible).shouldNotHave(exactText(titleFromMainBlock));
        linkingBlock().scrollIntoView("{block: \"center\"}");
        presenceOfTitleLinkingBlock();
        presenceOfTitleLinkingBlock();
        List<String> urlsOfLinkingBlock = btnMoreOfLinkingBlock().stream().map(n -> getAttributeFromUnVisibleElement(n, "url")).collect(Collectors.toList());
        Assert.assertFalse(urlsOfLinkingBlock.contains(url));
        presenceOfTitleLinkingBlock();
        return this;
    }

    @Step("presence of linking block. Tyres_feature_page")
    public Tyres_feature_page_Logic presenceOfLinkingBlock() {
        linkingBlock().shouldBe(visible);
        return this;
    }

    @Step("check swipe by click on paginator. Tyres_feature_pagee")
    public Tyres_feature_page_Logic checkSwipeByClick() {
        linkingBlock().scrollIntoView("{block: \"center\"}");
        String titleOfFirstLinkingBlock = visibleTitleOfLinkingBlocks().get(0).shouldBe(visible).getText();
        btnPaginatorOfLinkingBlock().get(1).click();
        presenceOfTitleLinkingBlock();
        presenceOfTitleLinkingBlock();
        visibleTitleOfLinkingBlocks().get(0).shouldNotHave(exactText(titleOfFirstLinkingBlock));
        presenceOfTitleLinkingBlock();
        presenceOfTitleLinkingBlock();
        btnPaginatorOfLinkingBlock().get(0).click();
        presenceOfTitleLinkingBlock();
        presenceOfTitleLinkingBlock();
        visibleTitleOfLinkingBlocks().get(0).shouldHave(exactText(titleOfFirstLinkingBlock));
        return this;
    }

    @Step("check swipe by moving block. Tyres_feature_page")
    public Tyres_feature_page_Logic checkSwipeByMoving() {
        linkingBlock().scrollIntoView("{block: \"center\"}");
        swipeByMovingToRight();
        swipeByMovingToLeft();
        return this;
    }

    @Step("swipe by moving to right in Linking Block. Tyres_feature_page")
    public Tyres_feature_page_Logic swipeByMovingToRight() {
        linkingBlock().scrollIntoView("{block: \"center\"}");
        presenceOfTitleLinkingBlock();
        String titleOfFirstLinkingBlock = visibleTitleOfLinkingBlocks().get(0).getText();
        actions().clickAndHold(visibleTitleOfLinkingBlocks().get(1)).moveToElement(visibleTitleOfLinkingBlocks().get(0)).release(visibleTitleOfLinkingBlocks().get(1)).build().perform();
        visibleTitleOfLinkingBlocks().get(0).shouldNotHave(exactText(titleOfFirstLinkingBlock));
        return this;
    }

    @Step("swipe by moving to left in Linking Block. Tyres_feature_page")
    public Tyres_feature_page_Logic swipeByMovingToLeft() {
        linkingBlock().scrollIntoView("{block: \"center\"}");
        presenceOfTitleLinkingBlock();
        headLineOfLinkingBlock().hover();
        String titleOfFirstLinkingBlock = visibleTitleOfLinkingBlocks().get(0).getText();
        actions().clickAndHold(visibleTitleOfLinkingBlocks().get(0)).moveToElement(visibleTitleOfLinkingBlocks().get(1)).release(visibleTitleOfLinkingBlocks().get(0)).build().perform();
        presenceOfTitleLinkingBlock();
        visibleTitleOfLinkingBlocks().get(0).shouldNotHave(exactText(titleOfFirstLinkingBlock));
        return this;
    }

    @Step("presence of title  in linking block. Tyres_feature_page")
    public Tyres_feature_page_Logic presenceOfTitleLinkingBlock() {
        for (int i = 0; i < 3; i++) {
            visibleTitleOfLinkingBlocks().get(i).shouldBe(visible);
        }
        return this;
    }

    @Step("swipe to right in Linking block. Tyres_feature_page")
    public Tyres_feature_page_Logic swipeToRightInLinkingBlock() {
        for (int i = 0; i < btnPaginatorOfLinkingBlock().size() - 1; i++) {
            String titleOfFirstLinkingBlock = visibleTitleOfLinkingBlocks().get(0).getText();
            btnPaginatorOfLinkingBlock().get(i + 1).click();
            presenceOfTitleLinkingBlock();
            presenceOfTitleLinkingBlock();
            visibleTitleOfLinkingBlocks().get(0).shouldNotHave(exactText(titleOfFirstLinkingBlock));
        }
        return this;
    }

    @Step("swipe to right in Linking block. Tyres_feature_page")
    public Tyres_feature_page_Logic swipeToLeftInLinkingBlock() {
        for (int i = btnPaginatorOfLinkingBlock().size() - 1; i > 0; i--) {
            String titleOfFirstLinkingBlock = visibleTitleOfLinkingBlocks().get(0).getText();
            btnPaginatorOfLinkingBlock().get(i - 1).click();
            presenceOfTitleLinkingBlock();
            presenceOfTitleLinkingBlock();
            visibleTitleOfLinkingBlocks().get(0).shouldNotHave(exactText(titleOfFirstLinkingBlock));
        }
        return this;
    }

    @Step("total count of Linking block. Tyres_feature_page")
    public Tyres_feature_page_Logic totalCountOfLinkingBlock(int size) {
        titlesOfLinkingBlocks().shouldHaveSize(size);
        return this;
    }

    @Step("appearance of animation in Linking block. Tyres_feature_pagee")
    public Tyres_feature_page_Logic appearanceOfAnimationInLinkingBlock() {
        headLineOfLinkingBlock().hover();
        for (int i = 0; i < visibleTitleOfLinkingBlocks().size(); i++) {
            visibleTitleOfLinkingBlocks().get(i).hover();
            btnMoreOfLinkingBlock().get(i).shouldBe(visible).shouldNotBe(empty);
        }
        return this;
    }

    @Step("check titles of linking block. Tyres_feature_page")
    public Tyres_feature_page_Logic checkTitlesOfLinkingBlock() {
        List<String> titlesOfAllLinkingBlock = titlesOfLinkingBlocks().stream().map(n -> getTextFromUnVisibleElement(n)).collect(Collectors.toList());
        for (int i = 0; i < titlesOfAllLinkingBlock.size(); i++) {
            Assert.assertFalse(titlesOfAllLinkingBlock.get(i).equals(" "));
        }
        return this;
    }

    @Step("presence of Headline at Linking block. Tyres_feature_page")
    public Tyres_feature_page_Logic presenceOfHeadlineAtLinkingBlock() {
        headLineOfLinkingBlock().shouldBe(visible);
        return this;
    }

    @Step("check transitions in Linking block. Tyres_feature_page")
    public Tyres_feature_page_Logic checkTransitionsInLinkingBlock(String url) {
        String mainTitle = mainHeadline().shouldBe(visible).getText();
        checkTransitionsOfVisibleLinkingBlock(url, mainTitle);
        /*ВРЕМЕННО ОТКЛЮЧЕНА ПРОВЕРКА*/
        //    checkTransitionsOfLastLinkingBlock(url, mainTitle);
        return this;
    }

    @Step("check transitions of visible Linking block. Tyres_feature_page")
    public Tyres_feature_page_Logic checkTransitionsOfVisibleLinkingBlock(String currentUrl, String title) {
        for (int i = 0; i < visibleTitleOfLinkingBlocks().size(); i++) {
            linkingBlock().scrollIntoView("{block: \"center\"}");
            presenceOfTitleLinkingBlock();
            presenceOfTitleLinkingBlock();
            String url = btnMoreOfLinkingBlock().get(i).getAttribute("url");
            visibleTitleOfLinkingBlocks().get(i).shouldBe(visible).click();
            checkingAbsenceOfCurrentLinkInLinkingBlock(url, title);
            back();
            waitWhileRouteContainsExpectedCondition(currentUrl);
        }
        return this;
    }

    @Step("check transitions of last Linking block. Tyres_feature_page")
    public Tyres_feature_page_Logic checkTransitionsOfLastLinkingBlock(String currentUrlSite, String title) {
        for (int i = 0; i < btnPaginatorOfLinkingBlock().size() - 1; i++) {
            linkingBlock().scrollIntoView("{block: \"center\"}");
            presenceOfTitleLinkingBlock();
            presenceOfTitleLinkingBlock();
            String titleOfLastLinkingBlock = visibleTitleOfLinkingBlocks().get(3).getText();
            btnPaginatorOfLinkingBlock().get(i + 1).click();
            presenceOfTitleLinkingBlock();
            presenceOfTitleLinkingBlock();
            visibleTitleOfLinkingBlocks().get(3).shouldNotHave(exactText(titleOfLastLinkingBlock));
            visibleTitleOfLinkingBlocks().get(3).hover();
            presenceOfTitleLinkingBlock();
            presenceOfTitleLinkingBlock();
            String currentUrl = visibleBtnMoreOfLinkingBlock().get(0).getAttribute("url");
            visibleTitleOfLinkingBlocks().get(3).click();
            checkingAbsenceOfCurrentLinkInLinkingBlock(currentUrl, title);
            back();
            waitWhileRouteContainsExpectedCondition(currentUrlSite);
        }
        return this;
    }


    @Step("check SEO block. Tyres_feature_page")
    public Tyres_feature_page_Logic checkSeoBlock() {
        presenceOfSeoHeadlines();
        presenceOfSeoTexts();
        return this;
    }

    @Step("check SEO block. Tyres_feature_page")
    public Tyres_feature_page_Logic presenceOfSeoHeadlines() {
        for (int i = 0; i < seoHeadlines().size(); i++) {
            seoHeadlines().get(i).shouldBe(visible).shouldNotBe(empty);
        }
        return this;
    }

    @Step("check SEO block. Tyres_feature_page")
    public Tyres_feature_page_Logic presenceOfSeoTexts() {
        for (int i = 0; i < seoTexts().size(); i++) {
            seoTexts().get(i).shouldBe(visible).shouldNotBe(empty);
        }
        return this;
    }

    @Step("presence of Tyres size selector. Tyres_feature_page")
    public Tyres_feature_page_Logic presenceOfTyresSizeSelector() {
        tyresSizeSelector().shouldBe(visible);
        return this;
    }

    @Step("default values of selector. Tyres_feature_page")
    public Tyres_feature_page_Logic defaultValuesOfSelector(String width, String height, String diameter) {
        widthDropdown().shouldBe(visible).shouldHave(value(width));
        heightDropdown().shouldBe(visible).shouldHave(value(height));
        diameterDropdown().shouldBe(visible).shouldHave(value(diameter));
        return this;
    }

    @Step("check of season selector. Tyres_feature_page")
    public Tyres_feature_page_Logic checkOfSeasonSelector() {
        DateFormat dateFormat = new SimpleDateFormat("M");
        Date date = new Date();
        int month = Integer.parseInt(dateFormat.format(date));
        switch (month) {
            case 1:
            case 2:
                winterSeason().shouldBe(exist).shouldHave(attribute("checked"));
                break;
            case 3:
            case 4:
            case 5:
            case 6:
            case 7:
            case 8:
                summerSeason().shouldBe(exist).shouldHave(attribute("checked"));
                break;
            case 9:
            case 10:
            case 11:
            case 12:
                winterSeason().shouldBe(exist).shouldHave(attribute("checked"));
                break;
        }
        return this;
    }

    @Step("Click all tyres sizes button. Tyres_feature_page")
    public Tyres_feature_page_Logic clickAllTyresSizesBtnInSizeBlock() {
        allSizesButtonInSizeBlock().click();
        return this;
    }

    @Step("Click brand in top block and check redirect. Tyres_feature_page")
    public String getBrandNameAndClickButtonInTopBlock() {
        String brandName = brandButtonInTopBlock().attr("alt").split(" ")[0];
        brandButtonInTopBlock().click();
        return brandName;
    }

    @Step("Check tyres diameter relink block presence. Tyres_feature_page")
    public Tyres_feature_page_Logic checkTyresDiameterRelinkBlockPresence() {
        diameterRelinkBlock().scrollIntoView("{block: \"center\"}").shouldBe(visible);
        linksInDiameterblock().shouldHave(sizeGreaterThan(1));
        return this;
    }

    @Step("Check tyres dimension relink block presence. Tyres_feature_page")
    public Tyres_feature_page_Logic checkTyresDimensionRelinkBlockPresence() {
        dimensionRelinkBlock().shouldBe(visible);
        linksInDimensionRelinkBlock().shouldHave(sizeGreaterThan(5));
        return this;
    }

    @Step("Click on expected season radio button {seasonBtn} in selector. Tyres_feature_page")
    public Tyres_feature_page_Logic clickExpectedSeasonBtn(SelenideElement seasonBtn) {
        seasonBtn.shouldBe(visible);
        seasonBtn.click();
        return this;
    }

    @Step("Select Width. Tyres_feature_page")
    public Tyres_feature_page_Logic selectWidth(String width) {
        widthDropdown().selectOption(width);
        Wait().until(webDriver -> widthDropdown().getSelectedText().equals(width));
        return this;
    }

    @Step("Select Height. Tyres_feature_page")
    public Tyres_feature_page_Logic selectHeight(String height) {
        heightDropdown().selectOption(height);
        Wait().until(webDriver -> heightDropdown().getSelectedText().equals(height));
        return this;
    }

    @Step("Select Diameter. Tyres_feature_page")
    public Tyres_feature_page_Logic selectDiameter(String diameter) {
        diameterDropdown().selectOption(diameter);
        Wait().until(webDriver -> diameterDropdown().getSelectedText().equals(diameter));
        return this;
    }

    @Step("Click Submit Tyres Selector. Tyres_feature_page")
    public TyresListing_page_Logic clickSubmitTyresSelector() {
        submitBtnInSelector().click();
        return page(TyresListing_page_Logic.class);
    }

    @Step("Click tyre in top block and check redirect. Tyres_feature_page")
    public Tyre_item_page_Logic clickTyreInTopBlocAndCheckRedirect(SelenideElement seasonTab, SelenideElement itemInTopBlock) {
        topBlock().scrollIntoView("{block: \"center\"}").shouldBe(visible);
        seasonTab.shouldBe(visible).click();
        String idProd = itemInTopBlock.attr("data-ga-action");
        itemInTopBlock.click();
        new Tyre_item_page_Logic().checkTyreIdOnProductPage(idProd);
        return page(Tyre_item_page_Logic.class);
    }

    @Step("Check popular brands block visibility. Tyres_feature_page")
    public Tyres_feature_page_Logic checkPopularBrandsBlockVisibility() {
        brandTopBlock().scrollIntoView("{block: \"center\"}").shouldBe(visible);
        brandTopBlockTitle().shouldHave(or("condition", exactText("Hochwertige Reifenmarken zur Auswahl"), exactText(" Hochwertige Reifenmarken für BMW zur Auswahl")));
        return this;
    }

    @Step("Check popular brand block first position. Tyres_feature_page")
    public Tyres_feature_page_Logic checkPopularBrandsSliderFirstPosition() {
        brandsInSlider().shouldHaveSize(14);
        List<String> brandsInFirstSliderPosition = new ArrayList<>();
        List<String> brandsInSecondSliderPosition = new ArrayList<>();
        for (int i = 0; i < brandsInSlider().size(); i++) {
            if (i < 6) {
                brandsInSlider().get(i).shouldBe(visible);
                brandsInFirstSliderPosition.add(brandsInSlider().get(i).attr("alt"));
            } else {
                brandsInSlider().get(i).shouldNotBe(visible);
                brandsInSecondSliderPosition.add(brandsInSlider().get(i).attr("alt"));
            }
        }
        Assert.assertNotEquals(brandsInFirstSliderPosition, brandsInSecondSliderPosition);
        return this;
    }

    @Step("Check popular brand block second position. Tyres_feature_page")
    public Tyres_feature_page_Logic checkPopularBrandsSliderSecondPosition() {
        brandsInSlider().shouldHaveSize(14);
        List<String> brandsInFirstSliderPosition = new ArrayList<>();
        List<String> brandsInSecondSliderPosition = new ArrayList<>();
        List<String> brandsInThirdThirdSliderPosition = new ArrayList<>();
        for (int i = 0; i < brandsInSlider().size(); i++) {
            if (i < 6) {
                brandsInSlider().get(i).shouldNotBe(visible);
                brandsInFirstSliderPosition.add(brandsInSlider().get(i).attr("alt"));
            } else if (i < 12){
                brandsInSlider().get(i).shouldBe(visible);
                brandsInSecondSliderPosition.add(brandsInSlider().get(i).attr("alt"));
            } else {
                if (secondButtonInTopBrandSlider().isDisplayed()) {
                    clickSecondPageInBrandSlider();
                }
                brandsInSlider().get(i).shouldBe(visible);
                brandsInThirdThirdSliderPosition.add(brandsInSlider().get(i).attr("alt"));
            }
        }
        Assert.assertNotEquals(brandsInFirstSliderPosition, brandsInSecondSliderPosition);
        Assert.assertNotEquals(brandsInFirstSliderPosition, brandsInThirdThirdSliderPosition);
        Assert.assertNotEquals(brandsInSecondSliderPosition, brandsInThirdThirdSliderPosition);
        return this;
    }

    @Step("Click second page in brand slider. Tyres_feature_page")
    public Tyres_feature_page_Logic clickSecondPageInBrandSlider() {
        secondButtonInTopBrandSlider().click();
        return this;
    }

    @Step("Check top block presence. Tyres_feature_page")
    public Tyres_feature_page_Logic checkTopBlock(SelenideElement seasonTab, ElementsCollection topItemCollection) {
        topBlock().scrollIntoView("{block: \"center\"}").shouldBe(visible);
            seasonTab.click();
            topItemCollection.shouldBe(sizeLessThanOrEqual(6));
        return this;
    }

    @Step("Checking the slider in the TOP block. Tyres_feature_page")
    public Tyres_feature_page_Logic checkingSliderInTopBlock(int numberTab, SelenideElement slickSlideForSeason ) {
        sleep(3000);
        secondButtonInTopItemSlider(numberTab).click();
        Wait().until(webDriver -> slickSlideForSeason.shouldHave(attribute("class", "slick-active")));
        return this;
    }
}
