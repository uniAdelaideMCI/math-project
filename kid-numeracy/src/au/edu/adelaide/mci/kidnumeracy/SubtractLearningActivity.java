package au.edu.adelaide.mci.kidnumeracy;

import android.app.Activity;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;
import au.edu.adelaide.mci.kidnumeracy.component.NumObjectGridView;

/**
 * The subtract learning activity
 * @author Group 5
 *
 */
@SuppressWarnings("deprecation")
public class SubtractLearningActivity extends BaseActivity implements
		SubtractListener {

	private SubtractLearning subtractLearning;

	private static final String SUBTRACT_LEARNING = "au.edu.adelaide.mci.kidnumeracy.SUBTRACT_LEARNING";

	private MediaPlayer mPlayer;

	// compoenets in the activity
	NumObjectGridView nogvOperand1; // objects for first operand
	NumImageView nivOperand1;
	NumObjectGridView nogvOperand2; // objects for second operand
	NumImageView nivOperand2;
	NumObjectGridView nogvResult; // objects for second operand
	NumImageView nivResult;

	/**
	 * The onClickListener for all NumObjectGridViews
	 */
	private OnClickListener onClickListener = new View.OnClickListener() {

		@Override
		public void onClick(View v) {
			// switch to next addition equation
			nextSubtraction();
		}

	};

	@Override
	protected void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_subtract_learning);
		mPlayer = MediaPlayer.create(SubtractLearningActivity.this, R.raw.ding);
		subtractLearning = new SubtractLearning();
		subtractLearning.addSubtractListener(this);

		nogvOperand1 = (NumObjectGridView) findViewById(R.id.nogvOperand1);
		int resIdsIndex = nogvOperand1.getRandomResIdIndex(false);
		nogvOperand1.setOpMode(NumObjectGridView.OP_MODE_READ_ONLY);
		nogvOperand1.setClickable(true);
		nogvOperand1.setClickHander(onClickListener);
		nivOperand1 = (NumImageView) findViewById(R.id.nivOperand1);
		nogvOperand2 = (NumObjectGridView) findViewById(R.id.nogvOperand2);
		nogvOperand2.setResIdIndex(resIdsIndex);
		nogvOperand2.setOpMode(NumObjectGridView.OP_MODE_READ_ONLY);
		nogvOperand2.setClickHander(onClickListener);

		nivOperand2 = (NumImageView) findViewById(R.id.nivOperand2);

		nogvResult = (NumObjectGridView) findViewById(R.id.nogvResult);
		nogvResult.setResIdIndex(resIdsIndex);
		nogvResult.setOpMode(NumObjectGridView.OP_MODE_READ_ONLY);
		nogvResult.setClickHander(onClickListener);
		nivResult = (NumImageView) findViewById(R.id.nivResult);

		subtractLearning.subtract();
	}

	private void nextSubtraction() {
		mPlayer.start();
		subtractLearning.subtract();
		// change pictures of objects at random
		int resIdsIndex = nogvOperand1.getRandomResIdIndex(true);
		nogvOperand2.setResIdIndex(resIdsIndex);
		nogvResult.setResIdIndex(resIdsIndex);
	}

	@Override
	protected void onPause() {
		mPlayer.release();
		super.onPause();
	}

	@Override
	protected void onStop() {
		mPlayer.release();
		super.onStop();
	}

	@Override
	public void subtract() {
		int operand1 = subtractLearning.getOperand1();
		int operand2 = subtractLearning.getOperand2();
		int result = subtractLearning.getResult();

		NumImageView nivResult = (NumImageView) findViewById(R.id.nivResult);
		nivResult.setNumValue(result);

		configNogvOperand(nogvResult, result);

		nivOperand1.setNumValue(operand1);
		configNogvOperand(nogvOperand1, operand1);
		// set width and height of operand1 gridview
		// configOperandDimension(nogvResult,nogvOperand1);

		nivOperand2.setNumValue(operand2);
		configNogvOperand(nogvOperand2, operand2);
		// set width and height of operand2 gridview
		// configOperandDimension(nogvResult,nogvOperand2);
	}

	/**
	 * config height and width of operand grid view according to the dimension
	 * of the result grid view
	 * 
	 * @param aNogvResult
	 * @param aNogvOperand
	 */
	private void configOperandDimension(NumObjectGridView aNogvResult,
			NumObjectGridView aNogvOperand) {
		int width = aNogvResult.getColumnWidth() * aNogvOperand.getNumColumns();
		// row height for result grid view
		int resultRowHeight = aNogvResult.getHeight()
				/ (aNogvResult.getRowCount());
		int height = resultRowHeight * aNogvOperand.getRowCount();
		aNogvOperand.setLayoutParams(new LinearLayout.LayoutParams(width,
				height));
	}

	private void configNogvOperand(NumObjectGridView numObject, int numValue) {
		numObject.setNumColumns(getNumColumns(numValue));
		numObject.setMaxValue(getMaxValue(numValue));
		numObject.setNumValue(numValue, true);

	}

	public void onSubtractUiClick(View view) {
		nextSubtraction();
	}

	public void onBackClick(View view) {
		finish();
	}

	/**
	 * determines maxValue according to current values
	 * 
	 * @param numValue
	 * @return
	 */
	private int getMaxValue(int numValue) {
		if (numValue == 1) {
			return 1;
		} else if (numValue == 2) {
			return 2;
		} else if (numValue >= 3 && numValue <= 4) {
			return 4;
		} else if (numValue >= 5 && numValue <= 6) {
			return 6;
		} else if (numValue >= 7 && numValue <= 9) {
			return 9;
		} else {
			return 12;
		}
	}

	private int getNumColumns(int numValue) {
		if (numValue == 1) {
			return 1;
		} else if (numValue >= 2 && numValue <= 4) {
			return 2;
		} else if (numValue >= 5 && numValue <= 9) {
			return 3;
		} else {
			return 3;
		}
	}
}
