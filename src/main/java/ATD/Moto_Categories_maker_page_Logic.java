package ATD;

import io.qameta.allure.Step;
import org.testng.Assert;

import static com.codeborne.selenide.Selenide.page;
import static com.codeborne.selenide.WebDriverRunner.url;

public class Moto_Categories_maker_page_Logic extends Moto_Categories_maker_page{

    @Step("get brand of  motorcycle from Url .Moto_Categories_maker_page")
    public String getBrandOfMotoFromUrl() {
        String brandOfMoto =url().replaceAll("(.*)\\/", "");
        return brandOfMoto;
    }

    @Step("presence brand of motorcycle in selector .Moto_Categories_maker_page")
    public Moto_Categories_maker_page_Logic presenceBrandOfMotoInSelector(String brandOfMoto) {
        String brandOfMotoFromSelector = markeOfMotoInSelector().getSelectedText().replaceAll("[^A-z]","");
        Assert.assertEquals(brandOfMotoFromSelector,brandOfMoto.replaceAll("-","").toUpperCase());
        return this;
    }

    @Step(" selecting motorcycle in selector .Moto_Categories_maker_page")
    public Moto_Catalog_page_Logic selectMotoInSelector(String marke, String model, String motor) {
       markeOfMotoInSelector().selectOptionByValue(marke);
        modelFiledInSelector().selectOptionByValue(model);
        motorFiledInSelector().selectOptionByValue(motor);
        searchButton().click();
        return page(Moto_Catalog_page_Logic.class);
    }
}
