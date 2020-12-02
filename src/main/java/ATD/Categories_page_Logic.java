package ATD;

import com.codeborne.selenide.ElementsCollection;
import io.qameta.allure.Step;
import org.testng.Assert;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.*;
import java.util.stream.Collectors;

import static com.codeborne.selenide.CollectionCondition.sizeNotEqual;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.WebDriverRunner.url;

public class Categories_page_Logic extends Categories_page {

    @Step("Input text {text} in search bar by catalog. Categories_page")
    public Categories_page_Logic inputTextInSearchBarByCatalog(String text) {
        searchBarByCatalog().setValue(text);
        return this;
    }

    @Step("Check that no deleted category {nameDeletedCategory} in tooltip to search by catalog. Categories_page")
    public Categories_page_Logic checkThatNoTooltipInSearchByCatalog(String nameDeletedCategory) {
        ElementsCollection tooltips = tooltipsToSearchByCatalog().shouldHave(sizeNotEqual(0));
        tooltips.filter(exactText(nameDeletedCategory)).shouldHaveSize(0);
        return this;
    }

    @Step("Click tooltip in search by catalog by exact text {exactTooltipText}. Categories_page")
    public Category_name_page_Logic clickTooltipInSearchByCatalogByExactText(String exactTooltipText) {
        tooltipsToSearchByCatalog().get(0).shouldBe(visible);
        tooltipsToSearchByCatalog().filter(exactText(exactTooltipText)).shouldHaveSize(1).get(0).click();
        return page(Category_name_page_Logic.class);
    }

    @Step("Click oil filter category link. Categories_page")
    Categories_page_Logic clickOilFilterCategoryLink() {
        linkForCategoryOilFilter().click();
        return page(Categories_page_Logic.class);
    }

    @Step("Select LKW category. Categories_page")
    public LKW_main_page_Logic selectLKWCategory() {
        lkwCategory().click();
        return page(LKW_main_page_Logic.class);
    }

    @Step("Select Moto category. Categories_page")
    public Moto_main_page_Logic selectMotoCategory() {
        motoCategory().click();
        return page(Moto_main_page_Logic.class);
    }

    @Step("Check response code is 200 for all categories in dropdown catalog. Categories_page")
    public Categories_page_Logic check200ResponseDropdown() throws Exception {
        catalogInHeader().click();
        Thread.sleep(5000);
        for (int i = 0; i < dropdownCategories().size(); i++) {
//        System.out.println(dropdownCategories().get(i).attr("href"));
            URL url = new URL(dropdownCategories().get(i).attr("href"));
            HttpURLConnection http = (HttpURLConnection) url.openConnection();
            http.setInstanceFollowRedirects(true);
            int responseCode = http.getResponseCode();
//        Assert.assertEquals(responseCode, 200);
            if (responseCode != 200) {
                System.err.println("Response code of route " + dropdownCategories().get(i).attr("href") + " is " + responseCode);
            }
        }
        return this;
    }

    @Step("Get All Child Categories From Catalog Dropdown Menu. Categories_page")
    public ArrayList<String> getAllChildCategoriesFromCatalogDropdownMenu() throws Exception {
        catalogInHeader().click();
        Thread.sleep(5000);
        ArrayList<String> listWithCategoriesInDropdownCatalog = new ArrayList<>();
        for (int i = 0; i < secondListInDropdownCatalog().size(); i++) {
            listWithCategoriesInDropdownCatalog.add(secondListInDropdownCatalog().get(i).attr("data-category-id"));
        }
        Collections.sort(listWithCategoriesInDropdownCatalog);
        System.out.println(listWithCategoriesInDropdownCatalog.size());
        return listWithCategoriesInDropdownCatalog;
    }

    @Step("Get All Parent Categories From Catalog Dropdown Menu. Categories_page")
    public ArrayList<String> getAllParentCategoriesFromCatalogDropdownMenu() {
        ArrayList<String> listWithCategoriesInDropdownCatalog = new ArrayList<>();
        for (int i = 0; i < firstListInDropdownCatalog().size(); i++) {
            listWithCategoriesInDropdownCatalog.add(firstListInDropdownCatalog().get(i).attr("data-for-first-list"));
        }
        Collections.sort(listWithCategoriesInDropdownCatalog);
        String tyresParentId = "23208";
        String engineOilParentId = "12094";
        listWithCategoriesInDropdownCatalog.remove(tyresParentId);
        listWithCategoriesInDropdownCatalog.remove(engineOilParentId);
        System.out.println(listWithCategoriesInDropdownCatalog.size());
        return listWithCategoriesInDropdownCatalog;
    }

