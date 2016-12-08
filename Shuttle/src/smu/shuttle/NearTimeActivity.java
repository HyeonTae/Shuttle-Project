package smu.shuttle;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

public class NearTimeActivity extends Activity {

	final String serverIp = "http://192.168.0.6";

	TextView tv,tv2;
	//현재 시간을 msec로 구한다.
	long now = System.currentTimeMillis();
	//현재시간을 nowTime에 저장
	Date date = new Date(now);
	//시간을 나타내는 포맷을 정한다
	SimpleDateFormat hour = new SimpleDateFormat("HH");
	String formatHour = hour.format(date);
	int numHour = Integer.parseInt(formatHour);
	SimpleDateFormat min = new SimpleDateFormat("mm");
	String formatMin = min.format(date);
	int numMin = Integer.parseInt(formatMin);
	
	
	//제이스 준비물
	String myJSON;
	JSONArray timeList = null;
	private static final String TAG_RESULT = "result";
	private static final String TAG_DEP = "dep";
	private static final String TAG_DEST = "dest";
	private static final String TAG_HOUR = "hour";
	private static final String TAG_MIN = "min";
	ArrayList<HashMap<String, String>> timeBusList;
	ListView list;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_near_time);
		list = (ListView) findViewById(R.id.nearListView);
		tv = (TextView) findViewById(R.id.textView1);
		tv2 = (TextView) findViewById(R.id.textView2);
//		tv.setText(formatHour);
		System.out.println(numHour);
//		tv2.setText(formatMin);
		System.out.println(numMin);
		
		timeBusList = new ArrayList<HashMap<String,String>>();
		
		new NearTimeAsyncTask().execute(serverIp);
	}
	
	
	//리스트뷰뿌려줄꼬야
	protected void showListView() {
		try {
			JSONObject jsonobj = new JSONObject(myJSON);
			timeList = jsonobj.getJSONArray(TAG_RESULT);
			
			for(int i = 0 ; i<timeList.length(); i++){
				JSONObject o =timeList.getJSONObject(i);
				String dep = o.getString(TAG_DEP);
				String dest = o.getString(TAG_DEST);
				int hour = o.getInt(TAG_HOUR);
				int min = o.getInt(TAG_MIN);
				
				HashMap<String, String> timeHash = new HashMap<String, String>();

				timeHash.put(TAG_DEP, dep);
				timeHash.put(TAG_DEST, dest);
				//인트형인데 스트링으로 해야하나?????
//				timeHash.put(TAG_HOUR, hour);
//				timeHash.put(TAG_DEP, dep);
				
				timeBusList.add(timeHash);
			}
			
			ListAdapter adapter = new SimpleAdapter(NearTimeActivity.this,timeBusList,R.layout.list_item,new String[]{TAG_DEP,TAG_DEST,TAG_HOUR,TAG_MIN},new int[]{R.id.dep,R.id.dest,R.id.hour,R.id.min});
			list.setAdapter(adapter);
			
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public class NearTimeAsyncTask extends AsyncTask<String , Void, String>{

		@Override
		protected String doInBackground(String... params) {
			// TODO Auto-generated method stub
			System.out.println("가까운시간 어씽크에 들어 왔다.");
			StringBuilder sb = new StringBuilder();
			String str="";
			String location ="아산시";
			String dep;
			HttpURLConnection conn;
			//지역이 아산시면 아산역 천안시면 천안역 //학교면 학굔데... 아산시이기때문에 탕정면으로???
			try {
				if(location == "아산시"){
					dep = "아산역";
					conn = (HttpURLConnection) new URL(params[0]+"/ShutlleServer/main.do").openConnection();
					conn.setRequestMethod("POST");
					conn.setDoInput(true);//읽기 모드
					conn.setDoOutput(true);//쓰기모드
					conn.setUseCaches(false);//캐싱데이터를 받을지 안받을지
					
					String param = "action=searchBusFromAsanToSMU&dep="+dep+"&Hour="+hour+"&min="+min;
					BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(conn.getOutputStream(),"UTF-8"));
					bw.write(param);
					bw.flush();
					bw.close();
				
					BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream(),"UTF-8"));
					while((str=br.readLine()) != null){
						sb.append(str);
						System.out.println("str:"+str);
					}
					return sb.toString().trim();
					
					
					
					
					
					
				}else if(location == "천안시"){
					conn = (HttpURLConnection) new URL(params[0]+"/ShutlleServer/main.do?"+"action=searchBusFromCheonAnToSMU").openConnection();
				}else if(location == "동남구"){
					conn = (HttpURLConnection) new URL(params[0]+"/ShutlleServer/main.do?"+"action=searchBusFromTerminalToSMU").openConnection();
				}else{
					conn = (HttpURLConnection) new URL(params[0]+"/ShutlleServer/main.do?"+"action=searchBusForAsan").openConnection();
				}
				
				
				
				
			
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
			showListView();
			
		}
		
	}
}
