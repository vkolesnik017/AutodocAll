package ATD;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$x;

public class Sponsoring_static_page {

    SelenideElement sponsorHeaderTitle() {
        return $x("//div[@class='sponsors-header__title']");
    }

    SelenideElement mainTextInHeaderPage() {
        return $x("//div[@class='sponsors-header']");
    }

    SelenideElement firstTabContentBlock() {
        return $x("//div[@class='sponsors-tabs-content']/div[@id='sponsor1']");
    }

    SelenideElement tabFirstSponsor() {
        return $x("//li[@data-tab='sponsor1']");
    }

    SelenideElement tabSecondSponsor() {
        return $x("//li[@data-tab='sponsor2']");
    }

    SelenideElement secondTabContentBlock() {
        return $x("//div[@class='sponsors-tabs-content']/div[@id='sponsor2']");
    }

    SelenideElement sponsorForm() {
        return $x("//div[@class='sponsor-form']");
    }

    SelenideElement sendBTN() {
        return $x("//a[@id='submit_ship_data']");
    }

    SelenideElement emailError() {
        return $x("//div[@class='error-block email_error']");
    }

    SelenideElement titleSponsorProject() {
        return $x("//div[@class='sponsor-projects__title']");
    }

    SelenideElement sponsorProjectBlock() {
        return $x("//div[@class='sponsor-projects__row']");
    }
}
