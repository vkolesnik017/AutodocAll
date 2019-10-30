package ATD;


import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static ATD.CommonMethods.closeCookiesFooterMessage;
import static ATD.SetUp.setUpBrowser;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.close;
import static com.codeborne.selenide.Selenide.open;

public class QASYS_360_StaticPage_vacancies {
    private Main_page mainPage = new Main_page();
    private Vacancies_static_page vacanciesStaticPage = new Vacancies_static_page();

    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0");
    }

    @DataProvider(name = "route", parallel = true)
    Object[] dataProvider() {
        return new SetUp().setUpShop("prod", "DE");
    }

    @Test(dataProvider = "route")
    public void checkVacanciesPageElements(String route) {
        open(route);
        closeCookiesFooterMessage();
        mainPage.clickVacancies();
        vacanciesStaticPage.title().shouldBe(visible);
        vacanciesStaticPage.subtitle().shouldBe(visible);
        vacanciesStaticPage.vacancyPositions().shouldBe(visible);
        vacanciesStaticPage.checkVacanciesPDF();
        close();
    }
}
