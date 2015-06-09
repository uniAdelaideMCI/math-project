package au.edu.adelaide.mci.kidnumeracy;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

/**
 * ImageAdapter for gridview which displays objects
 * @author Group 5
 *
 */
public class ImageAdapter extends BaseAdapter {
	private int[] resIds={R.drawable.apple_correct,
		R.drawable.balloon1,R.drawable.cat,R.drawable.garfield,
		R.drawable.hello_kitty,R.drawable.hippo,R.drawable.orange,R.drawable.tree};
	private Drawable[] drawables = new Drawable[resIds.length];
	private Random random = new Random(System.currentTimeMillis());
	
	private Context mContext;
	
	private CountLearnActivity countLearnActivity;
	
	private ImageView[] imageViews; 
	
	private int firstPicIndex;
	
	private CountLearning countLearning;
	
	//MediaPlayer mediaPlayer;
	
	private final static int FRAME_DELAY = 150;
	
	private Map<ImageView,Integer> counterNums = new HashMap<ImageView,Integer>(); 
	
	private Map<Integer,Drawable> numDrawables = new HashMap<Integer,Drawable>();
	
	private int maxValue;

	private boolean isNewStart = true;
	
	public ImageAdapter(Context c) {
        mContext = c;
        countLearnActivity = (CountLearnActivity)c;
        countLearning = countLearnActivity.getCountLearning();
        firstPicIndex = random.nextInt(resIds.length);
        //mediaPlayer = MediaPlayer.create(countLearnActivity, R.raw.bubble_explode);
//        try {
//			mediaPlayer.prepare();
//		} catch (IllegalStateException e) {
//			e.printStackTrace();
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
        
        maxValue = countLearnActivity.getCountLearning().getMaxValue();
        for(int i = 0 ; i <= maxValue ; i++){
        	numDrawables.put(i, getDrawableByValue(i));
        }
        imageViews = new ImageView[maxValue];
    }	
	
	@Override
	public int getCount() {
		return maxValue;
	}

	@Override
	public Object getItem(int position) {
		return null;
	}

	@Override
	public long getItemId(int position) {
		return 0;
	}
	
	private Drawable getDrawableByValue(int value) {
		int resId = R.drawable.n0;
		switch (value) {
		case 0:
			resId = R.drawable.n0;
			break;		
		case 1:
			resId = R.drawable.n1;
			break;
		case 2:
			resId = R.drawable.n2;
			break;
		case 3:
			resId = R.drawable.n3;
			break;
		case 4:
			resId = R.drawable.n4;
			break;
		case 5:
			resId = R.drawable.n5;
			break;	
		case 6:
			resId = R.drawable.n6;
			break;
		case 7:
			resId = R.drawable.n7;
			break;
		case 8:
			resId = R.drawable.n8;
			break;
		case 9:
			resId = R.drawable.n9;
			break;
		case 10:
			resId = R.drawable.n10;
			break;
		case 11:
			resId = R.drawable.n11;
			break;
		case 12:
			resId = R.drawable.n12;
			break;
		case 13:
			resId = R.drawable.n13;
			break;
		case 14:
			resId = R.drawable.n14;
			break;
		case 15:
			resId = R.drawable.n15;
			break;
		case 16:
			resId = R.drawable.n16;
			break;
		case 17:
			resId = R.drawable.n17;
			break;
		case 18:
			resId = R.drawable.n18;
			break;	
		case 19:
			resId = R.drawable.n19;
			break;
		case 20:
			resId = R.drawable.n20;
			break;				
		default:
			break;
		}
		
		return mContext.getResources().getDrawable(resId);
	}	

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {

    	imageViews[position] = new ImageView(mContext);
    	//imageViews[position].setImageResource(R.drawable.balloon1);

    	AnimationDrawable animationDrawable = new AnimationDrawable();
    	//Drawable balloon1 = mContext.getResources().getDrawable(R.drawable.apple_correct);
    	
    	animationDrawable.addFrame(getDrawableByIndex(firstPicIndex), FRAME_DELAY);
//    	Drawable balloon2 = mContext.getResources().getDrawable(R.drawable.balloon2);
//    	animationDrawable.addFrame(balloon2, FRAME_DELAY);
//    	Drawable balloon3 = mContext.getResources().getDrawable(R.drawable.balloon3);
//    	animationDrawable.addFrame(balloon3, FRAME_DELAY);
    	animationDrawable.setOneShot(true);
    	imageViews[position].setImageDrawable(animationDrawable);
    	imageViews[position].setLayoutParams(new GridView.LayoutParams(parent.getWidth()/5,parent.getHeight()/4));
    	imageViews[position].setScaleType(ImageView.ScaleType.FIT_CENTER);
    	animationDrawable.stop();
    	imageViews[position].setOnClickListener(new View.OnClickListener() {			
			@Override
			public void onClick(View v) {
				if (countLearnActivity.isViewChange()){
					countLearnActivity.updateView();
				}else{
					if (v != null && v instanceof ImageView){
						ImageView imageView = (ImageView)v;
						Drawable drawable = imageView.getDrawable();
						if (drawable instanceof AnimationDrawable){
							AnimationDrawable  animationDrawable = (AnimationDrawable)drawable;
							if (counterNums.get(imageView) == null){
								int newValue = countLearning.getCurrentVaue();
								if (isNewStart){
									isNewStart = false;
								}else{
									newValue = countLearning.nextValue();
								}
								
								counterNums.put(imageView,newValue);
								animationDrawable.addFrame(getCachedDrawableByValue(newValue), FRAME_DELAY);
								animationDrawable.start();							
							}
						}
					}						
				}

			}

			private Drawable getCachedDrawableByValue(int value) {
				return numDrawables.get(value);
			}
		});
    	return imageViews[position];
	}

	private Drawable getDrawableByIndex(int index) {
		return mContext.getResources().getDrawable(resIds[index]);
	}

}
