package ATD.Basket.QC_1882_Login_Registration_PasswordRecovery;

import ATD.CartAccount_page_Logic;
import ATD.Product_page_Logic;
import ATD.SetUp;
import io.qameta.allure.Description;
import io.qameta.allure.Flaky;
import io.qameta.allure.Owner;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.sql.SQLException;

import static ATD.CommonMethods.mailRandom;
import static ATD.CommonMethods.openPage;
import static ATD.SetUp.setUpBrowser;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.closeWebDriver;

public class QC_1885_RegistrationNegativeCase_EmptyPasswordField {


    private CartAccount_page_Logic cartAccount_page_logic = new CartAccount_page_Logic();

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
    @Description(value = "Test checks the registration with empty password field")
    public void testRegistrationNegativeCaseEmptyPasswordField(String route) throws SQLException {
    String mail = mailRandom();
        openPage(route);
        new Product_page_Logic().addProductToCart()
                .closePopupOtherCategoryIfYes()
                .cartClick()
                .nextButtonClick();
        cartAccount_page_logic.registrationFormEmailInput().setValue("QC_1885_" + mail);
        cartAccount_page_logic.registrationFormNextBtnClick();
        cartAccount_page_logic.errorPopUpForRegisteringAndRecovery().shouldBe(visible);
    }

    @AfterMethod
    private void close() {
        closeWebDriver();
    }
}