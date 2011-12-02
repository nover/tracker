package fi.aalto.mmc.tracker.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * SQLiteOpenHelper extension, providing methods for android to create and
 * upgrade tables
 * 
 * @author nover
 * 
 */
public class TrackerDatabaseHelper extends SQLiteOpenHelper {
	private static final String DATABASE_NAME = "locationdata";

	private static final int DATABASE_VERSION = 1;

	public TrackerDatabaseHelper(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}

	// Method is called during creation of the database
	@Override
	public void onCreate(SQLiteDatabase database) {
		LocationTable.onCreate(database);
	}

	// Method is called during an upgrade of the database,
	// e.g. if you increase the database version
	@Override
	public void onUpgrade(SQLiteDatabase database, int oldVersion,
			int newVersion) {
		LocationTable.onUpgrade(database, oldVersion, newVersion);
	}
}
