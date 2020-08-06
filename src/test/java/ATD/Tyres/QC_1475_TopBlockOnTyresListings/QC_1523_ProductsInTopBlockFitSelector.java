package ATD.Tyres.QC_1475_TopBlockOnTyresListings;


import ATD.SetUp;
import ATD.TyresListing_page_Logic;
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
import static com.codeborne.selenide.Selenide.closeWebDriver;

public class QC_1523_ProductsInTopBlockFitSelector {

    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0");
    }

    @DataProvider(name = "routes", parallel = true)
    Object[] dataProvider() throws SQLException {
        return new SetUp().setUpShopWithSubroutes("prod", "DE", "main", "tyre_form,tyre_form2,tyre_form3");
    }

    @Test(dataProvider = "routes")
    @Flaky
    @Owner(value = "Romaniuta")
    @Description(value = "Test Checks Products In Top Block Fit Selector")
    public void testProductsInTopBlockFitSelector(String route) {
        openPage(route);
        new TyresListing_page_Logic().getValueFromSelectorAndCheckTopBlockProducts();
    }


    @AfterMethod
    public void close() {
        closeWebDriver();
    }}
