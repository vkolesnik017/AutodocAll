package PKW.Section_ACC.QC_992_MainAccessories;

import Common.SetUp;
import PKW.Index_accessories_page_Logic;
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

import static Common.SetUp.setUpBrowser;
import static PKW.CommonMethods.openPage;
import static com.codeborne.selenide.Selenide.closeWebDriver;


public class QC_1028 {

    private String nameBrand, nameTitleBrand;
    private Index_accessories_page_Logic index_accessories_page_logic = new Index_accessories_page_Logic();


    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0", false);
    }

    @DataProvider(name = "route", parallel = true)
    Object[] dataProviderProducts() throws SQLException {
        return new SetUp("PKW").setUpShopWithSubroutes("prod", "DE", "main", "index_accessories");
    }

    @Test(dataProvider = "route")
    @Flaky
    @Owner(value = "Sergey-QA")
    @Description(value = "Test Checks presence block Top brands And Transition by first brand ")
    public void testCheckingPresenceTopBrandsAndTransitionByFirstBrand(String route) {
        openPage(route);
        index_accessories_page_logic.checkingPresenceBlockTopBrands();
        nameBrand = index_accessories_page_logic.getNameFirstBrandInBlockTopBrands();
        index_accessories_page_logic.clickOnFirstBrandInBlockTopBrands();
        nameTitleBrand = new Supplier_page_Logic().getAndCutNameFromSecondBreadCrumb();
        Assert.assertEquals(nameBrand, nameTitleBrand);

    }

    @AfterMethod
    public void close() {
        closeWebDriver();
    }
}
