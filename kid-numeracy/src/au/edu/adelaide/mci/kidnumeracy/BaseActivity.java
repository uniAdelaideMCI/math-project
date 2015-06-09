package au.edu.adelaide.mci.kidnumeracy;

import android.app.Activity;
import android.util.Log;

/**
 * Base Activity for all activities for playing background information
 * @author Group 5
 *
 */
public class BaseActivity extends Activity {
	private final static String TAG = "au.edu.adelaide.mci.kidnumeracy.BaseActivity";
	@Override
	protected void onResume() {
		Log.i(TAG, this.getClass().getName() + ".onResume");
		super.onResume();
		SoundHandler.playMusicRandom();
	}

	@Override
	protected void onPause(){ 
		Log.i(TAG, this.getClass().getName() + ".onPause");		
		super.onPause();
		SoundHandler.pause();
	}

}
