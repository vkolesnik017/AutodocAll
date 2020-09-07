package ATD;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import io.qameta.allure.Step;
import org.testng.Assert;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static ATD.CommonMethods.*;
import static PKW.CommonMethods.getTextFromUnVisibleElement;
import static com.codeborne.selenide.CollectionCondition.sizeGreaterThan;
import static com.codeborne.selenide.CollectionCondition.texts;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.WebDriverRunner.url;

public class Search_page_Logic extends Search_page {

    @Step(":on Search_page")
    public Cart_page_Logic cartClick() {
        new Main_page_Logic().cartClick();
        return page(Cart_page_Logic.class);
    }

    @Step(":on Search_page")
    public Search_page checksProductTitlesContainExpectedTextGoingAllPagination(String expectedText) {
        new Listing_page_Logic().checksProductTitlesContainExpectedTextGoingAllPagination(expectedText);
        return this;
    }

    @Step(":on Search_page")
    public Search_page checksProductTitlesContainExpectedTextGoingTwoPages(String expectedText) {
        new Listing_page_Logic().checksProductTitlesContainExpectedTextGoingTwoPagesPagination(expectedText);
        return this;
    }

    @Step("Close footer message cookies. Search_page")
    public Search_page_Logic closeFooterMessageCookies() {
        closeCookiesFooterMessage();
        return this;
    }

    @Step("Verify text in search bar. Search_page")
    public Search_page_Logic verifyTextInSearchBar(String expectedText) {
        new Main_page().searchBar().shouldHave(exactValue(expectedText));
        return this;
    }

    @Step("Verify name route equals search. Search_page")
    public Search_page_Logic verifyNameRouteEqualsSearch() {
        waitWhileRouteBecomeExpected("search");
        return this;
    }

    @Step("Close popup of choose car. Search_page")
    private Search_page_Logic closePopupOfChooseCar() {
        closeBtnPopupOfChooseCar().click();
        return this;
    }

    @Step("Switch on second page. Search_page")
    public Search_page_Logic switchOnSecondPage() {
        secondListingPage().scrollTo();
        closePopupOfChooseCar().secondListingPage().click();
        return this;
    }


    @Step("Click on the button (Tell me when the product appears). Search_page")
    public Search_page_Logic clickButtonProductById(String idProduct) {
        buttonProductById(idProduct).click();
        return this;
    }

    @Step("Subscriptions for product that are not in stock. Search_page")
    public Search_page_Logic sendRequestByGrayButtonFromSearchPage(String email) {
        emailFieldInPopUpOfGrayBtn().setValue(email);
        checkboxInPopUpOfGrayBtn().click();
        sendButtonInPopUpOfGrayBtn().click();
        closeSuccessPopUpOfGrayBtn().click();
        return this;
    }

    @Step("Checking behavior of soft form 404. Search_page")
    public Catalog_page_Logic checkingBehaviorSoft404(String mail) {
        mailFieldSoftForm().setValue(mail);
        submitBtnSoftForm().click();
        errPopupSoftForm().shouldHave(Condition.text("Um fortzufahren bestätigen Sie bitte Ihr Newsletter-Abo"));
        closeErrPopupSoftForm().click();
        subscribeCheckboxSoftForm().click();
        submitBtnSoftForm().click();
        successPopupSoftForm().shouldBe(Condition.appear);
        closeSuccessPopupSoftForm().shouldHave(Condition.text("Einkauf fortsetzen")).click();
        waitingWhileLinkBecomeExpected("https://www.autodoc.de/autoteile");
        return page(Catalog_page_Logic.class);
    }

    @Step(":soft 404 form. Search_page")
    public Search_page_Logic checkingDatenschutzerklarungLinkBehaviorSoftForm() {
        new CommonMethods().checkingDatenschutzerklarungLinkBehavior(datenschutzerklarungLinkSoftForm(), "underline solid rgb(0, 0, 0)");
        return this;
    }

    @Step("Adds the first product and goes to the basket")
    public Cart_page_Logic addFirstProductAndGoToCart() {
        buyButton().click();
        cartPopupWithProduct().shouldBe(visible);
        cartClick();
        return page(Cart_page_Logic.class);
    }

    @Step(": on Search_page")
    public Search_page_Logic counterIncreaseForPaired(String startValue) {
        new CommonMethods().checkingCounterIncreaseForPaired(startValue, counterValue(), counterPlus());
        return this;
    }

    @Step(": on Search_page")
    public Search_page_Logic counterDecreaseForPaired(String startValue) {
        new CommonMethods().checkingCounterDecreaseForPaired(startValue, counterValue(), counterMinus());
        return this;
    }

    @Step("Clicking details. Search_page")
    public Product_page_Logic detailsClick() {
        detalisBtn().click();
        return page(Product_page_Logic.class);
    }

    @Step("Gets all the characteristics of the desired product from search listing {productArticle}. Search_page")
    // example String for productArticle = V99-75-0011
    public ElementsCollection getCharacteristicsDesiredProductForSearch(String productArticle) {
        return $$x("//*[@class='rc' and contains(text(),'" + productArticle + "')]/ancestor::div[@class='box criteria_toogle_active']//li")
                .shouldHave(sizeGreaterThan(10));
    }

