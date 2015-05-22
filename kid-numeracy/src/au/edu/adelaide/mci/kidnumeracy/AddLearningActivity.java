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
			addLearning.add();
		}
		addLearning.addAddListener(this);
		this.add();
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
		StringBuffer sb = new StringBuffer();
		sb.append(addLearning.getOperand1())
			.append(" + ").append(addLearning.getOperand2())
			.append(" = ").append(addLearning.getResult());
		TextView tvResult = (TextView)findViewById(R.id.tvResult);
		tvResult.setText(sb.toString());
		
		NumImageView nivResult = (NumImageView)findViewById(R.id.nivResult);
		nivResult.setNumValue(addLearning.getResult());
		
		NumObjectGridView nogvAddResult = (NumObjectGridView)findViewById(R.id.nogvAddResult);
		nogvAddResult.setNumValue(addLearning.getResult());
	}
}
