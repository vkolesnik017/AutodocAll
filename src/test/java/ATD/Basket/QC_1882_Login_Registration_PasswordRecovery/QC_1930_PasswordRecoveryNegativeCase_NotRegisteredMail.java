package ATD.Basket.QC_1882_Login_Registration_PasswordRecovery;

import ATD.Product_page_Logic;
import Common.SetUp;
import io.qameta.allure.Description;
import io.qameta.allure.Flaky;
import io.qameta.allure.Owner;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.sql.SQLException;

import static ATD.CommonMethods.mailRandomMailinator;
import static ATD.CommonMethods.openPage;
import static Common.SetUp.setUpBrowser;
import static com.codeborne.selenide.Selenide.closeWebDriver;

public class QC_1930_PasswordRecoveryNegativeCase_NotRegisteredMail {

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
    @Description(value = "Test checks password recovery by entering not registered in the system email.")
    public void testPasswordRecoveryNegativeCase_NotRegisteredMail(String route) throws SQLException {
        String mail = mailRandomMailinator("1930");
        openPage(route);
        new Product_page_Logic().addProductToCart()
                .closePopupOtherCategoryIfYes()
                .cartClick()
                .nextButtonClick()
                .checkPopUpWithErrorWhenRecoveringPass(mail, "Ihre E-mail wurde nicht gefunden.Bitte erstellen Sie einen neuen Account");
    }

    @AfterMethod
    private void close() {
        closeWebDriver();
    }
}