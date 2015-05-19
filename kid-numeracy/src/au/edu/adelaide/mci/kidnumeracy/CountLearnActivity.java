package au.edu.adelaide.mci.kidnumeracy;

import java.io.IOException;

import org.json.JSONException;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.GridView;

@SuppressWarnings("deprecation")
public class CountLearnActivity extends ActionBarActivity implements NumberListener {

	private static final String TAG = "CountLearnActivity";
	
	private static String COUNT_LEARNING = "au.edu.adelaide.mci.kidnumeracy.COUNT_LEARNING";
	
	private CountLearning countLearning;
	
	private boolean mNumFinished = false;
	
	boolean viewChange = false;
	
	public boolean isViewChange() {
		return viewChange;
	}

	public CountLearning getCountLearning() {
		return countLearning;
	}	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_count_learn);
		
		//get count learning phase definition from json files
		try {
			CountLearningProcess countLearningProcess = CountLearningProcess.load(this);
			//restore counter learning object
			if (null != savedInstanceState){
				countLearning = (CountLearning)savedInstanceState.getSerializable(COUNT_LEARNING);
			}else{
				countLearning = new CountLearning(countLearningProcess);
			}
			countLearning.addNumberListener(this);
			
			GridView gridview = (GridView) findViewById(R.id.selectedApples);
			gridview.setAdapter(new ImageAdapter(this));				
		} catch (IOException e) {
			e.printStackTrace();
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}
	
	public void textViewNum_onClick(View v){
		if (viewChange){
			GridView gridview = (GridView) findViewById(R.id.selectedApples);
			gridview.setAdapter(new ImageAdapter(this));
			viewChange = false;
		}else{
			countLearning.nextValue();
			playConfirmSound();			
		}
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

	@Override
	public void phaseChanged() {
		viewChange = true;
		Log.d(TAG, "ondPhaseChanged");		
	}

	@Override
	public void ondDirectionChanged() {
		viewChange = true;
		Log.d(TAG, "ondDirectionChanged");
	}

	public void updateView() {
		GridView gridview = (GridView) findViewById(R.id.selectedApples);
		gridview.setAdapter(new ImageAdapter(this));
		viewChange = false;
	}
}
