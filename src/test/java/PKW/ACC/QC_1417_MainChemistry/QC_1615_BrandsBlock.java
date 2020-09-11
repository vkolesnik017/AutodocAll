package PKW.ACC.QC_1417_MainChemistry;


import Common.SetUp;
import PKW.Index_chemicals_page_Logic;
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

public class QC_1615_BrandsBlock {

    private String nameBrand, nameCrumb;
    private Index_chemicals_page_Logic index_chemicals_page_logic = new Index_chemicals_page_Logic();

    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0");
    }

    @DataProvider(name = "route", parallel = true)
    Object[] dataProviderProducts() throws SQLException {
        return new SetUp("PKW").setUpShopWithSubroutes("prod", "DE", "main", "index_chemicals");
    }

    @Test(dataProvider = "route")
    @Flaky
    @Owner(value = "Sergey-QA")
    @Description(value = "Test checks presence brands block and transition from first brands.")
    public void testCheckingBrandsBlockAndTransitionFromFirstBrand(String route) {
        openPage(route);
        nameBrand = index_chemicals_page_logic.getNameFirstBrandInTopBrandsBlock();
        index_chemicals_page_logic.checkingPresenceTopBrandsBlock()
                .clickFirstBrandInTopBrandsBlock();
        nameCrumb = new Supplier_page_Logic().getAndCutNameFromSecondBreadCrumb();
        Assert.assertEquals(nameBrand, nameCrumb);
    }

    @AfterMethod
    public void close() {
        closeWebDriver();
    }
}
