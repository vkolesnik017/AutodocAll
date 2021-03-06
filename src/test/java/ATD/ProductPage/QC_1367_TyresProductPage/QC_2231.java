package ATD.ProductPage.QC_1367_TyresProductPage;

import ATD.Tyre_item_page_Logic;
import Common.SetUp;
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

public class QC_2231 {

    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0", false);
    }

    @DataProvider(name = "routes", parallel = true)
    Object[] dataProvider() throws SQLException {
        return new SetUp("ATD").setUpShopWithSubroutes("prod", "DE", "main", "tyre_item");
    }

    @Test(dataProvider = "routes")
    @Flaky
    @Owner(value = "Sergey-QA")
    @Description(value = "Test checks color change on hover text from block with return period")
    public void testChecksColorChangeOnHoverTextFromBlockReturnPeriod(String route) {
        open(route);
        new Tyre_item_page_Logic().checkingColorChangeOnHoverTextFromBlockReturnPeriod();
    }

    @AfterMethod
    public void close() {
        closeWebDriver();
    }

}
