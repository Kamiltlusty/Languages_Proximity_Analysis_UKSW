package pl.zespolowy.Translation;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import pl.zespolowy.Words.WordSet;

public abstract class Translator {
    protected final WebDriver driver;
    private TranslatorType translatorType;

    public Translator() {
        ChromeOptions options = new ChromeOptions();
        //options.addArguments("no-sandbox", "headless");
        driver = new ChromeDriver(options);
    }

    //public Translator(TranslatorType translatorType) {
    //    System.out.println("translator constructor");
    //    this.translatorType = translatorType;
    //    ChromeOptions options = new ChromeOptions();
    //    //options.addArguments("no-sandbox", "headless");
    //    driver = new ChromeDriver(options);
    //}



    public abstract Translation translate(WordSet wordSet, String sourceLanguage, String targetLanguage);
    //{
    //    return switch (translatorType) {
    //        case GOOGLE -> goolgeTranlate(content, sourceLanguage, targetLanguage);
    //        case CHAT_GPT -> chatGPTTranslate(content, sourceLanguage, targetLanguage);
    //        case GEMINI -> geminiTranslate(content, sourceLanguage, targetLanguage);
    //    };
    //}


    //private Translation goolgeTranlate(String content, String sourceLanguage, String targetLanguage);

    //private Translation chatGPTTranslate(String content, String sourceLanguage, String targetLanguage) {
    //    String baseUrl = "https://chatgpt.com/";
    //    driver.get(baseUrl);
//
    //    //var input =
//
    //    //WebElement loginButton;
    //    //while (true) {
    //    //    try {
    //    //        Thread.sleep(10);
    //    //        loginButton = driver.findElement(By.xpath("/html/body/div[1]/div[1]/div[1]/div[3]/button"));
    //    //        break;
    //    //    }
    //    //    catch (Exception _) {}
    //    //}
    //    //loginButton.click();
//
//
    //     //var cloudFlare = driver.findElement(By.xpath("//*[@id=\"PNSoX8\"]/div/label/input"));
    //     //cloudFlare.click();
//
//
    //    return new Translation("");
    //}


    //private Translation geminiTranslate(String content, String sourceLanguage, String targetLanguage) {
    //    //String baseUrl = "https://accounts.google.com/v3/signin/identifier?continue=https%3A%2F%2Fgemini.google.com%2Fsignin%3Fcontinue%3Dhttps%3A%2F%2Fgemini.google.com%2F&followup=https%3A%2F%2Fgemini.google.com%2Fsignin%3Fcontinue%3Dhttps%3A%2F%2Fgemini.google.com%2F&ifkv=AcMMx-eY9lTGNmCqI5GvGWr74G6KTET0bp8RZwOmnaFTYw8aq-zyarN-5XzLmdP09adKQ1myF3VD&passive=1209600&flowName=GlifWebSignIn&flowEntry=ServiceLogin&dsh=S-1894582574%3A1732493067043135&ddm=1";
    //    String baseUrl = "https://gemini.google.com/?hl=pl";
//
    //    driver.get(baseUrl);
    //    googleConsent("account.google.com");
//
    //    var loginButton = driver.findElement(By.xpath("//*[@id=\"gb\"]/div/div[1]/a"));
    //    loginButton.click();
//
    //    //googleConsent("account.google.com");
//
    //    var emailInput = driver.findElements(By.tagName("input"));
    //    for (var input : emailInput) {
    //        input.sendKeys("iluzjajanapawlaiv@gmail.com");
    //    }
//
//
//
    //    var continueToPasswordButton = driver.findElement(By.xpath("/html/body/div[1]/div[1]/div[2]/c-wiz/div/div[3]/div/div[1]/div/div/button"));
    //    //continueToPasswordButton.click();
//
//
//
    //    return new Translation("");
    //}

}
