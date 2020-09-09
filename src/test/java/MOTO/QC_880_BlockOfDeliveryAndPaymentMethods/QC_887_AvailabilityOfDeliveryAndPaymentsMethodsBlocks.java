package MOTO.QC_880_BlockOfDeliveryAndPaymentMethods;

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

import static ATD.CommonMethods.openPage;
import static Common.SetUp.setUpBrowser;
import static com.codeborne.selenide.Selenide.closeWebDriver;

public class QC_887_AvailabilityOfDeliveryAndPaymentsMethodsBlocks {

    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0");
    }

    @DataProvider(name = "routes", parallel = true)
    Object[] dataProvider() throws SQLException {
        return new SetUp().setUpShopWithSubroutes("subprod", "DE", "moto_main", "moto_main");
    }

    @Test(dataProvider = "routes")
    @Flaky
    @Owner(value = "Kolesnik")
    @Description(value = "Test checks availability of Delivery and payment methods  blocks ")
    public void testChecksAvailabilityOfDeliveryAndPaymentsMethodsBlocks(String route) throws SQLException {
        openPage(route);

        new Moto_main_page_Logic()
                .availabilityOfDeliveryBlock()
                .availabilityOfPaymentMethodsBlock();
    }

    @AfterMethod
    public void close() {
        closeWebDriver();
    }
}
