package ATD;

import io.qameta.allure.Step;
import org.testng.Assert;

import java.util.ArrayList;
import java.util.List;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Condition.visible;
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
}
