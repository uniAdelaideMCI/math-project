/**
 * 
 */
package au.edu.adelaide.mci.kidnumeracy;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * @author Yun
 *
 */
public class JsonProgress implements Progress {
	private static final String JSON_PHASE_NO = "phaseNo";
	private static final String JSON_QUESTION_COUNT = "questionCount";
	private static final String JSON_ANSWERED_COUNT = "answeredCount";
	
	private int mPhaseNo;
	private int mQuestionCount;
	private int mAnsweredCount;
	
	/* (non-Javadoc)
	 * @see au.edu.adelaide.mci.kidnumeracy.Progress#getPhaseNo()
	 */
	@Override
	public int getPhaseNo() {
		return mPhaseNo;
	}

	/* (non-Javadoc)
	 * @see au.edu.adelaide.mci.kidnumeracy.Progress#getQuestionCount()
	 */
	@Override
	public int getQuestionCount() {
		return mQuestionCount;
	}

	/* (non-Javadoc)
	 * @see au.edu.adelaide.mci.kidnumeracy.Progress#getAnsweredCount()
	 */
	@Override
	public int getAnsweredCount() {
		return mAnsweredCount;
	}

	/* (non-Javadoc)
	 * @see au.edu.adelaide.mci.kidnumeracy.Progress#getAnsweredPercentage()
	 */
	@Override
	public double getAnsweredPercentage() {
		if (mQuestionCount == 0){
			return 100;
		}else{
			return getAnsweredCount()/getQuestionCount()*100;			
		}
	}
	
	public JsonProgress(JSONObject json) throws JSONException{
		mPhaseNo = json.getInt(JSON_PHASE_NO);
		mQuestionCount = json.getInt(JSON_QUESTION_COUNT);
		mAnsweredCount = json.getInt(JSON_ANSWERED_COUNT);
	}
	
	/* (non-Javadoc)
	 * @see au.edu.adelaide.mci.kidnumeracy.Progress#toJSON()
	 */
	@Override
	public JSONObject toJSON() throws JSONException{
		JSONObject json = new JSONObject();
		json.put(JSON_PHASE_NO, getPhaseNo());
		json.put(JSON_QUESTION_COUNT, getQuestionCount());
		json.put(JSON_ANSWERED_COUNT, getAnsweredCount());
		return json;
	}

	public void setPhaseNo(int phaseNo) {
		mPhaseNo = phaseNo;
	}

	public void setQuestionCount(int questionCount) {
		mQuestionCount = questionCount;
	}

	public void setAnsweredCount(int answeredCount) {
		mAnsweredCount = answeredCount;
	}

}
