package ATD;

import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.*;

public class Tyres_brand_dimension_page_Logic extends Tyres_brand_dimension_page {

    @Step("appears of Out of stock product pop-up. Tyres_brand_dimension_page")
    public Tyres_brand_dimension_page_Logic appearsOfOutOfStockProductPopUp(String ean) {
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

    @Step("appears of Out of stock product pop-up. Tyres_brand_dimension_page")
    public Tyres_brand_dimension_page_Logic clickOnGrayButtonByEab(String ean) {
        if (grayButtonByEan(ean).isDisplayed()) {
            grayButtonByEan(ean).scrollIntoView("{block: \"center\"}").click();
            popUpNotifyAboutAvailability().should(appear);
        }
        return this;
    }

    @Step("set value in email field of PopUp .Tyres_brand_dimension_page")
    public Tyres_brand_dimension_page_Logic setValueInEmailFieldOfPopUp(String email) {
        emailFieldOfFeedBackPopUp().shouldBe(visible).setValue(email);
        return this;
    }

    @Step("click on 'Get mailing' label of pop-up Notify about availability. Tyres_brand_dimension_page")
    public Tyres_brand_dimension_page_Logic clickOnGetMailingLabel() {
        labelOfPopUpNotifyAboutAvailability().shouldBe(visible).click();
        return this;
    }

    @Step("click on Subscription button . Tyres_brand_dimension_page")
    public Tyres_brand_dimension_page_Logic clickOnBtnSubscription() {
        btnSendOfFeedBackPopUp().click();
        return this;
    }

    @Step("close out of stock product pop-Up . Tyres_brand_dimension_page")
    public Tyres_brand_dimension_page_Logic closeOutOfStockProductPopUp() {
        btnCloseOutOfStockProductPopUp().shouldBe(visible).click();
        btnCloseOutOfStockProductPopUp().shouldNotBe(visible);
        return this;
    }

    @Step("presence of all TOP tire size links  . Tyres_brand_dimension_page")
    public Tyres_brand_dimension_page_Logic presenceOfAllTopTireSizeLinks() {
        for (int i = 0; i < visibleTopTireSizeLinks().size(); i++) {
            visibleTopTireSizeLinks().get(i).shouldBe(visible);
        }
        return this;
    }
}
