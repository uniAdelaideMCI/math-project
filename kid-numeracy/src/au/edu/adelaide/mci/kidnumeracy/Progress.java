package au.edu.adelaide.mci.kidnumeracy;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Learning progress info
 * @author Group 5
 *
 */
public interface Progress {
	public int getPhaseNo();
	public int getQuestionCount();
	public int getAnsweredCount();
	public void setPhaseNo(int phaseNo);
	public void setQuestionCount(int QuestionCount);
	public void setAnsweredCount(int answeredCount);	
	public double getAnsweredPercentage();
	public JSONObject toJSON() throws JSONException;
}
