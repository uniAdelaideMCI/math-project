package au.edu.adelaide.mci.kidnumeracy;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

public class ImageAdapter extends BaseAdapter {

	private Context mContext;
	
	private ImageView[] imageViews; 
	
	private int maxValue;
	
	public ImageAdapter(Context c) {
        mContext = c;
        CountLearnActivity countLearnActivity = (CountLearnActivity)c;
        maxValue = countLearnActivity.getCountLearning().getMaxValue();
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

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
    	imageViews[position] = new ImageView(mContext);
    	imageViews[position].setImageResource(R.drawable.balloon1); 
    	return imageViews[position];
	}

}
