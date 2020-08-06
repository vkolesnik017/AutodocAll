package ATD.FiltersSorting.QC_404_FiltersSorting_byPrice;


import ATD.DataBase;
import ATD.Listing_page;
import com.codeborne.selenide.CollectionCondition;
import io.qameta.allure.Description;
import io.qameta.allure.Flaky;
import io.qameta.allure.Owner;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.sql.SQLException;

import static ATD.CommonMethods.openPage;
import static ATD.SetUp.setUpBrowser;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.closeWebDriver;
import static com.codeborne.selenide.Selenide.executeJavaScript;

public class QC_421_FiltersSorting_TestPriceFilterWithFewProducts {
    private Listing_page listingPage = new Listing_page();
    private DataBase dataBase = new DataBase();

    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0");
    }

    @Test
    @Flaky
    @Owner(value = "Romaniuta")
    @Description(value = "Test checks price filter on listing with few products")
    public void checkPriceFilterWithFewProducts() throws SQLException {
        openPage("https://autodoc.de/" +  dataBase.getRouteByRouteName("DE", "category_car_list3"));
        executeJavaScript("arguments[0].value='15';", listingPage.minPriceValue());
        executeJavaScript("arguments[0].value='16';", listingPage.maxPriceValue());
        listingPage.priceFilterSubmitButton().click();
        listingPage.minPriceMapping().shouldHave(text("15"));
        listingPage.maxPriceMapping().shouldHave(text("16"));
        listingPage.priceFilterBlock().shouldBe(visible);
        listingPage.productTitleInListMode().shouldBe(CollectionCondition.sizeLessThan(5));
    }

    @AfterMethod
    private void close() {
        closeWebDriver();
    }
}
