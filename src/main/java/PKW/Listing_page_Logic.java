package PKW;

import com.codeborne.selenide.ElementsCollection;
import io.qameta.allure.Step;
import static com.codeborne.selenide.CollectionCondition.sizeGreaterThan;
import static com.codeborne.selenide.Condition.attribute;
import static com.codeborne.selenide.Condition.text;


public class Listing_page_Logic extends Listing_page {

    @Step("Method checks that expected text is present in title of all products on listing. Listing_page")
    public Listing_page_Logic checkProductTitleOnListing(String expectedTextInTitle, Boolean shouldHaveTextOrNotHave, ElementsCollection titleViewMode) {
        titleViewMode.shouldHave(sizeGreaterThan(0));
        for (int i = 0; i < titleViewMode.size(); i++) {
            if (shouldHaveTextOrNotHave) {
                titleViewMode.get(i).shouldHave(text(expectedTextInTitle));
            } else {
                titleViewMode.get(i).shouldNotHave(text(expectedTextInTitle));
            }
        }
        return this;
    }


    @Step("Wait until preloader disappear. Listing_page")
    public Listing_page_Logic waitUntilPreloaderDisappear() {
        preloader().waitUntil(attribute("style", "display: none;"), 20000);
        return this;
    }


}
