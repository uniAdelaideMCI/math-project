package au.edu.adelaide.mci.kidnumeracy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.content.Context;

/**
 * Define Count Learning Process
 * @author Group 5
 *
 */
public class CountLearningProcess {
	private static final String PROP_REPEAT_TIMES = "repeatTimes";
	private static final String PROP_PHASES = "phases";
	private List<CountLearningPhase> mPhases = new LinkedList<CountLearningPhase>();
	private int mRepeatTimes = 1;
	public int getRepeatTimes() {
		return mRepeatTimes;
	}
	public static CountLearningProcess load(
			Context context) throws IOException, JSONException {
		CountLearningProcess countLearningProcess = new CountLearningProcess();
		InputStream is = context.getAssets().open("count_learn_process.json");
		BufferedReader reader = new BufferedReader(new InputStreamReader(is));
		try{
			StringBuilder sb = new StringBuilder();
			String line = null;
			while ( (line = reader.readLine()) != null){
				sb.append(line);
			}
			JSONObject jsonObject = new JSONObject(sb.toString());
			countLearningProcess.mRepeatTimes = jsonObject.getInt(PROP_REPEAT_TIMES);
			JSONArray jsonArray = jsonObject.getJSONArray(PROP_PHASES);
			for (int i = 0; i < jsonArray.length(); i++) {
				JSONObject jsonPhase = jsonArray.getJSONObject(i);
				int phaseNo = jsonPhase.getInt(CountLearningPhase.PROP_PHASE_NO);
				int minValue = jsonPhase.getInt(CountLearningPhase.PROP_MIN_VALUE);
				int maxValue = jsonPhase.getInt(CountLearningPhase.PROP_MAX_VALUE);
				CountLearningPhase countLearningPhase = new CountLearningPhase(phaseNo,minValue,maxValue);
				countLearningProcess.mPhases.add(countLearningPhase);
			}
		}finally{
			if (is != null){
				is.close();
			}
		}
		return countLearningProcess;
	}
	public CountLearningPhase getFirstPhase() {
		return mPhases.get(0);
	}
	public CountLearningPhase nextPhase(CountLearningPhase currentPhase) {
	    int index = mPhases.indexOf(currentPhase);
		return mPhases.get(index+1);
	}
	
	public boolean isLastPhase(CountLearningPhase currentPhase){
		int index = mPhases.indexOf(currentPhase);
		if (index == mPhases.size() - 1){
			return true;
		}else{
			return false;
		}
	}
	
	public CountLearningPhase addLearningPhase(int phaseNo, int minValue, int maxValue){
		CountLearningPhase countLearningPhase = new CountLearningPhase(phaseNo, minValue, maxValue);
		mPhases.add(countLearningPhase);
		return countLearningPhase;
	}
}
