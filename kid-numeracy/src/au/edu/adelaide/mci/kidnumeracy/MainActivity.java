package au.edu.adelaide.mci.kidnumeracy;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import android.view.View;

@SuppressWarnings("deprecation")
public class MainActivity extends BaseActivity {
	private final String TAG = "au.edu.adelaide.mci.kidnumeracy.MainActivity";
	private boolean mIsBound = false;
	private MusicService mServ;
	private ServiceConnection Scon =new ServiceConnection(){

		public void onServiceConnected(ComponentName name, IBinder
	     binder) {
			mServ = ((MusicService.ServiceBinder)binder).getService();
		}

		public void onServiceDisconnected(ComponentName name) {
			mServ = null;
		}
		};

		void doBindService(){
	 		bindService(new Intent(this,MusicService.class),
					Scon,Context.BIND_AUTO_CREATE);
			mIsBound = true;
		}

		void doUnbindService()
		{
			if(mIsBound)
			{
				unbindService(Scon);
	      		mIsBound = false;
			}
		}
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		//bind service to play background music
//		doBindService();
//		Intent music = new Intent();
//		music.setClass(this,MusicService.class);
//		startService(music);
		SoundHandler.setContext(this);
	}
	
	public void onBtnCountClick(View v){
		//Intent intent = new Intent(MainActivity.this,CountLearnActivity.class);
		Intent intent = null;
		if (v.getId() == R.id.btnCountObject){
			intent = new Intent(MainActivity.this,CountUpDownLearnActivity.class);
		}else{
			intent = new Intent(MainActivity.this,CountRulerLearningActivity.class);
		}
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
		//Intent intent = new Intent(this,CountTestActivity.class);
		Intent intent = new Intent(this,PuzzleTestTouchActivity.class);
		startActivity(intent);
	}

	@Override
	protected void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);
	}

	@Override
	protected void onDestroy() {
		Log.d(TAG, "onDestroy");
		super.onDestroy();
//		doUnbindService();
	}

	@Override
	protected void onPause() {
		Log.d(TAG, "onPause");
		super.onPause();
	}

	@Override
	protected void onResume() {
		Log.d(TAG, "onResume");
		super.onResume();
	}

	@Override
	protected void onRestart() {
		Log.d(TAG, "onRestart");
		super.onRestart();
	}

	@Override
	protected void onStop() {
		// TODO Auto-generated method stub
		super.onStop();
		Log.i(TAG, "onStop");
	}
	
	

}
