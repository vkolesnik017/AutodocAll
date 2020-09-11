package ATD.Listings.QC_1298_AccountingForDynamicGenericsOfProductsInSearchResults;


import ATD.Listing_page_Logic;
import ATD.Main_page_Logic;
import ATD.Product_page_Logic;
import Common.SetUp;
import io.qameta.allure.Description;
import io.qameta.allure.Flaky;
import io.qameta.allure.Owner;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.sql.SQLException;

import static ATD.CommonMethods.clickOfBuyBtnForAllPages;
import static ATD.CommonMethods.openPage;
import static Common.SetUp.setUpBrowser;
import static com.codeborne.selenide.Selenide.closeWebDriver;

public class QC_1308_SearchByArticleBasedOnDynamicGenerics {

    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0");
    }

    @DataProvider(name = "routes", parallel = true)
    Object[] dataProvider() throws SQLException {
        return new SetUp("ATD").setUpShopWithSubroutes("prod", "AT", "main", "category_car_list27");
    }

    @Test(dataProvider = "routes")
    @Flaky
    @Owner(value = "Romaniuta")
    @Description(value = "Test Checks Search By Article Based On Dynamic Generics")
    public void testSearchByArticleBasedOnDynamicGenerics(String route) {
        openPage(route);
        new Main_page_Logic().useSearch("12336LLECOC1");
        new Listing_page_Logic().checkGenericAndArticleInFirstProduct("Glühlampe, Abbiegescheinwerfer", "12336LLECOC1");
        clickOfBuyBtnForAllPages();
        new Product_page_Logic().closePopupOtherCategoryIfYes()
                .checksPresentProductInCartPopup()
                .cartClick()
                .checkArticleNumberOfProduct("12336LLECOC1");
    }

    @AfterMethod
    public void close() {
        closeWebDriver();
    }
}
