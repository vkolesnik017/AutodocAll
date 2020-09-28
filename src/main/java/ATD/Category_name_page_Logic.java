package ATD;

import Common.DataBase;
import io.qameta.allure.Step;
import org.testng.Assert;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static ATD.CommonMethods.*;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;

// Не создавался класс Category_name_page, так как в нём пока нет надобности

public class Category_name_page_Logic extends Category_name_page {

    @Step("Verify name route equals category_name. Category_name_page")
    public Category_name_page_Logic verifyNameRouteEqualsCategoryName() {
        waitWhileRouteBecomeExpected("category_name");
        return this;
    }

    @Step("check successfully child category page loading. Category_name_page")
    public Category_name_page_Logic checkSuccessfullyChildCategoryLoadingFromMainPage() throws SQLException {
        imageOfChildCategory().shouldBe(visible);
        checkingContainsUrl(new DataBase("ATD").getRouteByRouteName("DE", "category_name8"));
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

    @Step("presence of TOP products block. Category_name_page")
    public Category_name_page_Logic presenceOfTopProductsBlock() {
        topProductsBlock().shouldBe(visible);
        return this;
    }

    @Step("get id of Dangerous product .Category_name_page")
    public String getIdOfDangerousProduct(int positionOfProduct) {
        scrollToDangerousElement(positionOfProduct);
        return btnAddDangerousProductToWishList().get(positionOfProduct).getAttribute("data-product-id");
    }

    @Step("get signal word from first dangerous product .Category_name_page")
    public String getSignalWordFromFirstDangerousProduct(int positionOfProduct) {
        return getTextFromUnVisibleElement(signalWordOfDangerousProduct().get(positionOfProduct));
    }

    @Step("get attribute of Warning icon in pop-Up .Category_name_page")
    public List<String> getAttributeOfWarningIconInPopUp(int positionOfProduct) {
        scrollToDangerousElement(positionOfProduct);
        List<String> attribute = new ArrayList<>();
        dangerousProducts().get(positionOfProduct).scrollIntoView("{block: \"center\"}").hover();
        for (int i = 0; i < attributeOfWarningIcon(positionOfProduct + 1).size(); i++) {
            attribute.add(attributeOfWarningIcon(positionOfProduct + 1).get(i).shouldBe(visible).getAttribute("style").replace("background-image: url(\"", "").replace("\");", ""));
        }
        return attribute;
    }

    @Step("scroll to dangerous element. Category_name_page")
    public Category_name_page_Logic scrollToDangerousElement(int positionOfProduct) {
        String artNumOfFirstTopProduct = null;
        while (!btnAddDangerousProductToWishList().get(positionOfProduct).isDisplayed()) {
            artNumOfFirstTopProduct = visibleArtNumOfTopProduct().get(0).getText();
            forwardOfTopBrandsBlock().click();
            visibleArtNumOfTopProduct().get(0).shouldNotHave(exactText(artNumOfFirstTopProduct));
        }
        return this;
    }

    @Step("click on dangerous label of product and compare elements. Category_name_page")
    public Category_name_page_Logic clickOnDangerousLabelAndCompareElements(int positionOfProduct, String signalWord, List<String> attributeOfWarningIcon) {
        scrollToDangerousElement(positionOfProduct);

            labelTitleDangerousProducts().get(positionOfProduct).shouldBe(visible).click();
            blackBackground().shouldHave(attribute("style", "display: block;"));
            warningPopUp().shouldBe(visible).shouldHave(attribute("style", "display: block;"));
            titleOfDangerousPopUp().shouldBe(visible).shouldHave(exactText(signalWord));
            infoTextOfDangerousPopUp().shouldNotBe(empty);
            List<String> attributeOfDangerousIcon = attributeOfWarningIcon(positionOfProduct+1).stream().map(n -> n.getAttribute("style").replace("background-image: url(\"", "").replace("\");", "")).collect(Collectors.toList());
            Assert.assertEquals(attributeOfDangerousIcon, attributeOfWarningIcon);

        return this;
    }

}
