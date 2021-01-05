package ATD.PrivateRoom.QC_1016_MyGarageInUserAccount;

import ATD.Main_page_Logic;
import Common.SetUp;
import io.qameta.allure.Description;
import io.qameta.allure.Flaky;
import io.qameta.allure.Owner;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.sql.SQLException;

import static ATD.CommonMethods.openPage;
import static Common.SetUp.setUpBrowser;
import static com.codeborne.selenide.Selenide.closeWebDriver;

public class QC_2319 {
    private String email = "QC_2319_UpdateOfData@mailinator.com";

    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0", false);
    }

    @DataProvider(name = "routes", parallel = true)
    Object[] dataProvider() throws SQLException {
        return new SetUp("ATD").setUpShopWithSubroutes("prod", "DE", "main", "main");

    }

    @Test(dataProvider = "routes")
    @Flaky
    @Owner(value = "Kolesnik")
    @Description(value = "Test checks updating data in Motorcycle selector with selected vehicle")
    public void testChecksUpdatingDataInMotoSelectorWithSelectedVehicle(String route) {
        openPage(route);

        new Main_page_Logic()
                .loginToProfilePlusPageAndBack(email).updateOfPage().checkCountOfVehicleInIconOfGarage("3")
                .clickMotoCategory()
                .clickOnGarageIconInHeader()
                .selectVehicleInGaragePopUp("101396")
                .checkValuesInSelector("4082", "12020", "101396")
                .resetOfMotoSelector()
                .clickOnLinkMoreAtTopBrandsBlock()
                .presenceOfAutomakersBlock()
                .clickOnGarageIconInHeader()
                .selectVehicleInGaragePopUp("101396")
                .checkValuesInSelector("4082", "12020", "101396");
    }

    @AfterMethod
    private void close() {
        closeWebDriver();
    }
}
