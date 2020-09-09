package ATD.Basket.QC_1882_Login_Registration_PasswordRecovery;

import ATD.CartAccount_page_Logic;
import ATD.CartAddress_page_Logic;
import Common.DataBase;
import ATD.Product_page_Logic;
import Common.SetUp;
import PKW.Main_page_Logic;
import io.qameta.allure.Description;
import io.qameta.allure.Flaky;
import io.qameta.allure.Owner;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.sql.SQLException;

import static ATD.CommonMethods.*;
import static Common.SetUp.setUpBrowser;
import static com.codeborne.selenide.Selenide.closeWebDriver;

public class QC_1924_LoginByUserLoginOnAnotherSkin {

    private Main_page_Logic main_page_logic = new Main_page_Logic();
    private CartAccount_page_Logic cartAccount_page_logic = new CartAccount_page_Logic();
    private String mailForPKW;

    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0");
    }

    @DataProvider(name = "route", parallel = true)
    Object[] dataProviderProducts() throws SQLException {
        return new SetUp().setUpShopsWithMainRoute("prod", "DE", "main");
    }

    @Test(dataProvider = "route")
    @Flaky
    @Owner(value = "Chelombitko")
    @Description(value = "Test checks login by user login with another skin")
    public void testLoginByUserLoginOnAnotherSkin(String route) throws SQLException {
        openPage(route);
        mailForPKW = main_page_logic.openRegistrationFormInHeader()
                .fillingRegistrationFields("QC_1924_");
        main_page_logic.checkPresencePopupSuccessAuthorization();
        openPage(new DataBase().getFullRouteByRouteAndSubroute("prod", "DE", "main", "product32"));
        new Product_page_Logic().addProductToCart()
                .closePopupOtherCategoryIfYes()
                .cartClick()
                .nextButtonClick()
                .signIn(mailForPKW, "pkwtest");
        cartAccount_page_logic.checkTextFromErrorPopUpForRegisteringAndRecovery("Ihre E-mail wurde nicht gefunden. Bitte erstellen Sie einen neuen Account.")
                .closeErrorPopup()
                .registrationFromCart(mailForPKW)
                .checkPresenceShippingForm()
                .logoClick()
                .logOutClick();
        close();
        openPage(new DataBase().getFullRouteByRouteAndSubroute("prod", "DE", "main", "product32"));
        new Product_page_Logic().addProductToCart()
                .closePopupOtherCategoryIfYes()
                .cartClick()
                .nextButtonClick()
                .signIn(mailForPKW, "pkwtest");
        checkingContainsUrl("basket/address");
        new CartAddress_page_Logic().checkPresenceShippingForm()
                .logoClick()
                .logOutClick();
        close();
        openPage(new DataBase().getFullRouteByRouteAndSubroute("prod", "DE", "main", "product32"));
        new Product_page_Logic().addProductToCart()
                .closePopupOtherCategoryIfYes()
                .cartClick()
                .nextButtonClick()
                .signIn(mailForPKW, password);
        checkingContainsUrl("basket/address");
    }

    @AfterMethod
    private void close() {
        closeWebDriver();
    }
}