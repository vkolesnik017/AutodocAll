package ATD.Characteristics.QC_2387_CheckingCharacteristicConditionForProducts;

import ATD.LKW_Search_page_Logic;
import ATD.Listing_page_Logic;
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
        setUpBrowser(false, "chrome", "77.0", false);
    }

    @DataProvider(name = "routes", parallel = true)
    Object[] dataProvider() throws SQLException {
        return new SetUp("ATD").setUpShopWithSubroutes("prod", "DE", "main", "product50");
    }

    @Test(dataProvider = "routes")
    @Flaky
    @Owner(value = "Sergey-QA")
    @Description(value = "Checking for the absence of the deposit characteristic for 3k brand goods with deposit on product page")
    public void testCheckingDepositCharacteristicFor3kBrandGoodsWithDeposit(String route) {
        openPage(route);
        new Product_page_Logic().checkingAbsenceZustandCharacteristicForGoodsWithDeposit();
    }


    @DataProvider(name = "routesListing", parallel = true)
    Object[] dataProviderListing() throws SQLException {
        return new SetUp("ATD").setUpShopWithSubroutes("prod", "DE", "main", "category_oen23");
    }

    @Test(dataProvider = "routesListing")
    @Flaky
    @Owner(value = "Sergey-QA")
    @Description(value = "Checking for the absence of the deposit characteristic for 3k brand goods with deposit on listing page")
    public void testCheckingDepositCharacteristicFor3kBrandGoodsWithDepositOnListing(String route) {
        openPage(route);
        new Listing_page_Logic().checkingAbsenceZustandCharacteristicForGoodsWithDeposit();
    }


    @DataProvider(name = "routesListingLkw", parallel = true)
    Object[] dataProviderListingLkw() throws SQLException {
        return new SetUp("ATD").setUpShopWithSubroutes("subprod", "DE", "lkw_main", "lkw_category_car_list46,lkw_search21");
    }

    @Test(dataProvider = "routesListingLkw")
    @Flaky
    @Owner(value = "Sergey-QA")
    @Description(value = "Checking for the absence of the deposit characteristic for 3k brand goods with deposit on listing page")
    public void testLkw_CheckingDepositCharacteristicFor3kBrandGoodsWithDeposit(String route) {
        openPage(route);
        new LKW_Search_page_Logic().checkingAbsenceZustandCharacteristicForGoodsWithDeposit();
    }



    @AfterMethod
    public void close() {
        closeWebDriver();
    }
}
