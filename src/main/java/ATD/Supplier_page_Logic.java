package ATD;

import Common.DataBase;
import com.codeborne.selenide.ElementsCollection;
import io.qameta.allure.Step;
import org.openqa.selenium.Keys;
import org.testng.Assert;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static ATD.CommonMethods.compareTwoListsBetweenFrontAndAws;
import static ATD.CommonMethods.getAttributeFromUnVisibleElement;
import static Common.CommonMethods.checkingContainsUrl;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.back;
import static com.codeborne.selenide.Selenide.switchTo;

public class Supplier_page_Logic extends Supplier_page {
    @Step("check all headlines and images. Supplier_page")
    public Supplier_page_Logic checkAllHeadlinesAndImages() {
        iconOfBrandInMainHeadline().shouldBe(visible);
        titleOfBrandInMainHeadline().shouldBe(visible).shouldHave(text("BOSCH Autoteile, Motoröl, Autopflege, Autozubehör, Werkzeuge"));
        breadCrumbsBlock().shouldBe(visible);
        selectorBlock().shouldBe(visible);
        titleOfTopChildCategories().shouldBe(visible).shouldHave(text("FINDEN SIE DIE BESTEN ANGEBOTE FÜR BELIEBTE BOSCH PKW-TEILE"));
        childCategories().shouldHaveSize(12);
        titleOfAdvantagesBlock().shouldBe(visible).shouldHave(text("WARUM SOLLTEN SIE BOSCH AUTOPRODUKTE KAUFEN?"));
        blocksOfOfAdvantagesBlock().shouldHaveSize(5);
        titleOfParentCategoriesBlock().shouldBe(visible).shouldHave(text("BOSCH ONLINEKATALOG FÜR AUTOTEILE, MOTORÖL, AUTOPFLEGE, AUTOZUBEHÖR, WERKZEUGE"));
        titleOfReviewBlock().shouldBe(visible).shouldHave(text("Kundenbewertungen zu BOSCH Kfz-Teilen & Produkten"));
        titleOfBrandsBlock().shouldBe(visible).shouldHave(text("Weitere beliebte Hersteller von Aftermarket-Autoteilen"));
        titleOfTopProductsBlock().shouldBe(visible).shouldHave(text("DIE BESTEN ANGEBOTE FÜR BOSCH PRODUKTE"));
        topProductsBlock().shouldBe(visible);
        return this;
    }

    @Step("check elements of brands block. Supplier_page")
    public Supplier_page_Logic checkElementsOfBrandsBlock() {
        titleOfBrandsBlock().shouldBe(visible);
        brandLinks().shouldHaveSize(15);
        return this;
    }

    @Step("get attribute of brand link. Supplier_page")
    public List<String> getAttributeOfBrandLinks() {
        List<String> titleOfBrands = brandLinks().stream().map(n -> getAttributeFromUnVisibleElement(n, "href").replaceAll("^.+\\/", "")).collect(Collectors.toList());
        return titleOfBrands;
    }

    @Step("check for absence of brand in brands block. Supplier_page")
    public Supplier_page_Logic checkForAbsenceOfBrandInBrandsBlock(List<String> list, String absenceBrand) {
        Assert.assertFalse(list.contains(absenceBrand));
        return this;
    }

    @Step("check count of brands. Supplier_page")
    public Supplier_page_Logic checkCountOfBrands(List<String> list, int expectedSize) {
        Assert.assertEquals(list.size(), expectedSize);
        return this;
    }

    @Step("click once to right in brands block. Supplier_page")
    public Supplier_page_Logic clickOnceToRightInBrandsBlock() {
        String urlFromLastBrandLink = visibleBrandsLinks().last().getAttribute("href");
        btnNextOfBrandsBlock().shouldBe(visible).click();
        visibleBrandsLinks().last().shouldNotHave(attribute("href", urlFromLastBrandLink));
        return this;
    }

    @Step("click once to left in brands block. Supplier_page")
    public Supplier_page_Logic clickOnceToLeftInBrandsBlock() {
        String urlFromFirstBrandLink = visibleBrandsLinks().first().getAttribute("href");
        btnBackOfBrandsBlock().shouldBe(visible).click();
        visibleBrandsLinks().first().shouldNotHave(attribute("href", urlFromFirstBrandLink));
        return this;
    }

