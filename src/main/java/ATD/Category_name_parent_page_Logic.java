package ATD;

import io.qameta.allure.Step;
import org.testng.Assert;

import java.util.List;
import java.util.stream.Collectors;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;

public class Category_name_parent_page_Logic extends Category_name_parent_page {


    @Step("Login in header with mail {mail} and transition to profile plus page and go back. Category_name_parent_page")
    public Category_name_parent_page_Logic loginToProfilePlusPageAndBack(String email) {
        new Main_page_Logic().loginAndTransitionToProfilePlusPage(email).visibilityOfUsersName();
        back();
        return this;
    }

    @Step("update of page. Category_name_parent_page")
    public Category_name_parent_page_Logic updateOfPage() {
        refresh();
        return this;
    }

    @Step("update of page. Category_name_parent_page")
    public Category_name_parent_page_Logic checkCountOfVehicleInIconOfGarage(String expectedCountOfVehicle) {
        countOfVehicleInIconOfGarageInHeader().shouldBe(visible).shouldHave(exactText(expectedCountOfVehicle));
        return this;
    }

    @Step("click on Garage icon in header. Category_name_parent_page")
    public Category_name_parent_page_Logic clickOnGarageIconInHeader() {
        headerGarageIcon().shouldBe(visible).click();
        popUpOfGarageInHeader().shouldBe(visible);
        return this;
    }

    @Step("click on Garage icon in header. Category_name_parent_page")
    public Category_car_list_page_Logic selectVehicleInGaragePopUp(String idOfVehicle) {
        idOfVehicleInGaragePopUp(idOfVehicle).shouldBe(visible).click();
        return page(Category_car_list_page_Logic.class);
    }

    @Step("check Headlines Of Seo Text. Category_name_parent_page")
    public Category_name_parent_page_Logic checkHeadlinesOfSeoText(List<String> expectedTitles) {
        List<String> frontTitlesOfSeoBlock = titlesOfSeoBlock().stream().map(n -> n.getText()).collect(Collectors.toList());
        Assert.assertEquals(frontTitlesOfSeoBlock, expectedTitles);
        return this;
    }

    @Step("check Headlines Of Seo Text. Category_name_parent_page")
    public Category_name_parent_page_Logic displayHeadlineOfSeoBlock(String expectedHeadLine) {
        headlineOfSeoText().shouldBe(visible).shouldHave(text(expectedHeadLine));
        return this;
    }
}
