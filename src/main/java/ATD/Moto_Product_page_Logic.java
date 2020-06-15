package ATD;

import AWS.ProductCard_aws;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.testng.Assert;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static ATD.CommonMethods.checkingContainsUrl;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.WebDriverRunner.url;

public class Moto_Product_page_Logic extends Moto_Product_page {

    @Step("availability of moto selector block .Moto_Product_page")
    public Moto_Product_page_Logic availabilityOfMotoSelector() {
        motoSelectorBlock().shouldBe(visible);
        return this;
    }

    @Step(" appears of tooltip for marke field in selector .Moto_Product_page")
    public Moto_Product_page_Logic visibilityOfToolTipForMarkeField() {
        btnSearchAtSelector().shouldBe(visible).click();
        toolTipForBrandFieldInSelector().shouldBe(visible);
        return this;
    }

    @Step(" appears of tooltip for model field in selector .Moto_Product_page")
    public Moto_Product_page_Logic visibilityOfToolTipForModelField() {
        brandOfMotoSelector().selectOptionByValue("4057");
        btnSearchAtSelector().click();
        toolTipForModelFieldInSelector().shouldBe(visible);
        return this;
    }

    @Step(" appears of tooltip for motor field in selector .Moto_Product_page")
    public Moto_Product_page_Logic visibilityOfToolTipForMotorField() {
        modelOfMotoSelector().selectOptionByValue("13475");
        btnSearchAtSelector().click();
        toolTipForMotorFieldInSelector().shouldBe(visible);
        return this;
    }


    @Step(" select motorcycle in horizontal selector .Moto_Product_page")
    public Moto_Product_page_Logic selectMotoInHorizontalSelector(String brand, String model, String motor) {
        brandOfMotoSelector().selectOptionByValue(brand);
        modelOfMotoSelector().selectOptionByValue(model);
        motorOfMotoSelector().selectOptionByValue(motor);
        btnSearchWithSelectedMoto().click();
        return this;
    }


    @Step(" visibility Of Error Message .Moto_Product_page")
    public Moto_Catalog_page_Logic visibilityOfErrorMessage() {
        infoPopUp().shouldBe(visible);
        return page(Moto_Catalog_page_Logic.class);
    }


    @Step(" get brand and model Of motorcycle .Moto_Product_page")
    public String getBrandAndModelOfMoto() {
        String brandOfMoto = getBrandOfMoto();
        String motorOfMoto = getMotorOfMoto();
        String brandAndModel = brandOfMoto + " " + motorOfMoto;
        return brandAndModel;
    }

    @Step(" get brand Of motorcycle .Moto_Product_page")
    public String getBrandOfMoto() {
        productCompatibilityHeader().shouldBe(visible);
        String brandOfMoto = brandOfMotoSelector().getSelectedText();
        return brandOfMoto;
    }

    @Step(" get brand Of motorcycle .Moto_Product_page")
    public String getMotorOfMoto() {
        String selectedMotorOfMoto = motorOfMotoSelector().getSelectedText().replace(motorOfMotoSelector().getSelectedText().substring(motorOfMotoSelector().getSelectedText().lastIndexOf("/")), "");
        String motorOfMoto = selectedMotorOfMoto.replace(selectedMotorOfMoto.substring(selectedMotorOfMoto.lastIndexOf(")")), "");

        return motorOfMoto;
    }


    @Step("presence  of motorcycle brand and model in an information message .Moto_Product_page")
    public Moto_Product_page_Logic presenceOfMotoBrandAtInfoMessage(String brandOfMoto) {
        Assert.assertTrue(motoBrandFromInfoMessage().getText().contains(brandOfMoto));
        return this;
    }


    @Step(" Select brand of motorcycle .Moto_Product_page")
    public Moto_Product_page_Logic selectBrandOfMoto(String markeOfMoto) {
        brandOfMotoSelector().selectOptionByValue(markeOfMoto);
        return this;
    }

    @Step(" reset of motorcycle selector .Moto_Product_page")
    public Moto_Product_page_Logic resetOfMotoSelector() {
        btnResetOfSelector().shouldBe(visible).click();
        return this;
    }


    @Step(" reset of motorcycle selector .Moto_Product_page")
    public Moto_Product_page_Logic presenceOfEmptyValuesInSelector() {
        brandOfMotoSelector().shouldHave(exactValue("0"));
        modelOfMotoSelector().shouldHave(exactValue("0"));
        motorOfMotoSelector().shouldHave(exactValue("0"));
        return this;
    }

    @Step(" check current url  .Moto_Product_page")
    public Moto_Product_page_Logic checkCurrentUrl(String subRoute) throws SQLException {
        checkingContainsUrl(new DataBase().getFullRouteByRouteAndSubroute("subprod", "DE", "moto_main", subRoute));
        return this;
    }

