/**
 * 
 */
package au.edu.adelaide.mci.kidnumeracy;

import java.io.IOException;

import org.json.JSONException;

/**
 * Save and load progress info from json files
 * @author Group 5
 *
 */
public interface ProgressSerialiser {
	/**
	 * Save progress to storage
	 * @param progress
	 */
	public void saveProgress(Progress progress) throws JSONException, IOException;
	
	/**
	 * Save progress to storage
	 * @param progress
	 */
	public Progress loadProgress () throws IOException, JSONException;
}
