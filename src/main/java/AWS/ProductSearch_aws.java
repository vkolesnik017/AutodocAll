package AWS;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.testng.Assert;

import java.util.ArrayList;
import java.util.List;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.byId;
import static com.codeborne.selenide.Selectors.byXpath;
import static com.codeborne.selenide.Selenide.*;

public class
ProductSearch_aws {

    public final String urlPage = "https://aws.autodoc.de/products";

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
        return $(byXpath("//*[@id='order_products_list']//tr/td[23]"));
    }

    private SelenideElement illiquidColumn() {
        return $(byId("form_filterSearch[isRock]"));
    }

    private ElementsCollection articleProductsInTable() {
        return $$(byXpath("//*[@id='order_products_list']//tr/td[3]"));
    }

    private SelenideElement articleProductInTable() {
        return $(byXpath("//*[@id='order_products_list']//tr/td[3]"));
    }

    private SelenideElement brandProductsInTable() {
        return $(byXpath("//*[@id='order_products_list']//tr/td[4]"));
    }

    private SelenideElement searchField() {
        return $(byId("form_filterSearch[search]"));
    }

    private SelenideElement fieldSelectBrand() {
        return $x(" //li[@class='search-field']/input[@value='Select Brand']");
    }

    private SelenideElement categoryFiled() {
        return $x("//div[@id='form_filterSearch_categories__chzn']//input");
    }

    private SelenideElement firstSearchFilter() {
        return $(byId("form_filterSearch[isDisabled]"));
    }

    private SelenideElement secondSearchFilter() {
        return $(byId("form_filterSearch[isNotAvailable]"));
    }

    private SelenideElement sortingFilter() {
        return $(byId("form_filterSearch[sort]"));
    }

    private SelenideElement availabilityAtSupplierFilter() {
        return $(byId("form_filterSearch[inSuplierStocks]"));
    }

    private SelenideElement productListBlock() {
        return $(byId("order_products_list"));
    }

    private ElementsCollection titleOfProductsInTable() {
        return $$x("//table[@id='order_products_list']//td[6]/a");
    }

    private SelenideElement depositProductsInTable() {
        return $x("//*[@id='order_products_list']//tr/td[15]/span");
    }

    private ElementsCollection titlesOfProducts() {
        return $$x("//table[@id='order_products_list']//tr/td[6]/a");
    }

    private SelenideElement incomeAmountFilter() { return $x("//*[@id='form_filterSearch[incomeAmount]']");}

    private SelenideElement productSetFilter() { return $x("//*[@id='form_filterSearch[isKitEnabled]']");}

    private SelenideElement formControlFilter() { return  $x("//input[@id='form_filterSearch[siteAmount]']");}



    @Step
    public ProductSearch_aws openProductSearchPageAndLogin() {
        open(urlPage);
        new Login_aws().loginInAws();
        return this;
    }

    @Step("open product page with logged user")
    public ProductSearch_aws openProductSearchPage() {
        open(urlPage);
        return this;
    }

    @Step("Choose illiquid product and get id. ProductSearch_aws")
    public String chooseIlliquidProductAndGetId() {
        inStockSelector().selectOption(1); // yes
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

    @Step("Choose illiquid product and get article id. ProductSearch_aws")
    public String chooseIlliquidProductAndGetArticleId() {
        inStockSelector().selectOption(1); // yes
        illiquidColumn().selectOption(1);
        searchBtn().click();
        illiquidColumn().shouldHave(value("yes"));
        return articleProductInTable().getText();
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

    @Step("get article number of product from aws table")
    public String getArtNumberOfProduct(int position) {
        return articleProductsInTable().get(position).shouldBe(visible).getText();
    }

    @Step("input MPN number and name brand in search field")
    public ProductSearch_aws inputMpnNumberAndBrandNameOfProduct(String mpnNumber, String nameBrand) {
        dangerousCargoSelector().shouldBe(visible);
        searchField().shouldBe(visible).setValue(mpnNumber);
        fieldSelectBrand().shouldBe(visible).setValue(nameBrand).pressEnter();
        searchBtn().click();
        articleProductsInTable().get(0).shouldHave(exactText(mpnNumber));
        return this;
    }

    @Step("select category. ProductSearch_aws")
    public ProductSearch_aws selectCategory(String idOfCategory) {
        categoryFiled().shouldBe(visible).setValue(idOfCategory).pressEnter();
        return this;
    }

    @Step("select first search filter. ProductSearch_aws")
    public ProductSearch_aws selectFirstSearchFilter(String filer) {
        firstSearchFilter().selectOptionByValue(filer);
        firstSearchFilter().shouldHave(exactValue(filer));
        return this;
    }

    @Step("select second search filter. ProductSearch_aws")
    public ProductSearch_aws selectSecondSearchFilter(String filer) {
        secondSearchFilter().selectOptionByValue(filer);
        secondSearchFilter().shouldHave(exactValue(filer));
        return this;
    }

    @Step("set sorting filter. ProductSearch_aws")
    public ProductSearch_aws setSortingFilter(String filter) {
        sortingFilter().selectOptionByValue(filter);
        sortingFilter().shouldHave(exactValue(filter));
        return this;
    }

    @Step("select availability at supplier filter. ProductSearch_aws")
    public ProductSearch_aws selectAvailabilityAtSupplierFilter(String filer) {
        availabilityAtSupplierFilter().selectOptionByValue(filer);
        return this;
    }

    @Step("click on Search button. ProductSearch_aws")
    public ProductSearch_aws clickOnSearchButton() {
        searchBtn().shouldBe(visible).click();
        return this;
    }

    @Step("go to product cart by click on Title of product. ProductSearch_aws")
    public ProductCard_aws goToProductCartByClickOnTitle(int positionOfProduct) {
        //  productListBlock().waitWhile(not(visible),5000);
        //   productListBlock().shouldBe(visible);
        titleOfProductsInTable().get(positionOfProduct).shouldBe(visible).click();
        return page(ProductCard_aws.class);
    }

    @Step("Set value in Search field. ProductSearch_aws")
    public ProductSearch_aws setValueInSearchField(String value) {
        searchField().setValue(value);
        searchBtn().click();
        return this;
    }

    @Step("Get value from deposit field. ProductSearch_aws")
    public String getValueFromDepositField() {
        return depositProductsInTable().getText();
    }

    @Step("Checking whether a product has a deposit by its article number .ProductSearch_aws")
    public ProductSearch_aws checkingWhetherProductHasDepositByArtNumber(ArrayList artNumAndDeposit) {
        depositProductsInTable().waitUntil(visible, 10000);
        for (int a = 0; a < artNumAndDeposit.size(); a++) {
            String onlyArtNum = String.valueOf(artNumAndDeposit.get(a)).replaceAll("\\D+", "");
            String fullText = String.valueOf(artNumAndDeposit.get(a));
            setValueInSearchField(onlyArtNum);
            float deposit = Float.parseFloat(getValueFromDepositField());
            if (deposit == 0) {
                Assert.assertEquals(fullText, onlyArtNum + " " + "- No deposit");
            } else if (deposit > 0) {
                Assert.assertEquals(fullText, onlyArtNum + " " + "- Have deposit");
            }
        }
        return this;
    }

    @Step("get Title Of First Product. ProductSearch_aws")
    public String getTitleOfFirstProduct(int positionOfProduct) {
        return titlesOfProducts().get(positionOfProduct).shouldBe(visible).getText();
    }

    @Step("wait of change title of product. ProductSearch_aws")
    public ProductSearch_aws waitOfChangeTitleOfProduct(int positionOfProduct, String title) {
        titlesOfProducts().get(positionOfProduct).shouldBe(visible).shouldNotHave(text(title));
        return this;
    }

    @Step("set brand. ProductSearch_aws")
    public ProductSearch_aws setBrand(String brand) {
        fieldSelectBrand().shouldBe(visible).setValue(brand).pressEnter();
        return this;
    }

    @Step("click on Search button. ProductSearch_aws")
    public ProductSearch_aws clickSearch() {
        searchBtn().click();
        return this;
    }

    @Step("Select piece or set equipment. ProductSearch_aws")
    public ProductSearch_aws selectPieceOrSetEquipment(String filer) {
        incomeAmountFilter().selectOptionByValue(filer);
        incomeAmountFilter().shouldHave(exactValue(filer));
        return this;
    }

    @Step("Select products set filter. ProductSearch_aws")
    public ProductSearch_aws selectProductsSetFilter(String filer) {
        productSetFilter().selectOptionByValue(filer);
        productSetFilter().shouldHave(exactValue(filer));
        return this;
    }

    @Step("Get article product. ProductSearch_aws")
    public String getArticleProduct() {
       return articleProductInTable().getText();
    }

    @Step(" Set value in form Control Filter. ProductSearch_aws")
    public ProductSearch_aws setValueInFormControl(String value) {
        formControlFilter().shouldBe(visible).setValue(value);
        return this;
    }


}
