package ATD;

import AWS.ProductCard_aws;
import Common.DataBase;
import Common.Excel;
import files.Product;
import io.qameta.allure.Step;
import org.apache.http.util.Asserts;
import org.testng.Assert;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static ATD.CommonMethods.*;
import static PKW.CommonMethods.checkingContainsUrl;
import static com.codeborne.selenide.Condition.attribute;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.page;
import static com.codeborne.selenide.WebDriverRunner.url;

public class Maker_car_list_page_Logic extends Maker_car_list_page {

    @Step("Verify name route equals maker_car_list. Maker_car_list_page")
    public Maker_car_list_page_Logic verifyNameRouteEqualsMakerCarList() {
        waitWhileRouteBecomeExpected("maker_car_list");
        return this;
    }

    @Step("Input text in search bar by catalog. Maker_car_list_page")
    public Maker_car_list_page_Logic inputTextInSearchBarByCatalog(String text) {
        new Categories_page_Logic().inputTextInSearchBarByCatalog(text);
        return this;
    }

    @Step("Click tooltip in search by catalog by exact text. Maker_car_list_page")
    public Category_car_list_page_Logic clickTooltipInSearchByCatalogByExactText(String exactTooltipText) {
        new Categories_page_Logic().clickTooltipInSearchByCatalogByExactText(exactTooltipText);
        return page(Category_car_list_page_Logic.class);
    }

    @Step("Click oil filter category link. Maker_car_list_page")
    public Category_car_list_page_Logic clickOilFilterCategoryLink() {
        new Categories_page_Logic().clickOilFilterCategoryLink();
        return page(Category_car_list_page_Logic.class);
    }

    @Step(" select car in selector . Maker_car_list_page")
    public Maker_car_list_page_Logic selectCarInSelector(String marke, String model, String motor) {
        selectorInCloseCondition().shouldBe(visible).click();
        mainFormOfSelector().shouldBe(visible);
        markeInSelector().selectOptionByValue(marke);
        modelInSelector().selectOptionByValue(model);
        motorInSelector().selectOptionByValue(motor);
        btnSearchOfSelector().click();
        mainFormOfSelector().shouldNotBe(visible);
        return this;
    }

    @Step("added current url to list . Maker_car_list_page")
    public Maker_car_list_page_Logic addedCurrentUrlToList(List<String> list) {
        selectorInCloseCondition().shouldBe(visible);
        list.add(url());
        return this;
    }

    @Step("check selected vehicle in PopUp of garage icon . Maker_car_list_page")
    public Maker_car_list_page_Logic checkSelectedVehicleInPopUpOfGarageIcon(List<String> list) {

        List<String> listOfVehicleFromPopUp = new ArrayList<>();
        garageIconInHeader().shouldBe(visible).click();
        popUpOfGarageInHeader().shouldBe(visible);
        for (int i = 0; i < urlsOfAddedVehicleInPopUpOfGarageInHeader().size(); i++) {
            listOfVehicleFromPopUp.add(urlsOfAddedVehicleInPopUpOfGarageInHeader().get(i).getAttribute("href"));
        }
        Assert.assertTrue(urlsOfAddedVehicleInPopUpOfGarageInHeader().get(0).getAttribute("href").equals(list.get(list.size() - 1)));
        //    urlsOfAddedVehicleInPopUpOfGarageInHeader().shouldHaveSize(list.size());
        //   Assert.assertEquals(getSortedList(listOfVehicleFromPopUp), getSortedList(list));
        for (int i = 0; i < list.size(); i++) {
            Assert.assertTrue(listOfVehicleFromPopUp.contains(list.get(i)));
        }

        return this;
    }

    @Step("get sorted list . Maker_car_list_page")
    public List<String> getSortedList(List<String> list) {
        List<String> expectedSortedList = new ArrayList<>(list);
        Collections.sort(expectedSortedList);
        return expectedSortedList;
    }

