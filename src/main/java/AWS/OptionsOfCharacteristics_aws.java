package AWS;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import java.util.List;
import java.util.stream.Collectors;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byId;
import static com.codeborne.selenide.Selenide.*;

public class OptionsOfCharacteristics_aws {

    public String currencyOptionsPageURL = "https://aws.autodoc.de/products/description/options";

    private SelenideElement skinField() {
        return $x("//div[@id='form_skin_chzn']/a");
    }

    private SelenideElement dropMenuOfSkinField() {
        return $x("//div[@id='form_skin_chzn']//div[@class='chzn-drop']");
    }

    private SelenideElement atdSkin() {
        return $(byId("form_skin_chzn_o_5"));
    }

    private SelenideElement genericField() {
        return $x("//div[@id='form_ga_chzn']/a/span");
    }

    private SelenideElement searchGenericField() {
        return $x("//div[@id='form_ga_chzn']//input");
    }

    private SelenideElement idOfGeneric(String id) {
        return $(byId("form_ga_chzn_o_" + id + ""));
    }

    private SelenideElement btnSearch() {
        return $x("//button[@class='btn btn-success search']");
    }

    private ElementsCollection idFromMainOptionsColumn() {return $$x("//td[@class='w-400']//tr//td[2]/input[@data-old-value='1']/ancestor::tr/td[@class='w-40']");}

    @Step("open alternative categories page in aws. OptionsOfCharacteristics_aws")
    public OptionsOfCharacteristics_aws openOptionsOfCharacteristicsInAwsWithLogin() {
        open(currencyOptionsPageURL);
        new Login_aws().loginInAws();
        return this;
    }

    @Step("select Skin. OptionsOfCharacteristics_aws")
    public OptionsOfCharacteristics_aws setSkin() {
        skinField().shouldBe(visible).click();
        dropMenuOfSkinField().shouldBe(visible);
        atdSkin().shouldBe(visible).click();
        genericField().shouldNotHave(text("Выберите генерик"));
        return this;
    }

    @Step("select Skin. OptionsOfCharacteristics_aws")
    public OptionsOfCharacteristics_aws setGeneric(String generic) {
        genericField().shouldBe(visible).click();
        searchGenericField().shouldBe(visible).setValue(generic).pressEnter();
        return this;
    }

    @Step("click Search. OptionsOfCharacteristics_aws")
    public OptionsOfCharacteristics_aws clickSearch() {
        btnSearch().click();
        return this;
    }

    @Step("get id of main parameters. OptionsOfCharacteristics_aws")
    public List<String> getIdMainParameters() {
      List<String> idOfMainParameters =idFromMainOptionsColumn().stream().map(n->n.getText()).collect(Collectors.toList());
        return idOfMainParameters;
    }
}
