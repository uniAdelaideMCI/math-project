package au.edu.adelaide.mci.kidnumeracy;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

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
	
	public void onBtnCountObjectsTestClick(View view){
		
	}
	
	public void onBtnCountRulerTestClick(View view){
		
	}
	
	public void onBtnAddTestClick(View view){
		
	}
	
	public void onBtnSubtractTestClick(View view){
		
	}
	
	
}
