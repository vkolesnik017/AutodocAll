package ATD;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selectors.byCssSelector;
import static com.codeborne.selenide.Selenide.*;

public class Tyres_page {

  public SelenideElement imagesProductsTires() {
    return $(byCssSelector(".prod_img"));
  }

  SelenideElement seasonDropdown() { return $("select#form_Season"); }

  SelenideElement widthDropdown() { return $("select#form_Width"); }

  SelenideElement heightDropdown() { return $("select#form_CrossSections"); }

  SelenideElement diameterDropdown() { return $("#form_Size"); }

  SelenideElement typeDropdown() { return $("#form_R_ZR"); }

  SelenideElement submitTyresSelectorButton() { return $x("//*[@id='tyres_search']"); }

  SelenideElement tyresSelectorBlock() { return $(".reifen_select"); }

  SelenideElement tyresSelectorBlockPKW() { return $(".reifen_select_block_pkw"); }

  SelenideElement tyresSelectorBlockSUV() { return $(".reifen_select_block_suv"); }

  SelenideElement tyresSelectorBlockMOTO() { return $(".reifen_select_block_moto"); }

  SelenideElement tyresSelectorBlockLLKW() { return $(".reifen_select_block_llkw"); }

  SelenideElement tabSUVtype() { return $(".select_link_suv"); }

  SelenideElement tabLLKWtype() { return $(".select_link_llkw"); }

  SelenideElement tabMOTOtype() { return $(".select_link_moto"); }

  SelenideElement tyresValidationPopup() { return  $("#popup_car_tires_not_found > div.txt"); }

  SelenideElement closeTyresValidationPopupButton() { return  $("#popup_car_tires_not_found > a"); }

  SelenideElement brandDropdown() { return $x("(//*[@class='multiple-select__title'])[1]"); }

  SelenideElement brandApolloInDropdown() { return $x("//*[@id='Brand_apollo']/../label"); }

  SelenideElement speedIndexDropdown() { return $x("(//*[@class='multiple-select__title'])[2]"); }

  SelenideElement speedIndexHinDropdown() { return $x("//*[@id='SpeedIndex_h']/../label"); }

  SelenideElement allBrandsButton() { return $(".tyres-brands-link > a"); }

  SelenideElement allSizesButton() { return $(".all_sizes"); }

  SelenideElement mobileAppLink() { return $x("//*[@class='features']/li[4]"); }

  SelenideElement videoLink() { return $x("//*[@class='features']/li[5]"); }

  SelenideElement topBlock() { return $(".ct_toppop_products"); }

  ElementsCollection productsInTopBlock() { return $$(".ct_toppop_products > li"); }

  SelenideElement productInTopBlock() { return $(".ct_toppop_products > li > span"); }

  SelenideElement michelinBrandInBrandCatalog() { return $x("//a[contains (text(),'Michelin')]"); }

  SelenideElement brandButtonInTopBlock() { return $(".unsere a img"); }

  SelenideElement summerButtonInSeasonBlock() { return $(".seasons_item_summer "); }

  SelenideElement winterButtonInSeasonBlock() { return $(".seasons_item_winter"); }

  SelenideElement priceInTopBlock() { return $(".ct_toppop_products .actual-price"); }

  SelenideElement allBrandsList() { return $(".table_sizes"); }

  SelenideElement allBrandsListTitle() { return $(".title_tires"); }

  SelenideElement seasonBlock() { return $(".tires_ban"); }

  ElementsCollection seasonsInSeasonBlock() { return $$(".tires_ban > ul > li"); }

  SelenideElement diameterRelinkBlock() { return $x("//div[@class='tit' and contains (text(),'Zoll')]/.."); }

  ElementsCollection linksInDiameterblock() { return $$x("//div[@class='tit' and contains (text(),'Zoll')]/..//li"); }

  SelenideElement dimensionRelinkBlock() { return $x("//div[@class='tit' and contains (text(),'Reifengrößen')]/.."); }

  ElementsCollection linksInDimensionRelinkBlock() { return $$x("//div[@class='tit' and contains (text(),'Reifengrößen')]/..//li"); }

  SelenideElement brandTopBlock() { return $(".unsere"); }

  SelenideElement brandTopBlockTitle() { return $(".unsere .tit"); }

  SelenideElement secondButtonInTopBrandSlider() { return $("#slick-slide-control11"); }

  ElementsCollection brandsInSlider() { return $$x("//div[@class='unsere']//img"); }



}
