package PKW;

import Common.Excel;
import com.codeborne.selenide.ex.ElementNotFound;
import io.qameta.allure.Step;
import org.testng.Assert;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static PKW.CommonMethods.getTextFromUnVisibleElement;
import static PKW.CommonMethods.openPage;
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
        CommonMethods commonMethods = new CommonMethods();
        displayOfNavigationHeaderLinks();
        String markeFromSelector = markeFieldSelector().getSelectedValue();
        String modelFromSelector = modelFieldSelector().getSelectedValue();
        String motorFromSelector = motorFieldSelector().getSelectedValue();
        boolean markeLabel = markeFromSelector.equals(marke) ? true : false;
        boolean modelLabel = modelFromSelector.equals(model) ? true : false;
        boolean motorLabel = motorFromSelector.equals(carId) ? true : false;
        if (markeLabel == false && modelLabel == true && motorLabel == true) {
            commonMethods.writerInFile(file, true, String.format("MakerId not equals. from File - %s, from Selector - %s . Url - %s", marke, markeFromSelector, startUrl));
        } else if (modelLabel == false && markeLabel == true && motorLabel == true) {
            commonMethods.writerInFile(file, true, String.format("ModelId not equals. from File - %s, from Selector - %s . Url - %s", model, modelFromSelector, startUrl));
        } else if (motorLabel == false && markeLabel == true && modelLabel == true) {
            commonMethods.writerInFile(file, true, String.format("MotorId(CarId) not equals. from File - %s, from Selector - %s . Url - %s", carId, motorFromSelector, startUrl));
        } else if (markeLabel == false && modelLabel == false && motorLabel == true) {
            commonMethods.writerInFile(file, true, String.format("MakerId and ModelId not equals. MakerId from File - %s,  MakerId from Selector - %s ; ModelId from File - %s,  ModelId from Selector - %s ; Url - %s",
                    marke, markeFromSelector, model, modelFromSelector, startUrl));
        } else if (markeLabel == false && modelLabel == true && motorLabel == false) {
            commonMethods.writerInFile(file, true, String.format("MakerId and MotorId(CarId) not equals. MakerId from File - %s,  MakerId from Selector - %s ; CarId from File - %s,  CarId from Selector - %s ; Url - %s",
                    marke, markeFromSelector, carId, motorFromSelector, startUrl));
        } else if (markeLabel == true && modelLabel == false && motorLabel == false) {
            commonMethods.writerInFile(file, true, String.format("ModelId and MotorId(CarId) not equals. ModelId from File - %s,  ModelId from Selector - %s ; CarId from File - %s,  CarId from Selector - %s ; Url - %s",
                    model, modelFromSelector, carId, motorFromSelector, startUrl));
        } else if (markeLabel == false && modelLabel == false && motorLabel == false) {
            commonMethods.writerInFile(file, true, String.format("MakerId, ModelId and MotorId(CarId) not equals. MakerId from File - %s,  MakerId from Selector - %s ;  ModelId from File - %s,  ModelId from Selector - %s ; " +
                            "CarId from File - %s,  CarId from Selector - %s ; Url - %s",
                    marke, markeFromSelector, model, modelFromSelector, carId, motorFromSelector, startUrl));
        }
        return this;
    }

    @Step("compare Selector Values With File. Search_page")
    public Search_page_Logic compareCarTitleSelectorValueWithFile(String carTitle, String file, String startUrl) throws IOException {
        CommonMethods commonMethods = new CommonMethods();
        String motorFromSelector = motorFieldSelector().getSelectedText();
        String carTitleFromFile = carTitle.replaceAll(",", ".");
        if (!motorFromSelector.contains(carTitleFromFile)) {
            commonMethods.writerInFile(file, true, String.format("CarTitle not contains value from file. from File - %s, from Selector - %s . Url - %s",
                    carTitleFromFile, motorFromSelector, startUrl));
        }
        return this;
    }

    @Step("display Of Navigation Header Links. Search_page")
    public Search_page_Logic displayOfNavigationHeaderLinks() {
        for (int i = 0; i < visibleNavigationHeaderLinks().size(); i++) {
            visibleNavigationHeaderLinks().get(i).shouldBe(visible);
        }
        return this;
    }

    @Step(": for Search_page")
    public Search_page_Logic checkAbsenceArticleNum(String expectedArtNum) {
        new Listing_page_Logic().checkAbsenceArticleNum(expectedArtNum);
        return this;
    }

    @Step(": for Search_page")
    public Search_page_Logic checkPresenceAddToCartBtnForSpecificItem(String productID) {
        new Listing_page_Logic().checkPresenceAddToCartBtnForSpecificItem(productID);
        return this;
    }

    @Step("compare Selector With File. Search_page")
    public Search_page_Logic compareSelectorWithFile(List<String> language, String dataFile, String result) throws IOException {
        String startUrl = null;
        try {
            for (int i = 0; i < language.size(); i++) {
                String currentLanguage = language.get(i).replaceAll("^.+\\.", "").toUpperCase();
                if (currentLanguage.equals("AT")  || currentLanguage.equals("CH")) {
                    currentLanguage = "DE";
                } else if (currentLanguage.equals("COM")) {
                    currentLanguage = "FR";
                }
                List<String> maker_id = new Excel().readFromExcel(dataFile, currentLanguage, 0);
                List<String> model_id = new Excel().readFromExcel(dataFile, currentLanguage, 4);
                List<String> group_id = new Excel().readFromExcel(dataFile, currentLanguage, 2);
                for (int j = 0; j < maker_id.size(); j++) {
                    startUrl = language.get(i) + "/search?&maker_id=" + maker_id.get(j) + "&model_id=" + model_id.get(j) + "&group_id=" + group_id.get(j);
                    openPage(startUrl);
                    compareSelectorValuesWithFile(maker_id.get(j), model_id.get(j), result, startUrl);
                }
                maker_id.clear();
                model_id.clear();
                group_id.clear();
            }
        } catch (ElementNotFound element) {
            new CommonMethods().writerInFile(result, true, "Trouble with element in selector" + "#" + startUrl);
        }

        return this;
    }

    @Step("compare Selector Values With File. Search_page")
    public Search_page_Logic compareSelectorValuesWithFile(String marke, String model, String file, String startUrl) throws IOException {
        CommonMethods commonMethods = new CommonMethods();
        displayOfNavigationHeaderLinks();
        String markeFromSelector = markeFieldSelector().getSelectedValue();
        String modelFromSelector = modelFieldSelector().getSelectedValue();
        boolean markeLabel = markeFromSelector.equals(marke) ? true : false;
        boolean modelLabel = modelFromSelector.equals(model) ? true : false;
        if (markeLabel == false && modelLabel == true) {
            commonMethods.writerInFile(file, true, String.format("MakerId not equals. from File - %s, from Selector - %s . Url - %s", marke, markeFromSelector, startUrl));
        } else if (modelLabel == false && markeLabel == true) {
            commonMethods.writerInFile(file, true, String.format("ModelId not equals. from File - %s, from Selector - %s . Url - %s", model, modelFromSelector, startUrl));
        } else if (markeLabel == false && modelLabel == false) {
            commonMethods.writerInFile(file, true, String.format("MakerId and ModelId not equals. MakerId from File - %s,  MakerId from Selector - %s ; ModelId from File - %s,  ModelId from Selector - %s ; Url - %s",
                    marke, markeFromSelector, model, modelFromSelector, startUrl));
        }
        return this;
    }
}
