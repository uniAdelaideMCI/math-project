package au.edu.adelaide.mci.kidnumeracy.component;

import java.util.HashSet;
import java.util.Set;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.GestureDetector.OnGestureListener;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.ImageView;
import au.edu.adelaide.mci.kidnumeracy.R;

/**
 * @author Yun
 *
 */
@SuppressLint("ClickableViewAccessibility")
public class Ruler extends ImageView implements OnTouchListener,
		OnGestureListener {

	//private GestureDetector mGestureDetector;
	private int verticalMinDistance = 60;
	private int minVelocity = 0;
	private int phaseNo = 1;

	// remmeber the last x coordinate when touch event is triggerd, because
	// touch event will be triggered multiple times
	//private float lastTouchedX = -1.0f;

	public int getPhaseNo() {
		return phaseNo;
	}

	public void setPhaseNo(int phaseNo) {
		if (phaseNo != this.phaseNo) {
			this.phaseNo = phaseNo;
		}

	}

	private Set<RulerNumberChosenListener> numberChosenListeners = new HashSet<RulerNumberChosenListener>();

	// The width for each number on the ruler which is 10 long
	private static final int NUM_COL_WIDTH_10 = 227;

	// The width for each number on the ruler which is 20 long
	private static final int NUM_COL_WIDTH_20 = 118;

	private static final int PHASE1_RES_IDS[] = { R.drawable.ruler_10_0,
			R.drawable.ruler_10_1, R.drawable.ruler_10_2,
			R.drawable.ruler_10_3, R.drawable.ruler_10_4,
			R.drawable.ruler_10_5, R.drawable.ruler_10_6,
			R.drawable.ruler_10_7, R.drawable.ruler_10_8,
			R.drawable.ruler_10_9, R.drawable.ruler_10_10 };
	private static final int PHASE2_RES_IDS[] = { R.drawable.ruler_20_0,
			R.drawable.ruler_20_1, R.drawable.ruler_20_2,
			R.drawable.ruler_20_3, R.drawable.ruler_20_4,
			R.drawable.ruler_20_5, R.drawable.ruler_20_6,
			R.drawable.ruler_20_7, R.drawable.ruler_20_8,
			R.drawable.ruler_20_9, R.drawable.ruler_20_10,
			R.drawable.ruler_20_11, R.drawable.ruler_20_12,
			R.drawable.ruler_20_13, R.drawable.ruler_20_14,
			R.drawable.ruler_20_15, R.drawable.ruler_20_16,
			R.drawable.ruler_20_17, R.drawable.ruler_20_18,
			R.drawable.ruler_20_19, R.drawable.ruler_20_20 };

	public Ruler(Context context) {
		super(context);
		init();
	}

	public Ruler(Context context, AttributeSet attrs) {
		super(context, attrs);
		init();
	}

	private void init() {
		//mGestureDetector = new GestureDetector(getContext(),(OnGestureListener) this);
		setOnTouchListener(this);
		setLongClickable(true);

		setCurrentValue(1);

		this.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

			}
		});
	}

	/** @pdOid 0de98837-efe8-495d-bf06-e2e79abaa24d */
	private int maxValue = 10;
	/** @pdOid 90c27153-0c9d-470a-baca-0baa128197aa */
	private int minValue = 1;
	/** @pdOid 9c1a3117-7beb-4924-9573-427513e803c1 */
	private int currentValue = 0;

	/** @pdOid 5c11f657-7758-45c3-aaaa-d48bf4b39a35 */
	public int getCurrentValue() {
		return currentValue;
	}

	public void addAfterRulerNumberChosen(RulerNumberChosenListener listener) {
		numberChosenListeners.add(listener);
	}

	private void fireAfterRulerNumberChosen() {
		for (RulerNumberChosenListener listener : numberChosenListeners) {
			listener.afterRulerNumberChosen(currentValue);
		}
	}

	@SuppressWarnings("deprecation")
	private Drawable getPhase1Drawable(int value) {
		if (value >= minValue && value <= maxValue) {
			return getResources().getDrawable(PHASE1_RES_IDS[value]);
		} else {
			return null;
		}
	}

	@SuppressWarnings("deprecation")
	private Drawable getPhase2Drawable(int value) {
		if (value >= minValue && value <= maxValue) {
			return getResources().getDrawable(PHASE2_RES_IDS[value]);
		} else {
			return null;
		}
	}

	/**
	 * @param newCurrentValue
	 * @pdOid bc23e2fa-47c8-4772-91e7-ee5ecf1ac542
	 */
	public void setCurrentValue(int newCurrentValue) {
		if (newCurrentValue != currentValue) {
			if (newCurrentValue <= maxValue && newCurrentValue >= minValue) {
				currentValue = newCurrentValue;
				// reload ruler image files
				reloadFiles();
				fireAfterRulerNumberChosen();
			}

		}
	}

	private void reloadFiles() {
		if (phaseNo == 1) {
			setImageDrawable(getPhase1Drawable(currentValue));
		} else {
			setImageDrawable(getPhase2Drawable(currentValue));
		}

	}

	@Override
	public boolean onDown(MotionEvent e) {
		return false;
	}

	@Override
	public void onShowPress(MotionEvent e) {

	}

	@Override
	public boolean onSingleTapUp(MotionEvent e) {
		return false;
	}

	@Override
	public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX,
			float distanceY) {
		return false;
	}

	@Override
	public void onLongPress(MotionEvent e) {
	}

	@Override
	public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX,
			float velocityY) {
		if (e1.getX() - e2.getX() > verticalMinDistance
				&& Math.abs(velocityX) > minVelocity) {

			// ÇÐ»»Activity
			// Intent intent = new Intent(ViewSnsActivity.this,
			// UpdateStatusActivity.class);
			// startActivity(intent);
			setCurrentValue(currentValue - 1);
		} else if (e2.getX() - e1.getX() > verticalMinDistance
				&& Math.abs(velocityX) > minVelocity) {

			// ÇÐ»»Activity
			// Intent intent = new Intent(ViewSnsActivity.this,
			// UpdateStatusActivity.class);
			// startActivity(intent);
			setCurrentValue(currentValue + 1);
		} else {
			return false;
		}
		return true;

	}

	@SuppressLint("ClickableViewAccessibility")
	@Override
	public boolean onTouch(View v, MotionEvent event) {

		if (event.getAction() == MotionEvent.ACTION_DOWN) {
			float x = event.getX();
			int numColWidth = getNumColWidth();
			if (x > numColWidth * currentValue) {// increae
				setCurrentValue(getCurrentValue() + 1);
			} else if (x < numColWidth * (currentValue - 1)) {
				setCurrentValue(getCurrentValue() - 1);
			}
			return true;
		}else{
			return false;
		}
	}

	private int getNumColWidth() {
		if (phaseNo == 1) {
			return NUM_COL_WIDTH_10;
		} else {
			return NUM_COL_WIDTH_20;
		}
	}

	public void nexPhase() {
		if (phaseNo == 1) {
			phaseNo = 2;
			maxValue = 20;
		} else {
			phaseNo = 1;
			maxValue = 10;
		}
		setCurrentValue(minValue);
		reloadFiles();
	}

	public boolean isLastPhase() {
		return phaseNo == 2;
	}
}
