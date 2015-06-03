package au.edu.adelaide.mci.kidnumeracy;

import java.util.Random;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import au.edu.adelaide.mci.kidnumeracy.component.Ruler;
import au.edu.adelaide.mci.kidnumeracy.component.RulerNumberChosenListener;

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

	@Override
	public void afterRulerNumberChosen(int newNumber) {
		if (newNumber == expectedValue){
			//tell the user that the answer was right
			answerIsRight();
		}else{
			anserIsWrong();
		}
	}

//	@SuppressWarnings("deprecation")
//	public void onPhaseChooseClick(View view) {
//		// change the phase
//		ruler.nexPhase();
//
//		// change image on the button
//		if (ruler.isLastPhase()) {
//			ibPhaseChangeRight.setImageDrawable(getResources().getDrawable(
//					R.drawable.phase_onestar));
//		} else {
//			ibPhaseChangeRight.setImageDrawable(getResources().getDrawable(
//					R.drawable.phase_twostar));
//		}
//	}

	public void onBackClick(View view) {
		finish();
	}
	
	public void onNextQuestionClick(View view) {
		nextQuestion();
	}

	private void nextQuestion() {
		setExpectedValue(getRandomValue());
		if (expectedValue <= 10){
			ruler.setPhaseNo(1,0);
		}else{
			ruler.setPhaseNo(2,0);
		}
		anserIsWrong();
	}

	private void anserIsWrong() {
		ivRightOrWrong.setVisibility(View.INVISIBLE);
	}

	private void answerIsRight() {
		ivRightOrWrong.setVisibility(View.VISIBLE);
	}

	private int getRandomValue() {
		return random.nextInt(getMaxValue()) + 1;
	}

	private int getMaxValue() {
		return 20;
	}
}
