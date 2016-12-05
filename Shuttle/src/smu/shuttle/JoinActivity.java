package smu.shuttle;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class JoinActivity extends Activity {

	private Button joinBut;
	private Button joinCancelBut;
	private EditText joinId;
	private EditText joinPass;
	private EditText joinName;
	private EditText joinDept;
	private EditText joinArea;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_join);
		
		joinBut = (Button) findViewById(R.id.joinBut);
		joinCancelBut = (Button) findViewById(R.id.joinCancelBut);
		joinId = (EditText) findViewById(R.id.joinId);
		joinPass = (EditText) findViewById(R.id.joinPass);
		joinName = (EditText) findViewById(R.id.joinName);
		joinDept = (EditText) findViewById(R.id.joinDept);
		joinArea = (EditText) findViewById(R.id.joinArea);
		
		
	}

	//가입하기버튼 클릭시
	public void joinBut(View v){
		new JoinAsyncTask().execute();
		Intent i = new Intent(JoinActivity.this,LoginActivity.class);
		startActivity(i);
		finish();
	}
	
	//취소하기버튼 클릭시
	public void joinCancelBut(View v){
		Toast.makeText(getApplicationContext(), "취소하기버튼을 눌렀습니다", Toast.LENGTH_SHORT).show();
		Intent i = new Intent(JoinActivity.this,LoginActivity.class);
		startActivity(i);
		finish();
	}
	
	public class JoinAsyncTask extends AsyncTask<String, Void, String>{


		@Override
		protected String doInBackground(String... params) {
			// TODO Auto-generated method stub
			System.out.println("회원가입 어씽크테스크에 진입 했따.");
			String str;
			String sendMsg, receiveMsg = null;
			String response = null;
			try {
				URL url = new URL("보낼 jsp경로");
				HttpURLConnection conn = (HttpURLConnection) url.openConnection();
				conn.setRequestMethod("POST");//데이터를 POST 방식으로 전송
				conn.setDoInput(true);
				conn.setDoOutput(true);
				//보낼 정보 ( 아이디 와 패스워드)
				sendMsg = "action=LoginUserId="+joinId.getText().toString()+"&pw="+joinPass.getText().toString()+
						"&name="+joinName.getText().toString()+"&Dept="+joinDept.getText().toString()+"&Area="+joinArea.getText().toString();
				
				OutputStreamWriter osw = new OutputStreamWriter(conn.getOutputStream());
				osw.write(sendMsg);
				osw.flush();
				
				//jsp와 통신이 정상적으로 되었을 때 
				if(conn.getResponseCode() == conn.HTTP_OK){
					InputStreamReader tmp = new InputStreamReader(conn.getInputStream(),"UTF-8");
					BufferedReader reader = new BufferedReader(tmp);
					StringBuffer buffer = new StringBuffer();
					//jsp에서 보낸 값을 받는다
					while((str =reader.readLine())!=null){
						buffer.append(str);
					}
					receiveMsg = buffer.toString();
					
				}else{
					Log.i("통신결과",conn.getResponseCode()+"에러");
				}
				
				
//				BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(conn.getOutputStream(),"UTF-8"));
				
				
			} catch (MalformedURLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			return response;
		}
		
		@Override
		protected void onPostExecute(String result) {
			// TODO Auto-generated method stub
			super.onPostExecute(result);
		}
		
	}
	
	
	
	
	
}
