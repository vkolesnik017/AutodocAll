package PKW.Product_Page.QC_3031_ProductPage_Car;

import PKW.Product_page_Logic;
import io.qameta.allure.Description;
import io.qameta.allure.Owner;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.sql.SQLException;

import static Common.SetUp.setUpBrowser;
import static PKW.CommonMethods.openPage;
import static com.codeborne.selenide.Selenide.closeWebDriver;

public class QC_1868 {

    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0", false);
    }

    @DataProvider(name = "routes", parallel = true)
    Object[] dataProvider() throws SQLException {
        return new Common.SetUp("PKW").setUpShopWithSubroutes("prod", "DE", "main", "car_parts2");
    }

    @Test(dataProvider = "routes", enabled = false)  //TODO Waiting for a reply from a reporter
    @Owner(value = "LavrynenkoOlha")
    @Description(value = "Checking the displaying to the OEN numbers with the selected car in the selector")
    public void testCheckingTheDisplayingOenNumber(String route) {
        openPage(route);
        new Product_page_Logic().checkingTheDisplayingTheOENNumbers();
    }

    @AfterMethod
    public void close() {
        closeWebDriver();
    }
}

