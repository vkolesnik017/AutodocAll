package ATD;


import Common.DataBase;
import PKW.Tyres_season_page_Logic;
import com.codeborne.selenide.ElementsCollection;
import io.qameta.allure.Step;
import org.testng.Assert;

import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

import static ATD.CommonMethods.*;
import static Common.CommonMethods.checkingContainsUrl;
import static Common.CommonMethods.waitWhileRouteContainsExpectedCondition;
import static com.codeborne.selenide.CollectionCondition.*;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Condition.empty;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.WebDriverRunner.url;

public class Tyres_page_Logic extends Tyres_page {

    @Step("Select Season Tyre. Tyres_page")
    public Tyres_page_Logic selectSeasonTyre(String season) {
        seasonDropdown().selectOption(season);
        Wait().until(webDriver -> seasonDropdown().getSelectedText().equals(season));
        return this;
    }

    @Step("Select Width. Tyres_page")
    public Tyres_page_Logic selectWidth(String width) {
        widthDropdown().selectOption(width);
        Wait().until(webDriver -> widthDropdown().getSelectedText().equals(width));
        return this;
    }

    @Step("Select Height. Tyres_page")
    public Tyres_page_Logic selectHeight(String height) {
        heightDropdown().selectOption(height);
        Wait().until(webDriver -> heightDropdown().getSelectedText().equals(height));
        return this;
    }


    @Step("Select Type. Tyres_page")
    public Tyres_page_Logic selectType(String type) {
        typeDropdown().selectOption(type);
        Wait().until(webDriver -> typeDropdown().getSelectedText().equals(type));
        return this;
    }

    @Step("Select Diameter. Tyres_page")
    public Tyres_page_Logic selectDiameter(String diameter) {
        diameterDropdown().selectOption(diameter);
        Wait().until(webDriver -> diameterDropdown().getSelectedText().equals(diameter));
        return this;
    }

    @Step("Select Diameter. Tyres_page")
    public Tyres_page_Logic selectDiameterInSelector(String diameter) {
        diameterDropdown().selectOptionByValue(diameter);
        return this;
    }

    @Step("Click Submit Tyres Selector. Tyres_page")
    public TyresListing_page_Logic clickSubmitTyresSelector() {
        submitTyresSelectorButton().click();
        return page(TyresListing_page_Logic.class);
    }

    @Step("Click Submit Tyres Selector To Check Popup. Tyres_page")
    public Tyres_page_Logic clickSubmitTyresSelectorToCheckPopup() {
        submitTyresSelectorButton().click();
        return this;
    }

    @Step("Check Tyres Selector Visibility. Tyres_page")
    public Tyres_page_Logic checkTyresSelectorVisibility() {
        tyresSelectorBlock().shouldBe(visible);
        return this;
    }

    @Step("Check Tyres Selector Visibility PKW. Tyres_page")
    public Tyres_page_Logic checkTyresSelectorVisibilityPKW() {
        tyresSelectorBlockPKW().shouldBe(visible);
        return this;
    }

    @Step("Check Tyres Selector Visibility SUV. Tyres_page")
    public Tyres_page_Logic checkTyresSelectorVisibilitySUV() {
        tyresSelectorBlockSUV().shouldBe(visible);
        return this;
    }

    @Step("Check Tyres Selector Visibility LLKW. Tyres_page")
    public Tyres_page_Logic checkTyresSelectorVisibilityLLKW() {
        tyresSelectorBlockLLKW().shouldBe(visible);
        return this;
    }

    @Step("Check Tyres Selector Visibility MOTO. Tyres_page")
    public Tyres_page_Logic checkTyresSelectorVisibilityMOTO() {
        tyresSelectorBlockMOTO().shouldBe(visible);
        return this;
    }

    @Step("Select Tab SUV. Tyres_page")
    public Tyres_page_Logic selectTabSUV() {
        tabSUVtype().click();
        return this;
    }

    @Step("Select Tab LLKW. Tyres_page")
    public Tyres_page_Logic selectTabLLKW() {
        tabLLKWtype().click();
        return this;
    }

    @Step("Select Tab MOTO. Tyres_page")
    public Tyres_page_Logic selectTabMOTO() {
        tabMOTOtype().click();
        return this;
    }

