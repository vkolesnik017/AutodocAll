package ATD;

import Common.DataBase;
import files.Product;
import io.qameta.allure.Step;
import org.testng.Assert;

import java.sql.SQLException;
import java.util.*;
import java.util.stream.Collectors;

import static ATD.CommonMethods.getTextFromUnVisibleElement;
import static Common.CommonMethods.checkingContainsUrl;
import static Common.CommonMethods.waitWhileRouteBecomeExpected;
import static com.codeborne.selenide.CollectionCondition.sizeGreaterThan;
import static com.codeborne.selenide.CollectionCondition.sizeLessThanOrEqual;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.WebDriverRunner.url;

// Не создавался класс Category_name_page, так как в нём пока нет надобности

public class Category_name_page_Logic extends Category_name_page {

    @Step("Verify name route equals category_name. Category_name_page")
    public Category_name_page_Logic verifyNameRouteEqualsCategoryName() {
        waitWhileRouteBecomeExpected("category_name");
        return this;
    }

    @Step("check successfully child category page loading. Category_name_page")
    public Category_name_page_Logic checkSuccessfullyChildCategoryLoadingFromMainPage() throws SQLException {
        imageOfChildCategory().shouldBe(visible);
        checkingContainsUrl(new DataBase("ATD").getRouteByRouteName("DE", "category_name8"));
        return this;
    }

    @Step("Login in header with mail {mail} and transition to profile plus page and go back. Category_name_page")
    public Category_name_page_Logic loginToProfilePlusPageAndBack(String email) {
        new Main_page_Logic().loginAndTransitionToProfilePlusPage(email).visibilityOfUsersName();
        back();
        return this;
    }

    @Step("update of page. Category_name_page")
    public Category_name_page_Logic updateOfPage() {
        refresh();
        return this;
    }

    @Step("update of page. Category_name_page")
    public Category_name_page_Logic checkCountOfVehicleInIconOfGarage(String expectedCountOfVehicle) {
        countOfVehicleInIconOfGarageInHeader().shouldBe(visible).shouldHave(exactText(expectedCountOfVehicle));
        return this;
    }

    @Step("click on Garage icon in header. Category_name_page")
    public Category_name_page_Logic clickOnGarageIconInHeader() {
        headerGarageIcon().shouldBe(visible).click();
        popUpOfGarageInHeader().shouldBe(visible);
        return this;
    }

    @Step("click on Garage icon in header. Category_name_page")
    public Category_car_list_page_Logic selectVehicleInGaragePopUp(String idOfVehicle) {
        idOfVehicleInGaragePopUp(idOfVehicle).shouldBe(visible).click();
        return page(Category_car_list_page_Logic.class);
    }

    @Step("presence of TOP products block. Category_name_page")
    public Category_name_page_Logic presenceOfTopProductsBlock() {
        topProductsBlock().shouldBe(visible);
        return this;
    }

    @Step("get id of Dangerous product .Category_name_page")
    public String getIdOfDangerousProduct(int positionOfProduct) {
        topProductsBlock().scrollIntoView("{block: \"center\"}");
        scrollToDangerousElement(positionOfProduct);
        return btnAddDangerousProductToWishList().get(positionOfProduct).getAttribute("data-product-id");
    }

    @Step("get signal word from first dangerous product .Category_name_page")
    public String getSignalWordFromFirstDangerousProduct() {
        dangerousPopUp().shouldBe(visible);
        return getTextFromUnVisibleElement(signalWordOfDangerousProduct().get(0));
    }

    @Step("get attribute of Warning icon in pop-Up .Category_name_page")
    public List<String> getAttributeOfWarningIconInPopUp(int positionOfProduct) {
        topProductsBlock().scrollIntoView("{block: \"center\"}");
        scrollToDangerousElement(positionOfProduct);
        List<String> attribute = new ArrayList<>();
        dangerousProducts().get(positionOfProduct).scrollIntoView("{block: \"center\"}").hover();
        for (int i = 0; i < attributeOfWarningIcon(positionOfProduct + 1).size(); i++) {
            String attributeFromIcon = attributeOfWarningIcon(positionOfProduct + 1).get(i).shouldBe(visible).getAttribute("style").replace("background-image: url(\"", "").replace("\");", "");
            String partOfAttribute = attributeFromIcon.replace(attributeFromIcon.substring(attributeFromIcon.lastIndexOf(".")), "");
            attribute.add(partOfAttribute);
        }
        return attribute;
    }

