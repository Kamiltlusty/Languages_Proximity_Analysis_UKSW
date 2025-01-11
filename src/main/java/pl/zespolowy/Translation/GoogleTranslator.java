//package pl.zespolowy.Translation;
//
//import org.openqa.selenium.By;
//import pl.zespolowy.Words.WordSet;
//
//import java.util.Objects;
//
//public class GoogleTranslator extends Translator {
//
//
//    public GoogleTranslator() {}
//
//    private String format(WordSet wordSet) {
//        StringBuilder sb = new StringBuilder();
//        //sb.append("This is list of ");
//        //sb.append(wordSet.getTitle());
//        //sb.append(": ");
//        for (int i = 0; i < wordSet.getWords().size(); i++) {
//            sb.append("fruit number ");
//            sb.append(i);
//            sb.append(": ");
//            sb.append(wordSet.getWords().get(i).getText());
//            if (i != wordSet.getWords().size() - 1) {
//                sb.append(", ");
//            }
//        }
//        return sb.toString();
//    }
//
//    private void googleConsent() {
//        if (Objects.requireNonNull(driver.getCurrentUrl()).contains("consent.google.")) {
//            var button = driver.findElement(By.xpath("//*[@id=\"yDmH0d\"]/c-wiz/div/div/div/div[2]/div[1]/div[3]/div[1]/div[1]/form[1]/div/div/button"));
//            button.click();
//        }
//    }
//
//    @Override
//    public GoogleTranslation translate(WordSet wordSet, String sourceLanguage, String targetLanguage) {
//        String content = format(wordSet);
//
//        String baseUrl = "https://translate.google.com/?sl=" + sourceLanguage + "&tl=" + targetLanguage + "&op=translate";
//        driver.get(baseUrl);
//
//        googleConsent();
//
//        var inputBox = driver.findElement(By.xpath("//*[@id=\"yDmH0d\"]/c-wiz/div/div[2]/c-wiz/div[2]/c-wiz/div[1]/div[2]/div[2]/div/c-wiz/span/span/div/textarea"));
//        inputBox.sendKeys(content);
//
//        while (true) {
//            try {
//                Thread.sleep(10); // Sleep for 10 milliseconds
//                var outputBox = driver.findElement(By.xpath("//*[@id=\"yDmH0d\"]/c-wiz/div/div[2]/c-wiz/div[2]/c-wiz/div[1]/div[2]/div[2]/c-wiz/div/div[6]/div/div[1]/span[1]"));
//                return new GoogleTranslation(outputBox.getText(), content);
//            } catch (Exception _) {}
//        }
//    }
//
//
//
//
//
//}