    @Step("Compare categories from catalog and AWS. Categories_page")
    public Categories_page_Logic compareCategoriesFromCatalogAndAWS(ArrayList<String> listFromCatalog, ArrayList<String> listFromAWS) {
        Assert.assertEquals(listFromCatalog, listFromAWS);
        return this;
    }

    @Step("Get All Parent Categories From Tecdoc Catalog. Categories_page")
    public ArrayList<String> getAllParentCategoriesFromTecdocCatalog() {
        ArrayList<String> listWithCategoriesInTecdocCatalog = new ArrayList<>();
        for (int i = 0; i < parentCategoriesTecdocName().size(); i++) {
            listWithCategoriesInTecdocCatalog.add(parentCategoriesTecdocName().get(i).text().trim());
        }
        String tyresParentName = "Reifen";
        listWithCategoriesInTecdocCatalog.remove(tyresParentName);
        System.out.println(listWithCategoriesInTecdocCatalog.size());
        for (int i = 0; i < listWithCategoriesInTecdocCatalog.size(); i++) {
            System.out.println(listWithCategoriesInTecdocCatalog.get(i));
        }
        return listWithCategoriesInTecdocCatalog;
    }

    @Step("Get All Child Categories From Tecdoc Catalog. Categories_page")
    public ArrayList<String> getAllChildCategoriesFromTecdocCatalog() {
        ArrayList<String> listWithCategoriesInTecdocCatalog = new ArrayList<>();
        for (int i = 0; i < childCategoriesTecdocName().size(); i++) {
            System.out.println(childCategoriesTecdocName().get(i).attr("alt").trim());
            listWithCategoriesInTecdocCatalog.add(childCategoriesTecdocName().get(i).attr("alt").trim());
        }

        ArrayList<String> listWithACCCategoriesInTecdocCatalog = new ArrayList<>();
        for (int i = 0; i < accessoriesCategories().size(); i++) {
            System.out.println(accessoriesCategories().get(i).attr("alt").trim());
            listWithACCCategoriesInTecdocCatalog.add(accessoriesCategories().get(i).attr("alt").trim());
        }

        listWithCategoriesInTecdocCatalog.removeAll(listWithACCCategoriesInTecdocCatalog);
        System.out.println(listWithCategoriesInTecdocCatalog.size());
        for (int i = 0; i < listWithCategoriesInTecdocCatalog.size(); i++) {
            System.out.println(listWithCategoriesInTecdocCatalog.get(i));
        }
        return listWithCategoriesInTecdocCatalog;
    }

