package ATD.Associated.QC_802_AnalogsForProductsNotInStock;


import ATD.Product_page_Logic;
import Common.SetUp;
import AWS.ProductCard_aws;
import io.qameta.allure.Description;
import io.qameta.allure.Flaky;
import io.qameta.allure.Owner;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.sql.SQLException;
import java.util.ArrayList;

import static ATD.CommonMethods.openPage;
import static Common.SetUp.setUpBrowser;
import static com.codeborne.selenide.Selenide.closeWebDriver;

public class QC_806_TestEmpfohlenerProductsMatchAws {

    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0");
    }

    @DataProvider(name = "routes", parallel = true)
    Object[] dataProvider() throws SQLException {
        return new SetUp("ATD").setUpShopWithSubroutes("prod", "DE", "main", "product24");
    }

    @Test(dataProvider = "routes")
    @Flaky
    @Owner(value = "Romaniuta")
    @Description(value = "Test Checks Empfohlener Products Match Aws")
    public void testEmpfohlenerProductsMatchAws(String route) {
        openPage(route);
        ArrayList articleNumbers = new Product_page_Logic().checkProductInStockAlternativeBlock()
                             .addArtikelNumberToCollection();
        new ProductCard_aws().checkAlternativesInAws(articleNumbers);
    }

    @AfterMethod
    public void close() {
        closeWebDriver();
    }

}
