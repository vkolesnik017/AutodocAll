package PKW.ACC.QC_1745_MainTools;

import PKW.Index_instruments_page_Logic;
import PKW.SetUp;
import PKW.Supplier_page_Logic;
import io.qameta.allure.Description;
import io.qameta.allure.Flaky;
import io.qameta.allure.Owner;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import java.sql.SQLException;
import static PKW.CommonMethods.openPage;
import static PKW.SetUp.setUpBrowser;
import static com.codeborne.selenide.Selenide.close;


public class QC_1761_BlockBrandsOnMainTools {

    private String nameBrand, nameBrandPage;
    private Index_instruments_page_Logic index_instruments_page_logic = new Index_instruments_page_Logic();

    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0");
    }

    @DataProvider(name = "route", parallel = true)
    Object[] dataProviderProducts() throws SQLException {
        return new SetUp().setUpShopWithSubroutes("prod", "DE", "main", "index_instruments");
    }

    @Test(dataProvider = "route")
    @Flaky
    @Owner(value = "Sergey-QA")
    @Description(value = "Test Checking presence top brands block and transition by first brand .")
    public void testCheckingPresenceTopBrandsBlockAndTransitionByFirstBrand(String route) {
        openPage(route);
        index_instruments_page_logic.checkingPresenceTopBrandsBlock();
        nameBrand = index_instruments_page_logic.getNameFirstBrandInTopBrandsBlock();
        index_instruments_page_logic.clickFirstBrandInTopBrandsBlock();
        nameBrandPage = new Supplier_page_Logic().getAndCutNameFromSecondBreadCrumb();
        Assert.assertEquals(nameBrand,nameBrandPage);
    }

    @AfterMethod
    private void tearDown() {
        close();
    }
}
