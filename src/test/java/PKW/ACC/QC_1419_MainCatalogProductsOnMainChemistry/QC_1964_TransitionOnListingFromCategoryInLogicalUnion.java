package PKW.ACC.QC_1419_MainCatalogProductsOnMainChemistry;

import PKW.Index_chemicals_page_Logic;
import PKW.Listing_chemicals_page_Logic;
import PKW.SetUp;
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


public class QC_1964_TransitionOnListingFromCategoryInLogicalUnion {

    private String nameCategory, titleNameCategory;
    Index_chemicals_page_Logic index_chemicals_page_logic = new Index_chemicals_page_Logic();

    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0");
    }

    @DataProvider(name = "route", parallel = true)
    Object[] dataProviderProducts() throws SQLException {
        return new SetUp().setUpShopWithSubroutes("prod", "DE", "main", "index_chemicals");
    }

    @Test(dataProvider = "route")
    @Flaky
    @Owner(value = "Sergey-QA")
    @Description(value = "Test checks transition on listing from category logical union.")
    public void testCheckingTransitionFromCategoryLogicalUnion(String route) {
        openPage(route);
        nameCategory = index_chemicals_page_logic.getTextFirstCategoryInLogicalUnion();
        index_chemicals_page_logic.clickFirstCategoryInLogicalUnion();
        titleNameCategory = new Listing_chemicals_page_Logic().getTitleNameListingPage();
        Assert.assertEquals(nameCategory, titleNameCategory);
    }

    @AfterMethod
    private void tearDown() {
        close();
    }
}