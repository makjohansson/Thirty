package se.umu.majo5632.thirty.diceCheckers;
/**
 * Check dice combinations for six dices and calculate score
 * @author Marcus Johansson
 */
import java.util.ArrayList;

public class SixDices extends DiceChecker {

    /**
     * Check dice combinations for six dices and calculate score
     * @param diceValues
     * @param score
     * @param scoringChoice
     */
    public SixDices(ArrayList<Integer> diceValues, int score, int scoringChoice) {
        super(diceValues, score, scoringChoice);
    }

    @Override
    void calcScore() {
        if(isEqual(sumDices()))
            score += scoringChoice;
    }

    /**
     * Add the six dices values together
     * @return the sum
     */
    private int sumDices() {
        int sum = 0;
        for (int value : diceValues)
            sum += value;
        return sum;
    }
    
}