package au.edu.adelaide.mci.kidnumeracy;

import android.content.Context;

/**
 * Manage the process
 * @author Yun
 *
 */
public class ProcessManager {
    private Context mAppContext;
    private SQLLiteDatabaseHelper mHelper;
    private static ProcessManager sProcessManager;
    
    public ProcessManager(Context appContext){
    	mAppContext = appContext;
    	mHelper = new SQLLiteDatabaseHelper(appContext);
    }
    
    public static ProcessManager get(Context c){
    	if (sProcessManager == null){
    		sProcessManager = new ProcessManager(c);
    	}
    	return sProcessManager;
    }
}
