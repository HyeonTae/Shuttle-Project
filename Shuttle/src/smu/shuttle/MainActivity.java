package smu.shuttle;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity implements OnClickListener{
    private LocationManager locationManager = null;
    private LocationListener locationListener = null;
 
    private Button StattionBtn, TerminalBtn;
    private TextView textLocation = null;
    private ProgressBar pb = null;
 
    private static final String TAG = "Debug";
    private boolean isGPSEnabled = false;
    private boolean isNetworkEnabled = false;
    
   

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
        // if you want to lock screen for always Portrait mode
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
 
        pb = (ProgressBar) findViewById(R.id.progressBar1);
        pb.setVisibility(View.INVISIBLE);
 
        textLocation =  (TextView) findViewById(R.id.cityName);
 
        StattionBtn = (Button) findViewById(R.id.StattionBtn);
        StattionBtn.setOnClickListener(this);
 
        TerminalBtn = (Button) findViewById(R.id.TerminalBtn);
        TerminalBtn.setOnClickListener(this);
 
        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);

     // Getting GPS status
        isGPSEnabled = locationManager
                .isProviderEnabled(LocationManager.GPS_PROVIDER);

        // Getting network status
        isNetworkEnabled = locationManager
                .isProviderEnabled(LocationManager.NETWORK_PROVIDER);
        
        if (isGPSEnabled && isNetworkEnabled) {
        	 
            textLocation.setText("방위가 바뀔때 까지 기다려주세요." + "\nWait..");
            pb.setVisibility(View.VISIBLE);
            locationListener = new MyLocationListener();
            locationManager.requestLocationUpdates(
                    LocationManager.GPS_PROVIDER, 5000, 10,
                    locationListener);
        } else {
            //alertbox("Gps 상태!!", "당신의 GPS 상태 : OFF");
        }

		
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.StattionBtn:
			Intent i = new Intent(this,AllBusActivity.class);
			startActivity(i);
//			finish();
			break;
		case R.id.TerminalBtn:
			Intent i2 = new Intent(MainActivity.this,TerminalActivity.class);
			startActivity(i2);
//			finish();
			break;
		default:
			break;
		}
	}
	
	private class MyLocationListener implements LocationListener {
        @Override
        public void onLocationChanged(Location loc) {
 
            textLocation.setText("");
            pb.setVisibility(View.INVISIBLE);
            Toast.makeText(
                    getBaseContext(),
                    "Location changed : Lat: " + loc.getLatitude() + " Lng: "
                            + loc.getLongitude(), Toast.LENGTH_SHORT).show();
            String longitude = "Longitude: " + loc.getLongitude();
            Log.v(TAG, longitude);
            String latitude = "Latitude: " + loc.getLatitude();
            Log.v(TAG, latitude);
 
            /*---------- 도시명 가져오기 ----------- */
            String cityName = null;
            Geocoder gcd = new Geocoder(getBaseContext(), Locale.getDefault());
            List<Address> addresses;
            try {
                addresses = gcd.getFromLocation(loc.getLatitude(),
                        loc.getLongitude(), 1);
                if (addresses.size() > 0)
                    System.out.println(addresses.get(0).getLocality());
                cityName = addresses.get(0).getLocality();
            } catch (IOException e) {
                e.printStackTrace();
            }
 
            String s = longitude + "\n" + latitude + "\n\n당신의 현재 도시명 : "
                    + cityName;
            textLocation.setText(s);
        }
 
        @Override
        public void onProviderDisabled(String provider) {
            // TODO Auto-generated method stub
        }
 
        @Override
        public void onProviderEnabled(String provider) {
            // TODO Auto-generated method stub
        }
 
        @Override
        public void onStatusChanged(String provider, int status, Bundle extras) {
            // TODO Auto-generated method stub
        }
    }

}
