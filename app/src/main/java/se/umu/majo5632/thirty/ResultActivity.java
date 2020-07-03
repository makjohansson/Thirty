package se.umu.majo5632.thirty;
/**Show the results in a table, is called when the Player of the Dice game Thirty
 * played 10 rounds.
 * @author Marcus Johansson
 */

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import java.util.HashMap;

public class ResultActivity extends AppCompatActivity {

    //Tag for Logcat filter
    private static final String TAG = "ResultActivity";

    //KEYS used by savedInstanceSate
    public static final String EXTRA_SCORE = "se.umu.majo5632.thirty.score";
    public static final String EXTRA_MAP = "se.umu.majo5632.thirty.map";

    private TextView mViewLow, mViewFour, mViewFive, mViewSix, mViewSeven, mViewEight, mViewNine,
                    mViewTen, mViewEleven, mViewTwelve, mViewFinal;
    private int mScore = 0;
    private HashMap<String, Integer> mScoreMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        mScoreMap = (HashMap<String, Integer>) getIntent().getSerializableExtra(EXTRA_MAP);
        calcFinalScore();
        mViewLow = (TextView)findViewById(R.id.low_score);
        mViewLow.setText(""+mScoreMap.get("Low"));

        mViewFour = (TextView)findViewById(R.id.four_score);
        mViewFour.setText(""+mScoreMap.get("4"));

        mViewFive = (TextView)findViewById(R.id.five_score);
        mViewFive.setText(""+mScoreMap.get("5"));

        mViewSix = (TextView)findViewById(R.id.six_score);
        mViewSix.setText(""+mScoreMap.get("6"));

        mViewSeven = (TextView)findViewById(R.id.seven_score);
        mViewSeven.setText(""+mScoreMap.get("7"));

        mViewEight = (TextView)findViewById(R.id.eight_score);
        mViewEight.setText(""+mScoreMap.get("8"));

        mViewNine = (TextView)findViewById(R.id.nine_score);
        mViewNine.setText(""+mScoreMap.get("9"));

        mViewTen = (TextView)findViewById(R.id.ten_score);
        mViewTen.setText(""+mScoreMap.get("10"));

        mViewEleven = (TextView)findViewById(R.id.eleven_score);
        mViewEleven.setText(""+mScoreMap.get("11"));

        mViewTwelve = (TextView)findViewById(R.id.twelve_score);
        mViewTwelve.setText(""+mScoreMap.get("12"));

        mViewFinal = (TextView)findViewById(R.id.final_score);
        mViewFinal.setText(""+mScore);
    }

    /**
     * sum the final score for a game of Thirty
     */
    private void calcFinalScore() {
        for(int i : mScoreMap.values()) mScore = mScore + i;
    }
}