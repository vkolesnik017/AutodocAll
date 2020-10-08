package ATD;

import Common.DataBase;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import io.qameta.allure.Step;
import org.testng.Assert;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import static ATD.CommonMethods.*;
import static PKW.CommonMethods.getTextFromUnVisibleElement;
import static com.codeborne.selenide.CollectionCondition.sizeGreaterThan;
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
        DataBase db = new DataBase("ATD");
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
            forwardLinkOfPaginator().scrollIntoView("{block: \"end\"}").click();
            selectProductInTecDocListing(generic);
        }
        return this;
    }

    @Step("click on product in TecDoc Listing .Search_page")
    public Search_page_Logic selectProductInTecDocListing(String generic) {
        for (int i = 0; i < titleOfMainProducts().size(); i++) {
            titleOfMainProducts().get(i).shouldHave(text(generic));
            clickOnProductInTecDocListing(i).checkCompatibilityProductAndGeneric();
            back();
        }
        return this;
    }

    @Step("click on Product in TecDoc listing .Search_page")
    public Product_page_Logic clickOnProductInTecDocListing(int point) {
        titleOfMainProducts().get(point).scrollIntoView("{block: \"center\"}").click();
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
        for (int i = 0; i < positionOfProduct; i++) {
            btnAddedProductToWishList().get(i).scrollIntoView("{block: \"center\"}");
            checkVisibleBrands();
            if (popUpSelector().isDisplayed()) {
                closePopUpSelector().shouldBe(visible).click();
                popUpSelector().shouldNotBe(visible);
            }
            btnAddedProductToWishList().get(i).shouldBe(visible).click();
            addedProductToWishList().get(i).shouldBe(exist);
        }
        return this;
    }

    @Step("add Product to WishList with selected car. Search_page")
    public Search_page_Logic addedProductToWishListWithSelectedCar(int positionOfProduct) {
        for (int i = 0; i < positionOfProduct; i++) {
            btnAddedProductToWishList().get(i).scrollIntoView("{block: \"center\"}");
            btnAddedProductToWishList().get(i).shouldBe(visible).click();
            addedProductToWishList().get(i).shouldBe(exist);
        }
        return this;
    }

    @Step("added Product to WishList. Search_page")
    public Search_page_Logic addNotActiveProductToWishList(int positionOfProduct) {
        labelAddToWishListNotActiveProduct().get(positionOfProduct).click();
        return this;
    }

    @Step("check visible brands. Search_page")
    public Search_page_Logic checkVisibleBrands() {
        for (int i = 0; i < 2; i++) {
            visibleBrands().get(i).shouldBe(exist);
        }
        return this;
    }

    @Step("go to WishList page. Search_page")
    public Services_wishList_page_Logic goToWishListPage() {
        iconOfWishList().shouldBe(visible).click();
        return page(Services_wishList_page_Logic.class);
    }

    @Step("presence Refurbished Characteristic in listing. Search_page")
    public Search_page_Logic presenceRefurbishedCharacteristic(String expectedCharacteristic) {
        titleOnSearchPage().shouldBe(visible);
        List<String> listOfCharacteristic = new ArrayList<>();
        String currentTitleOfProduct;
        addedAllCharacteristicsOfProductToList(listOfCharacteristic);
        while (forwardLinkOfPaginator().isDisplayed()) {
            currentTitleOfProduct = visibleTitleOfProducts().get(0).getText();
            forwardLinkOfPaginator().scrollIntoView("{block: \"end\"}").click();
            visibleTitleOfProducts().get(0).shouldNotHave(exactText(currentTitleOfProduct));
            addedAllCharacteristicsOfProductToList(listOfCharacteristic);
        }
        Assert.assertTrue(listOfCharacteristic.contains(expectedCharacteristic.replace(" ", "")));
        return this;
    }

    @Step("added all characteristics of product to list. Search_page")
    public Search_page_Logic addedAllCharacteristicsOfProductToList(List<String> list) {
        for (int i = 0; i < allCharacteristicsOfProducts().size(); i++) {
            list.add(getTextFromUnVisibleElement(allCharacteristicsOfProducts().get(i)).replaceAll("\n", "").replace(" ", ""));
        }
        return this;
    }

    @Step("presence Refurbished Characteristic In Listing if art number contains expected symbol . Search_page")
    public Search_page_Logic presenceRefurbishedCharacteristicInListingWithArticle(String expectedCharacteristic, String symbol) {
        titleOnSearchPage().shouldBe(visible);
        checkCharacteristicOfTopProduct(expectedCharacteristic, symbol);
        return this;
    }

    @Step("checking characteristic of TOP product . Search_page")
    public Search_page_Logic checkCharacteristicOfTopProduct(String expectedCharacteristic, String symbol) {
        List<String> characteristics = new ArrayList<>();

        for (int i = 0; i < visibleArtNumOfProduct().size(); i++) {

            String titleOfBrandImage = titleOfMainProducts().get(i).getText();
            String artNumOfProduct = visibleArtNumOfProduct().get(i).getText().replace("Artikelnummer: ", "");


            if (titleOfBrandImage.contains("Henkel Parts") && artNumOfProduct.contains(symbol)) {
                for (int j = 0; j < visibleCharacteristicsOfProducts(i + 1).size(); j++) {
                    characteristics.add(getTextFromUnVisibleElement(visibleCharacteristicsOfProducts(i + 1).get(j)).replaceAll("\n ", "").replace(" ", ""));
                }
                Assert.assertTrue(characteristics.contains(expectedCharacteristic.replaceAll(" ", "")));
                characteristics.clear();
            }
        }
        return this;
    }

    @Step(" added article number Of product to list. Search_page")
    public List<String> addArtNumOfProductToList(int countOfArtNum) {
        List<String> artNumOfProduct = artNumOfProduct().stream().limit(countOfArtNum).map(n -> n.getText().replaceAll("Artikelnummer: ", "")).collect(Collectors.toList());
        return artNumOfProduct;
    }

    @Step(" added article number Of not active product to list. Search_page")
    public List<String> addArtNumOfNotActiveProductToList(int countOfArtNum) {
        List<String> artNumOfNotActiveProductsProduct = new ArrayList<>();
        addNotActiveProductToList(artNumOfNotActiveProductsProduct);
        return artNumOfNotActiveProductsProduct;
    }

    @Step(" added article number Of not active product to list. Search_page")
    public Search_page_Logic addNotActiveProductToList(List<String> list) {

        while (!labelAddToWishListNotActiveProduct().get(0).isDisplayed()) {
            checkVisibleBrands();
            if (popUpSelector().isDisplayed()) {
                closePopUpSelector().shouldBe(visible).click();
                popUpSelector().shouldNotBe(visible);
            }
            forwardOfPaginator().click();
        }
        list.add(artNumOfNotActiveProduct().get(0).getText().replace("Artikelnummer: ", ""));
        return this;
    }

    @Step("remove products from WishList by click on label. Search_page")
    public Search_page_Logic removeProductsFromWishList(int countOfProduct) {
        String currentCountOfProduct;
        for (int i = 0; i < countOfProduct; i++) {
            currentCountOfProduct = currentCountOfProductInWishList().getText();
            addedProductToWishListLabel().get(i).click();
            currentCountOfProductInWishList().shouldNotHave(exactText(currentCountOfProduct));
        }
        return this;
    }

    @Step("added article number of added product of Wishlist to collections. Search_page")
    public List<String> addArtNumAddedProductOfWishlistToList() {
        List<String> artNumList = artNumOfAddedProductToWishList().stream().map(n -> n.getText().replaceAll("Artikelnummer: ", "")).collect(Collectors.toList());
        return artNumList;
    }

    @Step("added article number of added product of Wishlist to collections. Search_page")
    public Search_page_Logic checkCountOfAddedProductToWishListInIcon(List<String> list) {
        int countOfAddedProductInWishListIcon = Integer.parseInt(currentCountOfProductInWishList().getText());
        Assert.assertEquals(list.size(), countOfAddedProductInWishListIcon);
        return this;
    }

    @Step("get elements from Price block. Search_page")
    public String getElementsFromPriceBlock(int positionOfProduct) {
        String textOfElements = priceBlock().get(positionOfProduct).getText();
        return textOfElements;
    }

    @Step("get url of Product image. Search_page")
    public String getUrlOfProductImageBrand(int positionOfProduct) {
        String textOfElements = imageOfBrandInProductBlock().get(positionOfProduct).getAttribute("srcset");
        return textOfElements;
    }

    @Step("get of all product characteristics. Search_page")
    public List<String> getAllProductCharacteristics(int positionOfProduct) {
        List<String> listOfCharacteristics = visibleCharacteristicsOfProducts(positionOfProduct + 1).stream().map(n -> n.getText()).collect(Collectors.toList());
        return listOfCharacteristics;
    }

    @Step("get amount quantity of add Product. Search_page")
    public String getAmountQuantityOfProduct(int positionOfProduct) {
        String amountQuantity = amountQuantityOfProduct().get(positionOfProduct).getAttribute("value");
        return amountQuantity;
    }

    @Step("get article number of product. Search_page")
    public List<String> getArtNumOfProduct(int countOfProduct) {
        List<String> artNum = visibleArtNumOfProduct().stream().limit(countOfProduct).map(n -> n.getText().replaceAll("Artikelnummer: ", "")).collect(Collectors.toList());
        return artNum;
    }

    @Step("go to basket .Search_page")
    public Cart_page_Logic goToBasket() {
        basket().shouldBe(visible).click();
        return page(Cart_page_Logic.class);
    }

    @Step("check Image of brand at presence of expected article and title ofBrand. Search_page")
    public Search_page_Logic checkImageOfBrandAtPresenceOfArtAndBrand(String brand, String artNum) {
        mainListingBlock().shouldBe(visible);
        for (int i = 0; i < imageOfBrandInProductBlock().size(); i++) {
            String attributeOfBrandImage = imageOfBrandInProductBlock().get(i).getAttribute("alt");
            Assert.assertTrue(attributeOfBrandImage.contains(brand));
            Assert.assertTrue(attributeOfBrandImage.contains(artNum));
        }
        return this;
    }

    @Step("presence OEN number in product page. Search_page")
    public Search_page_Logic presenceOenNumInProductPage(String expectedBand, String oemNum) {
        for (int i = 0; i < titleOfProductsInListing().size(); i++) {
            presenceOfTecDocListing();
            titleOfProductsInListing().get(i).shouldHave(text(expectedBand));
            clickOnTitleOfProduct(i).presenceOenNumber(oemNum);
            back();
        }
        return this;
    }

    @Step("presence OEN number in product page. Search_page")
    public Product_page_Logic clickOnTitleOfProduct(int positionOfTitle) {
        titleOfProductsInListing().get(positionOfTitle).shouldBe(visible).click();
        return page(Product_page_Logic.class);
    }
}

