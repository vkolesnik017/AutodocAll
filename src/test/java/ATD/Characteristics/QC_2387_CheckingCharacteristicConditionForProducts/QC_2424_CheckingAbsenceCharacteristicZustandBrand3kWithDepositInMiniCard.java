package ATD.Characteristics.QC_2387_CheckingCharacteristicConditionForProducts;

import ATD.Category_name_brand_page_Logic;
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

public class QC_2424_CheckingAbsenceCharacteristicZustandBrand3kWithDepositInMiniCard {

    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0");
    }

    @DataProvider(name = "routes", parallel = true)
    Object[] dataProvider() throws SQLException {
        return new SetUp("ATD").setUpShopWithSubroutes("prod", "DE", "main", "category_name_brand9");
    }

    @Test(dataProvider = "routes")
    @Flaky
    @Owner(value = "Sergey-QA")
    @Description(value = "Checks the lack of characterization Zustand for 3K brand products with collateral in mini cards")
    public void test(String route) throws SQLException {
        openPage(route);
        new Category_name_brand_page_Logic().checkLackCharacterizationZustandFor3KBrandProducts();
    }

    @AfterMethod
    public void close() {
        closeWebDriver();
    }
}
