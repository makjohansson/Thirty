package se.umu.majo5632.thirty.diceCheckers;
/**
 * Check dice combinations for two dices and calculate score
 * @author Marcus Johansson
 */
import java.util.ArrayList;

public class TwoDices extends DiceChecker {

    /**
     * Check dice combinations for two dices and calculate score
     * @param diceValues
     * @param score
     * @param scoringChoice
     */
    public TwoDices(ArrayList<Integer> diceValues, int score, int scoringChoice) {
        super(diceValues, score, scoringChoice);
    }

    @Override
    void calcScore() {
        int sum = 0;
        for (int i = 0; i < diceValues.size(); i++) {
            for (int j = i + 1; j < diceValues.size(); j++) {
                if (i < 0)
                    i = 0;
                sum = diceValues.get(i) + diceValues.get(j);
                if (isEqual(sum)) {
                    score += scoringChoice;
                    diceValues.remove(i);
                    diceValues.remove(j - 1);
                    i--;
                }
            }
        }

    }
}