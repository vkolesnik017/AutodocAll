package ATD.Basket.QC_1882_Login_Registration_PasswordRecovery;

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

import static ATD.CommonMethods.*;
import static ATD.SetUp.setUpBrowser;
import static com.codeborne.selenide.Selenide.close;

public class QC_1923_LoginPositiveCase {

    private String mail = "QC_1923_autotestATD@mailinator.com";

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
    @Description(value = "Test checks successful login through the cart.")
    public void testLoginPositiveCase(String route) throws SQLException {
        openPage(route);
        new Product_page_Logic().addProductToCart()
                .closePopupOtherCategoryIfYes()
                .cartClick()
                .nextButtonClick()
                .signIn(mail, password);
        checkingContainsUrl("basket/address");
    }

    @AfterMethod
    private void tearDown() {
        close();
    }
}