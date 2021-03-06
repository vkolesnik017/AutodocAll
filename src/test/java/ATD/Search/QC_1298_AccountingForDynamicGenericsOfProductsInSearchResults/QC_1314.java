package ATD.Search.QC_1298_AccountingForDynamicGenericsOfProductsInSearchResults;


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

import static ATD.CommonMethods.openPage;
import static Common.SetUp.setUpBrowser;
import static com.codeborne.selenide.Selenide.closeWebDriver;

public class QC_1314 {

    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0", false);
    }

    @DataProvider(name = "routes", parallel = true)
    Object[] dataProvider() throws SQLException {
        return new SetUp("ATD").setUpShopWithSubroutes("prod", "DE", "main", "maker_car_list9");
    }

    @Test(dataProvider = "routes")
    @Flaky
    @Owner(value = "Romaniuta")
    @Description(value = "Test Checks Search By OEM Considering Dynamic Generics")
    public void testSearchByOEMconsideringDynamicGenerics(String route) {
        openPage(route);
        new Main_page_Logic().useSearch("4403530");
        new Listing_page_Logic().checkGenericAndArticleAndOEMnumber("CORTECO","Wellendichtring, Zwischenwelle", "46085509B", "4403530");
        new Product_page_Logic().closePopupOtherCategoryIfYes()
                .checksPresentProductInCartPopup()
                .cartClick()
                .checkArticleNumberOfProduct("46085509B");
    }

    @AfterMethod
    public void close() {
        closeWebDriver();
    }
}
