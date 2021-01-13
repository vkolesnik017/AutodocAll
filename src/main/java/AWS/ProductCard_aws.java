package AWS;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.testng.Assert;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static com.codeborne.selenide.CollectionCondition.sizeGreaterThanOrEqual;
import static com.codeborne.selenide.CollectionCondition.sizeNotEqual;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.byId;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.WebDriverRunner.url;

public class ProductCard_aws {
    private SelenideElement loginField() {
        return $(byId("login"));
    }

    private SelenideElement truckLabel() {
        return $x("//td/span[contains(text(),'LKW')]");
    }

    private SelenideElement truckCheckBox() {
        return $x("//td/span[contains(text(),'LKW')]/../following-sibling::td/input[@checked='checked']");
    }

    private SelenideElement vehicleLabel() {
        return $x("//td/span[contains(text(),'PKW')]");
    }

    private SelenideElement vehicleCheckBox() {
        return $x("//td/span[contains(text(),'PKW')]/../following-sibling::td/input[@checked='checked']");
    }

    private SelenideElement motoLabel() {
        return $x("//td/span[contains(text(),'MOTO')]");
    }

    private SelenideElement motoCheckBox() {
        return $x("//td/span[contains(text(),'MOTO')]/../following-sibling::td/input");
    }

    private SelenideElement universalApplicabilityCheckbox() {
        return $(".select-all-applicability");
    }

    private SelenideElement pkwApplicabilityCheckbox() {
        return $x("//*[@class='label label-danger' and contains (text(),'PKW')]/../..//input");
    }

    SelenideElement dangerousIconOfProductBlock() {
        return $x("//div[@class='col-md-12 col-sm-12 clearfix']");
    }

    ElementsCollection iconIfDangerousProducts() {
        return $$x("//div[@class='col-md-12 col-sm-12 clearfix']//input[@checked='checked']/../img");
    }

    SelenideElement signalAttentionCheckBox() {
        return $x("//input[@value='isAttention']");
    }

    SelenideElement signalDangerousCheckBox() {
        return $x("//input[@value='isDanger']");
    }

    SelenideElement activeSwitchOfDangerousProduct() {
        return $x("//div[@class='col-md-6 col-sm-6'][2]//div[@class='switch-animate switch-on']/span[1]");
    }

    private SelenideElement artNumOfProduct() {
        return $x("//td[contains(text(),'Артикль №.:')]/following-sibling::td");
    }

    ElementsCollection activeHazardStatement() {
        return $$x("//ul[@class='chzn-choices']/li[@class='search-choice']/span");
    }

    private SelenideElement statusInArtNum() {
        return $x("//td[contains(text(),'Артикль №.:')]/following-sibling::td/div");
    }

    private SelenideElement productManufacturer() {
        return $x("//td[contains(text(),'Производитель:')]/following-sibling::td/a");
    }

    private SelenideElement eanOfProduct() {
        return $x("//td[contains(text(),'EAN:')]/following-sibling::td/a");
    }

    private SelenideElement priceNetto() {
        return $x("//td[contains(text(),'Цена закупки:')]/following-sibling::td");
    }

    private ElementsCollection passportManagementForLanguage() {
        return $$x("//tbody[@class='hazardPassportTable']/tr//td[2]");
    }

    private SelenideElement language(String lang) {
        return $x("//ul[@class='nav nav-tabs']/li/a[@data-lang-code='" + lang + "']");
    }

    private SelenideElement activeLanguageTab(String language) {
        return $x("//li[@class='active']/a[@data-lang-code='" + language + "']");
    }

    private ElementsCollection staticIdOfCharacteristics() {
        return $$x("//div[@id='de']//span[@class='param']/span[2][not(contains(text(),'-'))]/../../span[1]");
    }

    private ElementsCollection staticTitleOfCharacteristics() {
        return $$x("//div[@id='de']//span[@class='param']/span[2][not(contains(text(),'-'))]/../span[1]");
    }

