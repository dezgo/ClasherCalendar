/*
 * http://stackoverflow.com/questions/4284224/android-hold-button-to-repeat-action
 * 
 * Usage:

Button button = new Button(context);
button.setOnTouchListener(new RepeatListener(400, 100, new OnClickListener() {
  @Override
  public void onClick(View view) {
    // the code to execute repeatedly
  }
}));
 */

package com.derekgillett.clashercalendar;

import android.annotation.SuppressLint;
import android.graphics.Rect;
import android.os.Handler;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;

/**
 * A class, that can be used as a TouchListener on any view (e.g. a Button).
 * It cyclically runs a clickListener, emulating keyboard-like behaviour. First
 * click is fired immediately, next after initialInterval, and subsequent after
 * normalInterval.
 *
 * <p>Interval is scheduled after the onClick completes, so it has to run fast.
 * If it runs slow, it does not generate skipped onClicks.
 */
public class RepeatListener implements OnTouchListener {
	private static final String TAG = "RepeatListener";
	private Rect rect; // Variable rect to hold the bounds of the view
	
    private Handler handler = new Handler();

    //private final int stepDownSize = 5;
    //private final int minInterval = 10;    
    //private int initialInterval = 300;
    private final OnClickListener clickListener;
    
    //private int normalInterval = 100;

    private Runnable handlerRunnable = new Runnable() {
        @Override
        public void run() {
            clickListener.onClick(downView);
            //updateInterval();
            handler.post(this);
            //handler.postDelayed(this, normalInterval);
        }
    };

    private View downView;

    /**
     * @param initialInterval The interval after first click event
     * @param normalInterval The interval after second and subsequent click 
     *       events
     * @param clickListener The OnClickListener, that will be called
     *       periodically
     */
    public RepeatListener(OnClickListener poClickListener) {
        if (poClickListener == null)
            throw new IllegalArgumentException("null runnable");
        clickListener = poClickListener;
    }
/*
    private void updateInterval() {
        if (normalInterval > minInterval) {
        	normalInterval = normalInterval - stepDownSize;
        	if (normalInterval < minInterval) normalInterval = minInterval;
        	Log.v(TAG, "updateInterval =" + normalInterval);
        }
    }
*/
    @SuppressLint("ClickableViewAccessibility")
	public boolean onTouch(View view, MotionEvent motionEvent) {
        switch (motionEvent.getAction()) {
        case MotionEvent.ACTION_DOWN:
            handler.removeCallbacks(handlerRunnable);
            //handler.postDelayed(handlerRunnable, initialInterval);
            handler.post(handlerRunnable);
            downView = view;
            clickListener.onClick(view);
            rect = new Rect(view.getLeft(), view.getTop(), view.getRight(),
                    view.getBottom());
            return true;
        case MotionEvent.ACTION_UP:
        case MotionEvent.ACTION_CANCEL:
            handler.removeCallbacks(handlerRunnable);
            downView = null;
            return true;
        case MotionEvent.ACTION_MOVE:
            if (!rect.contains(view.getLeft() + (int) motionEvent.getX(),
                    view.getTop() + (int) motionEvent.getY())) {
                // User moved outside bounds
                handler.removeCallbacks(handlerRunnable);
                downView = null;
                if (Utils.DEBUG) Log.d(TAG, "ACTION_MOVE...OUTSIDE");
            }
            break;
        }

        return false;
    }

}