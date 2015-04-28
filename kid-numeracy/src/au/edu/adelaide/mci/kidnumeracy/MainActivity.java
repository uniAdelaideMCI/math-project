package au.edu.adelaide.mci.kidnumeracy;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.GridView;
import android.widget.TextView;

public class MainActivity extends ActionBarActivity {
	
	private static String COUNT_LEARNING = "au.edu.adelaide.mci.kidnumeracy.MainActivity.COUNT_LEARNING";

	private CountLearning countLearning;
	
	public CountLearning getCountLearning() {
		return countLearning;
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		//restore counter learning object
		if (null != savedInstanceState){
			countLearning = (CountLearning)savedInstanceState.getSerializable(COUNT_LEARNING);
		}else{
			countLearning = new CountLearning();
		}
		updateViews();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
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
	
	public void textViewNum_onClick(View v){
		countLearning.nextValue();
		//playConfirmSound();
		updateViews();
	}

	/**
	 * p
	 */
	private void playConfirmSound() {
		MediaPlayer mPlayer = MediaPlayer.create(MainActivity.this, R.raw.app_6);
		mPlayer.start();
	}

	private void updateViews() {
		TextView textView = (TextView)findViewById(R.id.textViewNum);
		textView.setText(String.valueOf(countLearning.getCurrentVaue()));
		GridView gridview = (GridView) findViewById(R.id.selectedApples);
		
		gridview.setAdapter(new ImageAdapter(this));
	}

	@Override
	protected void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);
		outState.putSerializable(COUNT_LEARNING, countLearning);
	}
}