    @Step("Validation Popup With Clear Height Width Diameter. Tyres_page")
    public Tyres_page_Logic validationPopupWithClearHeightWidthDiameter() {
        tyresValidationPopup().shouldHave(text("Das Feld Breite ist erforderlich und muss einen Wert enthalten. Das Feld H??he ist erforderlich und muss einen Wert enthalten. Das Feld Zoll ist erforderlich und muss einen Wert enthalten."));
        return this;
    }

    @Step("Validation Popup With Clear Height Diameter. Tyres_page")
    public Tyres_page_Logic validationPopupWithClearHeightDiameter() {
        tyresValidationPopup().shouldHave(text("Das Feld H??he ist erforderlich und muss einen Wert enthalten. Das Feld Zoll ist erforderlich und muss einen Wert enthalten."));
        return this;
    }

    @Step("Validation Popup With Clear Diameter. Tyres_page")
    public Tyres_page_Logic validationPopupWithClearDiameter() {
        tyresValidationPopup().shouldHave(text("Das Feld Zoll ist erforderlich und muss einen Wert enthalten."));
        return this;
    }

    @Step("Validation Popup With Clear Height Typ Diameter. Tyres_page")
    public Tyres_page_Logic validationPopupWithClearHeightTypDiameter() {
        tyresValidationPopup().shouldHave(text("Das Feld H??he ist erforderlich und muss einen Wert enthalten. Das Feld Typ ist erforderlich und muss einen Wert enthalten. Das Feld Zoll ist erforderlich und muss einen Wert enthalten."));
        return this;
    }

    @Step("Validation Popup With Clear Typ Diameter. Tyres_page")
    public Tyres_page_Logic validationPopupWithClearTypDiameter() {
        tyresValidationPopup().shouldHave(text("Das Feld Typ ist erforderlich und muss einen Wert enthalten. Das Feld Zoll ist erforderlich und muss einen Wert enthalten."));
        return this;
    }

    @Step("Close Tyres Validation Popup. Tyres_page")
    public Tyres_page_Logic closeTyresValidationPopup() {
        closeTyresValidationPopupButton().click();
        return this;
    }

    @Step("Click Brand Dropdown. Tyres_page")
    public Tyres_page_Logic clickBrandDropdown() {
        brandDropdown().click();
        return this;
    }

    @Step("Click Apollo Brand. Tyres_page")
    public Tyres_page_Logic clickApolloBrand() {
        brandApolloInDropdown().click();
        return this;
    }

    @Step("Click Speed Index Dropdown. Tyres_page")
    public Tyres_page_Logic clickSpeedIndexDropdown() {
        speedIndexDropdown().click();
        return this;
    }

    @Step("Click Speed Index H. Tyres_page")
    public Tyres_page_Logic clickSpeedIndexH() {
        speedIndexHinDropdown().click();
        return this;
    }

    @Step("Click all tyres brands button. Tyres_page")
    public Tyres_page_Logic clickAllTyresBrandsButton() {
        allBrandsButton().click();
        return this;
    }

    @Step("Click all tyres sizes button. Tyres_page")
    public Tyres_page_Logic clickAllTyresSizesButton() {
        allSizesButton().click();
        return this;
    }

    @Step("Click mobile App link. Tyres_page")
    public Tyres_page_Logic clickMobileAppLink() {
        mobileAppLink().click();
        return this;
    }

    @Step("Click video link. Tyres_page")
    public Tyres_page_Logic clickVideoLink() {
        videoLink().click();
        return this;
    }

    @Step("Check top block presence. Tyres_page")
    public Tyres_page_Logic checkTopBlock() {
        topBlock().shouldBe(visible);
        productsInTopBlock().shouldBe(sizeLessThanOrEqual(12));
        return this;
    }

    @Step("Click Michelin brand link in brand catalog. Tyres_page")
    public TyresListing_page_Logic clickMichelinLink() {
        michelinBrandInBrandCatalog().click();
        return page(TyresListing_page_Logic.class);
    }

    @Step("Click brand in top block and check redirect. Tyres_page")
    public String getBrandNameAndClickButtonInTopBlock() {
        String brandName = brandButtonInTopBlock().attr("alt").split(" ")[0];
        brandButtonInTopBlock().click();
        return brandName;
    }

