package au.edu.adelaide.mci.kidnumeracy;

/**
 * @author Yun
 *
 */
import java.util.Random;

import android.content.Context;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;

public class SoundHandler {
    private static MediaPlayer backgroundMusic = null;
    private static Random random = new Random(System.currentTimeMillis());
    private static Context context;
    private final static int resIds[] = {R.raw.bgm_carousel,R.raw.bgm_dance_of_firebug
    	, R.raw.bgm_fantacy_of_kanong, R.raw.bgm_happy_time
    	, R.raw.bgm_mickey, R.raw.bgm_moving_in, R.raw.bgm_snow_dream,R.raw.bgm_unkown1};

    private static boolean isMuted = false;

    public static void setContext(Context cont){
        context = cont;
    }
    
    private static int getRandomResId(){
    	return resIds[random.nextInt(resIds.length)];
    }

    public static void playMusic(int resource){

        try{
        	if (backgroundMusic == null){
        		backgroundMusic = MediaPlayer.create(context, resource);
        		backgroundMusic.setLooping(true);
        	}
            
            if(!isMuted && !backgroundMusic.isPlaying()){
            	backgroundMusic.start();
            }
        } catch (NullPointerException e){
            //Creating MediaPlayer failed. This happens randomly without any clear reasons.
            e.printStackTrace();
        }
    }
    
	public static void playMusicRandom() {
        try{
        	if (backgroundMusic == null){
        		backgroundMusic = MediaPlayer.create(context, getRandomResId());
        		backgroundMusic.setLooping(false);
        		backgroundMusic.setOnCompletionListener(new OnCompletionListener() {
					
					@Override
					public void onCompletion(MediaPlayer mp) {
						backgroundMusic.release();
						backgroundMusic = null;
						playMusicRandom();
					}
				});
        	}
            
            if(!isMuted && !backgroundMusic.isPlaying()){
            	backgroundMusic.start();
            }
        } catch (NullPointerException e){
            //Creating MediaPlayer failed. This happens randomly without any clear reasons.
            e.printStackTrace();
        }
	}

    public static void setMuted(boolean muted){
        if(backgroundMusic != null){
            if(muted){
                if(backgroundMusic.isPlaying()){
                    backgroundMusic.stop();
                    isMuted = true;
                }
            } else {
                if(!backgroundMusic.isPlaying()){
                    backgroundMusic.start();
                    isMuted = false;
                }
            }
        }
    }

    public static void quit(){
        if(backgroundMusic != null){
        	backgroundMusic.stop();
            backgroundMusic.release();
            backgroundMusic = null;
        }
    }

	public static void pause() {
        if(backgroundMusic != null && backgroundMusic.isPlaying()){
        	backgroundMusic.pause();
        }
	}

}
