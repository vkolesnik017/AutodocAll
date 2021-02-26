package ATD;

import PKW.Index_accessories_group_page_Logic;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.testng.Assert;

import java.util.ArrayList;
import java.util.Collections;

import static Common.CommonMethods.checkingContainsUrl;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;

public class Listing_accessories_page_Logic extends Listing_accessories_page {

    @Step("Get title name on listing. Listing_accessories_page")
    public String getTitleName() {
        return listingTitle().getText();
    }

    @Step("Checking that selected brand become not active after clicking on it. Listing_accessories_page")
    public Listing_accessories_page_Logic checkResetSelectedFilterByBrand() {
        selectedBrand().shouldBe(visible).click();
        new Listing_page_Logic().waitUntilPreloaderDisappear();
        selectedBrand().shouldNotBe(visible);
        return this;
    }

    @Step("Checking that selected brand become not active after clicking on it for one brand. Listing_accessories_page")
    public Listing_accessories_page_Logic clickFirstActiveBrand() {
        selectedBrand().shouldBe(visible).click();
        new Listing_page_Logic().waitUntilPreloaderDisappear();
        return this;
    }

    @Step("Checking presence title categories block in Sidebar. Listing_accessories_page")
    public Listing_accessories_page_Logic checkingPresenceTitleCategoriesBlockInSidebar() {
        titleCategoriesBlockInSidebar().shouldBe(visible);
        return this;
    }

    @Step("Checking presence title name on listing page.  Listing_accessories_page ")
    public Listing_accessories_page_Logic checkingPresenceTitleName() {
        listingTitle().shouldBe(visible);
        return this;
    }

    @Step("Get name first category in Sidebar. Listing_accessories_page")
    public String getNameFirstCategoryInSidebar() {
        return firstCategoryInSidebar().getText();
    }

    @Step("Click on first category in Sidebar.Listing_accessories_page")
    public Listing_accessories_page_Logic clickOnFirstCategoryInSidebar() {
        firstCategoryInSidebar().click();
        return this;
    }

    @Step("Checking presence quantity products block. Listing_accessories_page")
    public Listing_accessories_page_Logic checkingPresenceQuantityProductBlock() {
        blockProductQuantity().shouldBe(visible);
        return this;
    }

    @Step("Checking change display of products on the list view and then grid. Listing_accessories_page")
    public Listing_accessories_page_Logic checkingChangeDisplayProductsAsListAndGrid() {
        blockChangePositionProductsOnListOrGrid().shouldBe(visible);
        listingProductsDisplayedAsList().shouldBe(visible);
        btnChangePositionProductsAsGrid().click();
        listingProductsDisplayedAsGrid().shouldBe(visible);
        return this;
    }

    @Step("Get name all categories from sidebar and title name page. Listing_accessories_page")
    public ArrayList<String> getNameAllCategoriesInSidebarAndTitleNamePage() {
        ArrayList<String> nameCategories = new ArrayList<>();
        titleCategoriesBlockInSidebar().shouldBe(visible).scrollIntoView(true);
        for (SelenideElement element : categoriesInSidebar()) {
            String name = element.getText();
            nameCategories.add(name);
        }
        String nameTitle = listingTitle().getText();
        if (nameCategories.contains(nameTitle)) Assert.fail(nameTitle.concat(" visible in sidebar. "));
        nameCategories.add(nameTitle);
        Collections.sort(nameCategories);
        return nameCategories;
    }

    @Step("Checking presence of the Breadcrumbs. Listing_accessories_page")
    public Listing_accessories_page_Logic checkingPresenceOfTheBreadcrumbs() {
        breadcrumbsBlock().shouldBe(visible);
        return this;
    }

    @Step("Checking presence and not clickable last Bread Crumb. Listing_accessories_page")
    public Listing_accessories_page_Logic checkingPresenceAndNotClickableLastBreadCrumb(SelenideElement lastBreadCrumb) {
        lastBreadCrumb.shouldBe(visible).shouldNotBe(attribute("href"));
        return this;
    }

    @Step("Click on fourth Bread Crumb. Listing_accessories_page")
    public Listing_accessories_page_Logic clickFourthBreadCrumb() {
        fourthBreadCrumb().click();
        return this;
    }

    @Step("Click on third Bread Crumb. Listing_accessories_page")
    public Index_accessories_group_page_Logic clickThirdBreadCrumb() {
        thirdBreadCrumb().click();
        return page(Index_accessories_group_page_Logic.class);
    }

