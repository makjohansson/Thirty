package se.umu.majo5632.thirty;
/** Represents a Dice.
 * @author Marcus Johansson
 */

import android.os.Parcel;
import android.os.Parcelable;

public class Dice implements Parcelable {

    private int value;

    public Dice() {
        value = roll();
    }

    protected Dice(Parcel in) {
        value = in.readInt();
    }

    public static final Creator<Dice> CREATOR = new Creator<Dice>() {
        @Override
        public Dice createFromParcel(Parcel in) {
            return new Dice(in);
        }

        @Override
        public Dice[] newArray(int size) {
            return new Dice[size];
        }
    };

    /**
     * Get Dice value
     * @return Dice value as an int
     */
    public int getValue() {
        return value;
    }

    /**
     * Random roll of dice
     * @return int
     */
    private int roll() {
        return (int)(Math.random()*6+1);
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(value);
    }
}
