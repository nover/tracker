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

			SimpleDateFormat dateFormat = new SimpleDateFormat("YYYY-MM-DD HH:MM:SS.SSS");
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
		// updates every two minutes: 1000 * 60 * 2
		locationManager.requestLocationUpdates(
				LocationManager.NETWORK_PROVIDER, 1000, 0, locationListener);
		locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,
				1000, 0, locationListener);
		
		dbAdapt =  new TrackerDbAdapter(getApplicationContext());
	}

	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		Toast.makeText(this, "service starting", Toast.LENGTH_SHORT).show();

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
		Toast.makeText(this, "service done", Toast.LENGTH_SHORT).show();
	}

}
