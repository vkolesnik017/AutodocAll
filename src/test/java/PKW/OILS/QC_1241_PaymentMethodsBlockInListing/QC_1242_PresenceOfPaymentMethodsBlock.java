package PKW.OILS.QC_1241_PaymentMethodsBlockInListing;

import PKW.Car_parts_motoroil_page_Logic;
import PKW.Motoroil_viscosity_page_Logic;
import io.qameta.allure.Description;
import io.qameta.allure.Flaky;
import io.qameta.allure.Owner;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.sql.SQLException;

import static ATD.CommonMethods.openPage;
import static ATD.SetUp.setUpBrowser;
import static com.codeborne.selenide.Selenide.close;

public class QC_1242_PresenceOfPaymentMethodsBlock {

    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0");
    }

    @DataProvider(name = "routes", parallel = true)
    Object[] dataProvider() throws SQLException {
        return new PKW.SetUp().setUpShopWithSubroutes("prod", "DE", "main", "motoroil_viscosity,motoroil_viscosity_brand,motoroil_specification,motoroil_release,motoroil_brand,motoroil_maker,motoroil_maker_group,motoroil_chemical_type,car_parts_motoroil,motoroil_search");
    }


    @Test(dataProvider = "routes")
    @Flaky
    @Owner(value = "Kolesnik")
    @Description(value = "Test checks presence of Payment methods block")
    public void testChecksPresenceOfPaymentMethodsBlock(String route) throws SQLException {
        openPage(route);

        new Motoroil_viscosity_page_Logic()
                .presenceOfPaymentMethodsBlock();
    }

    @AfterMethod
    private void tearDown() {
        close();
    }
}
