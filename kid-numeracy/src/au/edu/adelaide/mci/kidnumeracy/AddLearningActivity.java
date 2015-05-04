package au.edu.adelaide.mci.kidnumeracy;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

public class AddLearningActivity extends ActionBarActivity implements AddListener {
	
	private AddLearning addLearning = new AddLearning();
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_add_learning);
		addLearning.addAddListener(this);
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
	}
}
