package au.edu.adelaide.mci.kidnumeracy;

import android.app.Activity;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class CountTestActivity extends Activity {

	LinearLayout llMain;
	LinearLayout llNums;
	GridView gridView;
	private ImageView ivNum1;
	private ImageView ivNum2;
	private ImageView ivNum3;
	private PuzzleTest puzzleTest = new PuzzleTest();
	private DrawNumberMapper drawNumberMapper = new DrawNumberMapper();
	private DrawNumberMapper missingNumMapper = new DrawNumberMapper();
	private PuzzleTestImageAdapter adapter = new PuzzleTestImageAdapter();

	private Drawable questionDrawable = null;
	private int currentMissingIndex = 0;

	private Drawable drawables[];

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_count_test);

		drawables = new Drawable[21];// The variable used to buffer number
										// images

		gridView = (GridView) findViewById(R.id.gvPuzzle);
		gridView.setAdapter(adapter);
		// show the missing numbers
		int unorderedNums[] = puzzleTest.getMissNumsRandom();
		ivNum1 = (ImageView) findViewById(R.id.ivNum1);
		ivNum2 = (ImageView) findViewById(R.id.ivNum2);
		ivNum3 = (ImageView) findViewById(R.id.ivNum3);
		ivNum1.setImageDrawable(getDrawableByValue(unorderedNums[0]));
		missingNumMapper.register(unorderedNums[0], ivNum1.getDrawable());
		ivNum2.setImageDrawable(getDrawableByValue(unorderedNums[1]));
		missingNumMapper.register(unorderedNums[1], ivNum2.getDrawable());
		ivNum3.setImageDrawable(getDrawableByValue(unorderedNums[2]));
		missingNumMapper.register(unorderedNums[2], ivNum3.getDrawable());

		llMain = (LinearLayout) findViewById(R.id.ll_count_test_main);
		llNums = (LinearLayout) findViewById(R.id.ll_nums);

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
		}
		if (value >= 0 && value <= 20) {
			// if (drawables[value] == null){
			// drawables[value] = getResources().getDrawable(resId);
			// }
			// return drawables[value - 1];
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

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {

			imageViews[position] = new ImageView(CountTestActivity.this);
			imageViews[position].setLayoutParams(new GridView.LayoutParams(
					parent.getWidth() / 3, parent.getHeight() / 3));
			// if not missing or answered correctly
			PuzzleTest.PuzzleValue puzzleValue = puzzleTest.getNum(position);
			if (!puzzleValue.isOriginalMissing()
					|| (puzzleValue.isAnswered() && puzzleValue
							.isAnsweredCorrect())) {
				imageViews[position].setImageDrawable(CountTestActivity.this
						.getDrawableByValue(position + 1));
			} else if (puzzleValue.isAnswered()
					&& !puzzleValue.isAnsweredCorrect()) {
				imageViews[position].setImageDrawable(CountTestActivity.this
						.getResources().getDrawable(R.drawable.error));
			} else if (puzzleTest.isCurrentMissing(puzzleValue)) { // not
																	// answered
				imageViews[position].setImageDrawable(CountTestActivity.this
						.getResources().getDrawable(R.drawable.question_mark));
			} else {
				imageViews[position].setImageDrawable(CountTestActivity.this
						.getResources().getDrawable(R.drawable.blank_mark));
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


	public void onBtnNumChoose(View view) {
		// get the number corresponding to the button
		ImageView imageView = (ImageView) view;
		// the num is being touched
		int num = missingNumMapper.getNum(imageView.getDrawable());
		if (puzzleTest.hasMissing()
				&& puzzleTest.getCurrentMissIndex() == currentMissingIndex) {
			PuzzleTest.PuzzleValue missingNum = puzzleTest.currentMissingNum();
			boolean isCorrect = puzzleTest.answer(num);
			currentMissingIndex++;
			puzzleTest.nextMissingNum();
			adapter = new PuzzleTestImageAdapter();
			gridView.setAdapter(adapter);
		}

	}

	private Drawable getQuestionDrawable() {
		if (questionDrawable == null) {
			questionDrawable = this.getResources().getDrawable(
					R.drawable.question_mark);
		}
		return questionDrawable;
	}
}
