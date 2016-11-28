package smu.shuttle;

import java.text.SimpleDateFormat;
import java.util.Date;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {
	final LocationManager lm= (LocationManager) getSystemService(Context.LOCATION_SERVICE);
	TextView tv,tv5;
	Button totalTimeBtn, nearTimeBtn;
	//현재시간을 msec으로 구한다
	long now = System.currentTimeMillis();
	Date date = new Date(now);
	SimpleDateFormat dateFomat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
	String formatDate = dateFomat.format(date);
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		totalTimeBtn = (Button) findViewById(R.id.totalTimeBtn);
		nearTimeBtn = (Button) findViewById(R.id.nearTimeBtn);
		tv = (TextView) findViewById(R.id.textView1);
		tv = (TextView) findViewById(R.id.textView5);
		
		tv.setText(formatDate);
		tv5.setText("위치정보 수신중");
	}
	
	public void totalTimeBtn(View v){
		Toast.makeText(this, "토탈 타임클릭", Toast.LENGTH_SHORT).show();
		Intent i = new Intent(this, TotalTimeActivity.class);
		startActivity(i);
	}
	public void nearTimeBtn(View v){
		tv5.setText("수신중..");
		
		lm.requestLocationUpdates(LocationManager.GPS_PROVIDER, 100, 1, mLocationListenr);
		lm.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 100, 1, mLocationListenr);
		
	}
	private final LocationListener mLocationListenr = new LocationListener() {
		
		@Override
		public void onStatusChanged(String provider, int status, Bundle extras) {
			// TODO Auto-generated method stub
			Log.d("locationtest", provider+status+extras);
		}
		
		@Override
		public void onProviderEnabled(String provider) {
			// TODO Auto-generated method stub
			Log.d("locationtest", "인에이블"+provider);
		}
		
		@Override
		public void onProviderDisabled(String provider) {
			// TODO Auto-generated method stub
			Log.d("locationtest", "디스어블"+provider);
		}
		
		@Override
		public void onLocationChanged(Location location) {
			// TODO Auto-generated method stub
			double longitude = location.getLongitude();//경도
			double latitude = location.getLatitude();//위도
			double altitude = location.getAltitude();//고도
			float accuracy = location.getAccuracy();//정확도
			String provider = location.getProvider();//위치제공자
			
			tv5.setText("현재 위치 정보:"+provider+"\n위도:"+latitude+"\n경도"+longitude+"\n고도:"+altitude+"\n정확도:"+accuracy+"\n위치제공자:"+provider);
			
			
		}
	};
}
