package se.umu.majo5632.thirty;

import java.util.ArrayList;

public class ScoreHandler {

    private int scoringChoice;
    private ArrayList<Integer> diceValues;
    private int score;

    public ScoreHandler(int scoringChoice, ArrayList<Integer> diceValues) {
        this.scoringChoice = scoringChoice;
        this.diceValues = diceValues;
        calcScore();
    }

    public int getScoring() {
        return score;
    }

    private void calcScore() {

        //Low score
        if (scoringChoice == 3) {
            for (int i = 0; i < diceValues.size(); i++) {
                if (diceValues.get(i) <= 3) {
                    score += diceValues.get(i);
                }
            }
        } else {
            //Check one dice
            for (int i = 0; i < diceValues.size(); i++) {
                if (diceValues.get(i) == scoringChoice) {
                    score += scoringChoice;
                    diceValues.remove(i);
                    i--;
                }
            }

            //Check two dices
            for(int i = 0; i < diceValues.size(); i ++) {
                for(int j = i+1; j < diceValues.size(); j++) {
                    int sum = diceValues.get(i) + diceValues.get(j);
                    if(sum == scoringChoice) {
                        score += scoringChoice;
                        diceValues.remove(i);
                        diceValues.remove(j-1);
                        j = j - 2;
                    }
                }
            }

            //Check three dices
            for(int i = 0; i < diceValues.size(); i ++) {
                for(int j = i+1; j < diceValues.size(); j++) {
                    int k = j+1;
                    if(k >= diceValues.size())
                        break;
                    int sum = diceValues.get(i) + diceValues.get(j) + diceValues.get(k);
                    if(sum == scoringChoice) {
                        score += scoringChoice;
                        diceValues.remove(i);
                        diceValues.remove(j-1);
                        diceValues.remove(k-2);
                        j = j-2;
                    }
                }
            }

            //Check four dices
            for(int i = 0; i < diceValues.size(); i ++) {
                for(int j = i+1; j < diceValues.size(); j++) {
                    int k = j+1;
                    int l = j+2;
                    if(l >= diceValues.size())
                        break;
                    int sum = diceValues.get(i) + diceValues.get(j) + diceValues.get(k) + diceValues.get(l);
                    if(sum == scoringChoice) {
                        score += scoringChoice;
                        diceValues.remove(i);
                        diceValues.remove(j-1);
                        diceValues.remove(k-2);
                        diceValues.remove(l-3);
                        j = j-2;
                    }
                }
            }

            //Check five dices
            for(int i = 0; i < diceValues.size(); i ++) {
                for(int j = i+1; j < diceValues.size(); j++) {
                    int k = j+1;
                    int l = j+2;
                    int m = j+3;
                    if(m >= diceValues.size())
                        break;
                    int sum = diceValues.get(i) + diceValues.get(j) + diceValues.get(k) + diceValues.get(l) + diceValues.get(m);
                    if(sum == scoringChoice) {
                        score += scoringChoice;
                        diceValues.remove(i);
                        diceValues.remove(j-1);
                        diceValues.remove(k-2);
                        diceValues.remove(l-3);
                        diceValues.remove(m-4);
                        j = j-2;
                    }
                }
            }

            //Check six dices
            for(int i = 0; i < diceValues.size(); i ++) {
                for(int j = i+1; j < diceValues.size(); j++) {
                    int k = j+1;
                    int l = j+2;
                    int m = j+3;
                    int n = j+4;
                    if(m >= diceValues.size())
                        break;
                    int sum = diceValues.get(i) + diceValues.get(j) + diceValues.get(k) + diceValues.get(l) + diceValues.get(m) +diceValues.get(n);
                    if(sum == scoringChoice) {
                        score += scoringChoice;
                        diceValues.remove(i);
                        diceValues.remove(j-1);
                        diceValues.remove(k-2);
                        diceValues.remove(l-3);
                        diceValues.remove(m-4);
                        diceValues.remove(n-5);
                        j = j-2;
                    }
                }
            }

        }
    }

}


