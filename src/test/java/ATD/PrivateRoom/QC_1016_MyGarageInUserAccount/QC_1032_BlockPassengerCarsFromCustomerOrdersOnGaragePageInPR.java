package ATD.PrivateRoom.QC_1016_MyGarageInUserAccount;

import ATD.Main_page_Logic;
import ATD.Maker_car_list_page_Logic;
import ATD.SetUp;
import io.qameta.allure.Description;
import io.qameta.allure.Flaky;
import io.qameta.allure.Owner;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.sql.SQLException;

import static ATD.CommonMethods.openPage;
import static ATD.SetUp.setUpBrowser;

public class QC_1032_BlockPassengerCarsFromCustomerOrdersOnGaragePageInPR {

    private String mail = "QC_1032_autotestATD@mailinator.com", orderNumber;

    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0");
    }

    @DataProvider(name = "route", parallel = true)
    Object[] dataProvider() {
        return new SetUp().setUpShop("prod", "DE");
    }

    @Test(dataProvider = "route")
    @Flaky
    @Owner(value = "Chelombitko")
    @Description(value = "Test checks the block of passenger cars from customer orders on the Garage page in PR")
    public void testBlockPassengerCarsFromCustomerOrdersOnGaragePageInPR(String route) throws SQLException {
        openPage(route);
        new Main_page_Logic().loginAndTransitionToProfilePlusPage(mail)
                .goToMainPage()
                .chooseBrandModelTypeInSelector("BMW", "7378", "55986")
                .goToCatalog()
                .clickOilFilterCategoryLink()
                .clickFirstProductOnListing()
                .addProductToCart()
                .closePopupOtherCategoryIfYes()
                .cartClick()
                .clickBtnNextAndTransitionOnAddressPage()
                .fillFieldTelNumForShipping("200+002")
                .nextBtnClick()
                .chooseVorkasse()
                .nextBtnClick()
                .nextBtnClick()
                .goToProfilePlusPage()
                .goToMyVehiclesBlock()
                .checkPresenceInfoCarBlock("M50d 3.0 (280 KW / 381 PS) (08.2011 - 06.2014)")
                .checkTransitionToCatalogFromCarInfoBlock("/ersatzteile/bmw/x6/x6-e71-e72/55986-m-50-d");
        new Maker_car_list_page_Logic().profilePlusBtnClickInHeader()
                .goToMyVehiclesBlock()
                .clickBtnAddedCarFromOrderToGarage()
                .openPopUpMyGarageInHeader()
                .deleteOfAddedAuto();
    }
}