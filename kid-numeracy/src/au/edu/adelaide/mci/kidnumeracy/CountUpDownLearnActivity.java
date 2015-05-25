package au.edu.adelaide.mci.kidnumeracy;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import au.edu.adelaide.mci.kidnumeracy.component.NumObjectGridView;

/**
 * Count up and down at the same time
 * 
 * @author Yun Zhang
 *
 */
public class CountUpDownLearnActivity extends Activity implements NumberListener{
	private NumObjectGridView nogvLeft;
	private NumObjectGridView nogvRight;

	private NumImageView nivLeft;
	private NumImageView nivRight;

	private int phaseNo = 1;

	private int getMaxValue() {
		if (phaseNo == 1) {
			return 10;
		} else {
			return 20;
		}
	}
	
	public void nextPhase(){
		if (phaseNo == 1){
			phaseNo = 2;
		}else{
			phaseNo =1;
		} 
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_count_up_down_learn);
		
		int max = getMaxValue();

		// show objects to be counted
		nogvLeft = (NumObjectGridView) findViewById(R.id.nogvLeft);
		nogvRight = (NumObjectGridView) findViewById(R.id.nogvRight);
		nivLeft = (NumImageView) findViewById(R.id.nivLeft);
		nivRight = (NumImageView) findViewById(R.id.nivRight);
		nogvLeft.addNumChangedListener(this);
		int resIndex = nogvLeft.getRandomResIdIndex();
		nogvRight.setStyleType(2);
		nogvRight.setResIdIndex(resIndex);
		nogvLeft.setResIdIndex(resIndex);
		nogvLeft.setMaxValue(max - 1);
		nogvLeft.setNumValue(max - 1);
		nogvRight.addNumChangedListener(new NumberListener() {
			
			@Override
			public void ondDirectionChanged() {
				
			}
			
			@Override
			public void onPhaseChanged() {
				
			}
			
			@Override
			public void onAllPhaseCounted() {
				
			}
			
			@Override
			public void afterNumChanged(int oldValue) {
				nivRight.setNumValue(nogvRight.getNumValue());	
				if (oldValue > nogvRight.getNumValue()){  //decrease value 
					nogvLeft.increase(true);
				}
			}
		});
		nogvRight.setMaxValue(max - 1);
		nogvRight.setNumValue(1);

		// show corresponding value

		nivLeft.setNumValue(max - 1);
		nivRight.setNumValue(1);
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
	public void afterNumChanged(int oldValue) {
		nivLeft.setNumValue(nogvLeft.getNumValue());
		if (oldValue > nogvLeft.getNumValue()){  //decrease value 
			nogvRight.increase(true);
		}
	}

	@Override
	public void ondDirectionChanged() {
	}

	@Override
	public void onPhaseChanged() {
		
	}

	@Override
	public void onAllPhaseCounted() {
		
	}
	
	public void onPhaseChooseClick(View view){
		
	}
}