    @Step("clear list of vehicle in pop-up of garage icon . Maker_car_list_page")
    public Maker_car_list_page_Logic clearListOfVehicleInPopUpOfGarageIcon() {
        btnClearVehicleListInPopUpOfGarageIcon().shouldBe(visible).click();
        urlsOfAddedVehicleInPopUpOfGarageInHeader().shouldHaveSize(0);
        return this;
    }

    @Step("checking list of added vehicle in header after selected a new vehicle . Maker_car_list_page")
    public Maker_car_list_page_Logic checkListOfAddedVehicleInHeaderAfterSelectedNewAuto() {
        headingOfSearchByCatalog().shouldBe(visible);
        garageIconInHeader().shouldBe(visible).click();
        urlsOfAddedVehicleInPopUpOfGarageFromSelector().get(0).shouldBe(visible).shouldHave(attribute("href", url()));
        radioBtnOfAddedVehicleInPopUpOfGarageFromSelector().get(0).shouldHave(attribute("checked", "true"));
        return this;
    }


    @Step("select truck block in header . Maker_car_list_page")
    public LKW_main_page_Logic selectTruckBlock() {
        mainCategoriesInHeader().get(0).shouldBe(visible).click();
        return page(LKW_main_page_Logic.class);
    }

    @Step("close popUp of my garage block . Maker_car_list_page")
    public Maker_car_list_page_Logic closePopUpOfMyGarageBlock() {
        garageIconInHeaderActive().shouldBe(visible).click();
        return this;
    }

    @Step("open selector from My garage block . Maker_car_list_page")
    public Maker_car_list_page_Logic openSelectorFromMyGarageBlock() {
        garageIconInHeader().shouldBe(visible).click();
        btnAddedVehicleOfMyGaragePopUp().shouldBe(visible).click();
        selectorFromMyGarageBlock().shouldBe(visible);
        return this;
    }

    @Step("select Truck in selector  from My garage block. Maker_car_list_page")
    public LKW_maker_car_list_Logic selectTruckInSelectorFromMyGarage(String brand, String model, String motor) {
        trucksTab().click();
        markeOfTruckInSelector().shouldBe(visible).selectOptionByValue(brand);
        modelOfTruckInSelector().shouldBe(visible).selectOptionByValue(model);
        motorOfTruckInSelector().shouldBe(visible).selectOptionByValue(motor);
        btnSearchOfSelectorFromMyGarage().click();
        return page(LKW_maker_car_list_Logic.class);
    }

    @Step(": for Maker_car_list_page")
    public Profile_plus_page_Logic profilePlusBtnClickInHeader() {
        new Main_page_Logic().profilePlusBtnClickInHeader();
        return page(Profile_plus_page_Logic.class);
    }

    @Step("presence of TecDoc catalog . Maker_car_list_page")
    public Maker_car_list_page_Logic presenceOfTecDocCatalog() {
        tecDocCatalog().shouldBe(visible);
        return this;
    }

    @Step("check vehicle list before writing . Maker_car_list_page")
    public Maker_car_list_page_Logic checkVehicleListBeforeWrite(List<String> list) {
        selectorInCloseCondition().shouldBe(visible);
        if (list.size() > 0) {
            list.clear();
        }
        return this;
    }

    @Step("get Parent categories from TecDoc catalog . Maker_car_list_page")
    public List<String> getParentCategoriesFromCatalog() {
        List<String> parentCategories = titleOfParentCategories().stream()
                .filter(title -> !title.getText().equals("Reifen"))
                .map(title -> title.getText())
                .collect(Collectors.toList());
        return parentCategories;
    }

    @Step("go to WishList with logged user. Maker_car_list_page")
    public Profile_wishlist_page_Logic goToWishListWithLoggedUser() {
        iconOfWishList().shouldBe(visible).click();
        return page(Profile_wishlist_page_Logic.class);
    }

