package ATD.Catalog.QC_1447_AbsenceOf4xx3xxResponsesInCatalogCategories;


import ATD.Categories_page_Logic;
import Common.SetUp;
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

public class QC_1449_AbsenceOf4xx3xxResponsesInDropdownCatalogCategories {
    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0");
    }

    @DataProvider(name = "routes", parallel = true)
    Object[] dataProvider() throws SQLException {
        return new SetUp().setUpShopWithSubroutes("prod", "DE", "main", "categories");
    }

    @Test(dataProvider = "routes")
    @Flaky
    @Owner(value = "Romaniuta")
    @Description(value = "Test checks Absence Of 4xx 3xx Responses In Dropdown Catalog Categories")
    public void testAbsenceOf4xx3xxResponsesInDropdownCatalogCategories(String route) throws Exception {
        openPage(route);
        new Categories_page_Logic().check200ResponseDropdown();
    }

    @AfterMethod
    public void close() {
        closeWebDriver();
    }
}