    @Step("Click on second Bread Crumb. Listing_accessories_page")
    public Index_accessories_page_Logic clickSecondBreadCrumb() {
        secondBreadCrumb().click();
        return page(Index_accessories_page_Logic.class);
    }

    @Step("Click on first Bread Crumb. Listing_accessories_page")
    public Categories_page clickFirstBreadCrumb() {
        firstBreadCrumb().click();
        return page(Categories_page_Logic.class);
    }

    @Step("Checking quantity bread crumbs. Listing_accessories_page")
    public Listing_accessories_page_Logic checkingQuantityBreadCrumbs() {
        breadCrumbs().shouldHaveSize(3);
        return this;
    }

    @Step("Click on first brand in block brands. Listing_accessories_page")
    public Listing_accessories_page_Logic clickOnFirstBrand() {
        firstVisibleBrand().click();
        return this;
    }

    @Step("Click on second brand in block brands. Listing_accessories_page")
    public Listing_accessories_page_Logic clickOnSecondBrand() {
        secondVisibleBrand().click();
        return this;
    }

    @Step("Click on seven brands in block brands. Listing_accessories_page")
    public Listing_accessories_page_Logic clickOnSevenBrand() {
        sevenVisibleBrand().click();
        return this;
    }

    @Step("Get name first brand from brands block. Listing_accessories_page")
    public String getNameFromFirstBrand() {
        return firstVisibleBrandImg().getAttribute("alt");
    }

    @Step("Get name second brand from brands block. Listing_accessories_page")
    public String getNameFromSecondBrand() {
        return secondVisibleBrandImg().getAttribute("alt");
    }

    @Step("Checking sorting of Products with one brand selected then with two. Listing_accessories_page")
    public Listing_accessories_page_Logic checksSortingProductsWithOneBrandThenWithTwo() {
        Listing_page_Logic listingPageLogic = new Listing_page_Logic();
        String firstBrandName = getNameFromFirstBrand().replace(getNameFromFirstBrand().substring(getNameFromFirstBrand().lastIndexOf(" ")), "");
        String secondBrandName = getNameFromSecondBrand().replace(getNameFromSecondBrand().substring(getNameFromSecondBrand().lastIndexOf(" ")), "");
        clickOnFirstBrand();
        listingPageLogic.waitUntilPreloaderDisappear();
        listingPageLogic.checkProductTitleOnListing(firstBrandName, true, titleNameProductsFromListing());
        clickOnSecondBrand();
        listingPageLogic.waitUntilPreloaderDisappear();
        listingPageLogic.checkProductTitleOnListingWithTwoExpectedTexts(firstBrandName, secondBrandName, true, titleNameProductsFromListing());
        return this;
    }

    @Step("Checking work of the buttons Previous and Next in the brand block. Listing_accessories_page")
    public Listing_accessories_page_Logic checksWorkButtonsPrevAndNextInBlockBrands() {
        secondVisibleBrand().shouldHave(attribute("aria-hidden", "false"));
        btnPrevInBlockBrands().click();
        sleep(2000);
        secondVisibleBrand().shouldHave(attribute("aria-hidden", "true"));
        btnNextInBlockBrands().click();
        sleep(2000);
        secondVisibleBrand().shouldHave(attribute("aria-hidden", "false"));
        return this;
    }

    @Step("Get value first visible brand in block brands. Listing_accessories_page")
    public String getValueFirstVisibleBrandInBlockBrands() {
        return firstVisibleBrandToGetData().getAttribute("value");
    }

    @Step("Get value second visible brand in block brands. Listing_accessories_page")
    public String getValueSecondVisibleBrandInBlockBrands() {
        return secondVisibleBrandToGetData().getAttribute("value");
    }

    @Step("Get value seven visible brand in block brands. Listing_accessories_page")
    public String getValueSevenVisibleBrandInBlockBrands() {
        return sevenVisibleBrandToGetData().getAttribute("value");
    }

