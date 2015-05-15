/**
 * 
 */
package au.edu.adelaide.mci.kidnumeracy;

import java.io.IOException;

import org.json.JSONException;

/**
 * Save and load progress info from stroage
 * @author Yun
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
