package pl.zespolowy.Business.Algorithm;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import pl.zespolowy.Language;
import pl.zespolowy.Words.Word;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Getter
@Setter
@ToString(of = {"nameAbbr", "language1", "language2", "countedProximity", "numberOfWordsToNormalization"})
public class LanguageProximityResult {
    private final String nameAbbr;
    private final Language language1;
    private final Language language2;
    private Integer countedProximity;
    private Integer numberOfWordsToNormalization;
    private List<ResultTuple> resultTupleList = new ArrayList<>();
    private static final Double CUTOFF = 3.0;

    public LanguageProximityResult(Language language1, Language language2) {
        this.language1 = language1;
        this.language2 = language2;
        this.nameAbbr = setNameAbbr(language1, language2);
        countedProximity = 0;
        numberOfWordsToNormalization = 0;
    }

    /**
     * This method sets abbreviations for two given languages
     * for example nameAbbreviation = "enfr"
     *
     * @param language1
     * @param language2
     * @return String
     */
    public String setNameAbbr(Language language1, Language language2) {
        return language1.getCode() + language2.getCode();
    }

    /**
     * This method:
     * 1. counts z-score out of counted distance between two languages for one word
     * 2. removes values that are apart of |CUTOFF|, || - absolute value
     */
    public void removeOutliers() {
        // calculate average
        Double average = Double.valueOf(countedProximity) / Double.valueOf(numberOfWordsToNormalization);
        // calculate standard deviation
        Double sum = Double.valueOf(0);
        for (ResultTuple resultTuple : resultTupleList) {
            Integer value = resultTuple.getProximity();
            sum += Math.pow((Double.valueOf(value) - average), 2);
        }
        Double sd = Math.sqrt(sum / numberOfWordsToNormalization);
        // calculate z-score for each value
        for (ResultTuple resultTuple : resultTupleList) {
            resultTuple.setZ_score((resultTuple.getProximity() - average) / sd);
        }
        // remove outliers and rewrite list
        Iterator<ResultTuple> iterator = resultTupleList.iterator();
        while (iterator.hasNext()) {
            ResultTuple resultTuple = iterator.next();
            Double z_score = resultTuple.getZ_score();
            if (z_score > CUTOFF || z_score < -1 * CUTOFF) {
                iterator.remove();
            }
        }
        // set new countedProximity for new range of values
        Integer sumOfProximities = 0;
        for (ResultTuple resultTuple : resultTupleList) {
            sumOfProximities += resultTuple.getProximity();
        }
        countedProximity = sumOfProximities;
        // set new numberOfWordsToNormalization for new range of values
        numberOfWordsToNormalization = resultTupleList.size();
    }

    /**
     * This method sets list of ResultTuples which contains
     * calculated proximity, word in two langs as Word1 and Word2 and z-score
     * and adds calculated proximity so it sums up to value of all words in this class
     * as well as adds +1 to number counter
     * number of occurrences to counter
     * @param proximity
     * @param anotherNumber
     */
    public void updateLPR(Integer proximity, Integer anotherNumber, Word word1, Word word2) {
        resultTupleList.add(new ResultTuple(proximity, word1, word2, 0.0));
        countedProximity += proximity;
        numberOfWordsToNormalization += anotherNumber;
    }
}
