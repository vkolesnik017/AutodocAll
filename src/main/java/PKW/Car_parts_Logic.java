package PKW;

import io.qameta.allure.Step;
import org.testng.Assert;

import java.util.ArrayList;
import java.util.List;

import static PKW.CommonMethods.getTextFromUnVisibleElement;
import static com.codeborne.selenide.Condition.*;


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

    @Step("presence Refurbished Characteristic In TOP product if art number contains expected symbol . Car_parts")
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

}