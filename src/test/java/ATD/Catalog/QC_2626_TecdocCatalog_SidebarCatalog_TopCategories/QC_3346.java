package ATD.Catalog.QC_2626_TecdocCatalog_SidebarCatalog_TopCategories;

import ATD.Categories_maker_page_Logic;
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
import static Common.CommonMethods.waitWhileRouteBecomeExpected;
import static Common.SetUp.setUpBrowser;
import static com.codeborne.selenide.Selenide.closeWebDriver;

public class QC_3346 {

    Categories_maker_page_Logic categoryMakerPage = new Categories_maker_page_Logic();

    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0", false);
    }

    @DataProvider(name = "routes", parallel = true)
    Object[] dataProvider() throws SQLException {
        return new SetUp("ATD").setUpShopWithSubroutes("prod", "DE", "main", "categories_maker");
    }

    @Test(dataProvider = "routes")
    @Flaky
    @Owner(value = "Kolesnik")
    @Description(value = "Test check presence of tyres category ")
    public void testCheckPresenceOfTyresCategory(String route) {
        openPage(route);
        int positionOfParentCategory = categoryMakerPage.presenceOfTecDocCatalog().getPositionOfParentCategory("Reifen");
        Assert.assertEquals(positionOfParentCategory, 0);
        categoryMakerPage.clickOnParentCategoryByTitle("Reifen");
        waitWhileRouteBecomeExpected("tyres_maker");
    }

    @AfterMethod
    public void close() {
        closeWebDriver();
    }
}
