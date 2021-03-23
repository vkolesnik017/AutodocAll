package PKW;

import com.codeborne.selenide.ElementsCollection;
import io.qameta.allure.Step;
import org.testng.Assert;

import static com.codeborne.selenide.CollectionCondition.sizeGreaterThan;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Condition.or;
import static com.codeborne.selenide.Selenide.back;
import static com.codeborne.selenide.Selenide.page;


public class Listing_page_Logic extends Listing_page {

    @Step("Method checks that expected text is present in title of all products on listing. Listing_page")
    public Listing_page_Logic checkProductTitleOnListing(String expectedTextInTitle, Boolean shouldHaveTextOrNotHave, ElementsCollection titleViewMode) {
        titleViewMode.shouldHave(sizeGreaterThan(0));
        for (int i = 0; i < titleViewMode.size(); i++) {
            if (shouldHaveTextOrNotHave) {
                titleViewMode.get(i).shouldHave(text(expectedTextInTitle));
            } else {
                titleViewMode.get(i).shouldNotHave(text(expectedTextInTitle));
            }
        }
        return this;
    }

    @Step("Method checks that expected text is present in title of all products on listing with two conditions. Listing_page")
    public Listing_page_Logic checkProductTitleOnListingWithTwoExpectedTexts(String expectedTextInTitle, String secondExpText, Boolean shouldHaveTextOrNotHave, ElementsCollection titleViewMode) {
        titleViewMode.shouldHave(sizeGreaterThan(0));
        for (int i = 0; i < titleViewMode.size(); i++) {
            if (shouldHaveTextOrNotHave) {
                titleViewMode.get(i).shouldHave(or("condition", text(expectedTextInTitle), text(secondExpText)));
            } else {
                titleViewMode.get(i).shouldNotHave(or("condition", text(expectedTextInTitle), text(secondExpText)));
            }
        }
        return this;
    }

    @Step("Wait until preloader disappear. Listing_page")
    public Listing_page_Logic waitUntilPreloaderDisappear() {
        preloader().waitUntil(attribute("style", "display: none;"), 20000);
        return this;
    }

    @Step("Click first product on listing. Listing_page")
    public PKW.Product_page_Logic clickProductOnListing() {
        firstProductTitleOnListing().click();
        return page(Product_page_Logic.class);
    }

    @Step("Check listing with selecting brand. Listing_page")
    public Listing_page_Logic checkListingWithSelectingFilterByBrand(String brandId, String brandName) {
        moreBtnInBrandBloc().click();
        brandsOfBrandBlock(brandId).hover().click();
        waitUntilPreloaderDisappear();
        for (int i = 0; i < titleOfProductInListing().size(); i++) {
            String name = titleOfProductInListing().get(i).getText();
            String actualName = name.replace(name.substring(name.indexOf(" ")), "");
            Assert.assertEquals(brandName, actualName);
        }
        return this;
    }

    @Step("Checks the name of the feature state {expectedCharacteristicName} if the article number matches the expected one {expectedArticleNum}. Listing_page")
    public Listing_page_Logic checkNameOfFeatureStateIfArticleNumMatchesExpectedOne(String expectedArticleNum, String expectedCharacteristicName, String nameCharacteristic) {
        for (int i = 0; i < productArticlesInListing().size(); i++) {
            String articleNum = productArticlesInListing().get(i).getText().replaceAll("Art. Nr.: ", "");
            if (expectedArticleNum.equals(articleNum)) {
                if (moreBtnInProduct().isDisplayed()) {
                    moreBtnInProduct().click();
                }
                String characteristicName = nameProductCharacteristicINParametersBlock(nameCharacteristic).getText();
                Assert.assertEquals(expectedCharacteristicName, characteristicName);
            }
        }
        return this;
    }

    @Step("Check absence expected article num {expectedArtNum}. Listing_page")
    public Listing_page_Logic checkAbsenceArticleNum(String expectedArtNum) {
        for (int i = 0; i < productArticlesInListing().size(); i++) {
            productArticlesInListing().get(i).scrollIntoView(("{block: \"center\"}")).shouldNot(text(expectedArtNum));
        }
        return this;
    }

    @Step("Goes to the product page and checks that the name of the characteristic 'Zustand' feature is as expected {expectedCharacteristicName}. Listing_page")
    public Listing_page_Logic goToProductPageAndCheckThatNameOfCharacteristicFeatureIsExpected(String expectedArticleNum,String expectedCharacteristicName, String nameCharacteristic) {
        for (int i = 0; i < productArticlesInListing().size(); i++) {
            String articleNum = productArticlesInListing().get(i).getText().replaceAll("Art. Nr.: ", "");
            if (expectedArticleNum.equals(articleNum)) {
                searchPageTitle().scrollTo();
                clickProductOnListing();
                String characteristicName = new Product_page().nameProductCharacteristic(nameCharacteristic).getText();
                Assert.assertEquals(expectedCharacteristicName, characteristicName);
                back();
            }
        }
        return this;
    }
}
