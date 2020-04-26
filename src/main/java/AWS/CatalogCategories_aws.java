package AWS;


import com.codeborne.selenide.ElementsCollection;
import io.qameta.allure.Step;

import java.util.ArrayList;
import java.util.Collections;

import static ATD.CommonMethods.openPage;
import static com.codeborne.selenide.Selenide.$$x;

public class CatalogCategories_aws {

    ElementsCollection childIdInAWS() { return $$x("//*[@class='catalog-table-content-items-item-child ui-sortable']/li/div/div[4]"); }

    @Step("Get All Categories From Catalog AWS")
    public ArrayList<Integer> getAllCategoriesFromAWS() {
        new Login_aws().loginInAwsWithOpen();
        openPage("https://aws.autodoc.de/custom-catalog?filter%5Blang%5D=de&filter%5Bskin%5D%5B%5D=atd&filter%5Borigin%5D=&filter%5BnodeParentID%5D=&filter%5BnodeID%5D=&filter%5Bga%5D=&filter%5Bonly%5D=0&filter%5BorderBy%5D=groupRating&submitSearch=");
        System.out.println(childIdInAWS().size());
        ArrayList<Integer> arrayListAWS = new ArrayList<>();
        for (int i = 0; i < childIdInAWS().size(); i++) {
            System.out.println(childIdInAWS().get(i).text());
            if (!childIdInAWS().get(i).text().isEmpty()) {
                arrayListAWS.add(Integer.parseInt(childIdInAWS().get(i).text()));
            }
        }
        Collections.sort(arrayListAWS);
        System.out.println(arrayListAWS.size());
        return arrayListAWS;
    }
}
