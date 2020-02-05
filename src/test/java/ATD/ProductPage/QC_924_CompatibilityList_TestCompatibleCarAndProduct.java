package ATD.ProductPage;


import ATD.DataBase;
import ATD.Product_page;
import io.qameta.allure.Description;
import io.qameta.allure.Flaky;
import io.qameta.allure.Owner;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.sql.SQLException;

import static ATD.CommonMethods.closeCookiesFooterMessage;
import static ATD.CommonMethods.openPage;
import static ATD.SetUp.setUpBrowser;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.close;

public class QC_924_CompatibilityList_TestCompatibleCarAndProduct {
    private Product_page productPage = new Product_page();
    private DataBase dataBase = new DataBase();
    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0");
    }

    @Test
    @Flaky
    @Owner(value = "Romaniuta")
    @Description(value = "Test checks product compatibility with car")
    public void testCompatibleCarAndProduct() throws SQLException {
        openPage("https://autodoc.de/" +  dataBase.getRouteByRouteName("DE", "product16"));
        closeCookiesFooterMessage();
        productPage.firstBrandInCompabilityList().shouldHave(text("AUTOBIANCHI")).click();
        productPage.firstModelInFirstBrandInCompatibilityList().click();
        productPage.carListInFirstModelCompabilityList().shouldBe(visible);
        productPage.chooseBrandModelTypeInSelector("AUTOBIANCHI", "4822", "16213");
        productPage.selectorSearchBtn().click();
        productPage.compatibleCarInCompabilityList().shouldBe(visible);
        close();
    }
}
