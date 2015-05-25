package au.edu.adelaide.mci.kidnumeracy.component;

import java.util.Locale;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.GestureDetector.OnGestureListener;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.ImageView;
import android.widget.Toast;
import au.edu.adelaide.mci.kidnumeracy.CountLearning;
import au.edu.adelaide.mci.kidnumeracy.CountLearningPhase;
import au.edu.adelaide.mci.kidnumeracy.CountLearningProcess;
import au.edu.adelaide.mci.kidnumeracy.R;

/**
 * @author Yun
 *
 */
public class Ruler extends ImageView implements OnTouchListener,
		OnGestureListener {

	private GestureDetector mGestureDetector;
	private int verticalMinDistance = 20;
	private int minVelocity = 0;

	private CountLearningProcess countLearningProcess;
	private CountLearning countLearning;

	private static final int PHASE1_MAX = 10;
	private Drawable phase1Drawables[] = new Drawable[PHASE1_MAX+1];
	private static final int PHASE1_RES_IDS[] = { R.drawable.ruler_10_0,
			R.drawable.ruler_10_1, R.drawable.ruler_10_2,
			R.drawable.ruler_10_3, R.drawable.ruler_10_4,
			R.drawable.ruler_10_5, R.drawable.ruler_10_6,
			R.drawable.ruler_10_7, R.drawable.ruler_10_8,
			R.drawable.ruler_10_9, R.drawable.ruler_10_10 };

	public Ruler(Context context) {
		super(context);
		init();
	}

	public Ruler(Context context, AttributeSet attrs) {
		super(context, attrs);
		init();
	}

	private void init() {
		mGestureDetector = new GestureDetector((OnGestureListener) this);
		setOnTouchListener(this);
		setLongClickable(true);

		countLearningProcess = new CountLearningProcess();
		countLearningProcess.addLearningPhase(1, 1, 10);
		CountLearningPhase countLearningPhase = new CountLearningPhase(1, 1, 10);
		countLearning = new CountLearning(countLearningProcess);
		setCurrentValue(1);
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

	private Drawable getPhase1Drawable(int value) {
		if (value >= minValue && value <= maxValue) {
			if (phase1Drawables[value] == null){
				phase1Drawables[value] = getResources().getDrawable(PHASE1_RES_IDS[value]);
			}
			return phase1Drawables[value];
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
			}

		}
	}

	private void reloadFiles() {
		setImageDrawable(getPhase1Drawable(currentValue));
	}

	@Override
	public boolean onDown(MotionEvent e) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void onShowPress(MotionEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean onSingleTapUp(MotionEvent e) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX,
			float distanceY) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void onLongPress(MotionEvent e) {
		// TODO Auto-generated method stub
	}

	@Override
	public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX,
			float velocityY) {
		if (e1.getX() - e2.getX() > verticalMinDistance
				&& Math.abs(velocityX) > minVelocity) {

			// 切换Activity
			// Intent intent = new Intent(ViewSnsActivity.this,
			// UpdateStatusActivity.class);
			// startActivity(intent);
			Toast.makeText(getContext(), "向左手势", Toast.LENGTH_SHORT).show();
			setCurrentValue(currentValue - 1);
		} else if (e2.getX() - e1.getX() > verticalMinDistance
				&& Math.abs(velocityX) > minVelocity) {

			// 切换Activity
			// Intent intent = new Intent(ViewSnsActivity.this,
			// UpdateStatusActivity.class);
			// startActivity(intent);
			Toast.makeText(getContext(), "向右手势", Toast.LENGTH_SHORT).show();
			setCurrentValue(currentValue + 1);
		}

		return false;
	}

	@Override
	public boolean onTouch(View v, MotionEvent event) {
		return mGestureDetector.onTouchEvent(event);
	}
}
