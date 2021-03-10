package AWS;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.testng.Assert;

import java.util.ArrayList;

import static ATD.CommonMethods.openPage;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;

public class OrderAdd_page_aws {

    public static String addOrderPageURL = "https://aws.autodoc.de/order/add";

    private SelenideElement fieldCustomerID() {
        return $x("//input[@id='Order[CustomerID]']");
    }

    private SelenideElement selectorForSkin() {
        return $x("//div[@id='form_Order_orderProjectId__chzn']");
    }

    private SelenideElement listSkins(String skin) {
        return $x("//div[@class='chzn-drop']//ul//li[text()='" + skin + "']");
    }

    private SelenideElement searchBoxBySkin() {
        return $x("//div[@class='chzn-search']");
    }

    private SelenideElement fieldNameInBilling() {
        return $x("//input[@id='Order[rVorname]']");
    }

    private SelenideElement fieldSurnameInBilling() {
        return $x("//input[@id='Order[rName]']");
    }

    private SelenideElement fieldStreetInBilling() {
        return $x("//input[@id='Order[rStrasse]']");
    }

    private SelenideElement fieldHouseNumberInBilling() {
        return $x("//input[@id='Order[payment_house]']");
    }

    private SelenideElement fieldPostcodeInBilling() {
        return $x("//input[@id='Order[rPlz]']");
    }

    private SelenideElement fieldCityInBilling() {
        return $x("//input[@id='Order[rOrt]']");
    }

    private SelenideElement fieldCountryInBilling() {
        return $x("//select[@id='form_Order[payment_country_id]']");
    }

    private SelenideElement fieldMailInBilling() {
        return $x("//input[@id='Order[Email]']");
    }

    private SelenideElement fieldPhoneInBilling() {
        return $x("//input[@id='Order[rTelefon]']");
    }

    private SelenideElement fieldNameInDeliveryAddress() {
        return $x("//input[@id='Order[lVorname]']");
    }

    private SelenideElement fieldSurnameInDeliveryAddress() {
        return $x("//input[@id='Order[lName]']");
    }

    private SelenideElement fieldStreetInDeliveryAddress() {
        return $x("//input[@id='Order[lStrasse]']");
    }

    private SelenideElement fieldHouseNumberInDeliveryAddress() {
        return $x("//input[@id='Order[delivery_house]']");
    }

    private SelenideElement fieldPostcodeInDeliveryAddress() {
        return $x("//input[@id='form_Order[lPlz]']");
    }

    private SelenideElement fieldCityInDeliveryAddress() {
        return $x("//input[@id='Order[lOrt]']");
    }

    private SelenideElement fieldCountryInDeliveryAddress() {
        return $x("//select[@id='form_Order[delivery_country_id]']");
    }

    private SelenideElement choosesCountryInDeliveryAddress(String country) {
        return $x("//select[@id='form_Order[delivery_country_id]']/option[contains(text(),'" + country + "')]");
    }

    private SelenideElement fieldPhoneInDeliveryAddress() {
        return $x("//input[@id='Order[lTelefon]']");
    }

    private SelenideElement paymentMethod() {
        return $x("//select[@id='form_Order[PaymentID]']");
    }

    private SelenideElement deliveryMethod() {
        return $x("//select[@id='form_Order[DeliveryID]']");
    }

    private SelenideElement SafeOrder() {
        return $x("//select[@name='securityDeliveryStatusChange']");
    }

    private SelenideElement productSearchField() {
        return $(".search-products");
    }

    private SelenideElement btnAddProduct() {
        return $(".btn-add-product");
    }

    private SelenideElement tableOfSuppliers() {
        return $(".contains-suppliers");
    }

    private SelenideElement appendBlock() {
        return $x("//div[@class='w-box append-block']");
    }

    private SelenideElement radioBtnSuppliers() {
        return $x("//input[@value='suppliers']");
    }

    private SelenideElement radioBtnStorage() {
        return $x("//input[@value='storage']");
    }

    private SelenideElement tableOfStock() {
        return $x("//table[@class='table table-bordered contains-storage']");
    }

    private SelenideElement btnSelect() {
        return $(".prices-select");
    }

    public SelenideElement btnChooseProduct() {
        return $(".btn-change-product");
    }

    private ElementsCollection articleOfAddedProduct() {
        return $$x("//tr[@class='targerProduct ']//td[2]");
    }

    private SelenideElement deliveryCost() {
        return $x("//input[@id='Order[DeliveryCost]']");
    }

    private SelenideElement safeOrderCost() {
        return $x("//input[@id='isSecurityDelivery']");
    }

    private SelenideElement saveOrderBtn() {
        return $(".submit-order");
    }

    private SelenideElement productArticleID(String artID) {
        return $x("//input[@name='append_article_id'][@value='" + artID + "']");
    }

