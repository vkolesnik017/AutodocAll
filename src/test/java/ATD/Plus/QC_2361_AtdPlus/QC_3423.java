package ATD.Plus.QC_2361_AtdPlus;

import ATD.Service_packages_page_Logic;
import Common.DataBase;
import io.qameta.allure.Description;
import io.qameta.allure.Flaky;
import io.qameta.allure.Owner;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.sql.SQLException;

import static ATD.CommonMethods.openPage;
import static Common.SetUp.setUpBrowser;
import static com.codeborne.selenide.Selenide.closeWebDriver;

public class QC_3423 {

    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0", false);
    }

    @Test
    @Flaky
    @Owner(value = "Bobryshev")
    @Description(value = "Checking that there is no Personal Discount option on the Plus on France website")

    public void testCheckNoPersonalDiscountOnPlusOnFranceWeb() throws SQLException {
        openPage(new DataBase("ATD").getFullRouteByRouteAndSubroute("prod", "FR", "main", "service_packages"));
        new Service_packages_page_Logic().checkAbsencePersonalDiscount();
    }

    @AfterMethod
    public void close() {
        closeWebDriver();
    }
}
