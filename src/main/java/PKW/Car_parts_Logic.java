package PKW;

import com.codeborne.selenide.ElementsCollection;
import io.qameta.allure.Step;
import org.testng.Assert;

import java.util.ArrayList;
import java.util.List;

import static PKW.CommonMethods.getTextFromUnVisibleElement;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.refresh;


public class Car_parts_Logic extends Car_parts {

    @Step("Checking presence soft 404 block. Car_parts")
    public Car_parts_Logic checkingPresenceSoft404Block() {
        soft404Block().shouldBe(visible);
        return this;
    }

    @Step(":from Car_parts")
    public Car_parts_Logic checkingDatenschutzerklarungLinkBehaviorInSoft404Form() {
        new CommonMethods().checkingDatenschutzerklarungLinkBehavior(linkDatenschutzerklärungInSoft404Block(), "underline solid rgb(0, 0, 0)");
        return this;
    }

    @Step("Checking success popup with click checkbox in soft 404 block. Car_parts")
    public String checkingSuccessPopupClickCheckbox(String qc) {
        refresh();
        String mail = qc + PKW.CommonMethods.mailRandom();
        subscriptionMailFieldInSoft404Block().setValue(mail);
        subscriptionMailCheckboxInSoft404Block().click();
        subscriptionButtonInSoft404Block().click();
        subscriptionSuccessPopup().shouldHave(text("Mit großer Freude informieren wir Sie, sobald die Produkte auf Lager sind"));
        subscriptionPopupClose().click();
        return mail;
    }

    @Step("presence Refurbished Characteristic in listing. Car_parts")
    public Car_parts_Logic presenceRefurbishedCharacteristic(String expectedCharacteristic) {
        productListBlock().shouldBe(visible);
        List<String> listOfCharacteristic = new ArrayList<>();

        addedAllCharacteristicsOfProductToList(listOfCharacteristic);
        while (!listOfCharacteristic.contains(expectedCharacteristic)) {
            listOfCharacteristic.clear();
            forwardLinkOfPaginator().scrollIntoView("{block: \"end\"}").click();
            addedAllCharacteristicsOfProductToList(listOfCharacteristic);
        }
        Assert.assertTrue(listOfCharacteristic.contains(expectedCharacteristic));
        return this;
    }

    @Step("added all characteristics of product to list. Car_parts")
    public Car_parts_Logic addedAllCharacteristicsOfProductToList(List<String> list) {
        for (int i = 0; i < allCharacteristicsOfProducts().size(); i++) {
            list.add(getTextFromUnVisibleElement(allCharacteristicsOfProducts().get(i)));
        }
        return this;
    }

    @Step("presence Refurbished Characteristic In Listing if art number contains expected symbol . Car_parts")
    public Car_parts_Logic presenceRefurbishedCharacteristicInListingProductWithArticle(String expectedCharacteristic, String symbol) {
        productListBlock().shouldBe(visible);
        checkCharacteristicOfTopProduct(expectedCharacteristic, symbol);
        return this;
    }

    @Step("checking characteristic of TOP product . Car_parts")
    public Car_parts_Logic checkCharacteristicOfTopProduct(String expectedCharacteristic, String symbol) {
        for (int i = 0; i < visibleArtNumOfProduct().size(); i++) {
            List<String> characteristics = new ArrayList<>();
            String titleOfBrandImage = visibleTitleOfProducts().get(i).getText();
            String artNumOfProduct = visibleArtNumOfProduct().get(i).getText().replace("Art. Nr. : ", "");
            if (titleOfBrandImage.contains("Henkel Parts") && artNumOfProduct.contains(symbol)) {
                for (int j = 0; j < visibleCharacteristicsOfProducts(i + 1).size(); j++) {
                    characteristics.add(getTextFromUnVisibleElement(visibleCharacteristicsOfProducts(i + 1).get(j)));
                }
                Assert.assertTrue(characteristics.contains(expectedCharacteristic));
                characteristics.clear();
            }
        }
        return this;
    }


    @Step("get name criteria Front Axle. Car_parts")
    public String getNameFrontAxle() {
        return criteriaFrontAxle().getText();
    }

    @Step("Click criteria Front Axle. Car_parts")
    public Car_parts_Logic clickFrontAxle() {
        criteriaFrontAxle().shouldBe(visible).click();
        new Listing_page_Logic().waitUntilPreloaderDisappear();
        return this;
    }

    @Step("Get text criteria diameter from sidebar. Car_parts")
    public String getTextCriteriaDiameter() {
        return criteriaDiameterFromSidebar().getText();
    }

    @Step("Click criteria diameter from sidebar. Car_parts")
    public Car_parts_Logic clickCriteriaDiameter() {
        criteriaDiameterFromSidebar().shouldBe(visible).click();
        new Listing_page_Logic().waitUntilPreloaderDisappear();
        return this;
    }

    @Step("Get text criteria PR_Number from sidebar. Car_parts")
    public String getTextCriteriaPRNumber() {
        return criteriaPRNumberInSidebar().hover().getText();
    }

    @Step("Click criteria PR_Number from sidebar. Car_parts")
    public Car_parts_Logic clickCriteriaPRNumber() {
        criteriaPRNumberInSidebar().shouldBe(visible).click();
        new Listing_page_Logic().waitUntilPreloaderDisappear();
        return this;
    }

    @Step("Get text criteria Bremsscheibenart from sidebar. Car_parts")
    public String getTextCriteriaBremsscheibenart() {
        return criteriaBremsscheibenartFromSidebar().hover().getText();
    }

    @Step("Click criteria Bremsscheibenart from sidebar. Car_parts")
    public Car_parts_Logic clickCriteriaBremsscheibenart() {
        criteriaBremsscheibenartFromSidebar().shouldBe(visible).click();
        new Listing_page_Logic().waitUntilPreloaderDisappear();
        return this;
    }

    @Step("Check characteristic in products. Car_parts")
    public Car_parts_Logic checkCharacteristicInProducts(ElementsCollection characteristic, String expectedText) {
        for (int i = 0; i < characteristic.size(); i++) {
           String characteristicProduct = characteristic.get(i).getText().replace(",0", "").toLowerCase();
           Assert.assertEquals(expectedText, characteristicProduct);
        }
        return this;
    }

    @Step("Check characteristic in products if has multiple values. Car_parts")
    public Car_parts_Logic checkCharacteristicInProductsIfHasMultipleValues(ElementsCollection characteristic, String expectedText) {
        for (int i = 0; i < characteristic.size(); i++) {
            String characteristicProduct = characteristic.get(i).getText().toLowerCase();
            Assert.assertTrue(characteristicProduct.contains(expectedText));
        }
        return this;
    }

    @Step(": for Car_parts")
    public Car_parts_Logic checkPresenceAddToCartBtnForSpecificItem(String productID) {
        new Listing_page_Logic().checkPresenceAddToCartBtnForSpecificItem(productID);
        return this;
    }

}