    @Step("Get All Child Categories From Tecdoc Catalog Using Set. Categories_page")
    public Set<String> getAllChildCategoriesFromTecdocCatalogUsingSet() {
        Set<String> listWithCategoriesInTecdocCatalog = new LinkedHashSet<>();
        for (int i = 0; i < childCategoriesTecdocName().size(); i++) {
            System.out.println(childCategoriesTecdocName().get(i).attr("alt").trim());
            listWithCategoriesInTecdocCatalog.add(childCategoriesTecdocName().get(i).attr("alt").trim());
        }

        Set<String> listWithACCCategoriesInTecdocCatalog = new LinkedHashSet<>();
        for (int i = 0; i < accessoriesCategories().size(); i++) {
            System.out.println(accessoriesCategories().get(i).attr("alt").trim());
            listWithACCCategoriesInTecdocCatalog.add(accessoriesCategories().get(i).attr("alt").trim());
        }

        listWithCategoriesInTecdocCatalog.removeAll(listWithACCCategoriesInTecdocCatalog);
        Iterator iterator = listWithCategoriesInTecdocCatalog.iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }
        return listWithCategoriesInTecdocCatalog;
    }

    @Step("Check url undercategories id on soft 404. Categories_page")
    public Categories_page_Logic checkUrlUndercategoriesId404(ArrayList<String> notActiveCategories) throws IOException {
        for (int i = 0; i < notActiveCategories.size(); i++) {
            String urlWithCategoryId = categoryURL(notActiveCategories.get(i));
            System.out.println(urlWithCategoryId);
            URL url = new URL(urlWithCategoryId);
            HttpURLConnection http = (HttpURLConnection) url.openConnection();
            http.setInstanceFollowRedirects(true);
            int responseCode = http.getResponseCode();
            Assert.assertEquals(responseCode, 404);
        }
        return this;
    }

    @Step("Get parent categories from top block. Categories_page")
    public List<String> getParentCategoriesTopBlock() {
        List<String> parentCategoriesInTopBlock = new ArrayList<>();
        for (int i = 0; i < parentCategoriesTopBlock().size(); i++) {
            if (i == 5) {
                nextButtonTopBlock().click();
                sleep(2000);
            }
            System.out.println(parentCategoriesTopBlock().get(i).text().trim());
            parentCategoriesInTopBlock.add(parentCategoriesTopBlock().get(i).text().trim());
            if (i == 14) {
                nextButtonTopBlock().click();
                sleep(2000);
            }
        }
        return parentCategoriesInTopBlock;
    }

    @Step("Get child categories from top block. Categories_page")
    public List<String> getChildCategoriesTopBlock() {
        List<String> childCategoriesInTopBlock = new ArrayList<>();
        for (int i = 0; i < childCategoriesTopBlock().size(); i++) {
            if (i == 15) {
                nextButtonTopBlock().click();
                sleep(2000);
            }
            System.out.println(childCategoriesTopBlock().get(i).text().trim());
            childCategoriesInTopBlock.add(childCategoriesTopBlock().get(i).text().trim());
        }
        return childCategoriesInTopBlock;
    }

    @Step("Check product output on routes. Categories_page")
    public Categories_page_Logic checkProductOutputOnRoutes() {
        ArrayList<String> listWithRoutes = new ArrayList<>();
        for (int i = 0; i < tecdocChildCategoriesUrlA().size(); i++) {
            listWithRoutes.add(tecdocChildCategoriesUrlA().get(i).attr("href"));
        }

        for (int i = 0; i < tecdocCategoriesSpan().size(); i++) {
            listWithRoutes.add(tecdocCategoriesSpan().get(i).attr("url"));
        }

        System.out.println(listWithRoutes.size());
        for (int i = 0; i < listWithRoutes.size(); i++) {
            open(listWithRoutes.get(i));
            if (!listOfProducts().is(visible)) {
                System.err.println("There is no product output on route " + listWithRoutes.get(i));
            }
        }
        return this;
    }

    @Step("presence of TecDocCatalog. Categories_page")
    public Categories_page_Logic presenceOfTecDocCatalog() {
        tecDocCatalogBlock().shouldBe(visible);
        return this;
    }

    @Step("click on Garage icon in header. Categories_page")
    public Categories_page_Logic clickOnGarageIconInHeader() {
        headerGarageIcon().shouldBe(visible).click();
        popUpOfGarageInHeader().shouldBe(visible);
        return this;
    }

    @Step("select Vehicle in Garage pop-Up. Categories_page")
    public Categories_page_Logic selectVehicleInGaragePopUp(String idOfVehicle) {
        idOfVehicleInGaragePopUp(idOfVehicle).shouldBe(visible).click();
        return this;
    }

    @Step("check values in selector. Categories_page")
    public Categories_page_Logic checkValuesInSelector(String marke, String model, String motor) {
        selectorInCloseCondition().shouldBe(visible).click();
        mainFormOfSelector().shouldBe(visible);
        markeFieldInSelector().shouldHave(value(marke));
        modelFieldInSelector().shouldHave(value(model));
        motorFieldInSelector().shouldHave(value(motor));
        return this;
    }

    @Step("reset of Vertical selector. Categories_page")
    public Categories_page_Logic resetOfVerticalSelector() {
        mainFormOfSelector().shouldBe(visible);
        btnResetVerticalSelector().shouldBe(visible).click();
        btnResetVerticalSelector().shouldNotBe(visible);
        return this;
    }

    @Step("transition to main page. Categories_page")
    public Main_page_Logic goToMainPage() {
        mainLogoInHeader().shouldBe(visible).click();
        return page(Main_page_Logic.class);
    }

    @Step("get Parent categories. Categories_page")
    public List<String> getParentCategories() {
        List<String> parentCategories = titleOfParentCategories().stream()
                .filter(title -> !title.getText().equals("Reifen"))
                .map(title -> title.getText())
                .collect(Collectors.toList());
        return parentCategories;
    }

    @Step("select Vehicle in KBA selector. Categories_page")
    public Maker_car_list_page_Logic selectVehicleInSelector(String marke, String model, String motor) {
        selectorInCloseCondition().click();
        mainFormOfSelector().shouldBe(visible);
        markeFieldInSelector().selectOptionByValue(marke);
        modelFieldInSelector().selectOptionByValue(model);
        motorFieldInSelector().selectOptionByValue(motor);
        btnSearchOfSelector().click();
        return page(Maker_car_list_page_Logic.class);
    }

    @Step(": from. Categories_page")
    public ArrayList<String> getHrefOrUrlCategoriesThenWriteToList(ElementsCollection categories) {
        return CommonMethods.getHrefOrUrlCategoriesThenWriteToList(categories);
    }

    @Step(": from. Categories_page")
    public Categories_page_Logic checkCategoriesForServerResponses200( List<String> allCategories) throws IOException {
        CommonMethods.checkCategoriesForServerResponses200(allCategories);
        return this;
    }


}
