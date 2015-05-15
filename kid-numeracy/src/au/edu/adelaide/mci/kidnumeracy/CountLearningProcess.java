package au.edu.adelaide.mci.kidnumeracy;

import java.util.LinkedList;
import java.util.List;

/**
 * Define Count Learning Process
 * @author Yun
 *
 */
public class CountLearningProcess {
	private List<CountLearningPhase> mPhases = new LinkedList<CountLearningPhase>();
	private int mRepeatTimes = 1;
	public int getRepeatTimes() {
		return mRepeatTimes;
	}
}
