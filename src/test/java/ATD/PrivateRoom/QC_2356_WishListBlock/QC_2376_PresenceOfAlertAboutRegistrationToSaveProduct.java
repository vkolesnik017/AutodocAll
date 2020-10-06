package ATD.PrivateRoom.QC_2356_WishListBlock;

import ATD.Search_page_Logic;
import ATD.Services_wishList_page_Logic;
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
import static com.codeborne.selenide.Selenide.refresh;

public class QC_2376_PresenceOfAlertAboutRegistrationToSaveProduct {


    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0");
    }

    @DataProvider(name = "routes", parallel = true)
    Object[] dataProvider() throws SQLException {
        return new SetUp("ATD").setUpShopWithSubroutes("prod", "DE", "main", "search37");

    }

    @Test(dataProvider = "routes")
    @Flaky
    @Owner(value = "Kolesnik")
    @Description(value = "Test checks presence of alert about registration to save product in WishList")
    public void testChecksPresenceOfAlertAboutRegistrationToSaveProduct(String route) {
        openPage(route);

        new Search_page_Logic().presenceOfTecDocListing().addedProductToWishList(1).goToWishListPage()
                .presenceOfAuthorizationBlock().closeAuthorizationBlock();
        refresh();
        new Services_wishList_page_Logic().presenceOfAuthorizationBlock();
    }

    @AfterMethod
    private void close() {
        closeWebDriver();
    }
}
