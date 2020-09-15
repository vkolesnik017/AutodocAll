package AWS;


import Common.DataBase;
import Common.SetUp;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.Keys;

import java.sql.SQLException;
import java.util.*;
import java.util.stream.Collectors;

import static ATD.CommonMethods.openPage;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;

public class CatalogCategories_aws {

    private String awsEnv;
    private String categoriesInAwsPage;
    private String parentCategoriesInAwsPage;
    private String childCategoriesInAwsPage;

    public CatalogCategories_aws() throws SQLException {
        this.awsEnv = "https://aws.";
        init(awsEnv);
    }

    public CatalogCategories_aws(String envFromTest) throws SQLException {
        SetUp setUp = new SetUp();
        this.awsEnv = setUp.getEnvForAws(envFromTest);
        init(awsEnv);
    }

    private void init(String awsEnv) throws SQLException {
        this.categoriesInAwsPage = awsEnv + new DataBase("ATD").getRouteByRouteName("DE", "categoriesAws");
        this.parentCategoriesInAwsPage = awsEnv + new DataBase("ATD").getRouteByRouteName("DE", "parentCategoriesAws");
        this.childCategoriesInAwsPage = awsEnv + "autodoc.de/" + new DataBase("ATD").getRouteByRouteName("DE", "childCategoriesAws");
    }


    private ElementsCollection childIdInAWS() {
        return $$(".catalog-table-content-items-item.parent > ul >li > div > div:nth-child(4)");
    }

    private ElementsCollection parentIdInAws() {
        return $$(".catalog-table-content-items-item.parent > div > div:nth-child(2)");
    }

    private ElementsCollection notActiveChildCategoriesId() {
        return $$(".catalog-table-content-items-item.disabled > div > div:nth-child(4)");
    }

    private ElementsCollection notActiveParentCategoriesId() {
        return $$(".catalog-table-content-items-item.parent.disabled > div > div:nth-child(2)");
    }

    private ElementsCollection parentNameInAWS() {
        return $$(".catalog-table-content-items-item.parent > div >div:nth-child(5) > input");
    }

    private ElementsCollection notActiveParentCategoriesName() {
        return $$(".catalog-table-content-items-item.parent.disabled > div > div:nth-child(5) > input");
    }

    private ElementsCollection childNameInAWS() {
        return $$(".catalog-table-content-items-item.parent > ul >li > div >div:nth-child(5) > input");
    }

    private ElementsCollection notActiveChildCategoriesName() {
        return $$(".catalog-table-content-items-item.disabled > div > div:nth-child(4)");
    }

    private ElementsCollection notInCatalogCategories() {
        return $$(".catalog-table-content-items-item > ul >li > div >div:nth-child(5) > input[data-entity-id^=\"3\"]");
    }

    private ElementsCollection notInCatalogCateg2() {
        return $$(".catalog-table-content-items-item[data-node-id^=\"3\"] > ul >li > div >div:nth-child(5) > input");
    }

    public ElementsCollection crossCuttingCategoriesId() {
        return $$x("//*[@class='flex-box']/div[12]/input[@checked='checked']/../../div[4]");
    }

    private ElementsCollection categoriesIdInAWS() {
        return $$x("//*[@class='flex-box']//input[@checked='checked']/../../div[4]");
    }

    private ElementsCollection groupIdInAWS() {
        return $$(".catalog-table-content-items-item.parent > ul >li > div > div:nth-child(3)");
    }

    public SelenideElement fieldParentId() {
        return $x("//div[@class='w-box-content cnt_a clearfix']//input[@id='form_filter[nodeParentID]']");
    }

    public SelenideElement fieldSelectSkins() {
        return $x("//div[@id='filter_skin_chzn']//input[@type='text']");
    }


    public SelenideElement btnSearch() {
        return $x("//button[@class='btn btn-success search']");
    }

    public SelenideElement loadingText() {
        return $x("//div[@class='center loading-text']");
    }

    ElementsCollection parentCategoriesFromAws() {
        return $$x("//div[@class='size-300 flex-box']/input");
    }

    private SelenideElement parentAndChildCategoriesList() {
        return $x("//div[@class='catalog-table']");
    }

    private ElementsCollection notActiveChildCategoriesNameAWS() {
        return $$(".catalog-table-content-items-item.disabled > div > div:nth-child(5)>input");
    }

    private ElementsCollection limitedChildCategoriesNameAWS() {
        return $$x("//ul[@class='catalog-table-content-items-item-child ui-sortable']/li[not(contains(@class,'disabled'))]/div/div[5]/input");
    }


    @Step("Open AWS , Login in and open page custom-catalog. CatalogCategories_aws")
    public CatalogCategories_aws openAwsLoginInAndTransitionCustomCatalog() {
        new Login_aws().loginInAwsWithOpen();
        openPage(categoriesInAwsPage);
        return this;
    }

