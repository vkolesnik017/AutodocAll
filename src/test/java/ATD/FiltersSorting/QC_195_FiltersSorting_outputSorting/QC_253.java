package ATD.FiltersSorting.QC_195_FiltersSorting_outputSorting;


import Common.DataBase;
import ATD.Listing_page_Logic;
import io.qameta.allure.Description;
import io.qameta.allure.Flaky;
import io.qameta.allure.Owner;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.sql.SQLException;

import static ATD.CommonMethods.openPage;
import static Common.SetUp.setUpBrowser;
import static com.codeborne.selenide.Selenide.closeWebDriver;

public class QC_253 {
    private DataBase dataBase = new DataBase("ATD");

    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0", false);
    }

    @Test
    @Flaky
    @Owner(value = "Romaniuta")
    @Description(value = "Test checks addToBasket buttons sorting with brand filter Oem route")
    public void testSortingWithFilterByBrandOem() throws SQLException {
        openPage("https://autodoc.de/" + dataBase.getRouteByRouteName("DE", "category_oen"));
        new Listing_page_Logic().clickFirstBrandNameInFilter()
                .waitUntilPreloaderDisappear()
                .checkAddToBasketButtonsSorting();
    }

    @AfterMethod
    private void close() {
        closeWebDriver();
    }
}
