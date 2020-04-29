package AWS;


import com.codeborne.selenide.ElementsCollection;
import io.qameta.allure.Step;

import java.util.ArrayList;
import java.util.Collections;

import static ATD.CommonMethods.openPage;
import static com.codeborne.selenide.Selenide.$$;

public class CatalogCategories_aws {

    private String categoriesInAwsPage = "https://aws.autodoc.de/custom-catalog?filter%5Blang%5D=de&filter%5Bskin%5D%5B%5D=atd&filter%5Borigin%5D=&filter%5BnodeParentID%5D=&filter%5BnodeID%5D=&filter%5Bga%5D=&filter%5Bonly%5D=0&filter%5BorderBy%5D=groupRating&submitSearch=";

    private ElementsCollection childIdInAWS() { return $$(".catalog-table-content-items-item.parent > ul >li > div > div:nth-child(4)"); }

    private ElementsCollection parentIdInAws() { return $$(".catalog-table-content-items-item.parent > div > div:nth-child(2)"); }

    private ElementsCollection notActiveChildCategoriesId() { return $$(".catalog-table-content-items-item.disabled > div > div:nth-child(4)"); }

    private ElementsCollection notActiveParentCategoriesId() { return $$(".catalog-table-content-items-item.parent.disabled > div > div:nth-child(2)"); }

    @Step("Get All Child Categories From Catalog AWS")
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

    @Step("Get All Parent Categories From AWS")
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
}
