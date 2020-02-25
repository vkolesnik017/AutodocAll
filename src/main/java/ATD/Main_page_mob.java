package ATD;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selectors.byCssSelector;
import static com.codeborne.selenide.Selenide.$;

public class Main_page_mob {

    public SelenideElement numberBasket() {
        return $(byCssSelector(".code"));
    }

    SelenideElement firstPopup() {
        return $(By.className("cps_app"));
    }


    SelenideElement menuBtn() {
        return $(By.xpath("//a[@class='menu header__menu']"));
    }

    SelenideElement signInBtnInMenu() {
        return $(byCssSelector(".name.signin"));
    }

}
