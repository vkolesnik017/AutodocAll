package ATD;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$x;

public class Model_maker_list_year_page {

    SelenideElement linksBlock() {return $(".block_links");}

    ElementsCollection topAutoLinks() {return $$x("//b[text()='Top Automarken:']/following-sibling::a");}

    ElementsCollection topCategoriesLinks() {return $$x("//b[text()='Top Automarken:']/preceding-sibling::a");}
}
