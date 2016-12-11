package smu.shuttleapp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.fasterxml.jackson.databind.ObjectMapper;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;
import smu.shuttleapp.home.GoHomeActivity;
import smu.shuttleapp.home.SearchForAsanActivity.ViewHolder;
import smu.shuttleapp.smu.GoSMUActivity;

public class MainActivity extends Activity {
	String cityName = null;
	String dongName = null;
	TextView nameTv = null;
	ListView busListView = null;
	BusListAdapter bAdapter = null;
	ArrayList<ViewHolder> Data;
	final String serverIp = "http://192.168.0.34:8060";
	public static final int LOGOUT = 1;
	SharedPreferences sp;
	SharedPreferences.Editor ed;
	ToggleButton tb;
	ObjectMapper mapper = new ObjectMapper();
	// 시간입니더~
	// 현재 시간을 msec로 구한다.
	long now = System.currentTimeMillis();
	// 현재시간을 nowTime에 저장
	Date date = new Date(now);
	// 시간을 나타내는 포맷을 정한다
	SimpleDateFormat hour = new SimpleDateFormat("HH");
	String formatHour = hour.format(date);
	// int numHour = Integer.parseInt(formatHour);
	SimpleDateFormat min = new SimpleDateFormat("mm");
	String formatMin = min.format(date);
	// int numMin = Integer.parseInt(formatMin);

