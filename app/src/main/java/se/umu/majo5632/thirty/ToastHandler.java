package se.umu.majo5632.thirty;
/**
 * Handles Toast displays for the dice game Thirty
 * @author Marcus Johansson
 */

import android.content.Context;
import android.content.res.Configuration;
import android.view.Gravity;
import android.widget.Toast;

public class ToastHandler {
    private Toast mToast;
    private Context parent;
    private  String textToDisplay;

    /**
     * Handles how a toast will be presented in the dice game Thirty
     * @param parent Current context
     * @param mToast Toast object to use
     */
    public ToastHandler(Context parent, Toast mToast) {
        this.parent = parent;
        this.mToast = mToast;
    }

    /**
     * Set text to be displayed by the Toast
     * @param textToDisplay
     */
    public void setText(String textToDisplay) {
        this.textToDisplay = textToDisplay;
    }

    /**
     * Display a SHORT Toast
     */
    public void display() {
        this.textToDisplay = textToDisplay;
        if(mToast != null)
            mToast.cancel();
        mToast = mToast.makeText(parent, textToDisplay, Toast.LENGTH_SHORT);
        setGravity();
        mToast.show();
    }

    /**
     * Set position for mToast on screen. A bit below center if Portrait orientation and at the
     * bottom if landscape orientation
     */
    private void setGravity() {
        if(checkOrientation())
            mToast.setGravity(Gravity.BOTTOM, mToast.getXOffset() / 2, mToast.getYOffset() * 5);
        else
            mToast.setGravity(Gravity.BOTTOM, mToast.getXOffset() / 2, mToast.getYOffset() / 2);

    }

    /**
     * Check what orientation device is using
     * @return true if Portrait orientation
     */
    private boolean checkOrientation() {
        Integer mode = parent.getResources().getConfiguration().orientation;
        if(mode.equals(Configuration.ORIENTATION_PORTRAIT))
            return true;
        else
            return false;
    }
}
