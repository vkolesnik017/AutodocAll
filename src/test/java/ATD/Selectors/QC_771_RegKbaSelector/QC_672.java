package ATD.Selectors.QC_771_RegKbaSelector;

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

import static Common.CommonMethods.waitWhileRouteBecomeExpected;
import static Common.SetUp.setUpBrowser;
import static com.codeborne.selenide.Selenide.closeWebDriver;
import static com.codeborne.selenide.Selenide.open;

public class QC_672 {

    private Product_page_Logic productPage = new Product_page_Logic();

    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0", false);
    }


    @DataProvider(name = "route", parallel = true)
    Object[] dataProvider() throws SQLException {
        return new SetUp("ATD").setUpShopWithSubroutes("prod", "DE", "main", "product49");
    }

    @Test(dataProvider = "route")
    @Flaky
    @Owner(value = "Kolesnik")
    @Description(value = "Use horizontal selector when chosen existing kna number")
    public void testUseHorizontalSelectorWhenChosenExistingKBA(String route) {
        open(route);
        productPage.fillNumberKba("0603", "419")
                .clickKbaBtnAndClosePopUpKbaError();
        waitWhileRouteBecomeExpected("maker_car_list");
    }

    @DataProvider(name = "routeBrandLine", parallel = true)
    Object[] dataProviderBrandLineBrandLine() throws SQLException {
        return new SetUp("ATD").setUpShopWithSubroutes("prod", "DE", "main", "supplier_brand_line");
    }

    @Test(dataProvider = "routeBrandLine")
    @Flaky
    @Owner(value = "Kolesnik")
    @Description(value = "Use horizontal selector when chosen existing kna number")
    public void testUseHorizontalSelectorWhenChosenExistingKBABrandLine(String route) {
        open(route);
        productPage.fillNumberKba("0603", "419")
                .clickKbaBtnAndClosePopUpKbaError();
        waitWhileRouteBecomeExpected("maker_car_list");
    }

    @AfterMethod
    public void close() {
        closeWebDriver();
    }
}