    @Step("check product sorting by price netto.  Maker_car_list_page")
    public Maker_car_list_page_Logic checkProductSortingByPriceNetto(List<String> list) throws SQLException {
        Search_page_Logic searchPage = new Search_page_Logic();
        DataBase db = new DataBase("ATD");
        List<String> activeFirstPage, activeSecondPage, activeThirdPage;
        List<String> notActiveFirstPage, notActiveSecondPage, notActiveThirdPage;
        List<Double> priceNettoFirstPageAws = new ArrayList<>();
        List<Double> priceNettoSecondPageAws = new ArrayList<>();
        List<Double> priceNettoThirdPageAws = new ArrayList<>();
        List<Product> firstPageProduct = new ArrayList<>();
        List<Product> secondPageProduct = new ArrayList<>();
        List<Product> thirdPageProduct = new ArrayList<>();

        for (int i = 0; i < list.size(); i++) {
            if (!searchBar().isDisplayed()) {
                openPage(db.getFullRouteByRouteName("prod", "DE", "main"));
            }
            new Main_page_Logic().useSearch(list.get(i));

            activeFirstPage = searchPage.getIdOfActiveProduct();
            notActiveFirstPage = searchPage.getIdOfNotActiveProduct();
            searchPage.addProductToList(firstPageProduct, productsList()).checkSortingOfProducts(firstPageProduct).goToNextPage();
            checkingContainsUrl("page=2");
            activeSecondPage = searchPage.getIdOfActiveProduct();
            notActiveSecondPage = searchPage.getIdOfNotActiveProduct();
            searchPage.addProductToList(secondPageProduct, productsList()).checkSortingOfProducts(secondPageProduct).goToNextPage();
            checkingContainsUrl("page=3");
            activeThirdPage = searchPage.getIdOfActiveProduct();
            notActiveThirdPage = searchPage.getIdOfNotActiveProduct();
            searchPage.addProductToList(thirdPageProduct, productsList()).checkSortingOfProducts(thirdPageProduct);
            getPriceNettoOfProduct(priceNettoFirstPageAws, activeFirstPage);
            getPriceNettoOfProduct(priceNettoSecondPageAws, activeSecondPage);
            getPriceNettoOfProduct(priceNettoThirdPageAws, activeThirdPage);
            checkPriceNettoFromAWS(priceNettoFirstPageAws, priceNettoSecondPageAws, priceNettoThirdPageAws);
            priceNettoFirstPageAws.clear();
            priceNettoSecondPageAws.clear();
            priceNettoThirdPageAws.clear();
            getPriceNettoOfProduct(priceNettoFirstPageAws, notActiveFirstPage);
            getPriceNettoOfProduct(priceNettoSecondPageAws, notActiveSecondPage);
            getPriceNettoOfProduct(priceNettoThirdPageAws, notActiveThirdPage);
            checkPriceNettoFromAWS(priceNettoFirstPageAws, priceNettoSecondPageAws, priceNettoThirdPageAws);
            priceNettoFirstPageAws.clear();
            priceNettoSecondPageAws.clear();
            priceNettoThirdPageAws.clear();
            activeFirstPage.clear();
            activeSecondPage.clear();
            activeThirdPage.clear();
            firstPageProduct.clear();
            secondPageProduct.clear();
            thirdPageProduct.clear();
            notActiveFirstPage.clear();
            notActiveSecondPage.clear();
            notActiveThirdPage.clear();
        }
        return this;
    }

    @Step("get price netto of product.  Maker_car_list_page")
    public Maker_car_list_page_Logic getPriceNettoOfProduct(List<Double> priceNetto, List<String> idOfProduct) {
        if (idOfProduct.size() > 0) {
            for (int i = 0; i < idOfProduct.size(); i++) {
                priceNetto.add(new ProductCard_aws(idOfProduct.get(i)).openProductCardPageAndLogin().getPriceNetto());
            }
        }
        return this;
    }

