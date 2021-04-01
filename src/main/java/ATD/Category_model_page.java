package ATD;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.support.pagefactory.ElementLocator;

import static com.codeborne.selenide.Selenide.*;

public class Category_model_page {

    SelenideElement linksBlock() {return $(".block_links");}

    ElementsCollection topCategoriesLinks() {return $$x("//b[text()='Beste Kategorien:']/following-sibling::a");}

    ElementsCollection topModelsLinks() {return $$x("//b[text()='Direkt Zu:']/following-sibling::a");}

    ElementsCollection topCarLinks() {return $$x("//b[text()='Beste Kategorien:']/preceding-sibling::a");}
}