    @Step("Click season button and check transition. Tyres_page")
    public TyresListing_page_Logic clickSeasonButtonAndCheckTransition() {
        winterButtonInSeasonBlock().hover().click();
        return page(TyresListing_page_Logic.class);
    }

    @Step("Click tyre in top block and check redirect. Tyres_page")
    public String clickTyreInTopBlockAndGetTyreId() {
        topBlock().shouldBe(visible);
        String tyreId = productInTopBlock().attr("data-ga-action");
        productInTopBlock().click();
        return tyreId;
    }

    @Step("Check all brands list presence. Tyres_page")
    public Tyres_page_Logic checkAllBrandsListPresence() {
        allBrandsList().shouldBe(visible);
        return this;
    }

    @Step("Check all brands list title presence. Tyres_page")
    public Tyres_page_Logic checkAllBrandsListTitlePresence() {
        allBrandsListTitle().shouldBe(visible);
        if (url().contains("/offroad-suv")) {
            allBrandsListTitle().shouldHave(text("Alle 4x4 Reifen Hersteller"));
        } else if (url().contains("/llkw")) {
            allBrandsListTitle().shouldHave(text("Alle Transporterreifen Hersteller"));
        } else if (url().contains("/motorrad")) {
            allBrandsListTitle().shouldHave(text("Alle Reifen f??r Motorrad Hersteller"));
        } else {
            allBrandsListTitle().shouldHave(text("Alle PKW Reifen Hersteller"));
        }
        return this;
    }

    @Step("Check tyres size selector presence. Tyres_page")
    public Tyres_page_Logic checkTyresSizeSelectorPresence() {
        tyresSelectorBlock().shouldBe(visible);
        return this;
    }

    @Step("Check tyres season block presence. Tyres_page")
    public Tyres_page_Logic checkTyresSeasonBlockPresence() {
        seasonBlock().shouldBe(visible);
        if (url().contains("/motorrad")) {
            seasonsInSeasonBlock().shouldHaveSize(2);
        } else {
            seasonsInSeasonBlock().shouldHaveSize(3);
        }
        return this;
    }

    @Step("Check tyres diameter relink block presence. Tyres_page")
    public Tyres_page_Logic checkTyresDiameterRelinkBlockPresence() {
        diameterRelinkBlock().scrollIntoView("{block: \"center\"}").shouldBe(visible);
        linksInDiameterblock().shouldHave(sizeGreaterThan(1));
        return this;
    }

    @Step("Check tyres dimension relink block presence. Tyres_page")
    public Tyres_page_Logic checkTyresDimensionRelinkBlockPresence() {
        dimensionRelinkBlock().shouldBe(visible);
        linksInDimensionRelinkBlock().shouldHave(sizeGreaterThan(5));
        return this;
    }

    @Step("Check popular brands block visibility. Tyres_page")
    public Tyres_page_Logic checkPopularBrandsBlockVisibility() {
        brandTopBlock().shouldBe(visible);
        brandTopBlockTitle().shouldHave(exactText("Hochwertige Reifenmarken zur Auswahl"));
        return this;
    }

    @Step("Check popular brand block first position. Tyres_page")
    public Tyres_page_Logic checkPopularBrandsSliderFirstPosition(int numberOfBrands, int numberOfBrandsInFirstSlide, ElementsCollection brandsInSlider, String attrName) {
        brandsInSlider.shouldHaveSize(numberOfBrands);
        List<String> brandsInFirstSliderPosition = new ArrayList<>();
        List<String> brandsInSecondSliderPosition = new ArrayList<>();
        for (int i = 0; i < brandsInSlider.size(); i++) {
            if (i < numberOfBrandsInFirstSlide) {
                brandsInSlider.get(i).shouldBe(visible);
                brandsInFirstSliderPosition.add(brandsInSlider.get(i).attr(attrName));
            } else {
                brandsInSlider.get(i).shouldNotBe(visible);
                brandsInSecondSliderPosition.add(brandsInSlider.get(i).attr(attrName));
            }
        }
        Assert.assertNotEquals(brandsInFirstSliderPosition, brandsInSecondSliderPosition);
        return this;
    }

