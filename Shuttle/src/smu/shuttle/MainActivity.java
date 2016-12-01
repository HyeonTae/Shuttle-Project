package smu.shuttle;

import android.app.ActionBar;
import android.app.ActionBar.Tab;
import android.app.ActionBar.TabListener;
import android.app.Activity;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import smu.custom.CustomAdapter;

public class MainActivity extends Activity {
	ViewPager pager; 
	ActionBar actionBar; 
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		actionBar = getActionBar();
		actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
		
		//페이저 (밀어서 화면 전환)
		pager = (ViewPager) findViewById(R.id.pager);
		CustomAdapter adapter = new CustomAdapter(getLayoutInflater());
		pager.setAdapter(adapter);
		pager.setOnPageChangeListener(new OnPageChangeListener() {
			@Override
			public void onPageSelected(int position) {
				// TODO Auto-generated method stub
				actionBar.setSelectedNavigationItem(position);
			}
			@Override
			public void onPageScrolled(int arg0, float arg1, int arg2) {
				// TODO Auto-generated method stub
			}
			@Override
			public void onPageScrollStateChanged(int arg0) {
				// TODO Auto-generated method stub
			}
		});
		Tab tab = null;
		tab = actionBar.newTab();
		tab.setText("가장 가까운 시간표"); 
		tab.setTabListener(listener);
		actionBar.addTab(tab);
		tab = actionBar.newTab(); 
		tab.setText("천안/아산역"); 
		tab.setTabListener(listener);
		actionBar.addTab(tab);
		tab = actionBar.newTab(); 
		tab.setText("터미널"); 
		tab.setTabListener(listener);
		actionBar.addTab(tab);
	}
	TabListener listener = new TabListener() {
		@Override
		public void onTabUnselected(Tab tab, FragmentTransaction ft) {
			// TODO Auto-generated method stub
		}
		@Override
		public void onTabSelected(Tab tab, FragmentTransaction ft) {
			// TODO Auto-generated method stub
			int position = tab.getPosition();
			pager.setCurrentItem(position, true);
		}
		@Override
		public void onTabReselected(Tab tab, FragmentTransaction ft) {
			// TODO Auto-generated method stub
		}
	};
}
