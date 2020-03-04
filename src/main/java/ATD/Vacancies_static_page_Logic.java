package ATD;

import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.WebDriverRunner;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.switchTo;

public class Vacancies_static_page_Logic extends Vacancies_static_page {


    @Step("Checks for items. Vacancies_static_page")
    public Vacancies_static_page_Logic checkItemsFromPage(){
        title().shouldBe(visible);
        subtitle().shouldBe(visible);
        vacancyPositionsTitle().shouldBe(visible);
        vacancyBlock().shouldBe(visible);
        return this;
    }


    @Step("Checks the availability of PDF files by link. Vacancies_static_page")
    public Vacancies_static_page_Logic checkForPdfFilesByLink(){
        for (SelenideElement pdfButton : openBtnVacanciesPDF()) {
            pdfButton.click();
            switchTo().window(1);
            pdfCheck().shouldBe(visible);
            WebDriverRunner.getWebDriver().close();
            switchTo().window(0);
        }
        return this;
    }
}
