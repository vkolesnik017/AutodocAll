package ATD.ProductPage;


import ATD.DataBase;
import ATD.Main_page;
import ATD.Product_page_Logic;
import com.codeborne.selenide.Condition;
import io.qameta.allure.Description;
import io.qameta.allure.Flaky;
import io.qameta.allure.Owner;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.sql.SQLException;

import static ATD.CommonMethods.openPage;
import static ATD.SetUp.setUpBrowser;
import static com.codeborne.selenide.Selenide.close;

public class QC_951_CompatibilityList_TestIncompatibilityMessage {
    private Product_page_Logic product_page_logic = new Product_page_Logic();
    private Main_page mainPage = new Main_page();
    private DataBase dataBase = new DataBase();

    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0");
    }

    @Test
    @Flaky
    @Owner(value = "Romaniuta")
    @Description(value = "Test checks incompatibility message")
    public void testIncompatibilityMessage() throws SQLException {
        openPage("https://autodoc.de/" +  dataBase.getRouteByRouteName("DE", "maker_car_list7"));
        mainPage.searchBar().sendKeys("Bremsscheiben");
        mainPage.searchButton().click();
        product_page_logic.productOnListing().click();
        product_page_logic.incompatibilityMessage().shouldBe(Condition.visible);
        close();
    }
}
