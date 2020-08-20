package ATD;

import com.codeborne.selenide.ElementsCollection;
import files.Product;
import io.qameta.allure.Step;
import org.testng.Assert;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import static com.codeborne.selenide.Condition.*;

public class Tyre_form_page_Logic extends Tyre_form_page {

    @Step("Displaying customer feedback PopUp .Tyre_form_page")
    public Tyre_form_page_Logic displayingCustomerFeedbackPopUp() {
        productsList().shouldBe(visible);
        btnOutOfStock().get(0).click();
        feedBackPopUp().should(appear);
        return this;
    }

    @Step("displaying of popup about successful sending of the letter .Tyre_form_page")
    public Tyre_form_page_Logic displayingOfPopUPAboutSuccessfulSendingLetter(String email) {
        feedBackPopUp().shouldBe(visible);
        setValueInEmailFieldOfPopUp(email);
        checkBoxOfFeedBackPopUp().click();
        clickOnBtnSubscription();
        feedBackPopUp().should(disappear);
        popUPAboutSuccessfulSending().should(appear);
        return this;
    }

    @Step("set value in email field of PopUp .Tyre_form_page")
    public Tyre_form_page_Logic setValueInEmailFieldOfPopUp(String email) {
        emailFieldOfFeedBackPopUp().shouldBe(visible).setValue(email);
        return this;
    }

    @Step("click on Subscription button .Tyre_form_page")
    public Tyre_form_page_Logic clickOnBtnSubscription() {
        btnSendOfFeedBackPopUp().click();
        return this;
    }

    @Step("displaying of popUp about an unset checkbox when trying to send mail for feedback .Tyre_form_page")
    public Tyre_form_page_Logic displayingOfPopUPAboutUnsetCheckBox(String email) {
        feedBackPopUp().shouldBe(visible);
        setValueInEmailFieldOfPopUp(email);
        clickOnBtnSubscription();
        popUPAboutAnUnsetCheckbox().should(appear);
        return this;
    }

    @Step("displaying of popUp pop-up error about invalid email when trying to send mail for feedback .Tyre_form_page")
    public Tyre_form_page_Logic displayingOfPopUPAboutInvalidEmail(String email) {
        feedBackPopUp().shouldBe(visible);
        setValueInEmailFieldOfPopUp(email);
        checkBoxOfFeedBackPopUp().click();
        clickOnBtnSubscription();
        popUPAboutInvalidEmail().should(appear).shouldHave(text("Das Feld E-mail muss eine gültige E-Mail-Adresse enthalten"));
        return this;
    }

    @Step("displaying of popUp pop-up error about invalid email when trying to send mail for feedback with selected checkbox.Tyre_form_page")
    public Tyre_form_page_Logic displayingOfPopUPAboutInvalidEmailAndWithCheckbox(String email) {
        feedBackPopUp().shouldBe(visible);
        setValueInEmailFieldOfPopUp(email);
        clickOnBtnSubscription();
        popUPAboutInvalidEmail().should(appear).shouldHave(text("Das Feld E-mail muss eine gültige E-Mail-Adresse enthalten."));
        return this;
    }

    @Step("presence of product listing block. Tyre_form_page")
    public Tyre_form_page_Logic presenceOfListingBlock() {
        productListBlock().shouldBe(visible);
        return this;
    }

    @Step("check sorting of products with grey button. Tyre_form_page")
    public Tyre_form_page_Logic checkSortingOfProductsWithGreyButton() {
        List<Product> productList = new ArrayList<>();
        addProductToList(productList, productsFromListBlock());
        while (forwardOfListing().isDisplayed()) {
            forwardOfListing().click();
            addProductToList(productList, productsFromListBlock());
        }
        List<Product> listBeforeSorting = new ArrayList<>(productList);
        Comparator<Product> productsComparator = Comparator
                .comparing((Product p) -> "button ".equals(p.getAttributeOfButton()) ? -1 : 0)
                .thenComparingDouble(Product::getPriceOfProduct);
        productList.sort(productsComparator);
        Assert.assertEquals(listBeforeSorting, productList);
        return this;
    }

    @Step("added product to list. Tyre_form_page")
    public Tyre_form_page_Logic addProductToList(List<Product> activeList, ElementsCollection products) {
        for (int i = 0; i < products.size(); i++) {
            Product product = new Product();
            product.setGenericOfProduct(products.get(i).getAttribute("data-name"));
            product.setPriceOfProduct(Double.parseDouble(priceOfProduct().get(i).getText().replaceAll("[^0-9,]", "").replace(",", ".")));
            product.setAttributeOfButton(attributeOfBtnAddedToBasket().get(i).getAttribute("class"));
            activeList.add(product);
        }

        return this;
    }
}
