package au.edu.adelaide.mci.kidnumeracy;

import java.util.ArrayList;
import java.util.Random;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import au.edu.adelaide.mci.kidnumeracy.component.Ruler;
import au.edu.adelaide.mci.kidnumeracy.component.RulerNumberChosenListener;

/**
 * The User interface for count test via moving a ruler
 * @author Group 5
 *
 */
public class CountRulerTestActivity extends BaseActivity implements
		RulerNumberChosenListener {
	private Ruler ruler;
	private NumImageView nivNum;
	//private ImageButton ibPhaseChangeRight;
	private ImageButton ibBack;
	
	private ImageButton ibNextQuestion;
	private ImageView ivRightOrWrong;
	private Random random = new Random(System.currentTimeMillis());
	private int expectedValue = 0;
	private int currentValue = -1;
	private int newValue = 1;
	private ArrayList<Integer> nums = new ArrayList<Integer>();

	public void setExpectedValue(int expectedValue) {
		this.expectedValue = expectedValue;
		nivNum.setNumValue(expectedValue);
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_count_ruler_test);
		ruler = (Ruler) findViewById(R.id.ruler);
		ruler.addAfterRulerNumberChosen(this);
		nivNum = (NumImageView) findViewById(R.id.nivNum);
		nivNum.setNumValue(0);

		ibBack = (ImageButton) findViewById(R.id.ibBack);
		//ibPhaseChangeRight = (ImageButton) findViewById(R.id.ibPhaseChangeRight);
		ibNextQuestion = (ImageButton) findViewById(R.id.ibNextQuestion);
		ivRightOrWrong = (ImageView)findViewById(R.id.ivRightOrWrong);
		// show corresponding value
		nextQuestion();
	}
	
	public void onSubmitClick(View view){
		if (ruler.getCurrentValue() == expectedValue){
			//tell the user that the answer was right
			answerIsRight();
		}else{
			anserIsWrong();
		}
	}

	@Override
	public void afterRulerNumberChosen(int newNumber) {
		hideRightOrWrong();
	}

	public void onBackClick(View view) {
		finish();
	}
	
	public void onNextQuestionClick(View view) {
		nextQuestion();
	}

	private void nextQuestion() {
		//setExpectedValue(getRandomValue());
		
		setExpectedValue(getNextRandomSeqNum());
		if (expectedValue <= 10){
			ruler.setPhaseNo(1,0);
		}else{
			ruler.setPhaseNo(2,0);
		}
		ruler.reloadFiles();
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
	
//	private int getNextRandomSeqNum() {
//		if (currentValue == -1){
//			currentValue = 1;
//			return 1;
//		}else{
//			if (nums.size() != 0){
//				currentValue = nums.remove(random.nextInt(nums.size()));
//			}else{
//				if (newValue <= 5){
//					fillValues(2,5);
//					newValue = 5;
//				}
//				if (newValue <= 10){
//					fillValues(6,10);
//					newValue = 10;
//				}
//				if (newValue <= 15){
//					fillValues(11,15);
//					newValue = 15;
//				}
//				if (newValue <= 20){
//					fillValues(16,20);
//					newValue = 1;
//				}				
//			}
//
//		}
//		return currentValue;
//	}
	private void fillValues(int min, int max) {
		nums.clear();
		for(int i = min ; i <= max ; i++){
			nums.add(i);
		}
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

	private int getRandomValue() {
		return random.nextInt(getMaxValue()) + 1;
	}

	private int getMaxValue() {
		return 20;
	}
}
