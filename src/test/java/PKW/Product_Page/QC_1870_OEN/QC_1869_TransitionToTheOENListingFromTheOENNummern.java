package PKW.Product_Page.QC_1870_OEN;

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

public class QC_1869_TransitionToTheOENListingFromTheOENNummern {

    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0");
    }

    @DataProvider(name = "routes", parallel = true)
    Object[] dataProvider() throws SQLException {
        return new Common.SetUp().setUpShopWithSubroutes("prod", "DE", "main", "product4");
    }

    @Test(dataProvider = "routes")
    @Owner(value = "LavrynenkoOlha")
    @Description(value = "Checking the redirection to the OEN listing after clicking the OEN tab")
    public void testCheckingTheRedirectionToTheOenListing(String route) {
        openPage(route);
        new Product_page_Logic().checkingTheTransitionToTheOENListingPageAfterClickingTheOenTab();
    }

    @AfterMethod
    public void close() {
        closeWebDriver();
    }
}