    private SelenideElement foundProductsBlock() {
        return $x("//div[@class='w-box append-block']//table[@class='table table-bordered']");
    }

    public SelenideElement productArticleID() {
        return $x("//input[@name='append_article_id']");
    }

    private SelenideElement popUpWithDeliveryError() {
        return $x("//div[@class='sticky-note']");
    }

    private SelenideElement tableWithAddedProducts() {
        return $x("(//div[@class='w-box'])[5]");
    }

    private SelenideElement deliveryCountrySelector(String country) {
        return $x("//select[@name='Order[delivery_country_id]']//option[text()='" + country + "']");
    }

    private SelenideElement checkBoxHerrInBillingBlock() {
        return $x("//div[@id='billing_shipping_data']//div[@class='form-group'][1]/label[2]/input");
    }

    private SelenideElement preLoader() {
        return $x("//div[@class='block-msg order-view order-processing']");
    }


    @Step("Click checkbox Herr in Billing block. OrderAdd_page_aws")
    public OrderAdd_page_aws clickCheckboxHerrInBilling() {
        checkBoxHerrInBillingBlock().shouldBe(visible).click();
        return this;
    }

    @Step("Open add order page. OrderAdd_page_aws")
    public OrderAdd_page_aws openAddOrderPageWithLogin() {
        openPage(addOrderPageURL);
        new Login_aws().loginInAws();
        return this;
    }

    @Step("Checking correct text in input field. OrderAdd_page_aws")
    private void checkCorrectTextAndFillInput(SelenideElement element, String correctText) {
        Configuration.fastSetValue = false;
        if (!element.getValue().equals(correctText)) {
            element.clear();
            element.setValue(correctText);
        }
    }

    @Step("Filling postal code {postalCodeOrCode} in block delivery address. OrderAdd_page_aws")
    public OrderAdd_page_aws fillingPostalCodeInBlockDeliveryAddress (String postalCodeOrCode) {
        checkCorrectTextAndFillInput(fieldPostcodeInDeliveryAddress(), postalCodeOrCode);
        return this;
    }

    @Step("Chooses delivery country {country}. OrderAdd_page_aws")
    public OrderAdd_page_aws choosesDeliveryCountry(String country) {
        deliveryCountrySelector(country).click();
        return this;
    }

    @Step("Click save order button. OrderAdd_page_aws")
    public Order_aws clickSaveOrderBtn() {
        if (preLoader().isDisplayed()) {
            preLoader().waitUntil(attribute("style", "display: none;"), 60000);
        }
        saveOrderBtn().click();
        if (preLoader().isDisplayed()) {
            preLoader().waitUntil(attribute("style", "display: none;"), 60000);
        }
        sleep(5000);
        new Order_aws().checkOrderHasTestPhone();
        return page(Order_aws.class);
    }

    @Step("Click save order button. OrderAdd_page_aws")
    public Order_aws clickSaveOrderBtnAndCheckThatThisAccountInBlackList() {
        if (preLoader().isDisplayed()) {
            preLoader().waitUntil(attribute("style", "display: none;"), 60000);
        }
        saveOrderBtn().click();
        if (preLoader().isDisplayed()) {
            preLoader().waitUntil(attribute("style", "display: none;"), 60000);
        }
        sleep(5000);
        new Order_aws().checkInfoOrderAccountInBlackList();
        return page(Order_aws.class);
    }

    @Step("Get delivery cost. OrderAdd_page_aws")
    public Float getDeliveryCost() {
        sleep(2000);
        String deliveryCost = deliveryCost().getAttribute("data-default");
        return Float.valueOf(deliveryCost);
    }

    @Step("Get safe order cost. OrderAdd_page_aws")
    public Float getSafeOrderCost() {
        String safeOrderCost = safeOrderCost().getValue();
        return Float.valueOf(safeOrderCost);
    }

    @Step("Added product {articleNum}. OrderAdd_page_aws")
    public OrderAdd_page_aws addProduct(String articleNum) {
        productSearchField().clear();
        productSearchField().shouldBe(visible).sendKeys(articleNum);
        btnAddProduct().shouldBe(visible).click();
        return this;
    }

    @Step("Checks presence table of suppliers and click button Select. OrderAdd_page_aws")
    public OrderAdd_page_aws checkPresenceTableOfSuppliersAndClickBtnSelect() {
        if (preLoader().isDisplayed()) {
            preLoader().waitUntil(attribute("style", "display: none;"), 30000);
        }
        appendBlock().waitUntil(visible, 3000);
        if (tableOfStock().isDisplayed()){
            radioBtnStorage().click();
        } else {
            tableOfSuppliers().shouldBe(visible);
            radioBtnSuppliers().click();
        }
        btnSelect().scrollIntoView("{block: \"center\"}").click();
        return this;
    }

