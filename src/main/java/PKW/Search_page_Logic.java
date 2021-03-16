package PKW;

import io.qameta.allure.Step;
import org.testng.Assert;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static PKW.CommonMethods.getTextFromUnVisibleElement;
import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Condition.visible;

public class Search_page_Logic extends Search_page {

    @Step("presence Refurbished Characteristic in listing. Search_page")
    public Search_page_Logic presenceRefurbishedCharacteristic(String expectedCharacteristic) {
        productListBlock().shouldBe(visible);
        List<String> listOfCharacteristic = new ArrayList<>();
        String currentTitleOfProduct;
        addedAllCharacteristicsOfProductToList(listOfCharacteristic);
        if (!listOfCharacteristic.contains(expectedCharacteristic)) {
            while (forwardLinkOfPaginator().isDisplayed()) {
                currentTitleOfProduct = visibleTitleOfProducts().get(0).getText();
                forwardLinkOfPaginator().scrollIntoView("{block: \"end\"}").click();
                visibleTitleOfProducts().get(0).shouldNotHave(exactText(currentTitleOfProduct));
                addedAllCharacteristicsOfProductToList(listOfCharacteristic);
            }
            Assert.assertTrue(listOfCharacteristic.contains(expectedCharacteristic));
        }
        return this;
    }

    @Step("added all characteristics of product to list. Search_page")
    public Search_page_Logic addedAllCharacteristicsOfProductToList(List<String> list) {
        for (int i = 0; i < allCharacteristicsOfProducts().size(); i++) {
            list.add(getTextFromUnVisibleElement(allCharacteristicsOfProducts().get(i)));
        }
        return this;
    }

    @Step("presence Refurbished Characteristic In Listing if art number contains expected symbol . Search_page")
    public Search_page_Logic presenceRefurbishedCharacteristicInListingWithArticle(String expectedCharacteristic, String symbol) {
        productListBlock().shouldBe(visible);
        checkCharacteristicOfTopProduct(expectedCharacteristic, symbol);
        return this;
    }

    @Step("checking characteristic of TOP product . Search_page")
    public Search_page_Logic checkCharacteristicOfTopProduct(String expectedCharacteristic, String symbol) {
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

    @Step("compare Selector Values With File. Search_page")
    public Search_page_Logic compareSelectorValuesWithFile(String marke, String model, String carId, String file, String startUrl) throws IOException {
        String markeFromSelector = markeFieldSelector().getSelectedValue();
        String modelFromSelector = modelFieldSelector().getSelectedValue();
        String motorFromSelector = motorFieldSelector().getSelectedValue();
        boolean markeLabel = markeFromSelector.equals(marke) ? true : false;
        boolean modelLabel = modelFromSelector.equals(model) ? true : false;
        boolean motorLabel = motorFromSelector.equals(carId) ? true : false;
        if (markeLabel == false && modelLabel == true && motorLabel == true) {
            new CommonMethods().writerInFile(file, true, String.format("MakerId not equals. from File - %s, from Selector - %. Url - %s", marke, markeFromSelector, startUrl));
        } else if (modelLabel == false && markeLabel == true && motorLabel == true) {
            new CommonMethods().writerInFile(file, true, String.format("ModelId not equals. from File - %s, from Selector - %. Url - %s", model, modelFromSelector, startUrl));
        } else if (motorLabel == false && markeLabel == true && modelLabel == true) {
            new CommonMethods().writerInFile(file, true, String.format("MotorId(CarId) not equals. from File - %s, from Selector - %. Url - %s", carId, motorFromSelector, startUrl));
        } else if (markeLabel == false && modelLabel == false && motorLabel == true) {
            new CommonMethods().writerInFile(file, true, String.format("MakerId and ModelId not equals. MakerId from File - %s,  MakerId from Selector - %; ModelId from File - %s,  ModelId from Selector - %; Url - %s",
                    marke, markeFromSelector, model, modelFromSelector, startUrl));
        }
        System.out.println();
        return this;
    }
}
