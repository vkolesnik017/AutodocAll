package ATD.FiltersSorting.QC_195_FiltersSorting_outputSorting;


import ATD.DataBase;
import ATD.Listing_page;
import io.qameta.allure.Description;
import io.qameta.allure.Flaky;
import io.qameta.allure.Owner;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.sql.SQLException;

import static ATD.CommonMethods.openPage;
import static ATD.SetUp.setUpBrowser;
import static com.codeborne.selenide.Condition.attribute;
import static com.codeborne.selenide.Selenide.close;

public class QC_253_FiltersSorting_TestOutputSortingWithFilterByBrandOen {
    private Listing_page listingPage = new Listing_page();
    private DataBase dataBase = new DataBase();

    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0");
    }

    @Test
    @Flaky
    @Owner(value = "Romaniuta")
    @Description(value = "Test checks addToBasket buttons sorting with brand filter Oem route")
    public void testSortingWithFilterByBrandOem() throws SQLException {
        openPage("https://autodoc.de/" +  dataBase.getRouteByRouteName("DE", "category_oen"));
        listingPage.firstBrandInFilterButton().click();
        listingPage.preloader().shouldBe(attribute("style", "display: none;"));
        listingPage.checkAddToBasketButtonsSorting();
        close();
    }
}
