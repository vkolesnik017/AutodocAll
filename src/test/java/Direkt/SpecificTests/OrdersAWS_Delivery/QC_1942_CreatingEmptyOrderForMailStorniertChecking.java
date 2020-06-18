package Direkt.SpecificTests.OrdersAWS_Delivery;

import Direkt.Product_page_Logic;
import Direkt.SetUp;
import io.qameta.allure.Description;
import io.qameta.allure.Flaky;
import io.qameta.allure.Owner;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.sql.SQLException;

import static Direkt.SetUp.setUpBrowser;
import static com.codeborne.selenide.Selenide.close;
import static com.codeborne.selenide.Selenide.open;


public class QC_1942_CreatingEmptyOrderForMailStorniertChecking {


    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0");
    }

    @DataProvider(name = "route", parallel = true)
    Object[] dataProviderProducts() throws SQLException {
        return new SetUp().setUpShopWithSubroutes("prod", "DE", "main", "product2,product3,product4,product5,product6");
    }

    @Test(dataProvider = "route")
    @Flaky
    @Owner(value = "alex_qa")
    @Description(value = "Test checks translation of error popup on address page")
    public void testCreatingEmptyOrders(String route) {
        System.out.println(route);
        open(route);
        new Product_page_Logic().addProductToCart().cartClick()
        .nextButtonClick()
        .signIn("k.maierska@autodoc.eu", "123456")
        .fillingShippingReqField("DE", "12345", "12345", false).checkingSameAddressCheckbox().nextBtnClick()
        .clickVorkasse().nextBtnClick()
        .nextBtnClick()
        .checkLabelConfirm();
    }

    @AfterMethod
    private void teatDown() {
        close();
    }
}