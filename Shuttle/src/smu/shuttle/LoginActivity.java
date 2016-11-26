package smu.shuttle;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
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
		Toast.makeText(getApplicationContext(), "로그인버튼을 눌렀습니다", Toast.LENGTH_SHORT).show();
		//만약 아이디와 패스워드가 같다면 -> 로그인 성공
		
		//바로 메인액티비티로 넘어간다
		Intent i = new Intent(LoginActivity.this,MainActivity.class);
		startActivity(i);
		finish();
	}
	
	//회원가입버튼 클릭시
	public void joinBut(View v){
		
		Toast.makeText(getApplicationContext(), "회원가입버튼을 눌렀습니다", Toast.LENGTH_SHORT).show();
		Intent i = new Intent(LoginActivity.this,JoinActivity.class);
		startActivity(i);
		finish();
	}
}
