package ATD;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byId;
import static com.codeborne.selenide.Selenide.*;

public class Profile_wishlist {

    SelenideElement productBlock() {
        return $(byId("content-wishlist"));
    }

    SelenideElement countOfProduct() {
        return $x("//div[@class='count']/input");
    }

    SelenideElement btnIncreaseOfProduct() {
        return $x("//a[@class='ga-click plus add']");
    }

    ElementsCollection blocksInSideBar() {
        return $$x("//ul[@class='menu_member']/li/a").filter(visible);
    }

    SelenideElement infoText() {return $x("//p[contains(text(),'Ihre Wunschliste ist leer. Gehen Sie zum Katalog, um ein Produkt auszuwählen.')]");}

    SelenideElement btnOpenCatalog() {return $x("//div[@class='profile-msg']/a");}

    SelenideElement idOfClient() {return $x("//div[@class='top_title']");}

    SelenideElement nameOfClient() {return $x("//span[@class='name']");}
}
