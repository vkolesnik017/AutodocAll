package PKW.Product_Page.QC_1828_Reviews_Form;

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

public class QC_1822_DisplayingTheReviewPopUpOnTheProductWithoutRating {
    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0");
    }

    @DataProvider(name = "routes", parallel = true)
    Object[] dataProvider() throws SQLException {
        return new Common.SetUp("PKW").setUpShopWithSubroutes("prod", "DE", "main", "product8");
    }

    @Test(dataProvider = "routes")
    @Owner(value = "LavrynenkoOlha")
    @Description(value = "Displaying the Review Form on the product without rating after clicking on the stars")
    public void testDisplayingTheReviewFormWithoutRating(String route) {
        openPage(route);
        new Product_page_Logic().displayingReviewPopUpWithOutRating();
    }

    @AfterMethod
    public void close() {
        closeWebDriver();
    }
}

