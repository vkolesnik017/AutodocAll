package ATD.Associated.QC_802_AnalogsForProductsNotInStock;


import ATD.Product_page_Logic;
import AWS.ProductCard_aws;
import io.qameta.allure.Description;
import io.qameta.allure.Flaky;
import io.qameta.allure.Owner;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.ArrayList;

import static ATD.CommonMethods.openPage;
import static ATD.SetUp.setUpBrowser;

public class QC_806_TestEmpfohlenerProductsMatchAws {

    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0");
    }

    @Test
    @Flaky
    @Owner(value = "Romaniuta")
    @Description(value = "Test Checks Empfohlener Products Match Aws")
    public void testEmpfohlenerProductsMatchAws() {
        openPage("https://www.autodoc.de/automega/7868162");
        ArrayList articleNumbers = new Product_page_Logic().checkProductInStockAlternativeBlock()
                             .addArtikelNumberToCollection();
        new ProductCard_aws().checkAlternativesInAws(articleNumbers);
    }

}
