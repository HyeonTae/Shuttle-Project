package smu.shuttle;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.ToggleButton;
import smu.gotohome.SelectHomeActivity;
import smu.gotosmu.SelectSmuActivity;
import smu.gotosmu.ToSmuFromAsanActivity;

public class MainActivity extends Activity implements OnClickListener {

	
	final String serverIp = "http://192.168.0.6:8080";

	String cityName = null;
	String dongName = null;
	ToggleButton tb;

	private Button goToHomeBtn, goToSmuBtn;
	private TextView tv = null;
	private TextView tv2 = null;


	// 시간입니더~
	// 현재 시간을 msec로 구한다.
	long now = System.currentTimeMillis();
	// 현재시간을 nowTime에 저장
	Date date = new Date(now);
	// 시간을 나타내는 포맷을 정한다
	SimpleDateFormat hour = new SimpleDateFormat("HH");
	String formatHour = hour.format(date);
	int numHour = Integer.parseInt(formatHour);
	SimpleDateFormat min = new SimpleDateFormat("mm");
	String formatMin = min.format(date);
	int numMin = Integer.parseInt(formatMin);

	// 제이스 준비물
	String myJSON;
	JSONArray timeList = null;
	private static final String TAG_RESULT = "result";
	private static final String TAG_ID = "id";
	private static final String TAG_DEP = "dep";
	private static final String TAG_DEST = "dest";
	private static final String TAG_HOUR = "hour";
	private static final String TAG_MIN = "min";
	ArrayList<HashMap<String, String>> timeBusList;
	ListView list;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		tb = (ToggleButton) findViewById(R.id.toggle1);

		tv = (TextView) findViewById(R.id.tv);
		tv2 = (TextView) findViewById(R.id.tv2);
		list = (ListView) findViewById(R.id.listView1);

		goToHomeBtn = (Button) findViewById(R.id.goToHomeBtn);
		goToHomeBtn.setOnClickListener(this);

		goToSmuBtn = (Button) findViewById(R.id.goToSmuBtn);
		goToSmuBtn.setOnClickListener(this);

		list = (ListView) findViewById(R.id.mainListView1);
		timeBusList = new ArrayList<HashMap<String, String>>();

		// LocationManager 객체를 얻어온다
		final LocationManager lm = (LocationManager) getSystemService(Context.LOCATION_SERVICE);

