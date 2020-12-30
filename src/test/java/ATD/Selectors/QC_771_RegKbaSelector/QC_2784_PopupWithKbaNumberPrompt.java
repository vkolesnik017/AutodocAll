package ATD.Selectors.QC_771_RegKbaSelector;


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


public class QC_2784_PopupWithKbaNumberPrompt {

    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0", false);
    }

    @DataProvider(name = "routes", parallel = true)
    Object[] dataProvider() {
        return new SetUp("ATD").setUpShopsWithMainRoute("prod", "DE", "main");
    }


    @DataProvider(name = "routes2", parallel = true)
    Object[] dataProvider2() throws SQLException {
        return new SetUp("ATD").setUpShopWithSubroutes("prod", "DE", "main", "category_car_list,category_oen,search37");
    }



    @Test(dataProvider = "routes")
    @Flaky
    @Owner(value = "Sergey_QA")
    @Description(value = "Test checking popup with kba number prompt")
    public void testCheckingPopupWithKbaNumberPrompt(String route) {
        openPage(route);
        new Main_page_Logic().clickLinkAndCheckWorkKbaPopup();

    }

    @Test(dataProvider = "routes2")
    @Flaky
    @Owner(value = "Sergey_QA")
    @Description(value = "Test checking popup with kba number prompt")
    public void testCheckingPopupWithKbaNumberPrompt2(String route) {
        openPage(route);
        new Main_page_Logic().clickLinkAndCheckWorkKbaPopup();
    }

    @AfterMethod
    public void close() {
        closeWebDriver();
    }
}
