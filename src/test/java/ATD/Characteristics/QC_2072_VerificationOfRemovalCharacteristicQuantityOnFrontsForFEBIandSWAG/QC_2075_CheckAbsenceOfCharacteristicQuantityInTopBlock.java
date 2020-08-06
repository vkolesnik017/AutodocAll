package ATD.Characteristics.QC_2072_VerificationOfRemovalCharacteristicQuantityOnFrontsForFEBIandSWAG;

import ATD.Category_name_brand_page_Logic;
import ATD.SetUp;
import io.qameta.allure.Description;
import io.qameta.allure.Flaky;
import io.qameta.allure.Owner;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.sql.SQLException;

import static ATD.CommonMethods.openPage;
import static ATD.SetUp.setUpBrowser;
import static com.codeborne.selenide.Selenide.closeWebDriver;

public class QC_2075_CheckAbsenceOfCharacteristicQuantityInTopBlock {

    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0");
    }

    @DataProvider(name = "routes", parallel = true)
    Object[] dataProvider() throws SQLException {
        return new SetUp().setUpShopWithSubroutes("prod", "DE", "main", "category_name_brand3,category_name_brand4,category_maker_brand2,category_maker_brand3,category_group_brand2,category_group_brand3");
    }

    @Test(dataProvider = "routes")
    @Flaky
    @Owner(value = "Kolesnik")
    @Description(value = "Checking for the absence of the characteristic 'quantity' 563 in the TOP block for FEBI BILSTEIN and SWAG brands")
    public void testCheckAbsenceOfCharacteristicQuantityInTopBlockForFEBIBILSTEINAndSWAG(String route) {
        openPage(route);

        new Category_name_brand_page_Logic()
                .checkAbsenceOfQuantityCharacteristicInTopProducts();
    }

    @AfterMethod
    public void close() {
        closeWebDriver();
    }
}