    private ElementsCollection staticValuesOfCharacteristics() {
        return $$x("//div[@id='de']//span[@class='param']/span[2][not(contains(text(),'-'))]");
    }

    private SelenideElement languageBlock(String language) {
        return $(byId("" + language + ""));
    }

    private SelenideElement labelOfArtNum() {
        return $x("//td[contains(text(),'Артикль №.:')]/following-sibling::td/div");
    }

    private SelenideElement labelOfBrand() {
        return $x("//td[contains(text(),'Производитель:')]/following-sibling::td/div");
    }

    private SelenideElement labelTurnOnDangerousProduct() {
        return $x("//span[contains(text(),'Опасный товар включен')]/../../div[1]//span[@class='switch-left switch-mini switch-success']");
    }

    private ElementsCollection precautions() {
        return $$x("//div[@id='form_hazardTypes_2____chzn']/ul/li/span[not(contains(text(),'disabled'))]");
    }

    private ElementsCollection hazardStatementLabel() {
        return $$("#form_hazardTypes_1____chzn span");
    }

    private SelenideElement idCategory() {
        return $x("//td[contains(text(),'ID Категории:')]/following-sibling::td");
    }

    private SelenideElement descriptionTable() {
        return $x("//div[@class='tab-content col-md-4 col-sm-12 main-description']");
    }

    private ElementsCollection totalCountOfDescriptionTable() {
        return $$x("//div[@class='tab-content col-md-4 col-sm-12 main-description']//li");
    }

    String productId;

    public ProductCard_aws() {
    }

    public ProductCard_aws(String productId) {
        this.productId = productId;
    }

    public SelenideElement searchTextOnPage(String textForSearch) {
        return $x("//*[contains(text(),'" + textForSearch + "')]");
    }


    @Step("checking quantity Language in passport management block. ProductCard_aws")
    public ProductCard_aws quantityLanguageInPassportManagement() {
        passportManagementForLanguage().shouldHaveSize(16);
        return this;
    }

    @Step("get all active hazard statement. ProductCard_aws")
    public ArrayList<String> getAllActiveHazardStatement() {
        ArrayList<String> allHazardStatement = new ArrayList<>();
        for (int i = 0; i < activeHazardStatement().size(); i++) {
            String hazardStatement = activeHazardStatement().get(i).shouldBe(visible).getText();
            if (!hazardStatement.contains("disabled")) {
                allHazardStatement.add(hazardStatement);
            }
        }
        return allHazardStatement;
    }

    @Step("get current Url")
    public String currentUrl() {
        String url = "https://aws.autodoc.de/products/view/" + productId + "";
        return url;
    }

    @Step(" open product card page")
    public ProductCard_aws openProductCardPageAndLogin() {
        open(currentUrl());
        if (loginField().isDisplayed()) {
            new Login_aws().loginInAws();
        }
        return this;
    }

    @Step("Check alternatives in AWS")
    public ProductCard_aws checkAlternativesInAws(ArrayList articlesToCheck) {
        new Login_aws().loginInAwsWithOpen();
        open("https://aws.autodoc.de/products/view/7868162");
        for (int i = 0; i < articlesToCheck.size(); i++) {
            searchTextOnPage(articlesToCheck.get(i).toString()).shouldBe(visible);
        }
        return this;
    }

    @Step("check truck label")
    public ProductCard_aws checkTruckLabel() {
        truckLabel().shouldBe(visible);
        truckCheckBox().shouldBe(visible);
        return this;
    }

    @Step("check vehicle label. ProductCard_aws")
    public ProductCard_aws checkVehicleLabel() {
        vehicleLabel().shouldBe(visible);
        vehicleCheckBox().shouldBe(visible);
        return this;
    }

    @Step("check motorcycle label. ProductCard_aws")
    public ProductCard_aws checkMotoLabel() {
        motoLabel().shouldBe(visible);
        motoCheckBox().shouldHave(attribute("checked", "checked"));
        return this;
    }

