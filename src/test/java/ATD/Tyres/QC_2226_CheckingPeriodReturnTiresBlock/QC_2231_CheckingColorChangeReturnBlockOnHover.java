package ATD.Tyres.QC_2226_CheckingPeriodReturnTiresBlock;

import Common.SetUp;
import ATD.TyresProduct_page_Logic;
import io.qameta.allure.Description;
import io.qameta.allure.Flaky;
import io.qameta.allure.Owner;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import java.sql.SQLException;
import static Common.SetUp.setUpBrowser;
import static com.codeborne.selenide.Selenide.closeWebDriver;
import static com.codeborne.selenide.Selenide.open;

public class QC_2231_CheckingColorChangeReturnBlockOnHover {

    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0");
    }

    @DataProvider(name = "routes", parallel = true)
    Object[] dataProvider() throws SQLException {
        return new SetUp().setUpShopWithSubroutes("prod", "DE", "main", "tyre_item");
    }

    @Test(dataProvider = "routes")
    @Flaky
    @Owner(value = "Sergey-QA")
    @Description(value = "Test checks color change on hover text from block with return period")
    public void testChecksColorChangeOnHoverTextFromBlockReturnPeriod(String route) {
        open(route);
        new TyresProduct_page_Logic().checkingColorChangeOnHoverTextFromBlockReturnPeriod();
    }

    @AfterMethod
    public void close() {
        closeWebDriver();
    }

}