		tb.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				try {
					if (tb.isChecked()) {
						tv.setText("수신중..");
						// GPS 제공자의 정보가 바뀌면 콜백하도록 리스너 등록하기~!!!
						lm.requestLocationUpdates(LocationManager.GPS_PROVIDER, // 등록할
																				// 위치제공자
								100, // 통지사이의 최소 시간간격 (miliSecond)
								1, // 통지사이의 최소 변경거리 (m)
								mLocationListener);
						lm.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, // 등록할
																					// 위치제공자
								100, // 통지사이의 최소 시간간격 (miliSecond)
								1, // 통지사이의 최소 변경거리 (m)
								mLocationListener);

					} else {
						lm.removeUpdates(mLocationListener); // 미수신할때는 반드시 자원해체를
																// 해주어야 한다.
					}
//					new MainAsyncTask().execute(serverIp);
				} catch (SecurityException ex) {
				}

			}
		});
		if (tv.getText().toString().equals("아산시")) {
			System.out.println("아산시랑 비교했다");
//			new MainAsyncTask().execute(serverIp);

		}
	}

	private final LocationListener mLocationListener = new LocationListener() {
		public void onLocationChanged(Location location) {
			// 여기서 위치값이 갱신되면 이벤트가 발생한다.
			// 값은 Location 형태로 리턴되며 좌표 출력 방법은 다음과 같다.

			Log.d("test", "onLocationChanged, location:" + location);

			/*---------- 도시명 가져오기 ----------- */
			// String cityName = null;
			String cityName2 = null;

			Geocoder gcd = new Geocoder(getBaseContext(), Locale.getDefault());
			List<Address> addresses;
			try {
				addresses = gcd.getFromLocation(location.getLatitude(), location.getLongitude(), 1);
				if (addresses.size() > 0)
					System.out.println("ㄴㅇㄴㅇㅁㄴ" + addresses.get(0).getLocality());
				Log.d("ㅇㄹㅇㄴㄹㅇㄴㄹ", addresses.get(0).getLocality());
				cityName = addresses.get(0).getLocality();
				dongName = addresses.get(0).getThoroughfare();

			} catch (IOException e) {
				e.printStackTrace();
			}

//			tv.setText("도시이름 : " + cityName + "\n동이름:" + cityName2);
			tv.setText(cityName);
			tv2.setText(dongName);
			if (tv.getText().toString().equals("아산시")) {
				System.out.println("아산시랑 비교했다");
				new MainAsyncTask().execute(serverIp);
			}
		}

		public void onProviderDisabled(String provider) {
			// Disabled시
			Log.d("test", "onProviderDisabled, provider:" + provider);
		}

		public void onProviderEnabled(String provider) {
			// Enabled시
			Log.d("test", "onProviderEnabled, provider:" + provider);
		}

		public void onStatusChanged(String provider, int status, Bundle extras) {
			// 변경시
			Log.d("test", "onStatusChanged, provider:" + provider + ", status:" + status + " ,Bundle:" + extras);
		}
	};

	// 리스트뷰뿌려줄꼬야
	protected void showListView() {
		try {
			System.out.println("234234324234" + myJSON);

			JSONArray timeList = new JSONArray(myJSON);

			// timeList = jsonobj.getJSONArray(TAG_RESULT);
			System.out.println(timeList);
			for (int i = 0; i < timeList.length(); i++) {
				JSONObject o = timeList.getJSONObject(i);
				String id = o.getString(TAG_ID);
				String dep = o.getString(TAG_DEP);
				String dest = o.getString(TAG_DEST);
				String hour = o.getString(TAG_HOUR);
				String min = o.getString(TAG_MIN);
				// int hour = o.getInt(TAG_HOUR);
				// int min = o.getInt(TAG_MIN);

				HashMap<String, String> timeHash = new HashMap<String, String>();

				timeHash.put(TAG_ID, id);
				timeHash.put(TAG_DEP, dep);
				timeHash.put(TAG_DEST, dest);
				timeHash.put(TAG_HOUR, hour);
				timeHash.put(TAG_MIN, min);

				timeBusList.add(timeHash);
			}

			ListAdapter adapter = new SimpleAdapter(MainActivity.this, timeBusList, R.layout.list_item,
					new String[] { TAG_ID, TAG_DEP, TAG_DEST, TAG_HOUR, TAG_MIN },
					new int[] { R.id.id, R.id.dep, R.id.dest, R.id.hour, R.id.min });
			list.setAdapter(adapter);

		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public class MainAsyncTask extends AsyncTask<String, Void, String> {
		String City = null;

		@Override
		protected String doInBackground(String... params) {
			// TODO Auto-generated method stub
			System.out.println("가까운 시간표 어씽크에 들어 왔다.");
			System.out.println("몇시니="+formatHour);
			System.out.println("몇분이니="+formatMin);
			
			StringBuilder sb = new StringBuilder();
			String str = "";
			HttpURLConnection conn;

			if (tv.getText().toString().equals("아산시")&&tv2.getText().toString().equals("배방읍")) {
				City ="아산역";
				System.out.println("아 아산역에 들어오면 안되는데....");
				try {

					conn = (HttpURLConnection) new URL(
							params[0] + "/ShuttleServer/main.do?" + "action=searchBusFromAsanToSMUClass&hour="+formatHour.toString()+"&min="+formatMin.toString())
									.openConnection();
					System.out.println(params[0]);
					conn.setRequestMethod("POST");
					conn.setDoInput(true);// 읽기 모드
					conn.setDoOutput(true);// 쓰기모드
					conn.setUseCaches(false);// 캐싱데이터를 받을지 안받을지

					BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
					while ((str = br.readLine()) != null) {
						sb.append(str);
						System.out.println("str:" + str);
					}
					System.out.println("sb.toString:" + sb.toString());
					return sb.toString().trim();

				} catch (MalformedURLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}else if(tv.getText().toString().equals("천안시")){
				City ="천안역";
				System.out.println("아 천안역에 들어오면 안되는데....");
				try {

					conn = (HttpURLConnection) new URL(
							params[0] + "/ShuttleServer/main.do?" + "action=searchBusFromCheonAnToSMUClass&hour="+formatHour.toString()+"&min="+formatMin.toString())
									.openConnection();
					System.out.println(params[0]);
					conn.setRequestMethod("POST");
					conn.setDoInput(true);// 읽기 모드
					conn.setDoOutput(true);// 쓰기모드
					conn.setUseCaches(false);// 캐싱데이터를 받을지 안받을지

					BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
					while ((str = br.readLine()) != null) {
						sb.append(str);
						System.out.println("str:" + str);
					}
					System.out.println("sb.toString:" + sb.toString());
					return sb.toString().trim();

				} catch (MalformedURLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}else if(tv.getText().toString().equals("아산시")&&tv2.getText().toString().equals("탕정면")){
				City ="학교";
				System.out.println("아 학교에 들어오면 안되는데....");
				try {
					//학교출력은 
					conn = (HttpURLConnection) new URL(
							params[0] + "/ShuttleServer/main.do?" + "action=searchAllBusForClass"/*&hour="+formatHour.toString()+"&min="+formatMin.toString()*/)
									.openConnection();
					System.out.println(params[0]);
					conn.setRequestMethod("POST");
					conn.setDoInput(true);// 읽기 모드
					conn.setDoOutput(true);// 쓰기모드
					conn.setUseCaches(false);// 캐싱데이터를 받을지 안받을지

					BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
					while ((str = br.readLine()) != null) {
						sb.append(str);
						System.out.println("str:" + str);
					}
					System.out.println("sb.toString:" + sb.toString());
					return sb.toString().trim();

				} catch (MalformedURLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			return null;

		}

		@Override
		protected void onPostExecute(String result) {
			// TODO Auto-generated method stub
			myJSON = result;
			System.out.println("마이제이슨" + myJSON);
			showListView();

		}

	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.goToHomeBtn:// 집에갈래? 버튼
			Intent i = new Intent(this, SelectHomeActivity.class);
			startActivity(i);
			// finish();
			break;

		case R.id.goToSmuBtn:// 학교갈래? 버튼
			Intent i2 = new Intent(MainActivity.this, SelectSmuActivity.class);
			startActivity(i2);
			// finish();
			break;
//		 case R.id.toggle1:
//			 try {
//					if (tb.isChecked()) {
//						tv.setText("수신중..");
//						// GPS 제공자의 정보가 바뀌면 콜백하도록 리스너 등록하기~!!!
//						lm.requestLocationUpdates(LocationManager.GPS_PROVIDER, // 등록할
//																				// 위치제공자
//								100, // 통지사이의 최소 시간간격 (miliSecond)
//								1, // 통지사이의 최소 변경거리 (m)
//								mLocationListener);
//						lm.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, // 등록할
//																					// 위치제공자
//								100, // 통지사이의 최소 시간간격 (miliSecond)
//								1, // 통지사이의 최소 변경거리 (m)
//								mLocationListener);
//
//					} else {
//						lm.removeUpdates(mLocationListener); // 미수신할때는 반드시 자원해체를
//																// 해주어야 한다.
//					}
//					new MainAsyncTask().execute(serverIp);
//				} catch (SecurityException ex) {
//				}
//			 break;
		default:
			break;
		}
	}
}
