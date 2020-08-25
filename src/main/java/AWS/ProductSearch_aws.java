package AWS;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import java.util.ArrayList;
import java.util.List;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.byId;
import static com.codeborne.selenide.Selectors.byXpath;
import static com.codeborne.selenide.Selenide.*;

public class ProductSearch_aws {

    public final String urlPage = "https://aws.autodoc.de/products/search";

    private SelenideElement dangerousCargoSelector() {
        return $(byId("form_filterSearch[hazardEnabled]"));
    }

    private SelenideElement inStockSelector() {
        return $(byId("form_filterSearch[onStorage]"));
    }

    private SelenideElement searchBtn() {
        return $(byId("form_submit"));
    }

    private SelenideElement idProductsInTable() {
        return $(byXpath("//*[@id='order_products_list']//tr/td[1]"));
    }

    private SelenideElement dangerousProductsColumn() {
        return $(byXpath("//*[@id='order_products_list']//tr/td[22]"));
    }

    private SelenideElement illiquidColumn() {
        return $(byId("form_filterSearch[isRock]"));
    }

    private ElementsCollection articleProductsInTable() {
        return $$(byXpath("//*[@id='order_products_list']//tr/td[3]"));
    }

    private SelenideElement brandProductsInTable() {
        return $(byXpath("//*[@id='order_products_list']//tr/td[4]"));
    }

    private SelenideElement searchField() {
        return $(byId("form_filterSearch[search]"));
    }

    @Step
    public ProductSearch_aws openProductSearchPageAndLogin() {
        open(urlPage);
        new Login_aws().loginInAws();
        return this;
    }

    @Step
    public String chooseIlliquidProductAndGetId() {
        illiquidColumn().selectOption(1);
        searchBtn().click();
//        illiquidColumn().shouldHave(text("Да"));
        illiquidColumn().shouldHave(value("yes"));
        String id = idProductsInTable().getText();
        String brand = brandProductsInTable().getText().trim().toLowerCase().replaceAll("[^a-z]", "");
        System.out.println(id);
        System.out.println(brand);
        return id + "#" + brand;
    }

    @Step
    public String chooseFilterForDangerousProductsAndGetId() {
        dangerousCargoSelector().selectOption(2); // switched on
        inStockSelector().selectOption(1); // yes
        searchBtn().click();
        dangerousProductsColumn().shouldHave(text("yes"));
        return idProductsInTable().getText();
    }

    @Step("get article number of product from aws table")
    public List<String> getArtNumberOfProduct() {
        List<String> artNumOfProduct = new ArrayList<>();
        for (SelenideElement e : articleProductsInTable()) {
            artNumOfProduct.add(e.getText());
        }
        return artNumOfProduct;
    }

    @Step("input MPN number in search field")
    public ProductSearch_aws inputMpnNumberOfProduct(String mpnNumber) {
        dangerousCargoSelector().shouldBe(visible);
        searchField().shouldBe(visible).setValue(mpnNumber);
        searchBtn().click();
        articleProductsInTable().get(0).shouldHave(exactText(mpnNumber));
        return this;
    }
}
