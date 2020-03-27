package ATD.Associated.QC_802_AnalogsForProductsNotInStock;


import ATD.Product_page_Logic;
import io.qameta.allure.Description;
import io.qameta.allure.Flaky;
import io.qameta.allure.Owner;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static ATD.CommonMethods.openPage;
import static ATD.SetUp.setUpBrowser;
import static com.codeborne.selenide.Selenide.open;

public class QC_808_TestEmpfohlenerProductsMatchCar {

    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0");
    }

    @Test
    @Flaky
    @Owner(value = "Romaniuta")
    @Description(value = "Test Checks Empfohlener Products Match Car")
    public void testEmpfohlenerProductsMatchCar() {
        openPage("https://www.autodoc.de/ersatzteile/vw/golf/golf-iv-1j1/8799-1-4-16v");
        open("https://www.autodoc.de/automega/7868162");
        new Product_page_Logic().checkAnalogProductMatchCar();
    }
}
