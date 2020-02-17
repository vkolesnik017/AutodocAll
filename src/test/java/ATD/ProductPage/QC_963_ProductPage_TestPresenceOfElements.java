package ATD.ProductPage;


import ATD.CommonMethods;
import ATD.DataBase;
import ATD.Main_page;
import ATD.Product_page;
import io.qameta.allure.Description;
import io.qameta.allure.Flaky;
import io.qameta.allure.Owner;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.sql.SQLException;

import static ATD.CommonMethods.openPage;
import static ATD.SetUp.setUpBrowser;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.close;

public class QC_963_ProductPage_TestPresenceOfElements {
    private Product_page productPage = new Product_page();
    private CommonMethods commonMethods = new CommonMethods();
    private DataBase dataBase = new DataBase();
    private Main_page mainPage = new Main_page();

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
        //2
        productPage.carSelectorBlock().shouldBe(visible);
        //3
        productPage.minimizedCharactericticBlock().shouldBe(visible);
        productPage.uncoverCharactericticBtn().click();
        productPage.maximizedCharacteristicBlock().shouldBe(visible);
        productPage.coverCharacteristicBtn().click();
        productPage.minimizedCharactericticBlock().shouldBe(visible);
        //4
        productPage.featuresBlock().shouldBe(visible);
        productPage.jahreIcon().shouldBe(visible).hover();
        Assert.assertEquals(productPage.jahreIcon().getCssValue("color"), "rgba(51, 100, 219, 1)");
        productPage.tageIcon().shouldBe(visible).hover();
        Assert.assertEquals(productPage.tageIcon().getCssValue("color"), "rgba(51, 100, 219, 1)");
        productPage.tagePopup().shouldBe(visible);
        productPage.safeOrderIcon().shouldBe(visible).hover();
        Assert.assertEquals(productPage.safeOrderIconColor().getCssValue("color"), "rgba(51, 100, 219, 1)");
        productPage.safeOrderPopup().shouldBe(visible);
        //5
        productPage.breadcrumbsBlock().shouldBe(visible);
        //6
        mainPage.closeCarSelectorTooltipIfVisible();
        productPage.breadcrumbsBlock().shouldBe(visible);
        productPage.breadcrumbsCategoryDropdownButton().click();
        productPage.breadcrumbsFirstCategoryFromDropdown().click();
        commonMethods.checkingUrl("https://www.autodoc.de/autoteile/bremsbelag-10130");
        //7
        mainPage.closeCarSelectorTooltipIfVisible();
        productPage.breadcrumbsBlock().shouldBe(visible);
        productPage.breadcrumbsCategoryButton().click();
        commonMethods.checkingUrl("https://www.autodoc.de/autoteile/bremsscheibe-10132");
        //8
        mainPage.closeCarSelectorTooltipIfVisible();
        productPage.breadcrumbsBlock().shouldBe(visible);
        productPage.breadcrumbsParentCategoryDropdownButton().click();
        productPage.breadcrumbsFirstParentCategoryFromDropdown().click();
        commonMethods.checkingUrl("https://www.autodoc.de/autoteile/filter");
        //9
        mainPage.closeCarSelectorTooltipIfVisible();
        productPage.breadcrumbsBlock().shouldBe(visible);
        productPage.breadcrumbsParentCategotyButton().click();
        commonMethods.checkingUrl("https://www.autodoc.de/autoteile/bremsanlage");
        //10
        mainPage.closeCarSelectorTooltipIfVisible();
        productPage.breadcrumbsBlock().shouldBe(visible);
        productPage.breadcrumbsCatalogButton().click();
        commonMethods.checkingUrl("https://www.autodoc.de/autoteile");
        //11
        productPage.productImageBlock().shouldBe(visible);
        //12
        productPage.imageInSlider().click();
        productPage.productImageBlock().click();
        productPage.fullProductImage().shouldBe(visible);
        productPage.closeFullImagePreview().click();
        //13
        productPage.productImageSlider().shouldBe(visible);
        //14
        productPage.priceBlock().shouldBe(visible);
        //15
        productPage.addProductToCart();
        productPage.closePopupOtherCategoryIfYes();
        //16
        productPage.counterIncrease("2");
        productPage.counterDecrease("4");
        //17
        productPage.brandButtonOnImage().click();
        commonMethods.checkingUrlAndCloseTab("https://www.autodoc.de/autoteile/bremsscheibe-10132/mf-ridex");
        //18
        productPage.versandkostenButton().click();
        commonMethods.checkingUrl("https://www.autodoc.de/services/versand");
        //19
        productPage.raitingBlock().click();
        //20
        productPage.feedbackBlock().shouldBe(visible);
        //21
        productPage.similarPropertiesBlock().shouldBe(visible);
        productPage.linkInSimilarPropertiesBlock().click();
        commonMethods.checkingUrl("https://www.autodoc.de/bosch/1165812");
        //23
        productPage.paymentMethodsBlock().shouldBe(visible);
        //24
        productPage.deliveryServicesBlock().shouldBe(visible);
        //25
        productPage.videoBlock().shouldBe(visible);
        //26
        productPage.pdfTutorialsBlock().shouldBe(visible);
        productPage.checkPdfLinksForDownload();
        //22
        productPage.minicardsBlock().shouldBe(visible);
        productPage.tetleMiniCardBlock().scrollTo();
        productPage.productInMinicard().shouldBe(visible).hover();
        productPage.characteristicsInMinicard().shouldBe(visible);
        close();
    }
}