    @Step("check transition to brand page by all brands. Supplier_page")
    public Supplier_page_Logic checkTransitionToBrandPageByAllBrands() {
        checkTransitionOfFirstVisibleBrands();
        checkTransitionOfOthersBrands();
        return this;
    }

    @Step("check transition of first visible brand. Supplier_page")
    public Supplier_page_Logic checkTransitionOfFirstVisibleBrands() {
        String urlOfBrand;
        for (int i = 0; i < visibleBrandsLinks().size(); i++) {
            titleOfBrandsBlock().shouldBe(visible);
            urlOfBrand = visibleBrandsLinks().get(i).getAttribute("href");
            clickOnVisibleBrands(i);
            checkingContainsUrl(urlOfBrand);
            back();
        }
        return this;
    }

    @Step("check transition of others brand. Supplier_page")
    public Supplier_page_Logic checkTransitionOfOthersBrands() {
        String urlOfBrand;
        String expectedUrlOfBrand;
        String select = Keys.chord(Keys.CONTROL, Keys.RETURN);
        while (!notActiveBtnNextOfBrandsBlock().isDisplayed()) {
            urlOfBrand = visibleBrandsLinks().last().getAttribute("href");
            btnNextOfBrandsBlock().click();
            visibleBrandsLinks().last().shouldNotHave(attribute("href", urlOfBrand));
            expectedUrlOfBrand = visibleBrandsLinks().last().getAttribute("href");
            visibleBrandsLinks().last().sendKeys(select);
            switchTo().window(1);
            checkingContainsUrl(expectedUrlOfBrand);
            switchTo().window(1).close();
            switchTo().window(0);
        }
        return this;
    }

    @Step("click on visible brand. Supplier_page")
    public Supplier_page_Logic clickOnVisibleBrands(int positionOfBrand) {
        visibleBrandsLinks().get(positionOfBrand).click();
        return this;
    }

    @Step("presence of visible brand links. Supplier_page")
    public Supplier_page_Logic presenceOfVisibleBrandLinks() {
        for (int i = 0; i < visibleBrandsLinks().size(); i++) {
            visibleBrandsLinks().get(i).shouldBe(visible);
        }
        return this;
    }

    @Step("Get name list all parents from brand page. Supplier_page")
    public ArrayList<String> getIdListParentsFromBrandPage() {
        ArrayList<String> idParentsList = new ArrayList<>();
        for (int i = 0; i < idNumParents().size(); i++) {
            String idParent = idNumParents().get(i).getAttribute("src").replaceAll("[\\s\\S]*\\/", "").replaceAll(".png", "").trim();
            idParentsList.add(idParent);
        }
        return idParentsList;
    }

    @Step(": from. Supplier_page")
    public Supplier_page_Logic compareTwoListsBetweenFrontAndAwsFrom(List<String> listFront, List<String> listAws, List<Integer> listRating) {
        compareTwoListsBetweenFrontAndAws(listFront, listAws, listRating);
        return this;
    }

    @Step(": from. Supplier_page")
    public ArrayList<String> getHrefOrUrlCategoriesThenWriteToList(ElementsCollection categories) {
        return CommonMethods.getHrefOrUrlCategoriesThenWriteToList(categories);
    }

    @Step(": from. Supplier_page")
    public Supplier_page_Logic checkCategoriesForServerResponses200(List<String> allCategories) throws IOException {
        CommonMethods.checkCategoriesForServerResponses200(allCategories);
        return this;
    }

    @Step("presence Of TOP products block. Supplier_page")
    public Supplier_page_Logic presenceOfTopProductsBlock() {
        topProductsBlock().shouldBe(visible);
        return this;
    }

    @Step("check Seo Text Block. Supplier_page")
    public Supplier_page_Logic checkSeoTextBlock(String value) throws SQLException {
        String frontText = seoText().shouldBe(visible).getText().replaceAll("\\W", "");
        String bdText = new DataBase("ATD").getTranslate("seo_text", "DE", value).replaceAll("\\W", "");
        Assert.assertEquals(frontText, bdText);
        return this;
    }
}
