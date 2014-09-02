package com.derekgillett.clashercalendar;
//http://stackoverflow.com/questions/24524196/android-hold-button-to-send-multiple-clicks
import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;

public class AutoRepeatButton extends Button {
    private long initialRepeatDelay = 500;
    private long repeatIntervalInMilliseconds = 35;

    // speedup
    private long repeatIntervalCurrent = repeatIntervalInMilliseconds;
    private long repeatIntervalStep = 2;
    private long repeatIntervalMin = 10;

    private Runnable repeatClickWhileButtonHeldRunnable = new Runnable() {
        @Override
        public void run() {
            // Perform the present repetition of the click action provided by the user
            // in setOnClickListener().
            performClick();

            // Schedule the next repetitions of the click action, 
            // faster and faster until it reaches repeaterIntervalMin
            if (repeatIntervalCurrent > repeatIntervalMin)
                repeatIntervalCurrent = repeatIntervalCurrent - repeatIntervalStep;

            postDelayed(repeatClickWhileButtonHeldRunnable, repeatIntervalCurrent);
        }
    };

    private void commonConstructorCode() {
        this.setOnTouchListener(new OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                int action = event.getAction();
                if (action == MotionEvent.ACTION_DOWN) {
                    // Just to be sure that we removed all callbacks,
                    // which should have occurred in the ACTION_UP
                    removeCallbacks(repeatClickWhileButtonHeldRunnable);

                    // Perform the default click action.
                    performClick();

                    // Schedule the start of repetitions after a one half second delay.
                    repeatIntervalCurrent = repeatIntervalInMilliseconds;
                    postDelayed(repeatClickWhileButtonHeldRunnable, initialRepeatDelay);
                } else if (action == MotionEvent.ACTION_MOVE) {
                	// do nothing
                } else if (action == MotionEvent.ACTION_UP || action == MotionEvent.ACTION_CANCEL) {
                    // Cancel any repetition in progress.
                    removeCallbacks(repeatClickWhileButtonHeldRunnable);
                } else
                	if (Utils.DEBUG) Log.d("AutoRepeatButton", "unhandled action: " + action);

                // Returning true here prevents performClick() from getting called
                // in the usual manner, which would be redundant, given that we are
                // already calling it above.
                return true;
            }
        });
    }

    public AutoRepeatButton(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        commonConstructorCode();
    }

     public AutoRepeatButton(Context context, AttributeSet attrs) {
     super(context, attrs);
     commonConstructorCode();
     }

    public AutoRepeatButton(Context context) {
        super(context);
        commonConstructorCode();
    }
}