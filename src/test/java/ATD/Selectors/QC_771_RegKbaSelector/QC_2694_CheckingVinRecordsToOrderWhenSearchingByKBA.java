package ATD.Selectors.QC_771_RegKbaSelector;

import ATD.Main_page_Logic;
import ATD.Maker_car_list_page_Logic;
import AWS.Order_aws;
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
import static com.codeborne.selenide.Selenide.refresh;

public class QC_2694_CheckingVinRecordsToOrderWhenSearchingByKBA {

    private Main_page_Logic mainPageLogic = new Main_page_Logic();

    private String mail = "qc_2694_autotest@mailinator.com";
    private String kba = "YG66FGJ";
    private String vinNum = "WF0XXXTTGXGK11456";

    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0");
    }

    @DataProvider(name = "routes", parallel = true)
    Object[] dataProvider() throws SQLException {
        return new SetUp("ATD").setUpShopsWithMainRoute("prod", "EN", "main");
    }

    @Test(dataProvider = "routes")
    @Flaky
    @Owner(value = "Sergey_QA")
    @Description(value = "Test Checking the VIN entry from the service to the order when searching by KBA for product selection")
    public void testCheckingVinRecordsToOrderWhenSearchingByKBA(String route) throws SQLException {
        openPage(route);
        refresh();
        mainPageLogic.fillNumberKba(kba)
                .clickKbaBtn();
        String orderNumber = new Maker_car_list_page_Logic().addProductFromTopProductsToBasket()
                .cartClick()
                .nextButtonClick()
                .signIn(mail, password)
                .fillFieldTelNumForShipping("200+002")
                .nextBtnClick()
                .clickOnTheDesiredPaymentMethod("EN", "Bank")
                .nextBtnClick()
                .nextBtnClick()
                .getOrderNumber();
        new Order_aws(orderNumber).openOrderInAwsWithLogin()
                .clickBtnAutoBySearchFromTheSite()
         .getVinNumInPopupFromBtnAutoBySearchFromTheSite(vinNum)
                .reSaveOrder()
                .checkCurrentStatusInOrder("Testbestellungen");
    }

    @AfterMethod
    public void close() {
        closeWebDriver();
    }
}
