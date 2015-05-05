package au.edu.adelaide.mci.kidnumeracy;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

@SuppressWarnings("deprecation")
public class SubtractLearningActivity extends ActionBarActivity implements SubtractListener{

	private SubtractLearning subtractLearning;
	
	private static final String SUBTRACT_LEARNING = "au.edu.adelaide.mci.kidnumeracy.SUBTRACT_LEARNING";
	
	private MediaPlayer mPlayer; 
	
	@Override
	protected void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);
		outState.putSerializable(SUBTRACT_LEARNING, subtractLearning);
	}
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_subtract_learning);
		mPlayer = MediaPlayer.create(SubtractLearningActivity.this, R.raw.ding);
		if (null != savedInstanceState){
			subtractLearning = (SubtractLearning)savedInstanceState.getSerializable(SUBTRACT_LEARNING);
		}else{
			subtractLearning = new SubtractLearning();
			subtractLearning.subtract();
		}
		subtractLearning.addSubtractListener(this);
		this.subtract();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.subtract_learning, menu);
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
	public void subtract() {
		StringBuffer sb = new StringBuffer();
		sb.append(subtractLearning.getOperand1())
			.append(" - ").append(subtractLearning.getOperand2())
			.append(" = ").append(subtractLearning.getResult());
		TextView tvResult = (TextView)findViewById(R.id.tvResult);
		tvResult.setText(sb.toString());	
	}
	
	public void onSubtractUiClick(View view){
		mPlayer.start();
		subtractLearning.subtract();
	}
}
