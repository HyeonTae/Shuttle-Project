package smu.shuttleapp.smu;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import smu.shuttleapp.R;
import smu.shuttleapp.R.id;
import smu.shuttleapp.R.layout;
import smu.shuttleapp.R.menu;

public class GoSMUActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_go_smu);
	}

	public void fromAsan(View v) {
		Intent i = new Intent(GoSMUActivity.this, SearchFromAsanActivity.class);
		startActivity(i);
	}
	
	public void fromCheonAn(View v) {
		Intent i = new Intent(GoSMUActivity.this, SearchFromCheonAnActivity.class);
		startActivity(i);
	}
	
	public void fromTerminal(View v) {
		Intent i = new Intent(GoSMUActivity.this, SearchFromTerminalActivity.class);
		startActivity(i);
	}
}
