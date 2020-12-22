package AWS;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.testng.Assert;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.*;

public class CategoriesSynonyms_aws {

    public String urlWithSynonymTurboAndShopDE = "https://aws.autodoc.de/categories/synonyms?id=&name=&syn_name=turbo&pageId=&lang%5B%5D=de&search=";

    public String synonymsPage = "https://aws.autodoc.de/categories/synonyms";

    private By genericsInSearchTable = byXpath("(//table[4]//td[@class='name'])[position()>1]");

    private SelenideElement synonymByGenericFromSearchBlock(String generic) {
        return $x("//table[4]//td[@class='name' and text()='" + generic + "']/..//div[@class='synon']/span");
    }

    SelenideElement genericName(String genericId) {
        return $x("//table[4]//td[@class='id' and text()='" + genericId + "']/..//td[@class='name']");
    }

    ElementsCollection genericSynonyms(String genericId) {
        return $$x("//table[4]//td[@class='id' and text()='" + genericId + "']/..//div[@class='synon']/span");
    }

    private SelenideElement loginField() {
        return $(byId("login"));
    }

    private SelenideElement categoryField() {
        return $(byId("form_name"));
    }

    private SelenideElement languageField() {
        return $x("//div[@id='form_lang_chzn']//input");
    }

    private SelenideElement btnSearch() {
        return $(byName("search"));
    }

    private SelenideElement searchTable() {
        return $x("//h2[contains(text(),'Search')]/preceding-sibling::table[1]/following-sibling::table");
    }

    private ElementsCollection synonymsFromSearchTable(String text) {
        return $$x("(//h2[contains(text(),'Search')]/preceding-sibling::table[1]/following-sibling::table//td[text()='" + text + "'])[1]//following-sibling::td[@class='vlock_syn']//div[@class='synon']/span");
    }

    @Step
    public String getRandomGenericFromSearchBlock() {
        $(genericsInSearchTable).shouldBe(visible);
        ElementsCollection coll = $$(genericsInSearchTable);
        String synonym = coll.get((int) (Math.random() * coll.size())).getText();
        return synonym;
    }

    @Step
    public String getSynonymByGenericInSearchBlock(String generic) {
        return synonymByGenericFromSearchBlock(generic).getText();
    }

    @Step("Get generic name from AWS. CategoriesSynonyms_aws")
    public String getGenericNameFromAWS(String genericId) {
        return genericName(genericId).text();
    }

    @Step("Get generic synonyms from AWS. CategoriesSynonyms_aws")
    public ArrayList<String> getGenericSynonymsAWS(String genericId) {
        ArrayList<String> arrayListSynonyms = new ArrayList();
        for (int i = 0; i < genericSynonyms(genericId).size(); i++) {
            arrayListSynonyms.add(genericSynonyms(genericId).get(i).text());
        }
        return arrayListSynonyms;
    }

    @Step("open synonyms page in aws")
    public CategoriesSynonyms_aws openSynonymsPageInAws() {
        open(synonymsPage);
        if (loginField().isDisplayed()) {
            new Login_aws().loginInAws();
        }
        return this;
    }

    @Step("check generics. CategoriesSynonyms_aws")
    public CategoriesSynonyms_aws checkGenerics(List<String> generics, List<String> synonyms) {
        setLanguage("de");
        List<String> checkingSynonyms = new ArrayList<>();
        for (int i = 0; i < generics.size(); i++) {
            setCategoriesName(generics.get(i));
            searchTable().shouldBe(visible);
            checkingSynonyms = synonymsFromSearchTable(generics.get(i)).stream().map(n -> n.getText()).collect(Collectors.toList());
            for (int j = 0; j < checkingSynonyms.size(); j++) {
                Assert.assertFalse(synonyms.contains(checkingSynonyms.get(j)));
            }
            checkingSynonyms.clear();
        }
        return this;
    }

    @Step("set categories name. CategoriesSynonyms_aws")
    public CategoriesSynonyms_aws setCategoriesName(String category) {
        categoryField().shouldBe(visible).setValue(category).pressEnter();
        return this;
    }

    @Step("set language. CategoriesSynonyms_aws")
    public CategoriesSynonyms_aws setLanguage(String language) {
        languageField().shouldBe(visible).setValue(language).pressEnter();
        return this;
    }
}

