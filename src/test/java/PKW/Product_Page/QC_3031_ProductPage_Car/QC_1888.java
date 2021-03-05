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

public class QC_1888 {

    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0", false);
    }

    @DataProvider(name = "routes", parallel = true)
    Object[] dataProvider() throws SQLException {
        return new Common.SetUp("PKW").setUpShopWithSubroutes("prod", "DE", "main", "product13");
    }

    @Test(dataProvider = "routes" )
    @Owner(value = "LavrynenkoOlha")
    @Description(value = "Checking the presence of the elements, checking the transition after clicking the links ang brand logo")
    public void testCheckingThePresenceOfTheElements(String route) throws SQLException {
        openPage(route);
        new Product_page_Logic().presenceOfTheElementsInTheTopBlockOnTheProductPage()
                .checkingTheTransitionToTheBrandProductPageAfterClickingTheBrandLogo()
                .addProductToCart()
                .presenceOfTheElementsInTheCharacteristicBlockOnTheProductPage()
                .presenceOfTheElementsInTheBottomBlockOnTheProductPage();
    }

    @AfterMethod
    public void close() {
        closeWebDriver();
    }
}
