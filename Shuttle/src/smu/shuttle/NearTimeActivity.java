package smu.shuttle;

import java.text.SimpleDateFormat;
import java.util.Date;

import android.app.Activity;
import android.app.Fragment;
import android.content.pm.ActivityInfo;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

public class NearTimeActivity extends Activity {

	TextView tv;
	//현재 시간을 msec로 구한다.
	long now = System.currentTimeMillis();
	//현재시간을 nowTime에 저장
	Date date = new Date(now);
	//시간을 나타내는 포맷을 정한다
	SimpleDateFormat sdfNow = new SimpleDateFormat("HH:mm");
	String formatTime = sdfNow.format(date);

	////////////////////////////////////////////
	@Override
	protected void onCreate(Bundle arg0) {
		// TODO Auto-generated method stub
		super.onCreate(arg0);
	} 

}
