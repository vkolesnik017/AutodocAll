package ATD;


import com.codeborne.selenide.ElementsCollection;
import files.Product;
import io.qameta.allure.Step;
import org.testng.Assert;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.*;
import java.util.stream.Collectors;

import static ATD.CommonMethods.waitWhileRouteBecomeExpected;
import static ATD.CommonMethods.waitingWhileLinkBecomeExpected;
import static PKW.CommonMethods.getTextFromUnVisibleElement;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.WebDriverRunner.url;
import static org.testng.Assert.assertEquals;

public class Category_car_list_page_Logic extends Category_car_list_page {

    @Step("Verify name route equals category_car_list. Category_car_list_page")
    public Category_car_list_page_Logic verifyNameRouteEqualsCategoryCarList() {
        waitWhileRouteBecomeExpected("category_car_list");
        return this;
    }

    //form soft 404

    @Step("Checking behavior of soft form 404.Using mail {mail} Category_car_list_page")
    public Category_car_list_page_Logic checkingBehaviorSoft404(String mail) {
        mailFieldSoftForm().setValue(mail);
        submitBtnSoftForm().click();
        errPopupSoftForm().shouldHave(text("Um fortzufahren bestätigen Sie bitte Ihr Newsletter-Abo"));
        closeErrPopupSoftForm().click();
        subscribeCheckboxSoftForm().click();
        submitBtnSoftForm().click();
        successPopupSoftForm().shouldBe(appear);
        closeSuccessPopupSoftForm().shouldHave(text("Einkauf fortsetzen")).click();
        waitingWhileLinkBecomeExpected("https://www.autodoc.de/ersatzteile/genesis/g90/g90/123335-3-3-t-gdi");
        return this;
    }

    @Step(":soft 404 form. Category_car_list_page")
    public Category_car_list_page_Logic checkingDatenschutzerklarungLinkBehaviorSoftForm() {
        new CommonMethods().checkingDatenschutzerklarungLinkBehavior(datenschutzerklarungLinkSoftForm(), "underline solid rgb(0, 0, 0)");
        return this;
    }

    @Step(":for Category_car_list_page")
    public Product_page_Logic clickFirstProductOnListing() {
        new Listing_page_Logic().clickFirstProductOnListing();
        return page(Product_page_Logic.class);
    }

    @Step("select brand in brands block. Category_car_list_page")
    public Category_car_list_page_Logic selectBrandInBlock(String idOfBrand) {
        brandsFilterBlock().shouldBe(visible);
        while (!brandsLinkInSideBar(idOfBrand).isDisplayed()) {
            forwardLinkAtBrandsFilter().click();
        }
        brandsLinkInSideBar(idOfBrand).shouldBe(visible).click();
        appearsOfLoader();
        return this;
    }

    @Step("appears of Loader .Category_car_list_page")
    public Category_car_list_page_Logic appearsOfLoader() {
        loaderInTecDocListing().should(appear);
        loaderInTecDocListing().should(disappear);
        return this;
    }

