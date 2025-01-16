package pl.zespolowy.Business.Algorithm;

import lombok.Getter;
import lombok.Setter;
import pl.zespolowy.language.Language;
import pl.zespolowy.Words.Word;
import pl.zespolowy.Words.WordSet;

import java.util.*;
import java.util.stream.Collectors;

@Getter
@Setter
public class WordSetsRegrouper {
    private final WordSetsTranslation wordSetsTranslation;
    Map<String, Set<Map<Language, Word>>> regruopedMap = new HashMap<>();

    public WordSetsRegrouper(WordSetsTranslation wordSetsTranslation) {
        this.wordSetsTranslation = wordSetsTranslation;
        regroup();
    }

    /**
     * This method regroups Map with Topic as key and Map of pairs (key = Language, val = WordSet) as value
     * as a result we receive Map with key Topic and Set of Maps holding pairs (key = Language, val = Word)
     * What happened here is that we divided WordSets to singular Words as in calculation method we will need
     * to take one word in two languages and compare them
     */
    public void regroup() {
        regruopedMap = wordSetsTranslation.getTopicLangWSmap().entrySet().stream()
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        v -> mapToSet(v.getValue())
                ));
    }

    public Set<Map<Language, Word>> mapToSet(Map<Language, WordSet> wsInDiffLangs) {
        Set<Map<Language, Word>> mapSet = new LinkedHashSet<>();
        // find wordSet size
        int size = wsInDiffLangs.values().stream()
                .findFirst()
                .map(w -> w.getWords().size())
                .orElse(-1);

        // go through each wordSet and take i-th Word
        for (int i = 0; i < size; i++) {
            Map<Language, Word> languageWordMap = getLanguageWordMap(wsInDiffLangs, i);
            mapSet.add(languageWordMap);
        }
        return mapSet;
    }
    

    private Map<Language, Word> getLanguageWordMap(Map<Language, WordSet> wsInDiffLangs, int i) {
        return wsInDiffLangs.entrySet().stream()
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        e -> e.getValue().getWords().get(i)
                ));
    }
}
