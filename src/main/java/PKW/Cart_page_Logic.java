package PKW;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.testng.Assert;

import java.util.ArrayList;
import java.util.List;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.page;
import static com.codeborne.selenide.Selenide.sleep;

public class Cart_page_Logic extends Cart_page{

    @Step("Clicking next button. Cart_page")
    public CartAccount_page_Logic nextButtonClick() {
        nextButton().click();
        return page(CartAccount_page_Logic.class);
    }

    @Step("Make price for minimum order for CH. Cart_page")
    public Cart_page makePriceForMinimumOrderForCH(String shop) {
        if(shop.equals("CH")) {
            if (!closeDeliveryLimitPopupForCH().isDisplayed()) {
                sleep(2000);
            }
            if (closeDeliveryLimitPopupForCH().isDisplayed()) {
                closeDeliveryLimitPopupForCH().click();
                while (nextBtnIsNotActiveForCH().isDisplayed()) {
                    counterPlusBtn().click();
                    sleep(500);
                }
            }
        }
        return this;
    }

    @Step("Check that the basket is empty. Cart_page")
    public Cart_page_Logic checkEmptyCart() {
        emptyCart().shouldBe(visible);
        return this;
    }

    @Step("Get name added product in basket. Cart_page")
    public String getNameAddedProductInBasket() {
        return nameProduct().getText();
    }

    @Step("Get value in quantity counter. Cart_page")
    public String getValueQuantityCounter() {
        return valueQuantityCounter().getValue();
    }

    @Step("Get id product listing. Cart_page")
    public String getIdAddedProduct() {
        return idAddedProduct().getAttribute("data-article_id");
    }

    @Step("presence of added product list. Cart_page")
    public Cart_page_Logic presenceOfAddedProductList() {
        addedProductList().shouldBe(visible);
        return this;
    }

    @Step("Get volume of added product . Cart_page")
    public String getVolumeAddedProduct() {
        String volume = volumeOfAddedProduct().getText().replace(volumeOfAddedProduct().getText().substring(volumeOfAddedProduct().getText().lastIndexOf(":")), "");
        String expectedVolume = volumeOfAddedProduct().getText().replace(volume,"").replaceAll("[^0-9]","");
        return expectedVolume;
    }

    @Step("presence of expected characteristic. Cart_page")
    public Cart_page_Logic presenceOfExpectedCharacteristic(String expectedCharacteristic) {
        btnMoreInfoOfProduct().shouldBe(visible).click();
        List<String> valuesOfCharacteristic = new ArrayList<>();
        for (int i = 0; i < titleOfCharacteristicInInfoBlockOfProduct().size(); i++) {
            valuesOfCharacteristic.add(titleOfCharacteristicInInfoBlockOfProduct().get(i).getText() + " " + valueOfCharacteristicInInfoBlockOfProduct().get(i).getText());
        }
        valuesOfCharacteristic.contains(expectedCharacteristic);
        return this;
    }

    @Step("Checks presence Payments Method label. Cart_page")
    public Cart_page_Logic checkPresencePaymentsMethodLabel(SelenideElement ... locator) {
        for (SelenideElement expectedLocator : locator) {
            expectedLocator.shouldBe(visible);
        }
        return this;
    }

    @Step("Checks presence of payment methods label for required country. Cart_page")
    public Cart_page_Logic checksPresenceOfPaymentMethodsLabelForRequiredCountry(String actualShop, String expectedShop, SelenideElement ... locator) {
        for (SelenideElement expectedLocator : locator) {
            if (actualShop.equals(expectedShop)) {
            expectedLocator.shouldBe(visible);
            }
        }
        return this;
    }

   @Step("Checks for the label of the bank payment method. Cart_page")
    public Cart_page_Logic checksForLabelOfBankPaymentMethod() {
        if ((firstLabelBank().isDisplayed()) || secondLabelBank().isDisplayed() || thirdLabelBank().isDisplayed() || fourthLabelBank().isDisplayed()) {
            System.out.println("Label bank is visible");
       } else {
            Assert.fail("Leib bank not visible");
       }
        return this;
    }

    @Step("Checks that the Safe Order checkbox is not selected. Cart_page")
    public Cart_page_Logic checkThatSafeOrderCheckboxIsNotSelected() {
        safeOrderCheckbox().shouldNotHave(attribute("checked"));
        return this;
    }

    @Step("Check of id {idOfProduct} added product to basket. Cart_page")
    public Cart_page_Logic checkOfIdAddedProductInBasket(String idOfProduct) {
        idAddedProduct().shouldHave(attribute("data-article_id", idOfProduct));
        return this;
    }
}
