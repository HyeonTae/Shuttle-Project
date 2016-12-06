package smu.shuttle;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends Activity {

	private Button loginBut;
	private Button joinBut;
	private EditText userId;
	private EditText userPass;
	SharedPreferences sp;
	SharedPreferences.Editor ed;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		
		loginBut = (Button) findViewById(R.id.loginBut);
		joinBut = (Button) findViewById(R.id.joinBut);
		
		userId = (EditText) findViewById(R.id.id_input);
		userPass = (EditText) findViewById(R.id.pass_input);
		
	}

	//로그인버튼 클릭시
	public void loginBut(View v){
		
		String id = userId.getText().toString().trim();
		String pass = userPass.getText().toString().trim();
		
		//테스트
		System.out.println(id);
		System.out.println(pass);
		//만약 아이디와 패스워드가 같다면 -> 로그인 성공
		
		//로그인화면에서 아이디와 패스워드가 모두 입력시
		if(!id.isEmpty() && !pass.isEmpty()){
			new LoginAsincTask().execute();
			Toast.makeText(getApplicationContext(), "로그인버튼을 눌렀습니다", Toast.LENGTH_SHORT).show();
			//바로 메인액티비티로 넘어간다
			Intent i = new Intent(LoginActivity.this,MainActivity.class);
			startActivity(i);
			finish();
		}else{
			//아이디와 비밀번호가 빈칸일 시 토스트로 알림을 해준다
			Toast.makeText(getApplicationContext(), "아아디와 비밀번호를 다시 확인 해주세요", Toast.LENGTH_SHORT).show();
		}
		
	}
	
	//회원가입버튼 클릭시
	public void joinBut(View v){
		
		Toast.makeText(getApplicationContext(), "회원가입버튼을 눌렀습니다", Toast.LENGTH_SHORT).show();
		Intent i = new Intent(LoginActivity.this,JoinActivity.class);
		startActivity(i);
		finish();
	}
	
	
	
	public class LoginAsincTask extends AsyncTask<String, Void, String>{

		@Override
		protected String doInBackground(String... params) {
			// TODO Auto-generated method stub
			System.out.println("로그인 어씽크테스크에 진입 했따.");
			String str="";
			String sendMsg, receiveMsg = null;
			String response = null;
			try {
				URL url = new URL("보낼 jsp경로");
				HttpURLConnection conn = (HttpURLConnection) url.openConnection();
				conn.setRequestMethod("POST");//데이터를 POST 방식으로 전송
				conn.setDoInput(true);
				conn.setDoOutput(true);
				
				//보낼 정보 ( 아이디 와 패스워드)
				sendMsg = "action=LoginUserId="+userId.getText().toString()+"&pw="+userPass.getText().toString();
				OutputStreamWriter osw = new OutputStreamWriter(conn.getOutputStream());
				osw.write(sendMsg);
				osw.flush();
				osw.close();
				
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
