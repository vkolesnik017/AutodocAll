package PKW;

import io.qameta.allure.Step;
import org.testng.Assert;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static PKW.CommonMethods.getAttributeFromUnVisibleElement;
import static PKW.CommonMethods.getTextFromUnVisibleElement;
import static com.codeborne.selenide.Condition.attribute;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.page;


public class Tyres_page_Logic extends Tyres_page {

    @Step("Check presence car tabs on page. Tyres_page")
    public Tyres_page_Logic checkPresenceCarTabs() {
        carTabs().shouldBe(visible);
        return this;
    }

    @Step("Checks the entered vehicle data with the data in the selector. Tyres_page")
    public Tyres_page_Logic checkDataAddedVehicleWithDataInSelector(String brandVehicle, String modelVehicle, String motorVehicle) {
        activeMotorInput().shouldBe(visible);
        String brandFromSelector = brandInput().getValue().toUpperCase();
        String modelFromSelector = modelInput().getValue().toUpperCase();
        String motorFromSelector = motorInput().getValue().toUpperCase();
        Assert.assertEquals(brandVehicle, brandFromSelector);
        Assert.assertEquals(modelVehicle, modelFromSelector);
        Assert.assertEquals(motorVehicle, motorFromSelector);
        return this;
    }


    @Step("presence of linking block by brands. Tyres_page")
    public Tyres_page_Logic presenceOfBrandsLinkingBlock() {
        linkingBlockByBrands().shouldBe(visible);
        return this;
    }

    @Step("select brandbByname. Tyres_page")
    public Tyres_brand_page_Logic selectBrandByName(String brand) {
        brandByName(brand).shouldBe(visible).click();
        return page(Tyres_brand_page_Logic.class);
    }

    @Step("click on All brands button. Tyres_page")
    public Tyres_type_list_brands_page_Logic clickOnAllBrands() {
        btnAllBrands().shouldBe(visible).click();
        return page(Tyres_type_list_brands_page_Logic.class);
    }

    @Step("Click on size diameter from relink block. Tyres_page")
    public Tyres_size_page_Logic clickOnSizeDiameterFromRelinkBlock() {
        relinkBlock().scrollIntoView("{block: \"center\"}");
        sizeDiameterFromRelinkBlock().shouldBe(visible).click();
        return page(Tyres_size_page_Logic.class);

    }

    @Step("presence of TOP product block. Tyres_page")
    public Tyres_page_Logic presenceOfTopProductBlock() {
        topProductsBlock().shouldBe(visible);
        return this;
    }

    @Step("checking the uniqueness of brands in TOP products block. Tyres_page")
    public Tyres_page_Logic checkUniquenessOfBrandsInTopProductBlock(int count) {
        List<String> allTitlesOfBrand = imageOfBrandAtTopProducts().stream().map(n -> getAttributeFromUnVisibleElement(n, "src").replaceAll(".+thumbs\\/", "").replaceAll("\\..+", "")).collect(Collectors.toList());
        checkUniqueElementsOfList(allTitlesOfBrand, count);
        return this;
    }


    @Step("checking the unique sizes in TOP products block. Tyres_page")
    public Tyres_page_Logic checkUniqueSizeInTopProductBlock(int count) {
        List<String> allSizesOfTopProducts = allSizesOfTopProducts().stream().map(n -> getTextFromUnVisibleElement(n)).collect(Collectors.toList());
        checkUniqueElementsOfList(allSizesOfTopProducts, count);
        return this;
    }

    @Step("check unique elements of list. Tyres_page")
    public Tyres_page_Logic checkUniqueElementsOfList(List<String> list, int expectedCount) {
        Set<String> uniqueList = new HashSet<>(list);
        List<String> listOfUniqueBrands = new ArrayList<>(uniqueList);

        for (int i = 0; i < listOfUniqueBrands.size(); i++) {
            int count = 0;
            for (int j = 0; j < list.size(); j++) {
                if (listOfUniqueBrands.get(i).equals(list.get(j))) {
                    count++;
                }
            }
            Assert.assertTrue(count <= expectedCount);
            count = 0;
        }
        return this;
    }

    @Step("check size of TOP products. Tyres_page")
    public Tyres_page_Logic checkSizeOfTopProducts(int size) {
        Assert.assertTrue(imageOfBrandAtTopProducts().size() <= size);
        return this;
    }

    @Step("checking the ability to add an item to the cart. Tyres_page")
    public Tyres_page_Logic checkAbilityToAddTopProductToCart() {
        for (int i = 0; i < btnAddTopProductToBasket().size(); i++) {
            btnAddTopProductToBasket().get(i).shouldHave(attribute("class", "basket_btn button active_red_button"));
        }
        return this;
    }
    @Step("check presence relink block by seasons. Tyres_page")
    public Tyres_page_Logic checkPresenceRelinkBlockBySeasons() {
        relinkBlockBySeasons().shouldBe(visible);
        return this;
    }

    @Step("Click on winter seasons from relink block. Tyres_page")
    public Tyres_season_page_Logic clickOnWinterBlockFromRelink() {
        winterSeasonsFromRelinkBlock().click();
        return page(Tyres_season_page_Logic.class);
    }

    @Step("Get url from winter seasons in relink block. Tyres_page")
    public String getUrlFromWinterSeasonsInRelinkBlock() {
        return winterSeasonsFromRelinkBlock().getAttribute("href");
    }

    @Step("Check presence relink by car block. Tyres_page")
    public Tyres_page_Logic checkPresenceRelinkByCarBlock() {
        relinkByCarBlock().scrollIntoView("{block: \"center\"}").shouldBe(visible);
        return this;
    }

    @Step("Click on model in relink by car block. Tyres_page")
    public Tyres_maker_page_Logic clickOnModelInRelinkByCarBlock() {
        modelFromRelinkByCarBlock().click();
        return page(Tyres_maker_page_Logic.class);
    }

    @Step("Click btn more in relink by car block. Tyres_page")
    public Tyres_type_list_makers_page_Logic clickBtnMoreInRelinkByCarBlock() {
        btnMoreFromRelinkByCarBlock().shouldBe(visible).click();
        return page(Tyres_type_list_makers_page_Logic.class);
    }

    @Step("Get url model in relink by car block. Tyres_page")
    public String getUrlModelInRelinkByCarBlock() {
        return modelFromRelinkByCarBlock().getAttribute("href");
    }

    @Step("Get url btn more in relink by car block. Tyres_page")
    public String getUrlBtnMorelInRelinkByCarBlock() {
        return btnMoreFromRelinkByCarBlock().getAttribute("href");
    }

}