    @Step("check price Netto from aws.  Maker_car_list_page")
    public Maker_car_list_page_Logic checkPriceNettoFromAWS(List<Double> firstList, List<Double> secondList, List<Double> thirdList) {
        if (firstList.size() > 0 && secondList.size() > 0 && thirdList.size() > 0) {
            Assert.assertTrue(Collections.max(firstList) <= Collections.min(secondList));
            Assert.assertTrue(Collections.max(secondList) <= Collections.min(thirdList));
        } else if (firstList.size() > 0 && secondList.size() > 0 && thirdList.size() == 0) {
            Assert.assertTrue(Collections.max(firstList) <= Collections.min(secondList));
        } else if (firstList.size() > 0 && thirdList.size() > 0 && secondList.size() == 0) {
            Assert.assertTrue(Collections.max(firstList) <= Collections.min(thirdList));
        } else if (secondList.size() > 0 && thirdList.size() > 0 && firstList.size() == 0) {
            Assert.assertTrue(Collections.max(secondList) <= Collections.min(thirdList));
        }
        return this;
    }

    @Step("add product from top products block to basket. Maker_car_list_page")
    public Maker_car_list_page_Logic addProductFromTopProductsToBasket() {
        btnAddToBasketFromTopProductsBlock().scrollIntoView("{block: \"center\"}").shouldBe(visible).click();
        popupBasketAddedProducts().waitUntil(attribute("style", "visibility: visible; opacity: 1;"), 10000);
        return this;
    }

    @Step(":from Maker_car_list_page")
    public Cart_page_Logic cartClick() {
        new Main_page_Logic().cartClick();
        return page(Cart_page_Logic.class);
    }

    @Step("presence of TOP products block. Maker_car_list_page")
    public Maker_car_list_page_Logic presenceOfTopProductsBlock() {
        topProductsBlock().shouldBe(visible);
        return this;
    }

    @Step("absence of TOP products block. Maker_car_list_page")
    public Maker_car_list_page_Logic absenceOfTopProductsBlock() {
        topProductsBlock().shouldNotBe();
        return this;
    }

    @Step("check size of TOP products. Maker_car_list_page")
    public Maker_car_list_page_Logic checkSizeOfTopProducts(int expectedSize) {
        allTitlesOfTopProducts().shouldHaveSize(expectedSize);
        return this;
    }

    @Step("checking the ability to add an item to the cart. Maker_car_list_page")
    public Maker_car_list_page_Logic checkingAbilityToAddProductToBasket() {
        List<String> attributeOfButton = allBtnAddToBasketOfTopProducts().stream().map(n -> getAttributeFromUnVisibleElement(n, "class")).collect(Collectors.toList());
        for (int i = 0; i < attributeOfButton.size(); i++) {
            Assert.assertEquals(attributeOfButton.get(i), "button add_basket");
        }
        return this;
    }

    @Step("compare TOP products. Maker_car_list_page")
    public Maker_car_list_page_Logic compareTopProducts(int expectedSize) {
        Set<String> generics = genericsOfTopProducts().stream().map(n -> getAttributeFromUnVisibleElement(n, "data-name")).collect(Collectors.toSet());
        Assert.assertEquals(generics.size(), expectedSize);

        return this;
    }

    @Step("Check presence block with selected vehicle. Maker_car_list_page")
    public Maker_car_list_page_Logic checkPresenceBlockWithSelectedVehicle() {
        blockWithSelectedVehicle().shouldBe(visible);
        return this;
    }

    @Step("Checking the correct display of the parent with Excel. Maker_car_list_page")
    public Maker_car_list_page_Logic checkCorrectDisplayParentWithExcel(String file) {
        List<String> parent;
        parent = new Excel().readFromExcel(file, 0);
        parent.remove("Autolampen");
        for (int i = 0; i < getParentCategoriesFromCatalog().size(); i++) {
            String parentFromFront = getParentCategoriesFromCatalog().get(i);
            String parentFromExcel = parent.get(i);
            Assert.assertEquals(parentFromFront, parentFromExcel);
        }
        return this;
    }


}
