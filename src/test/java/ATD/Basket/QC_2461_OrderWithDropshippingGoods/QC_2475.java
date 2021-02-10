package ATD.Basket.QC_2461_OrderWithDropshippingGoods;

import ATD.Product_page_Logic;
import AWS.Order_aws;
import Common.DataBase;
import Common.SetUp;
import io.qameta.allure.Description;
import io.qameta.allure.Flaky;
import io.qameta.allure.Owner;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.sql.SQLException;

import static ATD.CommonMethods.*;
import static Common.SetUp.setUpBrowser;
import static com.codeborne.selenide.Selenide.closeWebDriver;

public class QC_2475 {

    private String mail = "QC_2475autotest@mailinator.com";
    private DataBase bd = new DataBase("ATD");

    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0", false);
    }

    @DataProvider(name = "route", parallel = true)
    Object[] dataProviderProducts() throws SQLException {
        return new SetUp("ATD").setUpShopsWithSubroute("prod", "NL", "main", "product70");
    }

    @Test(dataProvider = "route")
    @Flaky
    @Owner(value = "Chelombitko")
    @Description(value = "Checking the countries of exclusions, when ordering dropshipping goods with an indication of the company")
    public void testOrderingDropshippingGoodsWithSpecifiedEntity(String route) throws SQLException {
        openPage(route);
        String shop = getCurrentShopFromJSVarInHTML();
        String nameFirm = bd.getFirmData("firm_atd", shop, "Name");
        String idFirm = bd.getFirmData("firm_atd", shop, "ID");
        String indexOfCountry = bd.getFirmData("firm_atd", shop, "Index");
        String city = bd.getFirmData("firm_atd", shop, "City");
        String orderID = new Product_page_Logic().addProductToCart()
                .closePopupOtherCategoryIfYes()
                .cartClick()
                .nextButtonClick()
                .signIn(mail, password)
                .fillAllFieldsAndFirmForShipping(shop, indexOfCountry, nameFirm, city)
                .fillFieldIdCompanyShipping(idFirm)
                .nextBtnClick()
                .clickOnTheDesiredPaymentMethod(shop, "Bank")
                .nextBtnClick()
                .checkAbsenceOfVatPercentage()
                .nextBtnClick()
                .getOrderNumber();
        Order_aws order_aws = new Order_aws(orderID);
        order_aws.openOrderInAwsWithLogin()
                .checkVatStatusInOrder("Ohne Mwst")
                .reSaveOrder()
                .checkVatStatusInOrder("Ohne Mwst");
    }

    @AfterMethod
    private void close() {
        closeWebDriver();
    }
}