    @Step(": on Search_page")
    public Search_page_Logic closePopupOtherCategoryIfYes() {
        new Product_page_Logic().closePopupOtherCategoryIfYes();
        return this;
    }

    @Step("Check additional listing. Search_page")
    public Search_page_Logic checkAdditionalListing() {
        dividingLineForProductsOtherCategories().shouldHave(text("Ergebnisse für zuendkerzen für andere Fahrzeuge"));
        return this;
    }

    @Step("Check elements on search page. Search_page")
    public Search_page_Logic checkElementsOnSearchPage() {
        titleOnSearchPage().shouldHave(text("Treffer gefunden für Bremscheiben"));
        blockOfHelpSearchProducts().shouldBe(visible);
        blockOfLinkingCategory().shouldBe(visible);
        return this;
    }

    @Step("Check generic synonyms. Search_page")
    public Search_page_Logic checkGenericSynonyms(String genericName, ArrayList<String> genericSynonyms) {
        for (int i = 0; i < genericSynonyms.size(); i++) {
            new Main_page_Logic().inputTextInSearchBar(genericSynonyms.get(i))
                    .clickTooltipInSearchByExactText(genericSynonyms.get(i))
                    .checksProductTitlesContainExpectedTextGoingTwoPages(genericName);
        }
        return this;
    }

    @Step("check of current url .Search_page")
    public Search_page_Logic checkOfCurrentUrl(String subRoute) throws SQLException {
        DataBase db = new DataBase();
        Assert.assertEquals(url(), db.getFullRouteByRouteAndSubroute("prod", "DE", "main", subRoute));
        return this;
    }

    @Step("select brand in brands block. Search_page")
    public Search_page_Logic selectBrandInBlock(String idOfBrand) {
        brandsFilterBlock().shouldBe(visible);
        while (!brandsLinkInSideBar(idOfBrand).isDisplayed()) {
            forwardLinkAtBrandsFilter().click();
        }
        brandsLinkInSideBar(idOfBrand).shouldBe(visible).click();
        appearsOfLoader();
        return this;
    }

    @Step("appears of Loader .Search_page")
    public Search_page_Logic appearsOfLoader() {
        loaderInTecDocListing().should(appear);
        loaderInTecDocListing().should(disappear);
        return this;
    }

    @Step("check listing with selected brands .Search_page")
    public Search_page_Logic checkListingWithSelectedBrands(String brands) {
        String brandFromTitleOfProduct;
        List<String> brandsList = new ArrayList<>();
        String[] brand = brands.split("\\,");
        Collections.addAll(brandsList, brand);
        for (int i = 0; i < imageOfBrandInProductBlock().size(); i++) {
            String endOfAttribute = imageOfBrandInProductBlock().get(i).getAttribute("src").replace(imageOfBrandInProductBlock().get(i).getAttribute("src").substring(imageOfBrandInProductBlock().get(i).getAttribute("src").lastIndexOf(".")), "");
            String trimOfEndAttribute = endOfAttribute.replace(endOfAttribute.substring(endOfAttribute.lastIndexOf("/")), "");
            brandFromTitleOfProduct = endOfAttribute.replace(trimOfEndAttribute, "").replaceAll("[^0-9]", "");
            Assert.assertTrue(brandsList.contains(brandFromTitleOfProduct));
        }
        return this;
    }

    @Step("check absence of Quantity characteristic in Product description block .Search_page")
    public Search_page_Logic checkAbsenceOfQuantityCharacteristicInProductDescriptionBlock() {
        for (int i = 0; i < descriptionBlockOfProduct().size(); i++) {
            for (int j = 0; j < characteristicListOfProduct(i + 1).size(); j++) {
                characteristicListOfProduct(i + 1).get(j).shouldNotHave(exactText("Menge"));
            }
        }
        return this;
    }

    @Step("check listing with selected generic .Search_page")
    public Search_page_Logic checkListingWithSelectedGeneric(String generic) {
        mainListingBlock().shouldBe(visible);
        checkTitleOfProductsWithGeneric(generic);
        while (forwardLinkOfPaginator().isDisplayed()) {
            forwardLinkOfPaginator().click();
            checkTitleOfProductsWithGeneric(generic);
        }
        return this;
    }

    @Step("check listing with selected generic .Search_page")
    public Search_page_Logic checkTitleOfProductsWithGeneric(String expectedGeneric) {
        for (int i = 0; i < titleOfProductsInListing().size(); i++) {
            titleOfProductsInListing().get(i).shouldHave(text(expectedGeneric));
        }
        return this;
    }

    @Step("get id of added products to basket .Search_page")
    public List<String> getIdOfAddedProductsToBasket(int countOfAddedProducts) {
        List<String> idOfAddedProducts = new ArrayList<>();
        for (int i = 0; i < countOfAddedProducts; i++) {
            idOfAddedProducts.add(activeBtnAddProductToBasket().get(i).getAttribute("id"));
        }
        return idOfAddedProducts;
    }

