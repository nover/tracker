package fi.aalto.mmc.tracker.data;

import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

/**
 * Class providing methods to create and upgrade the locationdata storage table
 * 
 * @author nover
 * 
 */
public class LocationTable {
	// Database creation SQL statement
	private static final String DATABASE_CREATE = "create table locations "
			+ "(_id integer primary key autoincrement, "
			+ "longtitude text not null, " + "latitude text not null,"
			+ " time text not null);";

	public static void onCreate(SQLiteDatabase database) {
		database.execSQL(DATABASE_CREATE);
	}

	public static void onUpgrade(SQLiteDatabase database, int oldVersion,
			int newVersion) {
		Log.w(LocationTable.class.getName(), "Upgrading database from version "
				+ oldVersion + " to " + newVersion
				+ ", which will destroy all old data");
		database.execSQL("DROP TABLE IF EXISTS todo");
		onCreate(database);
	}
}