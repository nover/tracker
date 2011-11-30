/**
 * 
 */
package fi.aalto.mmc.tracker;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

/**
 * @author nover
 * 
 * The service for Tracker which hooks the android location services and stores information
 */
public class TrackerService extends Service {

	/* (non-Javadoc)
	 * @see android.app.Service#onBind(android.content.Intent)
	 */
	@Override
	public IBinder onBind(Intent arg0) {
		// TODO Auto-generated method stub
		return null;
	}

}
