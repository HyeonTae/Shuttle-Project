package smu.shuttle;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.Buffer;

import com.fasterxml.jackson.databind.ObjectMapper;

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
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;
import smu.model.Class;

public class LoginActivity extends Activity {

	private Button loginBut;
	private Button joinBut;
	private EditText classId;
	private EditText classPass;
	SharedPreferences sp;
	SharedPreferences.Editor ed;
	private CheckBox autolog;
	private static final String NO_DATA= "NO_DATA";
	ObjectMapper mapper = new ObjectMapper();
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		
		String atlogId;
		String atlogPass;
		
		sp = getSharedPreferences("sp", MODE_PRIVATE);
		ed = sp.edit();
		
		loginBut = (Button) findViewById(R.id.loginBut);
		joinBut = (Button) findViewById(R.id.joinBut);
		
		classId = (EditText) findViewById(R.id.id_input);
		classPass = (EditText) findViewById(R.id.pass_input);
		
		atlogId = sp.getString("autoid", NO_DATA);
		atlogPass = sp.getString("autopass", NO_DATA);
		
		if(!atlogId.equals(NO_DATA)&&!atlogPass.equals(NO_DATA)){
			Intent i = new Intent(LoginActivity.this,MainActivity.class);
			startActivity(i);
			finish();
		}
	}

	//로그인버튼 클릭시
	public void loginBut(View v){
		
		String id = classId.getText().toString().trim();
		String pass = classPass.getText().toString().trim();
		
		//테스트
		System.out.println(id);
		System.out.println(pass);
		//만약 아이디와 패스워드가 같다면 -> 로그인 성공
		
		//로그인화면에서 아이디와 패스워드가 모두 입력시
		if(!id.isEmpty() && !pass.isEmpty()){
			new LoginAsincTask().execute();
			Toast.makeText(getApplicationContext(), "로그인버튼을 눌렀습니다", Toast.LENGTH_SHORT).show();
//			//바로 메인액티비티로 넘어간다
//			Intent i = new Intent(LoginActivity.this,MainActivity.class);
//			startActivity(i);
//			finish();
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

		Class c;
		@Override
		protected String doInBackground(String... params) {
			// TODO Auto-generated method stub
			System.out.println("로그인 어씽크테스크에 진입 했따.");
			String str="";
			String sendMsg, receiveMsg = null;
			String response = null;
			StringBuilder sb = new StringBuilder();
			try {
				URL url = new URL("http://192.168.0.6:8060/ShuttleSever/main.do");
				HttpURLConnection conn = (HttpURLConnection) url.openConnection();
				conn.setRequestMethod("POST");//데이터를 POST 방식으로 전송
				conn.setDoInput(true);
				conn.setDoOutput(true);
				
				//보낼 정보 ( 아이디 와 패스워드)
				sendMsg = "action=LoginToClass&id="+classId.getText().toString()+"&pass="+classPass.getText().toString();
				
				BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(conn.getOutputStream(),"UTF-8"));
				bw.write(sendMsg);
				bw.flush();
				bw.close();
				
				BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream(),"UTF-8"));
				while((str=br.readLine()) != null){
					sb.append(str);
				}
				c = mapper.readValue(sb.toString(), Class.class);
				
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
			if(c!=null){
				if(autolog.isChecked()){
					ed.putString("autoid", classId.getText().toString());
					ed.putString("autopass", classPass.getText().toString());
					ed.putString("id", c.getClassId());
					ed.putString("pass",c.getClassPass() );
					ed.putString("name", c.getClassName());
					ed.putString("dept", c.getClassDept());
					ed.putString("area", c.getClassArea());
					ed.commit();
					Intent i = new Intent(LoginActivity.this,MainActivity.class);
					startActivity(i);
					finish();

				}else{
					ed.putString("id", c.getClassId());
					ed.putString("pass",c.getClassPass() );
					ed.putString("name", c.getClassName());
					ed.putString("dept", c.getClassDept());
					ed.putString("area", c.getClassArea());
					ed.commit();
					Intent i = new Intent(LoginActivity.this,MainActivity.class);
					startActivity(i);
					finish();
				}
			}else{
				Toast.makeText(getApplicationContext(), "계정을 다시 확인해주세요", Toast.LENGTH_SHORT).show();
			}
			super.onPostExecute(result);
		}
		
	}
}
