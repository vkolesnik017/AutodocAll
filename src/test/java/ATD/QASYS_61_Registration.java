package ATD;

import com.codeborne.selenide.Condition;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static ATD.CommonMethods.*;
import static com.codeborne.selenide.Selenide.open;

public class QASYS_61_Registration {
    private Main_page mainPage = new Main_page();

    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0");
    }

    @DataProvider(name = "route", parallel = true)
    Object[] dataProvider() {
        return new CommonMethods().setUpShop("prod", "AT");
    }

    @Test(dataProvider = "route")
    public void registrationButtonLogin(String route){
        open(route);
        mainPage.loginButton().click();
        mainPage.registrationButtonInLoginPopup().click();
        String firstName = firstNameRandom();
        mainPage.fillRequiredFieldsForRegistration(firstName, secondNameRandom(), mailRandom());
        mainPage.fillPasswordFieldsAndClickRegistration();
        new Profile_page().nameOfClient().shouldHave(Condition.text(firstName));
    }
}
