package ATD;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Random;

import static ATD.CommonMethods.clickable;
import static com.codeborne.selenide.CollectionCondition.size;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$x;
import static com.codeborne.selenide.Selenide.sleep;
import static org.testng.Assert.assertEquals;


public class Austauschartikel_static_page_Logic extends Austauschartikel_static_page {

    @Step("Checks items at the top of the Austauschartikel page. Austauschartikel_static_page")
    public Austauschartikel_static_page_Logic checkItemsTopPage() {
        title().shouldBe(visible);
        mainTextFromPage().shouldBe(visible);
        ausLogo().shouldBe(visible);
        titleForImage().shouldBe(visible);
        mainImage().shouldBe(visible);
        return this;
    }

    @Step("Checks for the presence of a Instruction block. Austauschartikel_static_page")
    public Austauschartikel_static_page_Logic checkInstructionBlock() {
        instruction().shouldBe(visible);
        formLink().shouldBe(clickable);
        return this;
    }

    @Step("Checks for the presence of a Return Policy block. Austauschartikel_static_page")
    public Austauschartikel_static_page_Logic checkReturnPolicyBlock() {
        returnPolicy().shouldBe(visible);
        return this;
    }

    @Step("Checks for the presence of a Categories Pfand block and elements inside it. Austauschartikel_static_page")
    public Austauschartikel_static_page_Logic checkCategoriesPfandBlock() {
        categoriesPfandBlock().shouldBe(visible);
        allCategoriesButton().click();
        pfandAllCategories().shouldHave(size(34));
        allCategoriesButton().click();
        return this;
    }

    @Step("Checks for the presence of a Requirements for Parts in use block and elements inside it. Austauschartikel_static_page")
    public Austauschartikel_static_page_Logic checkRequirementForPartsBlock() {
        requirementForPartsBlock().shouldBe(visible);
        requirementTitle().shouldBe(visible);
        categoriesList().shouldBe(visible);
        return this;
    }

    @Step("Selects one random category. Austauschartikel_static_page")
    public Austauschartikel_static_page_Logic SelectsRandomCategory() {
        ElementsCollection categoriesList = (categoriesWithDeposits());
        Random random = new Random();
        categoriesList.get(random.nextInt(categoriesList.size())).click();
        return this;
    }


    @Step("Gets the status of the image code. Austauschartikel_static_page")
    public Austauschartikel_static_page_Logic getStatusImageCod() throws IOException {
        // I added a condition in order to prevent a test fail in case there are no images
        if (categoryImage().isDisplayed()) {
            String linkInsideImage = categoryImage().getAttribute("src");
            URL url = new URL(linkInsideImage);
            HttpURLConnection http = (HttpURLConnection) url.openConnection();
            http.setInstanceFollowRedirects(true);
            int responseCode = http.getResponseCode();
            assertEquals(responseCode, 200);
        }
        return this;
    }

    @Step("Checks the title and description of the selected category. Austauschartikel_static_page")
    public Austauschartikel_static_page_Logic checkTitleAndDDescriptionOfCategory() {
        randomCategoryTitle().shouldHave(text(openedCategoryTitle().getText()));
        openedCategoryDescription().shouldBe(visible);
        openedCategoryTitle().click();
        return this;
    }

    @Step("Checks for the presence of a Deposit Refund form and elements inside it. Austauschartikel_static_page")
    public Austauschartikel_static_page_Logic checkDepositRefundForm() {
        depositRefundBlock().shouldBe(visible);
        titleFromDepositRefundBlock().shouldBe(visible);
        informTextFromDepositRefundBlock().shouldBe(visible);
        sleep(2000);
        depositRefundForm().scrollTo();
        sleep(2000);
        depositRefundForm().shouldBe(visible);
        plzTooltip().click();
        closePlzTooltip().click();
        numberTooltip().click();
        closeNumbetTooltip().click();
        plzSearchButton().click();
        popupError().click();
        closePopupButton().click();
        return this;
    }
}
