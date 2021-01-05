package PKW.Product_Page.QC_1894_ProductPage_Elements;

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

public class QC_1889 {

    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0",false);
    }

    @DataProvider(name = "routeForDE", parallel = true)
    Object[] dataProvider() throws SQLException {
        return new Common.SetUp("PKW").setUpShopWithSubroutes("prod", "DE", "main", "product4");
    }

    @Test(dataProvider = "routeForDE")
    @Owner(value = "LavrynenkoOlha")
    @Description(value = "Checking the presence of the KBA block on the DE shop")
    public void testCheckingThePresenceOfTheKBABlock(String route) {
        openPage(route);
        new Product_page_Logic().visibilityOfTheKBABlock();
    }


    @DataProvider(name = "routes", parallel = false)
    Object[] dataProviders() throws SQLException {
        return new Common.SetUp("PKW").setUpShopsWithSubroute("prod", "FR,DK,FI,NL,NO,SE,EN", "main", "product4");
    }

    @Test(dataProvider = "routes")
    @Owner(value = "LavrynenkoOlha")
    @Description(value = "Checking the presence of the Reg number block on the FR, DK, FI, NL, NO, SE, UK shops")
    public void testCheckingThePresenceOfTheRegNumberBlock(String routes) {
        openPage(routes);
        new Product_page_Logic().visibilityOfTheRegNumberBlock();
    }


    @DataProvider(name = "routess", parallel = false)
    Object[] dataProviderr() throws SQLException {
        return new Common.SetUp("PKW").setUpShopWithSubroutes("prod", "CH", "main", "product4");
    }

    @Test(dataProvider = "routess")
    @Owner(value = "LavrynenkoOlha")
    @Description(value = "Checking the presence of the Reg number block on the CH shop")
    public void testCheckingThePresenceOfTheRegNumberBlockOnTheCHShop(String routes) {
        openPage(routes);
        new Product_page_Logic().visibilityOfTheRegNumberBlockCH();
    }

    @AfterMethod
    public void close() {
        closeWebDriver();
    }
}