    @Step("Get All Child Categories From Catalog AWS. CatalogCategories_aws")
    public ArrayList<String> getAllChildCategoriesFromAWS() {
        new Login_aws().loginInAwsWithOpen();
        openPage(categoriesInAwsPage);
        ArrayList<String> allActiveChildCategoriesAWS = new ArrayList<>();
        for (int i = 0; i < childIdInAWS().size(); i++) {
            if (!childIdInAWS().get(i).text().isEmpty()) {
                allActiveChildCategoriesAWS.add(childIdInAWS().get(i).text());
            }
        }

        ArrayList<String> notActiveCategories = new ArrayList<>();
        for (int i = 0; i < notActiveChildCategoriesId().size(); i++) {
            if (!notActiveChildCategoriesId().get(i).text().isEmpty()) {
                notActiveCategories.add(notActiveChildCategoriesId().get(i).text());
            }
        }

        Collections.sort(allActiveChildCategoriesAWS);
        allActiveChildCategoriesAWS.removeAll(notActiveCategories);
        allActiveChildCategoriesAWS.removeIf(e -> e.startsWith("3"));
        System.out.println(allActiveChildCategoriesAWS.size());
        return allActiveChildCategoriesAWS;
    }

    @Step("Get All Parent Categories From AWS. CatalogCategories_aws")
    public ArrayList<String> getAllParentCategoriesFromAWS() {
        ArrayList<String> allActiveParentCategoriesAWS = new ArrayList<>();
        for (int i = 0; i < parentIdInAws().size(); i++) {
            if (!parentIdInAws().get(i).text().isEmpty()) {
                allActiveParentCategoriesAWS.add(parentIdInAws().get(i).text());
            }
        }

        ArrayList<String> notActiveParentCategories = new ArrayList<>();
        for (int i = 0; i < notActiveParentCategoriesId().size(); i++) {
            if (!notActiveParentCategoriesId().get(i).text().isEmpty()) {
                notActiveParentCategories.add(notActiveParentCategoriesId().get(i).text());
            }
        }

        Collections.sort(allActiveParentCategoriesAWS);
        allActiveParentCategoriesAWS.removeAll(notActiveParentCategories);
        System.out.println(allActiveParentCategoriesAWS.size());
        return allActiveParentCategoriesAWS;
    }

    @Step("Get All Parent Categories Name From AWS. CatalogCategories_aws")
    public ArrayList<String> getAllParentCategoriesNameFromAWS() {
        new Login_aws().loginInAwsWithOpen();
        openPage(parentCategoriesInAwsPage);
        ArrayList<String> allActiveParentCategoriesAWS = new ArrayList<>();
        for (int i = 0; i < parentNameInAWS().size(); i++) {
            if (!parentNameInAWS().get(i).attr("value").isEmpty()) {
                System.out.println(parentNameInAWS().get(i).attr("value").trim());
                allActiveParentCategoriesAWS.add(parentNameInAWS().get(i).attr("value").trim());
            }
        }

        ArrayList<String> notActiveParentCategories = new ArrayList<>();
        for (int i = 0; i < notActiveParentCategoriesName().size(); i++) {
            if (!notActiveParentCategoriesName().get(i).attr("value").isEmpty()) {
                notActiveParentCategories.add(notActiveParentCategoriesName().get(i).attr("value").trim());
            }
        }

        allActiveParentCategoriesAWS.removeAll(notActiveParentCategories);
        System.out.println(allActiveParentCategoriesAWS.size());
        for (int i = 0; i < allActiveParentCategoriesAWS.size(); i++) {
            System.out.println(allActiveParentCategoriesAWS.get(i));
        }
        return allActiveParentCategoriesAWS;
    }

    @Step("Get All ID group From Catalog AWS. CatalogCategories_aws")
    public ArrayList<String> getAllIdGroupFromAWS() {
        ArrayList<String> allActiveGroupAWS = new ArrayList<>();
        for (int i = 0; i < groupIdInAWS().size(); i++) {
            if (!groupIdInAWS().get(i).getText().isEmpty()) {
                allActiveGroupAWS.add(groupIdInAWS().get(i).text());
            }
        }
        Collections.sort(allActiveGroupAWS);
        return allActiveGroupAWS;
    }

    @Step("Get limited Child categories From Catalog AWS . CatalogCategories_aws")
    public List<String> getLimitedChildCategoriesNameFromAWS(List<String> list) {
        new Login_aws().loginInAwsWithOpen();
        openPage(childCategoriesInAwsPage);
        parentAndChildCategoriesList().shouldBe(visible);
        List<String> childCategoriesAWS = limitedChildCategoriesNameAWS().stream().map(n -> n.getAttribute("value").replaceAll("[^a-zA-ZÖö]", "")).limit(list.size()).collect(Collectors.toList());
        return childCategoriesAWS;
    }

