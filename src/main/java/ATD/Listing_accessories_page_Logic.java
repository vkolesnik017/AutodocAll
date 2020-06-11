package ATD;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.testng.Assert;

import java.util.ArrayList;
import java.util.Collections;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.page;
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
    public Listing_accessories_page_Logic checkingPresenceTitleName() {
        listingTitle().shouldBe(visible);
        return this;
    }

    @Step("Get name first category in Sidebar. Listing_accessories_page")
    public String getNameFirstCategoryInSidebar() {
        return firstCategoryInSidebar().getText();
    }

    @Step("Click on first category in Sidebar.Listing_accessories_page")
    public Listing_accessories_page_Logic clickOnFirstCategoryInSidebar() {
        firstCategoryInSidebar().click();
        return this;
    }

    @Step("Checking presence quantity products block. Listing_accessories_page")
    public Listing_accessories_page_Logic checkingPresenceQuantityProductBlock() {
        blockProductQuantity().shouldBe(visible);
        return this;
    }

    @Step("Checking change display of products on the list view and then grid. Listing_accessories_page")
    public Listing_accessories_page_Logic checkingChangeDisplayProductsAsListAndGrid() {
        blockChangePositionProductsOnListOrGrid().shouldBe(visible);
        listingProductsDisplayedAsList().shouldBe(visible);
        btnChangePositionProductsAsGrid().click();
        listingProductsDisplayedAsGrid().shouldBe(visible);
        return this;
    }

    @Step("Get name all categories in sidebar and title name page then add in list. Listing_accessories_page")
    public ArrayList<String> getNameAllCategoriesInSidebarAndTitleNamePage() {
        ArrayList<String> nameCategories = new ArrayList<>();
        for (SelenideElement element : categoriesInSidebar()) {
            String name = element.getText();
            nameCategories.add(name);
        }
        String nameTitle = listingTitle().getText();
        if (nameCategories.contains(nameTitle)) Assert.fail(nameTitle.concat(" visible in sidebar. "));
        nameCategories.add(nameTitle);
        Collections.sort(nameCategories);
        return nameCategories;
    }

    @Step("Checking presence and is not clickable third Bread Crumb. Listing_accessories_page")
    public Listing_accessories_page_Logic checkingPresenceAndWorkThirdBreadCrumb() {
        thirdBreadCrumb().shouldBe(visible).click();
        return this;
    }

    @Step(" Checking transition on main page category after Click on second Bread Crumb. Listing_accessories_page")
    public Index_accessories_page_Logic checkingTransitionAfterClickSecondBreadCrumb() {
        secondBreadCrumb().click();
        return page(Index_accessories_page_Logic.class);
    }

    @Step("Checking transition on TecDoc catalog after Click on first Bread Crumb. Listing_accessories_page")
    public Categories_page checkingTransitionAfterClickFirstBreadCrumb() {
        firstBreadCrumb().click();
        return page(Categories_page_Logic.class);
    }


}
