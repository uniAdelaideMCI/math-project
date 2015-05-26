package au.edu.adelaide.mci.kidnumeracy;

import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import au.edu.adelaide.mci.kidnumeracy.component.Ruler;
import au.edu.adelaide.mci.kidnumeracy.component.RulerNumberChosenListener;

public class CountRulerLearningActivity extends Activity implements
		RulerNumberChosenListener {
	private Ruler ruler;
	private NumImageView nivNum;
	private ImageButton ibPhaseChangeRight;
	private ImageButton ibPhaseChangeLeft;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_count_ruler_learning);
		ruler = (Ruler) findViewById(R.id.ruler);
		ruler.addAfterRulerNumberChosen(this);
		nivNum = (NumImageView) findViewById(R.id.nivNum);
		nivNum.setNumValue(1);

		ibPhaseChangeLeft = (ImageButton) findViewById(R.id.ibPhaseChangeLeft);
		ibPhaseChangeRight = (ImageButton) findViewById(R.id.ibPhaseChangeRight);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.count_ruler_learning, menu);
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
	public void afterRulerNumberChosen(int newNumber) {
		nivNum.setNumValue(newNumber);
	}

	@SuppressWarnings("deprecation")
	public void onPhaseChooseClick(View view) {
		// change the phase
		ruler.nexPhase();

		// change image on the button
		if (ruler.isLastPhase()) {
			ibPhaseChangeRight.setImageDrawable(getResources().getDrawable(R.drawable.phase_onestar));
		} else {
			ibPhaseChangeRight.setImageDrawable(getResources().getDrawable(R.drawable.phase_twostar));
		}
	}
	
	public void onBackClick(View view){
		finish();
	}
}
