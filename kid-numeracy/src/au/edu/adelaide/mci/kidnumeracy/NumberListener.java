
package au.edu.adelaide.mci.kidnumeracy;

/**
 * @author Yun
 *
 */
public interface NumberListener {
	/**
	 * Called whenever the number counted has changed
	 */
	public void afterNumChanged(int oldValue);
	
	public void ondDirectionChanged();	
	
	public void onPhaseChanged();
	
	/**
	 * Finish counting all the phases
	 */
	public void onAllPhaseCounted();
}
