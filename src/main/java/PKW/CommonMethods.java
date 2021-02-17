package PKW;


import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.ex.UIAssertionError;
import io.qameta.allure.Step;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Random;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.byXpath;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;
import static com.codeborne.selenide.WebDriverRunner.url;
import static org.testng.Assert.assertTrue;

public class CommonMethods {

    public static String password = "pkwtest";
    static String testNumberThatPutOrderInTest = "200+002";
    public static String passwordForPayments = "atdtest1";


    @Step("Generates random mail")
    public static String mailRandom() {
        Random randomGenerator = new Random();
        int random = randomGenerator.nextInt();
        return "autotest" + random + "@mailinator.com";
    }

    @Step("Generates random email on @mailinator.com")
    public static String mailRandomMailinator(String QCnumber) {
        Random randomGenerator = new Random();
        int random = randomGenerator.nextInt();
        return "QC_" + QCnumber + "_autotestMail" + random + "@mailinator.com";
    }

    @Step("{url} Open page with close popup")
    public static void openPage(String url) {
        System.out.println(url);
        open(url);
        closeCookiesFooterMessage();
    }

    private static void closeCookiesFooterMessage() {
        try {
            $(byXpath("//div[@class='block-cookies__close']")).click();
        } catch (UIAssertionError e) {
            System.out.println("Cookies block doesn't appear");
        }
    }


    public static String getCurrentShopFromJSVarInHTML() {
        String currentShop = executeJavaScript("return $siteSettings.lang");
        return currentShop.toUpperCase();
    }

