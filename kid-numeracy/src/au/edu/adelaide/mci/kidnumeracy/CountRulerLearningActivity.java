package au.edu.adelaide.mci.kidnumeracy;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import au.edu.adelaide.mci.kidnumeracy.component.Ruler;
import au.edu.adelaide.mci.kidnumeracy.component.RulerNumberChosenListener;

/**
 * The User Interface for count learning via dragging a ruler
 * @author Group 5
 *
 */
public class CountRulerLearningActivity extends BaseActivity implements
		RulerNumberChosenListener {
	private Ruler ruler;
	private NumImageView nivNum;
	private ImageButton ibPhaseChangeRight;
	private ImageButton ibBack;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_count_ruler_learning);
		ruler = (Ruler) findViewById(R.id.ruler);
		ruler.addAfterRulerNumberChosen(this);
		nivNum = (NumImageView) findViewById(R.id.nivNum);
		ruler.setCurrentValue(0);
		nivNum.setNumValue(0);

		ibBack = (ImageButton) findViewById(R.id.ibBack);
		ibPhaseChangeRight = (ImageButton) findViewById(R.id.ibPhaseChangeRight);
	}

	@Override
	public void afterRulerNumberChosen(int newNumber) {
		nivNum.setNumValue(newNumber);
	}

	@SuppressWarnings("deprecation")
	public void onPhaseChooseClick(View view) {
		// change the phase
		ruler.nexPhase();

		// change image on the button
		if (ruler.isLastPhase()) {
			ibPhaseChangeRight.setImageDrawable(getResources().getDrawable(R.drawable.phase_twostar));
		} else {
			ibPhaseChangeRight.setImageDrawable(getResources().getDrawable(R.drawable.phase_onestar));
		}
	}
	
	public void onBackClick(View view){
		finish();
	}
}
