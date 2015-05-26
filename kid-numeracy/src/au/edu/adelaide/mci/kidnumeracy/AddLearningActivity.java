package au.edu.adelaide.mci.kidnumeracy;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import au.edu.adelaide.mci.kidnumeracy.component.NumObjectGridView;

@SuppressWarnings("deprecation")
public class AddLearningActivity extends ActionBarActivity implements AddListener {
	
	private AddLearning addLearning;

	private MediaPlayer mPlayer; 
	
	//compoenets in the activity
	NumObjectGridView nogvOperand1; //objects for first operand
	NumImageView nivOperand1;
	NumObjectGridView nogvOperand2; //objects for second operand
	NumImageView nivOperand2;
	NumObjectGridView nogvResult; //objects for second operand
	NumImageView nivResult;	
	
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
		nivOperand1 = (NumImageView)findViewById(R.id.nivOperand1);
		nogvOperand2 = (NumObjectGridView)findViewById(R.id.nogvOperand2);
		nogvOperand2.setResIdIndex(resIdsIndex);
		nogvOperand2.setOpMode(NumObjectGridView.OP_MODE_READ_ONLY);
		nivOperand2 = (NumImageView)findViewById(R.id.nivOperand2);	
		
		nogvResult = (NumObjectGridView)findViewById(R.id.nogvResult);
		nogvResult.setResIdIndex(resIdsIndex);
		nogvResult.setOpMode(NumObjectGridView.OP_MODE_READ_ONLY);
		nivResult = (NumImageView)findViewById(R.id.nivResult);
		
		//trigger add event
		addLearning.add();	
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.add_learning, menu);
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
	
	public void onAddUiClick(View view){
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
