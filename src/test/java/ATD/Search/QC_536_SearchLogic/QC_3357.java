package ATD.Search.QC_536_SearchLogic;

import ATD.Main_page_Logic;
import AWS.DisabledCategories_aws;
import AWS.ProductSearch_aws;
import Common.SetUp;
import io.qameta.allure.Description;
import io.qameta.allure.Flaky;
import io.qameta.allure.Owner;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.sql.SQLException;
import java.util.List;

import static Common.SetUp.setUpBrowser;
import static com.codeborne.selenide.Selenide.closeWebDriver;

public class QC_3357 {

    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0", false);
    }

    @DataProvider(name = "route")
    Object[] dataProvider() throws SQLException {
        return new SetUp("ATD").setUpShopsWithMainRoute("prod", "DE", "main");
    }

    @Test(dataProvider = "route")
    @Flaky
    @Owner(value = "Kolesnik")
    @Description(value = "Checking the display of products disabled by brand and generic")
    public void testCheckingDisplayOfProductsDisabledByBrandAndGeneric(String route) throws SQLException {

        List<String> countries = new DisabledCategories_aws()
                .openDisabledCategoriesInAwsWithLogin()
                .setSkin("atd")
                .setBrand("30")
                .setCategoryId("1")
                .clickSearch()
                .getProjectLanguage();
        String artNumOfProduct = new ProductSearch_aws()
                .openProductSearchPage()
                .selectCategory("Starterbatterie ( Starter Battery ) [1]")
                .setBrand("BOSCH")
                .clickSearch()
                .getArtNumberOfProduct(0);
        new Main_page_Logic().checkDisabledCategory(countries, artNumOfProduct);
    }

    @AfterMethod
    public void close() {
        closeWebDriver();
    }
}
