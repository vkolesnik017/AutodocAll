package ATD.Characteristics.QC_506_StaticCharacteristics;


import ATD.Listing_page_Logic;
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
import java.util.List;

import static ATD.CommonMethods.openPage;
import static Common.SetUp.setUpBrowser;
import static com.codeborne.selenide.Selenide.closeWebDriver;

public class QC_1901_CharacteristicsDoNotHideAfterBrandFilterApplying {

    private Listing_page_Logic listingPageLogic = new Listing_page_Logic();
    private List<String> expectedCharacteristics;
    private List<String> actualCharacteristics;

    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0");
    }

    @DataProvider(name = "route", parallel = true)
    Object[] dataProvider() throws SQLException {
        return new SetUp("ATD").setUpShopWithSubroutes("prod", "DE", "main", "category_car_list24,search25,category_oen11");
    }

    @Test(dataProvider = "routes")
    @Flaky
    @Owner(value = "Romaniuta")
    @Description(value = "Checks Characteristics Do Not Hide After Brand Filter Applying")
    public void testCharacteristicsDoNotHideAfterBrandFilterApplying(String route) {
        openPage(route);
        expectedCharacteristics = listingPageLogic.getAllExpectedCharacteristcsOfProduct();
        listingPageLogic.getBrandFronFirstProductAndClickBrandButtonInFilter();
        actualCharacteristics = listingPageLogic.getAllExpectedCharacteristcsOfProduct();
        Assert.assertEquals(expectedCharacteristics, actualCharacteristics);
    }

    @AfterMethod
    private void close() {
        closeWebDriver();
    }
}
