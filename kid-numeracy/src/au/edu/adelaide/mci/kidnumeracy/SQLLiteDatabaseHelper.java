package au.edu.adelaide.mci.kidnumeracy;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class SQLLiteDatabaseHelper extends SQLiteOpenHelper {
    private static final String DB_NAME = "kidNum.sqlite";	
    private static final int VERSION = 1;
    
    public SQLLiteDatabaseHelper(Context context){
    	 super(context, DB_NAME, null, VERSION);
    }
	@Override
	public void onCreate(SQLiteDatabase db) {
        // create the "CountLearningProcess" table
        db.execSQL("create table CountLearningProcess (_id integer primary key autoincrement, repeatTimes integer)");
        // create the "CountLearningPhase" table
        db.execSQL("create table CountLearningPhase (" +
                " _id integer primary key autoincrement," +
                " phaseNo integer, minValue integer, maxValue integer"+
                ", processId integer references CountLearningProcess(_id))");

	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub

	}

}