    @Step("scroll to dangerous element. Category_name_page")
    public Category_name_page_Logic scrollToDangerousElement(int positionOfProduct) {
        String artNumOfFirstTopProduct = null;
        while (!btnAddDangerousProductToWishList().get(positionOfProduct).isDisplayed()) {
            artNumOfFirstTopProduct = visibleArtNumOfTopProduct().get(0).getText();
            forwardOfTopBrandsBlock().click();
            visibleArtNumOfTopProduct().get(0).shouldNotHave(exactText(artNumOfFirstTopProduct));
            checkOfVisibleTopProducts();
        }
        return this;
    }

    @Step("check of visible TOP products. Category_name_page")
    public Category_name_page_Logic checkOfVisibleTopProducts() {
        for (int i = 0; i < visibleTopProducts().size(); i++) {
            visibleTopProducts().get(i).shouldBe(visible);
        }
        return this;
    }

    @Step("click on dangerous label of product and compare elements. Category_name_page")
    public Category_name_page_Logic clickOnDangerousLabelAndCompareElements(int positionOfProduct, List<String> attributeOfWarningIcon) {
        scrollToDangerousElement(positionOfProduct);

        labelTitleDangerousProducts().get(positionOfProduct).shouldBe(visible).click();
        blackBackground().shouldHave(attribute("style", "display: block;"));
        warningPopUp().shouldBe(visible).shouldHave(attribute("style", "display: block;"));
        // titleOfDangerousPopUp().shouldBe(visible).shouldHave(exactText(signalWord));
        infoTextOfDangerousPopUp().shouldNotBe(empty);
        List<String> attributeOfDangerousIcon = new ArrayList<>();
        for (int i = 0; i < attributeOfWarningIcon(positionOfProduct + 1).size(); i++) {
            String urlFromAttribute = attributeOfWarningIcon(positionOfProduct + 1).get(i).getAttribute("style").replace("background-image: url(\"", "").replace("\");", "");
            String partOfAttribute = urlFromAttribute.replace(urlFromAttribute.substring(urlFromAttribute.lastIndexOf(".")), "");
            attributeOfDangerousIcon.add(partOfAttribute);
        }
        Assert.assertEquals(attributeOfDangerousIcon, attributeOfWarningIcon);

        return this;
    }

    @Step("check transition to brand page by click on all brands. Category_name_page")
    public Category_name_page_Logic checkTransitionToBrandPageByAllBrands() {
        String urlOfBrandPage;
        for (int i = 0; i < allTopBrands().size(); i++) {
            topBrandsBlock().shouldBe(visible);
            btnMoreOfBrandsBlock().shouldBe(visible).click();
            visibleTopBrands().shouldHaveSize(24);
            urlOfBrandPage = visibleTopBrands().get(i).getAttribute("href");
            clickOnTopBrands(i);
            checkingContainsUrl(urlOfBrandPage);
            back();
        }
        return this;
    }

    @Step("presence of TOP brands block. Category_name_page")
    public Category_maker_brand_page_Logic clickOnTopBrands(int positionOfTopBrands) {
        visibleTopBrands().get(positionOfTopBrands).click();
        return page(Category_maker_brand_page_Logic.class);
    }

    @Step("check price range block. Category_name_page")
    public Category_name_page_Logic checkPriceRangeBlock() {
        priceInfoText().shouldBe(visible);
        double minPrice = Double.parseDouble(priceInfoText().getText().replaceAll("^.+von\\s", "").replaceAll("€.+", "").replace(",", "."));
        double maxPrice = Double.parseDouble(priceInfoText().getText().replaceAll("^.+bis\\s", "").replaceAll("[^0-9,]", "").replace(",", "."));
        Assert.assertTrue(minPrice > 0 && maxPrice > 0);
        return this;
    }

    @Step("presence of brand headline. Category_name_page")
    public Category_name_page_Logic presenceOfBrandHeadline() {
        headlineOfBrandsBlock().shouldBe(visible).shouldHave(text("Für beliebte Automarken:"));
        return this;
    }

    @Step("check of duplicate in values at brands Block. Category_name_page")
    public Category_name_page_Logic checkOfDuplicateInBrandsBlock(List<String> brands) {
        Set<String> checkingBrands = new HashSet<>(brands);
        Assert.assertEquals(brands.size(), checkingBrands.size());
        return this;
    }

    @Step("presence of brand headline. Category_name_page")
    public Category_name_page_Logic presenceOfManufacturerHeadline() {
        headlineOfManufacturerBlock().shouldBe(visible).shouldHave(text("Von beliebten Herstellern:"));
        return this;
    }

    @Step("get values from brands block. Category_name_page")
    public List<String> getValuesFromBrandsBlock() {
        List<String> brands = valuesOfBrandsBlock().stream().map(n -> n.getText()).collect(Collectors.toList());
        return brands;
    }

    @Step("get values from manufacture block. Category_name_page")
    public List<String> getValuesFromManufactureBlock() {
        List<String> brands = valuesOfManufactureBlock().stream().map(n -> n.getText()).collect(Collectors.toList());
        return brands;
    }

