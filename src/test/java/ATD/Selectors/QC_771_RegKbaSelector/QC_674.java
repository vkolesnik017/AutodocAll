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

import static Common.SetUp.setUpBrowser;
import static com.codeborne.selenide.Selenide.closeWebDriver;
import static com.codeborne.selenide.Selenide.open;

public class QC_674 {

    private Product_page_Logic product_page_logic = new Product_page_Logic();

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
    @Description(value = "Appears error that kba number not filled")
    public void testAppearsErrorThatKbaNumberNotFilled(String route) {
        open(route);
        product_page_logic.displayKbaSelectorErrorToolTip("Geben Sie bitte eine Schlüsselnummer ein, um nach einem Wagen zu suchen");
    }


    @DataProvider(name = "routeBrandLine", parallel = true)
    Object[] dataProviderBrandLine() throws SQLException {

        return new SetUp("ATD").setUpShopWithSubroutes("prod", "DE", "main", "supplier_brand_line");
    }

    @Test(dataProvider = "routeBrandLine")
    @Flaky
    @Owner(value = "Kolesnik")
    @Description(value = "Appears error that kba number not filled")
    public void testAppearsErrorThatKbaNumberNotFilledBrandLine(String route) {
        open(route);
        product_page_logic.displayKbaSelectorErrorToolTip("Geben Sie bitte eine Schlüsselnummer ein, um nach einem Wagen zu suchen");
    }

    @AfterMethod
    public void close() {
        closeWebDriver();
    }
}
