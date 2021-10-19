package ATD.Section_Moto.QC_730_ProductPage;

import ATD.Moto_Product_page_Logic;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
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

public class QC_734 {

    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0", false);
    }

    @DataProvider(name = "routes", parallel = true)
    Object[] dataProvider() throws SQLException {
       // return new SetUp("ATD").setUpShopWithSubroutes("subprod", "DE", "moto_main", "moto_product4");
return new Object[][] {{"https://www.autodoc.de/"}};
    }

    @Test(dataProvider = "routes")
    @Flaky
    @Owner(value = "Kolesnik")
    @Description(value = "Test checks compatible motorcycle block")
    public void testChecksCompatibleMotoBlock(String route) {
        openPage(route);

        $x("//a[@class='popup-choose-cookies__btn popup-choose-cookies__btn--blue js-button_choose ga-click']").shouldBe(Condition.visible).click();

        SelenideElement plus = $x("//div[@class='header__plus-text']");

        executeJavaScript("document.plus.onmouseover()");


        new Moto_Product_page_Logic()
                .presenceOfCompatibilityBlock()
                .checkSortingOfCompatibilityBlock()
                .visibilityOfMotoModelsInCompatibilityBlock()
                .visibilityOfMotoMotorsInCompatibilityBlock();
    }

    @AfterMethod
    public void close() {
        closeWebDriver();
    }
}
