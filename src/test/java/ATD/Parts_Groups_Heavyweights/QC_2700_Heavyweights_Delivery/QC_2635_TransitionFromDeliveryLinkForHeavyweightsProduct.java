package ATD.Parts_Groups_Heavyweights.QC_2700_Heavyweights_Delivery;

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

import static ATD.CommonMethods.openPage;
import static Common.SetUp.setUpBrowser;
import static com.codeborne.selenide.Selenide.closeWebDriver;

public class QC_2635_TransitionFromDeliveryLinkForHeavyweightsProduct {
    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0", false);
    }

    @DataProvider(name = "route", parallel = true)
    Object[] dataProviderProducts() throws SQLException {
        return new SetUp("ATD").setUpShopWithSubroutes("prod", "DE", "main", "HeavyLoadProduct3");
    }

    @Test(dataProvider = "route")
    @Flaky
    @Owner(value = "LavrynenkoOlha")
    @Description(value = "Test checks the transition to the Versand page after clicking Versandkosten and Sperrgutzuschlag links")
    public void testOfHeavyLoadsPurchase(String route) throws SQLException {
        openPage(route);
        new Product_page_Logic().clickVerandkostenLink()
                .checkingHeavyCargoLinkTransition();
    }

    @AfterMethod
    private void close() {
        closeWebDriver();
    }
}