	// 제이스 준비물
	String myJSON;
	JSONArray timeList = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		sp = getSharedPreferences("sp", MODE_PRIVATE);
		ed = sp.edit();
		busListView = (ListView) findViewById(R.id.busList);
		tb = (ToggleButton) findViewById(R.id.gpsBut);
		nameTv = (TextView) findViewById(R.id.nameTv);
		Intent i = getIntent();
		String name = i.getExtras().getString("name");
		nameTv.setText(name+"님 환영합니다");
		// LocationManager 객체를 얻어온다
		final LocationManager lm = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
		tb.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				try {
					if (tb.isChecked()) {
						if (chkGpsService() == true) {
							Toast.makeText(getApplicationContext(), "GPS 연동중..", Toast.LENGTH_SHORT).show();
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
						}

					} else {
						lm.removeUpdates(mLocationListener); // 미수신할때는 반드시 자원해체를
						// 해주어야 한다.
					}
					// new MainAsyncTask().execute(serverIp);
				} catch (SecurityException ex) {

				}

			}
		});
	}

	private final LocationListener mLocationListener = new LocationListener() {
		public void onLocationChanged(Location location) {
			// 여기서 위치값이 갱신되면 이벤트가 발생한다.
			// 값은 Location 형태로 리턴되며 좌표 출력 방법은 다음과 같다.

			Log.d("test", "onLocationChanged, location:" + location);
			double longitude = location.getLongitude(); // 경도
			double latitude = location.getLatitude();

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
				
				System.out.println("어디야너는="+cityName+" "+dongName);
				System.out.println("ㅁㅁㅁㅁㅁ"+cityName.equals("아산시"));
				
				if (cityName.equals("아산시") == true && dongName.equals("탕정면") == true) { //학교
					new GetFestBusToHome().execute(serverIp);
				} else if (cityName.equals("아산시") == true && dongName.equals("배방읍") == true) {
					new GetFestBusFromAsan().execute(serverIp);
				} else if (cityName.equals("천안시") == true && dongName.equals("대흥동") == true) {
					new GetFestBusFromCheonAn().execute(serverIp);
				} else if (cityName.equals("천안시") == true && dongName.equals("신부동") == true) {
					new GetFestBusFromTerminal().execute(serverIp);
				}
			} catch (IOException e) {
				e.printStackTrace();
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

	public void goHome(View v) {
		Intent i = new Intent(MainActivity.this, GoHomeActivity.class);
		startActivity(i);
	}

	public void goSMU(View v) {
		Intent i = new Intent(MainActivity.this, GoSMUActivity.class);
		startActivity(i);
	}

	@Override
	public void onBackPressed() {
		new AlertDialog.Builder(this).setTitle("종료").setMessage("어플을 종료 하시겠습니까?").setNegativeButton("아니요", null)
				.setPositiveButton("예", new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int whichButton) {
						finish();
					}
				}).show();
	}

	private class BusListAdapter extends BaseAdapter {
		Context context;

		public BusListAdapter(Context context) {
			this.context = context;
		}

		@Override
		public int getCount() {
			return Data.size();
		}

		@Override
		public Object getItem(int position) {
			return Data.get(position);
		}

		@Override
		public long getItemId(int position) {
			return position;
		}

		@Override
		public View getView(final int position, View convertView, ViewGroup parent) {
			ViewHolder viewHolder;
			if (convertView == null) {
				viewHolder = new ViewHolder();

				LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
				convertView = inflater.inflate(R.layout.list_item1, null);

				viewHolder.start = (TextView) convertView.findViewById(R.id.start);
				viewHolder.last = (TextView) convertView.findViewById(R.id.last);
				viewHolder.hour = (TextView) convertView.findViewById(R.id.hour);
				viewHolder.minute = (TextView) convertView.findViewById(R.id.minute);

				convertView.setTag(viewHolder);
			} else {
				viewHolder = (ViewHolder) convertView.getTag();
			}

			viewHolder.start.setText(Data.get(position).getStart2());
			viewHolder.last.setText(Data.get(position).getLast2());
			viewHolder.hour.setText(Data.get(position).getHour2());
			viewHolder.minute.setText(Data.get(position).getMinute2());

			return convertView;
		}
	}

	public class ViewHolder {

		public TextView start, last, hour, minute;
		String start2, last2, hour2, minute2;

		public ViewHolder() {

		}

		public ViewHolder(String start2, String last2, String hour2, String minute2) {
			this.start2 = start2;
			this.last2 = last2;
			this.hour2 = hour2;
			this.minute2 = minute2;
		}

		public String getStart2() {
			return start2;
		}

		public String getLast2() {
			return last2;
		}

		public String getHour2() {
			return hour2;
		}

		public String getMinute2() {
			return minute2;
		}

	}

	// GPS 설정 체크
	private boolean chkGpsService() {

		String gps = android.provider.Settings.Secure.getString(getContentResolver(),
				android.provider.Settings.Secure.LOCATION_PROVIDERS_ALLOWED);

		Log.d(gps, "aaaa");

		if (!(gps.matches(".*gps.*") && gps.matches(".*network.*"))) {

			// GPS OFF 일때 Dialog 표시
			AlertDialog.Builder gsDialog = new AlertDialog.Builder(this);
			gsDialog.setTitle("위치 서비스 설정");
			gsDialog.setMessage("무선 네트워크 사용, GPS 위성 사용을 모두 체크하셔야 정확한 위치 서비스가 가능합니다.\n위치 서비스 기능을 설정하시겠습니까?");
			gsDialog.setPositiveButton("예", new DialogInterface.OnClickListener() {
				public void onClick(DialogInterface dialog, int which) {
					// GPS설정 화면으로 이동
					Intent intent = new Intent(android.provider.Settings.ACTION_LOCATION_SOURCE_SETTINGS);
					intent.addCategory(Intent.CATEGORY_DEFAULT);
					startActivity(intent);
				}
			}).setNegativeButton("아니오", new DialogInterface.OnClickListener() {
				public void onClick(DialogInterface dialog, int which) {
					return;
				}
			}).create().show();
			return false;

		} else {
			return true;
		}
	}

	private class GetFestBusFromAsan extends AsyncTask<String, String, String> {
		String asan = "아산역";

		@Override
		protected void onPreExecute() {
			super.onPreExecute();
		}

		@Override
		protected String doInBackground(String... params) {
			try {
				System.out.println("몇시=" + formatHour + "몇분=" + formatMin);
				System.out.println("무슨시=" + cityName + " 무슨동=" + dongName);
				HttpURLConnection conn = (HttpURLConnection) new URL(
						serverIp + "/ShuttleServer/main.do?" + "action=festSearchBusToSMU" + "&dep=" + asan.toString()
								+ "&hour=" + formatHour.toString() + "&min=" + formatMin.toString()).openConnection();

				conn.setRequestMethod("GET");
				conn.setDoInput(true);
				conn.setDoOutput(true);
				int responseCode = conn.getResponseCode();
				if (responseCode == HttpURLConnection.HTTP_OK) {
					BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
					String data = null;
					Data = new ArrayList<ViewHolder>();
					ArrayList<String> arrayList = new ArrayList<String>();
					while ((data = br.readLine()) != null) {
						arrayList.add(data);
					}

					try {
						JSONArray jsonArray = new JSONArray(arrayList.get(0));
						for (int i = 0; i < jsonArray.length(); i++) {
							JSONObject jsonObject = jsonArray.getJSONObject(i);
							Data.add(new ViewHolder(jsonObject.getString("dep"), jsonObject.getString("dest"),
									jsonObject.getString("hour"), jsonObject.getString("min")));
						}
					} catch (JSONException e) {
						e.printStackTrace();
					}
				}

			} catch (MalformedURLException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			return null;
		}

		@Override
		protected void onPostExecute(String result) {
			// Toast.makeText(getApplicationContext(), "GetAllDebt :
			// "+darr.toString(), Toast.LENGTH_SHORT).show();
			super.onPostExecute(result);

			bAdapter = new BusListAdapter(getApplicationContext());
			busListView.setAdapter(bAdapter);
			bAdapter.notifyDataSetChanged();
		}
	}
	
	private class GetFestBusFromCheonAn extends AsyncTask<String, String, String> {
		String cheonAn = "천안역";

		@Override
		protected void onPreExecute() {
			super.onPreExecute();
		}

		@Override
		protected String doInBackground(String... params) {
			try {
				System.out.println("몇시=" + formatHour + "몇분=" + formatMin);
				System.out.println("무슨시=" + cityName + " 무슨동=" + dongName);
				HttpURLConnection conn = (HttpURLConnection) new URL(
						serverIp + "/ShuttleServer/main.do?" + "action=festSearchBusToSMU" + "&dep=" + cheonAn.toString()
								+ "&hour=" + formatHour.toString() + "&min=" + formatMin.toString()).openConnection();

				conn.setRequestMethod("GET");
				conn.setDoInput(true);
				conn.setDoOutput(true);
				int responseCode = conn.getResponseCode();
				if (responseCode == HttpURLConnection.HTTP_OK) {
					BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
					String data = null;
					Data = new ArrayList<ViewHolder>();
					ArrayList<String> arrayList = new ArrayList<String>();
					while ((data = br.readLine()) != null) {
						arrayList.add(data);
					}

					try {
						JSONArray jsonArray = new JSONArray(arrayList.get(0));
						for (int i = 0; i < jsonArray.length(); i++) {
							JSONObject jsonObject = jsonArray.getJSONObject(i);
							Data.add(new ViewHolder(jsonObject.getString("dep"), jsonObject.getString("dest"),
									jsonObject.getString("hour"), jsonObject.getString("min")));
						}
					} catch (JSONException e) {
						e.printStackTrace();
					}
				}

			} catch (MalformedURLException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			return null;
		}

		@Override
		protected void onPostExecute(String result) {
			// Toast.makeText(getApplicationContext(), "GetAllDebt :
			// "+darr.toString(), Toast.LENGTH_SHORT).show();
			super.onPostExecute(result);

			bAdapter = new BusListAdapter(getApplicationContext());
			busListView.setAdapter(bAdapter);
			bAdapter.notifyDataSetChanged();
		}
	}
	
	private class GetFestBusFromTerminal extends AsyncTask<String, String, String> {
		String terminal = "터미널";

		@Override
		protected void onPreExecute() {
			super.onPreExecute();
		}

		@Override
		protected String doInBackground(String... params) {
			try {
				System.out.println("몇시=" + formatHour + "몇분=" + formatMin);
				System.out.println("무슨시=" + cityName + " 무슨동=" + dongName);
				HttpURLConnection conn = (HttpURLConnection) new URL(
						serverIp + "/ShuttleServer/main.do?" + "action=festSearchBusToSMU" + "&dep=" + terminal.toString()
								+ "&hour=" + formatHour.toString() + "&min=" + formatMin.toString()).openConnection();

				conn.setRequestMethod("GET");
				conn.setDoInput(true);
				conn.setDoOutput(true);
				int responseCode = conn.getResponseCode();
				if (responseCode == HttpURLConnection.HTTP_OK) {
					BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
					String data = null;
					Data = new ArrayList<ViewHolder>();
					ArrayList<String> arrayList = new ArrayList<String>();
					while ((data = br.readLine()) != null) {
						arrayList.add(data);
					}

					try {
						JSONArray jsonArray = new JSONArray(arrayList.get(0));
						for (int i = 0; i < jsonArray.length(); i++) {
							JSONObject jsonObject = jsonArray.getJSONObject(i);
							Data.add(new ViewHolder(jsonObject.getString("dep"), jsonObject.getString("dest"),
									jsonObject.getString("hour"), jsonObject.getString("min")));
						}
					} catch (JSONException e) {
						e.printStackTrace();
					}
				}

			} catch (MalformedURLException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			return null;
		}

		@Override
		protected void onPostExecute(String result) {
			// Toast.makeText(getApplicationContext(), "GetAllDebt :
			// "+darr.toString(), Toast.LENGTH_SHORT).show();
			super.onPostExecute(result);

			bAdapter = new BusListAdapter(getApplicationContext());
			busListView.setAdapter(bAdapter);
			bAdapter.notifyDataSetChanged();
		}
	}
	
	private class GetFestBusToHome extends AsyncTask<String, String, String> {

		@Override
		protected void onPreExecute() {
			super.onPreExecute();
		}

		@Override
		protected String doInBackground(String... params) {
			try {
				System.out.println("몇시=" + formatHour + "몇분=" + formatMin);
				System.out.println("무슨시=" + cityName + " 무슨동=" + dongName);
				HttpURLConnection conn = (HttpURLConnection) new URL(
						serverIp + "/ShuttleServer/main.do?" + "action=festSearchBusToDest" 
								+ "&hour=" + formatHour.toString() + "&min=" + formatMin.toString()).openConnection();

				conn.setRequestMethod("GET");
				conn.setDoInput(true);
				conn.setDoOutput(true);
				int responseCode = conn.getResponseCode();
				if (responseCode == HttpURLConnection.HTTP_OK) {
					BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
					String data = null;
					Data = new ArrayList<ViewHolder>();
					ArrayList<String> arrayList = new ArrayList<String>();
					while ((data = br.readLine()) != null) {
						arrayList.add(data);
					}

					try {
						JSONArray jsonArray = new JSONArray(arrayList.get(0));
						for (int i = 0; i < jsonArray.length(); i++) {
							JSONObject jsonObject = jsonArray.getJSONObject(i);
							Data.add(new ViewHolder(jsonObject.getString("dep"), jsonObject.getString("dest"),
									jsonObject.getString("hour"), jsonObject.getString("min")));
						}
					} catch (JSONException e) {
						e.printStackTrace();
					}
				}

			} catch (MalformedURLException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			return null;
		}

		@Override
		protected void onPostExecute(String result) {
			// Toast.makeText(getApplicationContext(), "GetAllDebt :
			// "+darr.toString(), Toast.LENGTH_SHORT).show();
			super.onPostExecute(result);

			bAdapter = new BusListAdapter(getApplicationContext());
			busListView.setAdapter(bAdapter);
			bAdapter.notifyDataSetChanged();
		}
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		menu.add(0, LOGOUT, 0, "로그아웃");
		return super.onCreateOptionsMenu(menu);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {

		switch (item.getItemId()) {
		case LOGOUT:
			new AlertDialog.Builder(this).setTitle("로그아웃").setMessage("로그아웃 하시겠습니까?").setNegativeButton("아니요", null)
					.setPositiveButton("예", new DialogInterface.OnClickListener() {
						public void onClick(DialogInterface dialog, int whichButton) {
							ed.remove("autoid");
							ed.remove("autopass");
							ed.commit();
							Intent i = new Intent(MainActivity.this, LoginActivity.class);
							startActivity(i);
							finish();
						}
					}).show();
			break;
		default:
			break;
		}
		return super.onOptionsItemSelected(item);
	}
}
