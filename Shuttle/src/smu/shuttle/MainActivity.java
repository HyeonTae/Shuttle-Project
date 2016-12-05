package smu.shuttle;

import android.app.ActionBar;
import android.app.Activity;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import smu.custom.TabListener;

public class MainActivity extends Activity {
//	ViewPager pager; 
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
//		setContentView(R.layout.activity_main);
		final ActionBar actionBar = getActionBar();
		actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
		
//		//페이저 (밀어서 화면 전환)
//		pager = (ViewPager) findViewById(R.id.pager);
//		CustomAdapter adapter = new CustomAdapter(getLayoutInflater());
//		pager.setAdapter(adapter);
//		pager.setOnPageChangeListener(new OnPageChangeListener() {
//			@Override
//			public void onPageSelected(int position) {
//				// TODO Auto-generated method stub
//				actionBar.setSelectedNavigationItem(position);
//			}
//			@Override
//			public void onPageScrolled(int arg0, float arg1, int arg2) {
//				// TODO Auto-generated method stub
//			}
//			@Override
//			public void onPageScrollStateChanged(int arg0) {
//				// TODO Auto-generated method stub
//			}
//		});
//		Tab tab = null;
//		tab = actionBar.newTab();
//		tab.setText("가장 가까운 시간표");
//		tab.setTabListener(listener);
//		actionBar.addTab(tab);
//		tab = actionBar.newTab();
//		tab.setText("천안/아산역");
//		tab.setTabListener(listener);
//		actionBar.addTab(tab);
//		tab = actionBar.newTab();
//		tab.setText("터미널"); 
//		tab.setTabListener(listener);
//		actionBar.addTab(tab);
		actionBar.addTab(actionBar.newTab().setText("가장가까운시간표").setTabListener(new TabListener<NearTimeActivity>(this, "rr",NearTimeActivity.class)));
		actionBar.addTab(actionBar.newTab().setText("천안아산역").setTabListener(new TabListener<StationActivity>(this, "rrr",StationActivity.class)));
		actionBar.addTab(actionBar.newTab().setText("터미널").setTabListener(new TabListener<TerminalActivity>(this, "r",TerminalActivity.class)));
	
		
	}
	@Override
		protected void onSaveInstanceState(Bundle outState) {
			// TODO Auto-generated method stub
			super.onSaveInstanceState(outState);
		}
}
