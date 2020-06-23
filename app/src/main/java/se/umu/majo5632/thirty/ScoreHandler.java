package se.umu.majo5632.thirty;



public class ScoreHandler {

    private int scoringChoice;
    private int[] diceValues;
    private int score;

    public ScoreHandler(int scoringChoice, int[] diceValues) {
        this.scoringChoice = scoringChoice;
        this.diceValues = diceValues;
        calcScore();
    }

    public int getScoring() {
        return score;
    }

    private void calcScore() {
        for(int i = 0; i < diceValues.length; i++) {
            if(scoringChoice == diceValues[i])
                score += scoringChoice;
        }
    }

}


