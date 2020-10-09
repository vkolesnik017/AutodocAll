package PKW;


import io.qameta.allure.Step;


public class Supplier_page_Logic extends Supplier_page {

    @Step("Get and cuts out only name from second Breadcrumbs. Supplier_page")
    public String getAndCutNameFromSecondBreadCrumb() {
        String nameBrand = nameSecondBreadCrumb().getText().replace(nameSecondBreadCrumb().getText().substring(nameSecondBreadCrumb().getText().lastIndexOf(" ")), "");
        return nameBrand;
    }

}
