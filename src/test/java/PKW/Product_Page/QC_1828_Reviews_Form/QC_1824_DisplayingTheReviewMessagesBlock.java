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

public class QC_1824_DisplayingTheReviewMessagesBlock {

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
    @Description(value = "Displaying the Review messages block")
    public void testDisplayingTheReviewMessagesBlock(String route) {
        openPage(route);
        new Product_page_Logic().displayingReviewMessagesBlock();
    }

    @AfterMethod
    public void close() {
        closeWebDriver();
    }
}

