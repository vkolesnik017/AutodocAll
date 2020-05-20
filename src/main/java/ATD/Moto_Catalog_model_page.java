package ATD;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selectors.byId;
import static com.codeborne.selenide.Selenide.*;

public class Moto_Catalog_model_page {

    SelenideElement motoSelectorBlock() {return $x("//div[contains(@class,'home-select-car--moto')]");}

    SelenideElement motoSelectorMainForm() {return $(byId("selector-wrapper"));}

    SelenideElement motoSelectorInCloseCondition() {return $x("//div[@class='catalog-title__change-car ']");}
}
