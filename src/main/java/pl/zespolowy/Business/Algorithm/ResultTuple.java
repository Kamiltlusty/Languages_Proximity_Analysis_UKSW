package pl.zespolowy.Business.Algorithm;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import pl.zespolowy.Words.Word;

@Getter
@Setter
@AllArgsConstructor
public final class ResultTuple {
    private final Integer proximity;
    private final Word word1;
    private final Word word2;
    private Double z_score;
}
