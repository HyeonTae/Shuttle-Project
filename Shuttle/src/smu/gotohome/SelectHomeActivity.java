package smu.gotohome;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import smu.gotosmu.ToSmuFromAsanActivity;
import smu.gotosmu.ToSmuFromCheonanActivity;
import smu.shuttle.R;
import smu.shuttle.R.id;
import smu.shuttle.R.layout;
import smu.shuttle.R.menu;

public class SelectHomeActivity extends Activity implements OnClickListener {

	private Button goToHomeFromStationBtn, goToHomeFromTerminalBtn;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_select_home);

		goToHomeFromStationBtn = (Button) findViewById(R.id.goToHomeFromStationBtn);
		goToHomeFromStationBtn.setOnClickListener(this);

		goToHomeFromTerminalBtn = (Button) findViewById(R.id.goToHomeFromTerminalBtn);
		goToHomeFromTerminalBtn.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.goToHomeFromStationBtn:// 학교에서 역으로 버튼
			Intent i = new Intent(this, ToHomeFromStationActivity.class);
			startActivity(i);
			// finish();
			break;

		case R.id.goToHomeFromTerminalBtn:// 학교에서 터미널 버튼
			Intent i2 = new Intent(this, ToHomeFromTerminalActivity.class);
			startActivity(i2);
			// finish();
			break;
		}
	}

}
