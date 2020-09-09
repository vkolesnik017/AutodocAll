package PKW.OILS.QC_943_ViscosityBlockOnOilMainPage;

import PKW.Motoroil_page_Logic;
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

public class QC_946_TransitionToListingWithSelectedViscosity {
    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0");
    }

    @DataProvider(name = "routes", parallel = true)
    Object[] dataProvider() throws SQLException {
        return new Common.SetUp().setUpShopWithSubroutes("prod", "DE", "main", "motoroil");
    }

    @Test(dataProvider = "routes")
    @Flaky
    @Owner(value = "Kolesnik")
    @Description(value = "Test checks transition to listing with selected Viscosity")
    public void testChecksTransitionToListingWithSelectedViscosity(String route) {
        openPage(route);

        new Motoroil_page_Logic()
                .presenceOfViscosityBlock()
                .selectViscosityInBlock(1)
                .checkViscosityGradeFieldInSelector("sae-10w-40")
                .visibilityOfSelectedViscosityInBlock("SAE 10W-40");
    }

    @AfterMethod
    public void close() {
        closeWebDriver();
    }
}
