package PKW.Listings.QC_429_Listing_OEN;

import PKW.Oe_number_page_Logic;
import io.qameta.allure.Description;
import io.qameta.allure.Flaky;
import io.qameta.allure.Owner;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import java.sql.SQLException;
import static Common.SetUp.setUpBrowser;
import static PKW.CommonMethods.openPage;
import static com.codeborne.selenide.Selenide.closeWebDriver;


public class QC_432 {


    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0", false);
    }

    @DataProvider(name = "routes", parallel = true)
    Object[] dataProvider() throws SQLException {
        return new Common.SetUp("PKW").setUpShopWithSubroutes("prod", "DE", "main", "oe_number4");
    }

    @Test(dataProvider = "routes")
    @Flaky
    @Owner(value = "Chebanenko")
    @Description(value = "Test checks text in Title on OEN listing")
    public void testChecksTextInTitleOnOenListing(String route) {
        openPage(route);
        String nameTitle = new Oe_number_page_Logic().checksTextInTitlePage();
        Assert.assertEquals(nameTitle, "Ähnliche Produkte von Bremssattel 1K0615124A OE Nummer");
    }


    @AfterMethod
    public void close() {
        closeWebDriver();
    }
}
