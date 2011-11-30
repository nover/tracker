package fi.aalto.mmc.tracker.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

public class TrackerDbAdapter {

	// Database fields
	public static final String KEY_ROWID = "_id";
	public static final String KEY_LONGTITUDE = "longtitude";
	public static final String KEY_LATITUDE = "latitude";
	public static final String KEY_TIME = "time";
	private static final String DB_TABLE = "locations";
	private Context context;
	private SQLiteDatabase db;
	private TrackerDatabaseHelper dbHelper;

	public TrackerDbAdapter(Context context) {
		this.context = context;
	}

	public TrackerDbAdapter open() throws SQLException {
		dbHelper = new TrackerDatabaseHelper(context);
		db = dbHelper.getWritableDatabase();
		return this;
	}

	public void close() {
		dbHelper.close();
	}

	/**
	 * Insert a new location in the database
	 */

	public long insertLocation(String longtitude, String latitude, String time) {
		ContentValues values = createContentValues(longtitude, latitude,
				time);

		return db.insert(DB_TABLE, null, values);
	}


	private ContentValues createContentValues(String longtitude, String latitude, String time) {
		ContentValues values = new ContentValues();
		values.put(KEY_LONGTITUDE, longtitude);
		values.put(KEY_LATITUDE, latitude);
		values.put(KEY_TIME, time);
		return values;
	}
}
