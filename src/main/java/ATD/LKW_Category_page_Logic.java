package ATD;

import Common.DataBase;
import com.codeborne.selenide.ElementsCollection;
import io.qameta.allure.Step;
import org.testng.Assert;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static ATD.CommonMethods.checkingContainsUrl;
import static ATD.CommonMethods.getTextFromUnVisibleElement;
import static com.codeborne.selenide.CollectionCondition.*;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Condition.empty;
import static com.codeborne.selenide.Selenide.back;
import static com.codeborne.selenide.Selenide.page;
import static com.codeborne.selenide.WebDriverRunner.url;

public class LKW_Category_page_Logic extends LKW_Category_page {
    @Step("check successfully child category page loading .LKW_Category_page ")
    public LKW_Category_page_Logic checkSuccessfullyChildCategoryPageLoading() {
        imageOfChildCategory().shouldBe(visible);
        headlineOfTopBlockBrands().shouldBe(visible);
        Assert.assertEquals(url(), "https://lkwteile.autodoc.de/ersatzteile/olfilter-200157");
        return this;
    }

    @Step("Check links in bread crumbs block .LKW_Category_page")
    public LKW_Category_page_Logic checkLinksInBreadCrumbsBlock() {
        breadCrumbsLinks().shouldHave(size(6));
        firstLinkOfBreadCrumbsBlock().shouldBe(visible);
        secondLinkOfBreadCrumbsBlock().shouldHave(exactText("Filter"));
        thirdLinkOfBreadCrumbsBlock().shouldHave(exactText("Lenkgetriebehydraulikfilter")).shouldNotHave(attribute("href"));
        fourthLinkOfBreadCrumbsBlock().shouldHave(exactText("Hersteller auswählen")).shouldNotHave(attribute("href"));
        fifthLinkOfBreadCrumbsBlock().shouldHave(exactText("Modell auswählen")).shouldNotHave(attribute("href"));
        sixthLinkOfBreadCrumbsBlock().shouldHave(exactText("Ersatzteile kaufen")).shouldNotHave(attribute("href"));
        return this;
    }

    @Step("Check  link click in bread crumbs block .LKW_Category_page")
    public LKW_Category_page_Logic checkLinkClickInBreadCrumbsBlock() throws SQLException {
        firstLinkClick().checkSuccessfullyLKWCategoriesPageLoading();
        back();
        secondLinkClick();
        checkingContainsUrl(new DataBase("ATD").getRouteByRouteName("DE", "lkw_parent_category"));

        return this;
    }

    @Step("Click on first link in bread crumbs block .LKW_Category_page")
    public LKW_Categories_page_Logic firstLinkClick() {
        firstLinkOfBreadCrumbsBlock().click();
        return page(LKW_Categories_page_Logic.class);
    }

    @Step("Click on second link in bread crumbs block .LKW_Category_page")
    public LKW_Parent_Category_page_Logic secondLinkClick() {
        secondLinkOfBreadCrumbsBlock().click();
        return page(LKW_Parent_Category_page_Logic.class);
    }

    @Step("check of presence child categories in TOP Child Category block in sidebar .LKW_Category_page ")
    public LKW_Category_page_Logic presenceOfChildCategoriesInTopBlockInSidebar() {
        topChildCategoryBlockInSidebar().shouldBe(visible);
        List<String> titleOfCategoriesForCheck = new ArrayList<>(titleOfChildCategoriesForCheck());
        List<String> titleOfCategoriesFromSideBar = new ArrayList<>(getTitleOfChildCategoriesLinksInSideBar(linksOfChildCategoriesOfTopChildBlockInSidebar()));
        checkOfPresenceCategoriesInChildCategoryBlockInSidebar(titleOfCategoriesForCheck, titleOfCategoriesFromSideBar);
        return this;
    }

