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
	
	private int step = 1;
	
	private Set<NumberListener> numberListeners = new HashSet<NumberListener>();
	
	public int nextValue(){
		currentValue += step;
		fireNumChangedEvent();
		return currentValue;
	}
	
	private void fireNumChangedEvent() {
		for (NumberListener numberListener : numberListeners) {
			numberListener.numberChanged();
		}		
	}

	public int getCurrentVaue(){
		return currentValue;
	}
}
