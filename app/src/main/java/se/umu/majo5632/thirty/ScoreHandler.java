package se.umu.majo5632.thirty;

import java.util.ArrayList;

/** Handle scoring for the Dice game Thirty
 * @author Marcus Johansson
 * @version 1.0
 */
public class ScoreHandler {

    private int scoringChoice;
    private ArrayList<Integer> diceValues;
    private int score;

    public ScoreHandler(int scoringChoice, ArrayList<Integer> diceValues) {
        this.scoringChoice = scoringChoice;
        this.diceValues = diceValues;
        calcScore();
    }

    /**
     * Get the score from ScoreHandler
     * @return score as an int
     */
    public int getScoring() {
        return score;
    }

    /**
     * Sum the values of six dices
     * @return the sum as an int
     */
    private int sumDices() {
        int sum = 0;
        for (int i : diceValues) sum += i;
        return sum;
    }

    /**
     * Calculate score according to the rules of the dice game Thirty.
     */
    private void calcScore() {

        while(!diceValues.isEmpty()) {

            if(sumDices() < scoringChoice)
                break;
            //Low score
            if (scoringChoice == 3) {
                for (int i = 0; i < diceValues.size(); i++) {
                    if (diceValues.get(i) <= 3) {
                        score += diceValues.get(i);
                    }
                }
                break;
            } else {
                //Check one dice
                for (int i = 0; i < diceValues.size(); i++) {
                    if (diceValues.get(i) == scoringChoice) {
                        score += scoringChoice;
                        diceValues.remove(i);
                        i--;
                    }
                }

                if (diceValues.isEmpty() || diceValues.size() < 2)
                   break;

                //Check two dices
                for (int i = 0; i < diceValues.size(); i++) {
                    for (int j = i + 1; j < diceValues.size(); j++) {
                        if (i < 0)
                            i = 0;
                        int sum = diceValues.get(i) + diceValues.get(j);
                        if (sum == scoringChoice) {
                            score += scoringChoice;
                            diceValues.remove(i);
                            diceValues.remove(j - 1);
                            i--;
                            j = i + 1;
                        }
                    }
                }

                if (diceValues.isEmpty() || diceValues.size() < 3)
                   break;

                //Check three dices
                for (int i = 0; i < diceValues.size(); i++) {
                    for (int j = i + 1; j < diceValues.size(); j++) {
                        if (i < 0)
                            i = 0;
                        int k = j + 1;
                        if (k >= diceValues.size())
                            break;
                        int sum = diceValues.get(i) + diceValues.get(j) + diceValues.get(k);
                        if (sum == scoringChoice) {
                            score += scoringChoice;
                            diceValues.remove(i);
                            diceValues.remove(j - 1);
                            diceValues.remove(k - 2);
                            i--;
                            j--;
                        }
                    }
                }

                if (diceValues.isEmpty() || diceValues.size() < 4)
                    break;

                //Check four dices
                for (int i = 0; i < diceValues.size(); i++) {
                    for (int j = i + 1; j < diceValues.size(); j++) {
                        int k = j + 1;
                        int l = j + 2;
                        if (l >= diceValues.size())
                            break;
                        int sum = diceValues.get(i) + diceValues.get(j) + diceValues.get(k) + diceValues.get(l);
                        if (sum == scoringChoice) {
                            score += scoringChoice;
                            diceValues.remove(i);
                            diceValues.remove(j - 1);
                            diceValues.remove(k - 2);
                            diceValues.remove(l - 3);
                            j = j--;
                        }
                    }
                }

                if (diceValues.isEmpty() || diceValues.size() < 5)
                    break;

                //Check five dices
                for (int i = 0; i < diceValues.size(); i++) {
                    for (int j = i + 1; j < diceValues.size(); j++) {
                        int k = j + 1;
                        int l = j + 2;
                        int m = j + 3;
                        if (m >= diceValues.size())
                            break;
                        int sum = diceValues.get(i) + diceValues.get(j) + diceValues.get(k)
                                + diceValues.get(l) + diceValues.get(m);
                        if (sum == scoringChoice) {
                            score += scoringChoice;
                            diceValues.remove(i);
                            diceValues.remove(j - 1);
                            diceValues.remove(k - 2);
                            diceValues.remove(l - 3);
                            diceValues.remove(m - 4);
                        }
                    }
                }

                if (diceValues.isEmpty() || diceValues.size() < 6)
                    break;

                //Check six dices
                for (int i = 0; i < diceValues.size(); i++) {
                    for (int j = i + 1; j < diceValues.size(); j++) {
                        int k = j + 1;
                        int l = j + 2;
                        int m = j + 3;
                        int n = j + 4;
                        if (n >= diceValues.size())
                            break;
                        int sum = diceValues.get(i) + diceValues.get(j) + diceValues.get(k)
                                + diceValues.get(l) + diceValues.get(m) + diceValues.get(n);
                        if (sum == scoringChoice) {
                            score += scoringChoice;
                            diceValues.remove(i);
                            diceValues.remove(j - 1);
                            diceValues.remove(k - 2);
                            diceValues.remove(l - 3);
                            diceValues.remove(m - 4);
                            diceValues.remove(n - 5);
                        }
                    }
                }
            }
        }
    }
}


