package ATD;

import io.qameta.allure.Step;
import org.testng.Assert;
import java.util.ArrayList;
import java.util.List;
import static ATD.CommonMethods.getTextFromUnVisibleElement;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;

public class Category_name_brand_page_Logic extends Category_name_brand_page {

    @Step("check absence of Quantity characteristic in Pop-Up of TOP products. Category_name_brand_page")
    public Category_name_brand_page_Logic checkAbsenceOfQuantityCharacteristicInTopProducts() {
        for (int i = 0; i < visibleTitleOfTopProducts().size(); i++) {
            titleOfTopProductsBlock().hover();
            visibleTitleOfTopProducts().get(i).shouldBe(visible).hover();
            popUpOfTopProducts().get(i).shouldBe(visible);
            checkAbsenceOfQuantityCharacteristicInPopUpTopProducts(i);
            if (i == 3) {
                activeBtnForwardOfTopProductsBlock().click();
                visibleTitleOfTopProducts().get(4).shouldBe(visible);
            }
        }
        return this;
    }

    @Step("check absence of Quantity characteristic in PopUp TOP product. Category_name_brand_page")
    public Category_name_brand_page_Logic checkAbsenceOfQuantityCharacteristicInPopUpTopProducts(int position) {
        List<String> listOfCharacteristic = new ArrayList<>();
        for (int i = 0; i < visibleCharacteristicInPopUpOfTopProducts(position + 1).size(); i++) {
            listOfCharacteristic.add(visibleCharacteristicInPopUpOfTopProducts(position + 1).get(i).getText());
        }
        Assert.assertTrue(!listOfCharacteristic.contains("Menge"));
        listOfCharacteristic.clear();
        return this;
    }

    @Step("Login in header with mail {mail} and transition to profile plus page and go back. Category_name_brand_page")
    public Category_name_brand_page_Logic loginToProfilePlusPageAndBack(String email) {
        new Main_page_Logic().loginAndTransitionToProfilePlusPage(email).visibilityOfUsersName();
        back();
        return this;
    }

    @Step("update of page. Category_name_brand_page")
    public Category_name_brand_page_Logic updateOfPage() {
        refresh();
        return this;
    }

    @Step("update of page. Category_name_brand_page")
    public Category_name_brand_page_Logic checkCountOfVehicleInIconOfGarage(String expectedCountOfVehicle) {
        countOfVehicleInIconOfGarageInHeader().shouldBe(visible).shouldHave(exactText(expectedCountOfVehicle));
        return this;
    }

    @Step("click on Garage icon in header. Category_name_brand_page")
    public Category_name_brand_page_Logic clickOnGarageIconInHeader() {
        headerGarageIcon().shouldBe(visible).click();
        popUpOfGarageInHeader().shouldBe(visible);
        return this;
    }

    @Step("click on Garage icon in header. Category_name_brand_page")
    public Category_car_list_page_Logic selectVehicleInGaragePopUp(String idOfVehicle) {
        idOfVehicleInGaragePopUp(idOfVehicle).shouldBe(visible).click();
        return page(Category_car_list_page_Logic.class);
    }

    @Step("presence Refurbished Characteristic In TOP products if art number contains expected symbol . Category_name_brand_page")
    public Category_name_brand_page_Logic presenceRefurbishedCharacteristicInTopProductWithArticle(String expectedCharacteristic, String symbol) {
        topProductsBlock().shouldBe(visible);
        List<String> characteristics = new ArrayList<>();
        addCharacteristicsOfTopProductsToList(characteristics, 0, symbol);
        while (!characteristics.contains(expectedCharacteristic.replaceAll(" ", ""))) {
            for (int i = 1; i < visibleImageOfTopProduct().size(); i++) {
                addCharacteristicsOfTopProductsToList(characteristics, i + 1, symbol);
            }
            characteristics.clear();
        }
        Assert.assertTrue(characteristics.contains(expectedCharacteristic.replaceAll(" ", "")));
        return this;
    }

    @Step("click on Garage icon in header. Category_name_brand_page")
    public Category_name_brand_page_Logic addCharacteristicsOfTopProductsToList(List<String> list, int positionOfTopProducts, String symbol) {
        String titleOfProduct = visibleTitleOfTopProducts().get(positionOfTopProducts).getText();
        String artNumOfProduct = artNumOfTopProduct().get(positionOfTopProducts).getText().replaceAll("Artikelnummer: ", "");
        if (titleOfProduct.contains("Henkel Parts") && artNumOfProduct.contains(symbol)) {
            for (int i = 0; i < allCharacteristicInPopUpOfTopProducts(positionOfTopProducts + 1).size(); i++) {
                list.add(getTextFromUnVisibleElement(allCharacteristicInPopUpOfTopProducts(positionOfTopProducts + 1).get(i)).replaceAll("\n", "").replace(" ", ""));
            }
        }

        return this;
    }

    @Step("Getting the article number and checking for a deposit in mini-cards for the 3K brand. Category_name_brand_page")
    public ArrayList<String> getArticleNumberAndCheckingDepositFor3KBrandFromMiniCard() {
        ArrayList<String> artNumAndDeposit = new ArrayList<>();
        activeBtnForwardOfTopProductsBlock().scrollIntoView(false);
        for (int i = 0; i < artNumOfTopProduct().size(); i++) {
            if (i == 4) {
                activeBtnForwardOfTopProductsBlock().click();
                sleep(2000);
            }
            artNumOfTopProduct().get(i).hover();
            String artNum = articleNumberInPopUpOfTopProducts(i + 1).shouldBe(visible).getText().replaceAll("\\D+", "");
            if (characteristicZustandInPopUpOfTopProducts(i + 1).isDisplayed()) {
                artNumAndDeposit.add(artNum + " " + "- No deposit");
            } else {
                artNumAndDeposit.add(artNum + " " + "- Have deposit");
            }
            titleOfTopProductsBlock().hover();
        }
        System.out.println(artNumAndDeposit);
        return artNumAndDeposit;
    }


}
