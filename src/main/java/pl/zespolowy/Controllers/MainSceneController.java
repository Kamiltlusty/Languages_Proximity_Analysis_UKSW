package pl.zespolowy.Controllers;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import lombok.Getter;
import pl.zespolowy.language.Language;
import pl.zespolowy.language.LanguageSet;
import pl.zespolowy.Translation.Translation;
import pl.zespolowy.Translation.Translator;
import pl.zespolowy.Words.WordSet;


import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Getter
public class MainSceneController {
    @FXML
    private AnchorPane mainSceneAnchorPane;

    @FXML
    private TextArea textArea;

    @FXML
    private VBox subjectBox;

    @FXML
    private ListView subjectList;

    private Translator translator;
    private Map<String, WordSet> wordSets;
    private LanguageSet languageSet;
    private boolean useCache;

    public List<Language> languageList;
    public List<WordSet> wordSetList;

    public void initialize() {

        String rootPath = System.getProperty("user.dir");
        System.out.println(rootPath);

        String languagesPath = rootPath + "\\languages.json";
        initLanguages(languagesPath);
        languageSet.print();

        String wordSetPath = rootPath + "\\wordsets\\";
        initWordSets(wordSetPath);
        for (String key : wordSets.keySet()) {
            wordSets.get(key).print();
        }

        useCache = false;
    }

    public void setTranslator(Translator translator) {
        this.translator = translator;
    }

    public void initLanguages(String path) {
        try {
            String content = Files.readString(Paths.get(path));
            languageSet = new LanguageSet(content);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void initWordSets(String path) {
        wordSets = new HashMap<>();



        File dir = new File(path);
        if (dir.exists() && dir.isDirectory()) {
            String[] fileNames = dir.list();

            if (fileNames != null) {
                for (String fileName : fileNames) {
                    try {
                        String title = fileName.split(".json")[0];
                        String content = Files.readString(Paths.get(path + fileName));

                        WordSet wordSet = new WordSet(title, content, false);

                        wordSets.put(title, wordSet);
                        //wordSetList.add(wordSet);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    System.out.println(fileName);
                }

            } else {
                System.out.println("The current directory is empty or an error occurred.");
            }
        } else {
            System.out.println("The current directory does not exist or is not a directory.");
        }
    }

    @FXML
    public void handleTranslate() {
        int format = 0;

        for (WordSet wordSet : wordSets.values()) {
            //WordSet wordSet = wordSets.get("Vegetables");
            //Translation t = translator.translate(wordSet, "en", "pl");
            //t.alert();

            String folderName = "Cache\\Translation\\Polish\\";
            String fileName = "Transportation methods.json";

            if (useCache) {
                Translation t = new Translation();
                boolean success = t.readJson(folderName, fileName);
                if (success) {
                    System.out.println(t.translationsText());
                }
                else {
                    System.out.println("Failed to read cache: \"" + folderName + fileName + "\"");
                }
            }
            else {
                System.out.println(wordSet.getTitle());
                Translation t = translator.translate(wordSet, "en", "es");
                System.out.println(t.targetText());
                //t.writeJson("Cache\\Translation\\Polish\\", "Fruits.json");

                //Translation t2 = translator.translate(wordSet, "en", "de");
                //t2.writeJson("Cache\\Translation\\German\\", "Fruits.json");
            }
        }




        for (String key : wordSets.keySet()) {
            //WordSet wordSet = wordSets.get(key);
            //Translation t = translator.translate(wordSet, "en", "pl");
            //t.alert();


            //Alert alert = new Alert(Alert.AlertType.NONE);
            //alert.setTitle(key);
            //alert.setContentText(t.multiLine());
            //alert.getDialogPane().getButtonTypes().add(ButtonType.OK);
            //alert.show();

            //System.out.println("----- LIST -------- ");
            //for (String str : t.toList()) {
            //    System.out.println("'" + str + "'");
            //}
            //System.out.println();
        }
    }
}
