package smu.gotohome;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import smu.shuttle.NearTimeActivity;
import smu.shuttle.R;
import smu.shuttle.NearTimeActivity.NearTimeAsyncTask;
import smu.shuttle.R.id;
import smu.shuttle.R.layout;
import smu.shuttle.R.menu;

public class ToHomeFromStationActivity extends Activity {

	final String serverIp = "http://192.168.0.6:8080";

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
		setContentView(R.layout.activity_to_home_from_station);
		list = (ListView) findViewById(R.id.listView1);
		timeBusList = new ArrayList<HashMap<String,String>>();
		
		new ToSmuFromAsanAsyncTask().execute(serverIp);
	}
	
	//리스트뷰뿌려줄꼬야
		protected void showListView() {
			try {
				System.out.println("234234324234"+myJSON);
				
				JSONArray timeList = new JSONArray(myJSON);
				
				//timeList = jsonobj.getJSONArray(TAG_RESULT);
				System.out.println(timeList);
				for(int i = 0 ; i<timeList.length(); i++){
					JSONObject o =timeList.getJSONObject(i);
					String id = o.getString(TAG_ID);
					String dep = o.getString(TAG_DEP);
					String dest = o.getString(TAG_DEST);
					String hour = o.getString(TAG_HOUR);
					String min = o.getString(TAG_MIN);
//					int hour = o.getInt(TAG_HOUR);
//					int min = o.getInt(TAG_MIN);
					
					HashMap<String, String> timeHash = new HashMap<String, String>();

					timeHash.put(TAG_ID, id);
					timeHash.put(TAG_DEP, dep);
					timeHash.put(TAG_DEST, dest);
					timeHash.put(TAG_HOUR, hour);
					timeHash.put(TAG_MIN, min);
					
					timeBusList.add(timeHash);
				}
				
				ListAdapter adapter = new SimpleAdapter(ToHomeFromStationActivity.this,timeBusList,R.layout.list_item,new String[]{TAG_ID, TAG_DEP,TAG_DEST,TAG_HOUR,TAG_MIN},new int[]{R.id.id, R.id.dep,R.id.dest,R.id.hour,R.id.min});
				list.setAdapter(adapter);
				
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	public class ToSmuFromAsanAsyncTask extends AsyncTask<String, Void, String> {

		@Override
		protected String doInBackground(String... params) {
			// TODO Auto-generated method stub
			System.out.println("학교에서 천안아산에 가는 시간표 어씽크에 들어 왔다.");
			StringBuilder sb = new StringBuilder();
			String str = "";
			HttpURLConnection conn;

			try {
				conn = (HttpURLConnection) new URL(
						params[0] + "/ShuttleServer/main.do?" + "action=searchBusForCheonAnClass").openConnection();
				System.out.println(params[0]);
				conn.setRequestMethod("POST");
				conn.setDoInput(true);// 읽기 모드
				conn.setDoOutput(true);// 쓰기모드
				conn.setUseCaches(false);// 캐싱데이터를 받을지 안받을지

				// String param = "action=searchAllBus";
				//// String param =
				// "action=searchBusFromAsanToSMU&dep="+dep+"&Hour="+hour+"&min="+min;
				// BufferedWriter bw = new BufferedWriter(new
				// OutputStreamWriter(conn.getOutputStream(),"UTF-8"));
				// bw.write(param);
				// bw.flush();
				// bw.close();

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
			return null;
		}
		@Override
		protected void onPostExecute(String result) {
			// TODO Auto-generated method stub			
			myJSON = result;
			System.out.println("마이제이슨"+myJSON);
			showListView();
			
		}

	}
}