    @Step("Chooses the article id of the desired product {artID} and click button choose product. OrderAdd_page_aws")
    public OrderAdd_page_aws chooseArticleIDOfDesiredProductAndClickBtnChooseProduct(String artID) {
        if (preLoader().isDisplayed()) {
            preLoader().waitUntil(attribute("style", "display: none;"), 20000);
        }
        sleep(3000);
        if (foundProductsBlock().isDisplayed()) {
            productArticleID(artID).shouldBe(visible);
            productArticleID(artID).click();
            btnChooseProduct().shouldBe(visible);
            btnChooseProduct().click();
        }
        return this;
    }

    @Step("Chooses the any article id of the desired product and click button choose product. OrderAdd_page_aws")
    public OrderAdd_page_aws chooseAnyArticleIDOfDesiredProductAndClickBtnChooseProduct() {
        if (preLoader().isDisplayed()) {
            preLoader().waitUntil(attribute("style", "display: none;"), 20000);
        }
        productArticleID().click();
        btnChooseProduct().click();
        return this;
    }

    @Step("Checks article number of added product {articleNum}. OrderAdd_page_aws")
    public OrderAdd_page_aws checkArticleOfAddedProduct(String articleNum) {
        if (preLoader().isDisplayed()) {
            preLoader().waitUntil(attribute("style", "display: none;"), 50000);
        }
        tableWithAddedProducts().shouldBe(visible);
        sleep(2000);
        ArrayList <String> list = new ArrayList<>();
        for (int i = 0; i < articleOfAddedProduct().size(); i++) {
            articleOfAddedProduct().get(i).shouldBe(visible);
            list.add(articleOfAddedProduct().get(i).getText());
        }
        Assert.assertTrue(list.contains(articleNum));
        return this;
    }

    @Step("Selected payment expected method {expectedMethod}. OrderAdd_page_aws")
    public OrderAdd_page_aws selectedPaymentMethod(String expectedMethod) {
        paymentMethod().selectOptionContainingText(expectedMethod);
        return this;
    }

    @Step("Selected credit card method {expectedMethod}. OrderAdd_page_aws")
    public OrderAdd_page_aws selectedCreditCardMethod(String shop, String expectedMethod) {
        if (shop.equals("SE")) {
            paymentMethod().selectOptionContainingText("Be2bill[SEK] (Visa/MC)");
        } else {
            paymentMethod().selectOptionContainingText(expectedMethod);
        }
        return this;
    }

    @Step("Selected bank method {expectedMethod}. OrderAdd_page_aws")
    public OrderAdd_page_aws selectedBankMethod(String shop, String expectedMethod) {
        if (shop.equals("EN")) {
            paymentMethod().selectOptionContainingText("UniCredit Bank GB");
        } else {
            paymentMethod().selectOptionContainingText(expectedMethod);
        }
        return this;
    }

    @Step("Selected delivery expectedMethod {expectedMethod}. OrderAdd_page_aws")
    public OrderAdd_page_aws selectedDeliveryMethod(String expectedMethod) {
        deliveryMethod().selectOptionContainingText(expectedMethod);
        return this;
    }

    @Step("Selected status in safe order {status}. OrderAdd_page_aws")
    public OrderAdd_page_aws selectedStatusInSafeOrder(String status) {
        SafeOrder().selectOptionContainingText(status);
        return this;
    }


    @Step("Gets user data. OrderAdd_page_aws")
    public ArrayList getUserDataInOrder() {
        String nameText = fieldNameInBilling().getValue();
        String surnameText = fieldSurnameInBilling().getValue();
        String streetText = fieldStreetInBilling().getValue();
        String houseNumberText = fieldHouseNumberInBilling().getValue();
        String postcodeText = fieldPostcodeInBilling().getValue();
        String cityText = fieldCityInBilling().getValue();
        String countryText = fieldCountryInBilling().getText();
        String mailText = fieldMailInBilling().getValue();
        String phoneText = fieldPhoneInBilling().getValue();
        String nameDeliveryAddressText = fieldNameInDeliveryAddress().getValue();
        String surnameDeliveryAddressText = fieldSurnameInDeliveryAddress().getValue();
        String streetDeliveryAddressText = fieldStreetInDeliveryAddress().getValue();
        String houseDeliveryAddressNumberText = fieldHouseNumberInDeliveryAddress().getValue();
        String postcodeDeliveryAddressText = fieldPostcodeInDeliveryAddress().getValue();
        String cityDeliveryAddressText = fieldCityInDeliveryAddress().getValue();
        String countryDeliveryAddressText = fieldCountryInDeliveryAddress().getText();
        String phoneDeliveryAddressText = fieldPhoneInDeliveryAddress().getValue();
        ArrayList <String> userData = new ArrayList<>();
        userData.add(nameText);
        userData.add(surnameText);
        userData.add(streetText);
        userData.add(houseNumberText);
        userData.add(postcodeText);
        userData.add(cityText);
        userData.add(countryText);
        userData.add(mailText);
        userData.add(phoneText);
        userData.add(nameDeliveryAddressText);
        userData.add(surnameDeliveryAddressText);
        userData.add(streetDeliveryAddressText);
        userData.add(houseDeliveryAddressNumberText);
        userData.add(postcodeDeliveryAddressText);
        userData.add(cityDeliveryAddressText);
        userData.add(countryDeliveryAddressText);
        userData.add(phoneDeliveryAddressText);
        return userData;
    }

