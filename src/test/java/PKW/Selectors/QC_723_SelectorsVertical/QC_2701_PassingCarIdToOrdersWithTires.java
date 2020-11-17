package PKW.Selectors.QC_723_SelectorsVertical;


import AWS.Order_aws;
import Common.DataBase;
import PKW.Car_tires_page_Logic;
import PKW.Tyres_size_page_Logic;
import io.qameta.allure.Description;
import io.qameta.allure.Owner;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import java.sql.SQLException;
import static Common.SetUp.setUpBrowser;
import static PKW.CommonMethods.*;
import static com.codeborne.selenide.Selenide.closeWebDriver;

public class QC_2701_PassingCarIdToOrdersWithTires {

    private Car_tires_page_Logic carTiresPageLogic = new Car_tires_page_Logic();
    private String mail = "QC_2701_autotest@mailinator.com";


    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0");
    }

    @DataProvider(name = "routes", parallel = true)
    Object[] dataProvider() throws SQLException {
        return new Common.SetUp("PKW").setUpShopWithSubroutes("prod", "DE", "main", "car_tires");
    }


    @Test(dataProvider = "routes")
    @Owner(value = "Sergey_QA")
    @Description(value = "Passing car id to orders with tires")
    public void testCheckPassingCarIdToOrdersWithTires(String route) throws SQLException {
        openPage(route);
        String valueCarIdFromSelector = carTiresPageLogic.getValueCarIdFromVehicleSelectors();
        carTiresPageLogic.clickBtnAddToBasket();
        openPage(new DataBase("PKW").getFullRouteByRouteAndSubroute("subprod","DE","main_tyres","tyres_size"));
        checkingContainsUrl("20-zoll");
        String orderNumber = new Tyres_size_page_Logic().clickBtnAddToBasket()
                .cartClick()
                .nextButtonClick()
                .signIn(mail,password)
                .fillFieldTelNumForShipping("200+002")
                .nextBtnClick()
                .clickOnTheDesiredPaymentMethod("DE","Bank")
                .nextBtnClick()
                .nextBtnClick()
                .getOrderNumber();
        new Order_aws(orderNumber).openOrderInAwsWithLogin()
                .checkingComplianceCarIdInOrderedGoods(valueCarIdFromSelector)
                .checksByCarIdThatProductsFitsCar()
                .reSaveOrder()
                .checkCurrentStatusInOrder("Testbestellungen");
    }

    @AfterMethod
    public void close() {
        closeWebDriver();
    }
}
