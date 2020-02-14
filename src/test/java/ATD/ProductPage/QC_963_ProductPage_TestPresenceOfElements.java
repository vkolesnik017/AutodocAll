package ATD.ProductPage;


import ATD.CommonMethods;
import ATD.DataBase;
import ATD.Product_page_Logic;
import io.qameta.allure.Description;
import io.qameta.allure.Flaky;
import io.qameta.allure.Owner;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.sql.SQLException;

import static ATD.CommonMethods.closeCookiesFooterMessage;
import static ATD.CommonMethods.openPage;
import static ATD.SetUp.setUpBrowser;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.close;

public class QC_963_ProductPage_TestPresenceOfElements {
    private Product_page_Logic product_page_logic = new Product_page_Logic();
    private CommonMethods commonMethods = new CommonMethods();
    private DataBase dataBase = new DataBase();

    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0");
    }

    @Test
    @Flaky
    @Owner(value = "Romaniuta")
    @Description(value = "Test checks presence of elements on product page")
    public void testPresenceOfElements() throws SQLException {
        //steps in QC: 1
        openPage("https://autodoc.de/" + dataBase.getRouteByRouteName("DE", "product13"));
        closeCookiesFooterMessage();
        //2
        product_page_logic.carSelectorBlock().shouldBe(visible);
        //3
        product_page_logic.minimizedCharactericticBlock().shouldBe(visible);
        product_page_logic.uncoverCharactericticBtn().click();
        product_page_logic.maximizedCharacteristicBlock().shouldBe(visible);
        product_page_logic.coverCharacteristicBtn().click();
        product_page_logic.minimizedCharactericticBlock().shouldBe(visible);
        //5
        product_page_logic.breadcrumbsBlock().shouldBe(visible);
        //6
        product_page_logic.breadcrumbsCategoryDropdownButton().click();
        product_page_logic.breadcrumbsFirstCategoryFromDropdown().click();
        commonMethods.checkingUrl("https://www.autodoc.de/autoteile/bremsbelag-10130");
        //7
        product_page_logic.breadcrumbsCategoryButton().click();
        commonMethods.checkingUrl("https://www.autodoc.de/autoteile/bremsscheibe-10132");
        //8
        product_page_logic.breadcrumbsParentCategoryDropdownButton().click();
        product_page_logic.breadcrumbsFirstParentCategoryFromDropdown().click();
        commonMethods.checkingUrl("https://www.autodoc.de/autoteile/filter");
        //9
        product_page_logic.breadcrumbsParentCategotyButton().click();
        commonMethods.checkingUrl("https://www.autodoc.de/autoteile/bremsanlage");
        //10
        product_page_logic.breadcrumbsCatalogButton().click();
        commonMethods.checkingUrl("https://www.autodoc.de/autoteile");
        //4
        product_page_logic.featuresBlock().shouldBe(visible);
        product_page_logic.jahreIcon().shouldBe(visible).hover();
        Assert.assertEquals(product_page_logic.jahreIcon().getCssValue("color"), "rgba(51, 100, 219, 1)");
        product_page_logic.tageIcon().shouldBe(visible).hover();
        Assert.assertEquals(product_page_logic.tageIcon().getCssValue("color"), "rgba(51, 100, 219, 1)");
        product_page_logic.tagePopup().shouldBe(visible);
        product_page_logic.safeOrderIcon().shouldBe(visible).hover();
        Assert.assertEquals(product_page_logic.safeOrderIconColor().getCssValue("color"), "rgba(51, 100, 219, 1)");
        product_page_logic.safeOrderPopup().shouldBe(visible);
        //11
        product_page_logic.productImageBlock().shouldBe(visible);
        //12
        product_page_logic.imageInSlider().click();
        product_page_logic.productImageBlock().click();
        product_page_logic.fullProductImage().shouldBe(visible);
        product_page_logic.closeFullImagePreview().click();
        //13
        product_page_logic.productImageSlider().shouldBe(visible);
        //14
        product_page_logic.priceBlock().shouldBe(visible);
        //15
        product_page_logic.addProductToCart();
        product_page_logic.closePopupOtherCategoryIfYes();
        //16
        product_page_logic.counterIncrease("2");
        product_page_logic.counterDecrease("4");
        //17
        product_page_logic.brandButtonOnImage().click();
        commonMethods.checkingUrlAndCloseTab("https://www.autodoc.de/autoteile/bremsscheibe-10132/mf-ridex");
        //18
        product_page_logic.versandkostenButton().click();
        commonMethods.checkingUrl("https://www.autodoc.de/services/versand");
        //19
        product_page_logic.raitingBlock().click();
        //20
        product_page_logic.feedbackBlock().shouldBe(visible);
        //21
        product_page_logic.similarPropertiesBlock().shouldBe(visible);
        product_page_logic.linkInSimilarPropertiesBlock().click();
        commonMethods.checkingUrl("https://www.autodoc.de/bosch/1165812");
        //22
        product_page_logic.minicardsBlock().shouldBe(visible);
        product_page_logic.productInMinicard().hover();
        product_page_logic.characteristicsInMinicard().shouldBe(visible);
        //23
        product_page_logic.paymentMethodsBlock().shouldBe(visible);
        //24
        product_page_logic.deliveryServicesBlock().shouldBe(visible);
        //25
        product_page_logic.videoBlock().shouldBe(visible);
        //26
        product_page_logic.pdfTutorialsBlock().shouldBe(visible);
        product_page_logic.checkPdfLinksForDownload();
        close();
    }
}
