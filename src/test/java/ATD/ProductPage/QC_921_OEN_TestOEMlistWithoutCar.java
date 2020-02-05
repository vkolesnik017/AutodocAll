package ATD.ProductPage;


import ATD.DataBase;
import ATD.Product_page;
import io.qameta.allure.Description;
import io.qameta.allure.Flaky;
import io.qameta.allure.Owner;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.sql.SQLException;

import static ATD.CommonMethods.openPage;
import static ATD.CommonMethods.waitingWhileLinkBecomeExpected;
import static ATD.SetUp.setUpBrowser;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.close;

public class QC_921_OEN_TestOEMlistWithoutCar {
    private Product_page productPage = new Product_page();
    private DataBase dataBase = new DataBase();

    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0");
    }

    @Test
    @Flaky
    @Owner(value = "Romaniuta")
    @Description(value = "Test checks oem list without car")
    public void testOEMlistWithoutCar() throws SQLException {
        openPage("https://autodoc.de/" +  dataBase.getRouteByRouteName("DE", "product15"));
        productPage.boldOenText().shouldNotBe(visible);
        String oenLink = productPage.linkInOemBlock().attr("href");
        productPage.linkInOemBlock().click();
        waitingWhileLinkBecomeExpected(oenLink);
        close();
    }
}
