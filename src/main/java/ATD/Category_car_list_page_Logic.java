package ATD;

import files.Product;
import io.qameta.allure.Step;
import org.testng.Assert;

import java.util.*;

import static ATD.CommonMethods.waitWhileRouteBecomeExpected;
import static ATD.CommonMethods.waitingWhileLinkBecomeExpected;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;

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
    public Category_car_list_page_Logic checkTecDocListing() {
        List<Product> productList = new ArrayList<>();

        List<String> expectedGenerics = Arrays.asList("Luftmassenmesser",
                "Impulsgeber, Kurbelwelle", "Sensor, Nockenwellenposition", "Sensor, Kühlmitteltemperatur",
                "Sensor, Ladedruck", "Sensor, Zündimpuls", "Sensor, Fahrpedalstellung",
                "Klopfsensor", "Sensor, Ansauglufttemperatur", "Drehzahlsensor, Motormanagement",
                "Öldruckschalter", "Steuergerät, Motormanagement", "Luftmengenmesser",
                "Sensor, Kühlmitteltemperatur", "Sensor, Ladedruck", "Sensor, Öldruck",
                "Sensor, Öltemperatur", "Steuergerät, Kraftstoffeinspritzung", "Sensor, Kühlmitteltemperatur",
                "Steuergerät, Zündanlage", "Sensor, Kühlmitteltemperatur", "Steuergerät, Einspritzanlage",
                "Schalter, Kupplungsbetätigung (Motorsteuerung)", "Sensor, Öltemperatur / -druck", "Gehäuse, Luftmengenmesser",
                "Schalter, Bremsbetätigung (Motorsteuerung)", "Sensor, Zylinderkopftemperatur", "Lambdasondensatz",
                "Elektromotor, Gebläse Steuergerätebox");

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
}
