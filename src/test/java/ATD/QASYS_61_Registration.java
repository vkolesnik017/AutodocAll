package ATD;

import com.codeborne.selenide.Condition;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static ATD.CommonMethods.*;
import static com.codeborne.selenide.Selenide.open;

public class QASYS_61_Registration {
    private Main_page mainPage = new Main_page();
    private Search_page search_page = new Search_page();
    private CartAccount_page cartAccount_page = new CartAccount_page();
    private CartAddress_page cartAddress_page = new CartAddress_page();

    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0");
    }

    @DataProvider(name = "route", parallel = true)
    Object[] dataProvider() {
        return new CommonMethods().setUpShop("prod", "AT");
    }

    @Test(dataProvider = "route", enabled = false)
    public void registrationButtonLogin(String route) {
        open(route);
        mainPage.loginButton().click();
        mainPage.registrationButtonInLoginPopup().click();
        String firstName = firstNameRandom();
        mainPage.fillRequiredFieldsForRegistration(firstName, secondNameRandom(), mailRandom());
        mainPage.fillPasswordFieldsAndClickRegistration();
        new Profile_page().nameOfClient().shouldHave(Condition.text(firstName));
    }

    @Test(dataProvider = "route")
    public void registrationInBasket(String route) {
        open(route);
        mainPage.useSearch(ridex_82B0896);
        search_page.buyButton().click();
        search_page.cartClick();
        new Cart_page().nextButtonClick();
        String mail = mailRandom();
        cartAccount_page.registrationFormEmailInput().setValue(mail);
        cartAccount_page.registrationFormPasswordInput().setValue(password);
        cartAccount_page.registrationFormNextButtonClick();
        cartAddress_page.nextButton().shouldBe(Condition.visible);
        cartAddress_page.logoClick();
        mainPage.logoutButton().click();
        mainPage.loginButton().click();
        mainPage.emailInputInLoginPopup().setValue(mail);
        mainPage.passwordInputInLoginPopup().setValue(password);
        mainPage.singInButtonClickInLoginPopup();
        mainPage.logoutButton().shouldBe(Condition.visible);
    }
}
