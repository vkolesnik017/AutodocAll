package ATD;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selectors.byId;
import static com.codeborne.selenide.Selenide.*;

public class Moto_Categories_maker_page {

    SelenideElement markeOfMotoInSelector() {return $(byId("form_maker_id"));}
}
