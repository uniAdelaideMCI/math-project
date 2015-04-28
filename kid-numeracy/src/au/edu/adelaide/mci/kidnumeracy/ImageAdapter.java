package au.edu.adelaide.mci.kidnumeracy;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

public class ImageAdapter extends BaseAdapter {

	private Context mContext;
	
	public ImageAdapter(Context c) {
        mContext = c;
    }	
	
	@Override
	public int getCount() {
		MainActivity mainActivity = (MainActivity)mContext;
		return mainActivity.getCountLearning().getCurrentVaue();
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
        ImageView imageView = new ImageView(mContext);
        imageView.setImageResource(R.drawable.apple_red);
        imageView.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
        imageView.setLayoutParams(new GridView.LayoutParams(70, 70));
        return imageView;
	}

}