    @Step("title of Child categories links in top Child Category block in sidebar for check .LKW_Category_page")
    public List<String> titleOfChildCategoriesForCheck() {
        List<String> titleOfCheckCategories = new ArrayList<>();
        titleOfCheckCategories.add("Kraftstofffilter");
        titleOfCheckCategories.add("Luftfilter");
        titleOfCheckCategories.add("Innenraumluftfilter");
        titleOfCheckCategories.add("Filter-Satz");
        titleOfCheckCategories.add("Lenkgetriebehydraulikfilter");
        titleOfCheckCategories.add("Getriebehydraulikfilter");
        titleOfCheckCategories.add("Kühlmittelfilter");
        return titleOfCheckCategories;
    }

    @Step("get title of Child categories links in Top Child category block in sideBar .LKW_Category_page")
    public List<String> getTitleOfChildCategoriesLinksInSideBar(ElementsCollection listOfChildCategoriesLinks) {
        List<String> titleOfChildCategoriesLinksInSideBar = new ArrayList<>();
        for (int i = 0; i < listOfChildCategoriesLinks.size(); i++) {
            titleOfChildCategoriesLinksInSideBar.add(listOfChildCategoriesLinks.get(i).getText());
        }
        return titleOfChildCategoriesLinksInSideBar;
    }

    @Step("check of presence categories in Top child category block in sidebar .LKW_Category_page")
    public LKW_Category_page_Logic checkOfPresenceCategoriesInChildCategoryBlockInSidebar(List<String> listOfExpectedCategories, List<String> listOfActualCategories) {
        Assert.assertTrue(listOfExpectedCategories.containsAll(listOfActualCategories));
        return this;
    }


    @Step("click on brand .LKW_Category_page")
    public LKW_Category_brand_page_Logic clickOnBrand(String titleOfBrand) {
        titleOfBrand(titleOfBrand).click();
        return page(LKW_Category_brand_page_Logic.class);
    }

    @Step("Select truck in vertical selector .LKW_Category_page")
    public LKW_Category_car_list_page_Logic selectTruckInSelector(String markeOfTruck, String modelOfTruck, String motorOfTruck) {
        markeOfVerticalTruckSelector().selectOptionByValue(markeOfTruck);
        modelOfVerticalTruckSelector().selectOptionByValue(modelOfTruck);
        motorOfVerticalTruckSelector().selectOptionByValue(motorOfTruck);
        buttonSuchenOfVerticaltruckSelector().click();
        return page(LKW_Category_car_list_page_Logic.class);
    }


    @Step("availability of headline in TOP block brands .LKW_Category_page")
    public LKW_Category_page_Logic availabilityOfHeadlineOfTopBlockBrands() {
        headlineOfTopBlockBrands().shouldBe(visible).shouldHave(text("TOP"));
        return this;
    }


    @Step("availability of  TOP  brands block .LKW_Category_page")
    public LKW_Category_page_Logic availabilityOfTopBrandsBlock() {
        topBrandsBlock().shouldBe(visible);
        return this;
    }


    @Step("visibility of brands in TOP block .LKW_Category_page")
    public LKW_Category_page_Logic visibilityOfBrandsInTopBlock() {
        brandsInTopBrandsBlock().shouldHaveSize(6);
        for (int i = 0; i < brandsInTopBrandsBlock().size(); i++) {
            int imageWidth = imageOfBrandsInTopBrandsBlock().get(i).getSize().getWidth();
            int imageHeight = imageOfBrandsInTopBrandsBlock().get(i).getSize().getHeight();
            Assert.assertEquals(imageWidth, 100);
            Assert.assertEquals(imageHeight, 100);
        }
        return this;
    }

    @Step("check transition at icon of truck brands in TOP brands block .LKW_Category_page")
    public LKW_Category_maker_Logic checkTransitionAtIconOfTruckBrand() {
        brandOfTruckInTopBlock().shouldBe(visible).click();
        return page(LKW_Category_maker_Logic.class);
    }


    @Step("click at link More for opening brands block .LKW_Category_page")
    public LKW_Category_page_Logic openOfBrandsBlock() {
        linkMoreOfTopBrandBlock().click();
        return this;
    }


    @Step("visibility of brands block in open condition .LKW_Category_page")
    public LKW_Category_page_Logic brandBlockInOpenCondition() {
        brandsOfTruckInBlock().shouldHave(sizeGreaterThanOrEqual(24));
        return this;
    }

