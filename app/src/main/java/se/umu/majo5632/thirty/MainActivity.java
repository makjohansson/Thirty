package se.umu.majo5632.thirty;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    private int mRollsCounter = 0;
    private int mRoundCounter = 0;
    private final int NUMBER_OF_DICES = 6;
    private Dice mDice;
    private Button mThrow;
    private ArrayList<Dice> mDicesList;
    private ImageButton mDiceOne, mDiceTwo, mDiceThree, mDiceFour, mDiceFive, mDiceSix;
    private boolean ismDiceOneClicked, ismDiceTwoClicked, ismDiceThreeClicked, ismDiceFourClicked,
            ismDiceFiveClicked, ismDiceSixClicked;
    private Spinner mScoringChoices;
    private TextView mWhatRound;

    //private int[] mDefaultDiceValues = new int[]{1, 2, 3, 4, 5, 6};
    private String[] mScoresOptions = new String[]
            {"Low", "4", "5", "6", "7", "8", "9", "10", "11", "12"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //TextView at the top displaying what round the player is on.
        mWhatRound = (TextView)findViewById(R.id.what_round);

        //Throw button, press and roll the dices
        mThrow = (Button)findViewById(R.id.throw_button);
        mThrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int message = 0;
                if(mRollsCounter < 3 ) {
                    rollDices();
                    Log.d(TAG, "clicked less than three times and in if");
                } else {
                    Log.d(TAG, "clicked three times and in else");
                    mWhatRound.setText("Round " + ++mRoundCounter);
                    message = R.string.pick_a_score;
                    Toast.makeText(MainActivity.this, message, Toast.LENGTH_SHORT).show();

                }
            }
        });

        mDiceOne = (ImageButton)findViewById(R.id.dice_one);
        mDiceTwo = (ImageButton)findViewById(R.id.dice_two);
        mDiceThree = (ImageButton)findViewById(R.id.dice_three);
        mDiceFour = (ImageButton)findViewById(R.id.dice_four);
        mDiceFive = (ImageButton)findViewById(R.id.dice_five);
        mDiceSix = (ImageButton)findViewById(R.id.dice_six);

        if(savedInstanceState != null) {
            ArrayList<Dice> theDices = savedInstanceState.getParcelableArrayList("Dices");
            for(int i = 0; i < NUMBER_OF_DICES; i++) {
                Log.i(TAG, "value of dice " + theDices.get(i).getValue());
                setDiceImage(theDices.get(i).getValue(), i);
            }
        } else {
            mDiceOne = (ImageButton)findViewById(R.id.dice_one);
            mDiceTwo = (ImageButton)findViewById(R.id.dice_two);
            mDiceThree = (ImageButton)findViewById(R.id.dice_three);
            mDiceFour = (ImageButton)findViewById(R.id.dice_four);
            mDiceFive = (ImageButton)findViewById(R.id.dice_five);
            mDiceSix = (ImageButton)findViewById(R.id.dice_six);
            rollDices();
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
        mScoringChoices = (Spinner)findViewById(R.id.spinner);
        ArrayAdapter<String> adapter = new ArrayAdapter<>
                (this, R.layout.support_simple_spinner_dropdown_item, mScoresOptions);
        mScoringChoices.setAdapter(adapter);

    }

    /**
     * To save Dices if the user flips the screen or exit the app
     * @param outState
     */
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putParcelableArrayList("Dices", mDicesList);
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
     * Roll the Dice, add value to ImageButton and add Dice to ArrayList
     * used by onSaveInstanceState
     */
    private void rollDices() {
        mDicesList = new ArrayList<Dice>();
        mRollsCounter++;
        mDice = new Dice();
        int diceValue = 0;

        boolean[] clickCheck = new boolean[]{ismDiceOneClicked, ismDiceTwoClicked,
                ismDiceThreeClicked, ismDiceFourClicked, ismDiceFiveClicked, ismDiceSixClicked};

        for(int i = 0; i < NUMBER_OF_DICES; i++) {
            if(!clickCheck[i]) {
                Dice die = new Dice();
                diceValue = die.getValue();
                setDiceImage(diceValue, i);
                mDicesList.add(die);
            }
        }
    }
}