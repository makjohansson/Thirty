package se.umu.majo5632.thirty;
/** Main activity for the Dice game Thirty
 * Acting as the game controller, depending on the classes Dice and ScoreHandler.
 * ResultActivity is the only child activity
 * @author Marcus Johansson
 */

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    //Tag for Logcat filter
    private static final String TAG = "MainActivity";
    //Keys used by savedInstanceState
    private static final String KEY_ROUND = "Round";
    private static final String KEY_CLICKED = "Clicked";
    private static final String KEY_ROLLS = "Rolls";
    private static final String KEY_DICES = "Dices";
    private static final String KEY_MAP = "Map";
    private static final String KEY_SCORE_LIST = "Score list";

    private final int ROUNDS = 10;
    private final int NUMBER_OF_DICES = 6;
    private int spinnerCheck = 0;
    private Integer mRollsCounter = 0;
    private int mRoundCounter = 1;
    private int mScore = 0;
    private Button mThrow;
    private ArrayList<Dice> mDicesList;
    private ImageButton mDiceOne, mDiceTwo, mDiceThree, mDiceFour, mDiceFive, mDiceSix;
    private boolean[] mIsClicked;
    private Spinner spinner;
    private TextView mWhatRound;
    private ScoreHandler mScorerHandler;
    private ArrayAdapter<String> adapter;
    private String[] mScoresOptions = new String[]
            {"Pick Score", "Low", "4", "5", "6", "7", "8", "9", "10", "11", "12", "---"};
    private ArrayList<String> mScoreList = new ArrayList<>(Arrays.asList(mScoresOptions));
    private HashMap<String, Integer> mScoreMap;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Displays what round the player is on at the top of the screen
        mWhatRound = (TextView)findViewById(R.id.what_round);
        setIsClickedArray();
        //Throw button, press and roll the dices
        mThrow = (Button)findViewById(R.id.throw_button);
        mThrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mIsClicked[6] = true;
                mWhatRound.setText("Round " + mRoundCounter);
                if(mRoundCounter > ROUNDS) {
                    reset();
                    startResultActivity();
                } else if(mRollsCounter < 3 && !isAllDicesPicked() ) {
                    mRollsCounter++;
                    rollDices();
                } else {
                    int message = R.string.pick_a_score;
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

        //Data needed to be saved between screen flips and activity changes
        if(savedInstanceState != null) {
            mRollsCounter = savedInstanceState.getInt(KEY_ROLLS, 0);
            mRoundCounter = savedInstanceState.getInt(KEY_ROUND, 0);
            mScoreMap = (HashMap<String, Integer>) savedInstanceState.getSerializable(KEY_MAP);
            mIsClicked = savedInstanceState.getBooleanArray(KEY_CLICKED);
            mScoreList = savedInstanceState.getStringArrayList(KEY_SCORE_LIST);
            ArrayList<Dice> theDices = savedInstanceState.getParcelableArrayList(KEY_DICES);
            mWhatRound.setText("Round " + mRoundCounter);
            for(int i = 0; i < NUMBER_OF_DICES; i++) {
                setDiceImage(theDices.get(i).getValue(), i);
            }
        } else {
            rollDices();
            mScoreMap = new HashMap<>();
            setMap();
        }
        //TextView at the top displaying what round the player is on.
        if(mIsClicked[6])
            mWhatRound.setText("Round " + mRoundCounter);

        //ImageButton's representing the dices
        mDiceOne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mIsClicked[6]) {
                    if (mIsClicked[0]) {
                        mIsClicked[0] = false;
                        mDiceOne.setBackgroundResource(R.drawable.defualt_background);
                    } else {
                        mIsClicked[0] = true;
                        mDiceOne.setBackgroundResource(R.drawable.dice_background);
                    }
                }

            }
        });

        mDiceTwo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mIsClicked[6]) {
                    if (mIsClicked[1]) {
                        mIsClicked[1] = false;
                        mDiceTwo.setBackgroundResource(R.drawable.defualt_background);
                    } else {
                        mIsClicked[1] = true;
                        mDiceTwo.setBackgroundResource(R.drawable.dice_background);
                    }
                }
            }
        });

        mDiceThree.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mIsClicked[6] ) {
                    if (mIsClicked[2]) {
                        mIsClicked[2] = false;
                        mDiceThree.setBackgroundResource(R.drawable.defualt_background);
                    } else {
                        mIsClicked[2] = true;
                        mDiceThree.setBackgroundResource(R.drawable.dice_background);
                    }
                }
            }
        });

        mDiceFour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mIsClicked[6]) {
                    if (mIsClicked[3]) {
                        mIsClicked[3] = false;
                        mDiceFour.setBackgroundResource(R.drawable.defualt_background);
                    } else {
                        mIsClicked[3] = true;
                        mDiceFour.setBackgroundResource(R.drawable.dice_background);
                    }
                }
            }
        });

        mDiceFive.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mIsClicked[6]) {
                    if (mIsClicked[4]) {
                        mIsClicked[4] = false;
                        mDiceFive.setBackgroundResource(R.drawable.defualt_background);
                    } else {
                        mIsClicked[4] = true;
                        mDiceFive.setBackgroundResource(R.drawable.dice_background);
                    }
                }
            }
        });

        mDiceSix.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mIsClicked[6]) {
                    if (mIsClicked[5]) {
                        mIsClicked[5] = false;
                        mDiceSix.setBackgroundResource(R.drawable.defualt_background);
                    } else {
                        mIsClicked[5] = true;
                        mDiceSix.setBackgroundResource(R.drawable.dice_background);
                    }
                }
            }
        });

        setupSpinner();

    }

    /**
     * Setup spinner with the scoring choices from mScoreList.
     */
    private void setupSpinner() {
        //Spinner to pick what type of scoring to use
        spinner = (Spinner)findViewById(R.id.spinner);
        spinner.setOnItemSelectedListener(this);

        adapter = new ArrayAdapter<>
                (this, R.layout.support_simple_spinner_dropdown_item, mScoreList);
        spinner.setAdapter(adapter);
    }

    /**
     * Set the mIsClicked boolean array with the default value false.
     */
    private void setIsClickedArray() {
        mIsClicked = new boolean[7];
        for(boolean i : mIsClicked) i = false;
    }

    /**
     * Reset the game when player returns from viewing the result and allow
     * the player to play again.
     */
    private void reset() {
        mRollsCounter = 0;
        mRoundCounter = 1;
        mScoreList = new ArrayList<>(Arrays.asList(mScoresOptions));
        mWhatRound.setText(R.string.round);
        setupSpinner();
        setIsClickedArray();
        rollDices();
    }

    /**
     * Check if all dices are picked, to allow the player to pick a score
     * @return true if all dices are picked, else false
     */
    private boolean isAllDicesPicked() {
        Integer counter = 0;
        for(int i = 0; i < mIsClicked.length - 1; i++) {
            if(mIsClicked[i] == true)
                counter++;
        }
        if(counter.equals(6))
            return true;
        else
            return false;
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putParcelableArrayList(KEY_DICES, mDicesList);
        outState.putInt(KEY_ROUND, mRoundCounter);
        outState.putInt(KEY_ROLLS, mRollsCounter);
        outState.putBooleanArray(KEY_CLICKED, mIsClicked);
        outState.putSerializable(KEY_MAP, mScoreMap);
        outState.putStringArrayList(KEY_SCORE_LIST, mScoreList);
        super.onSaveInstanceState(outState);
    }

    /**
     * Set dice image to corresponding dice value and set correct background basted on if
     * player clicked the dice or not.
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
        if(mIsClicked[pos]) {
            dices[pos].setBackgroundResource(R.drawable.dice_background);
        } else {
            dices[pos].setBackgroundResource(R.drawable.defualt_background);
        }

    }


    /**
     * Roll the Dices, add value to ImageButtons and replace non clicked Dices in mDiceList
     */
    private void rollDices() {
        int diceValue = 0;
        for(int i = 0; i < NUMBER_OF_DICES; i++) {
            if(!mIsClicked[i]) {
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
     * Return value of dices as an ArrayList<Integer>
     * @return int array
     */
    private ArrayList<Integer> getDiceValues() {
        ArrayList<Integer> arr = new ArrayList<Integer>();
        for(int i = 0; i < NUMBER_OF_DICES; i++) arr.add(mDicesList.get(i).getValue());
        return arr;
    }

    /**
     * Starts the child activity: ResultActivity
     */
    private void startResultActivity() {
        Intent i = new Intent(MainActivity.this, ResultActivity.class);
        i.putExtra(ResultActivity.EXTRA_SCORE, mScore);
        i.putExtra(ResultActivity.EXTRA_MAP, mScoreMap);
        startActivityForResult(i, 0);
    }

    @Override
    /**
     * Handles the logic connected to the spinner. Remove scores picked, reset mRollsCounter
     * increasing mScore using the class ScoreHandler and keep player from picking a score without
     * doing 3 rolls or picking all dices.
     */
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        if(mRollsCounter.equals(3) || isAllDicesPicked()) {
            int pos = adapter.getPosition("Pick Score");
            spinner.setSelection(pos);
            if (++spinnerCheck > 1) {
                String item = parent.getItemAtPosition(position).toString();
                int scoringChoice = 0;
                if (item.equals("---") || item.equals("Pick Score"))
                    return;
                if (item.equals("Low")) {
                    scoringChoice = 3;
                } else
                    scoringChoice = Integer.parseInt(item);
                mScoreList.remove(position);
                adapter.notifyDataSetChanged();
                mScorerHandler = new ScoreHandler(scoringChoice, getDiceValues());
                mScore = mScorerHandler.getScoring();
                mScoreMap.put(item, mScore);
                mRollsCounter = 0;
                mRoundCounter++;
                setIsClickedArray();
                Toast.makeText(parent.getContext(), "Score " + mScore, Toast.LENGTH_SHORT).show();
            }
        } else {
            int pos = adapter.getPosition("Pick Score");
            spinner.setSelection(pos);
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }



}