    @Step("Check universal applicability of product. ProductCard_aws")
    public boolean checkUniversalApplicabilityOfProduct() {
        return universalApplicabilityCheckbox().is(selected);
    }

    @Step("Check PKW applicability of product. ProductCard_aws")
    public ProductCard_aws checkPKWApplicabilityOfProduct() {
        pkwApplicabilityCheckbox().shouldBe(selected);
        return this;
    }

    @Step("Check universal or PKW applicability. ProductCard_aws")
    public ProductCard_aws checkUniversalOrPKWApplicability() {
        if (!checkUniversalApplicabilityOfProduct()) {
            checkPKWApplicabilityOfProduct();
        }
        return this;
    }

    @Step("presence of dangerous icon block. ProductCard_aws")
    public ProductCard_aws presenceOfDangerousIconBlock() {
        dangerousIconOfProductBlock().shouldBe(visible);
        return this;
    }

    @Step("compare elements of Dangerous product. ProductCard_aws")
    public ProductCard_aws compareElementsOfDangerousProduct(List<String> listOfDangerousIconFromProduct, String signalWord) {
        List<String> dangerousIconFromAws = new ArrayList<>();
        if (signalWord.toUpperCase().equals("ACHTUNG!")) {
            signalAttentionCheckBox().shouldHave(attribute("checked", "true"));
        } else if (signalWord.toUpperCase().equals("GEFAHR!")) {
            signalDangerousCheckBox().shouldHave(attribute("checked", "true"));
        }
        for (int i = 0; i < iconIfDangerousProducts().size(); i++) {
            String attFromImage = iconIfDangerousProducts().get(i).getAttribute("src").replace("pkwteile", "autodoc");
            String partOfAtt = attFromImage.replace(attFromImage.substring(attFromImage.lastIndexOf(".")), "");
            dangerousIconFromAws.add(partOfAtt);
        }

        Assert.assertEquals(dangerousIconFromAws, listOfDangerousIconFromProduct);
        activeSwitchOfDangerousProduct().shouldBe(exist).shouldHave(text("ON"));
        return this;
    }

    @Step("get article number of product. ProductCard_aws")
    public String getArtNumOfProduct() {
        String artNum = artNumOfProduct().shouldBe(visible).getText().replace(statusInArtNum().getText(), "").replace(" ", "");
        return artNum;
    }

    @Step("get id of product. ProductCard_aws")
    public String getIdOfProduct() {
        String id = url().replaceAll(".+\\/", "");
        return id;
    }

    @Step("get id of category. ProductCard_aws")
    public String getCategoryId() {
        String id = idCategory().getText().replaceAll("\\s.+", "");
        return id;
    }

    @Step("get title of product brand. ProductCard_aws")
    public String getTitleOfBrandProduct() {
        String titleOfBrand = productManufacturer().shouldBe(visible).getText().toLowerCase();
        return titleOfBrand;
    }

    @Step("get EAN of product. ProductCard_aws")
    public String getEanOfProduct() {
        String eanOfProduct = eanOfProduct().shouldBe(visible).getText();
        return eanOfProduct;
    }

    @Step("get price netto. ProductCard_aws")
    public Double getPriceNetto() {
        return Double.parseDouble(priceNetto().shouldBe(visible).getText());
    }

    @Step("set language. ProductCard_aws")
    public ProductCard_aws setLanguage(String expectedLanguage) {
        language(expectedLanguage).shouldBe(exist).scrollIntoView("{block: \"center\"}").click();
        activeLanguageTab(expectedLanguage).shouldBe(visible);
        return this;
    }

    @Step("presence of language block. ProductCard_aws")
    public ProductCard_aws presenceOfLanguageBlock(String expectedLanguage) {
        languageBlock(expectedLanguage).shouldBe(visible).scrollIntoView("{block: \"center\"}");
        return this;
    }

