
package au.edu.adelaide.mci.kidnumeracy;

/**
 * @author Yun
 *
 */
public interface NumberListener {
	/**
	 * Called whenever the number counted has changed
	 */
	public void numberChanged();
	
	public void ondDirectionChanged();	
	
	public void phaseChanged();	
	
	/**
	 * Finish counting all the phases
	 */
	public void numberFinished();
}