    @Step("close of truck brands block .LKW_Category_page")
    public LKW_Category_page_Logic closeOfBrandsBlock() {
        linkLessOfTopBrandBlock().scrollTo().click();
        brandsOfTruckInBlock().shouldHaveSize(6);
        return this;
    }

    @Step("availability of popular model list in first row of TOP brands block .LKW_Category_page")
    public LKW_Category_page_Logic availabilityOfPopularModelList() {
        for (int i = 1; i <= firstRowOfBrands().size(); i++) {
            linksOfPopularModelList(i).shouldHave(sizeGreaterThan(1));
            for (int j = 0; j < linksOfPopularModelList(i).size(); j++) {
                linksOfPopularModelList(i).get(j).shouldHave(attribute("href"));
            }
        }
        return this;
    }

    @Step("select popular model from TOP brands block .LKW_Category_page")
    public LKW_Category_car_list_page_Logic selectPopularModel(int positionOfBrand, int positionOfModel) {
        linksOfPopularModelList(positionOfBrand).get(positionOfModel).click();
        return page(LKW_Category_car_list_page_Logic.class);
    }

    @Step("visibility of advantage block .LKW_Category_page")
    public LKW_Category_page_Logic visibilityOfAdvantageBlock() {
        advantageBlock().shouldBe(visible);
        return this;
    }

    @Step("presence of headline and two advantages in Advantage block .LKW_Category_page")
    public LKW_Category_page_Logic presenceOfHeadlineAndAdvantagesInBlock() {
        headlineOfAdvantageBlock().shouldBe(visible);
        advantagesInBlock().shouldHaveSize(2);
        return this;
    }


    @Step("appearance of tooltip when you hover on advantage .LKW_Category_page")
    public LKW_Category_page_Logic visibilityOfTooltips() {
        for (int i = 0; i < imageOfAdvantageInBlock().size(); i++) {
            imageOfAdvantageInBlock().get(i).hover();
            tooltipOfAdvantage().get(i).should(appear);
        }
        return this;
    }

    @Step("visibility of main elements in advantage block .LKW_Category_page")
    public LKW_Category_page_Logic visibilityOfElementsInAdvantageBlock() {
        for (int i = 0; i < imageOfAdvantageInBlock().size(); i++) {
            imageOfAdvantageInBlock().get(i).shouldBe(visible);
            titleOfAdvantageInBlock().get(i).shouldBe(visible);
        }
        return this;
    }


    @Step("visibility of main image in advantage block .LKW_Category_page")
    public LKW_Category_page_Logic visibilityOfMainImageInAdvantageBlock() {
        imageOfChildCategory().shouldBe(visible);
        int widthOfChildImage = imageOfChildCategory().getSize().getWidth();
        int heightOfChildImage = imageOfChildCategory().getSize().getHeight();
        Assert.assertEquals(widthOfChildImage, 200);
        Assert.assertEquals(heightOfChildImage, 200);
        return this;
    }


    @Step("visibility of headline of TOP products block .LKW_Category_page")
    public LKW_Category_page_Logic visibilityOfHeadlineOfTopProductsBlock() {
        headlineOfTopProductsBlock().shouldBe(visible);
        return this;
    }

    @Step("availability of top block and top products .LKW_Category_page")
    public LKW_Category_page_Logic availabilityOfTopProductsBlock() {
        topBLock().shouldBe(visible);
        productsOfTopBlock().shouldHaveSize(8);
        return this;
    }

    @Step("get id of product in Top products block .LKW_Category_page")
    public String getIdOfTopProduct() {
        productsOfTopBlock().get(0).hover();
        btnAddToBasketTopBLock(1).should(appear);
        String idOfProduct = btnAddToBasketTopBLock(1).getAttribute("id");
        return idOfProduct;
    }