    @Step("check listing with selected brands .Category_car_list_page")
    public Category_car_list_page_Logic checkListingWithSelectedBrands(String brands) {
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

    @Step("check absence of Quantity characteristic in Product description block .Category_car_list_page")
    public Category_car_list_page_Logic checkAbsenceOfQuantityCharacteristicInProductDescriptionBlock() {
        for (int i = 0; i < descriptionBlockOfProduct().size(); i++) {
            for (int j = 0; j < characteristicListOfProduct(i + 1).size(); j++) {
                characteristicListOfProduct(i + 1).get(j).shouldNotHave(exactText("Menge"));
            }
        }
        return this;
    }

    @Step("checking TecDoc listing .Category_car_list_page")
    public Category_car_list_page_Logic checkTecDocListing(List<String> expectedGenerics) {
        List<Product> productList = new ArrayList<>();
        addedProductsToList(productList, expectedGenerics);
        while (forwardNextPaginator().isDisplayed() && !notActiveBtnAddProductToBasket().get(0).isDisplayed()) {
            forwardNextPaginator().click();
            addedProductsToList(productList, expectedGenerics);
        }
        List<Product> listBeforeSorting = new ArrayList<>(productList);

        Comparator<String> genericsComparator = (g1, g2) -> {
            if (!expectedGenerics.contains(g1)) {
                return 1;
            }
            if (!expectedGenerics.contains(g2)) {
                return -1;
            }
            return expectedGenerics.indexOf(g1) - expectedGenerics.indexOf(g2);
        };

        Comparator<Product> productsComparator = Comparator
                .comparing((Product p) -> "RIDEX".equals(p.getBrandOfProduct()) ? -1 : 0)
                .thenComparing(Product::getGenericOfProduct, genericsComparator)
                .thenComparingDouble(Product::getPriceOfProduct);
        productList.sort(productsComparator);
        Assert.assertEquals(listBeforeSorting, productList);

        return this;
    }

    @Step("added products to list .Category_car_list_page")
    public Category_car_list_page_Logic addedProductsToList(List<Product> list, List<String> genericList) {
        String brand, generic, price, url, checkUrl, genericForList = null;
        for (int i = 0; i < activeBtnAddProductToBasket().size(); i++) {
            brand = activeBtnAddProductToBasket().get(i).getAttribute("data-brand-name");
            generic = titleOfProductInTecDocListing().get(i).getText().replaceAll(brand + " ", " ")
                    .replace("\n" + subTitleOfProductInTecDocListing().get(i).getText(), "");
            for (int j = 0; j < genericList.size(); j++) {
                if (generic.contains(genericList.get(j))) {
                    genericForList = genericList.get(j);
                }
            }
            Product productPage = new Product();
            productPage.setGenericOfProduct(genericForList);
            productPage.setBrandOfProduct(brand);
            productPage.setPriceOfProduct(Double.parseDouble(priceOfProduct().get(i).getText().replaceAll("[^0-9,]", "").replace(",", ".")));
            list.add(productPage);
        }
        return this;
    }

    @Step("input generic in Search field. Category_car_list_page")
    public Search_page_Logic inputGenericInSearchField(String generic) {
        mainSearchField().shouldBe(visible).setValue(generic);
        btnSearchOfSearchField().click();
        return page(Search_page_Logic.class);
    }


    @Step("Login in header with mail {mail} and transition to profile plus page and go back. Category_car_list_page")
    public Category_car_list_page_Logic loginToProfilePlusPageAndBack(String email) {
        new Main_page_Logic().loginAndTransitionToProfilePlusPage(email).visibilityOfUsersName();
        back();
        return this;
    }

    @Step("update of page. Category_car_list_page")
    public Category_car_list_page_Logic updateOfPage() {
        refresh();
        return this;
    }

    @Step("update of page. Category_car_list_page")
    public Category_car_list_page_Logic checkCountOfVehicleInIconOfGarage(String expectedCountOfVehicle) {
        countOfVehicleInIconOfGarageInHeader().shouldBe(visible).shouldHave(exactText(expectedCountOfVehicle));
        return this;
    }

    @Step("click on Garage icon in header. Category_car_list_page")
    public Category_car_list_page_Logic clickOnGarageIconInHeader() {
        headerGarageIcon().shouldBe(visible).click();
        popUpOfGarageInHeader().shouldBe(visible);
        return this;
    }

    @Step("click on Garage icon in header. Category_car_list_page")
    public Category_car_list_page_Logic selectVehicleInGaragePopUp(String idOfVehicle) {
        idOfVehicleInGaragePopUp(idOfVehicle).shouldBe(visible).click();
        return this;
    }

    @Step("presence Refurbished Characteristic in listing. Category_car_list_page")
    public Category_car_list_page_Logic presenceRefurbishedCharacteristic(String expectedCharacteristic) {
        listingOfProductsBlock().shouldBe(visible);
        List<String> listOfCharacteristic = new ArrayList<>();
        String currentArtNumOfProduct;
        addedAllCharacteristicsOfProductToList(listOfCharacteristic);
        while (!listOfCharacteristic.contains(expectedCharacteristic.replace(" ", ""))) {
            currentArtNumOfProduct = artNumOfProduct().get(0).getText();
            forwardLinkOfPaginator().scrollIntoView("{block: \"end\"}").click();
            titleOfProductInTecDocListing().get(0).shouldNotHave(exactText(currentArtNumOfProduct));
            addedAllCharacteristicsOfProductToList(listOfCharacteristic);
        }
        Assert.assertTrue(listOfCharacteristic.contains(expectedCharacteristic.replace(" ", "")));
        return this;
    }

    @Step("added all characteristics of product to list. Category_car_list_page")
    public Category_car_list_page_Logic addedAllCharacteristicsOfProductToList(List<String> list) {
        for (int i = 0; i < allCharacteristicsOfProducts().size(); i++) {
            list.add(getTextFromUnVisibleElement(allCharacteristicsOfProducts().get(i)).replaceAll("\n", "").replace(" ", ""));
        }
        return this;
    }

    @Step("presence Refurbished Characteristic In Listing if art number contains expected symbol . Category_car_list_page")
    public Category_car_list_page_Logic presenceRefurbishedCharacteristicInListingProductWithArticle(String expectedCharacteristic, String symbol) {
        listingOfProductsBlock().shouldBe(visible);
        checkCharacteristicOfTopProduct(expectedCharacteristic, symbol);
        return this;
    }

    @Step("checking characteristic of TOP product . Category_car_list_page")
    public Category_car_list_page_Logic checkCharacteristicOfTopProduct(String expectedCharacteristic, String symbol) {
        for (int i = 0; i < artNumOfProduct().size(); i++) {
            List<String> characteristics = new ArrayList<>();
            String titleOfBrandImage = titleOfProductInTecDocListing().get(i).getText();
            String artNumOfProduct = artNumOfProduct().get(i).getText().replace("Artikelnummer: ", "");
            if (titleOfBrandImage.contains("Henkel Parts") && artNumOfProduct.contains(symbol)) {
                for (int j = 0; j < visibleCharacteristicsOfProducts(i + 1).size(); j++) {
                    characteristics.add(getTextFromUnVisibleElement(visibleCharacteristicsOfProducts(i + 1).get(j)).replaceAll("\n", "").replace(" ", ""));
                }
                Assert.assertTrue(characteristics.contains(expectedCharacteristic.replaceAll(" ", "")));
                characteristics.clear();
            }
        }
        return this;
    }

    @Step("presence of TecDoc listing . Category_car_list_page")
    public Category_car_list_page_Logic presenceOfTecDocListing() {
        listingOfProductsBlock().shouldBe(visible);
        return this;
    }

    @Step(" added article number Of product to list. Category_car_list_page")
    public List<String> addArtNumOfProductToList(int countOfArtNum) {
        List<String> artNumOfProduct = artNumOfProduct().stream().limit(countOfArtNum).map(n -> n.getText().replaceAll("Artikelnummer: ", "")).collect(Collectors.toList());
        return artNumOfProduct;
    }

    @Step("added Product to WishList. Category_car_list_page")
    public Category_car_list_page_Logic addProductToWishList(int positionOfProduct) {
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

    @Step("check visible brands. Category_car_list_page")
    public Category_car_list_page_Logic checkVisibleBrands() {
        for (int i = 0; i < 2; i++) {
            visibleBrands().get(i).shouldBe(exist);
        }
        return this;
    }

    @Step("Get Id list all parents from Teilecatalog in sidebar. Category_car_list_page")
    public ArrayList<String> getIdListParentsTeilecatalogInSidebar() {
        ArrayList<String> idListParents = new ArrayList<>();
        if (btnTeilecatalogInSidebar().isDisplayed()) {
            btnTeilecatalogInSidebar().scrollIntoView("{block: \"center\"}").click();
            parentFromTeilecatalogInSidebar().waitUntil(visible, 10000);
        }
        for (int i = 0; i < parentsIdFromTeilecatalogInSidebar().size(); i++) {
            String parentId = parentsIdFromTeilecatalogInSidebar().get(i).getAttribute("src").replaceAll("[\\s\\S]*\\/", "").replaceAll(".png", "").trim();
            idListParents.add(parentId);
        }
        return idListParents;
    }

    @Step("Compare two list with parent between front and Aws. Category_car_list_page")
    public Category_car_list_page_Logic compareTwoListsBetweenFrontAndAws(List<String> listFront, List<String> listAws, List<String> groupRating) {

        for (int i = 0; i < listFront.size(); i++) {
            if (!listFront.get(i).equals(listAws.get(i))) {
                try {
                    if (groupRating.get(i + 1).equals(groupRating.get(i))) {
                        Assert.assertEquals(listFront.get(i), (listAws.get(i + 1)));
                    } else if (groupRating.get(i - 1).equals(groupRating.get(i))) {
                        Assert.assertEquals(listFront.get(i), (listAws.get(i - 1)));
                    } else if (!listFront.contains(listAws.get(i))) {
                        listAws.remove(listAws.get(i));
                        groupRating.remove(groupRating.get(i));
                        i--;
                    } else {
                        Assert.fail("Products not equals between front and aws!");
                    }
                } catch (IndexOutOfBoundsException x) {
                    if (groupRating.get(i - 1).equals(groupRating.get(i))) {
                        Assert.assertEquals(listFront.get(i), (listAws.get(i - 1)));
                    } else {
                        Assert.fail("Products not equals between front and aws!");
                    }
                }
            }
        }
        return this;
    }

    @Step("presence Refurbished characteristic in listing of Product with expected Characteristic. Category_car_list_page")
    public Category_car_list_page_Logic presenceRefurbishedCharacteristicInListingProductWithCharacteristic(String expectedCharacteristic, String titleOfProduct, String presentCharacteristic) {
        listingOfProductsBlock().shouldBe(visible);
        List<String> characteristicOfProduct = new ArrayList<>();
        for (int i = 0; i < titleOfProductInTecDocListing().size(); i++) {
            if (titleOfProductInTecDocListing().get(i).getText().contains(titleOfProduct)) {
                characteristicOfProduct = visibleCharacteristicsOfProducts(i + 1).stream().map(n -> n.getText().replaceAll("\n", "")).collect(Collectors.toList());
                if (characteristicOfProduct.contains(presentCharacteristic)) {
                    Assert.assertTrue(characteristicOfProduct.contains(expectedCharacteristic));
                }
            }
            characteristicOfProduct.clear();
        }
        return this;
    }

    @Step("check work of slider in generic block. Category_car_list_page")
    public Category_car_list_page_Logic checkWorkOfSliderInGenericBlock() {
        genericBlock().shouldBe(visible);
        String firstGeneric = visibleTitleOfGenerics().get(0).getText();
        rightPaginatorOfGenericBlock().shouldBe(visible).click();
        presenceVisibleTittleOfGeneric();
        visibleTitleOfGenerics().get(0).shouldNotHave(exactText(firstGeneric));
        leftPaginatorOfGenericBlock().shouldBe(visible).click();
        presenceVisibleTittleOfGeneric();
        visibleTitleOfGenerics().get(0).shouldHave(exactText(firstGeneric));
        return this;
    }

    @Step("presence of visible tittle Of generic. Category_car_list_page")
    public Category_car_list_page_Logic presenceVisibleTittleOfGeneric() {
        for (int i = 0; i < visibleTitleOfGenerics().size() - 1; i++) {
            visibleTitleOfGenerics().get(i).shouldBe(visible);
        }
        return this;
    }

    @Step("select car in selector. Category_car_list_page")
    public Category_car_list_page_Logic selectCarInSelector(String brand, String model, String motor) {
        new Main_page_Logic().chooseBrandModelTypeInSelector(brand, model, motor);
        btnSearchOfSelectedSelector().shouldBe(visible).click();
        return this;
    }

    @Step("check response of server. Category_car_list_page")
    public Category_car_list_page_Logic checkResponseOfServer(int code) throws IOException {
        URL url = new URL(url());
        HttpURLConnection http = (HttpURLConnection) url.openConnection();
        http.setInstanceFollowRedirects(true);
        int responseCode = http.getResponseCode();
        assertEquals(responseCode, code);
        return this;
    }

    @Step("select visible brand in brands block. Category_car_list_page")
    public Category_car_list_page_Logic selectVisibleBrandInBlock(String idOfBrand) {
        brandsFilterBlock().shouldBe(visible);
        while (!visibleBrandsLinkInSideBar(idOfBrand).isDisplayed()) {
            forwardLinkAtBrandsFilter().click();
            checkVisibleBrands();
        }
        visibleBrandsLinkInSideBar(idOfBrand).shouldBe(visible).click();
        appearsOfLoader();
        return this;
    }


    @Step("checkListingWithVisibleSelectedBrands(selectedBrands). Category_car_list_page")
    public Category_car_list_page_Logic checkListingWithVisibleSelectedBrands(List<String> selectedBrands) {
        Set<String> listOfBrands = new HashSet<>();
        addProductToSetList(listOfBrands);
        while (forwardNextPaginator().isDisplayed()) {
            forwardNextPaginator().click();
            addProductToSetList(listOfBrands);
        }
        List<String> brandsFromListing = new ArrayList<>(listOfBrands);
        Collections.sort(brandsFromListing);
        Collections.sort(selectedBrands);
        Assert.assertEquals(brandsFromListing, selectedBrands);
        return this;
    }

    @Step("select visible brand in brands block. Category_car_list_page")
    public Category_car_list_page_Logic addProductToSetList(Set<String> list) {
        for (int i = 0; i < allBtnAddToBasket().size(); i++) {
            list.add(allBtnAddToBasket().get(i).attr("data-brand-name"));
        }
        return this;
    }

    @Step("Click btn Teilecatalog in sidebar. Category_car_list_page")
    public Category_car_list_page_Logic clickBtnTeilecatalogInSidebar() {
        btnTeilecatalogInSidebar().shouldBe(visible).click();
        parentFromTeilecatalogInSidebar().shouldBe(visible);
        return this;
    }

    @Step(": from. Category_car_list_page")
    public ArrayList<String> getHrefOrUrlCategoriesThenWriteToList(ElementsCollection categories) {
        return new Main_page_Logic().getHrefOrUrlCategoriesThenWriteToList(categories);
    }

    @Step(": from. Category_car_list_page")
    public Category_car_list_page_Logic checkCategoriesForServerResponses200( List<String> allCategories) throws IOException {
        new Index_instruments_page_Logic().checkCategoriesForServerResponses200(allCategories);
        return this;
    }

    @Step("checking the alternative name of the product through the product article. Category_car_list_page")
    public Category_car_list_page_Logic checkAlternativeTitleOfProductThroughArticle(String artNUm, String alternativeTitle) {

        if (!titleOfProductWithArtNum(artNUm).isDisplayed()) {
            while (!titleOfProductWithArtNum(artNUm).isDisplayed()) {
                forwardNextPaginator().click();
            }
            titleOfProductWithArtNum(artNUm).shouldHave(text(alternativeTitle));
        } else {
            titleOfProductWithArtNum(artNUm).shouldBe(visible).shouldHave(text(alternativeTitle));
        }


        return this;
    }
}
