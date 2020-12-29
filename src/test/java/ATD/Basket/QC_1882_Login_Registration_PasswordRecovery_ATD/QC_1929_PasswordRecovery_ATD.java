package ATD.Basket.QC_1882_Login_Registration_PasswordRecovery_ATD;

import ATD.*;
import Common.DataBase;
import Common.SetUp;
import io.qameta.allure.Description;
import io.qameta.allure.Flaky;
import io.qameta.allure.Owner;
import mailinator.WebMail;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.sql.SQLException;

import static ATD.CommonMethods.*;
import static Common.SetUp.setUpBrowser;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.closeWebDriver;
import static mailinator.WebMail.passwordForMail;

public class QC_1929_PasswordRecovery_ATD {

    private String mail = "QC_1929_autotestATD@autodoc.si";
    private WebMail webMail = new WebMail();
    private Product_page_Logic product_page_logic = new Product_page_Logic();

    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0", false);
    }

    @DataProvider(name = "route")
    Object[] dataProviderProducts() throws SQLException {
        return new SetUp("ATD").setUpShopWithSubroutes("prod", "DE", "main", "product32,listing_accessories");
    }

    @Test(dataProvider = "route")
    @Flaky
    @Owner(value = "Chelombitko")
    @Description(value = "Test checks the successful password recovery.")
    public void testPasswordRecovery(String route) throws SQLException {
        String newPassword = getRandomNumber();
        openPage(route);
        product_page_logic.checkNumberBasketAndRefreshPageIfNot();
        clickOfBuyBtnForAllPages();
        product_page_logic.closePopupOtherCategoryIfYes()
                .cartClick()
                .nextButtonClick()
                .passwordRecoveryRequestFromCart(mail);
        webMail.openMail(mail, passwordForMail).subjectLetter(1).shouldHave(text("Setzen Sie das Passwort"));
        webMail.checkPresenceUnderFirstLetter()
                .openLetter(1)
                .clickLinkRecoveryPasswordInLetter()
                .fillPasswordFieldsAndClickSubmit(newPassword)
                .nameOfClient().shouldBe(visible);
        checkingContainsUrl("services/plus-service");
        new Profile_plus_page_Logic().logOutClick();
        close();
        openPage(new DataBase("ATD").getFullRouteByRouteAndSubroute("prod", "DE", "main", "product32"));
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
    private void close() {
        closeWebDriver();
    }
}