package pl.zespolowy.language;

import com.fasterxml.jackson.databind.ObjectMapper;
import pl.zespolowy.Language;

import java.io.IOException;
import java.util.List;

public class LanguageSet {
    private List<pl.zespolowy.Language> languages;

    public LanguageSet(String jsonString) {
        Deserialize(jsonString);
    }

    private void Deserialize(String jsonString) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            languages = objectMapper.readValue(jsonString, objectMapper.getTypeFactory().constructCollectionType(List.class, pl.zespolowy.Language.class));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<pl.zespolowy.Language> getLanguages() {
        return languages;
    }

    public void addLanguage(pl.zespolowy.Language language) {
        languages.add(language);
    }

    public pl.zespolowy.Language get(int index) {
        return this.languages.get(index);
    }

    public int Size() {
        return this.languages.size();
    }
    // test
    public void print() {
        System.out.println("---- Languages ----");
        for (Language l : languages) {
            System.out.println(l.getName());
        }
        System.out.println();
    }
}
