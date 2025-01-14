package pl.zespolowy.Business.Algorithm;


import lombok.Getter;
import pl.zespolowy.Language;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Getter
public class WordsProximityNormalizer {
    private final LanguageSimilarityCalculator languageSimilarityCalculator;
    private final Map<String, Map<LanguageProximityResult, Double>> countedProximityBetweenWords = new HashMap<>();
    private final Map<String, Map<Language, Map<Language, Double>>> resultByTopic = new HashMap<>();
    private Map<Language, Map<Language, Double>> finalResult = new HashMap<>();

    private final WordSetsTranslation wordSetsTranslation;

    public WordsProximityNormalizer(LanguageSimilarityCalculator lSC, WordSetsTranslation wsT) {
        this.languageSimilarityCalculator = lSC;
        this.wordSetsTranslation = wsT;
        removeOutliersAndNormalize();
        prepareResultByTopic();
        prepareResult();
    }

    // test
    public void print() {
        for (var majorLangs : finalResult.values()) {
            System.out.println(majorLangs.keySet());
            for (var minorLangs : majorLangs.entrySet()) {
                System.out.println(minorLangs.getKey() + " " + minorLangs.getValue());
            }
        }
    }

    /**
     * This method takes map from language Similarity Calculator which contains
     * (key = Topic, val = Map <key = combined languages codes, val = LPR class>
     * in next step it removes outliers from each class instance so we get rid of incorrect data
     * when data is correct it executes normalization on data to values in range [0.0, 1.0]
     * data is put to new map which now has LanguageProximityResult as key in inner Map and calculated proximity from
     * all words as a value
     */
    public void removeOutliersAndNormalize() {
        var mapOfTopics = languageSimilarityCalculator.getProximityOfTwoLangsByTopic();
        for (var map : mapOfTopics.entrySet()) {
            var topicKey = map.getKey();
            var innerMap = map.getValue();
            // removing outliers
            innerMap.values().forEach(LanguageProximityResult::removeOutliers);
            var mapOfMaps = normalizationBetweenWords(innerMap);
            // filling new map
            countedProximityBetweenWords.put(topicKey, mapOfMaps);
        }
    }
    /**
     * This method prepares final result which is map
     * of type key = topic, value = Map <key = Language, val = Map <key = Language, val =AverageProximity>>
     * for example {"School supplies":{"Spanish":{"Portuguese":0.096,"French":0.181,"German":0.832,"Italian":0.235,"Swedish":0.774,
     * "Dutch":0.697,"English":0.584,"Polish":0.638,"Turkish":0.801}, "Portuguese":{"Spanish":0.096 ... and so on }}
     * 1. It prepares Language Result Map
     * 2. Fills this map by putting both sides of relation (languages1, languages2) to new maps
     */
    public void prepareResultByTopic() {
        List<Language> languageList = wordSetsTranslation.getLangList();

        for (var topic : countedProximityBetweenWords.entrySet()) {
            String key = topic.getKey();
            Map<LanguageProximityResult, Double> mapsOfLPR = topic.getValue();

            Map<Language, Map<Language, Double>> languageResultMap = languageList.stream().collect(Collectors.toMap(language -> language, language -> new HashMap<>()));

            for (var entry : mapsOfLPR.entrySet()) {
                LanguageProximityResult key1 = entry.getKey();
                Double newVal = entry.getValue();

                Map<Language, Double> language1Map = languageResultMap.computeIfAbsent(key1.getLanguage2(), k -> new HashMap<>());
                language1Map.merge(key1.getLanguage1(), newVal, Double::sum);

                Map<Language, Double> language2Map = languageResultMap.computeIfAbsent(key1.getLanguage1(), k -> new HashMap<>());
                language2Map.merge(key1.getLanguage2(), newVal, Double::sum);
            }
            resultByTopic.put(key, languageResultMap);
        }
    }
    public void prepareResult() {
        // divisor for calculating average of sum of languages
        int topicsNum = resultByTopic.size();
        // creating new map to keep finalResult with deep copy
        this.finalResult = resultByTopic.values().stream().findFirst().get()
                .entrySet()
                .stream()
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        entry -> new HashMap<>(entry.getValue()) // Głęboka kopia wewnętrznej mapy
                ));
        // reset values to 0 so they can contain sum
        for (var majorLangs : finalResult.values()) {
            for (var minorLangs : majorLangs.entrySet()) {
                minorLangs.setValue(0.0);
            }
        }
        for (var topic : resultByTopic.entrySet()) {
            for (var majorLangs : topic.getValue().entrySet()) {
                Language majorLang = majorLangs.getKey();
                for (var minorLangs : majorLangs.getValue().entrySet()) {
                    Language minorLang = minorLangs.getKey();
                    Double val = minorLangs.getValue();

                    Map<Language, Double> languageDoubleMap = finalResult.get(majorLang);
                    languageDoubleMap.put(minorLang, languageDoubleMap.get(minorLang) + val);
                }
            }
        }
        for (var majorLangs : finalResult.values()) {
            for (var minorLangs : majorLangs.entrySet()) {
                minorLangs.setValue(minorLangs.getValue() / topicsNum);
            }
        }
    }

    // ta metoda potrzebuje przynajmniej 3 jezykow w pliku languages do poprawnego dzialania
    public Map<LanguageProximityResult, Double> normalizationBetweenWords(Map<String, LanguageProximityResult> resultMap) {
        // count average for all Language Proximity Result classes
        var innerMap = resultMap.values().stream()
                .collect(Collectors.toMap(
                        lpr -> lpr,
                        lpr -> {
                            Double val1 = Double.valueOf(lpr.getCountedProximity());
                            Double val2 = Double.valueOf(lpr.getNumberOfWordsToNormalization());
                            return val1 / val2;
                        }
                ));
        // normalizacja ze wzoru (xi - xmin)/(xmax - xmin)
        Double xmax = findMaxValue(innerMap.values());
        Double xmin = findMinValue(innerMap.values());
        // normalize
        innerMap = innerMap.entrySet().stream()
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        v -> normalize(v.getValue(), xmax, xmin)
                ));


        return innerMap;
    }

    /**
     * this method gets minimum value of averaged results as well as maximum and i-th value and executes normalization
     * @param value
     * @param xmax
     * @param xmin
     * @return
     */
    private Double normalize(Double value, Double xmax, Double xmin) {
        return (value - xmin) / (xmax - xmin);
    }

    /**
     * finds maximum value from map of averaged results
     * @param results
     * @return
     */
    private Double findMaxValue(Collection<Double> results) {
        Double maxVal = Double.valueOf(-1);
        for (var val : results) {
            if (maxVal < val) maxVal = val;
        }
        return maxVal;
    }

    /**
     * finds minimum value from map of averaged results
     * @param results
     * @return
     */
    private Double findMinValue(Collection<Double> results) {
        Double minVal = Double.valueOf(1000);
        for (var val : results) {
            if (minVal > val) minVal = val;
        }
        return minVal;
    }
}
