package smu.shuttle;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;

import org.apache.http.HttpConnection;
import org.json.JSONObject;

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
	final String serverIp = "http://192.168";
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_allbus);

		new AllBusAsyncTask().execute(serverIp);
		
	}

	
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
					sb.append(str+"\n");
				}
				br.close();
				conn.disconnect();
				
//				JavaType type = mapper.getTypeFactory().constructCollectionType(ArrayList.class,ShuttleBus.class);
//				arryBus = mapper.readValue(sb.toString(), type);
				
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
//			myJSON = result;
//			showList();
//		
		}

		
	}

}
