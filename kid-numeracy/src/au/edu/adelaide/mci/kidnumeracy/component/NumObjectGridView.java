package au.edu.adelaide.mci.kidnumeracy.component;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Random;
import java.util.Set;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.AttributeSet;
import android.view.DragEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import au.edu.adelaide.mci.kidnumeracy.NumberListener;
import au.edu.adelaide.mci.kidnumeracy.R;

public class NumObjectGridView extends GridView {
	private class ImageViewDragListener implements OnDragListener {
		//Drawable enterShape = getResources().getDrawable(R.drawable.shape_droptarget);
		@Override
		public boolean onDrag(View v, DragEvent event) {
			
			int action = event.getAction();
			switch (action) {
			case DragEvent.ACTION_DRAG_STARTED:
				//do nothing
				break;
			case DragEvent.ACTION_DRAG_ENTERED:
				//TODO: v.setBackgroundDrawable(enterShape);
				break;
		    case DragEvent.ACTION_DRAG_EXITED:        
		        v.setBackgroundDrawable(null);
		        break;				
		    case DragEvent.ACTION_DROP:
		    	View origView = (View)event.getLocalState();
		    	if (origView != null && v.getParent() != origView.getParent()){
			        int position = getPositionForView(v);
			        increase(position);
		    	}
		        break;
		    case DragEvent.ACTION_DRAG_ENDED:
		    	break;
			default:
				break;
			}
			return true;
		}

	}

	/**
	 * The touch event for image vies
	 * @author Yun
	 *
	 */
	private class ImageViewTouchListener implements OnTouchListener {

		@SuppressLint("ClickableViewAccessibility")
		@Override
		public boolean onTouch(View view, MotionEvent motionEvent) {
			if (opMode == OP_MODE_DRAG_N_DROP){
				
				if (motionEvent.getAction() == MotionEvent.ACTION_DOWN){
					//store the original position in data
					int position = getPositionForView(view);
					
					DragShadowBuilder shadowBuilder = new View.DragShadowBuilder(view);
					//drag data contains the position information
					droppedPosition = position;
					view.startDrag(null, shadowBuilder, view, 0);
					return true;
				}				
			}
			return false;
		}

	}
	
	
	/**
	 * The click event listener for the whole grid view
	 */
	private OnClickListener onClickListener;

	//The position and corresponding image
	private Map<Integer,ImageView> positions = new HashMap<Integer,ImageView>();
	
	/**
	 * The map between position and value
	 */
	private Map<Integer,Integer> positionValueMap = new HashMap<Integer,Integer>();
	
	private Set<NumberListener> numListeners = new HashSet<NumberListener>();
	
	private static final int resIds[] = {R.drawable.apple_correct,R.drawable.garfield,
		R.drawable.hello_kitty,R.drawable.hippo,R.drawable.kangroo,R.drawable.monkey,R.drawable.orange};
	private static final int resIds2[] = {R.drawable.apple_correct2,R.drawable.garfield2,
		R.drawable.hello_kitty2,R.drawable.hippo2,R.drawable.kangroo2,R.drawable.monkey2,R.drawable.orange2};
	
	private Random random = new Random(System.currentTimeMillis());
	
	//0 read-only mode 1 tap mode 2 drop and drag mode
	public final static int OP_MODE_READ_ONLY = 0;
	public final static int OP_MODE_TAP = 1;
	public final static int OP_MODE_DRAG_N_DROP = 2;	
	private int opMode = 1;
	
	public int getOpMode() {
		return opMode;
	}

	public void setOpMode(int opMode) {
		this.opMode = opMode;
	}


	//This project does not need 0
	private int numValue = 0;
	
	private int maxNumValue = 1;

	public int getMaxNumValue() {
		return maxNumValue;
	}


	private int minValue = 0;
	
	private int resIdIndex = -1;
	
	private int droppedPosition = -1;
	
	public int getRandomResIdIndex(boolean forceUpdate) {
		if (resIdIndex == -1){
			resIdIndex = random.nextInt(resIds.length);
		}else{
			if (forceUpdate){
				resIdIndex = random.nextInt(resIds.length);
			}
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
	
	public int getRowCount(){
		return getMaxNumValue() / getNumColumns();
	}
	
	public int getCurResId(){
		if (resIdIndex == -1){
			resIdIndex = getRandomResIdIndex(false);
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

	/**
	 * Set the num value of the grid view
	 * @param numValue
	 */
	public void setNumValue(int numValue) {
			positions.clear();
			for (int i = 0 ; i < numValue ; i++){
				positions.put(i, null);
			}
			this.numValue = numValue;
			setAdapter(new NumObjectImageAdapter());
	}
	
	public void setNumValue(int numValue,boolean random) {
		if (random){
			positions.clear();
			//set image position randomly
			ArrayList<Integer> arrayList = new ArrayList<Integer>();
			for (int i = 0 ; i < maxNumValue ; i++){
				arrayList.add(i);
			}
			for(int i = 0 ; i < numValue ; i++){
				//the randomly generated index
				Integer index = arrayList.remove(this.random.nextInt(arrayList.size()));
				positions.put(index, null);
			}
			
			this.numValue = numValue;
			setAdapter(new NumObjectImageAdapter());
		}else{
			setNumValue(numValue);
		}
}	


	public void decreaseValue(int position) {
		if (numValue > minValue ){
			positions.remove(position);
			numValue--;
			setAdapter(new NumObjectImageAdapter());			
			fireAfterNumChangedEvent(numValue + 1);			
		}
	}
	
	public void decreaseDroppedValue() {
		if (droppedPosition != -1){
			decreaseValue(droppedPosition);
			droppedPosition = -1;
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
			increase(position);
			return position;
		}else{
			return -1;
		}
	}
	
	/**
	 * Increase value by 1 and add object to the cell indicated by position
	 * @param position
	 * @return
	 */
	public int increase(int position){
		if (numValue < maxNumValue){
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
				imageView.setOnTouchListener(new ImageViewTouchListener());
			}else{
				imageView.setImageDrawable(null);
				//the blank cell could be drop target
				imageView.setOnDragListener(new ImageViewDragListener());
			}
			
			
			int columnCount = NumObjectGridView.this.getNumColumns();
			int rowCount = (int)Math.ceil(getCount() / (double)columnCount);
			
			imageView.setLayoutParams(new GridView.LayoutParams(parent.getWidth()/columnCount, parent.getHeight() / rowCount ));
			imageView.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					if (opMode == OP_MODE_TAP){
						ImageView iv = (ImageView)v;
						if (iv.getDrawable() != null){
							int position = iv.getId();
							if (position != -1){
								decreaseValue(position);
							}
						}						
					}
					if (onClickListener != null){
						onClickListener.onClick(NumObjectGridView.this);
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

	/**
	 * Set click Listener for the whole component. note that this method differs from setOnClickListener
	 *  which is not allowed to use.
	 * @param onClickListener
	 */
	public void setClickHander(OnClickListener onClickListener) {
		this.onClickListener = onClickListener;
	}

}