    @Step("get of matching options. ProductCard_aws")
    public List<String> getOfMatchingOptions(List<String> id) {
        List<String> matchingOptions = new ArrayList<>();
        for (int i = 0; i < id.size(); i++) {
            for (int j = 0; j < staticIdOfCharacteristics().size(); j++) {
                if (id.get(i).equals(staticIdOfCharacteristics().get(j).getText())) {
                    matchingOptions.add(staticTitleOfCharacteristics().get(j).getText());
                }
            }
        }
        return matchingOptions;
    }

    @Step("get values of matching options. ProductCard_aws")
    public List<String> getValuesOfMatchingOptions(List<String> id) {
        List<String> matchingOptions = new ArrayList<>();
        for (int i = 0; i < id.size(); i++) {
            for (int j = 0; j < staticIdOfCharacteristics().size(); j++) {
                if (id.get(i).equals(staticIdOfCharacteristics().get(j).getText())) {
                    matchingOptions.add(staticValuesOfCharacteristics().get(j).getText());
                }
            }
        }
        return matchingOptions;
    }

    @Step("get active label by article number and brand. ProductCard_aws")
    public boolean getActiveLabelByArtNumAndBrand() {
        artNumOfProduct().shouldBe(visible);
        boolean label = labelOfArtNum().getText().equals("Включен") && labelOfBrand().getText().equals("Включен") ? true : false;
        return label;
    }

    @Step("displaying of Turn on of dangerous product. ProductCard_aws")
    public ProductCard_aws displayOfTurnOnOfDangerousProduct() {
        labelTurnOnDangerousProduct().shouldBe(exist);
        return this;
    }

    @Step("check of not selected dangerous words. ProductCard_aws")
    public ProductCard_aws emptyDangerousLabel() {
        signalAttentionCheckBox().shouldNotHave(attribute("checked", "true"));
        signalDangerousCheckBox().shouldNotHave(attribute("checked", "true"));
        return this;
    }

    @Step("get dangerous pictogram from AWS. ProductCard_aws")
    public List<String> getDangerousPictogram() {
        List<String> dangerousIcon = new ArrayList<>();
        iconIfDangerousProducts().shouldHave(sizeGreaterThanOrEqual(1));
        for (int i = 0; i < iconIfDangerousProducts().size(); i++) {
            String attFromImage = iconIfDangerousProducts().get(i).getAttribute("src").replace("pkwteile", "autodoc");
            String partOfAtt = attFromImage.replace(attFromImage.substring(attFromImage.lastIndexOf(".")), "");
            dangerousIcon.add(partOfAtt);
        }
        return dangerousIcon;
    }

    @Step("get hazard statement. ProductCard_aws")
    public List<String> getStatementLabels() {
        List<String> hazardStatement = new ArrayList<>();
        hazardStatement = hazardStatementLabel().stream().map(n -> n.getText()).collect(Collectors.toList());
        for (int i = 0; i < precautions().size(); i++) {
            hazardStatement.add(precautions().get(i).getText());
        }
        Assert.assertTrue(hazardStatement.size() > 1);
        return hazardStatement;
    }

    @Step("check count of selected dangerous pictogram. ProductCard_aws")
    public ProductCard_aws checkCountOfSelectedPictogram(int expectedCount) {
        iconIfDangerousProducts().shouldHaveSize(expectedCount);
        return this;
    }

    @Step("selected signal dangerous checkbox. ProductCard_aws")
    public ProductCard_aws selectedSignalDangerousCheckBox() {
        signalAttentionCheckBox().shouldHave(attribute("checked", "true"));
        return this;
    }

    @Step("get count Of Description Values. ProductCard_aws")
    public int getCountOfDescriptionValues() {
        descriptionTable().shouldBe(visible);
        return totalCountOfDescriptionTable().size();
    }

    @Step("wait Of Change Count Of Description Values. ProductCard_aws")
    public ProductCard_aws waitOfChangeCountOfDescriptionValues(int size) {
        totalCountOfDescriptionTable().shouldHave(sizeNotEqual(size));
        return this;
    }
}
