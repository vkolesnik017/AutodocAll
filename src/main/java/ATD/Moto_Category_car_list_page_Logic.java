package ATD;

import com.codeborne.selenide.CollectionCondition;
import io.qameta.allure.Step;
import org.testng.Assert;

import java.sql.SQLException;

import static com.codeborne.selenide.CollectionCondition.*;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.page;
import static com.codeborne.selenide.WebDriverRunner.url;

public class Moto_Category_car_list_page_Logic extends Moto_Category_car_list_page {

    @Step("visibility of TecDoc listing .Moto_Category_car_list_page")
    public Moto_Category_car_list_page_Logic visibilityOfTecDocListing(String textInHeadline) {
        headline().shouldNotHave(exactText(textInHeadline));
        tecDocListingBlock().shouldBe(visible);
        return this;
    }

    @Step(" selecting motorcycle in selector .Moto_Category_car_list_page")
    public Moto_Category_car_list_page_Logic selectMotoInSelector(String marke, String model, String motor) {
        brandOfMotoField().selectOptionByValue(marke);
        brandOfMotoField().shouldHave(exactValue(marke));
        modelFiledInSelector().selectOptionByValue(model);
        modelFiledInSelector().shouldHave(exactValue(model));
        motorFiledInSelector().selectOptionByValue(motor);
        motorFiledInSelector().shouldHave(exactValue(motor));
        searchButton().click();
        return page(Moto_Category_car_list_page_Logic.class);
    }

    @Step(" reset of motorcycle selector .Moto_Category_car_list_page")
    public Moto_Category_page_Logic resetOfMotoSelector() {
        btnResetOfSelector().shouldBe(visible).click();
        return page(Moto_Category_page_Logic.class);
    }

    @Step("check current url .Moto_Category_car_list_page")
    public Moto_Category_car_list_page_Logic checkCurrentUrl(String subRoute) throws SQLException {
        tecDocListingBlock().shouldBe(visible);
        DataBase db = new DataBase();
        Assert.assertEquals(url(), db.getFullRouteByRouteAndSubroute("subprod", "DE", "moto_main", subRoute));
        return this;
    }


    @Step("get text from headline .Moto_Category_car_list_page")
    public String getTextFromHeadline() {
        String textFromHeadline = headline().getText();
        return textFromHeadline;
    }

    @Step(" select motorcycle model in selector .Moto_Category_car_list_page")
    public Moto_Category_car_list_page_Logic selectMotoModel(String model) {
        brandOfMotoField().shouldNotHave(exactValue("0"));
        modelFiledInSelector().shouldBe(visible).selectOptionByValue(model);
        return this;
    }

    @Step(" select motorcycle motor in selector .Moto_Category_car_list_page")
    public Moto_Category_car_list_page_Logic selectMotoMotor(String motor) {
        motorFiledInSelector().shouldBe(visible).selectOptionByValue(motor);
        return this;
    }

    @Step(" click on Search button in selector .Moto_Category_car_list_page")
    public Moto_Category_car_list_page_Logic clickOnSearchButton() {
        searchButton().click();
        return this;
    }


    @Step("get amount count of products from TecDoc listing .Moto_Category_car_list_page")
    public double getAmountCountOfProducts() {
        double countOfProductsFromTecDoc = Double.parseDouble(countOfProduct().getText().replaceAll("[^0-9]", ""));
        return countOfProductsFromTecDoc;
    }


    @Step("check amount count of pages at TecDoc listing .Moto_Category_car_list_page")
    public Moto_Category_car_list_page_Logic checkCountOfPagesAtTecDocListing() {
        String url = lastForwardOfPagination().getAttribute("href");
        String urlPart = url.replace(url.substring(url.lastIndexOf("=")), "");
        int lastPage = Integer.parseInt(url.replace(urlPart, "").replaceAll("[^0-9]", ""));
        int countOfPagesFromAmountCountOfProducts = (int) Math.ceil(getAmountCountOfProducts() / 20);
        Assert.assertEquals(countOfPagesFromAmountCountOfProducts, lastPage);
        return this;
    }


    @Step(" check TecDoc listing on pages .Moto_Category_car_list_page")
    public Moto_Category_car_list_page_Logic checkTecDocListingOnPages() {
        presenceOfProductsAtTecDocListing();
        while (lastForwardOfPagination().isDisplayed()) {
            nextForwardOfPagination().click();
            presenceOfProductsAtTecDocListing();
        }
        return this;
    }

    @Step(" presence of products at TecDoc listing.Moto_Category_car_list_page")
    public Moto_Category_car_list_page_Logic presenceOfProductsAtTecDocListing() {
        productsAtTecDocListing().shouldHave(sizeGreaterThan(1));
        return this;
    }


    @Step(" presence of TecDoc listing block.Moto_Category_car_list_page")
    public Moto_Category_car_list_page_Logic presenceOfTecDocListingBlock() {
        tecDocListingBlock().shouldBe(visible);
        productsAtTecDocListing().shouldHave(sizeGreaterThan(1));
        return this;
    }

}
