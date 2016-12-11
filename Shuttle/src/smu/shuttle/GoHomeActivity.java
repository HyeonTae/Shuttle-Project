package smu.shuttleapp.home;

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

public class GoHomeActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_go_home);
	}

	public void forAsan(View v) {
		Intent i = new Intent(GoHomeActivity.this, SearchForAsanActivity.class);
		startActivity(i);
	}
	
	public void forCheonAn(View v) {
		Intent i = new Intent(GoHomeActivity.this, SearchForCheonAnActivity.class);
		startActivity(i);
	}
	
	public void forTerminal(View v) {
		Intent i = new Intent(GoHomeActivity.this, SearchForTerminalActivity.class);
		startActivity(i);
	}
}
