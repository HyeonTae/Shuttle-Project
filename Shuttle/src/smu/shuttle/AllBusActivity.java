package smu.shuttle;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import org.apache.http.HttpConnection;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;

import android.app.Activity;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import smu.model.ShuttleBus;

public class AllBusActivity extends Activity {
	ArrayList<ShuttleBus> arryBus;
	ArrayList<ShuttleBus> myarryBus =new ArrayList<ShuttleBus>();
	ArrayList<Integer> marrtBusNums = new ArrayList<Integer>();
	
	ListView mListView = null;
//	AllbusListAdapter mAdapter = null;
	ObjectMapper mapper = new ObjectMapper();
	
	final String serverIp = "http://192.168.0.6";
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_allbus);
		
		mListView = (ListView) findViewById(R.id.listView1);
//		mAdapter = new AllbusListAdapter(this);
		
		new AllBusAsyncTask().execute(serverIp);
		runOnUiThread(new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				//미구현....
//				mAdapter.getItem(position);
//				mListView.setAdapter(arryBus);
			}
		});
	}

	
//	private class AllbusListAdapter extends BaseAdapter{
//		Context context;
//		ArrayList<ShuttleBus> BusArr = new ArrayList<ShuttleBus>();
//		
//		public AllbusListAdapter(Context context){
//			this.context = context;
//		}
//		@Override
//		public int getCount() {
//			// TODO Auto-generated method stub
//			return BusArr.size();
//		}
//
//		@Override
//		public Object getItem(int position) {
//			// TODO Auto-generated method stub
//			return BusArr.get(position);
//		}
//
//		@Override
//		public long getItemId(int position) {
//			// TODO Auto-generated method stub
//			return position;
//		}
//
//		@Override
//		public View getView(int position, View convertView, ViewGroup parent) {
//			// TODO Auto-generated method stub
//			
//			return null;
//		}
//		
//	}
	
	public class AllBusAsyncTask extends AsyncTask<String, Void, String>{
		StringBuilder sb = new StringBuilder();
		String busTime;
		@Override
		protected String doInBackground(String... params) {
			// TODO Auto-generated method stub
			System.out.println("전체시카푠어씽크에 들어 왔다");
//			busTime = params[1];
			String str= "";
			try {
				HttpURLConnection conn = (HttpURLConnection) new URL(params[0]+"/ShutlleServer/main.do?"+"action=searchAllBus").openConnection();
				conn.setRequestMethod("GET");
				conn.setDoInput(true);
				conn.setDoOutput(true);
				conn.setUseCaches(false);
				
				BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream(),"UTF-8"));
				
				while((str = br.readLine())!=null){
					sb.append(str);
				}
				br.close();
				conn.disconnect();
				
				JavaType type = mapper.getTypeFactory().constructCollectionType(ArrayList.class,ShuttleBus.class);
				arryBus = mapper.readValue(sb.toString(), type);
				
				
			} catch (MalformedURLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			return null;
		}
		
	}
}
