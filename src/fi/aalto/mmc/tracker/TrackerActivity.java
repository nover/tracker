package fi.aalto.mmc.tracker;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.location.LocationManager;
import android.location.LocationProvider;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class TrackerActivity extends Activity {
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		// bind a click handler to the "start service button"
		final Button button = (Button) findViewById(R.id.button1);
		button.setOnClickListener(new View.OnClickListener() {

			public void onClick(View v) {
				Intent intent = new Intent(getApplicationContext(),
						TrackerService.class);
				startService(intent);
				updateServiceStatus();
			}
		});

		// bind a click handler to the "stop service button"
		final Button button1 = (Button) findViewById(R.id.button2);
		button1.setOnClickListener(new View.OnClickListener() {

			public void onClick(View v) {
				Intent intent = new Intent(getApplicationContext(),
						TrackerService.class);
				stopService(intent);
				updateServiceStatus();
			}
		});

		this.updateServiceStatus();
	}

	private void updateServiceStatus() {
		final TextView serviceText = (TextView) findViewById(R.id.textServiceStatus);

		String text = TrackerService.IsRunning ? "Running..." : "Stopped...";
		serviceText.setText(text);

	}
}