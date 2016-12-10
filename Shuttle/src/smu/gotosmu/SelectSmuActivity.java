package smu.gotosmu;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import smu.gotohome.SelectHomeActivity;
import smu.shuttle.MainActivity;
import smu.shuttle.R;
import smu.shuttle.R.id;
import smu.shuttle.R.layout;
import smu.shuttle.R.menu;

public class SelectSmuActivity extends Activity implements OnClickListener{

	//버튼 설정
	private Button goToSmuFromCheonan,goToSmuFromAsan,goToSmuFromTerminal;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_select_smu);
		
		goToSmuFromCheonan = (Button) findViewById(R.id.goToSmuFromCheonan);
		goToSmuFromCheonan.setOnClickListener(this);
		
		goToSmuFromAsan = (Button) findViewById(R.id.goToSmuFromAsan);
		goToSmuFromAsan.setOnClickListener(this);
		
		goToSmuFromTerminal = (Button) findViewById(R.id.goToSmuFromTerminal);
		goToSmuFromTerminal.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.goToSmuFromCheonan://천안발 버튼
			Intent i = new Intent(this,ToSmuFromCheonanActivity.class);
			startActivity(i);
//			finish();
			break;
			
		case R.id.goToSmuFromAsan://아산발 버튼
			Intent i2 = new Intent(this,ToSmuFromAsanActivity.class);
			startActivity(i2);
//			finish();
			break;
		case R.id.goToSmuFromTerminal://터미널발 버튼
			Intent i3 = new Intent(this,ToSmuFromTerminalActivity.class);
			startActivity(i3);
//			finish();
			break;
		default:
			break;
		}
	}

	
}
