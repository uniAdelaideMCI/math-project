package au.edu.adelaide.mci.kidnumeracy;

import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
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

	private NumImageView nivRight;
	
	private ImageButton ibBack;
	private ImageButton ibOpMode;
	private ImageButton ibPhaseChangeRight;
	
	//0 read-only mode 1 tap mode 2 drop and drag mode
	private int opMode = 2;

	//Which phase
	private int phaseNo = 1;

	/**
	 * @return the max number of cells in the grid
	 */
	private int getMaxValue() {
		if (phaseNo == 1){
			return 6;
		}else if (phaseNo == 2){
			return 10;
		}else if (phaseNo == 3){
			return 16;
		}else{
			return 20;
		}
	}
	
	@SuppressWarnings("deprecation")
	public void nextPhase(){
		//go to next phase
		phaseNo++;
		if (phaseNo == 5){
			phaseNo = 1;
		}
		
		//change the number of stars according to phase
		Drawable drawable = null;
		if (phaseNo == 1){
			drawable = getResources().getDrawable(R.drawable.phase_onestar);
		}else if (phaseNo == 2){
			drawable = getResources().getDrawable(R.drawable.phase_twostar);
		}else if (phaseNo == 3){
			drawable = getResources().getDrawable(R.drawable.phase_threestar);
		}else{
			drawable = getResources().getDrawable(R.drawable.phase_fourstar);
		}
		
		//set the number of columns of the grid view containing objects
		nogvLeft.setNumColumns(getNumColumns());
		nogvRight.setNumColumns(getNumColumns());
		//choose the picture randomnly
	    nogvRight.setResIdIndex(nogvLeft.getRandomResIdIndex(true));
		ibPhaseChangeRight.setImageDrawable(drawable);
		nogvLeft.setMaxValue(getMaxValue());
		nogvLeft.setNumValue(getLeftInitialValue(),true);
		nogvRight.setMaxValue(getMaxValue());
		nogvRight.setNumValue(0);
		nivRight.setNumValue(0);
	}

	private int getNumColumns() {
		if (phaseNo == 1 || phaseNo == 2){
			return 3;
		}else if (phaseNo == 3){
			return 4;
		}else{
			return 5;
		}
	}

	/**
	 * @return The initial value in the left part
	 */
	private int getLeftInitialValue() {
		return phaseNo * 5;
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_count_up_down_learn);
		
		int max = getMaxValue();

		// show objects to be counted
		nogvLeft = (NumObjectGridView) findViewById(R.id.nogvLeft);
		nogvRight = (NumObjectGridView) findViewById(R.id.nogvRight);
		nogvLeft.setOpMode(opMode);
		nogvRight.setOpMode(opMode);
		nivRight = (NumImageView) findViewById(R.id.nivRight);
		
		ibBack = (ImageButton)findViewById(R.id.ibBack);
		ibOpMode = (ImageButton)findViewById(R.id.ibOpMode);
		ibPhaseChangeRight= (ImageButton)findViewById(R.id.ibPhaseChangeRight);
		nogvLeft.addNumChangedListener(this);
		int resIndex = nogvLeft.getRandomResIdIndex(false);
		//choose new set of pictures whose color is different from the old ones
		nogvRight.setStyleType(2);
		nogvRight.setResIdIndex(resIndex);
		nogvLeft.setResIdIndex(resIndex);
		nogvLeft.setMaxValue(max);
		nogvLeft.setNumValue(getLeftInitialValue(),true);
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
				if (opMode == NumObjectGridView.OP_MODE_TAP){
					if (oldValue > nogvRight.getNumValue()){  //decrease value 
						nogvLeft.increase(true);
					}
				}else if (opMode == NumObjectGridView.OP_MODE_DRAG_N_DROP){
					if (oldValue < nogvRight.getNumValue()){  //increase
						nogvLeft.decreaseDroppedValue(); 
					}					
				}
			}
		});
		nogvRight.setMaxValue(max);
		nogvRight.setNumValue(0);

		// show corresponding value

		nivRight.setNumValue(0);
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
		//tap mode 
		if (opMode == NumObjectGridView.OP_MODE_TAP){
			if (oldValue > nogvLeft.getNumValue()){  //decrease value 
				nogvRight.increase(true);
			}			
		}else if (opMode == NumObjectGridView.OP_MODE_DRAG_N_DROP){
			if (oldValue < nogvLeft.getNumValue()){
				nogvRight.decreaseDroppedValue();
			}
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
		nextPhase();
	}
	
	/**
	 * the event handler for back button to back to the main activity
	 * @param view
	 */
	public void onBackClick(View view){
		finish();
	}
	
	/**
	 * The event handler for the botton of choosing operation mode
	 * @param view
	 */
	public void onOpModeClick(View view){
		if (opMode == NumObjectGridView.OP_MODE_DRAG_N_DROP){
			opMode = NumObjectGridView.OP_MODE_TAP;
			ibOpMode.setImageDrawable(getResources().getDrawable(R.drawable.button_touch));
		}else{
			opMode = NumObjectGridView.OP_MODE_DRAG_N_DROP;
			ibOpMode.setImageDrawable(getResources().getDrawable(R.drawable.button_drag));
		}
		nogvLeft.setOpMode(opMode);
		nogvRight.setOpMode(opMode);
	}
}
