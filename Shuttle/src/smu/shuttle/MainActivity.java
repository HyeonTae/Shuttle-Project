package smu.shuttle;

import java.text.SimpleDateFormat;
import java.util.Date;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity implements OnClickListener {
	TextView tv;
	Button totalTimeBtn, nearTimeBtn;
	//현재시간을 msec으로 구한다
	long now = System.currentTimeMillis();
	Date date = new Date(now);
	SimpleDateFormat dateFomat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
	String formatDate = dateFomat.format(date);
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		totalTimeBtn = (Button) findViewById(R.id.totalTimeBtn);
		nearTimeBtn = (Button) findViewById(R.id.nearTimeBtn);
		tv = (TextView) findViewById(R.id.textView1);
		totalTimeBtn.setOnClickListener(this);
		nearTimeBtn.setOnClickListener(this);
	
	
		
		tv.setText(formatDate);
	}
	
	public void onClick(View v){
		Toast.makeText(this, "토탈 타임클릭", Toast.LENGTH_SHORT).show();
		Intent i = new Intent(this, TotalTimeActivity.class);
		startActivity(i);
	}
	
}
