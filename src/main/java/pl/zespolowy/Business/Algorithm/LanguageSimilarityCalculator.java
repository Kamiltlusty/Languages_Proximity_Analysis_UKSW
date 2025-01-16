package pl.zespolowy.Business.Algorithm;

import lombok.Getter;
import lombok.Setter;
import org.apache.commons.text.similarity.LevenshteinDistance;
import pl.zespolowy.language.Language;
import pl.zespolowy.Words.Word;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Getter
@Setter
public final class LanguageSimilarityCalculator {
    private LevenshteinDistance levenshteinDistance = new LevenshteinDistance();
    private WordSetsRegrouper wordSetsRegrouper;
    private Map<String, Map<String, LanguageProximityResult>> proximityOfTwoLangsByTopic = new HashMap<>();

    public LanguageSimilarityCalculator(WordSetsRegrouper wordSetsRegrouper) {
        this.wordSetsRegrouper = wordSetsRegrouper;
    }

    /**
     * This method loops through regrouped map {
     * regrouped map = (result we receive is Map with key Topic and Set of Maps holding pairs (key = Language, val = Word)
     * from class WordSetsRegrouper }
     * 1. creates Language Proximity Result classes for each two Languages in every topic and puts it to Map with
     * combined codes of these two Languages as key and LanguageProximityResult class as value
     * 2. counts levenshteinDistance for each pair of word in two Languages and adds the result to Language Proximity Result class
     * 3. searches for adequate LanguageProximityResult and when finds, adds counted value to found class
     */
    public void countProximityAndFillLPRClasses() {
        var mapOfTopics = wordSetsRegrouper.getRegruopedMap();
        Map<String, LanguageProximityResult> proximityOfTwoLangsMap;

        for (var map : mapOfTopics.entrySet()) {
            proximityOfTwoLangsMap = createLPRClasses(wordSetsRegrouper.getWordSetsTranslation().getLangList());
            for (var set : map.getValue()) {
                count(set, proximityOfTwoLangsMap);
            }
            proximityOfTwoLangsByTopic.put(map.getKey(), proximityOfTwoLangsMap);
        }
    }

    private void count(Map<Language, Word> set, Map<String, LanguageProximityResult> proximityBetweenTwoLanguagesMap) {
        for (Map.Entry<Language, Word> outerEntry : set.entrySet()) {
            Language outerKey = outerEntry.getKey();
            Word outerValue = outerEntry.getValue();

            var innerIterator = set.entrySet().iterator();
            // skips elems to outer Entry
            while (innerIterator.hasNext() && !innerIterator.next().equals(outerEntry)) {
            }

            while (innerIterator.hasNext()) {
                var innerEntry = innerIterator.next();
                Language innerKey = innerEntry.getKey();
                Word innerValue = innerEntry.getValue();
                // proximity calculation
                Integer countedDistance = levenshteinDistance.apply(outerValue.getText(), innerValue.getText());
                putResultToLPR(outerKey, innerKey, proximityBetweenTwoLanguagesMap, countedDistance, outerValue, innerValue);
            }
        }
    }

    private void putResultToLPR(Language outerKey, Language innerKey, Map<String, LanguageProximityResult> proximityOfTwoLangsMap, Integer countedDistance, Word word1, Word word2) {
        String langsAbbr = outerKey.getCode() + innerKey.getCode();
        String langsAbbrReversed = innerKey.getCode() + outerKey.getCode();

        // check whether language abbreviations newly created are equal with abbreviations in earlier created LPR classes and add result
        if (proximityOfTwoLangsMap.containsKey(langsAbbr)) {
            proximityOfTwoLangsMap.get(langsAbbr).updateLPR(countedDistance, 1, word1, word2);
        } else if (proximityOfTwoLangsMap.containsKey(langsAbbrReversed)) {
            proximityOfTwoLangsMap.get(langsAbbrReversed).updateLPR(countedDistance, 1, word1, word2);
        } else {
            System.out.println("LanguageProximityError: Abbreviation not found, line: 68");
        }
    }

    private Map<String, LanguageProximityResult> createLPRClasses(List<Language> languages) {
        Map<String, LanguageProximityResult> map = new HashMap<>();
        for (int i = 0; i < languages.size(); i++) {
            for (int j = i + 1; j < languages.size(); j++) {
                map.put(languages.get(i).getCode() + languages.get(j).getCode(), new LanguageProximityResult(languages.get(i), languages.get(j)));
            }
        }
        return map;
    }
}

