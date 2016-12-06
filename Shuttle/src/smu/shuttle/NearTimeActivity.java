package smu.shuttle;

import java.text.SimpleDateFormat;
import java.util.Date;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class NearTimeActivity extends Fragment {

	TextView tv;
	//현재 시간을 msec로 구한다.
	long now = System.currentTimeMillis();
	//현재시간을 nowTime에 저장
	Date date = new Date(now);
	//시간을 나타내는 포맷을 정한다
	SimpleDateFormat sdfNow = new SimpleDateFormat("HH:mm");
	 
	String formatTime = sdfNow.format(date);

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View v = inflater.inflate(R.layout.activity_near_time, container,false);
		View tv=v.findViewById(R.id.textView1);
		((TextView)tv).setText("sdfkj");
		
		return v;
	}

}
