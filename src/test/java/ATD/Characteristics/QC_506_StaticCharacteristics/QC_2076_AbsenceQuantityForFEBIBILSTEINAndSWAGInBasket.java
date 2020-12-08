package ATD.Characteristics.QC_506_StaticCharacteristics;

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
import java.util.ArrayList;
import java.util.List;

import static ATD.CommonMethods.openPage;
import static Common.SetUp.setUpBrowser;
import static com.codeborne.selenide.Selenide.closeWebDriver;

public class QC_2076_AbsenceQuantityForFEBIBILSTEINAndSWAGInBasket {

    private List<String> idBtnAddToBasket = new ArrayList<>();

    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0");
    }

    @DataProvider(name = "routes", parallel = true)
    Object[] dataProvider() throws SQLException {
        return new SetUp("ATD").setUpShopWithSubroutes("prod", "DE", "main", "main");
    }

    @Test(dataProvider = "routes")
    @Flaky
    @Owner(value = "Kolesnik")
    @Description(value = "Checking for the absence of the characteristic 'quantity' 563 in basket for FEBI BILSTEIN and SWAG brands")
    public void testCheckAbsenceQuantityForFEBIBILSTEINAndSWAGInBasket(String route) {
        openPage(route);

        new Main_page_Logic()
                .inputTextInSearchBar("10 92 9140")
                .selectProductFromHintOfSearchField("10 92 9140")
                .addedIdOfBtnAddToBasketToList(idBtnAddToBasket)
                .addedProductToBasket()
                .closePopupOtherCategoryIfYes()
                .inputTextInSearchBar("01472")
                .selectProductFromHintOfSearchField("01472")
                .addedIdOfBtnAddToBasketToList(idBtnAddToBasket)
                .addedProductToBasket()
                .goToBasket()
                .checkAbsenceOfQuantityCharacteristicInProductDescriptionBlock(idBtnAddToBasket);
    }


    @AfterMethod
    public void close() {
        closeWebDriver();
    }
}
