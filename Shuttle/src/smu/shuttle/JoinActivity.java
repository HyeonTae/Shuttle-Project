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
		
	}
	
	//취소하기버튼 클릭시
	public void joinCancelBut(View v){
		Toast.makeText(getApplicationContext(), "취소하기버튼을 눌렀습니다", Toast.LENGTH_SHORT).show();
		Intent i = new Intent(JoinActivity.this,LoginActivity.class);
		startActivity(i);
		finish();
	}
}