    @Step("Check popular brand block second position. Tyres_page")
    public Tyres_page_Logic checkPopularBrandsSliderSecondPosition(int numberOfAllBrands, int numberOfBrandsInFirstSlide, ElementsCollection brandsInSlider, String attrName) {
        brandsInSlider.shouldHaveSize(numberOfAllBrands);
        List<String> brandsInFirstSliderPosition = new ArrayList<>();
        List<String> brandsInSecondSliderPosition = new ArrayList<>();
        for (int i = 0; i < brandsInSlider.size(); i++) {
            if (i < numberOfBrandsInFirstSlide) {
                brandsInSlider.get(i).shouldNotBe(visible);
                brandsInFirstSliderPosition.add(brandsInSlider.get(i).attr(attrName));
            } else {
                brandsInSlider.get(i).shouldBe(visible);
                brandsInSecondSliderPosition.add(brandsInSlider.get(i).attr(attrName));
            }
        }
        Assert.assertNotEquals(brandsInFirstSliderPosition, brandsInSecondSliderPosition);
        return this;
    }

    @Step("Click second page in brand slider. Tyres_page")
    public Tyres_page_Logic clickSecondPageInBrandSlider() {
        secondButtonInTopBrandSlider().click();
        return this;
    }

    @Step("??hecks the number of identical brands and size tyres in top block. Tyres_page")
    public Tyres_page_Logic checkingNumberIdenticalBrandAndSizeInTopBlock(int expectedSize) {
        ArrayList<String> brandAndSizeTyres = new ArrayList<>();
        for (int i = 0; i < productsInTopBlock().size(); i++) {
            String nameBrand = brandProductFromTopTyresBlock().get(i).getAttribute("alt");
            String size = sizeProductFromTopTyresBlock().get(i).getText();
            brandAndSizeTyres.add(nameBrand);
            brandAndSizeTyres.add(size);
        }
        Collections.sort(brandAndSizeTyres);

        for (int i = 0; i < brandAndSizeTyres.size(); i++) {
            String name = brandAndSizeTyres.get(i);
            int quantity = Collections.frequency(brandAndSizeTyres, name);

            if (quantity > expectedSize) {
                Assert.fail("Finds more than three brands or size! " + name + " =" + quantity);
            }
            System.out.println(name + " (quantity =" + quantity + ")");
        }
        return this;
    }

    @Step("Click on the tyres in top block . Tyres_page")
    public Tyres_page_Logic clickTyresInTopBlock() {
        Tyre_item_page_Logic tyre_item_page_logic = new Tyre_item_page_Logic();
        topBlock().shouldBe(visible);
        for (int i = 0; i < imagesProductInTopBlock().size(); i++) {
            imagesProductInTopBlock().get(i).scrollIntoView("{block: \"center\"}").waitUntil(visible, 4000).click();
            tyre_item_page_logic.addButtonToBasket().waitUntil(visible, 4000).shouldBe(visible);
            back();
        }
        return this;
    }

    @Step("default values of selector. Tyres_page")
    public Tyres_page_Logic defaultValuesOfSelector(String width, String height, String diameter) {
        widthDropdown().shouldBe(visible).shouldHave(value(width));
        heightDropdown().shouldBe(visible).shouldHave(value(height));
        diameterDropdown().shouldBe(visible).shouldHave(value(diameter));
        return this;
    }

    @Step("check of season selector. Tyres_page")
    public Tyres_page_Logic checkOfSeasonSelector() {
        DateFormat dateFormat = new SimpleDateFormat("M");
        Date date = new Date();
        int month = Integer.parseInt(dateFormat.format(date));
        switch (month) {
            case 1:
            case 2:
                seasonDropdown().shouldHave(value("winter"));
                break;
            case 3:
            case 4:
            case 5:
            case 6:
            case 7:
            case 8:
                seasonDropdown().shouldHave(value("sommer"));
                break;
            case 9:
            case 10:
            case 11:
            case 12:
                seasonDropdown().shouldHave(value("winter"));
                break;
        }
        return this;
    }

    @Step("check of season motorcycle selector. Tyres_page")
    public Tyres_page_Logic checkOfSeasonMotoSelector(String value) {
        seasonDropdown().shouldBe(visible).shouldHave(value(value));
        return this;
    }

