package pl.zespolowy.Translation;

import org.openqa.selenium.By;
import pl.zespolowy.Words.WordSet;

public class DeepLTranslator extends Translator {

    public DeepLTranslator() {

    }

    private String format(WordSet wordSet) {
        StringBuilder sb = new StringBuilder();
        sb.append("This is list of ");
        sb.append(wordSet.getTitle());
        sb.append(": ");
        for (int i = 0; i < wordSet.getWords().size(); i++) {
            sb.append(wordSet.getWords().get(i).getText());
            if (i != wordSet.getWords().size() - 1) {
                sb.append(", ");
            }
        }
        sb.append(".");
        return sb.toString();
    }

    public Translation translate(WordSet wordSet, String sourceLanguage, String targetLanguage) {
        String content = format(wordSet);



        String baseUrl = "https://www.deepl.com/" + sourceLanguage + "/translator#" + sourceLanguage + "/" + targetLanguage + "/" + content;
        driver.get(baseUrl);

        System.out.println(content);

        //System.out.println("get");
        ////var inputs = driver.findElements(By.name("source"));
        //var input = driver.findElement(By.name("source"));
        //System.out.println("find");
        //input.click();
        //System.out.println("click");
        //input.sendKeys(content);
        //System.out.println("enter");


        while (true) {
            try {
                Thread.sleep(10); // Sleep for 10 milliseconds
                var output = driver.findElement(By.name("target"));
                if (output.getText().length() > 50) {

                    String[] source = content.split(": ")[1].replace(".", "").toLowerCase().split(", ");
                    String[] target = output.getText().split(": ")[1].replace(".", "").toLowerCase().split(", ");
                    String desc = output.getText().split(": ")[0];
                    System.out.println("DESC: \"" + desc + "\"");
                    return new Translation(source, target);
                }
            } catch (Exception _) {

            }
        }
    }

}