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

public class QC_2316 {

    private String email = "QC_2316_UpdateOfData@mailinator.com";

    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0", false);
    }

    @DataProvider(name = "routes", parallel = true)
    Object[] dataProvider() throws SQLException {
        return new SetUp("ATD").setUpShopWithSubroutes("subprod", "DE", "main", "main");
    }

    @Test(dataProvider = "routes")
    @Flaky
    @Owner(value = "Kolesnik")
    @Description(value = "Test checks updating of data with selected vehicle in Garage pop-up")
    public void testChecksUpdatingOfDataWithSelectedVehicleInGaragePopUp(String route) throws SQLException {
        openPage(route);

        new Main_page_Logic()
                .loginToProfilePlusPageAndBack(email).updateOfPage().checkCountOfVehicleInIconOfGarage("3")
                .clickOnGarageIconInHeader()
                .selectVehicleInGaragePopUp("6926")
                .checkValuesInSelector("74", "1746", "6926")
                .resetVerticalCarSelector()
                .clickOnMoreSparePartsLink()
                .presenceOfTecDocCatalog()
                .clickOnGarageIconInHeader()
                .selectVehicleInGaragePopUp("6926")
                .checkValuesInSelector("74", "1746", "6926")
                .resetOfVerticalSelector()
                .goToMainPage()
                .selectTopBrandsBlock()
                .clickOnMoreSparePartsLinkOfTopBrandsBlock()
                .presenceOfBrandsBlock()
                .clickOnGarageIconInHeader()
                .selectVehicleInGaragePopUp("6926")
                .checkValuesInSelector("74", "1746", "6926");
    }

    @AfterMethod
    private void close() {
        closeWebDriver();
    }
}
