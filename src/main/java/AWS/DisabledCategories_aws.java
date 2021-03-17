package AWS;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import java.util.List;
import java.util.stream.Collectors;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byId;
import static com.codeborne.selenide.Selectors.byName;
import static com.codeborne.selenide.Selenide.*;

public class DisabledCategories_aws {

    public String disabledCategoriesUrl = "https://aws.autodoc.de/products/disableCategories";

    private SelenideElement skinProjectField() {
        return $x("//ul[@class='chzn-choices']/li/input");
    }

    private SelenideElement brandField() {return $(byId("form_Filter[brandNo]"));}

    private SelenideElement categoryIdField() {return $(byId("form_Filter[genericArticleId]"));}

    private SelenideElement btnSearch() {return $x("//button[@class='btn btn-primary']");}

    private ElementsCollection projectLanguages() {return $$x("//table[@class='table table-striped table-bordered table-condensed orders']//tr/td[1]");}

    @Step("open Disabled Dangerous Product In Aws. DisabledCategories_aws")
    public DisabledCategories_aws openDisabledCategoriesInAwsWithLogin() {
        open(disabledCategoriesUrl);
        new Login_aws().loginInAws();
        return this;
    }

    @Step("set skin. DisabledCategories_aws")
    public DisabledCategories_aws setSkin(String skin) {
        skinProjectField().shouldBe(visible).setValue(skin).pressEnter();
        return this;
    }

    @Step("set Brand. DisabledCategories_aws")
    public DisabledCategories_aws setBrand(String numberOfBrand) {
        brandField().shouldBe(visible).selectOptionByValue(numberOfBrand);
        return this;
    }

    @Step("set Category Id. DisabledCategories_aws")
    public DisabledCategories_aws setCategoryId(String categoryId) {
        categoryIdField().shouldBe(visible).setValue(categoryId);
        return this;
    }

    @Step("click Search button. DisabledCategories_aws")
    public DisabledCategories_aws clickSearch() {
        btnSearch().click();
        return this;
    }

    @Step("get languages of project. DisabledCategories_aws")
    public List<String> getProjectLanguage() {
        List<String> languages = projectLanguages().stream().map(n->n.getText()).collect(Collectors.toList());
        return languages;
    }


}
