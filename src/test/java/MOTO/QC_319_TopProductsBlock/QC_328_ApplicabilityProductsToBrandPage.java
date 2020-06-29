package MOTO.QC_319_TopProductsBlock;

import ATD.Moto_Category_maker_page_Logic;
import ATD.SetUp;
import io.qameta.allure.Description;
import io.qameta.allure.Flaky;
import io.qameta.allure.Owner;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.sql.SQLException;

import static ATD.CommonMethods.openPage;
import static ATD.SetUp.setUpBrowser;
import static com.codeborne.selenide.Selenide.close;

public class QC_328_ApplicabilityProductsToBrandPage {

    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0");
    }

    @DataProvider(name = "routes", parallel = true)
    Object[] dataProvider() throws SQLException {
        return new SetUp().setUpShopWithSubroutes("subprod", "DE", "moto_main", "moto_category_maker,moto_parent_category_maker2");
    }

    @Test(dataProvider = "routes")
    @Flaky
    @Owner(value = "Kolesnik")
    @Description(value = "Test checks Applicability of products from the TOP block to the brand the page on which is open")
    public void testChecksApplicabilityProductsToBrandPage(String route) {
        openPage(route);
       String motoBrand =  new Moto_Category_maker_page_Logic().getMotoBrandFromUrl();
        new Moto_Category_maker_page_Logic().checkApplicabilityMotoAndProduct(motoBrand);
    }

    @AfterMethod
    private void tearDown() {
        close();
    }
}