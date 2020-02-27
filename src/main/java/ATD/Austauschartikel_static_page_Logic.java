package ATD;

import com.codeborne.selenide.ElementsCollection;
import io.qameta.allure.Step;

import java.util.Random;

import static ATD.CommonMethods.clickable;
import static com.codeborne.selenide.CollectionCondition.size;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.sleep;


public class Austauschartikel_static_page_Logic extends Austauschartikel_static_page {

    @Step("Checks items at the top of the Austauschartikel page")
    public Austauschartikel_static_page_Logic checkItemsTopPage() {
        logo().shouldBe(visible);
        title().shouldBe(visible);
        mainTextFromPage().shouldBe(visible);
        ausLogo().shouldBe(visible);
        title2().shouldBe(visible);
        mainImage().shouldBe(visible);
        return this;
    }

    @Step("Checks for the presence of a Instruction block")
    public Austauschartikel_static_page_Logic checkInstructionBlock() {
        instruction().shouldBe(visible);
        formLink().shouldBe(clickable);
        return this;
    }

    @Step("Checks for the presence of a Return Policy block")
    public Austauschartikel_static_page_Logic checkReturnPolicyBlock() {
        returnPolicy().shouldBe(visible);
        return this;
    }

    @Step("Checks for the presence of a Categories Pfand block and elements inside it")
    public Austauschartikel_static_page_Logic checkCategoriesPfandBlock() {
        categoriesPfandBlock().shouldBe(visible);
        allCategoriesButton().click();
        pfandAllCategories().shouldHave(size(34));
        allCategoriesButton().click();
        return this;
    }

    @Step("Checks for the presence of a Requirements for Parts in use block and elements inside it")
    public Austauschartikel_static_page_Logic checkRequirementForPartsBlock() {
        requirementForPartsBlock().shouldBe(visible);
        requirementTitle().shouldBe(visible);
        categoriesList().shouldBe(visible);
        return this;
    }

    @Step("Selects one random category")
    public Austauschartikel_static_page_Logic SelectsRandomCategory() {
        ElementsCollection categoriesList = (categoriesWithDeposits());
        Random random = new Random();
        categoriesList.get(random.nextInt(categoriesList.size())).click();
        return this;
    }

    @Step("Checks the title and description of the selected category")
    public Austauschartikel_static_page_Logic checkTitleAndDDescriptionOfCategory() {
        randomCategoryTitle().shouldHave(text(openedCategoryTitle().getText()));
        openedCategoryDescription().shouldBe(visible);
        openedCategoryTitle().click();
        return this;
    }

    @Step("Checks for the presence of a Deposit Refund form and elements inside it")
    public Austauschartikel_static_page_Logic checkDepositRefundForm() {
        depositRefundBlock().shouldBe(visible);
        titleFromDepositRefundBlock().shouldBe(visible);
        informTextFromDepositRefundBlock().shouldBe(visible);
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
