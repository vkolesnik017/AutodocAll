package ATD.ACC.QC_1276_BlockTop6ProductsOnMainTools;

import ATD.Index_instruments_page_Logic;
import ATD.SetUp;
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

public class QC_1280_NumberOfMiniCardsBlockTop6Products {

    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0");
    }

    @DataProvider(name = "route", parallel = true)
    Object[] dataProvider() throws SQLException {
        return new SetUp().setUpShopWithSubroutes("prod", "DE", "main","index_instruments");
    }

    @Test(dataProvider = "route")
    @Flaky
    @Owner(value = "Sergey-QA")
    @Description(value = "Test Checks the number of mini-cards in top products block")
    public void testCheckNumberOfMiniCardsTopProductsForInstruments(String route) {
        openPage(route);
        new Index_instruments_page_Logic().checkingNumberOfMiniCardsTop6Products();
    }

    @AfterMethod
    private void tearDown() {
        close();
    }
}
