package ATD;

import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.*;

public class Sponsoring_static_page_Logic extends Sponsoring_static_page {

    @Step("Check presence for Sponsoring page elements. Sponsoring_static_page")
    public Sponsoring_static_page_Logic checkForPageElements() {
        sponsorHeaderTitle().shouldBe(visible);
        mainTextInHeaderPage().shouldBe(visible);
        firstTabContentBlock().shouldBe(visible);
        tabFirstSponsor().shouldBe(visible);
        tabSecondSponsor().shouldBe(visible);
        tabSecondSponsor().click();
        secondTabContentBlock().shouldBe(visible);
        sponsorForm().shouldBe(visible);
        sendBTN().click();
        emailError().shouldBe(visible);
        titleSponsorProject().shouldBe(visible);
        sponsorProjectBlock().shouldBe(visible);
        return this;
    }
}
