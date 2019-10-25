package ATD;


import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.WebDriverRunner;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;

class Vacancies_static_page {

    SelenideElement title() {
        return $(By.xpath("//*[@class='vacancy-header__title']/h2"));
    }

    SelenideElement subtitle() {
        return $(By.xpath("//*[@class='vacancy-header__subtitle']"));
    }

    SelenideElement vacancyPositions() {
        return $(By.xpath("//*[@class='vacancy-header__positions']"));
    }

    SelenideElement pdfCheck() {
        return $(By.xpath("//*[@type='application/pdf']"));
    }

    //Checks the availability of PDF files by link
    void checkVacanciesPDF() {
        ElementsCollection pdfButtons = $$(By.cssSelector(".vacancy-content__table-details-btn"));
        for (SelenideElement pdfButton : pdfButtons) {
            pdfButton.click();
            switchTo().window(1);
            pdfCheck().shouldBe(visible);
            WebDriverRunner.getWebDriver().close();
            switchTo().window(0);
        }
    }
}
