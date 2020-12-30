package ATD.OILS;

import ATD.MotoroilViscosity_page_Logic;
import ATD.Motoroil_page_Logic;
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

public class QC_3178_TransferToListingFromViscosityOilBlock {

    MotoroilViscosity_page_Logic motoroilViscosity_page_logic = new MotoroilViscosity_page_Logic();

    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0");
    }

    @DataProvider(name = "routes", parallel = true)
    Object[] dataProvider() throws SQLException {
        return new SetUp("ATD").setUpShopWithSubroutes("prod", "DE","main","motoroil");
    }

    @Test(dataProvider = "routes")
    @Flaky
    @Owner(value = "OlhaLavrynenko")
    @Description(value = "Test checks transfer to listing from viscosity  oil Block on the Main oil page ")
    public void testChecksTransferFromViscosityOilBlock(String route) {
        openPage(route);
        new Motoroil_page_Logic().oilViscosityBlockFunctionality();
        motoroilViscosity_page_logic.checkVisibilityOfViscosityOilValueInSelector();
    }

    @AfterMethod
    public void close() {
        closeWebDriver();
    }
}