    @Step("checking the compatibility of goods and cars .Moto_Product_page")
    public Moto_Product_page_Logic checkCompatibilityProductAndTruck(String motoBrand) {
        titleOfProduct().shouldBe(visible);
        if (!linkOfCompatibilityMotoAndProduct().isDisplayed()) {
            compatibilityMotoBlock().shouldBe(visible).scrollTo();
            compatibilityMotoBrand().click();
            compatibilityModelBlock().should(appear);
            List<String> motoFromBlock = new ArrayList<>();
            for (SelenideElement e : listOfMotoAtCompatibilityBlock()) {
                motoFromBlock.add(e.getText().replace(e.getText().substring(e.getText().lastIndexOf("(")), "").replaceAll(" ", ""));
            }
            if (!motoFromBlock.contains(motoBrand)) {
                String idOfProduct = url().replaceAll("[^0-9]", "");
                executeJavaScript("window.open('about:blank','_blank')");
                switchTo().window(1);
                new ProductCard_aws(idOfProduct).openProductCardPageAndLogin().checkMotoLabel();
                switchTo().window(1).close();
                switchTo().window(0);
            }
        }

        return this;
    }


    @Step("check size of links in bread crumbs .Moto_Product_page")
    public Moto_Product_page_Logic checkSizeOfBreadCrumbsLinks(int size) {
        breadCrumbsLinks().shouldHaveSize(size);
        return this;
    }

    @Step("check first link of bread crumbs .Moto_Product_page ")
    public Moto_Product_page_Logic checkFirstLinkOfBreadCrumbs() throws SQLException {
        clickOnFirstLinkOfBreadCrumbs();
        checkingContainsUrl(new DataBase().getRouteByRouteName("DE", "moto_categories"));
        back();
        return this;
    }

    @Step("click on first link of bread crumbs .Moto_Product_page ")
    public Moto_Categories_page_Logic clickOnFirstLinkOfBreadCrumbs() {
        iconOfCatalogBrandInBreadCrumbs().shouldBe(visible).click();
        return page(Moto_Categories_page_Logic.class);
    }


    @Step("check second link of bread crumbs .Moto_Product_page ")
    public Moto_Product_page_Logic checkSecondLinkOfBreadCrumbs(String title) throws SQLException {
        clickOnSecondLinkOfBreadCrumbs(title);
        checkingContainsUrl(new DataBase().getRouteByRouteName("DE", "moto_parent_category"));
        back();
        return this;
    }

    @Step("click on second link of bread crumbs .Moto_Product_page ")
    public Moto_Parent_Category_page_Logic clickOnSecondLinkOfBreadCrumbs(String title) {
        horizontalMotoSelector().shouldBe(visible);
        closeTooltipOfBrandField();
        breadCrumbsLinks().get(1).shouldBe(visible).shouldHave(text(title)).click();
        return page(Moto_Parent_Category_page_Logic.class);
    }


    @Step("check third link of bread crumbs .Moto_Product_page ")
    public Moto_Product_page_Logic checkThirdLinkOfBreadCrumbs(String title) throws SQLException {
        clickOnThirdLinkOfBreadCrumbs(title);
        checkingContainsUrl(new DataBase().getRouteByRouteName("DE", "moto_category"));
        back();
        return this;
    }

    @Step("click on third link of bread crumbs .Moto_Product_page ")
    public Moto_Category_page_Logic clickOnThirdLinkOfBreadCrumbs(String title) {
        horizontalMotoSelector().shouldBe(visible);
        closeTooltipOfBrandField();
        breadCrumbsLinks().get(2).shouldBe(visible).shouldHave(text(title)).click();
        return page(Moto_Category_page_Logic.class);
    }

    @Step("check fourth link of bread crumbs .Moto_Product_page ")
    public Moto_Product_page_Logic checkFourthLinkOfBreadCrumbs(String title) {
        horizontalMotoSelector().shouldBe(visible);
        closeTooltipOfBrandField();
        breadCrumbsLinks().get(3).shouldBe(visible).shouldHave(text(title));
        return this;
    }


    @Step("check second link of bread crumbs with out SubRoute .Moto_Product_page ")
    public Moto_Product_page_Logic checkSecondLinkOfBreadCrumbsWithOutSubRoute(String title) {
        horizontalMotoSelector().shouldBe(visible);
        breadCrumbsLinks().get(1).shouldBe(visible).shouldHave(text(title)).shouldNotHave(attribute("href"));
        return this;
    }

    @Step("close tooltip at brand field in horizontal motorcycle selector .Moto_Product_page ")
    public Moto_Product_page_Logic closeTooltipOfBrandField() {
       if (btnCloseTooltipOfBrandFieldAtSelector().isDisplayed()) {
           btnCloseTooltipOfBrandFieldAtSelector().click();
       }
        return this;
    }

}