package ATD.Tyres.QC_1038_CreatingTyresOrder;


import ATD.*;
import AWS.Order_aws;
import io.qameta.allure.Description;
import io.qameta.allure.Flaky;
import io.qameta.allure.Owner;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.sql.SQLException;

import static ATD.CommonMethods.clickOfBuyBtnForAllPages;
import static ATD.CommonMethods.openPage;
import static ATD.SetUp.setUpBrowser;
import static com.codeborne.selenide.Selenide.close;
import static com.codeborne.selenide.Selenide.open;

public class QC_1046_CreatingTyresAndOtherProductOrderShopNotMatchDeliveryCountry {
    private String emailDE = "qc_1046_autotestDE@mailinator.com";
    private String passwordDE = "password";

    private TyresListing_page_Logic tyresListingPageLogic = new TyresListing_page_Logic();
    private DataBase dataBase = new DataBase();

    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0");
    }

    @DataProvider(name = "routes", parallel = true)
    Object[] dataProvider() throws SQLException {
        return new SetUp().setUpShopWithSubroutes("prod", "DE", "main", "tyre_form,tyre_form2,tyre_form3,tyre_form4");
    }

    @Test(dataProvider = "routes")
    @Flaky
    @Owner(value = "Romaniuta")
    @Description(value = "Test checks order with tyres and other product where shop not match delivery country")
    public void testTyresAndOtherProductOrderShopNotMatchDeliveryCountry(String route) throws SQLException {
        openPage(route);
        String tyreId = tyresListingPageLogic.getTyreId();
        clickOfBuyBtnForAllPages();
        tyresListingPageLogic.addFirstProductToCart();
        open("https://autodoc.de/" + dataBase.getRouteByRouteName("DE", "search3"));
        clickOfBuyBtnForAllPages();
        String orderNumber = new Product_page_Logic().closePopupOtherCategoryIfYes().cartClick()
                            .nextButtonClick()
                            .signIn(emailDE, passwordDE)
                            .nextBtnClick()
                            .chooseVorkasse()
                            .nextBtnClick()
                            .checkRemovingTyresFromAlldataWithOtherProducts(tyreId)
                            .nextBtnClick()
                            .getOrderNumber();
        new Order_aws(orderNumber).openOrderInAwsWithLogin();
    }

    @AfterMethod
    private void tearDown() {
        close();
    }
}