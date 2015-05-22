package au.edu.adelaide.mci.kidnumeracy.component;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import au.edu.adelaide.mci.kidnumeracy.R;

public class NumObjectGridView extends GridView {
	
	//This project does not need 0
	private int numValue = 1;
	
	public int getNumValue() {
		return numValue;
	}

	public void setNumValue(int numValue) {
		this.numValue = numValue;
		
		setAdapter(new NumObjectImageAdapter());
	}

	private ImageView imageView = new ImageView(getContext());
	
	public NumObjectGridView(Context context) {
		super(context);
		this.setAdapter(new NumObjectImageAdapter());
		imageView.setImageDrawable(this.getResources().getDrawable(R.drawable.apple_correct));
	}

	public NumObjectGridView(Context context, AttributeSet attrs) {
		super(context, attrs);
		this.setAdapter(new NumObjectImageAdapter());
		imageView.setImageDrawable(this.getResources().getDrawable(R.drawable.apple_correct));		
	}
	
	private class NumObjectImageAdapter extends BaseAdapter{

		public NumObjectImageAdapter() {
			super();
			// TODO Auto-generated constructor stub
		}



		@Override
		public int getCount() {
			return numValue;
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

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			ImageView imageView = new ImageView(getContext());
			imageView.setImageDrawable(getContext().getResources().getDrawable(R.drawable.tree));
			int columnCount = NumObjectGridView.this.getNumColumns();
			int rowCount = (int)Math.ceil(getCount() / (double)columnCount);
			
			imageView.setLayoutParams(new GridView.LayoutParams(parent.getWidth()/columnCount, parent.getHeight() / rowCount ));
			return imageView;
		}
		
	}
}
