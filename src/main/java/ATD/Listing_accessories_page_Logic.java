package ATD;

import io.qameta.allure.Step;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.sleep;

public class Listing_accessories_page_Logic extends Listing_accessories_page {

    @Step("Get title name on listing. Listing_accessories_page")
    public String getTitleName() {
        return listingTitle().getText();
    }

    @Step("Checking that selected brand become not active after clicking on it. Listing_accessories_page")
    public Listing_accessories_page_Logic checkResetSelectedFilterByBrand() {
        selectedBrand().shouldBe(visible).click();
        sleep(3000);
        selectedBrand().shouldNotBe(visible);
        return this;
    }

    @Step("Checking presence title categories block in Sidebar. Listing_accessories_page")
    public Listing_accessories_page_Logic checkingPresenceTitleCategoriesBlockInSidebar() {
        titleCategoriesBlockInSidebar().shouldBe(visible);
        return this;
    }

    @Step("Checking presence title name on listing page.  Listing_accessories_page ")
    public  Listing_accessories_page_Logic checkingPresenceTitleName() {
        listingTitle().shouldBe(visible);
        return this;
    }

    @Step("Get name first category in Sidebar. Listing_accessories_page")
    public  String getNameFirstCategoryInSidebar() {
        return firstCategoryInSidebar().getText();
    }

    @Step("Click on first category in Sidebar.Listing_accessories_page")
    public Listing_accessories_page_Logic clickOnFirstCategoryInSidebar(){
        firstCategoryInSidebar().click();
        return this;
    }

    @Step("Checking presence quantity products block. Listing_accessories_page")
    public Listing_accessories_page_Logic checkingPresenceQuantityProductBlock(){
        blockProductQuantity().shouldBe(visible);
        return this;
    }

}
