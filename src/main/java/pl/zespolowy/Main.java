package pl.zespolowy;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import pl.zespolowy.Business.Algorithm.*;
import pl.zespolowy.Controllers.MainSceneController;
import pl.zespolowy.Translation.DeepLTranslator;
import pl.zespolowy.Translation.Translator;

import java.io.IOException;

public class Main extends Application {
    @Override
    public void start(Stage stage) throws IOException, NoSuchFieldException {

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/main-scene.fxml"));
        Parent root = fxmlLoader.load();
        MainSceneController controller = fxmlLoader.getController();

        Translator translator = new DeepLTranslator();
        controller.setTranslator(translator);

        Scene scene = new Scene(root, 800, 600);

        stage.setTitle("Windows");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) throws IOException {
        WordSetsTranslation wst = new WordSetsTranslation();
        WordSetsRegrouper wordSetsRegroup = new WordSetsRegrouper(wst);
        LanguageSimilarityCalculator languageProximity = new LanguageSimilarityCalculator(wordSetsRegroup);
        languageProximity.countProximityAndFillLPRClasses();
        WordsProximityNormalizer wordsProximityNormalizer = new WordsProximityNormalizer(languageProximity, wst);

        ProximityResultJSONExporter proximityResultJSONExporter = new ProximityResultJSONExporter(wordsProximityNormalizer);
        proximityResultJSONExporter.createJsonByTopics();
        System.out.println("Final Result");
        wordsProximityNormalizer.print();


        launch();
    }
}