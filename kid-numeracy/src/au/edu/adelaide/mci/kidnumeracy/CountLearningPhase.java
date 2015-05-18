/**
 * 
 */
package au.edu.adelaide.mci.kidnumeracy;

/**
 * Define each phase for count learning
 * @author Yun
 *
 */
public class CountLearningPhase {
	public static final String PROP_PHASE_NO = "phaseNo";
	public static final String PROP_MIN_VALUE = "minValue";
	public static final String PROP_MAX_VALUE = "maxValue";	
	
	public CountLearningPhase(int phaseNo, int minValue, int maxValue) {
		super();
		mPhaseNo = phaseNo;
		mMinValue = minValue;
		mMaxValue = maxValue;
	}
	private int mPhaseNo;
	private int mMinValue;
	private int mMaxValue;
	public int getPhaseNo() {
		return mPhaseNo;
	}
	public int getMinValue() {
		return mMinValue;
	}
	public int getMaxValue() {
		return mMaxValue;
	}
}
