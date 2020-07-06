package ATD.Basket.QC_1882_Login_Registration_PasswordRecovery;

import ATD.DataBase;
import ATD.Product_page_Logic;
import PKW.Main_page_Logic;
import io.qameta.allure.Description;
import io.qameta.allure.Flaky;
import io.qameta.allure.Owner;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.sql.SQLException;

import static ATD.CommonMethods.checkingContainsUrl;
import static ATD.CommonMethods.openPage;
import static ATD.SetUp.setUpBrowser;
import static com.codeborne.selenide.Selenide.close;

public class QC_1887_RegistrationByUserRegisteredOnAnotherSkin {


    private Main_page_Logic main_page_logic = new Main_page_Logic();
    private String mailForPKW;

    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0");
    }

    @DataProvider(name = "route", parallel = true)
    Object[] dataProviderProducts() throws SQLException {
        return new PKW.SetUp().setUpShopsWithMainRoute("prod", "DE", "main");
    }

    @Test(dataProvider = "route")
    @Flaky
    @Owner(value = "Chelombitko")
    @Description(value = "Test checks the successful registration by user registered on another skin")
    public void testRegistrationByUserRegisteredOnAnotherSkin(String route) throws SQLException {
        openPage(route);
        mailForPKW = main_page_logic.openRegistrationFormInHeader()
                .fillingRegistrationFields("QC_1887_");
        main_page_logic.checkPresencePopupSuccessAuthorization();
        openPage(new DataBase().getFullRouteByRouteAndSubroute("prod", "DE", "main", "product32"));
        new Product_page_Logic().addProductToCart()
                .closePopupOtherCategoryIfYes()
                .cartClick()
                .nextButtonClick()
                .registrationFromCart(mailForPKW);
        checkingContainsUrl("basket/address");
    }

    @AfterMethod
    private void tearDown() {
        close();
    }
}