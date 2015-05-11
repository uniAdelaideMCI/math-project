package au.edu.adelaide.mci.kidnumeracy;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * @author Yun
 *
 */
public class CountLearning implements Serializable {
	
	private static final long serialVersionUID = 422349641231512002L;

	private int currentValue = 0;
	
	private int mMaxValue = 20;
	
	public int getMaxValue() {
		return mMaxValue;
	}

	public void setMaxValue(int maxValue) {
		mMaxValue = maxValue;
	}

	private int step = 1;
	
	private Set<NumberListener> numberListeners = new HashSet<NumberListener>();
	
	public void addNumberListener(NumberListener numberListener){
		numberListeners.add(numberListener);
	}
	
	public int nextValue(){
		if (currentValue < mMaxValue){
			currentValue += step;
			fireNumChangedEvent();			
		}else{
			fireNumFinishedEvent();
		}
		return currentValue;
	}
	
	private void fireNumChangedEvent() {
		for (NumberListener numberListener : numberListeners) {
			numberListener.numberChanged();
		}		
	}
	
	private void fireNumFinishedEvent() {
		for (NumberListener numberListener : numberListeners) {
			numberListener.numberFinished();
		}		
	}	

	public int getCurrentVaue(){
		return currentValue;
	}

	public void reset() {
		currentValue = 0;		
	}
}
