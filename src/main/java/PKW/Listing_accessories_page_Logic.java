package PKW;

import io.qameta.allure.Step;
import static com.codeborne.selenide.Condition.attribute;

public class Listing_accessories_page_Logic extends Listing_accessories_page {

    @Step("Get name main title on Listing Page. Listing_accessories_page")
    public String getNameMainTitleOnListingPage(){
        return mainTitleListingPage().getText();
    }

    @Step("Checking work quantity counter on decrease and increase products. Listing_accessories_page")
    public Listing_accessories_page_Logic checkingWorkQuantityCounterOnDecreaseAndIncrease() {
        new CommonMethods().checkingCounterIncrease(3, counterValueInQuantityCounter(), btnPlusInQuantityCounter());
        new CommonMethods().checkingCounterDecrease(2, counterValueInQuantityCounter(), btnMinusInQuantityCounter() );
        counterValueInQuantityCounter().shouldHave(attribute("value", "2"));
        return this;
    }
}
