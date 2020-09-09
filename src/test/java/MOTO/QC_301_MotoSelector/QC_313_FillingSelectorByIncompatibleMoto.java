package MOTO.QC_301_MotoSelector;

import Common.DataBase;
import ATD.Moto_Product_page_Logic;
import Common.SetUp;
import io.qameta.allure.Description;
import io.qameta.allure.Flaky;
import io.qameta.allure.Owner;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.sql.SQLException;

import static ATD.CommonMethods.checkingContainsUrl;
import static Common.SetUp.setUpBrowser;
import static com.codeborne.selenide.Selenide.*;

public class QC_313_FillingSelectorByIncompatibleMoto {
    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0");
    }

    @DataProvider(name = "routes", parallel = true)
    Object[] dataProvider() throws SQLException {
        return new SetUp().setUpShopWithSubroutes("subprod", "DE", "moto_main", "moto_product");

    }

    @Test(dataProvider = "routes")
    @Flaky
    @Owner(value = "Kolesnik")
    @Description(value = "Test checks filling selector by incompatible motorcycle on product page")
    public void testChecksFillingSelectorByIncompatibleMoto(String route) throws SQLException {
        open(route);

        new Moto_Product_page_Logic()
                .selectMotoInHorizontalSelector("4057", "13475", "109218")
                .visibilityOfErrorMessage();
        checkingContainsUrl(new DataBase().getFullRouteByRouteAndSubroute("subprod", "DE", "moto_main", "moto_catalog3"));

    }

    @AfterMethod
    public void close() {
        closeWebDriver();
    }
}
