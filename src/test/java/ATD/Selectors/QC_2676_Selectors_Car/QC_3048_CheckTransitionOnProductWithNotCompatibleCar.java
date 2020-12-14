package ATD.Selectors.QC_2676_Selectors_Car;

import ATD.Product_page_Logic;
import Common.DataBase;
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
import static ATD.CommonMethods.openPage;
import static Common.SetUp.setUpBrowser;
import static com.codeborne.selenide.Selenide.closeWebDriver;

public class QC_3048_CheckTransitionOnProductWithNotCompatibleCar {

    private Product_page_Logic product_page_logic = new Product_page_Logic();

    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0");
    }

    @DataProvider(name = "route", parallel = true)
    Object[] dataProvider() throws SQLException {
        return new SetUp("ATD").setUpShopWithSubroutes("prod", "DE", "main", "product48");
    }

    @Test(dataProvider = "route")
    @Flaky
    @Owner(value = "Kolesnik")
    @Description(value = "Compatibility list: Checking the transition from a product to a part that is NOT compatible with the selected vehicle")
    public void testCheckTransitionOnProductWithNotCompatibleCar(String route) throws SQLException {
        openPage(route);
        product_page_logic.selectCarHorizontalSelector("121", "1994", "8799")
                .clickSearchBtnInHorizontalSelector()
                .incompatibilityOfCarAndProductPopUp("Es tut uns leid! Dieses Ersatzteil ist nicht mit dem von Ihnen gewählten Fahrzeug kompatibel. Wir leiten Sie zu unserem Katalog weiter, so können Sie das richtige Teil auswählen");
        checkingContainsUrl(new DataBase("ATD").getRouteByRouteName("DE", "maker_car_list3"));

    }

    @AfterMethod
    public void close() {
        closeWebDriver();
    }
}
