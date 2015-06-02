package au.edu.adelaide.mci.kidnumeracy;

import android.app.Activity;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import au.edu.adelaide.mci.kidnumeracy.component.NumObjectGridView;

public class AddLearningActivity extends Activity implements AddListener {
	
	private AddLearning addLearning;

	private MediaPlayer mPlayer; 
	
	//compoenets in the activity
	NumObjectGridView nogvOperand1; //objects for first operand
	NumImageView nivOperand1;
	NumObjectGridView nogvOperand2; //objects for second operand
	NumImageView nivOperand2;
	NumObjectGridView nogvResult; //objects for second operand
	NumImageView nivResult;

	/**
	 * The onClickListener for all NumObjectGridViews
	 */
	private OnClickListener onClickListener = new View.OnClickListener() {
		
		@Override
		public void onClick(View v) {
			//switch to next addition equation
			nextAddition();					
		}
	};	
	
	private static final String ADD_LEARNING = "au.edu.adelaide.mci.kidnumeracy.ADD_LEARNING";

	@Override
	protected void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);
		outState.putSerializable(ADD_LEARNING, addLearning);
	}	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_add_learning);
		mPlayer = MediaPlayer.create(AddLearningActivity.this, R.raw.ding);
		if (null != savedInstanceState){
			addLearning = (AddLearning)savedInstanceState.getSerializable(ADD_LEARNING);
		}else{
			addLearning = new AddLearning();
		}
		addLearning.addAddListener(this);

		
		nogvOperand1 = (NumObjectGridView)findViewById(R.id.nogvOperand1);
		int resIdsIndex = nogvOperand1.getRandomResIdIndex(false);
		nogvOperand1.setOpMode(NumObjectGridView.OP_MODE_READ_ONLY);
		nogvOperand1.setClickable(true);
		nogvOperand1.setClickHander(onClickListener );
		nivOperand1 = (NumImageView)findViewById(R.id.nivOperand1);
		nogvOperand2 = (NumObjectGridView)findViewById(R.id.nogvOperand2);
		nogvOperand2.setResIdIndex(resIdsIndex);
		nogvOperand2.setOpMode(NumObjectGridView.OP_MODE_READ_ONLY);
		nogvOperand2.setClickHander(onClickListener);
		
		nivOperand2 = (NumImageView)findViewById(R.id.nivOperand2);	
		
		nogvResult = (NumObjectGridView)findViewById(R.id.nogvResult);
		nogvResult.setResIdIndex(resIdsIndex);
		nogvResult.setOpMode(NumObjectGridView.OP_MODE_READ_ONLY);
		nogvResult.setClickHander(onClickListener);
		nivResult = (NumImageView)findViewById(R.id.nivResult);
		
		//trigger add event
		addLearning.add();	
	}

	
	public void onAddUiClick(View view){
		nextAddition();
	}

	/**
	 * Generate the next addition equation
	 */
	private void nextAddition() {
		mPlayer.start();
		addLearning.add();
	}

	@Override
	public void add() {
		int operand1 = addLearning.getOperand1();
		int operand2 = addLearning.getOperand2();
		int result = addLearning.getResult();
		
		nivOperand1.setNumValue(operand1);
		nogvOperand1.setMaxValue(20);
		nogvOperand1.setNumValue(operand1);
		
		nivOperand2.setNumValue(operand2);
		nogvOperand2.setMaxValue(20);
		nogvOperand2.setNumValue(operand2);
		
		NumImageView nivResult = (NumImageView)findViewById(R.id.nivResult);
		nivResult.setNumValue(result);
		
		nogvResult.setMaxValue(20);
		nogvResult.setNumValue(result);
	}
}
