package ATD;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import static com.codeborne.selenide.Selenide.$$x;
import static com.codeborne.selenide.Selenide.$x;


public class Tyres_model_page {


    SelenideElement selectorTiresPosition(int position) {
        return $x("//div[@id='content']/div[" + position + "]");
    }

    SelenideElement topModelBlockPosition(int position) {
        return $x("//div[@id='content']/div[" + position + "]");
    }

    SelenideElement topModelBlock() {
        return $x("//div[@class='car-group']");
    }

    ElementsCollection modelsFromTopModelBlock() {
        return $$x("//div[@class='car-group']//ul/li/a");
    }

    SelenideElement titlePage() {
        return $x("//div[@class='title_count_search']/h1");
    }


}
