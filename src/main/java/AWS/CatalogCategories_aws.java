package AWS;


import com.codeborne.selenide.ElementsCollection;
import io.qameta.allure.Step;

import java.util.*;

import static ATD.CommonMethods.openPage;
import static com.codeborne.selenide.Selenide.$$;
import static com.codeborne.selenide.Selenide.$$x;

public class CatalogCategories_aws {

    private String categoriesInAwsPage = "https://aws.autodoc.de/custom-catalog?filter%5Blang%5D=de&filter%5Bskin%5D%5B%5D=atd&filter%5Borigin%5D=&filter%5BnodeParentID%5D=&filter%5BnodeID%5D=&filter%5Bga%5D=&filter%5Bonly%5D=0&filter%5BorderBy%5D=groupRating&submitSearch=";

    private String parentCategoriesInAwsPage = "https://aws.autodoc.de/custom-catalog?filter%5Blang%5D=de&filter%5Bskin%5D%5B%5D=atd&filter%5Borigin%5D=&filter%5BnodeParentID%5D=&filter%5BnodeID%5D=&filter%5Bga%5D=&filter%5Bonly%5D=1&filter%5BorderBy%5D=groupRating&submitSearch=";

    private ElementsCollection childIdInAWS() { return $$(".catalog-table-content-items-item.parent > ul >li > div > div:nth-child(4)"); }

    private ElementsCollection parentIdInAws() { return $$(".catalog-table-content-items-item.parent > div > div:nth-child(2)"); }

    private ElementsCollection notActiveChildCategoriesId() { return $$(".catalog-table-content-items-item.disabled > div > div:nth-child(4)"); }

    private ElementsCollection notActiveParentCategoriesId() { return $$(".catalog-table-content-items-item.parent.disabled > div > div:nth-child(2)"); }

    private ElementsCollection parentNameInAWS() { return $$(".catalog-table-content-items-item.parent > div >div:nth-child(5) > input"); }

    private ElementsCollection notActiveParentCategoriesName() { return $$(".catalog-table-content-items-item.parent.disabled > div > div:nth-child(5) > input"); }

    private ElementsCollection childNameInAWS() { return $$(".catalog-table-content-items-item.parent > ul >li > div >div:nth-child(5) > input"); }

    private ElementsCollection notActiveChildCategoriesName() { return $$(".catalog-table-content-items-item.disabled > div > div:nth-child(5) > input"); }

    private ElementsCollection notInCatalogCategories() { return $$(".catalog-table-content-items-item > ul >li > div >div:nth-child(5) > input[data-entity-id^=\"3\"]"); }

    private ElementsCollection notInCatalogCateg2() { return $$(".catalog-table-content-items-item[data-node-id^=\"3\"] > ul >li > div >div:nth-child(5) > input"); }

    public ElementsCollection crossCuttingCategoriesId() { return $$x("//*[@class='flex-box']/div[12]/input[@checked='checked']/../../div[4]"); }

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

    @Step("Get All Child Categories From Catalog AWS. CatalogCategories_aws")
    public ArrayList<String> getAllChildCategoriesNameFromAWS() {
        new Login_aws().loginInAwsWithOpen();
        openPage(categoriesInAwsPage);
        ArrayList<String> allActiveChildCategoriesAWS = new ArrayList<>();
        for (int i = 0; i < childNameInAWS().size(); i++) {
            if (!childNameInAWS().get(i).attr("value").isEmpty()) {
                System.out.println(childNameInAWS().get(i).attr("value").trim());
                allActiveChildCategoriesAWS.add(childNameInAWS().get(i).attr("value").trim());
            }
        }

        ArrayList<String> notActiveCategories = new ArrayList<>();
        for (int i = 0; i < notActiveChildCategoriesName().size(); i++) {
                notActiveCategories.add(notActiveChildCategoriesName().get(i).attr("value").trim());
        }

        ArrayList<String> notInCatalogCategoriesList = new ArrayList<>();
        for (int i = 0; i < notInCatalogCategories().size(); i++) {
                notInCatalogCategoriesList.add(notInCatalogCategories().get(i).attr("value").trim());

        }

        ArrayList<String> notInCatalogCategories2 = new ArrayList<>();
        for (int i = 0; i < notInCatalogCateg2().size(); i++) {
                notInCatalogCategories2.add(notInCatalogCateg2().get(i).attr("value").trim());
        }

        allActiveChildCategoriesAWS.removeAll(notActiveCategories);
        allActiveChildCategoriesAWS.removeAll(notInCatalogCategoriesList);
        allActiveChildCategoriesAWS.removeAll(notInCatalogCategories2);
        for (int i = 0; i < allActiveChildCategoriesAWS.size(); i++) {
            System.out.println(allActiveChildCategoriesAWS.get(i));
        }

        return allActiveChildCategoriesAWS;
    }

    @Step("Get All Child Categories From Catalog AWS Using Set. CatalogCategories_aws")
    public Set<String> getAllChildCategoriesNameFromAWSusingSet() {
        new Login_aws().loginInAwsWithOpen();
        openPage(categoriesInAwsPage);
        Set<String> allActiveChildCategoriesAWS = new LinkedHashSet<>();
        for (int i = 0; i < childNameInAWS().size(); i++) {
            if (!childNameInAWS().get(i).attr("value").isEmpty()) {
                allActiveChildCategoriesAWS.add(childNameInAWS().get(i).attr("value").trim());
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


        Set<String> notInCatalogCategories2 =  new LinkedHashSet<>();
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
}
