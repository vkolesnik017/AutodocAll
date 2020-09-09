package PKW.OILS.QC_1249_MainHeadlineBlockInListings;

import PKW.Car_parts_motoroil_page_Logic;
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

public class QC_1252_PresenceOfOilIconInMainHeadline {
    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0");
    }

    @DataProvider(name = "routes", parallel = true)
    Object[] dataProvider() throws SQLException {
        return new Common.SetUp().setUpShopWithSubroutes("prod", "DE", "main", "car_parts_motoroil");
    }

    @Test(dataProvider = "routes")
    @Flaky
    @Owner(value = "Kolesnik")
    @Description(value = "Test checks presence of Oil icon in main headline")
    public void testChecksPresenceOfOilIconInMainHeadline(String route) throws SQLException {
        openPage(route);

        new Car_parts_motoroil_page_Logic()
                .presenceOfOilLiterIconInMainHeadline();
    }

    @AfterMethod
    public void close() {
        closeWebDriver();
    }
}
