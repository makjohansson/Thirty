package se.umu.majo5632.thirty;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.sql.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    //Tag for Logcat filter
    private static final String TAG = "MainActivity";
    //Keys used by savedInstanceState
    private static final String KEY_ROUND = "Round";
    private static final String KEY_ROLLS = "Rolls";
    private static final String KEY_DICES = "Dices";
    private static final String KEY_MAP = "Map";

    private int mRollsCounter = 0;
    private int mRoundCounter = 1;
    private int mScore = 0;
    private final int NUMBER_OF_DICES = 6;
    private Button mThrow;
    private ArrayList<Dice> mDicesList;
    private ImageButton mDiceOne, mDiceTwo, mDiceThree, mDiceFour, mDiceFive, mDiceSix;
    private boolean ismDiceOneClicked, ismDiceTwoClicked, ismDiceThreeClicked, ismDiceFourClicked,
            ismDiceFiveClicked, ismDiceSixClicked;
    private Spinner spinner;
    private TextView mWhatRound;
    private ScoreHandler mScorerHandler;
    private ArrayAdapter<String> adapter;

    private String[] mScoresOptions = new String[]
            {"0", "Low", "4", "5", "6", "7", "8", "9", "10", "11", "12"};
    private List<String> scoreList = new ArrayList<>(Arrays.asList(mScoresOptions));
    private HashMap<String, Integer> mScoreMap;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        //Throw button, press and roll the dices
        mThrow = (Button)findViewById(R.id.throw_button);
        mThrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TextView at the top displaying what round the player is on.
                mWhatRound = (TextView)findViewById(R.id.what_round);

                mWhatRound.setText("Round " + mRoundCounter);
                if(mRoundCounter == 3) {
                    mRoundCounter = 0;
                    startResultActivity();
                } else if(mRollsCounter < 3 ) {
                    rollDices();
                    Log.d(TAG, "Throw number " + mRollsCounter);
                } else {
                    int message = 0;
                    Log.d(TAG, "Round " + ++mRoundCounter);

                    message = R.string.pick_a_score;
                    Toast.makeText(MainActivity.this, message, Toast.LENGTH_SHORT).show();
                }
            }
        });

        onCreateDices();

        //Identify image buttons
        mDiceOne = (ImageButton)findViewById(R.id.dice_one);
        mDiceTwo = (ImageButton)findViewById(R.id.dice_two);
        mDiceThree = (ImageButton)findViewById(R.id.dice_three);
        mDiceFour = (ImageButton)findViewById(R.id.dice_four);
        mDiceFive = (ImageButton)findViewById(R.id.dice_five);
        mDiceSix = (ImageButton)findViewById(R.id.dice_six);

        if(savedInstanceState != null) {
            mRollsCounter = savedInstanceState.getInt(KEY_ROLLS, 0);
            mRoundCounter = savedInstanceState.getInt(KEY_ROUND, 0);
            mScoreMap = (HashMap<String, Integer>) savedInstanceState.getSerializable(KEY_MAP);
            ArrayList<Dice> theDices = savedInstanceState.getParcelableArrayList(KEY_DICES);
            for(int i = 0; i < NUMBER_OF_DICES; i++) {
                setDiceImage(theDices.get(i).getValue(), i);
            }
        } else {
            rollDices();
            mScoreMap = new HashMap<>();
            setMap();
        }

        //ImageButton's representing the dices
        ismDiceOneClicked = false;
        mDiceOne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ismDiceOneClicked = true;

            }
        });

        mDiceTwo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ismDiceTwoClicked = true;
            }
        });

        mDiceThree.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ismDiceThreeClicked = true;
            }
        });

        mDiceFour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    ismDiceFourClicked = true;
            }
        });

        mDiceFive.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ismDiceFiveClicked = true;
            }
        });

        mDiceSix.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ismDiceSixClicked = true;
            }
        });

        //Spinner to pick what type of scoring to use
        spinner = (Spinner)findViewById(R.id.spinner);
        spinner.setOnItemSelectedListener(this);
        adapter = new ArrayAdapter<>
                (this, R.layout.support_simple_spinner_dropdown_item, scoreList);
        spinner.setAdapter(adapter);





    }

    /**
     * To save Dices if the user flips the screen or exit the app
     * @param outState
     */
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putParcelableArrayList(KEY_DICES, mDicesList);
        outState.putInt(KEY_ROUND, mRoundCounter);
        outState.putInt(KEY_ROLLS, mRollsCounter);
        outState.putSerializable(KEY_MAP, mScoreMap);
        super.onSaveInstanceState(outState);
    }

    /**
     *
     * @param diceValue value of a dice
     * @param pos position of an ImageButton(0 -> 5)
     */
    private void setDiceImage(int diceValue, int pos) {
        int newDice = 0;
        String diceToPick = "";
        ImageButton[] dices = new ImageButton[]{mDiceOne, mDiceTwo, mDiceThree,
                mDiceFour, mDiceFive, mDiceSix};
        diceToPick = "dice" + diceValue;
        newDice = dices[pos].getResources().getIdentifier(diceToPick,
                "drawable",
                "se.umu.majo5632.thirty");
        dices[pos].setImageResource(newDice);

    }

    /**
     * Roll the Dices, add value to ImageButtons and replace non clicked Dices in mDiceList
     */
    private void rollDices() {
        mRollsCounter++;
        int diceValue = 0;

        boolean[] clickCheck = new boolean[]{ismDiceOneClicked, ismDiceTwoClicked,
                ismDiceThreeClicked, ismDiceFourClicked, ismDiceFiveClicked, ismDiceSixClicked};

        for(int i = 0; i < NUMBER_OF_DICES; i++) {
            if(!clickCheck[i]) {
                Dice die = new Dice();
                diceValue = die.getValue();
                setDiceImage(diceValue, i);
                mDicesList.set(i, die);
            }
        }
    }

    /**
     * Fill mDicesList with 6 new dices.
     */
    private void onCreateDices() {
        mDicesList = new ArrayList<>();
        Dice die;
        for(int i = 0; i < NUMBER_OF_DICES; i++) {
            die = new Dice();
            mDicesList.add(die);
        }
    }

    /**
     * Set the HashMap mScoreMap with value 0 for all scoring options
     */
    private void setMap() {
        for(int i = 1; i < mScoresOptions.length; i++) {
            mScoreMap.put(mScoresOptions[i], 0);
        }
    }

    /**
     * Return value of dices as an int array
     * @return int array
     */
    private int[] getDiceValues() {
        int[] arr = new int[NUMBER_OF_DICES];
        for(int i = 0; i < NUMBER_OF_DICES; i++) {
            arr[i] = mDicesList.get(i).getValue();
        }
        return arr;
    }

    private void startResultActivity() {
        Intent i = new Intent(MainActivity.this, ResultActivity.class);
        i.putExtra(ResultActivity.EXTRA_SCORE, mScore);
        i.putExtra(ResultActivity.EXTRA_MAP, mScoreMap);
        startActivityForResult(i, 0);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String item = parent.getItemAtPosition(position).toString();
        int scoringChoice = 0;
        if(item == "Low")
            scoringChoice = 3;
        else
            scoringChoice = Integer.parseInt(item);
        scoreList.remove(position);
        adapter.notifyDataSetChanged();
        mScorerHandler = new ScoreHandler(scoringChoice, getDiceValues());
        mScore = mScorerHandler.getScoring();
        mScoreMap.put(item, mScore);
        mRollsCounter = 0;
        Log.i(TAG, "Spinner " + scoringChoice + " selected");
        Toast.makeText(parent.getContext(), "Score " + mScore, Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}