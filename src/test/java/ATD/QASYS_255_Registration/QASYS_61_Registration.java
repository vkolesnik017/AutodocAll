package ATD.QASYS_255_Registration;

import ATD.*;
import com.codeborne.selenide.Condition;
import io.qameta.allure.Description;
import io.qameta.allure.Flaky;
import io.qameta.allure.Owner;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static ATD.CommonMethods.*;
import static ATD.SetUp.setUpBrowser;
import static com.codeborne.selenide.Selenide.open;

public class QASYS_61_Registration {
    private Main_page_Logic main_page_logic = new Main_page_Logic();
    private Search_page_Logic search_page_logic = new Search_page_Logic();
    private CartAccount_page_Logic cartAccount_page_logic= new CartAccount_page_Logic();
    private CartAddress_page_Logic cartAddress_page = new CartAddress_page_Logic();

    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0");
    }

    @DataProvider(name = "route", parallel = true)
    Object[] dataProvider() {
        return new SetUp().setUpShop("prod", "DE");
    }

    @Test(dataProvider = "route")
    @Owner(value = "alex_qa")
    @Description(value = "ТС01 Registration from login button")
    @Flaky
    public void registrationButtonLogin(String route) {
        open(route);
        main_page_logic.loginBtnInHeader().click();
        main_page_logic.registrationButtonInLoginPopup().click();
        String firstName = firstNameRandom();
        main_page_logic.fillRequiredFieldsForRegistration(firstName, secondNameRandom(), mailRandom(), false);
        main_page_logic.fillPasswordFieldsAndClickRegistration();
        new Profile_page().nameOfClient().shouldHave(Condition.text(firstName));
    }

    @Test(dataProvider = "route")
    @Owner(value = "alex_qa")
    @Description(value = "ТС02 Registration from basket")
    @Flaky
    public void registrationInBasket(String route) {
        open(route);
        main_page_logic.useSearch(ridex_82B0896);
        search_page_logic.addFirstProductAndGoToCart();
        new Cart_page_Logic().nextButtonClick();
        String mail = mailRandom();
        cartAccount_page_logic.registrationFormEmailInput().setValue(mail);
        cartAccount_page_logic.registrationFormPasswordInput().setValue(password);
        cartAccount_page_logic.registrationFormNextBtnClick();
        cartAddress_page.nextButton().shouldBe(Condition.visible);
        cartAddress_page.logoClick();
        main_page_logic.logoutButton().click();
        main_page_logic.loginBtnInHeader().click();
        main_page_logic.emailInputInLoginPopup().setValue(mail);
        main_page_logic.passwordInputInLoginPopup().setValue(password);
        main_page_logic.loginBtnInPopUp().click();
        main_page_logic.logoutButton().shouldBe(Condition.visible);
    }
}
