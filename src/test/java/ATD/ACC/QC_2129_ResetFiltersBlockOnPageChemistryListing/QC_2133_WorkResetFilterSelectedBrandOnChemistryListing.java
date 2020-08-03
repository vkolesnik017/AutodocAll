package ATD.ACC.QC_2129_ResetFiltersBlockOnPageChemistryListing;

import ATD.Listing_chemicals_Page_Logic;
import ATD.Listing_page_Logic;
import ATD.SetUp;
import io.qameta.allure.Description;
import io.qameta.allure.Flaky;
import io.qameta.allure.Owner;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.sql.SQLException;

import static ATD.CommonMethods.openPage;
import static ATD.SetUp.setUpBrowser;
import static com.codeborne.selenide.Selenide.close;

public class QC_2133_WorkResetFilterSelectedBrandOnChemistryListing {

    private String idBrand, idBtnResetFilter;
    private Listing_chemicals_Page_Logic listingChemicalsPageLogic = new Listing_chemicals_Page_Logic();


    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0");
    }

    @DataProvider(name = "route", parallel = true)
    Object[] dataProvider() throws SQLException {
        return new SetUp().setUpShopWithSubroutes("prod", "DE", "main", "listing_chemicals");
    }

    @Test(dataProvider = "route")
    @Flaky
    @Owner(value = "Sergey-QA")
    @Description(value = "Test Checking work btn reset filter selected brand from reset filter block")
    public void testCheckingWorkBtnResetFilterSelectedBrand(String route) {
        openPage(route);
        idBrand = listingChemicalsPageLogic.getValueFirstVisibleBrandInBlockBrands();
        listingChemicalsPageLogic.clickFirstVisibleBrand();
        new Listing_page_Logic().waitUntilPreloaderDisappear();
        idBtnResetFilter = listingChemicalsPageLogic.getIdFirstBtnResetFilterInResetBlock();
        Assert.assertEquals(idBrand, idBtnResetFilter);
        listingChemicalsPageLogic.clickBtnResetFilterFromFilterBlock();
        new Listing_page_Logic().waitUntilPreloaderDisappear();
        listingChemicalsPageLogic.checkingWhatBrandNotSelected();
    }

    @AfterMethod
    private void tearDown() {
        close();
    }
}
