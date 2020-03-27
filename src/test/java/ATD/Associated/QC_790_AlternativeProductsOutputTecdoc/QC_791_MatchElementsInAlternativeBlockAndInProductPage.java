package ATD.Associated.QC_790_AlternativeProductsOutputTecdoc;


import ATD.Product_page_Logic;
import io.qameta.allure.Description;
import io.qameta.allure.Flaky;
import io.qameta.allure.Owner;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static ATD.CommonMethods.openPage;
import static ATD.SetUp.setUpBrowser;

public class QC_791_MatchElementsInAlternativeBlockAndInProductPage {

    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0");
    }

    @Test
    @Flaky
    @Owner(value = "Romaniuta")
    @Description(value = "Test Checks Match Elements In Alternative Block And In Product Page")
    public void testMatchElementsInAlternativeBlockAndInProductPage() {
        openPage("https://www.autodoc.de/japanparts/7533961");
        new Product_page_Logic().checkLinkInAlternativeBlock();
    }
}
