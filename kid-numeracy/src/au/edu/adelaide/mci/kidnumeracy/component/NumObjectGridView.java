package au.edu.adelaide.mci.kidnumeracy.component;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Random;
import java.util.Set;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import au.edu.adelaide.mci.kidnumeracy.NumberListener;
import au.edu.adelaide.mci.kidnumeracy.R;

public class NumObjectGridView extends GridView {
	//The position and corresponding image
	private Map<Integer,ImageView> positions = new HashMap<Integer,ImageView>();
	
	private Set<NumberListener> numListeners = new HashSet<NumberListener>();
	
	private static final int resIds[] = {R.drawable.apple_correct,R.drawable.garfield,
		R.drawable.hello_kitty,R.drawable.hippo,R.drawable.kangroo,R.drawable.monkey,R.drawable.orange};
	private static final int resIds2[] = {R.drawable.apple_correct2,R.drawable.garfield2,
		R.drawable.hello_kitty2,R.drawable.hippo2,R.drawable.kangroo2,R.drawable.monkey2,R.drawable.orange2};
	
	private Random random = new Random(System.currentTimeMillis());
	
	//This project does not need 0
	private int numValue = 0;
	
	private int maxNumValue = 1;

	private int minValue = 1;
	
	private int resIdIndex = -1;
	
	public int getRandomResIdIndex() {
		if (resIdIndex == -1){
			resIdIndex = random.nextInt(resIds.length);
		}
		return resIdIndex;
	}


	//1 old style type
	//2 new style type
	private int styleType = 1;

	public int getStyleType() {
		return styleType;
	}

	public void setStyleType(int styleType) {
		this.styleType = styleType;
	}

	public int getNumValue() {
		return numValue;
	}
	
	public int getCurResId(){
		if (resIdIndex == -1){
			resIdIndex = getRandomResIdIndex();
		}
		if (styleType == 1){
			return resIds[resIdIndex];
		}else{
			return resIds2[resIdIndex];
		}
	}
	
	public void addNumChangedListener(NumberListener listener){
		numListeners.add(listener);
	}
	
	private void fireAfterNumChangedEvent(int oldValue){
		for (NumberListener listener : numListeners) {
			listener.afterNumChanged(oldValue);
		}
	}

	public void setNumValue(int numValue) {
			for (int i = 0 ; i < numValue ; i++){
				positions.put(i, null);
			}
			this.numValue = numValue;
			setAdapter(new NumObjectImageAdapter());


	}


	private void decreaseValue(int position) {
		if (numValue > minValue ){
			positions.remove(position);
			numValue--;
			setAdapter(new NumObjectImageAdapter());			
			fireAfterNumChangedEvent(numValue + 1);			
		}
	}
	
	public int increase(boolean randomPos){
		if (numValue < maxNumValue){
			int position = 0;
			ArrayList<Integer> list = new ArrayList<Integer>();
			for(int i = 0 ; i < maxNumValue ; i++){
				if (!positions.containsKey(i)){
					list.add(i);
				}
			}
			
			if (randomPos){
				position = list.get(random.nextInt(list.size()));
			}else{
				Collections.sort(list);
				position = list.get(0);
			}
			numValue++;
			positions.put(position, null);
			setAdapter(new NumObjectImageAdapter());
			fireAfterNumChangedEvent(numValue - 1);	
			return position;
		}else{
			return -1;
		}
	}	
	
	public NumObjectGridView(Context context) {
		super(context);
		this.setAdapter(new NumObjectImageAdapter());
	}

	public NumObjectGridView(Context context, AttributeSet attrs) {
		super(context, attrs);
		this.setAdapter(new NumObjectImageAdapter());
	}
	
	private class NumObjectImageAdapter extends BaseAdapter{

		public NumObjectImageAdapter() {
			super();
			// TODO Auto-generated constructor stub
		}



		@Override
		public int getCount() {
			return maxNumValue;
		}
		
		

		@Override
		public Object getItem(int position) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public long getItemId(int position) {
			// TODO Auto-generated method stub
			return 0;
		}

		@SuppressWarnings("deprecation")
		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			ImageView imageView;
    		imageView = new ImageView(getContext());
    		imageView.setId(position);


			if (positions.containsKey(position)){
				imageView.setImageDrawable(getContext().getResources().getDrawable(getCurResId()));
				positions.put(position, imageView);
			}else{
				imageView.setImageDrawable(null);
			}
			
			
			int columnCount = NumObjectGridView.this.getNumColumns();
			int rowCount = (int)Math.ceil(getCount() / (double)columnCount);
			
			imageView.setLayoutParams(new GridView.LayoutParams(parent.getWidth()/columnCount, parent.getHeight() / rowCount ));
			imageView.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					ImageView iv = (ImageView)v;
					if (iv.getDrawable() != null){
						int position = iv.getId();
						if (position != -1){
							decreaseValue(position);
							
						}
					}
				}

				/**
				 * @param iv
				 * @return
				 */
				private int getImagePostion(ImageView iv) {
					for (Map.Entry<Integer, ImageView> entry : positions.entrySet()) {
						if (entry.getValue() == iv){
							return entry.getKey();
						}
					}
					return -1;
				}
			});
			return imageView;
		}
		
	}

	public void setMaxValue(int max) {
		maxNumValue = max;		
	}

	public void setResIdIndex(int resIndex) {
		this.resIdIndex = resIndex;		
	}
}