    @Step("added products to basket .Search_page")
    public Search_page_Logic addedProductsToBasket(int countOfAddedProducts) {
        for (int i = 0; i < countOfAddedProducts; i++) {
            activeBtnAddProductToBasket().get(i).click();
            basketDropMenu().shouldBe(visible);
            basketDropMenu().should(disappear);
            if (closeAnotherPartsOfCarPopUp().isDisplayed()) {
                closeAnotherPartsOfCarPopUp().click();
                closeAnotherPartsOfCarPopUp().shouldNotBe(visible);
            }
        }
        return this;
    }

    @Step("presence of TecDoc listing. Search_page")
    public Search_page_Logic presenceOfTecDocListing() {
        mainListingBlock().shouldBe(visible);
        return this;
    }

    @Step("checking applicability of product for selected generic. Search_page")
    public Search_page_Logic checkingApplicabilityOfProductForSelectedGeneric(String generic) {
        selectProductInTecDocListing(generic);
        while (forwardLinkOfPaginator().isDisplayed()) {
            forwardLinkOfPaginator().click();
            selectProductInTecDocListing(generic);
        }
        return this;
    }

    @Step("click on product in TecDoc Listing .Search_page")
    public Search_page_Logic selectProductInTecDocListing(String generic) {
        for (int i = 0; i < titleOfProductsInListing().size(); i++) {
            titleOfProductsInListing().get(i).shouldHave(text(generic));
            clickOnProductInTecDocListing(i).checkCompatibilityProductAndGeneric();
            back();
        }
        return this;
    }

    @Step("click on Product in TecDoc listing .Search_page")
    public Product_page_Logic clickOnProductInTecDocListing(int point) {
        titleOfProductsInListing().get(point).scrollIntoView("{block: \"center\"}").click();
        return page(Product_page_Logic.class);
    }

    @Step("Login in header with mail {mail} and transition to profile plus page and go back. Search_page")
    public Search_page_Logic loginToProfilePlusPageAndBack(String email) {
        new Main_page_Logic().loginAndTransitionToProfilePlusPage(email).visibilityOfUsersName();
        back();
        return this;
    }

    @Step("update of page. Search_page")
    public Search_page_Logic updateOfPage() {
        refresh();
        return this;
    }

    @Step("update of page. Search_page")
    public Search_page_Logic checkCountOfVehicleInIconOfGarage(String expectedCountOfVehicle) {
        countOfVehicleInIconOfGarageInHeader().shouldBe(visible).shouldHave(exactText(expectedCountOfVehicle));
        return this;
    }

    @Step("click on Garage icon in header. Search_page")
    public Search_page_Logic clickOnGarageIconInHeader() {
        headerGarageIcon().shouldBe(visible).click();
        popUpOfGarageInHeader().shouldBe(visible);
        return this;
    }

    @Step("click on Garage icon in header. Search_page")
    public Search_page_Logic selectVehicleInGaragePopUp(String idOfVehicle) {
        idOfVehicleInGaragePopUp(idOfVehicle).shouldBe(visible).click();
        return this;
    }

    @Step("added Product to WishList. Search_page")
    public Search_page_Logic addedProductToWishList(int positionOfProduct) {
        btnAddedProductToWishList().get(positionOfProduct).click();
        addedProductToWishList().get(positionOfProduct).shouldBe(exist);
        return this;
    }

    @Step("go to WishList page. Search_page")
    public Profile_wishlist_page_Logic goToWishListPage() {
        iconOfWishList().shouldBe(visible).click();
        return page(Profile_wishlist_page_Logic.class);
    }

    @Step("presence Refurbished Characteristic in listing. Search_page")
    public Search_page_Logic presenceRefurbishedCharacteristic(String expectedCharacteristic) {
        productListBlock().shouldBe(visible);
        List<String> listOfCharacteristic = new ArrayList<>();
        String currentTitleOfProduct;
        addedAllCharacteristicsOfProductToList(listOfCharacteristic);
        while (forwardLinkOfPaginator().isDisplayed()){
            currentTitleOfProduct = visibleTitleOfProducts().get(0).getText();
            forwardLinkOfPaginator().scrollIntoView("{block: \"end\"}").click();
            visibleTitleOfProducts().get(0).shouldNotHave(exactText(currentTitleOfProduct));
            addedAllCharacteristicsOfProductToList(listOfCharacteristic);
        }
       /* for (int i = 0; i < allCharacteristicsOfProducts().size(); i++) {
            listOfCharacteristic.add(getTextFromUnVisibleElement(allCharacteristicsOfProducts().get(i)));
        }*/
        Assert.assertTrue(listOfCharacteristic.contains(expectedCharacteristic));
        return this;
    }

    @Step("added all characteristics of product to list. Search_page")
    public Search_page_Logic addedAllCharacteristicsOfProductToList(List<String> list) {
        for (int i = 0; i < allCharacteristicsOfProducts().size(); i++) {
            list.add(getTextFromUnVisibleElement(allCharacteristicsOfProducts().get(i)));
        }
        return this;
    }

}

