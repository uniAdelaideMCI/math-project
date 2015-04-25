/**
 * 
 */
package au.edu.adelaide.mci.kidnumeracy;

/**
 * @author Yun
 *
 */
public class CountLearning {
	
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
