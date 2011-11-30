/**
 * 
 */
package fi.aalto.mmc.tracker;

import java.text.SimpleDateFormat;
import java.util.Date;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.IBinder;
import android.widget.Toast;

import fi.aalto.mmc.tracker.data.*;

/**
 * @author nover
 * 
 *         The service for Tracker which hooks the android location services and
 *         stores information
 */
public class TrackerService extends Service {

	TrackerDbAdapter dbAdapt = null;
	
	// Define a listener that responds to location updates
	LocationListener locationListener = new LocationListener() {
		public void onLocationChanged(Location location) {

			Toast.makeText(
					getApplicationContext(),
					"Latitude " + location.getLatitude() + " longtitude: "
							+ location.getLongitude(), Toast.LENGTH_SHORT)
					.show();

			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Date date = new Date();
			
			dbAdapt.insertLocation(Double.toString(location.getLongitude()), Double.toString(location.getLatitude()), dateFormat.format(date));
		}

		public void onStatusChanged(String provider, int status, Bundle extras) {
		}

		public void onProviderEnabled(String provider) {
		}

		public void onProviderDisabled(String provider) {
		}
	};

	@Override
	public void onCreate() {
		// Acquire a reference to the system Location Manager
		LocationManager locationManager = (LocationManager) this
				.getSystemService(Context.LOCATION_SERVICE);

		// Register the listener with the Location Manager to receive location
		// updates every two minutes: 1000 * 60 * 10
		long minTime = 1000 * 60 * 10; // each 10 minutes approximately
		long minDist = 1000; // approximately every 1000 meters travelled
		
		locationManager.requestLocationUpdates(
				LocationManager.NETWORK_PROVIDER, 100, 0, locationListener);
		locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,
				100, 0, locationListener);
		
		dbAdapt =  new TrackerDbAdapter(getApplicationContext());
		dbAdapt.open();
	}

	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		Toast.makeText(this, "Tracker service starting", Toast.LENGTH_SHORT).show();

		// If we get killed, after returning from here, restart
		return START_STICKY;
	}

	@Override
	public IBinder onBind(Intent intent) {
		// We don't provide binding, so return null
		return null;
	}

	@Override
	public void onDestroy() {
		Toast.makeText(this, "Tracker service closing", Toast.LENGTH_SHORT).show();
		
		stopSelf();
	}

}
