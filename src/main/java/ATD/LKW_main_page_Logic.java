package ATD;

import io.qameta.allure.Step;
import org.testng.Assert;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.title;

public class LKW_main_page_Logic extends LKW_main_page {

    @Step("Validation page loading")
    public LKW_main_page_Logic checkPagesIssuccessfulyLoaded() {
        Assert.assertNotEquals(title(), "404");
        return this;
    }

    @Step("checking of appearance Hint block after click on search field")
    public LKW_main_page_Logic checkAppearanceOfHintBlock() {
        searchBar().clear();
        searchBar().click();
        tooltipToSearch().should(appear);
        return this;
    }

    @Step("checking of appearance Pop-Up block after click on Beispiel link")
    public LKW_main_page_Logic checkAppearanceOfBeispielPopUp() {
        infoIconForSearch().click();
        infoPopupForSearch().should(appear);
        return this;
    }

    @Step("checking of appearance Pop-Up block after click on Beispiel link")
    public LKW_main_page_Logic chekingOfVisibilityOfLogoInHeader() {
        logoInHeader().shouldBe(visible);
        return this;
    }


}
