package ATD.Basket.QC_1882_Login_Registration_PasswordRecovery;

import ATD.*;
import io.qameta.allure.Description;
import io.qameta.allure.Flaky;
import io.qameta.allure.Owner;
import mailinator.Mailinator;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.sql.SQLException;

import static ATD.CommonMethods.*;
import static ATD.SetUp.setUpBrowser;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.close;

public class QC_1929_PasswordRecovery {

    //TODO This test is temporarily inactive due to incorrect description of steps.
    //TODO After successfully editing the QC, the test will be added to xml. file.

    private String mail = "QC_1929_autotestATD@mailinator.com";
    private Mailinator mailinator = new Mailinator();
    private Product_page_Logic product_page_logic = new Product_page_Logic();

    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0");
    }

    @DataProvider(name = "route", parallel = true)
    Object[] dataProviderProducts() throws SQLException {
        return new SetUp().setUpShopWithSubroutes("prod", "DE", "main", "product32");
    }

    @Test(dataProvider = "route")
    @Flaky
    @Owner(value = "Chelombitko")
    @Description(value = "Test checks the successful password recovery.")
    public void testPasswordRecovery(String route) throws SQLException {
        String newPassword = getRandomNumber();
        openPage(route);
        product_page_logic.addProductToCart()
                .closePopupOtherCategoryIfYes()
                .cartClick()
                .nextButtonClick()
                .passwordRecoveryRequestFromCart(mail);
        mailinator.openEmail(mail).letterInfo(1).shouldHave(text("moments ago")).shouldHave(text("neues Passwort"));
        mailinator.openLetter(1)
                .clickLinkRecoveryPasswordInLetter()
                .fillPasswordFieldsAndClickSubmit(newPassword)
                .nameOfClient().shouldBe(visible);
        checkingContainsUrl("services/plus-service");
        new Profile_plus_page_Logic().logOutClick();
        close();
        openPage(new DataBase().getFullRouteByRouteAndSubroute("prod", "DE", "main", "product32"));
        product_page_logic.addProductToCart()
                .closePopupOtherCategoryIfYes()
                .cartClick()
                .nextButtonClick()
                .signIn(mail, password);
        new CartAccount_page_Logic().checkTextFromErrorPopUpForRegisteringAndRecovery("E-mail und Passwort passen nicht zusammen!")
                .closeErrorPopup()
                .signIn(mail, newPassword);
        checkingContainsUrl("basket/address");
    }

    @AfterMethod
    private void tearDown() {
        close();
    }
}