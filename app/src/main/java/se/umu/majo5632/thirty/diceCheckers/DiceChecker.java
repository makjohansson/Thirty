package se.umu.majo5632.thirty.diceCheckers;
/**
 * Abstract class used to check al combinations of dices for the dice game Thirty
 * @author Marcus Johansson
 */

import java.util.ArrayList;

public abstract class DiceChecker {
    protected Integer scoringChoice;
    protected ArrayList<Integer> diceValues;
    protected int score;

    

    public DiceChecker(ArrayList<Integer> diceValues, int score,int scoringChoice) {
        this.scoringChoice = scoringChoice;
        this.diceValues = diceValues;
        this.score = score;
        calcScore();
    }

    /**
     * Get the dices that's in ArrayList diceValues
     * @return current dice values
     */
    public ArrayList<Integer> getDiceValues() {
        return diceValues;
    };

    /**
     * Calculate the score depending on scoreChoice and dice values
     */
    abstract void calcScore();

    /**
     * Score for a specific number of dices
     * @return score
     */
    public int getScore() {
        return  score;
    }

    /**
     * Check if a sum is equal to scoringChoice
     * @param sum to check if equal with scoringChoice
     * @return true if equal
     */
    protected boolean isEqual(int sum) {
        if (scoringChoice.equals(sum))
            return true;
        else
            return false;
    }

    
}