package smu.shuttle;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import smu.model.SuttleBus;

public class AllBusActivity extends Activity {
	ArrayList<SuttleBus> arryBus;
	ObjectMapper mapper = new ObjectMapper();
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		new AllBusAsyncTask().execute();
		runOnUiThread(new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				for(SuttleBus s: arryBus){
					
				}
			}
		});
	}

	public class AllBusAsyncTask extends AsyncTask<String, Void, String>{

		@Override
		protected String doInBackground(String... params) {
			// TODO Auto-generated method stub
			System.out.println("전체시카푠어씽크에 들어 왔다");
			String str= "";
			try {
				URL url = new URL("보낼 jsp경로");
				HttpURLConnection conn = (HttpURLConnection) url.openConnection();
				conn.setRequestMethod("GET");
				conn.setDoInput(true);
				conn.setDoOutput(true);
				conn.setUseCaches(false);
				
				//jsp와 통신이 정상이면 받아온다
				if(conn.getResponseCode() == conn.HTTP_OK){
					InputStreamReader tmp = new InputStreamReader(conn.getInputStream(),"UTF-8");
					BufferedReader reader = new BufferedReader(tmp);
					StringBuffer buffer = new StringBuffer();
					//jsp에서 보낸 값을 받는다
					while((str =reader.readLine())!=null){
						buffer.append(str);
					}
					reader.close();
					conn.disconnect();

					System.out.println("가져온 값"+buffer.toString());
					
//					JavaType type = mapper.getTypeFactory().constructParametricType(ArrayList.class, )
				}else{
					Log.i("통신결과",conn.getResponseCode()+"에러");
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
		
	}
}
