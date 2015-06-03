package au.edu.adelaide.mci.kidnumeracy;

import android.annotation.SuppressLint;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.DragEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.DragShadowBuilder;
import android.view.View.OnDragListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class PuzzleTestTouchActivity extends BaseActivity {

	/**
	 * Listener for drag drop event
	 * @author Yun
	 *
	 */
	public class ImageViewDragListener implements OnDragListener {

		/* (non-Javadoc)
		 * @see android.view.View.OnDragListener#onDrag(android.view.View, android.view.DragEvent)
		 */
		@Override
		public boolean onDrag(View v, DragEvent event) {
			int action = event.getAction();
			switch (action) {
			case DragEvent.ACTION_DROP:
				if (event.getLocalState() != null){
					View origView = (View)event.getLocalState();
					Integer answer = (Integer)origView.getTag();
					if (v != null && v instanceof ImageView){
						ImageView imageView = (ImageView)v;
						//the position for the drop target
						int position = gridView.getPositionForView(v);
						boolean isCorrect = puzzleTest.answer(position,answer);
						if (isCorrect){
							origView.setVisibility(View.INVISIBLE);
						}
						adapter = new PuzzleTestImageAdapter();
						gridView.setAdapter(adapter);
					}
				}

				break;

			default:
				break;
			}
			return true;
		}

	}

	/**
	 * The on touch event listener for the numbers to be selected
	 * @author Yun
	 *
	 */
	@SuppressLint("ClickableViewAccessibility")
	public class NumOptionTouchListener implements OnTouchListener {

		/* (non-Javadoc)
		 * @see android.view.View.OnTouchListener#onTouch(android.view.View, android.view.MotionEvent)
		 */
		@Override
		public boolean onTouch(View v, MotionEvent event) {
			if (event.getAction() == MotionEvent.ACTION_DOWN){
				DragShadowBuilder dragShadowBuilder = new DragShadowBuilder(v);
				v.startDrag(null, dragShadowBuilder, v, 0);
				return true;	
			}
			return false;
		}

	}

	LinearLayout llMain;
	LinearLayout llNums;
	GridView gridView;
	private ImageView ivNum1;
	private ImageView ivNum2;
	private ImageView ivNum3;
	private PuzzleTest puzzleTest;
	private DrawNumberMapper drawNumberMapper;
	private DrawNumberMapper missingNumMapper;
	private PuzzleTestImageAdapter adapter;


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_count_test);
		
		init();

	}

	private void init() {
		puzzleTest = new PuzzleTest();
		drawNumberMapper = new DrawNumberMapper();
		missingNumMapper = new DrawNumberMapper();
		adapter = new PuzzleTestImageAdapter();

		gridView = (GridView) findViewById(R.id.gvPuzzle);
		gridView.setAdapter(adapter);
		// show the missing numbers
		int unorderedNums[] = puzzleTest.getMissNumsRandom();
		ivNum1 = (ImageView) findViewById(R.id.ivNum1);
		ivNum1.setOnTouchListener(new NumOptionTouchListener());
		ivNum1.setVisibility(View.VISIBLE);
		ivNum2 = (ImageView) findViewById(R.id.ivNum2);
		ivNum2.setOnTouchListener(new NumOptionTouchListener());
		ivNum2.setVisibility(View.VISIBLE);
		ivNum3 = (ImageView) findViewById(R.id.ivNum3);
		ivNum3.setOnTouchListener(new NumOptionTouchListener());
		ivNum3.setVisibility(View.VISIBLE);
		ivNum1.setImageDrawable(getDrawableByValue(unorderedNums[0]));
		ivNum1.setTag(unorderedNums[0]);
		missingNumMapper.register(unorderedNums[0], ivNum1.getDrawable());
		ivNum2.setImageDrawable(getDrawableByValue(unorderedNums[1]));
		ivNum2.setTag(unorderedNums[1]);
		missingNumMapper.register(unorderedNums[1], ivNum2.getDrawable());
		ivNum3.setImageDrawable(getDrawableByValue(unorderedNums[2]));
		ivNum3.setTag(unorderedNums[2]);
		missingNumMapper.register(unorderedNums[2], ivNum3.getDrawable());

		llMain = (LinearLayout) findViewById(R.id.ll_count_test_main);
		llNums = (LinearLayout) findViewById(R.id.ll_nums);
	}

	@SuppressWarnings("deprecation")
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
		}
		if (value >= 0 && value <= 20) {
			return getResources().getDrawable(resId);
		} else {
			return null;
		}

	}

	public class PuzzleTestImageAdapter extends BaseAdapter {

		private ImageView[] imageViews;

		public PuzzleTestImageAdapter() {
			imageViews = new ImageView[getCount()];
		}

		@Override
		public int getCount() {
			return puzzleTest.getNumCount();
		}

		@Override
		public Object getItem(int position) {
			return puzzleTest.getNum(position);
		}

		@Override
		public long getItemId(int position) {
			return puzzleTest.getNum(position).getRowNo();
		}

		@SuppressWarnings("deprecation")
		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			if (convertView != null && convertView instanceof ImageView){
				imageViews[position] = (ImageView)convertView;
			}else{
				imageViews[position] = new ImageView(PuzzleTestTouchActivity.this);
			}
			
			GridView.LayoutParams layoutParams = new  GridView.LayoutParams(
					parent.getWidth()/ 3, parent.getHeight() / 3);
			imageViews[position].setLayoutParams(layoutParams);
			imageViews[position].setScaleType(ImageView.ScaleType.CENTER_INSIDE);
			// if not missing or answered correctly
			PuzzleTest.PuzzleValue puzzleValue = puzzleTest.getNum(position);
			if (!puzzleValue.isOriginalMissing()
					|| (puzzleValue.isAnswered() && puzzleValue
							.isAnsweredCorrect())) {
				imageViews[position].setImageDrawable(PuzzleTestTouchActivity.this
						.getDrawableByValue(position + 1));
			} else if (puzzleValue.isAnswered()
					&& !puzzleValue.isAnsweredCorrect()) {
				imageViews[position].setImageDrawable(PuzzleTestTouchActivity.this
						.getResources().getDrawable(R.drawable.error));
			} else {
//				AnimationDrawable animationDrawable = new AnimationDrawable();
//				animationDrawable.addFrame(getResources().getDrawable(R.drawable.question_mark_red), 300);
//				animationDrawable.addFrame(getResources().getDrawable(R.drawable.question_mark_green), 300);
//				animationDrawable.addFrame(getResources().getDrawable(R.drawable.question_mark_blue), 300);
//				animationDrawable.setOneShot(false);
				imageViews[position].setImageDrawable(getResources().getDrawable(R.drawable.blank_mark));;
//				animationDrawable.start();
////				Animation blinkAnimation = AnimationUtils.loadAnimation(PuzzleTestTouchActivity.this, R.drawable.question_blink);
////				imageViews[position].startAnimation(blinkAnimation);
//				imageViews[position].setBackgroundResource(R.drawable.question_blink);
				imageViews[position].setOnDragListener(new ImageViewDragListener());
			}
			drawNumberMapper.register(position + 1,
					imageViews[position].getDrawable());
			parent.invalidate();
			
			return imageViews[position];

		}

		public void setImage(int num, Drawable drawable) {
			imageViews[num - 1].setImageDrawable(drawable);
			drawNumberMapper.register(num, drawable);
		}

	}


//	public void onBtnNumChoose(View view) {
//		// get the number corresponding to the button
//		ImageView imageView = (ImageView) view;
//		// the num is being touched
//		int num = missingNumMapper.getNum(imageView.getDrawable());
//		if (puzzleTest.hasMissing()
//				&& puzzleTest.getCurrentMissIndex() == currentMissingIndex) {
//			puzzleTest.answer(num);
//			puzzleTest.nextMissingNum();
//			adapter = new PuzzleTestImageAdapter();
//			gridView.setAdapter(adapter);
//		}
//
//	}
	
	public void onBackClick(View view){
		finish();
	}
	
	public void onRestartClick(View view){
		init();
	}
}