    @Step("Fills in the field CustomerID {CustomerID}. OrderAdd_page_aws")
    public OrderAdd_page_aws fillsInFieldCustomerID(String CustomerID) {
        fieldCustomerID().sendKeys(CustomerID);
        return this;
    }

    @Step("Choosing skin in selector {skin}. OrderAdd_page_aws")
    public OrderAdd_page_aws chooseSkinInSelector(String skin) {
        selectorForSkin().click();
        sleep(2000);
        if (!searchBoxBySkin().isDisplayed()) {
            selectorForSkin().click();
        }
        listSkins(skin).click();
        return this;
    }

    @Step("Selects shop for skin PKW a specific country{shop}. OrderAdd_page_aws")
    public OrderAdd_page_aws selectShopForSkinPKWFopSpecificCountry(String shop) {
        selectorForSkin().click();
        sleep(2000);
        if (!searchBoxBySkin().isDisplayed()) {
            selectorForSkin().click();
        }
        switch (shop) {
            case "AT":
                listSkins("pkwteile.at (AT)").click();
                break;
            case "NL":
                listSkins("auto-onderdelen24.nl (NL)").click();
                break;
            case "DE":
                listSkins("pkwteile.de (DE)").click();
                break;
            case "FI":
                listSkins("autonvaraosat24.fi (FI)").click();
                break;
            case "SE":
                listSkins("bildelaronline24.se (SE)").click();
                break;
            case "ES":
                listSkins("recambioscoches.es (ES)").click();
                break;
            case "IT":
                listSkins("autoparti.it (IT)").click();
                break;
            case "PT":
                listSkins("autopecasonline24.pt (PT)").click();
        }
        return this;
    }

    @Step("Selects shop for skin ATD a specific country{shop}. OrderAdd_page_aws")
    public OrderAdd_page_aws selectShopForSkinATDFopSpecificCountry(String shop) {
        selectorForSkin().click();
        sleep(2000);
        if (!searchBoxBySkin().isDisplayed()) {
            selectorForSkin().click();
        }
        switch (shop) {
            case "EE":
                listSkins("autodoc.ee (EE)").click();
                break;
            case "LV":
                listSkins("autodoc.lv (LV)").click();
                break;
            case "LT":
                listSkins("autodoc.lt (LT)").click();
                break;
            case "SI":
                listSkins("autodoc.si (SI)").click();
                break;
            case "EN":
                listSkins("autodoc.co.uk (GB)").click();
                break;
        }
        return this;
    }

    @Step("Checks presence popup with delivery error. OrderAdd_page_aws")
    public OrderAdd_page_aws checkPresencePopupWithDeliveryError() {
        popUpWithDeliveryError().shouldBe(visible);
        return this;
    }

    @Step("Choosing delivery country {country} for sipping and get name country. OrderAdd_page_aws")
    public String chooseDeliveryCountryAndGetNameCountry(String country) {
        choosesCountryInDeliveryAddress(country).shouldBe(visible).click();
        return choosesCountryInDeliveryAddress(country).getText();
    }

    @Step("Choosing delivery country {expectedCountry}. OrderAdd_page_aws")
    public OrderAdd_page_aws chooseDeliveryCountry(String expectedCountry) {
        choosesCountryInDeliveryAddress(expectedCountry).shouldBe(visible).click();
        return this;
    }

    @Step("Choosing delivery country equal to the shop {expectedShop}. OrderAdd_page_aws")
    public OrderAdd_page_aws choosingDeliveryCountryEqualToShop(String expectedShop) {
        fieldCountryInDeliveryAddress().shouldBe(visible);
        switch (expectedShop) {
            case "EE":
                chooseDeliveryCountry("Estonia");
                break;
            case "LV":
                chooseDeliveryCountry("Latvia");
                break;
            case "LT":
                chooseDeliveryCountry("Lithuania");
                break;
            case "SI":
                chooseDeliveryCountry("Slovenia");
                break;
            case "EN":
                chooseDeliveryCountry("United Kingdom");
                break;
        }
        return this;
    }
}
