package PRF;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;

import static com.codeborne.selenide.Selenide.executeJavaScript;
import static com.codeborne.selenide.WebDriverRunner.url;

public class CommonMethods {

    public static String getCurrentShopFromJSVarInHTML() {
        String currentShop = executeJavaScript("return $siteSettings.lang");
        if (url().contains("piecesautopro.be")) currentShop = "bf";
        return currentShop.toUpperCase();
    }

    public void writerInFile(String fileName, boolean append, String write) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(fileName, append), StandardCharsets.UTF_8));
        System.out.println("Write in file");
        bufferedWriter.newLine();
        bufferedWriter.write(write);
        bufferedWriter.close();
    }
}
