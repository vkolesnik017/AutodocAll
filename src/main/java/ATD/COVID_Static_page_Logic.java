package ATD;

import io.qameta.allure.Step;
import org.testng.Assert;

import static com.codeborne.selenide.Condition.visible;

public class COVID_Static_page_Logic extends COVID_Static_page {

    @Step("Checks the title on the COVID page. COVID_Static_page")
    public COVID_Static_page_Logic checkTitleOnThePage() {
        covidTitle().shouldBe(visible);
        Assert.assertFalse(covidTitle().text().isEmpty());
        return this;
    }

    @Step("Checking questions and answers on the CoVID page. COVID_Static_page")
    public COVID_Static_page_Logic checkingAnswersAndQuestions() {
        for (int i = 0; i < 12; i++) {
            covidQuestionsBlock().get(i).shouldBe(visible);
            Assert.assertFalse(covidQuestionsBlock().get(i).text().isEmpty());
        }
        Assert.assertEquals(covidQuestionsBlock().size(), 12);
        return this;
    }
}
