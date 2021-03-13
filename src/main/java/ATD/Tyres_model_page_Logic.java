package ATD;


import io.qameta.allure.Step;
import org.testng.Assert;
import static com.codeborne.selenide.Condition.attribute;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.back;


public class Tyres_model_page_Logic extends Tyres_model_page {


    @Step("Check presence top model block. Tyres_model_page")
    public Tyres_model_page_Logic checkPresenceTopModelBlock() {
        topModelBlock().shouldBe(visible);
        return this;
    }


    @Step("Check transition on all models in top models block. Tyres_model_page")
    public Tyres_model_page_Logic checkTransitionOnAllModelsInTopModelsBlock() {
        modelsFromTopModelBlock().shouldHaveSize(15);
        for (int i = 0; i < modelsFromTopModelBlock().size(); i++) {
            String nameModel = modelsFromTopModelBlock().get(i).getText();
            modelsFromTopModelBlock().get(i).click();
            String titleName = getTextTitlePage();
            Assert.assertTrue(titleName.contains(nameModel));
            checkPresenceTopModelBlock();
            back();
        }
        return this;
    }


    @Step("Get text title page. Tyres_model_page")
    public String getTextTitlePage() {
       return titlePage().getText();
    }


    @Step("Checks that the top models block is under the Tires selector. Tyres_model_page")
    public Tyres_model_page_Logic checkingLocationTopModelsBlock() {
        selectorTiresPosition(3).shouldHave(attribute("class", "reifen_select reifen_select_block_pkw"));
        topModelBlockPosition(4).shouldHave(attribute("class", "sub_catalog_grid"));
        return this;
    }




}
