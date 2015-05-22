/**
 * 
 */
package au.edu.adelaide.mci.kidnumeracy;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.widget.ImageView;

/**
 * @author Yun
 *
 */
public class NumImageView extends ImageView {
	private Context context;
	
	//the number this image view represents
	private int numValue = 0;
	
	public NumImageView(Context context) {
		super(context);
		this.context = context;
		this.setImageDrawable(context.getResources().getDrawable(R.drawable.n0));
	}

	public NumImageView(Context context, AttributeSet attrs) {
		super(context, attrs);
		this.context = context;
		this.setImageDrawable(context.getResources().getDrawable(R.drawable.n0));
	}
	
	public int getNumValue() {
		return numValue;
	}

	public void setNumValue(int numValue) {
		if (numValue >= 0 && numValue <= 20){
			this.numValue = numValue;
			setImageDrawable(getDrawableByValue(numValue));
		}else{
			throw new IllegalArgumentException(String.format("Illegal Value : %d, value must be between 0 and 20.",numValue));
		}
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
		if (value >= 0 && value <=20){
			return context.getResources().getDrawable(resId);
		}else{
			return null;
		}

	}	
	
}
