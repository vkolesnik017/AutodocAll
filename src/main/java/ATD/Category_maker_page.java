package ATD;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$x;

public class Category_maker_page {

    SelenideElement linksBlock() {return $(".block_links");}

    ElementsCollection topAutoLinks() {return $$x("//b[text()='Top Automarken:']/..//a");}

    ElementsCollection carPartsForLinks() {return $$x("//b[text()='Direkt Zu']/../..//a");}
}
