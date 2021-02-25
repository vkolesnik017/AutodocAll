package ATD.Selectors.QC_771_RegKbaSelector;

import ATD.Category_car_list_page_Logic;
import ATD.Motoroil_page_Logic;
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
import static ATD.CommonMethods.*;
import static Common.CommonMethods.waitWhileRouteBecomeExpected;
import static Common.SetUp.setUpBrowser;
import static com.codeborne.selenide.Selenide.closeWebDriver;


public class QC_3169 {

    private String firstValueOfKbaNumber = "0603";
    private String secondValueOfKbaNumber = "bqo";

    private DataBase db = new DataBase();


    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0", false);
    }

    @DataProvider(name = "routesKba", parallel = true)
    Object[] dataProviderKba() throws SQLException {
        return new SetUp("ATD").setUpShopsWithSubroute("prod","DE", "main", "motoroil");
    }

    @Test(dataProvider = "routesKba")
    @Flaky
    @Owner(value = "Sergey_QA")
    @Description(value = "Test checks transition to listing with correct KBA number")
    public void testChecksTransitionToListingWithCorrectKbaNumber(String route) {
        openPage(route);
        new Motoroil_page_Logic()
                .sendKbaSelectorFormWithValidData(firstValueOfKbaNumber, secondValueOfKbaNumber);
        waitWhileRouteBecomeExpected("category_car_list");
        new Category_car_list_page_Logic().presenceVehicleInSelector("121", "8607", "107860")
                .presenceVehicleInKbaSelector(firstValueOfKbaNumber, secondValueOfKbaNumber)
                .checkingApplicabilityOfProductForSelectedVehicle();
    }


    @DataProvider(name = "routesReg", parallel = true)
    Object[] dataProviderReg() throws SQLException {
        return new SetUp("ATD").setUpShopsWithSubroute("prod", "DK,FI,FR,IT,NL,NO,PT,SE,CH", "main", "motoroil");
    }

    @Test(dataProvider = "routesReg")
    @Flaky
    @Owner(value = "Sergey_QA")
    @Description(value = "Test checks transition to listing with correct REG number")
    public void testChecksTransitionToListingWithCorrectRegNumber(String route) throws SQLException {
        openPage(route);
        String shop = getCurrentShopFromJSVarInHTML();
        String reg = db.getKba(shop);
        new Motoroil_page_Logic()
                .sendingRegSelectorWithValidData(reg);
        waitWhileRouteBecomeExpected("category_car_list");
        new Category_car_list_page_Logic()
                .presenceVehicleInREGSelector(reg)
                .selectProductInTecDocListing();
    }


    @AfterMethod
    public void close() {
        closeWebDriver();
    }
}
