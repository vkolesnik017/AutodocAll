package ATD.Characteristics.QC_2387_CheckingCharacteristicConditionForProducts;

import ATD.Category_name_brand_page_Logic;
import AWS.ProductSearch_aws;
import Common.SetUp;
import io.qameta.allure.Description;
import io.qameta.allure.Flaky;
import io.qameta.allure.Owner;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import java.sql.SQLException;
import java.util.ArrayList;
import static ATD.CommonMethods.openPage;
import static Common.SetUp.setUpBrowser;
import static com.codeborne.selenide.Selenide.closeWebDriver;

public class QC_2424_CheckingAbsenceCharacteristicZustandBrand3kWithDepositInMiniCard {

    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0", false);
    }

    @DataProvider(name = "routes", parallel = true)
    Object[] dataProvider() throws SQLException {
        return new SetUp("ATD").setUpShopWithSubroutes("prod", "DE", "main", "category_name_brand9");
    }

    @Test(dataProvider = "routes")
    @Flaky
    @Owner(value = "Sergey-QA")
    @Description(value = "Checking the availability of a deposit for 3K brand goods with a deposit in mini-cards")
    public void testChecksAvailabilityDepositFor3KBrand(String route) {
        openPage(route);
        ArrayList<String> artNumAndDeposit = new Category_name_brand_page_Logic().getArticleNumberAndCheckingDepositForProductsMiniCard();
        new ProductSearch_aws().openProductSearchPageAndLogin()
                .checkingWhetherProductHasDepositByArtNumber(artNumAndDeposit);
    }

    @AfterMethod
    public void close() {
        closeWebDriver();
    }
}
