package ATD.Listings.QC_1298_AccountingForDynamicGenericsOfProductsInSearchResults;


import Common.DataBase;
import ATD.Main_page_Logic;
import io.qameta.allure.Description;
import io.qameta.allure.Flaky;
import io.qameta.allure.Owner;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.sql.SQLException;

import static ATD.CommonMethods.openPage;
import static Common.SetUp.setUpBrowser;
import static com.codeborne.selenide.Selenide.closeWebDriver;

public class QC_1309_TestDisplayDynamicProductNameInSearchHints {

    @BeforeClass
    void setUp() {
    setUpBrowser(false, "chrome", "77.0", false);
}

    @DataProvider(name = "searchTexts", parallel = true)
    Object[] dataProviderSearchTexts() {
        return new Object[][]{
                {"12336LLECOC1"},
                {"032005"},
                {"8GH 002 090-133"}
        };
    }

    @Test(dataProvider = "searchTexts")
    @Flaky
    @Owner(value = "Romaniuta")
    @Description(value = "Test Checks Display Dynamic Product Name In Search Hints")
    public void testDisplayDynamicProductNameInSearchHints(String searchText) throws SQLException {
        openPage(new DataBase("ATD").getFullRouteByRouteAndSubroute("prod", "AT", "main", "category_car_list27"));
        new Main_page_Logic().inputTextInSearchBar(searchText)
                             .checkGenericNameInSearchTooltip("Glühlampe, Abbiegescheinwerfer");

    }

    @AfterMethod
    public void close() {
        closeWebDriver();
    }
}