    @Step("select season. Tyres_page")
    public Tyres_page_Logic selectSeasonInSelector(String value) {
        seasonDropdown().shouldBe(visible).selectOptionByValue(value);
        return this;
    }

    @Step("get all width values from selector. Tyres_page")
    public List<String> getAllWidthValuesFromSelector() {
        List<String> values = allWidthValues().stream().map(n -> getTextFromUnVisibleElement(n)).collect(Collectors.toList());
        return values;
    }

    @Step("mismatch comparison of seasons. Tyres_page")
    public Tyres_page_Logic mismatchComparisonOfSeasons(List<String> firstList, List<String> secondList) {
        Assert.assertFalse(firstList.equals(secondList));
        return this;
    }

    @Step("check default value of width field. Tyres_page")
    public Tyres_page_Logic checkVisibleTopTyres() {
        for (int i = 0; i < visibleTopTyres().size(); i++) {
            visibleTopTyres().get(i).shouldBe(visible);
        }
        return this;
    }

    @Step("check of all width values. Tyres_page")
    public Tyres_page_Logic checkOfAllWidthValues(List<String> firstList, List<String> secondList, List<String> thirdList) {
        Set<String> allValues = new HashSet<>();
        allValues.addAll(firstList);
        allValues.addAll(secondList);
        allValues.addAll(thirdList);
        List<String> allWidthValues = allWidthValues().stream().map(n -> getTextFromUnVisibleElement(n)).collect(Collectors.toList());
        Assert.assertEquals(allWidthValues.size(), allValues.size());
        for (int i = 0; i < allValues.size(); i++) {
            for (int j = 0; j < allWidthValues.size(); j++) {
                Assert.assertTrue(allValues.contains(allWidthValues.get(j)));
            }
        }
        return this;
    }

    @Step("get all values from selector. Tyres_page")
    public List<String> getAllValuesFromSelector() {
        List<String> values = new ArrayList<>();
        addValuesToList(allWidthValues(), values);
        addValuesToList(allHeightValues(), values);
        addValuesToList(allTypeValues(), values);
        addValuesToList(allDiameterValues(), values);
        return values;
    }

    @Step("add values to list. Tyres_page")
    public Tyres_page_Logic addValuesToList(ElementsCollection list, List<String> arrayOfList) {
        for (int i = 0; i < list.size(); i++) {
            arrayOfList.add(getTextFromUnVisibleElement(list.get(i)));
        }
        return this;
    }

    @Step("comparison of all seasons values. Tyres_page")
    public Tyres_page_Logic comparisonOfAllSeasonsValues(List<String> firstList, List<String> secondList) {
        Assert.assertEquals(firstList, secondList);
        return this;
    }

    @Step("presence of linking block. Tyres_page")
    public Tyres_page_Logic presenceOfLinkingBlock() {
        linkingBlock().shouldBe(visible);
        return this;
    }

