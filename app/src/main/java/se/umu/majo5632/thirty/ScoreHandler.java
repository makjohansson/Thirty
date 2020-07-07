package se.umu.majo5632.thirty;
/**
 * Handles score logic for the dice game Thirty
 * @author Marcus Johansson
 */

import java.util.ArrayList;
import se.umu.majo5632.thirty.diceCheckers.*;


public class ScoreHandler {

    private ArrayList<Integer> diceValues;
    private final Integer LOW = 3;
    private int score;
    private Integer numOfDicesToCheck = 1;
    private final Integer scoringChoice;
    private DiceChecker diceChecker;

    /**
     * Handels the score logic for the dice game Thirty
     * @param diceValues ArrayList of dice values
     * @param scoringChoice current scoringChoice
     */
    public ScoreHandler(ArrayList<Integer> diceValues, int scoringChoice) {
        this.diceValues = diceValues;
        this.scoringChoice = scoringChoice;
        calcScore();
    }

    /**
     * Calculate score
     */
    private void calcScore() {
        if (LOW.equals(scoringChoice))
            low();
        else {
            checkDices();
        }
    }

    /**
     * Check all combinations for the six dices and set score according to the rules
     * of Thirty.
     */
    private void checkDices() {
        diceChecker = new OneDice(diceValues, score, scoringChoice);
        score = diceChecker.getScore();
        diceValues = diceChecker.getDiceValues();
        numOfDicesToCheck++;
        while (!diceValues.isEmpty() && numOfDicesToCheck <= diceValues.size()) {
            switch (numOfDicesToCheck) {
                case 2:
                    diceChecker = new TwoDices(diceValues, score, scoringChoice);
                    score = diceChecker.getScore();
                    diceValues = diceChecker.getDiceValues();
                    break;
                case 3:
                    diceChecker = new ThreeDices(diceValues, score, scoringChoice);
                    score = diceChecker.getScore();
                    diceValues = diceChecker.getDiceValues();
                    break;
                case 4:
                    diceChecker = new FourDices(diceValues, score, scoringChoice);
                    score = diceChecker.getScore();
                    diceValues = diceChecker.getDiceValues();
                    break;
                case 5:
                    diceChecker = new FiveDices(diceValues, score, scoringChoice);
                    score = diceChecker.getScore();
                    diceValues = diceChecker.getDiceValues();
                    break;
                case 6:
                    diceChecker = new SixDices(diceValues, score, scoringChoice);
                    score = diceChecker.getScore();
                    diceValues = diceChecker.getDiceValues();
                    break;
            }
            numOfDicesToCheck++;
        }
    }

    /**
     * The score based on dice values and scoring choice
     * @return score for round
     */
    public int getScore() {
        return score;
    }

    /**
     * Calculate score for scoringChoice == LOW
     */
    private void low() {
        for (final int value : diceValues) {
            if (value <= LOW)
                score += value;
        }
    }
}
