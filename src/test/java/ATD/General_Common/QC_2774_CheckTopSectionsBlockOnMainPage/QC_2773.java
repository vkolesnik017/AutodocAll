package ATD.General_Common.QC_2774_CheckTopSectionsBlockOnMainPage;

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
import static com.codeborne.selenide.Selenide.*;

public class QC_2773 {

    private Main_page_Logic mainPageLogic = new Main_page_Logic();


    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0", false);
    }

    @DataProvider(name = "routes", parallel = true)
    Object[] dataProvider() {
        return new SetUp("ATD").setUpShop("prod", "DE");
    }

    @Test(dataProvider = "routes")
    @Flaky
    @Owner(value = "Sergey_QA")
    @Description(value = "Test checks transitions by tab in top sections block on main page")
    public void testChecksTransitionsByTabInTopSectionsBlockOnMainPage(String route) throws SQLException {
        openPage(route);
        mainPageLogic.checkPresenceAllTabInTopBlock()
                .selectTabTopCarBrandsBlock()
                .checkTransitionByTabLkwInTopBlock()
                .checkTransitionByTabMotoInTopBlock()
                .checkTransitionByTabAccessoriesInTopBlock();
    }

    @AfterMethod
    private void close() {
        closeWebDriver();
    }
}
