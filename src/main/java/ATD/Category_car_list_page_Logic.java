package ATD;

import com.codeborne.selenide.Condition;
import files.Product;
import io.qameta.allure.Step;
import org.testng.Assert;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static ATD.CommonMethods.waitWhileRouteBecomeExpected;
import static ATD.CommonMethods.waitingWhileLinkBecomeExpected;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.page;

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
        errPopupSoftForm().shouldHave(Condition.text("Um fortzufahren bestätigen Sie bitte Ihr Newsletter-Abo"));
        closeErrPopupSoftForm().click();
        subscribeCheckboxSoftForm().click();
        submitBtnSoftForm().click();
        successPopupSoftForm().shouldBe(appear);
        closeSuccessPopupSoftForm().shouldHave(Condition.text("Einkauf fortsetzen")).click();
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
        addedProductsToList(productList);
        while (forwardNextPaginator().isDisplayed() && !notActiveBtnAddProductToBasket().get(0).isDisplayed()) {
            forwardNextPaginator().click();
            addedProductsToList(productList);
        }
        for (int i = 0; i < productList.size(); i++) {
            System.out.println(productList.get(i).getBrandOfProduct() + " - " + productList.get(i).getGenericOfProduct() + " - " + productList.get(i).getPriceOfProduct());
        }
        return this;
    }

    @Step("added products to list .Category_car_list_page")
    public Category_car_list_page_Logic addedProductsToList(List<Product> list) {
        for (int i = 0; i < activeBtnAddProductToBasket().size(); i++) {
            Product productPage = new Product();
            productPage.setBrandOfProduct(activeBtnAddProductToBasket().get(i).getAttribute("data-brand-name"));
            productPage.setGenericOfProduct(activeBtnAddProductToBasket().get(i).getAttribute("data-name"));
            productPage.setPriceOfProduct(Double.parseDouble(priceOfProduct().get(i).getText().replaceAll("[^0-9,]", "").replace(",", ".")));
            list.add(productPage);
        }
        return this;
    }
}
