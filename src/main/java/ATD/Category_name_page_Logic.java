package ATD;

import io.qameta.allure.Step;
import org.testng.Assert;

import static ATD.CommonMethods.waitWhileRouteBecomeExpected;
import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.WebDriverRunner.url;

// Не создавался класс Category_name_page, так как в нём пока нет надобности

public class Category_name_page_Logic extends Category_name_page {

    @Step("Verify name route equals category_name. Category_name_page")
    public Category_name_page_Logic verifyNameRouteEqualsCategoryName() {
        waitWhileRouteBecomeExpected("category_name");
        return this;
    }

    @Step("check successfully child category page loading. Category_name_page")
    public Category_name_page_Logic checkSuccessfullyChildCategoryLoadingFromMainPage() {
        imageOfChildCategory().shouldBe(visible);
        Assert.assertEquals(url(), "https://www.autodoc.de/autoteile/olfilter-10359");
        return this;
    }

    @Step("Login in header with mail {mail} and transition to profile plus page and go back. Category_name_page")
    public Category_name_page_Logic loginToProfilePlusPageAndBack(String email) {
        new Main_page_Logic().loginAndTransitionToProfilePlusPage(email).visibilityOfUsersName();
        back();
        return this;
    }

    @Step("update of page. Category_name_page")
    public Category_name_page_Logic updateOfPage() {
        refresh();
        return this;
    }

    @Step("update of page. Category_name_page")
    public Category_name_page_Logic checkCountOfVehicleInIconOfGarage(String expectedCountOfVehicle) {
        countOfVehicleInIconOfGarageInHeader().shouldBe(visible).shouldHave(exactText(expectedCountOfVehicle));
        return this;
    }

    @Step("click on Garage icon in header. Category_name_page")
    public Category_name_page_Logic clickOnGarageIconInHeader() {
        headerGarageIcon().shouldBe(visible).click();
        popUpOfGarageInHeader().shouldBe(visible);
        return this;
    }

    @Step("click on Garage icon in header. Category_name_page")
    public Category_car_list_page_Logic selectVehicleInGaragePopUp(String idOfVehicle) {
        idOfVehicleInGaragePopUp(idOfVehicle).shouldBe(visible).click();
        return page(Category_car_list_page_Logic.class);
    }
}
