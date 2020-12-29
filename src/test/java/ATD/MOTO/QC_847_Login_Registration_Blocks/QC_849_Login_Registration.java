package ATD.MOTO.QC_847_Login_Registration_Blocks;

import Common.DataBase;
import ATD.Moto_main_page_Logic;
import Common.SetUp;
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

public class QC_849_Login_Registration {
    private String email = "QC_849_autotestDE@mailinator.com";

    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0", false);
    }

    @DataProvider(name = "routes", parallel = true)
    Object[] dataProvider() throws SQLException {
        return new SetUp("ATD").setUpShopWithSubroutes("subprod", "DE", "moto_main", "moto_main");

    }

    @Test(dataProvider = "routes")
    @Flaky
    @Owner(value = "Kolesnik")
    @Description(value = "Test checks login and registration of user")
    public void testChecksLoginAndRegistration(String route) throws SQLException {
        openPage(route);

        new Moto_main_page_Logic()
                .loginOfUser(email, password)
                .visibilityOfUsersName();
        checkingContainsUrl(new DataBase("ATD").getFullRouteByRouteAndSubroute("prod", "DE", "main", "profile_plus"));
    }

    @AfterMethod
    public void close() {
        closeWebDriver();
    }
}
