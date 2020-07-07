package se.umu.majo5632.thirty.diceCheckers;
/**
 * Check dice combinations for three dices and calculate score
 * @author Marcus Johansson
 */
import java.util.ArrayList;

public class ThreeDices extends DiceChecker {

    /**
     * Check dice combinations for three dices and calculate score
     * @param diceValues
     * @param score
     * @param scoringChoice
     */
    public ThreeDices(ArrayList<Integer> diceValues, int score, int scoringChoice) {
        super(diceValues, score, scoringChoice);
    }

    @Override
    void calcScore() {
        int sum = 0;
        for (int i = 0; i < diceValues.size(); i++) {
            for (int j = i + 1; j < diceValues.size(); j++) {
                for (int k = j + 1; k < diceValues.size(); k++) {
                    sum = diceValues.get(i) + diceValues.get(j) + diceValues.get(k);
                    if (isEqual(sum)) {
                        score += scoringChoice;
                        diceValues.remove(i);
                        diceValues.remove(j - 1);
                        diceValues.remove(k - 2);
                        j--;
                    }
                }
            }
        }
    }
}