    @Step("check swipe by click on paginator. Tyres_page")
    public Tyres_page_Logic checkSwipeByClick() {
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

    @Step("check swipe by moving block. Tyres_page")
    public Tyres_page_Logic checkSwipeByMoving() {
        linkingBlock().scrollIntoView("{block: \"center\"}");
        swipeByMovingToRight();
        swipeByMovingToLeft();
        return this;
    }

    @Step("swipe by moving to right in Linking Block. Tyres_page")
    public Tyres_page_Logic swipeByMovingToRight() {
        linkingBlock().scrollIntoView("{block: \"center\"}");
        presenceOfTitleLinkingBlock();
        String titleOfFirstLinkingBlock = visibleTitleOfLinkingBlocks().get(0).getText();
        actions().clickAndHold(visibleTitleOfLinkingBlocks().get(1)).moveToElement(visibleTitleOfLinkingBlocks().get(0)).release(visibleTitleOfLinkingBlocks().get(1)).build().perform();
        presenceOfTitleLinkingBlock();
        visibleTitleOfLinkingBlocks().get(0).shouldNotHave(exactText(titleOfFirstLinkingBlock));
        return this;
    }

    @Step("swipe by moving to left in Linking Block. Tyres_page")
    public Tyres_page_Logic swipeByMovingToLeft() {
        linkingBlock().scrollIntoView("{block: \"center\"}");
        presenceOfTitleLinkingBlock();
        String titleOfFirstLinkingBlock = visibleTitleOfLinkingBlocks().get(0).getText();
        actions().clickAndHold(visibleTitleOfLinkingBlocks().get(0)).moveToElement(visibleTitleOfLinkingBlocks().get(1)).release(visibleTitleOfLinkingBlocks().get(0)).build().perform();
        presenceOfTitleLinkingBlock();
        visibleTitleOfLinkingBlocks().get(0).shouldNotHave(exactText(titleOfFirstLinkingBlock));
        return this;
    }

    @Step("presence of title  in linking block. Tyres_page")
    public Tyres_page_Logic presenceOfTitleLinkingBlock() {
        for (int i = 0; i < 3; i++) {
            visibleTitleOfLinkingBlocks().get(i).shouldBe(visible);
        }
        return this;
    }

    @Step("swipe to right in Linking block. Tyres_page")
    public Tyres_page_Logic swipeToRightInLinkingBlock() {
        for (int i = 0; i < btnPaginatorOfLinkingBlock().size() - 1; i++) {
            String titleOfFirstLinkingBlock = visibleTitleOfLinkingBlocks().get(0).shouldBe(visible).getText();
            btnPaginatorOfLinkingBlock().get(i + 1).click();
            presenceOfTitleLinkingBlock();
            presenceOfTitleLinkingBlock();
            visibleTitleOfLinkingBlocks().get(0).shouldNotHave(exactText(titleOfFirstLinkingBlock));
        }
        return this;
    }

    @Step("swipe to right in Linking block. Tyres_page")
    public Tyres_page_Logic swipeToLeftInLinkingBlock() {
        for (int i = btnPaginatorOfLinkingBlock().size() - 1; i > 0; i--) {
            String titleOfFirstLinkingBlock = visibleTitleOfLinkingBlocks().get(0).getText();
            btnPaginatorOfLinkingBlock().get(i - 1).click();
            presenceOfTitleLinkingBlock();
            presenceOfTitleLinkingBlock();
            visibleTitleOfLinkingBlocks().get(0).shouldNotHave(exactText(titleOfFirstLinkingBlock));
        }
        return this;
    }

    @Step("total count of Linking block. Tyres_page")
    public Tyres_page_Logic totalCountOfLinkingBlock(int size) {
        titlesOfLinkingBlocks().shouldHaveSize(size);
        return this;
    }

    @Step("appearance of animation in Linking block. Tyres_page")
    public Tyres_page_Logic appearanceOfAnimationInLinkingBlock() {
        headLineOfLinkingBlock().hover();
        for (int i = 0; i < visibleTitleOfLinkingBlocks().size(); i++) {
            visibleTitleOfLinkingBlocks().get(i).hover();
            btnMoreOfLinkingBlock().get(i).shouldBe(visible).shouldNotBe(empty);
            presenceOfTitleLinkingBlock();
        }
        return this;
    }

    @Step("check titles of linking block. Tyres_page")
    public Tyres_page_Logic checkTitlesOfLinkingBlock() {
        List<String> titlesOfAllLinkingBlock = titlesOfLinkingBlocks().stream().map(n -> getTextFromUnVisibleElement(n)).collect(Collectors.toList());
        for (int i = 0; i < titlesOfAllLinkingBlock.size(); i++) {
            Assert.assertFalse(titlesOfAllLinkingBlock.get(i).equals(" "));
        }
        return this;
    }

    @Step("presence of Headline at Linking block. Tyres_page")
    public Tyres_page_Logic presenceOfHeadlineAtLinkingBlock() {
        headLineOfLinkingBlock().shouldBe(visible);
        return this;
    }

    @Step("check transitions in Linking block. Tyres_page")
    public Tyres_page_Logic checkTransitionsInLinkingBlock(String url) {
        String mainTitle = mainHeadline().shouldBe(visible).getText();
        checkTransitionsOfVisibleLinkingBlock(url, mainTitle);
        /*???????????????? ?????????????????? ????????????????*/
        //    checkTransitionsOfLastLinkingBlock(url, mainTitle);
        return this;
    }

    @Step("check transitions of visible Linking block. Tyres_page")
    public Tyres_page_Logic checkTransitionsOfVisibleLinkingBlock(String currentUrl, String title) {
        for (int i = 0; i < visibleTitleOfLinkingBlocks().size(); i++) {
            linkingBlock().scrollIntoView("{block: \"center\"}");
            presenceOfTitleLinkingBlock();
            presenceOfTitleLinkingBlock();
            String url = btnMoreOfLinkingBlock().get(i).getAttribute("url");
            visibleTitleOfLinkingBlocks().get(i).shouldBe(visible).click();
            new Tyres_feature_page_Logic().checkingAbsenceOfCurrentLinkInLinkingBlock(url, title);
            back();
            waitWhileRouteContainsExpectedCondition(currentUrl);
        }
        return this;
    }

    @Step("check transitions of last Linking block. Tyres_page")
    public Tyres_page_Logic checkTransitionsOfLastLinkingBlock(String currentUrlSite, String title) {
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
            String currentUrl = visibleBtnMoreOfLinkingBlock().get(0).getAttribute("url");
            visibleTitleOfLinkingBlocks().get(3).click();
            new Tyres_feature_page_Logic().checkingAbsenceOfCurrentLinkInLinkingBlock(currentUrl, title);
            back();
            waitWhileRouteContainsExpectedCondition(currentUrlSite);
        }
        return this;
    }

