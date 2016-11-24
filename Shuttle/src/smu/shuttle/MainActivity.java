package smu.shuttle;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
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
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		totalTimeBtn = (Button) findViewById(R.id.totalTimeBtn);
		nearTimeBtn = (Button) findViewById(R.id.nearTimeBtn);
		
		totalTimeBtn.setOnClickListener(this);
		nearTimeBtn.setOnClickListener(this);
	}
	
	public void onClick(View v){
		Toast.makeText(this, "토탈 타임클릭", Toast.LENGTH_SHORT).show();
		Intent i = new Intent(this, TotalTimeActivity.class);
		startActivity(i);
	}
	
}
