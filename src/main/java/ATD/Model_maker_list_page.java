package ATD;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class Model_maker_list_page {

    SelenideElement linksBlock() {return $(".block_links");}

    ElementsCollection topAutoLinks() {return $$(".block_links a");}
}