    @Step("check for empty values of manufacturer block. Category_name_page")
    public Category_name_page_Logic checkNotEmptyValuesOfManufacturerBlock() {
        List<String> brands = valuesOfManufactureBlock().stream().map(n -> n.getText()).collect(Collectors.toList());
        int countOfFullValues = 0;
        for (int i = 0; i < brands.size(); i++) {
            if (brands.get(i).matches("\\D+") == true) {
                countOfFullValues++;
            }
        }
        Assert.assertTrue(countOfFullValues >= 2);
        return this;
    }

    @Step("check displaying of information block. Category_name_page")
    public Category_name_page_Logic checkDisplayInfoBlock() {
        infoBlockUnderTopProductsBlock().shouldBe(visible);
        headlineOfInfoBlock().shouldBe(visible);
        return this;
    }

    @Step("check of empty values in an information block. Category_name_page")
    public Category_name_page_Logic checkOfEmptyValuesOfInfoBlock() {
        List<String> list = valuesOfInfoBlock().stream().map(n -> n.getText()).collect(Collectors.toList());
        int countOfEmptyValues = 0;
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).matches("\\W") == true) {
                countOfEmptyValues++;
            }
        }
        Assert.assertTrue(countOfEmptyValues > 0);
        return this;
    }

    @Step("presence of headline at product Article block. Category_name_page")
    public Category_name_page_Logic presenceOfHeadlineProductArtBlock() {
        headlineOfProductArtBlock().shouldBe(visible);
        return this;
    }

    @Step("check format of links in Product article block. Category_name_page")
    public Category_name_page_Logic checkFormatOfLinksInProductArtBlock() {
        valuesOfProductArtBlock().shouldHaveSize(6);
        return this;
    }

    @Step("get Values from Product art Block. Category_name_page")
    public List<String> getValuesFromProductArtNumBlock() {
        List<String> values = valuesOfProductArtBlock().stream().map(n -> n.getText()).collect(Collectors.toList());
        return values;
    }

    @Step("check not empty values of product art block. Category_name_page")
    public Category_name_page_Logic checkNotEmptyValuesOfProductArtBlock() {
        for (int i = 0; i < valuesOfProductArtBlock().size(); i++) {
            valuesOfProductArtBlock().get(i).shouldNotBe(empty);
        }
        return this;
    }

    @Step("check not empty values of product art block. Category_name_page")
    public String getLinkFromProductArtBlock(int positionOfLink) {
        String link = valuesOfProductArtBlock().get(positionOfLink).getText();
        return link;
    }

    @Step("check not empty values of product art block. Category_name_page")
    public Product_page_Logic clickOnProductArtBlockLink(int positionOfLink) {
        valuesOfProductArtBlock().get(positionOfLink).click();
        return page(Product_page_Logic.class);
    }

    @Step("check displaying of Characteristic block. Category_name_page")
    public Category_name_page_Logic checkDisplayCharacteristicBlock() {
        characteristicBlockUnderMainBlock().shouldBe(visible);
        return this;
    }

    @Step("check count of Characteristic bLock. Category_name_page")
    public Category_name_page_Logic checkCountOfCharacteristicBLock() {
        typeOfCharacteristicsBlock().shouldHave(sizeGreaterThan(2)).shouldHave(sizeLessThanOrEqual(3));
        return this;
    }


    @Step("check of duplicate in Characteristic block. Category_name_page")
    public Category_name_page_Logic checkOfDuplicateInCharacteristicBlock() {
        List<Double> valueOfCharacteristic;
        for (int i = 0; i < typeOfCharacteristicsBlock().size() * 2; i++) {
            if (i <= typeOfCharacteristicsBlock().size()) {
                valueOfCharacteristic = allValueFromCharacteristicBlock(i + 1).stream().skip(1).map(n -> Double.parseDouble(n.getText().replaceAll(",", "."))).collect(Collectors.toList());
            } else {
                valueOfCharacteristic = allValueFromCharacteristicBlock(i + 1).stream().map(n -> Double.parseDouble(n.getText().replaceAll(",", "."))).collect(Collectors.toList());
            }
            Set<Double> checkList = new HashSet<>(valueOfCharacteristic);
            Assert.assertEquals(valueOfCharacteristic.size(), checkList.size());
            valueOfCharacteristic.clear();
            checkList.clear();
        }
        return this;
    }

    @Step("check position of empty values in characteristic block. Category_name_page")
    public Category_name_page_Logic checkPositionOfEmptyValuesInCharacteristicBlock() {
        List<Product> characteristicList = new ArrayList<>();
        for (int i = 0; i < typeOfCharacteristicsBlock().size() * 2; i++) {
            if (i <= typeOfCharacteristicsBlock().size()) {
                for (int j = 1; j < allValueFromCharacteristicBlock(i + 1).size(); j++) {
                    Product productPage = new Product();
                    productPage.setValueOfCharacteristics(allValueFromCharacteristicBlock(i + 1).get(j).getText());
                    characteristicList.add(productPage);
                }
            } else {
                for (int j = 0; j < allValueFromCharacteristicBlock(i + 1).size(); j++) {
                    Product productPage = new Product();
                    productPage.setValueOfCharacteristics(allValueFromCharacteristicBlock(i + 1).get(j).getText());
                    characteristicList.add(productPage);
                }
            }
            Collections.reverse(characteristicList);
            List<Product> list = new ArrayList<>(characteristicList);
            List<Product> listAfterSorting = new ArrayList<>(list);
            Comparator<Product> productComparator = Comparator.comparing((Product p) -> "-".equals(p.getValueOfCharacteristics()) ? -1 : 0);
            list.sort(productComparator);
            Assert.assertEquals(list, listAfterSorting);
            characteristicList.clear();

        }

        return this;
    }

    @Step("Click on brand from brands block. Category_name_page")
    public Category_name_brand_page_Logic clickOnBrandFromBrandsBlock() {
        brandImgFromBrandsBlock().shouldBe(visible).click();
        return page(Category_name_brand_page_Logic.class);
    }

    @Step("Get id brand from brands block. Category_name_page")
    public String getIdBrandsFromBrandsBlock() {
        String idBrand = brandImgFromBrandsBlock().getAttribute("src").replaceAll("[\\s\\S]*\\/", "");
        String cutIdBrand = idBrand.replace(idBrand.substring(idBrand.indexOf(".png")), "");
        return cutIdBrand;
    }

    @Step("Get url brand from brands block. Category_name_page")
    public String getUrlBrandsFromBrandsBlock() {
        return hrefBrandFromBrandsBlock().getAttribute("href");
    }

    @Step("Get id category from url. Category_name_page")
    public String getIdCategoryFromUrl() {
        return url().replaceAll("\\D+", "");
    }

    @Step("Concat id category and id brand from brands block. Category_name_page")
    public String concatIdCategoryAndBrand() {
        String idCategoryAndBrand = getIdCategoryFromUrl() + "_" + getIdBrandsFromBrandsBlock();
        return idCategoryAndBrand;
    }

    @Step("check main headline. Category_name_page")
    public Category_name_page_Logic checkMainHeadline(String title) {
        mainHeadline().shouldBe(visible).shouldHave(text(title));
        return this;
    }

    @Step("set Marke In Selector And Refresh Page. Category_name_page")
    public Category_name_page_Logic setMarkeInSelectorAndRefreshPage(String marke) {
        setMarkeInSelector(marke);
        refresh();
        if (!markeFieldInSelector().getSelectedValue().equals(marke)) {
            setMarkeInSelector(marke);
            refresh();
        }
        return this;
    }

    @Step("set Marke In Selector. Category_name_page")
    public Category_name_page_Logic setMarkeInSelector(String marke) {
        markeFieldInSelector().shouldBe(visible).selectOptionByValue(marke);
        Wait().until(webDriver -> markeFieldInSelector().getSelectedValue().equals(marke));
        return this;
    }

    @Step("click on TOP Product Title. Category_name_page")
    public Product_page_Logic clickOnTopProductTitle(int position) {
        titleOfTopProducts().get(position).click();
        return page(Product_page_Logic.class);
    }

    @Step("display of Headline at Summary Table. Category_name_page")
    public Category_name_page_Logic displayHeadlineOfSummaryTable() {
        Assert.assertTrue(headlineOfSummaryTable().shouldBe(visible).getText().matches("KEILRIPPENRIEMEN: WÄHLEN SIE AUS\\s\\d+\\sARTIKELN IM AUTODOC-SORTIMENT"));
        return this;
    }

    @Step("check Titles Of Summary Table. Category_name_page")
    public Category_name_page_Logic checkTitlesOfSummaryTable(List<String> expectedTitles) {
        List<String> frontTitlesOfSummaryTable = titlesOfSummaryTable().stream().map(n -> n.getText()).collect(Collectors.toList());
        Assert.assertEquals(frontTitlesOfSummaryTable, expectedTitles);
        return this;
    }

    @Step("check Headlines Of Seo Text. Category_name_page")
    public Category_name_page_Logic checkHeadlinesOfSeoText(List<String> expectedTitles) {
        List<String> frontTitlesOfSeoBlock = titlesOfSeoBlock().stream().map(n -> n.getText()).collect(Collectors.toList());
        Assert.assertEquals(frontTitlesOfSeoBlock, expectedTitles);
        return this;
    }

}
