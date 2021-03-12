package ATD;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import static com.codeborne.selenide.Selenide.$$x;
import static com.codeborne.selenide.Selenide.$x;


public class Tyres_model_page {


    SelenideElement topModelBlock() {
        return $x("//div[@class='car-group']");
    }

    ElementsCollection modelsFromTopModelBlock() {
        return $$x("//div[@class='car-group']//ul/li/a");
    }



}
