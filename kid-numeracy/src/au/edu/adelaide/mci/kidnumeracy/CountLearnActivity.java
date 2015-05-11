package au.edu.adelaide.mci.kidnumeracy;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.GridView;
import android.widget.TextView;

@SuppressWarnings("deprecation")
public class CountLearnActivity extends ActionBarActivity implements NumberListener {

	private static String COUNT_LEARNING = "au.edu.adelaide.mci.kidnumeracy.COUNT_LEARNING";
	
	private CountLearning countLearning;
	
	private boolean mNumFinished = false;
	
	public CountLearning getCountLearning() {
		return countLearning;
	}	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_count_learn);
		
		//restore counter learning object
		if (null != savedInstanceState){
			countLearning = (CountLearning)savedInstanceState.getSerializable(COUNT_LEARNING);
		}else{
			countLearning = new CountLearning();
		}
		
		countLearning.addNumberListener(this);
		
		GridView gridview = (GridView) findViewById(R.id.selectedApples);
		
		gridview.setAdapter(new ImageAdapter(this));
		updateViews();		
	}
	
	public void textViewNum_onClick(View v){
		countLearning.nextValue();
		playConfirmSound();
		updateViews();
	}
	
	/**
	 * p
	 */
	private void playConfirmSound() {
		MediaPlayer mPlayer;
		if (!mNumFinished){
			mPlayer = MediaPlayer.create(CountLearnActivity.this, R.raw.ding);
		}else{
			mPlayer = MediaPlayer.create(CountLearnActivity.this, R.raw.cheer);
			mNumFinished = false;
		}
		
		mPlayer.start();
	}
	
	@Override
	protected void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);
		outState.putSerializable(COUNT_LEARNING, countLearning);
	}
	
	@Override
	public void numberChanged() {

	}
	
	@Override
	public void numberFinished() {
		mNumFinished = true;
		countLearning.reset();			
	}	
	
	private void updateViews() {

	}	

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.count_learn, menu);
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
}