    @Step("check Transition To Tyres Season Listing. Tyres_page")
    public Tyres_page_Logic checkTransitionToTyresSeasonListing() throws SQLException {
        DataBase db = new DataBase("ATD");
        clickSeasonButtonAndCheckTransition();
        checkingContainsUrl(db.getRouteByRouteName("DE", "tyres_season6"));
        back();
        clickOnSummerSeason();
        checkingContainsUrl(db.getRouteByRouteName("DE", "tyres_season2"));
        back();
        clickOnAllSeason();
        checkingContainsUrl(db.getRouteByRouteName("DE", "tyres_season7"));
        return this;
    }

    @Step("Click on summer season. Tyres_page")
    public Tyres_season_page_Logic clickOnSummerSeason() {
        summerButtonInSeasonBlock().shouldBe(visible).click();
        return page(Tyres_season_page_Logic.class);
    }

    @Step("Click on all season. Tyres_page")
    public Tyres_season_page_Logic clickOnAllSeason() {
        allSeason().shouldBe(visible).click();
        return page(Tyres_season_page_Logic.class);
    }

    @Step("Click on car brand from top car brand block. Tyres_page")
    public Tyres_maker_page_Logic clickOnCarBrand() {
        carBrandFromTopCarBrandBlock().shouldBe(visible).click();
        return page(Tyres_maker_page_Logic.class);
    }

    @Step("Get only visible brand from top car brand block. Tyres_page")
    public ArrayList<String> getSizeTopCarBrands() {
        ArrayList<String> carBrands = new ArrayList<>();
        for (int i = 0; i < carBrandsFromTopCarBrandBlock().size(); i++) {
            if (carBrandsFromTopCarBrandBlock().get(i).isDisplayed()) {
                String brands = carBrandsFromTopCarBrandBlock().get(i).getAttribute("href");
                carBrands.add(brands);
            }
        }
        return carBrands;
    }

    @Step("Check work top car brand block. Tyres_page")
    public Tyres_page_Logic checkWorkTopCarBrandBlock() {
        topCarBrandBlock().scrollIntoView("{block: \"center\"}").shouldBe(visible);
        titleTopCarBrandBlock().shouldHave(exactText("Autoreifen f??r Top Automarken"));
        int sizeBrands = getSizeTopCarBrands().size();
        Assert.assertEquals(sizeBrands,8);
        btnMoreInTopCarBrandBlock().shouldBe(visible).click();
        int moreSizeBrand = getSizeTopCarBrands().size();
        Assert.assertEquals(moreSizeBrand,10);
        btnLessInTopCarBrandBlock().shouldBe(visible).click();
        btnMoreInTopCarBrandBlock().shouldBe(visible);
        int lessSizeBrand = getSizeTopCarBrands().size();
        Assert.assertEquals(lessSizeBrand,8);
        return this;
    }


}
