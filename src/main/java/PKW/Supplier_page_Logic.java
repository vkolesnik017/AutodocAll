package PKW;


import io.qameta.allure.Step;


public class Supplier_page_Logic extends Supplier_page {


    @Step("Get and cuts out only name brand from title on page brand. Supplier_page")
    public String getAndCutNameBrandFromTitleOnPageBrand() {
        String name =  nameTitleOnPageBrand().getText().replace(nameTitleOnPageBrand().getText().substring(nameTitleOnPageBrand().getText().lastIndexOf(" ")),"");
        return name;
    }



}