    public void writerInFile(String fileName, boolean append, String write) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(fileName, append), StandardCharsets.UTF_8));
        System.out.println("Write in file");
        bufferedWriter.newLine();
        bufferedWriter.write(write);
        bufferedWriter.close();
    }

    @Step("Checking counter increase on {increaseCount} of product quantity")
    void checkingCounterIncrease(int increaseCount, SelenideElement counterValue, SelenideElement counterPlus) {
        int startValue = Integer.parseInt(counterValue.getValue());
        for (int i = 1; i <= increaseCount; i++) {
            counterPlus.click();
            sleep(2000);
            int valueAfterIncrease = startValue + i;
            counterValue.shouldHave(value(String.valueOf(valueAfterIncrease)));
        }
        int valueAfterAllIncrease = startValue + increaseCount;
        counterValue.shouldHave(value(String.valueOf(valueAfterAllIncrease)));
    }

    @Step("Checking counter decrease on {decreaseCount} of product quantity")
    void checkingCounterDecrease(int decreaseCount, SelenideElement counterValue, SelenideElement counterMinus) {
        int startValue = Integer.parseInt(counterValue.getValue());
        for (int i = 1; i <= decreaseCount; i++) {
            counterMinus.click();
            sleep(2000);
            int valueAfterDecrease = startValue - i;
            counterValue.shouldHave(value(String.valueOf(valueAfterDecrease)));
        }
        int valueAfterAllDecrease = startValue - decreaseCount;
        counterValue.shouldHave(value(String.valueOf(valueAfterAllDecrease)));
    }

    @Step("Checking follow url on new tab and close tab")
    public void checkingUrlAndCloseTab(String expectedUrl) {
        switchTo().window(1);
        String actualUrl = url();
        assertTrue(actualUrl.contains(expectedUrl));
        getWebDriver().close();
        switchTo().window(0);
    }

    @Step("Checking datenschutzerklarung link behavior")
    public void checkingDatenschutzerklarungLinkBehavior(SelenideElement datenschutzerklarungLink, String cssValue) {
        datenschutzerklarungLink.shouldHave(attribute("title", "Datenschutzerklärung"));
        datenschutzerklarungLink.shouldHave(cssValue("cursor", "pointer"));
        datenschutzerklarungLink.shouldHave(cssValue("text-decoration", cssValue));
        datenschutzerklarungLink.click();
        checkingUrlAndCloseTab("/datenschutz");
    }

    @Step("Checking Privacy Policy link behavior for shop EN")
    public void checkingPrivacyPolicyLinkBehavior(SelenideElement PrivacyPolicyLink, String cssValue) {
        PrivacyPolicyLink.shouldHave(attribute("title", "Privacy Policy"));
        PrivacyPolicyLink.shouldHave(cssValue("cursor", "pointer"));
        PrivacyPolicyLink.shouldHave(cssValue("text-decoration", cssValue));
        PrivacyPolicyLink.click();
        checkingUrlAndCloseTab("/privacy-policy");
    }

    @Step("get text from not visible element {expected element}")
    public static String getTextFromUnVisibleElement(SelenideElement element){
        return (String) (executeJavaScript(
                "return jQuery(arguments[0]).text();", element));
    }

    //checking selector

    public void checkingMakerName(SelenideElement makerNameLocator, String makerName, String fileForReport, String url) throws IOException {
        makerNameLocator.shouldBe(Condition.visible);
        String makerNameTextFromSelector = makerNameLocator.getAttribute("innerText").replace('Ë','E');
        makerName = makerName.replace('Ë','E');
        if (!makerName.equals(makerNameTextFromSelector))
            writerInFile(fileForReport, true, "Maker from data doesn't equals maker from selector:" + "#" + makerName + "#" + makerNameTextFromSelector + "#" + url);
    }

    public void checkingGroupName(SelenideElement groupNameLocator, String groupName, String fileForReport, String url) throws IOException {
        groupNameLocator.shouldBe(Condition.visible);
        String groupNameTextFromSelector = groupNameLocator.getAttribute("label");
        if (!groupName.equals(groupNameTextFromSelector))
            writerInFile(fileForReport, true, "Group from data doesn't equals group from selector:" + "#" + groupName + "#" + groupNameTextFromSelector + "#" + url);
    }

    public void checkingModelName(SelenideElement modelNameLocator, String modelName, String fileForReport, String url) throws IOException {
        modelNameLocator.shouldBe(Condition.visible);
        String modelNameTextFromSelector = modelNameLocator.getAttribute("innerText");
        modelNameTextFromSelector = modelNameTextFromSelector.substring(0, modelNameTextFromSelector.lastIndexOf("(")).trim();
        if (!modelName.equals(modelNameTextFromSelector))
            writerInFile(fileForReport, true, "Model from data doesn't equals model from selector:" + "#" + modelName + "#" + modelNameTextFromSelector + "#" + url);
    }

    public void checkingCarName(SelenideElement carNameLocator, String carName, String yearBegin, String yearEnd, String kw, String hp, String fileForReport, String url) throws IOException {
        carNameLocator.shouldBe(Condition.visible);
        String carNameTextFromSelector = carNameLocator.getText();

        String yearBeginMonth = yearBegin.substring(4);
        yearBegin = yearBeginMonth.concat("/").concat(yearBegin.substring(0, 4)).trim();
        if (!yearEnd.equals("0")) {
            String yearEndMonth = yearEnd.substring(4);
            yearEnd = yearEndMonth.concat("/").concat(yearEnd.substring(0, 4)).trim();
        } else {
            yearEnd = "...";
        }
        carNameTextFromSelector = carNameTextFromSelector.replace(" ", "").trim();
        String carNameFull = carName + "(" + kw + "KW" + "/" + hp + "HP" + ")" + "(" + yearBegin + "-" + yearEnd + ")";
        carNameFull = carNameFull.replace(" ", "").trim();


        if (!carNameFull.equals(carNameTextFromSelector))
            writerInFile(fileForReport, true, "Car from data doesn't equals car from selector:" + "#" + carNameFull + "#" + carNameTextFromSelector + "#" + url);
    }

    //Checks element clickability
    public static Condition clickable = and("can be clicked", visible, enabled);

    @Step("get attribute from not visible element {expected element}")
    public static String getAttributeFromUnVisibleElement(SelenideElement element, String attribute) {
        return (String) (executeJavaScript("return arguments[0].getAttribute('" + attribute + "')", element));
    }

    @Step("Pulling prices from text of element")
    public static Float getPriceFromElement(SelenideElement element) {
        element.shouldBe(visible);
        return Float.parseFloat(element.text().replaceAll("[^0-9,]", "").replace(",", "."));
    }
}