    @Step("Checking that Selected brands displayed active and entered at top of list after they selected. Listing_accessories_page")
    public Listing_accessories_page_Logic checkingLocationAndActivityBrandsAfterTheySelected() {
        String secondActiveBrand = getValueSecondVisibleBrandInBlockBrands();
        String sevenActiveBrand = getValueSevenVisibleBrandInBlockBrands();
        clickOnSecondBrand();
        new Listing_page_Logic().waitUntilPreloaderDisappear();
        String firstSelectedBrand = getValueFirstVisibleBrandInBlockBrands();
        Assert.assertEquals(secondActiveBrand, firstSelectedBrand);
        clickOnSevenBrand();
        new Listing_page_Logic().waitUntilPreloaderDisappear();
        ArrayList<String> twoFirstBrandsFromBegin = new ArrayList<>();
        String firstCheckedBrand = getValueFirstVisibleBrandInBlockBrands();
        String secondCheckedBrand = getValueSecondVisibleBrandInBlockBrands();
        twoFirstBrandsFromBegin.add(firstCheckedBrand);
        twoFirstBrandsFromBegin.add(secondCheckedBrand);
        if (!twoFirstBrandsFromBegin.contains(secondActiveBrand) && twoFirstBrandsFromBegin.contains(sevenActiveBrand)) {
            Assert.fail(" Two first brands does not match selected brands");
        }
        firstVisibleBrandToGetData().shouldHave(attribute("data-checked", "true"));
        secondVisibleBrandToGetData().shouldHave(attribute("data-checked", "true"));
        return this;
    }


    @Step(" Checking work buttons in pagination. Listing_accessories_page")
    public Listing_accessories_page_Logic checkingWorkBtnInPagination() {
        blockPagination().shouldBe(visible);
        btnPreviousPageInPagination().shouldBe(visible);
        btnNextPageInPagination().shouldBe(visible);
        btnLastPageInPagination().shouldBe(visible);
        btnSecondPageInPagination().click();
        btnReturnOnFirstPageInPagination().shouldBe(visible);
        btnPreviousPageInPagination().shouldBe(visible);
        btnNextPageInPagination().shouldBe(visible);
        btnLastPageInPagination().shouldBe(visible);
        checkingContainsUrl("page=2");
        btnNextPageInPagination().click();
        checkingContainsUrl("page=3");
        btnReturnOnFirstPageInPagination().click();
        return this;
    }

    @Step("Get id product listing. Listing_accessories_page")
    public String getIdProductListing() {
        return idProductInBtnAddBasket().getAttribute("id");
    }

    @Step(":from Listing_accessories_page")
    public Listing_accessories_page_Logic increasesNumberProductsInQuantityCounter() {
        new CommonMethods().checkingCounterIncrease(2, counterValueInQuantityCounter(), btnPlusInQuantityCounter());
        return this;
    }

    @Step("Get value quantity counter from first product listing. Listing_accessories_page")
    public String getValueQuantityCounterFirstProductListing() {
        return counterValueInQuantityCounter().getValue();
    }

    @Step("Click button add to basket first product. Listing_accessories_page")
    public Listing_accessories_page_Logic clickBtnAddToBasketFirstProduct() {
        redBtnAddToBasket().click();
        popupBasketAddedProducts().waitUntil(attribute("style", "visibility: visible; opacity: 1;"), 10000);
        return this;
    }

    @Step(":from Listing_accessories_page")
    public Cart_page_Logic cartClick() {
        new Main_page_Logic().cartClick();
        return page(Cart_page_Logic.class);
    }

    @Step("Checking presence products block and they quantity. Listing_accessories_page")
    public Listing_accessories_page_Logic checkingBlockAndQuantityMainProducts() {
        listingProductsDisplayedAsList().shouldBe(appear);
        mainProductsList().shouldHaveSize(20);
        return this;
    }

    @Step("Checking work quantity counter on decrease and increase products. Listing_accessories_page")
    public Listing_accessories_page_Logic checkingWorkQuantityCounterOnDecreaseAndIncrease() {
        counterValueInQuantityCounter().scrollIntoView("{block: \"center\"}");
        new CommonMethods().checkingCounterIncrease(3, counterValueInQuantityCounter(), btnPlusInQuantityCounter());
        new CommonMethods().checkingCounterDecrease(3, counterValueInQuantityCounter(), btnMinusInQuantityCounter());
        btnMinusInQuantityCounter().click();
        counterValueInQuantityCounter().shouldHave(attribute("value", "1"));
        return this;
    }

    @Step("Click on title name main product. Listing_accessories_page")
    public Product_page_Logic clickOnTitleNameMainProduct() {
        titleNameProductFromListing().click();
        return page(Product_page_Logic.class);
    }

    @Step("Click logo Autodoc . Listing_accessories_page")
    public Main_page_Logic clickLogoAutodoc() {
        logoAutodoc().shouldBe(visible).click();
        return page(Main_page_Logic.class);
    }
}