    @Step("added top product to basket .LKW_Category_page")
    public Cart_page_Logic addTopProductToBasket() {
        btnAddToBasketTopBLock(1).shouldBe(visible).click();
        basketDropMenu().should(appear);
        basketDropMenu().should(disappear);
        basket().click();
        return page(Cart_page_Logic.class);
    }

    @Step("visibility of addition information when hover on the product in Top block .LKW_Category_page")
    public LKW_Category_page_Logic visibilityOfAdditionInfoInTopBlock() {
        for (int i = 0; i < productsOfTopBlock().size(); i++) {
            productsOfTopBlock().get(i).hover();
            additionInfoBlockOfTopProduct().get(i).should(appear);
            blockBefore().hover();
        }
        return this;
    }


    @Step("ratio of product's generic to category .LKW_Category_page")
    public LKW_Category_page_Logic ratioOfProductsGenericToCategory() {
        for (int i = 0; i < titleOfTopProduct().size(); i++) {
            titleOfTopProduct().get(i).shouldHave(text("Ölfilter"));
        }
        return this;
    }


    @Step("transition to product page by click on top image of product .LKW_Category_page")
    public LKW_Category_page_Logic transitionToProductPageByClickOnTopImage() {
        clickOnImageOfTopProduct().checkSuccessfullyLKWProductPageLoading("autodoc.de/mann-filter/");
        back();
        return this;
    }

    @Step("click on image of top product .LKW_Category_page")
    public LKW_Product_page_Logic clickOnImageOfTopProduct() {
        imageOfTopProduct().get(0).click();
        return page(LKW_Product_page_Logic.class);
    }

    @Step("transition to product page by click on title of top product .LKW_Category_page")
    public LKW_Category_page_Logic transitionToProductPageByClickOnTitleOfTopProduct() {
        if (closeCookiesPopUp().isDisplayed()) {
            closeCookiesPopUp().click();
        }
        clickOnTitleOfTopProduct().checkSuccessfullyLKWProductPageLoading("autodoc.de/mann-filter/");
        back();
        return this;
    }

    @Step("click on title of top product .LKW_Category_page")
    public LKW_Product_page_Logic clickOnTitleOfTopProduct() {
        titleOfTopProduct().get(0).click();
        return page(LKW_Product_page_Logic.class);
    }


    @Step("transition to product page by click on link Details .LKW_Category_page")
    public LKW_Category_page_Logic transitionToProductPageByClickOnLinkDetails() {
        if (closeCookiesPopUp().isDisplayed()) {
            closeCookiesPopUp().click();
        }
        clickOnLinkDetails().checkSuccessfullyLKWProductPageLoading("autodoc.de/mann-filter/");
        back();
        return this;
    }

    @Step("click on Details link .LKW_Category_page")
    public LKW_Product_page_Logic clickOnLinkDetails() {
        imageOfTopProduct().get(0).hover();
        additionInfoBlockOfTopProduct().get(0).should(appear);
        linkDetails().get(0).shouldBe(visible).click();
        return page(LKW_Product_page_Logic.class);
    }

    @Step("comparison of product and generic .LKW_Category_page")
    public LKW_Category_page_Logic comparisonOfProductAndGeneric(String titleOfGeneric) {
        for (int i = 0; i < titleOfTopProduct().size(); i++) {
            titleOfTopProduct().get(i).shouldHave(text(titleOfGeneric));
        }
        return this;
    }

    @Step("presence child categories block in sidebar. LKW_Category_page")
    public LKW_Category_page_Logic presenceChildCategoriesBlockInSidebar() {
        topChildCategoryBlockInSidebar().shouldBe(visible);
        return this;
    }

    @Step("click On child category .LKW_Category_page")
    public LKW_Category_page_Logic clickOnChildCategory(int positionOfChildCategory) {
        linksOfChildCategoriesOfTopChildBlockInSidebar().get(positionOfChildCategory).scrollIntoView("{block: \"center\"}").click();
        return this;
    }


    @Step("presence of TOP products block .LKW_Category_page")
    public LKW_Category_page_Logic presenceOfTopProductsBlock() {
        topProductsBlock().shouldBe(visible);
        return this;
    }

