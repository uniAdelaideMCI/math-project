package au.edu.adelaide.mci.kidnumeracy;

import java.util.Random;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import au.edu.adelaide.mci.kidnumeracy.component.NumObjectGridView;

/**
 * The User interface for count test via dragging or tapping objects
 * @author Group 5
 *
 */
public class CountObjectTestActivity extends BaseActivity implements
		NumberListener {
	private NumObjectGridView nogvLeft;
	private NumObjectGridView nogvRight;

	private NumImageView nivRight;

	private ImageButton ibBack;
	private ImageButton ibOpMode;
	private ImageButton ibNextQuestion;
	private Random random = new Random(System.currentTimeMillis());
	//the current expected value
	private int expectedValue = 0;
	private int currentValue = -1;
	
	private ImageView ivRightOrWrong;

	private void setExpectedValue(int expectedValue) {
		this.expectedValue = expectedValue;
		nivRight.setNumValue(expectedValue);
	}

	// 0 read-only mode 1 tap mode 2 drop and drag mode
	private int opMode = 2;

	/**
	 * @return the max number of cells in the grid
	 */
	private int getMaxValue() {
			return 20;
	}

	private int getNumColumns() {
		return 5;
	}

	/**
	 * @return The initial value in the left part
	 */
	private int getLeftInitialValue() {
		return getMaxValue();
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_count_object_test);

		int max = getMaxValue();

		// show objects to be counted
		nogvLeft = (NumObjectGridView) findViewById(R.id.nogvLeft);
		nogvRight = (NumObjectGridView) findViewById(R.id.nogvRight);
		nogvLeft.setOpMode(opMode);
		nogvRight.setOpMode(opMode);
		nivRight = (NumImageView) findViewById(R.id.nivRight);

		ibBack = (ImageButton) findViewById(R.id.ibBack);
		ibOpMode = (ImageButton) findViewById(R.id.ibOpMode);
		ibNextQuestion = (ImageButton) findViewById(R.id.ibNextQuestion);
		nogvLeft.addNumChangedListener(this);
		int resIndex = nogvLeft.getRandomResIdIndex(false);
		// choose new set of pictures whose color is different from the old ones
		nogvRight.setStyleType(2);
		nogvRight.setResIdIndex(resIndex);
		nogvLeft.setResIdIndex(resIndex);
		nogvLeft.setMaxValue(max);
		nogvLeft.setNumValue(getLeftInitialValue(), true);
		nogvRight.addNumChangedListener(new NumberListener() {

			@Override
			public void ondDirectionChanged() {

			}

			@Override
			public void onPhaseChanged() {

			}

			@Override
			public void onAllPhaseCounted() {

			}

			@Override
			public void afterNumChanged(int oldValue) {
				if (opMode == NumObjectGridView.OP_MODE_TAP){
					if (oldValue > nogvRight.getNumValue()){  //decrease value 
						nogvLeft.increase(true);
					}
				}else if (opMode == NumObjectGridView.OP_MODE_DRAG_N_DROP){
					if (oldValue < nogvRight.getNumValue()){  //increase
						nogvLeft.decreaseDroppedValue(); 
					}					
				}
				hideRightOrWrong();	
			}

		});
		nogvRight.setMaxValue(max);
		ivRightOrWrong = (ImageView)findViewById(R.id.ivRightOrWrong);
		// show corresponding value
		setExpectedValue(getNextRandomSeqNum());
	}
	
	private int getRandomValue() {
		return random.nextInt(getMaxValue()) + 1;
	}

	private void nextQuestion() {
		int resIndex = nogvLeft.getRandomResIdIndex(true);
		nogvRight.setResIdIndex(resIndex);
		nogvLeft.setNumValue(getLeftInitialValue(), true);
		nogvLeft.setNumValue(getLeftInitialValue(), true);
		nogvRight.setNumValue(0);
		//setExpectedValue(getRandomValue());
		setExpectedValue(getNextRandomSeqNum());
		hideRightOrWrong();
	}
	private int getNextRandomSeqNum() {
		if (currentValue == -1){
			currentValue = 1;
			return 1;
		}else{
			currentValue++;
			if (currentValue == 21){
				currentValue = 1;
			}
		}
		return currentValue;
	}

	private void anserIsWrong() {
		ivRightOrWrong.setImageDrawable(getResources().getDrawable(R.drawable.answer_wrong));
		ivRightOrWrong.setVisibility(View.VISIBLE);		
	}

	private void answerIsRight() {
		ivRightOrWrong.setImageDrawable(getResources().getDrawable(R.drawable.answer_right_tick));
		ivRightOrWrong.setVisibility(View.VISIBLE);
	}
	
	private void hideRightOrWrong(){
		ivRightOrWrong.setVisibility(View.INVISIBLE);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.count_up_down_learn, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	@Override
	public void afterNumChanged(int oldValue) {
		// tap mode
		if (opMode == NumObjectGridView.OP_MODE_TAP) {
			if (oldValue > nogvLeft.getNumValue()) { // decrease value
				nogvRight.increase(true);
			}
		} else if (opMode == NumObjectGridView.OP_MODE_DRAG_N_DROP) {
			if (oldValue < nogvLeft.getNumValue()) {
				nogvRight.decreaseDroppedValue();
			}
		}

	}

	@Override
	public void ondDirectionChanged() {
	}

	@Override
	public void onPhaseChanged() {

	}

	@Override
	public void onAllPhaseCounted() {

	}

	public void onNextQuestionClick(View view) {
		nextQuestion();
	}
	
	public void onSubmitClick(View view){
		if (nogvRight.getNumValue() == expectedValue){
			//tell the user that the answer was right
			answerIsRight();
		}else{
			anserIsWrong();
		}
	}



	/**
	 * the event handler for back button to back to the main activity
	 * 
	 * @param view
	 */
	public void onBackClick(View view) {
		finish();
	}

	/**
	 * The event handler for the botton of choosing operation mode
	 * 
	 * @param view
	 */
	public void onOpModeClick(View view) {
		if (opMode == NumObjectGridView.OP_MODE_DRAG_N_DROP) {
			opMode = NumObjectGridView.OP_MODE_TAP;
			ibOpMode.setImageDrawable(getResources().getDrawable(
					R.drawable.button_touch));
		} else {
			opMode = NumObjectGridView.OP_MODE_DRAG_N_DROP;
			ibOpMode.setImageDrawable(getResources().getDrawable(
					R.drawable.button_drag));
		}
		nogvLeft.setOpMode(opMode);
		nogvRight.setOpMode(opMode);
	}
}
