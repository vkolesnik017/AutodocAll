package ATD.ACC.QC_836_MainAccessories;

import ATD.Index_accessories_group_page_Logic;
import ATD.Listing_accessories_page_Logic;
import Common.SetUp;
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
import static Common.SetUp.setUpBrowser;
import static com.codeborne.selenide.Selenide.closeWebDriver;

public class QC_3026 {
    private String nameCategory, nameTitle;

    private Index_accessories_group_page_Logic index_accessories_group_page_logic = new Index_accessories_group_page_Logic();
    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "87.0",false);
    }

    @DataProvider(name = "route", parallel = true)
    Object[] dataProvider() throws SQLException {
        return new SetUp("ATD").setUpShopWithSubroutes("prod", "DE", "main","index_accessories_group1");
    }

    @Test(dataProvider = "route")
    @Flaky
    @Owner(value = "LavrynenkoOlha")
    @Description(value = "Test checks presence of logical union block and transfer here")
    public void testCheckPresenceAOfLogicalUnionBlock(String route) {
        openPage(route);
        new Index_accessories_group_page_Logic().checkingPresenceAccessorizeBlock();
        nameCategory = index_accessories_group_page_logic.getNameFirstCategoryInBlockUnionLogic();
        index_accessories_group_page_logic.clicksOnFirstCategory();
        nameTitle = new Listing_accessories_page_Logic().getTitleName();
        Assert.assertEquals(nameCategory, nameTitle);
    }

    @AfterMethod
    private void close() {
        closeWebDriver();
    }
}