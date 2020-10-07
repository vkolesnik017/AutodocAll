package ATD.Characteristics.QC_2387_CheckingCharacteristicConditionForProducts;

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

public class QC_2422_CheckingAbsenceCharacteristicZustandBrand3kWithDepositOnProductAndListingPage {

    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0");
    }

    @DataProvider(name = "routes", parallel = true)
    Object[] dataProvider() throws SQLException {
        return new SetUp("ATD").setUpShopWithSubroutes("prod", "DE", "main", "product42");
    }

    @Test(dataProvider = "routes")
    @Flaky
    @Owner(value = "Sergey-QA")
    @Description(value = "Checking for the absence of the deposit characteristic  for 3k brand goods with a deposit on product and listing page")
    public void testCheckingDepositCharacteristicFor3kBrandGoodsWithDeposit(String route) {
        openPage(route);
        new Product_page_Logic().checkingAbsenceZustandCharacteristicFor3kBrandGoodsWithDeposit();
    }


    @DataProvider(name = "routesListing", parallel = true)
    Object[] dataProviderListing() throws SQLException {
        return new SetUp("ATD").setUpShopWithSubroutes("prod", "DE", "main", "product42");
    }

    @Test(dataProvider = "routesListing")
    @Flaky
    @Owner(value = "Sergey-QA")
    @Description(value = "Checking for the absence of the deposit characteristic  for 3k brand goods with a deposit on product and listing page")
    public void test(String route) {
        openPage(route);
        new Product_page_Logic().checkingAbsenceZustandCharacteristicFor3kBrandGoodsWithDeposit();
    }

    @AfterMethod
    public void close() {
        closeWebDriver();
    }
}
