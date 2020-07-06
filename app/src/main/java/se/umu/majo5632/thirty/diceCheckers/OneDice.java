package se.umu.majo5632.thirty.diceCheckers;
/**
 * Check dice combinations for one dice and calculate score
 * @author Marcus Johansson
 */
import java.util.ArrayList;

public class OneDice extends DiceChecker {


    public OneDice(ArrayList<Integer> diceValues, int score, int scoringChoice) {
        super(diceValues, score, scoringChoice);
    }

    @Override
    void calcScore() {
        int value = 0;
        for (int i = 0; i < diceValues.size(); i++) {
            value = diceValues.get(i);
            if (isEqual(value)) {
                score += scoringChoice;
                diceValues.remove(i);
                i--;
            }

        }
    }
}