package au.edu.adelaide.mci.kidnumeracy;

import java.io.IOException;

import org.json.JSONException;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import au.edu.adelaide.mci.kidnumeracy.component.NumObjectGridView;

/**
 * Count up and down at the same time
 * @author Yun Zhang
 *
 */
public class CountUpDownLearnActivity extends Activity implements NumberListener {
	private CountLearning countLearning;
	private NumObjectGridView nogvLeft;
	private NumObjectGridView nogvRight;
	
	private NumImageView nivLeft;
	private NumImageView nivRight;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_count_up_down_learn);
		try {
			CountLearningProcess countLearningProcess = CountLearningProcess
					.load(this);
			int max = countLearningProcess.getFirstPhase().getMaxValue();
			countLearning = new CountLearning(countLearningProcess);
			countLearning.addNumberListener(this);
			
			//show objects to be counted
			nogvLeft = (NumObjectGridView)findViewById(R.id.nogvLeft);
			nogvRight = (NumObjectGridView)findViewById(R.id.nogvRight);
			nogvLeft.setNumValue(max - 1);
			nogvRight.setNumValue(1);
			
			//show corresponding value
			nivLeft = (NumImageView)findViewById(R.id.nivLeft);
			nivRight = (NumImageView)findViewById(R.id.nivRight);
			nivLeft.setNumValue(max - 1);
			nivRight.setNumValue(1);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.count_up_down_learn, menu);
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
	public void numberChanged() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void ondDirectionChanged() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onPhaseChanged() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onAllPhaseCounted() {
		// TODO Auto-generated method stub
		
	}
}
