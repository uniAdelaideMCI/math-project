/**
 * 
 */
package au.edu.adelaide.mci.kidnumeracy;

import java.io.Serializable;

/**
 * @author Yun
 *
 */
public class CountLearning implements Serializable {
	
	private static final long serialVersionUID = 422349641231512002L;

	private int currentValue = 0;
	
	private int step = 1;
	
	public int nextValue(){
		currentValue += step;
		return currentValue;
	}
	
	public int getCurrentVaue(){
		return currentValue;
	}
}
