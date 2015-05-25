package au.edu.adelaide.mci.kidnumeracy;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

@SuppressWarnings("deprecation")
public class MainActivity extends ActionBarActivity {
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}
	
	public void onBtnCountClick(View v){
		//Intent intent = new Intent(MainActivity.this,CountLearnActivity.class);
		//Intent intent = new Intent(MainActivity.this,CountUpDownLearnActivity.class);
		Intent intent = new Intent(MainActivity.this,CountRulerLearningActivity.class);
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
		Intent intent = new Intent(this,CountTestActivity.class);
		startActivity(intent);
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

	@Override
	protected void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);
	}

}
