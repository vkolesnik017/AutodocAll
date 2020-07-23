package ATD.Basket.QC_1873_SafeOrder;

import ATD.Product_page_Logic;
import ATD.SetUp;
import AWS.Order_aws;
import io.qameta.allure.Description;
import io.qameta.allure.Flaky;
import io.qameta.allure.Owner;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.sql.SQLException;

import static ATD.CommonMethods.openPage;
import static ATD.CommonMethods.password;
import static ATD.SetUp.setUpBrowser;
import static com.codeborne.selenide.Selenide.close;

public class QC_1874_Unchecked_SO_CheckboxOn_FR_DE_LanguageVersions {

    private String mail = "QC_1874_autotest@mailinator.com";

    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0");
    }

    @DataProvider(name = "route", parallel = true)
    Object[] dataProviderProducts() throws SQLException {
        return new SetUp().setUpShopWithSubroutes("prod", "DE", "main", "product32");
    }

    @Test(dataProvider = "route")
    @Flaky
    @Owner(value = "Chelombitko")
    @Description(value = "Test checks Unchecked SO checkbox on FR / DE language versions")
    public void testUnchecked_SO_CheckboxOn_FR_DE_LanguageVersions(String route) throws SQLException {
        openPage(route);
        String orderNumber =  new Product_page_Logic().addProductToCart()
                .closePopupOtherCategoryIfYes()
                .cartClick()
                .nextButtonClick()
                .signIn(mail, password)
                .fillFieldTelNumForShipping("200+002")
                .nextBtnClick()
                .chooseVorkasse()
                .nextBtnClick()
                .checkPresenceSafeOrderBlock()
                .checkThatSafeOrderCheckboxIsNotSelected()
                .nextBtnClick()
                .getOrderNumber();
        new Order_aws(orderNumber).openOrderInAwsWithLogin()
                .checkThatStatusSafeOrderIsOff()
                .reSaveOrder()
                .checkThatStatusSafeOrderIsOff();
    }

    @AfterMethod
    private void tearDown() {
        close();
    }
}
