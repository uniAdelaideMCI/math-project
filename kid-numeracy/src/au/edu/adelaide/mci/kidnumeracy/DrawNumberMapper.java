package au.edu.adelaide.mci.kidnumeracy;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;

import android.annotation.SuppressLint;
import android.graphics.drawable.Drawable;

/**
 * the mapping of drawalbes and numbers
 * @author Yun
 *
 */
@SuppressLint("UseSparseArrays")
public class DrawNumberMapper {
	private HashMap<Integer,Drawable> map = new HashMap<Integer,Drawable>();
	
	public void register(int num, Drawable drawable){
		map.put(num, drawable);
	}
	public Drawable getDrawable(int num){
		return map.get(num);
	}
	
	public int getNum(Drawable drawable){
		Iterator<Entry<Integer, Drawable>> it = map.entrySet().iterator();
		while(it.hasNext()){
			Entry<Integer, Drawable> entry = it.next();
			if (entry.getValue() == drawable){
				return entry.getKey();
			}
		}
		return -1;
	}
}
