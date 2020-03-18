package ATD.ProductPage;


import ATD.DataBase;
import ATD.Product_page_Logic;
import io.qameta.allure.Description;
import io.qameta.allure.Flaky;
import io.qameta.allure.Owner;
import org.testng.annotations.AfterMethod;
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
    private Product_page_Logic product_page_logic = new Product_page_Logic();
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
        product_page_logic.firstBrandInCompabilityList().shouldHave(text("AUTOBIANCHI")).click();
        product_page_logic.firstModelInFirstBrandInCompatibilityList().click();
        product_page_logic.carListInFirstModelCompabilityList().shouldBe(visible);
        product_page_logic.chooseBrandModelTypeInHorizontalSelector("AUTOBIANCHI", "4822", "16213");
        product_page_logic.selectorSearchBtn().click();
        product_page_logic.compatibleCarInCompabilityList().shouldBe(visible);
    }
    @AfterMethod
    private void tearDown() {
        close();
    }
}
