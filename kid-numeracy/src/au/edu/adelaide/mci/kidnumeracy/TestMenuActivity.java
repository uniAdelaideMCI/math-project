package au.edu.adelaide.mci.kidnumeracy;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

/**
 * The user interface for the test menu
 * @author Group 5
 *
 */
public class TestMenuActivity extends BaseActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_test_menu);
	}
	
	public void onBtnCountGridTestClick(View view){
		//Intent intent = new Intent(this,CountTestActivity.class);
		Intent intent = new Intent(this,PuzzleTestTouchActivity.class);
		startActivity(intent);
	}
	
	/**
	 * Invoke the user interface that test kids' ability to count objects
	 * @param view
	 */
	public void onBtnCountObjectsTestClick(View view){
		Intent intent = new Intent(this,CountObjectTestActivity.class);
		startActivity(intent);
	}
	
	public void onBtnCountRulerTestClick(View view){
		Intent intent = new Intent(this, CountRulerTestActivity.class);
		startActivity(intent);
	}
	
	public void onBtnAddTestClick(View view){
		
	}
	
	public void onBtnSubtractTestClick(View view){
		
	}
	
	
}
