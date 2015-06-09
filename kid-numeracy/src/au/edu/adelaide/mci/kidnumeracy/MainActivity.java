package au.edu.adelaide.mci.kidnumeracy;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

/**
 * The main user interface for the whole app
 * @author Group 5
 *
 */
public class MainActivity extends BaseActivity {
	private final String TAG = "au.edu.adelaide.mci.kidnumeracy.MainActivity";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		//set main activity as the context of the background music player
		SoundHandler.setContext(this);
	}
	
	public void onBtnCountClick(View v){
		//Intent intent = new Intent(MainActivity.this,CountLearnActivity.class);
		Intent intent = null;
		if (v.getId() == R.id.btnCountObject){
			intent = new Intent(MainActivity.this,CountUpDownLearnActivity.class);
		}else{
			intent = new Intent(MainActivity.this,CountRulerLearningActivity.class);
		}
		startActivity(intent);
	}
	
	public void onBtnAddClick(View v){
		Intent intent = new Intent(MainActivity.this,AddLearningActivity.class);
		startActivity(intent);		
	}
	
	public void onBtnSubtractClick(View v){
		Intent intent = new Intent(MainActivity.this,SubtractLearningActivity.class);
		startActivity(intent);
	}
	
	/**
	 * @param view
	 */
	public void onBtnTestClick(View view){
		Intent intent = new Intent(this,TestMenuActivity.class);
		startActivity(intent);
	}

	@Override
	protected void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);
	}

	@Override
	protected void onDestroy() {
		Log.d(TAG, "onDestroy");
		super.onDestroy();
//		doUnbindService();
	}

	@Override
	protected void onPause() {
		Log.d(TAG, "onPause");
		super.onPause();
	}

	@Override
	protected void onResume() {
		Log.d(TAG, "onResume");
		super.onResume();
	}

	@Override
	protected void onRestart() {
		Log.d(TAG, "onRestart");
		super.onRestart();
	}

	@Override
	protected void onStop() {
		super.onStop();
		Log.i(TAG, "onStop");
	}
	
	

}