    @Step("Get All Child Categories From Catalog AWS Using Set. CatalogCategories_aws")
    public Set<String> getAllChildCategoriesNameFromAWSusingSet() {
        new Login_aws().loginInAwsWithOpen();
        openPage(categoriesInAwsPage);
        Set<String> allActiveChildCategoriesAWS = new LinkedHashSet<>();
        for (int i = 0; i < childNameInAWS().size(); i++) {
            if (!childNameInAWS().get(i).attr("value").isEmpty()) {
                allActiveChildCategoriesAWS.add(childNameInAWS().get(i).attr("value").replaceAll("[^a-zA-Z]", ""));
            }
        }

        Set<String> notActiveCategories = new LinkedHashSet<>();
        for (int i = 0; i < notActiveChildCategoriesName().size(); i++) {
            notActiveCategories.add(notActiveChildCategoriesName().get(i).attr("value").trim());
        }

        Set<String> notInCatalogCategoriesList = new LinkedHashSet<>();
        for (int i = 0; i < notInCatalogCategories().size(); i++) {
            notInCatalogCategoriesList.add(notInCatalogCategories().get(i).attr("value").trim());

        }


        Set<String> notInCatalogCategories2 = new LinkedHashSet<>();
        for (int i = 0; i < notInCatalogCateg2().size(); i++) {
            System.out.println(notInCatalogCateg2().get(i).attr("value").trim());
            notInCatalogCategories2.add(notInCatalogCateg2().get(i).attr("value").trim());
        }

        allActiveChildCategoriesAWS.removeAll(notActiveCategories);
        allActiveChildCategoriesAWS.removeAll(notInCatalogCategoriesList);
        allActiveChildCategoriesAWS.removeAll(notInCatalogCategories2);
        Iterator iterator = allActiveChildCategoriesAWS.iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }
        return allActiveChildCategoriesAWS;
    }

    @Step("Get not active undercategories from AWS. CatalogCategories_aws")
    public ArrayList<String> getNotActiveUndercategoriesFromAWS() {
        new Login_aws().loginInAwsWithOpen();
        openPage(categoriesInAwsPage);
        ArrayList<String> notActiveCategories = new ArrayList<>();
        for (int i = 0; i < notActiveChildCategoriesId().size(); i++) {
            if (!notActiveChildCategoriesId().get(i).text().isEmpty()) {
                System.out.println(notActiveChildCategoriesId().get(i).text());
                notActiveCategories.add(notActiveChildCategoriesId().get(i).text());
            }
        }
        return notActiveCategories;
    }

    @Step("Get crossCutting categories from AWS. CatalogCategories_aws")
    public ArrayList<String> getCrossCuttingCategoriesFromAWS() {
        new Login_aws().loginInAwsWithOpen();
        openPage(categoriesInAwsPage);
        ArrayList<String> crossCuttingCategories = new ArrayList<>();
        crossCuttingCategories.addAll(crossCuttingCategoriesId().texts());
        return crossCuttingCategories;
    }

    @Step("Cleans then Add skins in field Select Skin. CatalogCategories_aws")
    public CatalogCategories_aws cleansAndAddSkinInFieldSelectSkin(String skins) {
        fieldSelectSkins().doubleClick().sendKeys(Keys.BACK_SPACE, Keys.BACK_SPACE);
        fieldSelectSkins().setValue(skins).pressEnter();
        return this;
    }

    @Step("Add filter paren id. CatalogCategories_aws")
    public CatalogCategories_aws addFilterParentId(String parentId) {
        fieldParentId().setValue(parentId);
        return this;
    }

    @Step("Click btn search. CatalogCategories_aws")
    public CatalogCategories_aws clickBtnSearch() {
        btnSearch().click();
        return this;
    }

    @Step("Wait until the preloader appears and disappears. CatalogCategories_aws")
    public CatalogCategories_aws waitUntilPreloaderAppearsAndDisappears() {
        loadingText().shouldBe(visible);
        loadingText().shouldNotBe(visible);
        return this;

    }

    @Step("Get all id active categories from AWS. CatalogCategories_aws")
    public ArrayList<String> getAllIdActiveCategories() {
        waitUntilPreloaderAppearsAndDisappears();
        ArrayList<String> allCategories = new ArrayList<>();
        for (int i = 0; i < categoriesIdInAWS().size(); i++) {
            if (!categoriesIdInAWS().get(i).getText().isEmpty()) {
                allCategories.add(categoriesIdInAWS().get(i).text());
            }
        }

        ArrayList<String> notActiveCategories = new ArrayList<>();
        for (int i = 0; i < notActiveChildCategoriesName().size(); i++) {
            if (!notActiveChildCategoriesName().get(i).getText().isEmpty()) {
                notActiveCategories.add(notActiveChildCategoriesName().get(i).text());
            }
        }

        allCategories.removeAll(notActiveCategories);
        Collections.sort(allCategories);
        return allCategories;
    }

    @Step("Get All Parent Categories from AWS. CatalogCategories_aws")
    public List<String> getParentCategories() {
        new Login_aws().loginInAwsWithOpen();
        openPage(parentCategoriesInAwsPage);
        List<String> allActiveParentCategoriesAWS = parentCategoriesFromAws().stream().map(n -> n.getAttribute("data-old-value")).collect(Collectors.toList());
        return allActiveParentCategoriesAWS;
    }

}