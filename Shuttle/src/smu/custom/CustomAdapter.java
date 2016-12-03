package smu.custom;

import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import smu.shuttle.R;

public class CustomAdapter extends PagerAdapter {

	LayoutInflater inflater;
	public CustomAdapter(LayoutInflater inflater) {
		// TODO Auto-generated constructor stub
		this.inflater = inflater;
	}
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return 3; 
	}
	@Override
	public Object instantiateItem(ViewGroup container, int position) {
		// TODO Auto-generated method stub
		View view = null;
		switch (position) {
		case 0: 
			view = inflater.inflate(R.layout.activity_near_time, null);
			break;
		case 1: 
			view = inflater.inflate(R.layout.activity_station, null);
			break;
		case 2:
			view = inflater.inflate(R.layout.activity_terminal, null);
			break;
		}
		if (view != null)
			container.addView(view);
		return view;
	}
	@Override
	public void destroyItem(ViewGroup container, int position, Object object) {
		// TODO Auto-generated method stub
		container.removeView((View) object);
	}
	@Override
	public boolean isViewFromObject(View v, Object obj) {
		// TODO Auto-generated method stub
		return v == obj;
	}
}
