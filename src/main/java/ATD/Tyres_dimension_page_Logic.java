package ATD;

import io.qameta.allure.Step;
import org.testng.Assert;

import java.util.Arrays;
import java.util.List;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.page;

public class Tyres_dimension_page_Logic extends Tyres_dimension_page {

    @Step("Displaying customer feedback PopUp .Tyres_dimension_page")
    public Tyres_dimension_page_Logic displayingCustomerFeedbackPopUp() {
        productsList().shouldBe(visible);
        btnOutOfStock().get(0).click();
        feedBackPopUp().should(appear);
        return this;
    }

    @Step("displaying of popUp pop-up error about invalid email when trying to send mail for feedback with selected checkbox. Tyres_dimension_page")
    public Tyres_dimension_page_Logic displayingOfPopUPAboutInvalidEmailAndWithCheckbox(String email) {
        feedBackPopUp().shouldBe(visible);
        setValueInEmailFieldOfPopUp(email);
        clickOnBtnSubscription();
        popUPAboutInvalidEmail().should(appear).shouldHave(text("Das Feld E-mail muss eine gültige E-Mail-Adresse enthalten."));
        return this;
    }

    @Step("set value in email field of PopUp .Tyres_dimension_page")
    public Tyres_dimension_page_Logic setValueInEmailFieldOfPopUp(String email) {
        emailFieldOfFeedBackPopUp().shouldBe(visible).setValue(email);
        return this;
    }

    @Step("click on 'Get mailing' label of pop-up Notify about availability.Tyres_dimension_page")
    public Tyres_dimension_page_Logic clickOnGetMailingLabel() {
        labelOfPopUpNotifyAboutAvailability().shouldBe(visible).click();
        return this;
    }

    @Step("click on Subscription button .Tyres_dimension_page")
    public Tyres_dimension_page_Logic clickOnBtnSubscription() {
        btnSendOfFeedBackPopUp().click();
        return this;
    }


    @Step("close pop-up about sent letter .Tyres_dimension_page")
    public Tyres_dimension_page_Logic closePopUpAboutSentLetter() {
        btnCloseSentLetterPopUp().shouldBe(visible).click();
        return this;
    }
    @Step("presence of product listing block. Tyres_dimension_page")
    public Tyres_dimension_page_Logic presenceOfListingBlock() {
        productListBlock().shouldBe(visible);
        return this;
    }

    @Step("get MPN number of product. Tyres_dimension_page")
    public String getMpnNumberOfProduct(int positionOfProduct) {
        String mpnNumber = mpnNumberOfProduct().get(positionOfProduct).getText().replace("MPN: ", "");
        return mpnNumber;
    }

    @Step("added product to Wishlist. Tyres_dimension_page")
    public Tyres_dimension_page_Logic addProductToWishList(int positionOfProduct) {
        btnAddProductToWishList().get(positionOfProduct).shouldBe(visible).click();
        addedProductToWishList().get(0).shouldBe(exist);
        return this;
    }

    @Step("go to WishList page. Tyres_dimension_page")
    public Services_wishList_page_Logic goToWishListPage() {
        iconOfWishList().shouldBe(visible).click();
        return page(Services_wishList_page_Logic.class);
    }

    @Step("select from any visible brands. Tyres_dimension_page")
    public Tyres_dimension_page_Logic selectAnyBrand(int positionOfBrand) {
        visibleBrands().get(positionOfBrand).click();
        anySelectedBrand().shouldBe(visible);
        return this;
    }

    @Step("check maximize count of product in listing. Tyres_dimension_page")
    public Tyres_dimension_page_Logic checkMaxCountOfProductInListing(int maxSize) {
        List<String> list = Arrays.asList(countOfProductsInPage().getText().replace("Aktuell ", "").split(" "));
        Assert.assertTrue(Integer.parseInt(list.get(0)) <= maxSize);
        return this;
    }

    @Step("check maximize count of pages in listing. Tyres_dimension_page")
    public Tyres_dimension_page_Logic checkMaxCountOfPagesInListing() {
        String lastPageOfListing = btnLastPaginator().getAttribute("href");
        String urlPart = lastPageOfListing.replace(lastPageOfListing.substring(lastPageOfListing.lastIndexOf("=")), "");
        String lastCountOfPage = lastPageOfListing.replace(urlPart + "=", "");
        Assert.assertTrue(Integer.parseInt(lastCountOfPage) <= 35);
        return this;
    }

    @Step("presence addition information filter block. Tyres_dimension_page")
    public Tyres_dimension_page_Logic presenceAdditionInfoFilterBlock() {
        additionInfoFilterBlock().shouldBe(visible);
        return this;
    }

    @Step("select addition filter. Tyres_dimension_page")
    public Tyre_form_page_Logic selectAdditionFilter(int positionOfFilter) {
        additionIfoFilters().get(positionOfFilter).click();
        return page(Tyre_form_page_Logic.class);
    }

    @Step("appears of Out of stock product pop-up. Tyres_dimension_page")
    public Tyres_dimension_page_Logic appearsOfOutOfStockProductPopUp(String ean) {
        productListBlock().shouldBe(visible);
        clickOnGrayButtonByEab(ean);
        String currentMpnNumber;
        while (!grayButtonByEan(ean).isDisplayed()) {
            currentMpnNumber = mpnNumberOfProduct().get(0).getText();
            btnNextPaginator().click();
            mpnNumberOfProduct().get(0).shouldNotHave(exactText(currentMpnNumber));
            clickOnGrayButtonByEab(ean);
        }
        return this;
    }

    @Step("appears of Out of stock product pop-up. Tyres_dimension_page")
    public Tyres_dimension_page_Logic clickOnGrayButtonByEab(String ean) {
        if (grayButtonByEan(ean).isDisplayed()) {
            grayButtonByEan(ean).scrollIntoView("{block: \"center\"}").click();
            popUpNotifyAboutAvailability().should(appear);
        }
        return this;
    }

}
