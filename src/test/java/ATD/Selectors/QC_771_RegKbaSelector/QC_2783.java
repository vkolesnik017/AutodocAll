package ATD.Selectors.QC_771_RegKbaSelector;

import ATD.Main_page_Logic;
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


public class QC_2783 {

    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0", false);
    }

    @DataProvider(name = "routes", parallel = true)
    Object[] dataProvider()  {
        return new SetUp("ATD").setUpShopsWithMainRoute("prod", "DE", "main");
    }

    @DataProvider(name = "routes2", parallel = true)
    Object[] dataProvider2() throws SQLException {
        return new SetUp("ATD").setUpShopWithSubroutes("prod", "DE", "main", "search6,maker_car_list,category_oen");
    }

    @Test(dataProvider = "routes")
    @Flaky
    @Owner(value = "Sergey_QA")
    @Description(value = "Test presence of mandatory elements in the KBA number block")
    public void testCheckingPresenceMandatoryElementsInKbaBlock(String route) {
        openPage(route);
        new Main_page_Logic().checkPresenceAllElementsInKbaSelectors();
    }

    @Test(dataProvider = "routes2")
    @Flaky
    @Owner(value = "Sergey_QA")
    @Description(value = "Test presence of mandatory elements in the KBA number block")
    public void test2CheckingPresenceMandatoryElementsInKbaBlock(String route) {
        openPage(route);
        new Main_page_Logic().checkPresenceAllElementsInKbaSelectors();
    }

    @AfterMethod
    public void close() {
        closeWebDriver();
    }
}