    @Step("get signal word from first dangerous product .LKW_Category_page")
    public String getSignalWordFromFirstDangerousProduct(int positionOfProduct) {
        return getTextFromUnVisibleElement(signalWordOfDangerousProduct().get(positionOfProduct));
    }


    @Step("get id of Dangerous product .LKW_Category_page")
    public String getIdOfDangerousProduct(int positionOfProduct) {
        return btnAddDangerousProductToWishList().get(positionOfProduct).getAttribute("data-product-id");
    }

    @Step("get attribute of Warning icon in pop-Up .LKW_Category_page")
    public List<String> getAttributeOfWarningIconInPopUp(int positionOfProduct) {
        List<String> attribute = new ArrayList<>();
        dangerousProducts().get(positionOfProduct).scrollIntoView("{block: \"center\"}").hover();
        for (int i = 0; i < attributeOfWarningIcon(positionOfProduct + 1).size(); i++) {
            String attributeFromIcon = attributeOfWarningIcon(positionOfProduct + 1).get(i).shouldBe(visible).getAttribute("style").replace("background-image: url(\"", "").replace("\");", "");
            String partOfAttribute = attributeFromIcon.replace(attributeFromIcon.substring(attributeFromIcon.lastIndexOf(".")), "");
            attribute.add(partOfAttribute);
        }
        return attribute;
    }

    @Step("click on dangerous label of product and compare elements. LKW_Category_page")
    public LKW_Category_page_Logic clickOnDangerousLabelAndCompareElements(int positionOfProduct, String signalWord, List<String> attributeOfWarningIcon) {
        if (!labelTitleDangerousProducts().get(positionOfProduct).isDisplayed()) {
            dangerousProducts().get(positionOfProduct).scrollIntoView("{block: \"center\"}").hover();
        } else {
            labelTitleDangerousProducts().get(positionOfProduct).shouldBe(visible).click();
            blackBackground().shouldHave(attribute("style", "display: block;"));
            warningPopUp().shouldBe(visible).shouldHave(attribute("style", "display: block;"));
            titleOfDangerousPopUp().shouldBe(visible).shouldHave(exactText(signalWord));
            infoTextOfDangerousPopUp().shouldNotBe(empty);
            List<String> attributeOfDangerousIcon = new ArrayList<>();
            for (int i = 0; i < dangerousIconInWarningPopUp().size(); i++) {
                String urlFromAttribute = dangerousIconInWarningPopUp().get(i).getAttribute("style").replace("background-image: url(\"", "").replace("\");", "");
                String partOfAttribute = urlFromAttribute.replace(urlFromAttribute.substring(urlFromAttribute.lastIndexOf(".")), "");
                attributeOfDangerousIcon.add(partOfAttribute);
            }
            Assert.assertEquals(attributeOfDangerousIcon, attributeOfWarningIcon);
        }
        return this;
    }


    @Step("get url of TOP product.LKW_Category_page")
    public String getUrlOfTopProduct(int positionOfProduct) {
        return urlOfTopProduct().get(positionOfProduct).attr("href");
    }


    @Step("transition to product page by click on top image of product .LKW_Category_page")
    public LKW_Category_page_Logic transitionToProductPageByClickOnTopImage(String url) {
        clickOnImageOfTopProduct();
        checkingContainsUrl(url);
        back();
        return this;
    }


    @Step("transition to product page by click on title of top product .LKW_Category_page")
    public LKW_Category_page_Logic transitionToProductPageByClickOnTitleOfTopProduct(String url) {
        if (closeCookiesPopUp().isDisplayed()) {
            closeCookiesPopUp().click();
        }
        clickOnTitleOfTopProduct();
        checkingContainsUrl(url);
        back();
        return this;
    }


    @Step("transition to product page by click on link Details .LKW_Category_page")
    public LKW_Category_page_Logic transitionToProductPageByClickOnLinkDetails(String url) {
        if (closeCookiesPopUp().isDisplayed()) {
            closeCookiesPopUp().click();
        }
        clickOnLinkDetails();
        checkingContainsUrl(url);
        back();
        return this;
    }
}
