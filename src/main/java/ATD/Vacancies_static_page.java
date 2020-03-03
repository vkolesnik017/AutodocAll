package ATD;


import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.*;

public class Vacancies_static_page {

    SelenideElement title() {
        return $(By.xpath("//*[@class='vacancy-header__title']/h2"));
    }

    SelenideElement subtitle() {
        return $(By.xpath("//*[@class='vacancy-header__subtitle']"));
    }

    SelenideElement vacancyPositionsTitle() {
        return $(By.xpath("//*[@class='vacancy-header__positions']"));
    }

    SelenideElement vacancyBlock() {
        return $(By.cssSelector(".vacancy-content"));
    }

    SelenideElement pdfCheck() {
        return $(By.xpath("//*[@type='application/pdf']"));
    }

    ElementsCollection openBtnVacanciesPDF() {
        return $$(By.cssSelector(".vacancy-content__table-details-btn"));
    }
}
